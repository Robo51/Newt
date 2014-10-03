package newt.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import newt.blocks.ModBlocks;
import newt.items.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;

public final class CraftingItems {
	
	public static void init(){

		GameRegistry.addRecipe(new ItemStack(Blocks.mossy_cobblestone), new Object[] {
			"AAA",
			"ABA",
			"AAA",
			'A', Blocks.sapling, 'B', Blocks.cobblestone
		});
	
		GameRegistry.addRecipe(new ItemStack(Blocks.stonebrick, 1, 1), new Object[] {
			"AAA",
			"ABA",
			"AAA",
			'A', Blocks.sapling, 'B', Blocks.stonebrick
		});	    	
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redcoalBlock), new Object[] {
			"AAA",
			"AAA",
			"AAA",
			'A', ModItems.redcoalingotItem
		});	  
		
		GameRegistry.addRecipe(new ItemStack(ModItems.redcoalbreadItem, 4), new Object[] {
			"AAA",
			"ABA",
			"AAA",
			'A', ModItems.redcoalItem, 'B', Items.bread
		});	
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.redcoalingotItem, 9), new Object[] {
			new ItemStack(ModBlocks.redcoalBlock)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.redcoalItem), new Object[] {
			new ItemStack(Items.coal), Items.redstone
		});
	
		GameRegistry.addSmelting(new ItemStack(ModItems.redcoalItem), new ItemStack(ModItems.redcoalingotItem), 0.4F);
		
	}
	
}
