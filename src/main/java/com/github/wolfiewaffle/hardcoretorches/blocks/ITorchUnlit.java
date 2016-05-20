package com.github.wolfiewaffle.hardcoretorches.blocks;

import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchUnlit;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public interface ITorchUnlit {

	/**
	 * Lights the torch, setting it to a lit torch and keeping the fuel level
	 * @param world The world object (should be provided somewhere)
	 * @param pos The position (also usually provded) of the torch
	 * @param block The new block type
	 */
	public default void lightTorch(World world, BlockPos pos, Block block, IBlockState state, EnumFacing enumfacing, TileEntityTorchUnlit te) {
		// Store old fuel value
		int oldFuel = ((TileEntityTorchUnlit) world.getTileEntity(pos)).getFuelAmount();

		// Set block
		world.setBlockState(pos, block.getDefaultState());

		// Particle effects
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY() + 0.7D;
		double d2 = (double) pos.getZ() + 0.5D;
		double d3 = 0.22D;
		double d4 = 0.27D;

		if (enumfacing.getAxis().isHorizontal()) {
			EnumFacing enumfacing1 = enumfacing.getOpposite();
			for (int i = 0; i < 2; i++) {
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4 * (double) enumfacing1.getFrontOffsetX(), d1 + d3, d2 + d4 * (double) enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D, new int[0]);
				world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4 * (double) enumfacing1.getFrontOffsetX(), d1 + d3, d2 + d4 * (double) enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D, new int[0]);
			}
		} else {
			for (int i = 0; i < 2; i++) {
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
				world.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}

		// Set new fuel value
		((ITorchLit) world.getTileEntity(pos)).setFuel(oldFuel);
	}
}
