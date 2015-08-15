package armerger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import armerger.lib.RefStrings;

@Mod(modid = RefStrings.MODID, name = RefStrings.NAME, version = RefStrings.VERSION, useMetadata = true)
public class Armerger {

	@Instance(RefStrings.MODID)
    public static Armerger instance;
	
	@EventHandler
    public void preInit (FMLPreInitializationEvent event){
        // preinit
	}
	
	@EventHandler
    public void init (FMLInitializationEvent event){
        // init
	}
	
	@EventHandler
    public void postInit (FMLPostInitializationEvent event){
        // postinit
	}
}
