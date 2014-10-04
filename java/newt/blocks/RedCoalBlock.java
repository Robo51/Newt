package Newt.blocks;

import Newt.lib.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class RedCoalBlock extends Block {
	
	private String name = "redcoalBlock";
	
	public RedCoalBlock() {
		
		super(Material.iron);
		setBlockName(Constants.MODID + "_" + name); //MODID BLOCKNAME
		setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this, name);
		setBlockTextureName(Constants.MODID + ":" + name);
		setStepSound(soundTypeMetal);
		setHardness(5.0F);
		setResistance(10.0F);
	}

}
