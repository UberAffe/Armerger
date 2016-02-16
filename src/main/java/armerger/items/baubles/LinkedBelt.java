package armerger.items.baubles;

import armerger.items.interfaces.LinkedBauble;
import armerger.lib.RefStrings;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class LinkedBelt extends LinkedBauble{

private static final String name = "linkedbelt";
	
	public LinkedBelt()
	{
		super();
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}
	
	@Override
	public void acceptInfo(NBTTagCompound[] info, ItemStack itemStack)
	{
		System.out.println("Belt got info");
	}
}
