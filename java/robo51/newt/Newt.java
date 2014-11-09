package robo51.newt;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import robo51.newt.blocks.ModBlocks;
import robo51.newt.entity.EntityCyborg;
import robo51.newt.gui.GuiWaterBar;
import robo51.newt.handlers.CraftingHandler;
import robo51.newt.handlers.EntityHandler;
import robo51.newt.handlers.EventsHandler;
import robo51.newt.handlers.FuelHandler;
import robo51.newt.handlers.GuiHandler;
import robo51.newt.handlers.NewtMessage;
import robo51.newt.handlers.NewtMessageHandler;
import robo51.newt.items.ModItems;
import robo51.newt.lib.Constants;
import robo51.newt.proxy.CommonProxy;
import robo51.newt.world.NewtWorldGenerator;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Constants.MODID, name = Constants.MODNAME, version = Constants.VERSION)

public class Newt {
	
    @SidedProxy(clientSide = Constants.CLIENT_PROXY_CLASS, serverSide = Constants.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
	
    public static CreativeTabs tabNewt = new CreativeTabsNewt("NewtTab");
    
    public static SimpleNetworkWrapper snw;
    
    @Instance(Constants.MODID)
    public static Newt instance;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
		ModBlocks.init();
		ModItems.init();
		GameRegistry.registerFuelHandler(new FuelHandler());
		GameRegistry.registerWorldGenerator(new NewtWorldGenerator(), 10);
		
		EntityHandler.registerMonsters(EntityCyborg.class, "Cyborg");
		
		proxy.registerRenderThings();
		
		snw = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.MODID);
		snw.registerMessage(NewtMessageHandler.class, NewtMessage.class, 0, Side.CLIENT); 
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    
		CraftingHandler.init();
        proxy.registerTileEntities();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(new EventsHandler());
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
 
    	if (FMLCommonHandler.instance().getEffectiveSide().isClient())
    	MinecraftForge.EVENT_BUS.register(new GuiWaterBar(Minecraft.getMinecraft()));
    }
    
}