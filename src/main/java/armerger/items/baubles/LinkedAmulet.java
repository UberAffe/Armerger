package armerger.items.baubles;

import armerger.items.interfaces.LinkedBauble;
import armerger.lib.RefStrings;

public class LinkedAmulet extends LinkedBauble{

	private static final String name = "linkedamulet";
	
	public LinkedAmulet()
	{
		super();
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}
}
