package melonslise.runicinscription.common.worldgen;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import melonslise.runicinscription.RunicInscription;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldGenRunestone implements IWorldGenerator
{
	public Block generatedBlock;
	public int metadata;
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(world.provider.dimensionId == 0)
		{
			for(int chance1 = 0; chance1 < random.nextInt(3); ++chance1)
			{
				int x = chunkX * 16 + random.nextInt(16) + 8;
				int z = chunkZ * 16 + random.nextInt(16) + 8;
				int y = random.nextInt(64) + 64;
			
				double chance2 = Math.random() * 100;
				int metadata = 0;
				if(chance2 < 25)
				{
					metadata = 0;
				}
				else if(chance2 < 50)
				{
					metadata = 1;
				}
				else if(chance2 < 75)
				{
					metadata = 2;
				}
				else
				{
					metadata = 3;
				}
				
				double chance3 = random.nextInt(8);
				Block generatedBlock = RunicInscription.blockMonolithFirebolt;
				if(chance3 == 0)
				{
					generatedBlock = RunicInscription.blockMonolithFirebolt;
				}
				if(chance3 == 1)
				{
					generatedBlock = RunicInscription.blockMonolithIcebolt;
				}
				if(chance3 == 2)
				{
					generatedBlock = RunicInscription.blockMonolithLightningbolt;
				}
				if(chance3 == 3)
				{
					generatedBlock = RunicInscription.blockMonolithHeal;
				}
				if(chance3 == 4)
				{
					generatedBlock = RunicInscription.blockMonolithDispel;
				}
				if(chance3 == 5)
				{
					generatedBlock = RunicInscription.blockMonolithFirerain;
				}
				if(chance3 == 6)
				{
					generatedBlock = RunicInscription.blockMonolithChainlightning;
				}
			
				this.generatedBlock = generatedBlock;
				this.metadata = metadata;
				
				this.generate(world, random, x, y, z);
			}
		}
	}
	
    public boolean generate(World world, Random random, int x, int y, int z)
    {
    	int i1 = x + random.nextInt(8) - random.nextInt(8);
    	int j1 = y + random.nextInt(4) - random.nextInt(4);
    	int k1 = z + random.nextInt(8) - random.nextInt(8);
    	
    	if ((!world.provider.hasNoSky || j1 < 255) && (world.getBlock(i1, j1 - 1, k1) == Blocks.grass || world.getBlock(i1, j1 - 1, k1) == Blocks.sand || world.getBlock(i1, j1 - 1, k1) == Blocks.dirt) && this.generatedBlock.canPlaceBlockAt(world, i1, j1, k1) && this.generatedBlock.canPlaceBlockAt(world, i1, j1 + 1, k1))
    	{
    		world.setBlock(i1, j1 + 1, k1, this.generatedBlock, this.metadata, 2);
    		world.setBlock(i1, j1, k1, RunicInscription.blockMonolith, 0, 2);
    	}
    	
        return true;
    }
}
