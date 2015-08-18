package armerger.proxy;

import armerger.client.renderer.ArmorStandRenderer;
import armerger.items.TEArmorStand;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{

	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TEArmorStand.class, new ArmorStandRenderer());
	}
}
