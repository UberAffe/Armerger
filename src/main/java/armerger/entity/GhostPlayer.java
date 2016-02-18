package armerger.entity;

import armerger.items.TEArmorStand;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class GhostPlayer extends EntityLiving{

	private TEArmorStand containingStand;
	
	public GhostPlayer(World world, TEArmorStand containingStand) {
		super(world);
		this.containingStand = containingStand;
	}
	
	public ItemStack getItemInSlot(int index)
	{
		return containingStand.getStackInSlot(index);
	}
	
	@Override
	public void onEntityUpdate()
    {
        super.onEntityUpdate();
        this.worldObj.theProfiler.startSection("mobBaseTick");
        	if(containingStand.isParentStand())        		
        		containingStand.sendTickInfoToLinkedArmor(getEffects());
        this.worldObj.theProfiler.endSection();
    }
	
	//Store any passive effects and the number of effects
	private NBTTagCompound getEffects() {
		
		return null;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return true;
	}
	
	@Override
	protected void dropEquipment(boolean p_82160_1_, int p_82160_2_)
    {
        for (int j = 0; j < 4; ++j)
        {
            ItemStack itemstack = this.getEquipmentInSlot(j);
            if(itemstack != null)
            	this.entityDropItem(itemstack, 0.0F);
        }
    }
	
	@Override
	public void playLivingSound()
    {
    }

}
