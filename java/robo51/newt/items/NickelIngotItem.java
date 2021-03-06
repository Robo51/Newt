package robo51.newt.items;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import robo51.newt.Newt;
import robo51.newt.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class NickelIngotItem extends Item {
	
	private String name = "nickelingotItem";
	
	public NickelIngotItem() {
		setUnlocalizedName(Constants.MODID + "_" + name); 
		setCreativeTab(Newt.tabNewt);
		GameRegistry.registerItem(this, name);
		setTextureName(Constants.MODID + ":" + name);
		OreDictionary.registerOre("ingotNickel", this);
	}

}
