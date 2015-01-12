package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTopHat extends ModelBiped {

	public ModelRenderer hat = new ModelRenderer(this, 0, 0);

	public ModelTopHat() {
		this(0.0F);
	}

	public ModelTopHat(float whatever) {
		super(whatever);
		this.hat.setRotationPoint(0, 0, 0);
		this.hat.setTextureOffset(0, 16).addBox(-3f, -16f, -3f, 6, 6, 6, whatever);
	}
	
	@Override
	public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float something) {
		super.render(entity, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, something);
		this.bipedBody.isHidden = true;
		this.bipedCloak.isHidden = true;
		this.bipedEars.isHidden = true;
		this.bipedLeftArm.isHidden = true;
		this.bipedLeftLeg.isHidden = true;
		this.bipedRightArm.isHidden = true;
		this.bipedRightLeg.isHidden = true;
		this.hat.render(something);
	}

	@Override
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
		super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
		this.hat.rotateAngleX = this.bipedHead.rotateAngleX;
		this.hat.rotateAngleY = this.bipedHead.rotateAngleY;
	}
	
}
