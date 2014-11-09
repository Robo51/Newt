package robo51.newt.handlers;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import robo51.newt.blocks.ModBlocks;
import robo51.newt.items.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;

public final class CraftingHandler {
	
	public static void init(){
//Shaped Recipes
		//Vanilla+
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
		
		
		//Storage Blocks
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redcoalBlock), new Object[] {
			"AAA",
			"AAA",
			"AAA",
			'A', ModItems.redcoalingotItem
		});
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mageniteBlock), new Object[] {
			"AAA",
			"AAA",
			"AAA",
			'A', ModItems.mageniteingotItem
		});
		
		
		//Food
		GameRegistry.addRecipe(new ItemStack(ModItems.redcoalbreadItem, 2), new Object[] {
			"AAA",
			"ABA",
			"AAA",
			'A', ModItems.redcoalItem, 'B', Items.bread
		});
		
		//Machines
		GameRegistry.addRecipe(new ItemStack(ModBlocks.chassisBlock), new Object[] {
			"BAB",
			"A A",
			"BAB",
			'A', ModItems.redcoalingotItem, 'B', ModItems.alumicingotItem
		});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.chassisBlock), new Object[] {
			"ABA",
			"B B",
			"ABA",
			'A', ModItems.redcoalingotItem, 'B', ModItems.alumicingotItem
		});
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.presserBlock_Idle), new Object[] {
			" M ",
			"IAI",
			" B ",
			'A', ModBlocks.chassisBlock, 'B', ModItems.smallbatteryItem, 'M', ModItems.motorcoilItem, 'I', Items.iron_ingot
		});
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.solarcellBlock), new Object[] {
			"RRR",
			"CA ",
			" B ",
			'A', ModBlocks.chassisBlock, 'B', ModItems.smallbatteryItem, 'R', ModItems.redcoalItem, 'C', ModItems.coppercoilItem
		});
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.powercellBlock), new Object[] {
			" B ",
			"BAB",
			" B ",
			'A', ModBlocks.chassisBlock, 'B', ModItems.smallbatteryItem
		});
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.furnaceBlock_Idle), new Object[] {
			" M ",
			"FAF",
			" B ",
			'A', ModBlocks.chassisBlock, 'B', ModItems.smallbatteryItem, 'F', Blocks.furnace, 'M', ModItems.motorcoilItem
		});
		
		//Ingot
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.alumicingotItem, 4), new Object[] {
			"AA",
			"AB",
			'A', "ingotAluminium", 'B', "ingotZinc"
		}));
		
		//Electronics
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.smallbatteryItem), new Object[] {
			" A",
			"CB",
			" D",
			'A', "ingotZinc", 'B', ModItems.saltpaperItem, 'C', Items.string, 'D', "ingotNickel"
		}));
		
		GameRegistry.addRecipe(new ItemStack(ModItems.tempbatteryItem, 2), new Object[] {
			"AAA",
			" B ",
			" C ",
			'A', Blocks.sapling, 'B', Items.coal, 'C', Blocks.log
		});
		
//Shapeless Recipes
		//Blocks
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.redcoalingotItem, 9), new Object[] {
			new ItemStack(ModBlocks.redcoalBlock)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.mageniteingotItem, 9), new Object[] {
			new ItemStack(ModBlocks.mageniteBlock)
		});
		
		//Materials
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.redcoalItem), new Object[] {
			new ItemStack(Items.coal), Items.redstone
		});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.redcoalItem), new Object[] {
			new ItemStack(Items.coal, 1, 1), Items.redstone
		});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.paperstackItem), new Object[] {
			new ItemStack(Items.paper), Items.paper
		});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.motorcoilItem), new Object[] {
			new ItemStack(ModItems.coppercoilItem), Items.iron_ingot
		});
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.copperstackItem, new Object[] {
			"ingotCopper", "ingotCopper"
		}));
		
//Furnace Recipes
		//Ingot
		GameRegistry.addSmelting(new ItemStack(ModItems.redcoalItem), new ItemStack(ModItems.redcoalingotItem), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.unrefinedmageniteItem), new ItemStack(ModItems.mageniteingotItem), 0.4F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.aluminiumoreBlock), new ItemStack(ModItems.aluminiumingotItem), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.zincoreBlock), new ItemStack(ModItems.zincingotItem), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.nickeloreBlock), new ItemStack(ModItems.nickelingotItem), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.copperoreBlock), new ItemStack(ModItems.copperingotItem), 0.1F);
		

	}
}
