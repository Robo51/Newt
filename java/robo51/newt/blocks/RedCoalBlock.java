package robo51.newt.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import robo51.newt.Newt;
import robo51.newt.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class RedCoalBlock extends Block {
	
	private String name = "redcoalBlock";
	
	public RedCoalBlock() {
		
		super(Material.iron);
		setBlockName(Constants.MODID + "_" + name); //MODID BLOCKNAME
		setCreativeTab(Newt.tabNewt);
		GameRegistry.registerBlock(this, name);
		setBlockTextureName(Constants.MODID + ":" + name);
		setStepSound(soundTypeMetal);
		setHardness(5.0F);
		setResistance(10.0F);
	}
}