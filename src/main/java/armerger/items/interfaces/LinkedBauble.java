package armerger.items.interfaces;


import armerger.items.TEArmorStand;
import armerger.lib.RefStrings;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract class LinkedBauble extends Item implements ILinked, IBauble{
	private TEArmorStand parentStand;
	
	public LinkedBauble()
	{
		super();
	}
	
	@Override
	public boolean isLinked(){return parentStand != null;}
	@Override
	public void removeLink() {parentStand = null;}
	
	public boolean createLink(TEArmorStand parentStand)
	{
		if(this.parentStand != null)
			return false;
		this.parentStand = parentStand;
		return true;
	}
	
	@Override
	public void acceptInfo(NBTTagCompound[] info, ItemStack itemStack) {
		System.out.println("baubles accept your offering");
		
	}
	
	@Override
	public void doLivingHurtEvent(LivingHurtEvent event) {
		System.out.println("Doing bauble things");
		
	}
	
	public void readFromNBT(NBTTagCompound nbt) 
	{
		if(nbt.hasKey(RefStrings.LINKEDSTANDS))
		{
		   int[] pos = nbt.getIntArray(RefStrings.LINKEDSTANDS);
		   parentStand = (TEArmorStand) MinecraftServer.getServer().getEntityWorld().getTileEntity(pos[0], pos[1], pos[2]);
		}
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setIntArray(RefStrings.LINKEDSTANDS, new int[]{parentStand.xCoord, parentStand.yCoord, parentStand.zCoord});
	}

	@Override
	public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
		if(arg1 instanceof EntityPlayer)
			return true;
		return false;
	}

	@Override
	public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
		return true;
	}

	@Override
	public void onEquipped(ItemStack arg0, EntityLivingBase arg1) {
		if(arg1 instanceof EntityPlayer)
			parentStand.setEquiped(arg0, arg1);
	}

	@Override
	public void onUnequipped(ItemStack arg0, EntityLivingBase arg1) {}

	@Override
	public void onWornTick(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public NBTTagCompound sendOnTickEffects() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
