package newt.proxy;

import newt.tileentities.TileEntityPresserBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void registerTileEntities() {
    	 
        GameRegistry.registerTileEntity(TileEntityPresserBlock.class, TileEntityPresserBlock.publicName);
    }
}