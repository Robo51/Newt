package newt;

import newt.blocks.ModBlocks;
import newt.crafting.CraftingItems;
import newt.items.ModItems;
import newt.lib.Constants;
import newt.proxy.CommonProxy;
import newt.world.NewtWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

//NeWt

@Mod(modid = Constants.MODID, name = Constants.MODNAME, version = Constants.VERSION)
public class NeWt {

    @SidedProxy(clientSide = Constants.CLIENT_PROXY_CLASS, serverSide = Constants.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		
		ModItems.init();		
		ModBlocks.init();
		GameRegistry.registerFuelHandler(new NewtFuel());
		GameRegistry.registerWorldGenerator(new NewtWorldGenerator(), 10);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		CraftingItems.init();
        proxy.registerTileEntities();
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		
	}
}
