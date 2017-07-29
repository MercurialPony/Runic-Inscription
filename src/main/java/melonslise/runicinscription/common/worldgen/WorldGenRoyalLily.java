package melonslise.runicinscription.common.worldgen;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import melonslise.runicinscription.RunicInscription;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraftforge.common.BiomeDictionary;

public class WorldGenRoyalLily implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(world.provider.dimensionId == 0)
		{
			BiomeGenBase biome = world.getBiomeGenForCoords(chunkX * 16, chunkZ * 16);
			
			if((BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST)) || BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.PLAINS))
			{
				for(int chance1 = 0; chance1 < random.nextInt(5); ++chance1)
				{
					int x = chunkX * 16 + random.nextInt(16) + 8;
					int z = chunkZ * 16 + random.nextInt(16) + 8;
					int y = random.nextInt(128);
				
					double chance2 = Math.random() * 100;
					int metadata = 3;
					if(chance2 < 85)
					{
						metadata = 7;
					}
					else if(chance2 < 90)
					{
						metadata = 6;
					}
					else if(chance2 < 95)
					{
						metadata = 3;
					}
					else
					{
						metadata = 1;
					}
				
					WorldGenFlowers flowerGen = new WorldGenFlowers(RunicInscription.cropRoyalLily);
					flowerGen.func_150550_a(RunicInscription.cropRoyalLily, metadata);
					flowerGen.generate(world, random, x, y, z);
				}
			}
		}
	}
}
