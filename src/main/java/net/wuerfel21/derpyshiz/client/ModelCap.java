package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCap extends ModelBiped {

	public ModelRenderer cap = new ModelRenderer(this, 0, 0);

	public ModelCap() {
		this(0.0F);
	}

	public ModelCap(float whatever) {
		super(whatever);
		this.cap.setRotationPoint(0, 0, 0);
		this.cap.setTextureOffset(0, 16).addBox(-4f, -6f, -10.5f, 8, 1, 5, whatever);
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
		this.cap.render(something);
	}

	@Override
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
		super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
		this.cap.rotateAngleX = this.bipedHead.rotateAngleX;
		this.cap.rotateAngleY = this.bipedHead.rotateAngleY;
	}
	
}
