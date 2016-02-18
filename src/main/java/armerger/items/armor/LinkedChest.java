package armerger.items.armor;

import armerger.items.interfaces.LinkedArmor;
import armerger.lib.RefStrings;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class LinkedChest extends LinkedArmor{

	private static final String name = "linkedchest";
	public static final LinkedChest linkedchest = new LinkedChest();
	
	public LinkedChest() {
		super(ArmorMaterial.valueOf(RefStrings.ARMORMATERIAL), 0, 1);
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}

	@Override
	public void acceptInfo(NBTTagCompound info)
	{
		System.out.println("Chest got passive stats");
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		System.out.println("chest ticking");
		if(this.parentStand != null && itemStack.getTagCompound().getBoolean(RefStrings.RUNNING))
		{
			System.out.println("chest running on tick");
		}
			
	}

	public void startEffects(ItemStack itemStack) {itemStack.getTagCompound().setBoolean(RefStrings.RUNNING, true);}

	public void stopEffects(ItemStack itemStack) {itemStack.getTagCompound().setBoolean(RefStrings.RUNNING, false);}

	@Override
	public void doLivingHurtEvent(LivingHurtEvent event) {System.out.println("providing on-hit support");}

	@Override
	public NBTTagCompound sendOnTickEffects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGhost(EntityLiving ghost) {
		// TODO Auto-generated method stub
		
	}
}
