package net.wuerfel21.derpyshiz.client;

import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;

public class BlankIcon {
	
	public static IIcon icon;
	private static BufferedImage[] buf = {new BufferedImage(1,1,BufferedImage.TYPE_4BYTE_ABGR)};
	
	public static void register() {
		buf[0].setRGB(0, 0, 0);
		TextureAtlasSprite sprite = hackSprite("blank");
		if (sprite==null) {System.out.println("SUPER UEBER MEGA ULTRA FATAL ERROR!!!!!!!! BLANK ICON HACKERY FAILED!!!!"); icon = Blocks.air.getIcon(0, 0);return;}
		List list = new LinkedList();
		list.add(new AnimationFrame(0));
		sprite.loadSprite(buf, new AnimationMetadataSection(list,1,1,-1), false);
		icon = sprite;
	}
	
	private static TextureAtlasSprite hackSprite(String s) {
		try {
			Constructor[] cons = TextureAtlasSprite.class.getDeclaredConstructors();
			cons[0].setAccessible(true);
			return (TextureAtlasSprite) cons[0].newInstance(s);
		} catch (Exception e) {
			return null;
		}
	}
	
}
