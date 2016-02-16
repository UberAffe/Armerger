package armerger.items.armor;

import armerger.items.interfaces.LinkedArmor;
import armerger.lib.RefStrings;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class LinkedLegs extends LinkedArmor{
	
	private static final String name = "linkedlegs";
	public static final LinkedLegs linkedlegs = new LinkedLegs();

	public LinkedLegs() {
		super(ArmorMaterial.valueOf(RefStrings.ARMORMATERIAL), 0, 2);
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}
	
	@Override
	public void acceptInfo(NBTTagCompound[] info, ItemStack itemStack)
	{
		System.out.println("Legs got info");
	}
}
