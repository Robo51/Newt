package robo51.newt.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import robo51.newt.Newt;
import robo51.newt.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class RedCoalBreadItem extends ItemFood {

	private String name = "redcoalbreadItem";
	
	public RedCoalBreadItem(int heal, float saturation, boolean wolfMeat) {
		
		super(heal, saturation, wolfMeat);
		setUnlocalizedName(Constants.MODID + "_" + name); 
		setCreativeTab(Newt.tabNewt);
		GameRegistry.registerItem(this, name);
		setTextureName(Constants.MODID + ":" + name);
		setAlwaysEdible();
	}
	
	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(12, 2400, 0));
		player.addPotionEffect(new PotionEffect(1, 2400, 0));
	}
	
}
