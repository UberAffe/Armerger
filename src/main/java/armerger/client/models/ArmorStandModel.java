package armerger.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ArmorStandModel extends ModelBase{
	
	//fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer base;
    ModelRenderer post;
  
  public ArmorStandModel()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      head = new ModelRenderer(this, 40, 16);
      head.addBox(-4F, -8F, -4F, 4, 2, 2);
      head.setRotationPoint(2F, 2F, 3F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 48, 0);
      body.addBox(-4F, 0F, -2F, 2, 12, 2);
      body.setRotationPoint(3F, -4F, 1F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 18, 16);
      rightarm.addBox(-3F, -2F, -2F, 7, 2, 2);
      rightarm.setRotationPoint(-5F, 2F, 1F);
      rightarm.setTextureSize(64, 32);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 0, 16);
      leftarm.addBox(-1F, -2F, -2F, 7, 2, 2);
      leftarm.setRotationPoint(2F, 2F, 1F);
      leftarm.setTextureSize(64, 32);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 12, 12);
      rightleg.addBox(-2F, 0F, -2F, 4, 2, 2);
      rightleg.setRotationPoint(-2F, 12F, 1F);
      rightleg.setTextureSize(64, 32);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 0, 12);
      leftleg.addBox(-2F, 0F, -2F, 4, 2, 2);
      leftleg.setRotationPoint(2F, 12F, 1F);
      leftleg.setTextureSize(64, 32);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      base = new ModelRenderer(this, 0, 0);
      base.addBox(0F, 0F, 0F, 10, 2, 10);
      base.setRotationPoint(-5F, 22F, -6F);
      base.setTextureSize(64, 32);
      base.mirror = true;
      setRotation(base, 0F, 0F, 0F);
    post = new ModelRenderer(this, 40, 0);
    post.addBox(0F, 0F, 0F, 2, 14, 2);
    post.setRotationPoint(-1F, 8F, -1F);
    setRotation(post, 0F, 0F, 0F);
    post.mirror = true;
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    body.render(f5);
    rightarm.render(f5);
    leftarm.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
    base.render(f5);
    post.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}
