package robo51.newt.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;
import robo51.newt.Newt;
import robo51.newt.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class CopperOreBlock extends Block {
	
	private String name = "copperoreBlock";
	
	public CopperOreBlock() {
		
		super(Material.rock);
		setBlockName(Constants.MODID + "_" + name); //MODID BLOCKNAME
		setCreativeTab(Newt.tabNewt);
		GameRegistry.registerBlock(this, name);
		setBlockTextureName(Constants.MODID + ":" + name);
		setStepSound(soundTypeStone);
		setHardness(3.0F);
		setResistance(5.0F);
		setHarvestLevel("pickaxe", 1);
		OreDictionary.registerOre("oreCopper", this);
	}

}
