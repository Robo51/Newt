package robo51.newt.entity;

import ibxm.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayerWater implements IExtendedEntityProperties {
	
	public final static String EXT_PROP_NAME = "ExtendedPlayer";
	private final EntityPlayer player;
	private static int currentWaterSealing;
	private static int maxWaterSealing;
	public boolean isUsing;
	
	public ExtendedPlayerWater(EntityPlayer player) {
		this.player = player;
		this.currentWaterSealing = 600;
		this.maxWaterSealing = 1200;
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(ExtendedPlayerWater.EXT_PROP_NAME, new ExtendedPlayerWater(player));
	}
	
	public static final ExtendedPlayerWater get(EntityPlayer player) {
		return (ExtendedPlayerWater) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger("CurrentWaterSealing", this.currentWaterSealing);
		properties.setInteger("MaxWaterSealing", this.maxWaterSealing);
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		this.currentWaterSealing = properties.getInteger("CurrentWaterSealing");
		this.maxWaterSealing = properties.getInteger("MaxWaterSealing");
		System.out.println("WaterSealing from NBT: " + this.currentWaterSealing + "/" + this.maxWaterSealing);
	}
	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub
	}
	
	public static int getCurrentWaterSealing() {
		return currentWaterSealing;
	}
	
	public static int getMaxWaterSealing() {
		return maxWaterSealing;
	}
	
	public static int newCurrentWaterSealing(int i) {
		return currentWaterSealing = i;
	}
	
	public static int newMaxWaterSealing(int i) {
		return maxWaterSealing = i;
	}
	
	public void setCurrentWaterSealing(int amount) {
		this.currentWaterSealing = (amount < this.maxWaterSealing ? amount : this.maxWaterSealing);
	}

	public void setMaxWaterSealing(int amount) {
		this.maxWaterSealing = (amount > 0 ? amount : 0);
	}

}
