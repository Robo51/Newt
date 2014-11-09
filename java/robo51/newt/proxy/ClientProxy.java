package robo51.newt.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import robo51.newt.blocks.ModBlocks;
import robo51.newt.entity.EntityCyborg;
import robo51.newt.model.ModelCyborg;
import robo51.newt.renderer.ItemRenderNeuralCellBlock;
import robo51.newt.renderer.RenderCyborg;
import robo51.newt.renderer.RenderNeuralCellBlock;
import robo51.newt.tileentities.TileEntityNeuralCellBlock;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
    public void registerRenderThings() {
    	//Neural Cell
    	TileEntitySpecialRenderer render = new RenderNeuralCellBlock();
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNeuralCellBlock.class, render);
    	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.neuralcellBlock), new ItemRenderNeuralCellBlock(render, new TileEntityNeuralCellBlock()));
    	
    	//Entities
    	RenderingRegistry.registerEntityRenderingHandler(EntityCyborg.class, new RenderCyborg(new ModelCyborg(), 0.3F));
    }
    
    public void registerTileEntitySpecialRenderer() {
    	
    }
}
