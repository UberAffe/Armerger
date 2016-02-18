package armerger.items.baubles;

import armerger.items.interfaces.LinkedBauble;
import armerger.lib.RefStrings;

public class LinkedRing extends LinkedBauble{

private static final String name = "linkedring";
	
	public LinkedRing()
	{
		super();
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}
}
