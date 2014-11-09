package robo51.newt.gui;

import org.lwjgl.opengl.GL11;

import robo51.newt.entity.ExtendedPlayerWater;
import robo51.newt.lib.Constants;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class GuiWaterBar extends Gui {
	
	private Minecraft mc;
	
	private static final ResourceLocation texturepath = new ResourceLocation(Constants.MODID + ":" + "textures/gui/GuiWaterBar.png");
	
	public GuiWaterBar(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {
		if (event.isCancelable() || event.type != ElementType.EXPERIENCE) {
			return;
		}
		
		ExtendedPlayerWater props = ExtendedPlayerWater.get(this.mc.thePlayer);
		
		if (props == null || props.getMaxWaterSealing() == 0) {
			return;
		}
		
		int xPos = 2;
		int yPos = 2;
				
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);;
		
		this.mc.getTextureManager().bindTexture(texturepath);
		this.drawTexturedModalRect(xPos, yPos, 0, 0, 55, 9);
		
		int waterbarwidth = (int)(((float) props.getCurrentWaterSealing() / props.getMaxWaterSealing()) * 49);
		
		this.drawTexturedModalRect(xPos + 3, yPos + 3, 0, 9, waterbarwidth, 3);
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
	}
}