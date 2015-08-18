package armerger.items.armor;

import armerger.lib.RefStrings;
import net.minecraft.item.ItemArmor;

public class LinkedChest extends ItemArmor{

	private static final String name = "linkedchest";
	public static final LinkedChest linkedchest = new LinkedChest();
	
	public LinkedChest() {
		super(ArmorMaterial.valueOf(RefStrings.ARMORMATERIAL), 0, 1);
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}

}
