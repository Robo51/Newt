package robo51.newt.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import robo51.newt.Newt;
import robo51.newt.entity.ExtendedPlayerWater;
import cpw.mods.fml.common.registry.GameRegistry;

public class MageniteIngotItem extends Item {
	
	private String name = "mageniteingotItem";
	
	public static int currentWaterSealing;
	public static int maxWaterSealing;
	public static int workingWaterSealing;
	
	public MageniteIngotItem() {
		setUnlocalizedName(robo51.newt.lib.Constants.MODID + "_" + name); 
		setCreativeTab(Newt.tabNewt);
		GameRegistry.registerItem(this, name);
		setTextureName(robo51.newt.lib.Constants.MODID + ":" + name);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			currentWaterSealing = ExtendedPlayerWater.getCurrentWaterSealing();
			maxWaterSealing = ExtendedPlayerWater.getMaxWaterSealing();
			workingWaterSealing = currentWaterSealing + 600;
				if(workingWaterSealing <= maxWaterSealing) {
					--itemStack.stackSize;
					ExtendedPlayerWater.newCurrentWaterSealing(workingWaterSealing);
				} else {
					if(!world.isRemote) {
						player.addChatMessage(new ChatComponentText("You are already sealed up to the maximum."));
					}
				}
			return itemStack;
		} else {
			return itemStack;
		}
	}
}