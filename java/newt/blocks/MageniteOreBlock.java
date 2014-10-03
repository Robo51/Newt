package newt.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import newt.items.ModItems;
import newt.lib.Constants;

public class MageniteOreBlock extends Block {

	private String name = "mageniteoreBlock";
	
	public MageniteOreBlock() {
		
		super(Material.rock);
		setBlockName(Constants.MODID + "_" + name); //MODID BLOCKNAME
		setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this, name);
		setBlockTextureName(Constants.MODID + ":" + name);
		setStepSound(soundTypeStone);
		setHardness(3.0F);
		setResistance(5.0F);
		setHarvestLevel("pickaxe", 2);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune){
		return ModItems.magenitedustItem;
		}
	
	@Override
	public int quantityDropped(Random rand){
		return 3 + rand.nextInt(2);
		}
}