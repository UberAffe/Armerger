package armerger.items.armor;

import armerger.items.interfaces.LinkedArmor;
import armerger.lib.RefStrings;
import net.minecraft.entity.EntityLiving;

public class LinkedHelm extends LinkedArmor{

	private static final String name = "linkedhelm";
	public static final LinkedHelm linkedhelm = new LinkedHelm();
	
	public LinkedHelm() {
		super(ArmorMaterial.valueOf(RefStrings.ARMORMATERIAL), 0, 0);
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}

	@Override
	public void setGhost(EntityLiving ghost) {
		// TODO Auto-generated method stub
		
	}
}
