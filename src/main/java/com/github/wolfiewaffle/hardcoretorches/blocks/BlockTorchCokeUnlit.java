package com.github.wolfiewaffle.hardcoretorches.blocks;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;

import net.minecraft.block.ITileEntityProvider;

public class BlockTorchCokeUnlit extends BlockTorchBasicUnlit implements ITileEntityProvider {

	public static int MAX_FUEL = HardcoreTorches.configTorchCokeFuel;

	public BlockTorchCokeUnlit(String name) {
		super(name);
	}
}
