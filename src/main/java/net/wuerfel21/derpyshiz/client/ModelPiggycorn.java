package net.wuerfel21.derpyshiz.client;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelPiggycorn extends ModelPig {
	
	public ModelRenderer horn = new ModelRenderer(this, 0, 0);
	
	public ModelPiggycorn() {
        this(0.0F);
    }
	
	public ModelPiggycorn(float whatever) {
		super(whatever);
		this.horn.setRotationPoint(0.0F, (float)(18 - 6), -6.0F);
		this.horn.setTextureOffset(0, 26).addBox(-2f,-5f,-6f,4,1,4,whatever);
		this.horn.setTextureOffset(16, 24).addBox(-1f,-10f,-5f,2,5,2,whatever);
	}
	

	@Override
	public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float something) {
		super.render(entity, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, something);
		if (this.isChild) {
			//do nothing, child piggycorns dont have their horns grown
		} else {
			this.horn.render(something);
		}
	}
	
	@Override
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
		super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
		this.horn.rotateAngleX = this.head.rotateAngleX;
		this.horn.rotateAngleY = this.head.rotateAngleY;
	}
	
}
