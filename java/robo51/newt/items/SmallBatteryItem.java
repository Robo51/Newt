package robo51.newt.items;

import robo51.newt.Newt;
import robo51.newt.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class SmallBatteryItem extends Item {
	
	private String name = "smallbatteryItem";
	
	public SmallBatteryItem() {
		setUnlocalizedName(Constants.MODID + "_" + name); 
		setCreativeTab(Newt.tabNewt);
		GameRegistry.registerItem(this, name);
		setTextureName(Constants.MODID + ":" + name);
	}

}
