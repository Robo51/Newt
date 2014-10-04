package Newt.items;

import Newt.lib.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class RedCoalIngotItem extends Item {
	
	private String name = "redcoalingotItem";
	
	public RedCoalIngotItem() {
		setUnlocalizedName(Constants.MODID + "_" + name); 
		setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(this, name);
		setTextureName(Constants.MODID + ":" + name);
	}

}
