package newt;

import net.minecraft.item.ItemStack;
import newt.items.ModItems;
import cpw.mods.fml.common.IFuelHandler;

public class NewtFuel implements IFuelHandler {

	@Override
    public int getBurnTime(ItemStack fuel)
    {
            if(fuel.getItem() == ModItems.redcoalItem)
                    return 2400;
            else
                    return 0;
	}

}
