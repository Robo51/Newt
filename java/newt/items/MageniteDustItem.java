package newt.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import newt.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class MageniteDustItem extends Item {

	private String name = "magenitedustItem";
	
	public MageniteDustItem() {
		setUnlocalizedName(Constants.MODID + "_" + name); 
		setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(this, name);
		setTextureName(Constants.MODID + ":" + name);
	}
	
}
