package robo51.newt.proxy;

import net.minecraft.entity.player.EntityPlayer;
import robo51.newt.tileentities.TileEntityFurnaceBlock;
import robo51.newt.tileentities.TileEntityNeuralCellBlock;
import robo51.newt.tileentities.TileEntityPowerCellBlock;
import robo51.newt.tileentities.TileEntityPresserBlock;
import robo51.newt.tileentities.TileEntitySolarCellBlock;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void registerTileEntities() {
    	 
        GameRegistry.registerTileEntity(TileEntityPresserBlock.class, TileEntityPresserBlock.publicName);
        GameRegistry.registerTileEntity(TileEntitySolarCellBlock.class, TileEntitySolarCellBlock.publicName);
        GameRegistry.registerTileEntity(TileEntityPowerCellBlock.class, TileEntityPowerCellBlock.publicName);
        GameRegistry.registerTileEntity(TileEntityFurnaceBlock.class, TileEntityFurnaceBlock.publicName);
        GameRegistry.registerTileEntity(TileEntityNeuralCellBlock.class, TileEntityNeuralCellBlock.publicName);        
    }
    
    public void registerRenderThings() {
    	
    }
    
    public void registerTileEntitySpecialRenderer() {
    	
    }

    public EntityPlayer getPlayerEntity(MessageContext ctx) {
    	return ctx.getServerHandler().playerEntity;
    }
}
