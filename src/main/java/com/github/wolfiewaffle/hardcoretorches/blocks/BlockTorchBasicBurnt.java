package com.github.wolfiewaffle.hardcoretorches.blocks;

import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTorchBasicBurnt extends BlockTorch {

	public BlockTorchBasicBurnt(String name) {
		super();
		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().toString());
	}

	// No particles
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	}
}
