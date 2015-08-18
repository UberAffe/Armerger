package armerger.items.armor;

import armerger.lib.RefStrings;
import net.minecraft.item.ItemArmor;

public class LinkedHelm extends ItemArmor{

	private static final String name = "linkedhelm";
	public static final LinkedHelm linkedhelm = new LinkedHelm();
	
	public LinkedHelm() {
		super(ArmorMaterial.valueOf(RefStrings.ARMORMATERIAL), 0, 0);
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}

}
