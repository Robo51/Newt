package robo51.newt.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import robo51.newt.Newt;
import robo51.newt.lib.Constants;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.registry.GameRegistry;

public class CopperStackItem extends Item {
	
	private String name = "copperstackItem";
    private Block isCoil;
	
	public CopperStackItem(Block CopperStackItem) {
		setUnlocalizedName(Constants.MODID + "_" + name); 
		setCreativeTab(Newt.tabNewt);
		GameRegistry.registerItem(this, name);
		setTextureName(Constants.MODID + ":" + name);
        this.maxStackSize = 64;
        this.isCoil = CopperStackItem;
	}
	
public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		
		boolean flag = this.isCoil == Blocks.air;
		MovingObjectPosition movingObjectPosition = this.getMovingObjectPositionFromPlayer(world, player, flag);
		
		if (movingObjectPosition == null) {
			return itemStack;
		} else {
			FillBucketEvent event = new FillBucketEvent(player, itemStack, world, movingObjectPosition);
			if (MinecraftForge.EVENT_BUS.post(event)) {
				return itemStack;
			}
			
			if (event.getResult() == Event.Result.ALLOW) {
				if (--itemStack.stackSize <= 0) {
					return event.result;
				}
			
				if (!player.inventory.addItemStackToInventory(event.result)) {
					player.dropPlayerItemWithRandomChoice(event.result,  false);
				}
				return itemStack;
			}
		
			if (movingObjectPosition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				int x = movingObjectPosition.blockX;
				int y = movingObjectPosition.blockY;
				int z = movingObjectPosition.blockZ;
			
				if (!world.canMineBlock(player, x, y, z)) {
					return itemStack;
				}
				
				if (!player.canPlayerEdit(x, y, z, movingObjectPosition.sideHit, itemStack)) {
					return itemStack;
				}
				
				Block material = world.getBlock(x, y, z);
				
				if (material == Blocks.stone) {
					return this.func_150910_a(itemStack, player, ModItems.coppercoilItem);
				}
			} else {
				if (this.isCoil == Blocks.air) {
					return new ItemStack(ModItems.copperstackItem);
				}
			}
			return itemStack;			
		}
	}
	
    private ItemStack func_150910_a(ItemStack itemStack, EntityPlayer player, Item item) {
        if (--itemStack.stackSize <= 0) {
            return new ItemStack(item);
         } else {
            if (!player.inventory.addItemStackToInventory(new ItemStack(item))) {
               player.dropPlayerItemWithRandomChoice(new ItemStack(item, 1, 0), false);
               }
            return itemStack;
             }
         }

}
