package net.wuerfel21.derpyshiz;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class WoolCrafting {
	
	public static ItemStack getPatternWool(int meta) {
		return new ItemStack(GameRegistry.findBlock("derpyshiz", "pattern_wool"), 9, meta);
	}
	
	public static ItemStack getWool(int meta) {
		return new ItemStack(GameRegistry.findBlock("minecraft", "wool"), 1, meta);
	}
	
	public static void register() {
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(0), "WWW","WBW","WWW",'W',"blockWool",'B',getWool(3))); //Colordots
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(1), "rbr","bbb","rbr",'W',"blockWool",'b',getWool(3),'r',getWool(14))); //Lines
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(2), "wbw","bwb","wbw",'W',"blockWool",'b',getWool(15),'w',getWool(0))); //Checker
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(3), "www","wpw","www",'W',"blockWool",'p',getWool(6),'w',getWool(0))); //Dotted
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(4), "ppp","mmm","ppp",'W',"blockWool",'p',getWool(6),'m',getWool(2))); //Striped
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(5), "lll","ldl","lll",'W',"blockWool",'l',getWool(8),'d',getWool(7))); //Squares
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(6), "yby","rgb","yry",'W',"blockWool",'b',getWool(3),'r',getWool(14),'y',getWool(4),'g',getWool(5))); //Bright
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(7), "ppp","prp","ppp",'W',"blockWool",'p',getWool(6),'r',getWool(14))); //Heart
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(8), "roy","bWl","scg",'W',"blockWool",'r',getWool(14),'o',getWool(1),'y',getWool(4),'l',getWool(5),'g',getWool(13),'c',getWool(9),'s',getWool(3),'b',getWool(11))); //Rainbow
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(9), "rwr","www","rwr",'W',"blockWool",'r',getWool(14),'w',getWool(0))); //Swiss
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(10), "bbb","rrr","yyy",'W',"blockWool",'b',getWool(15),'r',getWool(14),'y',getWool(4))); //Germany
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(11), "bwb","wbw","bwb",'W',"blockWool",'b',getWool(3),'w',getWool(0))); //Bavaria
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(12), "wwo","woo","www",'W',"blockWool",'o',getWool(1),'w',getWool(0))); //Mojang
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(13), "bpb","pwp","pbp",'W',"blockWool",'p',getWool(6),'b',getWool(15),'w',getWool(0))); //Notch
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(14), "ggg","yyy","gwg",'W',"blockWool",'y',getWool(4),'w',getWool(0),'g',getWool(8))); //Jeb
		GameRegistry.addRecipe(new ShapedOreRecipe(getPatternWool(15), "ppp","vmv","ppp",'W',"blockWool",'p',getWool(6),'m',getWool(2),'v',getWool(10))); //Pig
		
		GameRegistry.addRecipe(new ShapedOreRecipe(Items.bed, "WWW","www",'W',"blockWool",'w',"plankWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(Items.painting, "sss","sWs","sss",'W',"blockWool",'s',"stickWood"));
	}
	
}
