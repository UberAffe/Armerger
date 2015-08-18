package armerger.items.armor;

import armerger.lib.RefStrings;
import net.minecraft.item.ItemArmor;

public class LinkedBoots extends ItemArmor{

	private static final String name = "linkedboots";
	public static final LinkedBoots linkedboots = new LinkedBoots();

	public LinkedBoots() {
		super(ArmorMaterial.valueOf(RefStrings.ARMORMATERIAL), 0, 3);
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}
}
