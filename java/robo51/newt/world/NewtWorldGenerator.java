package robo51.newt.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import robo51.newt.blocks.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;

public class NewtWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
        case 0: GenerateOverworld(random, chunkX * 16, chunkZ * 16, world); break;
        case 1: GenerateEnd(random, chunkX * 16, chunkZ * 16, world); break;
        case -1: GenerateNether(random, chunkX * 16, chunkZ * 16, world); break;
        default: GenerateOverworld(random, chunkX * 16, chunkZ * 16, world);
        }
	}
	
    private void GenerateOverworld(Random random, int x, int z, World world) {
    	addOres(ModBlocks.mageniteoreBlock, world, random, x, z, 2, 6, 10, 0, 30);
    	addOres(ModBlocks.aluminiumoreBlock, world, random, x, z, 3, 6, 50, 0, 70);
    	addOres(ModBlocks.zincoreBlock, world, random, x, z, 2, 5, 20, 0, 70);
    	addOres(ModBlocks.nickeloreBlock, world, random, x, z, 2, 6, 40, 20, 50);
    	addOres(ModBlocks.copperoreBlock, world, random, x, z, 4, 8, 35, 30, 80);
    }
 
    private void GenerateNether(Random random, int x, int z, World world) {
    }
 
    private void GenerateEnd(Random random, int x, int z, World world) {
    }

   public void addOres(Block block, World world, Random random, int blockXPos, int blockZPos, int minVeinSize, int maxVeinSize,
		   				int chanceToSpawn, int minY, int maxY) {
	   WorldGenMinable minable = new WorldGenMinable(block, minVeinSize + random.nextInt(maxVeinSize - minVeinSize), Blocks.stone);
	   for(int i = 0; i < chanceToSpawn; i++) {
		   int posX = blockXPos + random.nextInt(15);
		   int posZ = blockZPos + random.nextInt(15);
		   int posY = minY + random.nextInt(maxY - minY);
		   minable.generate(world, random, posX, posY, posZ);
	   }
   }
}
