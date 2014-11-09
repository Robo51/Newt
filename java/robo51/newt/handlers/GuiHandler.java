package robo51.newt.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import robo51.newt.blocks.ModBlocks;
import robo51.newt.container.ContainerFurnaceBlock;
import robo51.newt.container.ContainerPresserBlock;
import robo51.newt.gui.GuiFurnaceBlock;
import robo51.newt.gui.GuiPresserBlock;
import robo51.newt.tileentities.TileEntityFurnaceBlock;
import robo51.newt.tileentities.TileEntityPresserBlock;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null) {
			switch(ID) {
			case ModBlocks.guiIdFurnaceBlock:
				if (entity instanceof TileEntityFurnaceBlock) {
					return new ContainerFurnaceBlock(player.inventory, (TileEntityFurnaceBlock) entity);
				}
				return null;
				
			case ModBlocks.guiIdPresserBlock:
				if (entity instanceof TileEntityPresserBlock) {
					return new ContainerPresserBlock(player.inventory, (TileEntityPresserBlock) entity);
				}
				return null;
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null) {
			switch(ID) {
			case ModBlocks.guiIdFurnaceBlock:
				if (entity instanceof TileEntityFurnaceBlock) {
					return new GuiFurnaceBlock(player.inventory, (TileEntityFurnaceBlock) entity);
				}
				return null;
				
			case ModBlocks.guiIdPresserBlock:
				if (entity instanceof TileEntityPresserBlock) {
					return new GuiPresserBlock(player.inventory, (TileEntityPresserBlock) entity);
				}
				return null;
			}
		}
		return null;
	}

}
