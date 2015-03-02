package net.wuerfel21.derpyshiz.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;

public class WorldGenEbonyTree extends WorldGenAbstractTree {

	public WorldGenEbonyTree(boolean notify) {
		super(notify);

	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int size = rand.nextInt(3) + 4;

		if (rand.nextInt(10) == 0) {
			size += rand.nextInt(3);
		}

		boolean flag = true;

		if (y >= 1 && y + size + 1 <= 256) {
			int j1;
			int k1;

			for (int i1 = y; i1 <= y + 1 + size; ++i1) {
				byte b0 = 1;

				if (i1 == y) {
					b0 = 0;
				}

				if (i1 >= y + 1 + size - 2) {
					b0 = 2;
				}

				for (j1 = x - b0; j1 <= x + b0 && flag; ++j1) {
					for (k1 = z - b0; k1 <= z + b0 && flag; ++k1) {
						if (i1 >= 0 && i1 < 256) {
							Block block = world.getBlock(j1, i1, k1);

							if (!this.isReplaceable(world, j1, i1, k1)) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}

			if (!flag) {
				return false;
			} else {
				Block block2 = world.getBlock(x, y - 1, z);

				boolean isSoil = block2.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling) Blocks.sapling);
				if (isSoil && y < 256 - size - 1) {
					block2.onPlantGrow(world, x, y - 1, z, x, y, z);
					int k2;

					for (k2 = 0; k2 < size; ++k2) {
						int X = x;
						int Z = z;

						Block block3 = world.getBlock(X, y + k2, Z);

						if (block3.isAir(world, X, y + k2, Z) || block3.isLeaves(world, X, y + k2, Z)) {
							this.setBlockAndNotifyAdequately(world, X, y + k2, Z, GameRegistry.findBlock("derpyshiz", "log"), 0);
						}

					}

					for (k2 = y - 3 + size; k2 <= y + size; ++k2) {
						j1 = k2 - (y + size);
						k1 = 1 - j1 / 2;

						for (int l2 = x - k1; l2 <= x + k1; ++l2) {
							int l1 = l2 - x;

							for (int i2 = z - k1; i2 <= z + k1; ++i2) {
								int j2 = i2 - z;

								int X = l2;
								int Z = i2;

								if (Math.abs(l1) != k1 || Math.abs(j2) != k1 || rand.nextInt(2) != 0 && j1 != 0) {
									Block block1 = world.getBlock(X, k2, Z);

									if (block1.isAir(world, X, k2, Z) || block1.isLeaves(world, X, k2, Z)) {
										this.setBlockAndNotifyAdequately(world, X, k2, Z, GameRegistry.findBlock("derpyshiz", "leaves"), 0);
									}
								}
							}
						}
					}

					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

}
