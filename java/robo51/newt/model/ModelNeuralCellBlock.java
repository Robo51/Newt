package robo51.newt.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNeuralCellBlock extends ModelBase {
  //fields
    ModelRenderer tabletop;
    ModelRenderer legone;
    ModelRenderer legtwo;
    ModelRenderer legthree;
    ModelRenderer legfour;
  
  public ModelNeuralCellBlock() {
    textureWidth = 64;
    textureHeight = 64;
    
      tabletop = new ModelRenderer(this, 0, 0);
      tabletop.addBox(0F, 0F, 0F, 16, 3, 16);
      tabletop.setRotationPoint(-8F, 9F, -8F);
      tabletop.setTextureSize(64, 64);
      tabletop.mirror = true;
      setRotation(tabletop, 0F, 0F, 0F);
      legone = new ModelRenderer(this, 0, 19);
      legone.addBox(0F, 0F, 0F, 3, 12, 3);
      legone.setRotationPoint(5F, 12F, -8F);
      legone.setTextureSize(64, 64);
      legone.mirror = true;
      setRotation(legone, 0F, 0F, 0F);
      legtwo = new ModelRenderer(this, 12, 19);
      legtwo.addBox(0F, 0F, 0F, 3, 12, 3);
      legtwo.setRotationPoint(-8F, 12F, -8F);
      legtwo.setTextureSize(64, 64);
      legtwo.mirror = true;
      setRotation(legtwo, 0F, 0F, 0F);
      legthree = new ModelRenderer(this, 24, 19);
      legthree.addBox(0F, 0F, 0F, 3, 12, 3);
      legthree.setRotationPoint(5F, 12F, 5F);
      legthree.setTextureSize(64, 64);
      legthree.mirror = true;
      setRotation(legthree, 0F, 0F, 0F);
      legfour = new ModelRenderer(this, 36, 19);
      legfour.addBox(0F, 0F, 0F, 3, 12, 3);
      legfour.setRotationPoint(-8F, 12F, 5F);
      legfour.setTextureSize(64, 64);
      legfour.mirror = true;
      setRotation(legfour, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    tabletop.render(f5);
    legone.render(f5);
    legtwo.render(f5);
    legthree.render(f5);
    legfour.render(f5);
  }
  
  public void renderModel(Float f) {
	  tabletop.render(f);
	  legone.render(f);
	  legtwo.render(f);
	  legthree.render(f);
	  legfour.render(f);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}