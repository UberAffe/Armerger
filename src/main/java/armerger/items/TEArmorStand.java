package armerger.items;

import armerger.entity.GhostPlayer;
import armerger.items.armor.LinkedArmor;
import armerger.items.armor.LinkedBoots;
import armerger.items.armor.LinkedChest;
import armerger.items.armor.LinkedHelm;
import armerger.items.armor.LinkedLegs;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TEArmorStand extends TileEntity  implements IEnergyHandler{

	protected EnergyStorage storage;
	
	private TEArmorStand[] linkedStands;
	private ItemStack[] linkedArmor;
	public ItemStack[] heldArmor;
	private EntityLiving ghost;
	private boolean ischild = false;
	private boolean isparent = false;
	
	public TEArmorStand(World world)
	{
		storage = new EnergyStorage(0, Integer.MAX_VALUE);
		heldArmor = new ItemStack[8];
		linkedArmor = new ItemStack[8];
		linkedStands = new TEArmorStand[10];
		ghost = new GhostPlayer(world, this);
	}
	
	public void setGhost(GhostPlayer ghost){this.ghost = ghost;}
	public boolean isChildStand(){return ischild;}
	public boolean isParentStand(){return isparent;}
	public boolean becomeParent(){return true;}
	public boolean containsType(ItemStack type){return (type.getItem() instanceof ItemArmor);}
	
	public void sendTickInfoToLinkedArmor()
	{
		NBTTagCompound[][] info = getTickInfoFromChildren();
		NBTTagCompound[] temp = this.getTickInfo();
		for(int i = 0; i < 8; i++)
			info[i][0] = temp[i];
		for(int i = 0; i < 8; i++)
		{
			((LinkedArmor)linkedArmor[i].getItem()).acceptInfo(info[i]);
		}
	}
	
	private NBTTagCompound[][] getTickInfoFromChildren() {
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
		info[1] = ghost.getEquipmentInSlot(3).getTagCompound();
		info[2] = ghost.getEquipmentInSlot(2).getTagCompound();
		info[3] = ghost.getEquipmentInSlot(1).getTagCompound();
		return info;
	}

	public boolean onActivate(World world, int x, int y, int z, EntityPlayer player)
	{
		ItemStack heldStack = player.getHeldItem();
		if(heldStack == null)
		{
			openGUI();
		}
		else if(heldStack.getItem() instanceof ItemArmor)
		{
			Item held = heldStack.getItem();
			if(held == LinkedHelm.linkedhelm)
			{
				heldArmor[0] = heldStack.copy();
			}
			else if(held == LinkedChest.linkedchest)
			{
				heldArmor[1] = heldStack.copy();
			}
			else if(held == LinkedLegs.linkedlegs)
			{
				heldArmor[2] = heldStack.copy();
			}
			else if(held == LinkedBoots.linkedboots)
			{
				heldArmor[3] = heldStack.copy();
			}
			player.destroyCurrentEquippedItem();
		}
		return false;
	}

	private void openGUI() {
		// TODO Auto-generated method stub
		
	}
	
	public void onBlockPlaced(World world, int x, int y, int z)
	{
		placeGhost();
	}

	private void placeGhost() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		nbt.setTag("ghost", ghost.getEntityData());
	}

	/* IEnergyConnection */
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {

		return true;
	}

	/* IEnergyReceiver */
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {

		return storage.receiveEnergy(maxReceive, simulate);
	}

	/* IEnergyProvider */
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {

		return storage.extractEnergy(maxExtract, simulate);
	}

	/* IEnergyReceiver and IEnergyProvider */
	@Override
	public int getEnergyStored(ForgeDirection from) {

		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {

		return storage.getMaxEnergyStored();
	}

}
