package robo51.newt.blocks;

import robo51.newt.Newt;
import robo51.newt.lib.Constants;
import robo51.newt.tileentities.TileEntityNeuralCellBlock;
import robo51.newt.tileentities.TileEntitySolarCellBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class NeuralCellBlock extends BlockContainer {
	
	private String name = "neuralcellBlock";
	
	public NeuralCellBlock() {
		
		super(Material.iron);
		setBlockName(Constants.MODID + "_" + name); //MODID BLOCKNAME
		setBlockBounds(0F, 0F, 0F, 1F, 0.9375F, 1F);
		setCreativeTab(Newt.tabNewt);
		GameRegistry.registerBlock(this, name);
		setBlockTextureName(Constants.MODID + ":" + name);
		setStepSound(soundTypeMetal);
		setHardness(5.0F);
		setResistance(10.0F);
	}
	
	public int getRenderType() {
		return -1;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityNeuralCellBlock();
	}
	
    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

}
