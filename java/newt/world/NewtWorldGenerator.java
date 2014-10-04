package Newt.world;

import java.util.Random;

import Newt.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
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
    	addOres(ModBlocks.mageniteoreBlock, world, random, x, z, 2, 8, 10, 0, 40);
    	addOres(ModBlocks.mageniteoreBlock, world, random, x, z, 2, 8, 10, 0, 20);
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
