package armerger.items.baubles;

import armerger.items.interfaces.LinkedBauble;
import armerger.lib.RefStrings;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class LinkedAmulet extends LinkedBauble{

	private static final String name = "linkedamulet";
	
	public LinkedAmulet()
	{
		super();
		setUnlocalizedName(RefStrings.MODID + "_" + name);
		setTextureName(RefStrings.MODID + ":" + name);
	}
	
	@Override
	public void acceptInfo(NBTTagCompound[] info, ItemStack itemStack)
	{
		System.out.println("Amulet got info");
	}
}
