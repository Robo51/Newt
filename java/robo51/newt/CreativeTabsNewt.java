package robo51.newt;

import robo51.newt.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabsNewt extends CreativeTabs {

	public CreativeTabsNewt(String lable) {
		super(lable);
	}

	@Override
	public Item getTabIconItem() {
		return ModItems.redcoalItem;
	}

}
