package robo51.newt.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import robo51.newt.tileentities.TileEntityFurnaceBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerFurnaceBlock extends Container {
	
	private TileEntityFurnaceBlock furnaceBlock; 
	
	public int cookTime;
	public int burnPower;
	public int lastItemBurnTime;
	
	public ContainerFurnaceBlock(InventoryPlayer inventory, TileEntityFurnaceBlock tileentity) {
		cookTime = 0;
		burnPower = 0;
		lastItemBurnTime = 0;
		
		this.furnaceBlock = tileentity;
		
		this.addSlotToContainer(new Slot(tileentity, 0, 56, 35));
		this.addSlotToContainer(new Slot(tileentity, 1, 8, 62));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity, 2, 116, 35));
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}
	
	public void addCraftingToCrafters (ICrafting icrafting) {
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.furnaceBlock.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.furnaceBlock.burnPower);
	}
	
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for(int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
			
			if(this.cookTime != this.furnaceBlock.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.furnaceBlock.cookTime);
			}
			
			if(this.burnPower != this.furnaceBlock.burnPower) {
				icrafting.sendProgressBarUpdate(this, 1, this.furnaceBlock.burnPower);
			}
		}
		
		this.cookTime = this.furnaceBlock.cookTime;
		this.burnPower = this.furnaceBlock.burnPower;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int i, int j) {
		 if (i == 0) {
		 this.furnaceBlock.cookTime = j;
		 }
		 
		 if (i == 1) {
		 this.furnaceBlock.burnPower = j;
		 }
	 }
	 
	 public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
	        ItemStack itemstack = null;
	        Slot slot = (Slot)this.inventorySlots.get(par2);

	        if (slot != null && slot.getHasStack()) {
	            ItemStack itemstack1 = slot.getStack();
	            itemstack = itemstack1.copy();

	            if (par2 == 2) {
	                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
	                    return null;
	                }
	                slot.onSlotChange(itemstack1, itemstack);
	            } else if (par2 != 1 && par2 != 0) {
	                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
	                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
	                        return null;
	                    }
	                } else if (TileEntityFurnaceBlock.hasItemPower(itemstack1)) {
	                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
	                        return null;
	                    }
	                } else if (par2 >= 3 && par2 < 30) {
	                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
	                        return null;
	                    }
	                } else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
	                    return null;
	                }
	            }
	            else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
	                return null;
	            }

	            if (itemstack1.stackSize == 0) {
	                slot.putStack((ItemStack)null);
	            }
	            else {
	                slot.onSlotChanged();
	            }

	            if (itemstack1.stackSize == itemstack.stackSize) {
	                return null;
	            }
	            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
	        }
	        return itemstack;
	    }

	@Override	 
	public boolean canInteractWith(EntityPlayer player) {
		return furnaceBlock.isUseableByPlayer(player);
	}

}