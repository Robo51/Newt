package robo51.newt.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import robo51.newt.crafting.PresserBlockRecipes;
import robo51.newt.slot.SlotPresserBlock;
import robo51.newt.tileentities.TileEntityPresserBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerPresserBlock extends Container {
	
	private TileEntityPresserBlock presserBlock;
	
	private int dualCookTime;
	private int dualPower;
	private int lastItemBurnTime;

	public ContainerPresserBlock(InventoryPlayer inventoryPlayer, TileEntityPresserBlock tileentity) {
		dualCookTime = 0;
		dualPower = 0;
		lastItemBurnTime = 0;
		
		this.presserBlock = tileentity;
		
		this.addSlotToContainer(new Slot(tileentity, 0, 56, 24));
		this.addSlotToContainer(new Slot(tileentity, 1, 56, 45));
		this.addSlotToContainer(new Slot(tileentity, 2, 8, 62));
		this.addSlotToContainer(new SlotPresserBlock(inventoryPlayer.player, tileentity, 3, 116, 35));
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}
	
	public void addCraftingToCrafters (ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.presserBlock.dualCookTime);
		crafting.sendProgressBarUpdate(this, 1, this.presserBlock.dualPower);
	}
	
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (int i =0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting)this.crafters.get(i);
			
			if(this.dualCookTime != this.presserBlock.dualCookTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.presserBlock.dualCookTime);
			}
			
			if(this.dualPower != this.presserBlock.dualPower) {
				icrafting.sendProgressBarUpdate(this, 1, this.presserBlock.dualPower);
			}
		}
		
		this.dualCookTime = this.presserBlock.dualCookTime;
		this.dualPower = this.presserBlock.dualPower;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int i, int j) {
		if(i == 0) {
			presserBlock.dualCookTime = j;
		}
		
		if(i == 1) {
			presserBlock.dualPower = j;
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
                if (PresserBlockRecipes.getOutput(null, null) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntityPresserBlock.hasItemPower(itemstack1)) {
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
		return presserBlock.isUseableByPlayer(player);
	}
}
