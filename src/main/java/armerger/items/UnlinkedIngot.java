package armerger.items;

import armerger.lib.RefStrings;
import net.minecraft.item.Item;

public class UnlinkedIngot extends Item{

	public static final UnlinkedIngot unlinkedingot = new UnlinkedIngot();
	private static final String name = "unlinkedingot";
	
	public UnlinkedIngot()
	{
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}
	
}
