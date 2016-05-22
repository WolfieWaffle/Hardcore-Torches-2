package com.github.wolfiewaffle.hardcoretorches.interfaces;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBlockTorchUnlit {

	// No particle effects
	public default void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	}

	// Must have a lit version of the torch
	public Block getLitVariant();
}
