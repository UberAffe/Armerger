package armerger.items.armor;

import armerger.items.interfaces.LinkedArmor;
import armerger.lib.RefStrings;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class LinkedHelm extends LinkedArmor{

	private static final String name = "linkedhelm";
	public static final LinkedHelm linkedhelm = new LinkedHelm();
	
	public LinkedHelm() {
		super(ArmorMaterial.valueOf(RefStrings.ARMORMATERIAL), 0, 0);
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}

	@Override
	public void acceptInfo(NBTTagCompound[] info, ItemStack itemStack)
	{
		System.out.println("Helm got info");
		System.out.println("linked helms:");
	}
}
