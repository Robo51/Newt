package robo51.newt.slot;

import robo51.newt.tileentities.TileEntityPresserBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotPresserBlock extends Slot {

	public SlotPresserBlock(EntityPlayer player, IInventory iInventory, int i, int j, int k) {
		super(iInventory, i, j, k);
	}
	
	public boolean isItemValid(ItemStack itemstack) {
        return false;
    }
}