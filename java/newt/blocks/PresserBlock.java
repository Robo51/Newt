package newt.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import newt.lib.Constants;
import newt.tileentities.TileEntityPresserBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PresserBlock extends BlockContainer {
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFace;
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	private String name = "presserBlock";
	
	public PresserBlock() {
		
		super(Material.iron);
		setBlockName(Constants.MODID + "_" + name);
		setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this, name);
		setStepSound(soundTypeMetal);
		setHardness(4.0F);
		setResistance(5.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityPresserBlock();
	}
	
    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IconRegistry) {
		this.blockIcon = IconRegistry.registerIcon(Constants.MODID + ":" + "presserBlockside");
		this.iconFace = IconRegistry.registerIcon(Constants.MODID + ":" + "presserBlockface");
		this.iconTop = IconRegistry.registerIcon(Constants.MODID + ":" + "presserBlocktop");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? this.iconTop : (side == 0 ? this.blockIcon : (side != meta ? this.blockIcon : this.iconFace));
	}
	
	public Item getItemDropped(int par1, Random random, int par3) {
		return Item.getItemFromBlock(ModBlocks.presserBlock);
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}
	
	private void setDefaultDirection(World world, int x, int y, int z) {
		if(!world.isRemote) {
			Block block = world.getBlock(x, y, z -1);
			Block block1 = world.getBlock(x, y, z +1);
			Block block2 = world.getBlock(x -1, y, z);
			Block block3 = world.getBlock(x +1, y, z);
			byte b0 = 3;
			
			if(block.func_149730_j() && !block1.func_149730_j()) {
				b0 = 3;
			}
			
			if(block1.func_149730_j() && !block.func_149730_j()) {
				b0 = 2;
			}
			
			if(block2.func_149730_j() && !block3.func_149730_j()) {
				b0 = 5;
			}
			
			if(block3.func_149730_j() && !block2.func_149730_j()) {
				b0 = 4;
			}
			
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0 / 360.0F) + 0.5D) & 3;
		
		if(l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
		
		if(l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
		
		if(l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		
		if(l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
		

	}
}
