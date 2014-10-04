package Newt;

import Newt.blocks.ModBlocks;
import Newt.crafting.CraftingItems;
import Newt.fuel.NewtFuel;
import Newt.items.ModItems;
import Newt.lib.Constants;
import Newt.proxy.CommonProxy;
import Newt.world.NewtWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Constants.MODID, name = Constants.MODNAME, version = Constants.VERSION)

public class Newt {
	
    @SidedProxy(clientSide = Constants.CLIENT_PROXY_CLASS, serverSide = Constants.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
	
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
		ModBlocks.init();
		ModItems.init();
		GameRegistry.registerFuelHandler(new NewtFuel());
		GameRegistry.registerWorldGenerator(new NewtWorldGenerator(), 10);
    }
 
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    	
		CraftingItems.init();
        proxy.registerTileEntities();
    }
 
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
 
    }
}
