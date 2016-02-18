package armerger.items.baubles;

import armerger.items.interfaces.LinkedBauble;
import armerger.lib.RefStrings;

public class LinkedBelt extends LinkedBauble{

private static final String name = "linkedbelt";
	
	public LinkedBelt()
	{
		super();
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}
}
