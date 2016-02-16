package armerger.items.interfaces;

import armerger.items.TEArmorStand;
import armerger.lib.RefStrings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract class LinkedArmor extends ItemArmor implements ILinked{

	protected TEArmorStand parentStand = null;
	
	public LinkedArmor(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player)
	{
		stack.stackTagCompound = new NBTTagCompound();
	}
	
	@Override
	public void doLivingHurtEvent(LivingHurtEvent event)
	{
		System.out.println("doing armor things");
	}
	
	@Override
	public NBTTagCompound sendOnTickEffects()
	{
		return null;
	}
	
	@Override
	public boolean createLink(TEArmorStand parentStand)
	{
		if(parentStand != null)
			return false;
		this.parentStand = parentStand;
		return true;
	}
	
	@Override
	public void removeLink() {this.parentStand = null;}
	
	@Override
	public boolean isLinked()
	{
		return parentStand != null;
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
}
