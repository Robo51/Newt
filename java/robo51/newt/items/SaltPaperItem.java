package robo51.newt.items;

import net.minecraft.item.Item;
import robo51.newt.Newt;
import robo51.newt.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class SaltPaperItem extends Item {
	
	private String name = "saltpaperItem";
	
	public SaltPaperItem() {
		setUnlocalizedName(Constants.MODID + "_" + name); 
		setCreativeTab(Newt.tabNewt);
		GameRegistry.registerItem(this, name);
		setTextureName(Constants.MODID + ":" + name);
	}
}