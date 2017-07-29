package melonslise.runicinscription.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWritingTable extends ModelBase
{
  //fields
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Top;
    ModelRenderer Joint;
    ModelRenderer Torch;
    ModelRenderer Quill;
    ModelRenderer Feather;
    ModelRenderer Book;
  
  public ModelWritingTable()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Leg1 = new ModelRenderer(this, 0, 0);
      Leg1.addBox(0F, 0F, 0F, 4, 2, 12);
      Leg1.setRotationPoint(-8F, 22F, -6F);
      Leg1.setTextureSize(128, 64);
      Leg1.mirror = true;
      setRotation(Leg1, 0F, 0F, 0F);
      Leg2 = new ModelRenderer(this, 32, 0);
      Leg2.addBox(0F, 0F, 0F, 4, 2, 12);
      Leg2.setRotationPoint(4F, 22F, -6F);
      Leg2.setTextureSize(128, 64);
      Leg2.mirror = true;
      setRotation(Leg2, 0F, 0F, 0F);
      Leg3 = new ModelRenderer(this, 64, 0);
      Leg3.addBox(0F, 0F, 0F, 2, 11, 10);
      Leg3.setRotationPoint(-7F, 11F, -5F);
      Leg3.setTextureSize(128, 64);
      Leg3.mirror = true;
      setRotation(Leg3, 0F, 0F, 0F);
      Leg4 = new ModelRenderer(this, 88, 0);
      Leg4.addBox(0F, 0F, 0F, 2, 11, 10);
      Leg4.setRotationPoint(5F, 11F, -5F);
      Leg4.setTextureSize(128, 64);
      Leg4.mirror = true;
      setRotation(Leg4, 0F, 0F, 0F);
      Top = new ModelRenderer(this, 0, 14);
      Top.addBox(0F, 0F, 0F, 16, 3, 16);
      Top.setRotationPoint(-8F, 8F, -8F);
      Top.setTextureSize(128, 64);
      Top.mirror = true;
      setRotation(Top, 0F, 0.0174533F, 0F);
      Joint = new ModelRenderer(this, 64, 21);
      Joint.addBox(0F, 0F, 0F, 16, 2, 8);
      Joint.setRotationPoint(-8F, 16F, -4F);
      Joint.setTextureSize(128, 64);
      Joint.mirror = true;
      setRotation(Joint, 0F, 0F, 0F);
      Torch = new ModelRenderer(this, 112, 0);
      Torch.addBox(0F, 0F, 0F, 2, 6, 2);
      Torch.setRotationPoint(-7F, 2F, -1F);
      Torch.setTextureSize(128, 64);
      Torch.mirror = true;
      setRotation(Torch, 0F, 0F, 0F);
      Quill = new ModelRenderer(this, 120, 0);
      Quill.addBox(0F, 0F, 0F, 2, 2, 2);
      Quill.setRotationPoint(5F, 6F, -4F);
      Quill.setTextureSize(128, 64);
      Quill.mirror = true;
      setRotation(Quill, 0F, 0F, 0F);
      Feather = new ModelRenderer(this, 112, 8);
      Feather.addBox(0F, 0F, 0F, 1, 2, 1);
      Feather.setRotationPoint(6F, 4F, -3.5F);
      Feather.setTextureSize(128, 64);
      Feather.mirror = true;
      setRotation(Feather, 0F, 0F, 0.1115358F);
      Book = new ModelRenderer(this, 108, 20);
      Book.addBox(0F, 0F, 0F, 4, 2, 6);
      Book.setRotationPoint(5.5F, 6F, 0F);
      Book.setTextureSize(128, 64);
      Book.mirror = true;
      setRotation(Book, 0F, -1.041001F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Leg1.render(f5);
    Leg2.render(f5);
    Leg3.render(f5);
    Leg4.render(f5);
    Top.render(f5);
    Joint.render(f5);
    Torch.render(f5);
    Quill.render(f5);
    Feather.render(f5);
    Book.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

  public void renderModel(float f)
  {
	    Leg1.render(f);
	    Leg2.render(f);
	    Leg3.render(f);
	    Leg4.render(f);
	    Top.render(f);
	    Joint.render(f);
	    Torch.render(f);
	    Quill.render(f);
	    Feather.render(f);
	    Book.render(f);
  }
}
