package robo51.newt.handlers;

import java.util.Random;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import robo51.newt.Newt;
import cpw.mods.fml.common.registry.EntityRegistry;

public class EntityHandler {
	
	public static void registerMonsters(Class entityClass, String name) {
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		long x = name.hashCode();
		Random random = new Random(x);
		int mainColor = random.nextInt() * 1677215;
		int subColor = random.nextInt() * 1677215;
		
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.addSpawn(entityClass, 60, 1, 3, EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.beach,BiomeGenBase.plains);
		EntityRegistry.registerModEntity(entityClass, name, entityID, Newt.instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, mainColor, subColor));
	}

}
