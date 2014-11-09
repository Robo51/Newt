package robo51.newt.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import robo51.newt.Newt;
import robo51.newt.lib.Constants;
import robo51.newt.tileentities.TileEntityFurnaceBlock;
import robo51.newt.tileentities.TileEntityPresserBlock;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PresserBlock extends BlockContainer {
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFace;
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	private static boolean keepInventory = false;
	private Random rand;
	private final boolean isActive;
	
	public PresserBlock(boolean blockState) {
		
		super(Material.iron);
		setStepSound(soundTypeMetal);
		setHardness(4.0F);
		setResistance(5.0F);
		rand = new Random();
		isActive = blockState;
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
		this.blockIcon = IconRegistry.registerIcon(Constants.MODID + ":" + "chassisBlock");
		this.iconFace = IconRegistry.registerIcon(Constants.MODID + ":" + (this.isActive ? "presserBlock_face_active" : "presserBlock_face_idle"));
		this.iconTop = IconRegistry.registerIcon(Constants.MODID + ":" + "chassisBlock_top");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta == 0 && side == 3) {
			return this.iconFace;
		} else if (side == 1) {
			return this.iconTop;
		} else if (side == 0) {
			return this.iconTop;
		} else if (side != meta) {
			return this.blockIcon;
		} return this.iconFace;
	}
	
	@SideOnly(Side.CLIENT)
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}
	
	private void setDefaultDirection(World world, int x, int y, int z) {
		if(!world.isRemote) {
			Block dir = world.getBlock(x, y, z -1);
			Block dir1 = world.getBlock(x, y, z +1);
			Block dir2 = world.getBlock(x -1, y, z);
			Block dir3 = world.getBlock(x +1, y, z);
			byte byte0 = 3;
			
			if(dir.func_149730_j() && !dir1.func_149730_j()) {
				byte0 = 3;
			}
			
			if(dir1.func_149730_j() && !dir.func_149730_j()) {
				byte0 = 2;
			}
			
			if(dir2.func_149730_j() && !dir3.func_149730_j()) {
				byte0 = 5;
			}
			
			if(dir3.func_149730_j() && !dir2.func_149730_j()) {
				byte0 = 4;
			}
			
			world.setBlockMetadataWithNotify(x, y, z, byte0, 2);
		}
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {
		int dir = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		if(dir == 0) world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		
		if(dir == 1) world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		
		if(dir == 2) world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		
		if(dir == 3) world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		
		if(itemstack.hasDisplayName()) {
			((TileEntityFurnaceBlock)world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
		}
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    	if (world.isRemote) {
    		return true;
    	}else if (!player.isSneaking()) {
    		TileEntityPresserBlock entity = (TileEntityPresserBlock) world.getTileEntity(x, y, z);
    		if (entity != null) {
    			FMLNetworkHandler.openGui(player, Newt.instance, ModBlocks.guiIdPresserBlock, world, x, y, z);
    		}
    		return true;
    	}else {
    		return false;
    	}
    }
	
	public static void updatePresserBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord) {
		int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		TileEntity tilentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
		
		keepInventory = true;
		
		if(active) {
			worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.presserBlock_Active);
		} else {
			worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.presserBlock_Idle);
		}
		
		keepInventory = false;
		
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
		
		if(tilentity != null) {
			tilentity.validate();
			worldObj.setTileEntity(xCoord, yCoord, zCoord, tilentity);
		}
	}
	
	public void breakBlock(World world, int x, int y, int z, Block oldblock, int oldMetadata) {
		if(!keepInventory) {
			TileEntityPresserBlock tileentity = (TileEntityPresserBlock) world.getTileEntity(x, y, z);
			
			if(tileentity != null) {
				for(int i = 0; i < tileentity.getSizeInventory(); i++) {
					ItemStack itemstack = tileentity.getStackInSlot(i);
					
					if(itemstack != null) {
						float f = this.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
						
						while(itemstack.stackSize > 0) {
							int j = this.rand.nextInt(21) + 10;
							
							if(j > itemstack.stackSize) {
								j = itemstack.stackSize;
							}
							
							itemstack.stackSize -= j;
							
							EntityItem item = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));
							
							if(itemstack.hasTagCompound()) {
								item.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
							}
							
							world.spawnEntityInWorld(item);
						}
					}
				}
				
				world.func_147453_f(x, y, z, oldblock);
			}
		}
		
		super.breakBlock(world, x, y, z, oldblock, oldMetadata);
	}
	
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(ModBlocks.presserBlock_Idle);
	}
}