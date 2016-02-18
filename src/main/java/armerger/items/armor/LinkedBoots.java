package armerger.items.armor;

import armerger.items.interfaces.LinkedArmor;
import armerger.lib.RefStrings;
import net.minecraft.entity.EntityLiving;

public class LinkedBoots extends LinkedArmor{

	private static final String name = "linkedboots";
	public static final LinkedBoots linkedboots = new LinkedBoots();

	public LinkedBoots() {
		super(ArmorMaterial.valueOf(RefStrings.ARMORMATERIAL), 0, 3);
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}

	@Override
	public void setGhost(EntityLiving ghost) {
		// TODO Auto-generated method stub
		
	}
}
