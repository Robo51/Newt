package robo51.newt.crafting;

import robo51.newt.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PresserBlockRecipes {
	
	public PresserBlockRecipes() {
		
	}
	public static ItemStack getPressingResult(Item item, Item item2) {
		return getOutput(item, item2);
	}
	
	public static ItemStack getOutput(Item item, Item item2) {
		//Unrefined Magenite
		if (item == ModItems.magenitedustItem && item2 == ModItems.redcoalItem || item == ModItems.redcoalItem && item2 == ModItems.magenitedustItem) {
			return new ItemStack(ModItems.unrefinedmageniteItem);
		}
		
		return null;
	}
}
