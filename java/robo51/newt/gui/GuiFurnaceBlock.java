package robo51.newt.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import robo51.newt.container.ContainerFurnaceBlock;
import robo51.newt.lib.Constants;
import robo51.newt.tileentities.TileEntityFurnaceBlock;

public class GuiFurnaceBlock extends GuiContainer {
	
	public static final ResourceLocation bground = new ResourceLocation(Constants.MODID + ":" + "textures/gui/GuiFurnaceBlock.png");
	public TileEntityFurnaceBlock furnaceBlock;

	public GuiFurnaceBlock(InventoryPlayer inventoryPlayer,TileEntityFurnaceBlock entity) {
		super(new ContainerFurnaceBlock(inventoryPlayer, entity));
		
		this.furnaceBlock = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		String name = "Furnace";
		
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 118, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if(this.furnaceBlock.hasPower()) {
			int k = this.furnaceBlock.getBurnTimeRemainingScaled(41);
			int j = 41 - k;
			drawTexturedModalRect(guiLeft + 29, guiTop + 65, 176, 0, 41 - j, 10);
		}
		
		int k = this.furnaceBlock.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 78, guiTop + 35, 176, 10, k + 1, 16);
	}
}