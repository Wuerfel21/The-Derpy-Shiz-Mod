package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPiggycorn extends RenderPig {
	
	private static final ResourceLocation pigTextures = new ResourceLocation("derpyshiz:textures/entity/piggycorn/piggycorn.png");
	
	public RenderPiggycorn(ModelBase par1, ModelBase par2, float par3) {
		super(par1,par2,par3);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityPig p_110775_1_)
    {
        return pigTextures;
    }
	
}
