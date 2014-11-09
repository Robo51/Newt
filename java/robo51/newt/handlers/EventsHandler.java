package robo51.newt.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import org.lwjgl.input.Keyboard;

import robo51.newt.entity.ExtendedPlayerWater;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class EventsHandler {
	
	@SubscribeEvent
	public void playerTickEvent(PlayerTickEvent event) {
		EntityLivingBase entity;
		
		if (event.player instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.player;
			if(event.player.isWet()) {
				int watersealing = ExtendedPlayerWater.getCurrentWaterSealing();
				if(watersealing <= 0) {
					event.player.attackEntityFrom(DamageSource.drown, 1.0F);
				} else {
					--watersealing;
					ExtendedPlayerWater.newCurrentWaterSealing(watersealing);
					if(watersealing == 200) {
						if(!event.player.worldObj.isRemote) {
							player.addChatMessage(new ChatComponentText("5 Seconds left of WaterSealing."));
						}
					}
					if(watersealing == 50) {
						if(!event.player.worldObj.isRemote) {
							player.addChatMessage(new ChatComponentText("1 Second left of WaterSealing!"));
						}
					}
				}
			}
			/*
			if(event.entity.isInWater()) {
				event.entity.setAir(10);
				if(Keyboard.isKeyDown(57)) {
					event.entity.motionY = 0.005D;
					if(event.entity.isCollidedHorizontally) {
						event.entity.motionY = 0.2D;
					}
				} else {
					event.entity.motionY = -1.0D;
				}
			} */
		}
	}
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && ExtendedPlayerWater.get((EntityPlayer) event.entity) == null) {
			ExtendedPlayerWater.register((EntityPlayer) event.entity);
		}
	}
}