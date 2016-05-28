package com.github.wolfiewaffle.hardcoretorches.interfaces;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBlockTorchLit {

	// Particle effects
	public default void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	}

	// Must have a lit version of the torch
	public Block getUnlitVariant();

	// Must have a burnt version of the torch
	public Block getBurntVariant();

	// Must be able to burn out
	public void burnOut(World worldIn, BlockPos pos);
}
