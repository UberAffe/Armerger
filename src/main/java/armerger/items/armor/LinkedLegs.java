package armerger.items.armor;

import armerger.lib.RefStrings;
import net.minecraft.item.ItemArmor;

public class LinkedLegs extends ItemArmor{
	
	private static final String name = "linkedlegs";
	public static final LinkedLegs linkedlegs = new LinkedLegs();

	public LinkedLegs() {
		super(ArmorMaterial.valueOf(RefStrings.ARMORMATERIAL), 0, 2);
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}
}
