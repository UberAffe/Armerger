package armerger.items;

import armerger.entity.GhostPlayer;
import armerger.items.armor.LinkedChest;
import armerger.items.interfaces.ILinked;
import armerger.items.interfaces.LinkedArmor;
import armerger.lib.RefStrings;
import baubles.api.IBauble;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TEArmorStand extends TileEntity  implements IEnergyHandler, IInventory{

	protected EnergyStorage storage;
	
	private TEArmorStand[] linkedStands;
	private ItemStack[] linkedArmor;
	private EntityPlayer[] equippedBy;
	private ItemStack[] inventory;
	private EntityLiving ghost;
	private boolean ischild = false;
	private boolean isparent = false;
	
	public TEArmorStand(World world)
	{
		storage = new EnergyStorage(0, Integer.MAX_VALUE);
		inventory = new ItemStack[8];
		linkedArmor = new ItemStack[8];
		linkedStands = new TEArmorStand[10];
		equippedBy = new EntityPlayer[8];
		ghost = new GhostPlayer(world, this);
	}
	
	public void setGhost(GhostPlayer ghost){this.ghost = ghost;}
	public boolean isChildStand(){return ischild;}
	public boolean isParentStand(){return isparent;}
	public boolean becomeParent(){return true;}
	public boolean containsType(ItemStack type){return (type.getItem() instanceof ItemArmor || type.getItem() instanceof IBauble);}		

	private void openGUI() 
	{
		this.openInventory();
	}
	
	public void onBlockPlaced(World world, int x, int y, int z)
	{
		placeGhost();
	}

	//ghost is only used for rendering
	private void placeGhost() 
	{
		// TODO Auto-generated method stub
	}
	
	//------------------------
	// Load and Save Methods
	//------------------------

	@Override
	public void readFromNBT(NBTTagCompound nbt) 
	{
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		ghost = (EntityLiving) MinecraftServer.getServer().getEntityWorld().getEntityByID(nbt.getInteger(RefStrings.GHOSTTAG));
		this.blockMetadata = nbt.getInteger(RefStrings.METADATATAG);
		World world = MinecraftServer.getServer().getEntityWorld();
		int[] uuids = nbt.getIntArray(RefStrings.EQUIPPEDUUIDS);
		NBTTagCompound held = nbt.getCompoundTag(RefStrings.HELDARMOR);
		NBTTagCompound linked = nbt.getCompoundTag(RefStrings.LINKEDARMOR);
		NBTTagCompound stands = nbt.getCompoundTag(RefStrings.LINKEDSTANDS);
		for(int i = 0; i < 8; i++)
		{
			//entities
			Entity ent = (uuids[i] != -1) ? world.getEntityByID(uuids[i]) : null;
			equippedBy[i] = (ent instanceof EntityPlayer) ? ((EntityPlayer)ent) : null;
			//heldArmor
			inventory[i] = (held.hasKey(RefStrings.HELDARMOR + i)) ? ItemStack.loadItemStackFromNBT(held.getCompoundTag(RefStrings.HELDARMOR + i)) : null;
			//linkedArmor
			linkedArmor[i] = (linked.hasKey(RefStrings.LINKEDARMOR + i)) ? ItemStack.loadItemStackFromNBT(held.getCompoundTag(RefStrings.LINKEDARMOR + i)) : null;
		}
		for(int i = 0; i < 10; i++)
		{
			if(stands.hasKey(RefStrings.LINKEDSTANDS + i))
			{
				int[] pos = stands.getIntArray(RefStrings.LINKEDSTANDS + i);
				TileEntity ent = world.getTileEntity(pos[0], pos[1], pos[2]);
				linkedStands[i] = (ent instanceof TEArmorStand) ? ((TEArmorStand)ent) : null;
			}
			else
				linkedStands[i] = null;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) 
	{
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		nbt.setInteger(RefStrings.GHOSTTAG, ghost.getEntityId());
		nbt.setInteger(RefStrings.METADATATAG, this.blockMetadata);
		int[] uuids = new int[8];
		NBTTagCompound held = new NBTTagCompound();
		NBTTagCompound linked = new NBTTagCompound();
		NBTTagCompound nbttagcompound1;
		for(int i = 0; i < 8; i++)
		{
			//entities
			uuids[i] = (equippedBy[i] != null) ? equippedBy[i].getEntityId() : -1;
			//heldArmor
			if(inventory[i] != null) 
			{
				nbttagcompound1 = new NBTTagCompound();
                inventory[i].writeToNBT(nbttagcompound1);
                held.setTag(RefStrings.HELDARMOR + i, nbttagcompound1);
			}
			//linkedArmor
			if(linkedArmor[i] != null)
			{
				nbttagcompound1 = new NBTTagCompound();
                inventory[i].writeToNBT(nbttagcompound1);
                linked.setTag(RefStrings.LINKEDARMOR + i, nbttagcompound1);
			}
		}
		nbt.setIntArray(RefStrings.EQUIPPEDUUIDS, uuids);
		nbt.setTag(RefStrings.HELDARMOR, held);
		nbt.setTag(RefStrings.LINKEDARMOR, linked);
		NBTTagCompound stands = new NBTTagCompound();
		for(int i = 0; i < 10; i++)
		{
			if(linkedStands[i] != null)
				stands.setIntArray(RefStrings.LINKEDSTANDS + 1, new int[]{linkedStands[i].xCoord, linkedStands[i].yCoord,linkedStands[i].zCoord});
		}
		nbt.setTag(RefStrings.LINKEDSTANDS, stands);
	}
	
	//--------------------------
	// Custom Methods
	//--------------------------
	
	/*
	 * Only for use by Parent Stands, should only be called by a single linked armor piece
	 */
	public void sendTickInfoToLinkedArmor()
	{
		NBTTagCompound[][] info = getTickInfoFromChildren();
		NBTTagCompound[] temp = this.getTickInfo();
		for(int i = 0; i < 8; i++)
			info[i][0] = temp[i];
		for(int i = 0; i < 8; i++)
		{
			((LinkedArmor)linkedArmor[i].getItem()).acceptInfo(info[i], linkedArmor[i]);
		}
	}
	
	private NBTTagCompound[][] getTickInfoFromChildren() 
	{
		NBTTagCompound[][] info = new NBTTagCompound[8][11];
		for(int i = 0; i < 10; i++)
		{
			NBTTagCompound[] temp = linkedStands[i].getTickInfo();
			for(int j = 0; j < 8; j++)
				info[j][i + 1] = temp[j];
		}
		return info;
	}
	
	public NBTTagCompound[] getTickInfo()
	{
		NBTTagCompound[] info = new NBTTagCompound[8];
		info[0] = ghost.getEquipmentInSlot(4).getTagCompound();
		info[0].setString(RefStrings.ITEMNAMETAG, ghost.getEquipmentInSlot(4).toString());
		info[1] = ghost.getEquipmentInSlot(3).getTagCompound();
		info[1].setString(RefStrings.ITEMNAMETAG, ghost.getEquipmentInSlot(3).toString());
		info[2] = ghost.getEquipmentInSlot(2).getTagCompound();
		info[2].setString(RefStrings.ITEMNAMETAG, ghost.getEquipmentInSlot(2).toString());
		info[3] = ghost.getEquipmentInSlot(1).getTagCompound();
		info[3].setString(RefStrings.ITEMNAMETAG, ghost.getEquipmentInSlot(1).toString());
		return info;
	}

	public boolean onActivate(World world, int x, int y, int z, EntityPlayer player)
	{
		boolean toReturn = tryPlacingArmor(world,x,y,z,player);
		if((!this.isparent || !this.ischild) && this.isReadyToLink())
			createLinkedStandArmor();
		return toReturn;
	}
		
	private void createLinkedStandArmor() {
		System.arraycopy(inventory, 0, linkedArmor, 0, 8);
		for(ItemStack linked : linkedArmor)
		{
			if(linked != null)
			{
			Item lItem = linked.getItem();
			lItem.getClass().isInstance(lItem);
			if(ILinked.class.isInstance(lItem))
				((ILinked)lItem).createLink(this);
			}
		}
	}

	private boolean isReadyToLink() {
		//for(ItemStack held : heldArmor)
		for(int i = 0; i < 4; i++)// this for loop is until I have bauble implemented
		{
			ItemStack held = inventory[i];
			if(held == null)
			{
				return false;
			}
			else
			{
				Item checking = held.getItem();
				if(!ILinked.class.isInstance(checking) || ((ILinked)checking).isLinked())
					return false;
			}
			
		}
		return true;
	}

	private boolean tryPlacingArmor(World world, int x, int y, int z, EntityPlayer player)
	{
		ItemStack heldStack = player.getHeldItem();
		if(heldStack == null)
		{
			openGUI();
		}
		else if(heldStack.getItem() instanceof ItemArmor)
		{
			Item held = heldStack.getItem();
			if(((ItemArmor)held).armorType == 0)
			{
				if(inventory[0] == null)
				{
					inventory[0] = heldStack.copy();
					player.destroyCurrentEquippedItem();
					return true;
				}
			}
			else if(((ItemArmor)held).armorType == 1)
			{
				if(inventory[1] == null)
				{
					inventory[1] = heldStack.copy();
					player.destroyCurrentEquippedItem();
					return true;
				}
			}
			else if(((ItemArmor)held).armorType == 2)
			{
				if(inventory[2] == null)
				{
					inventory[2] = heldStack.copy();
					player.destroyCurrentEquippedItem();
					return true;
				}
			}
			else if(((ItemArmor)held).armorType == 3)
			{
				if(inventory[3] == null)
				{
					inventory[3] = heldStack.copy();
					player.destroyCurrentEquippedItem();
					return true;
				}
			}
			else
				return false;
			/*else if(heldStack.getItem() instanceof LinkedBauble)
			{
				Item held1 = heldStack.getItem();
				if(((LinkedBauble)held1).getBaubleType(heldStack) == BaubleType.AMULET)
				{
					if(heldArmor[4] == null)
					{
						heldArmor[4] = heldStack.copy();
						player.destroyCurrentEquippedItem();
						return true;
					}
				}
				else if(((LinkedBauble)held1).getBaubleType(heldStack) == BaubleType.RING)
				{
					if(heldArmor[5] == null || heldArmor[6] == null)
					{
						if(heldArmor[5] == null)
							heldArmor[5] = heldStack.copy();
						else
							heldArmor[6] = heldStack.copy();
						player.destroyCurrentEquippedItem();
						return true;
					}
				}
				else if(((LinkedBauble)held1).getBaubleType(heldStack) == BaubleType.BELT)
				{
					if(heldArmor[7] == null)
					{
						heldArmor[7] = heldStack.copy();
						player.destroyCurrentEquippedItem();
						return true;
					}
				}
				else 
					return false;
			}*/
		}
		return false;
	}
	
	public void setEquiped(ItemStack linkedStack, EntityLivingBase player) {
		int pos = findPosition(linkedStack, linkedArmor);
		if(pos != -1)
		{
			equippedBy[pos] = (EntityPlayer) player;
			if(wearingFull())
				((LinkedChest)linkedArmor[1].getItem()).startEffects(linkedArmor[1]);
		}
	}
	
	public void removeEquiped(ItemStack linkedStack)
	{
		int pos = findPosition(linkedStack, linkedArmor);
		if(pos != -1)
		{
			equippedBy[pos] = null;
			((LinkedChest)linkedArmor[1].getItem()).stopEffects(linkedArmor[1]);
		}
	}

	private boolean wearingFull() {
		if(equippedBy[0] != null)
		{
		EntityPlayer player = equippedBy[0];
		for(int i = 0; i < 8; i++)
			if(equippedBy[i] == null || equippedBy[i] != player)
				return false;
		return true;
		}
		return false;
	}

	private int findPosition(ItemStack thing, ItemStack[] arr) {
		for(int i = 0; i < arr.length; i ++)
		{
			if(arr[i].equals(thing))
				return i;
		}
		return -1;
	}
	
	//--------------------------
	// RF Energy Methods
	//--------------------------
	
	/* IEnergyConnection */
	@Override
	public boolean canConnectEnergy(ForgeDirection from) 
	{
		return true;
	}

	/* IEnergyReceiver */
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) 
	{
		return storage.receiveEnergy(maxReceive, simulate);
	}

	/* IEnergyProvider */
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) 
	{
		return storage.extractEnergy(maxExtract, simulate);
	}

	/* IEnergyReceiver and IEnergyProvider */
	@Override
	public int getEnergyStored(ForgeDirection from) 
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) 
	{
		return storage.getMaxEnergyStored();
	}
	
	

	//--------------------------
	// Inventory Methods
	//--------------------------
	
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
	        return null;
	    return this.inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.getStackInSlot(index) != null) 
		{
	        ItemStack itemstack;
	        if (this.getStackInSlot(index).stackSize <= count) {
	            itemstack = this.getStackInSlot(index);
	            this.setInventorySlotContents(index, null);
	            this.markDirty();
	            return itemstack;
	        } 
	        else 
	        {
	            itemstack = this.getStackInSlot(index).splitStack(count);
	            if (this.getStackInSlot(index).stackSize <= 0) {
	                this.setInventorySlotContents(index, null);
	            } else {
	                //Just to show that changes happened
	                this.setInventorySlotContents(index, this.getStackInSlot(index));
	            }
	            this.markDirty();
	            return itemstack;
	        }
    	} 
		else 
		{
	        return null;
	    }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		ItemStack stack = this.getStackInSlot(index);
	    this.setInventorySlotContents(index, null);
	    return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory())
	        return;
	    if (stack != null && stack.stackSize > this.getInventoryStackLimit())
	        stack.stackSize = this.getInventoryStackLimit();   
	    if (stack != null && stack.stackSize == 0)
	        stack = null;
	    this.inventory[index] = stack;
	    this.markDirty();
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this 
				&& player.getDistanceSq(this.xCoord + .5, this.yCoord + .5, this.zCoord + .5) <= 64;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		Item item = stack.getItem();
		if(stack.getItem() instanceof ItemArmor)
			if(((ItemArmor)item).armorType == index) //helm in slot 0, boots in slot 3
				return true;		
		else if(item instanceof IBauble)
		{
			;
		}
		return false;
	}

}
