package armerger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
//import armerger.proxy.ClientProxy;
import armerger.proxy.CommonProxy;
import armerger.lib.RefStrings;

@Mod(modid = RefStrings.MODID, /*name = RefStrings.NAME,*/ version = RefStrings.VERSION/*, dependencies = RefStrings.REQUIRED, useMetadata = true*/)
public class Armerger {

	@Instance(RefStrings.MODID)
    public static Armerger instance;
	
	@SidedProxy(clientSide = RefStrings.CLIENTPROXY, serverSide = RefStrings.SERVERPROXY)
    public static CommonProxy proxy;
	
	@EventHandler
    public void preInit (FMLPreInitializationEvent event){
		proxy.preInit();
	}
	
	@EventHandler
    public void init (FMLInitializationEvent event){
        // init
		proxy.init();
	}
	
	@EventHandler
    public void postInit (FMLPostInitializationEvent event){
        proxy.postInit();
	}
}
