package robo51.newt.renderer;

import robo51.newt.entity.EntityCyborg;
import robo51.newt.lib.Constants;
import robo51.newt.model.ModelCyborg;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderCyborg extends RenderLiving {
	
	private static final ResourceLocation texture = new ResourceLocation(Constants.MODID + ":" + "textures/model/cyborgEntity.png");
	
	protected ModelCyborg modelEntity;

	public RenderCyborg(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		modelEntity = ((ModelCyborg) mainModel);

	}
	
	public void renderCyborg(EntityCyborg entity, double x, double y, double z, float u, float v) {
		super.doRender(entity, x, y, z, u, v);
	}
	
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float u, float v) {
		renderCyborg((EntityCyborg)entityLiving, x, y, z, u, v);
	}
	
	public void doRender(Entity entity, double x, double y, double z, float u, float v) {
		renderCyborg((EntityCyborg)entity, x, y, z, u, v);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return texture;
	}
}