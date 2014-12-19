package net.wuerfel21.derpyshiz.entity.tile;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.wuerfel21.derpyshiz.client.DerpyRenderHelper;

import com.google.common.collect.EvictingQueue;

public class TileEntitySeizureWool extends TileEntity {
	
	public EvictingQueue rects = EvictingQueue.create(5);
	public int color = 0;
	
	public static Random rand = new Random();
	
	public TileEntitySeizureWool() {
		super();
	}
	
	public String getName() {return "ds_seizure_wool";}
	
	@Override
	public void updateEntity() {;
		rects.add(new SeizureRect(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), rand.nextInt(16)));
		this.color = rand.nextInt(16);
	}
	
	public class SeizureRect {
		
		public int color;
		public double minX,minY,maxX,maxY;
		
		public SeizureRect(double minX,double minY,double maxX,double maxY,int color) {
			this.color = color;
			this.minX = minX;
			this.minX = minY;
			this.minX = maxX;
			this.minX = maxY;
		}

		public void render(Tessellator tessellator,IIcon[] wool) {
			
		}
		
	}
	
}
