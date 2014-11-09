package robo51.newt.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import robo51.newt.Newt;
import robo51.newt.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class TempBatteryItem extends ItemFood {
	
	private String name = "tempbatteryItem";
	
	public TempBatteryItem(int heal, float saturation, boolean wolfMeat) {
		
		super(heal, saturation, wolfMeat);
		setUnlocalizedName(Constants.MODID + "_" + name); 
		setCreativeTab(Newt.tabNewt);
		GameRegistry.registerItem(this, name);
		setTextureName(Constants.MODID + ":" + name);
		setAlwaysEdible();
	}
	
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 1;
    }
    
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.none;
    }
    
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player)
    {
        --itemstack.stackSize;
        player.getFoodStats().func_151686_a(this, itemstack);
        world.playSoundAtEntity(player, Constants.MODID + ":" + "munch", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(itemstack, world, player);
        return itemstack;
    }
}
