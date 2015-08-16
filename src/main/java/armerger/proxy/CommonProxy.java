package armerger.proxy;

import armerger.items.TEArmorStand;
import armerger.items.ArmorStand;
import armerger.lib.RefStrings;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy {
	
	public void registerRenderers(){}
	
	public void registerBlocks()
	{
		GameRegistry.registerBlock(ArmorStand.armorstand, RefStrings.MODID + "_" + "armorstand");
	}
	
	public void registerTileEntitys()
	{
		GameRegistry.registerTileEntity(TEArmorStand.class, RefStrings.MODID + "_" + "armorstand");
	}
	
	public void init()
	{
		
	}
	
	public void postInit()
	{
		
	}
}
