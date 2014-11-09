package robo51.newt.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import robo51.newt.Newt;
import robo51.newt.lib.Constants;
import robo51.newt.tileentities.TileEntitySolarCellBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SolarCellBlock extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	@SideOnly(Side.CLIENT)
	private IIcon iconBottom;
	
	private String name = "solarcellBlock";
	
	public SolarCellBlock() {
		
		super(Material.iron);
		setBlockName(Constants.MODID + "_" + name);
		setCreativeTab(Newt.tabNewt);
		GameRegistry.registerBlock(this, name);
		setStepSound(soundTypeMetal);
		setHardness(4.0F);
		setResistance(5.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySolarCellBlock();
	}
	
    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }
    
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IconRegistry) {
		this.blockIcon = IconRegistry.registerIcon(Constants.MODID + ":" + "solarcellBlockside");
		this.iconTop = IconRegistry.registerIcon(Constants.MODID + ":" + "solarcellBlocktop");
		this.iconBottom = IconRegistry.registerIcon(Constants.MODID + ":" + "chassisBlock");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if(side == 0) {
			return iconBottom;
		} else if(side == 1) {
			return iconTop;
		} else {
			return blockIcon;
		}
	}
}