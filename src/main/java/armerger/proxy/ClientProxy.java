package armerger.proxy;

import armerger.client.renderer.ArmorStandRenderer;
import armerger.items.TEArmorStand;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void preInit()
	{
		super.preInit();
		registerRenderers();
	}
	
	@Override
	public void init()
	{
		super.init();
	}
	
	@Override
	public void postInit()
	{
		
	}
	
	@Override
	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TEArmorStand.class, new ArmorStandRenderer());
	}
}
