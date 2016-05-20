package com.github.wolfiewaffle.hardcoretorches.blocks;

import java.util.Random;

import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchLit;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockTorchCokeLit extends BlockTorchLit implements ITileEntityProvider {

	public final static int MAX_FUEL = 1000;

	public BlockTorchCokeLit(String name) {
		super(name);
	}

	// Create tile entity
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTorchLit();
	}

	@Override
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY() + 0.7D;
		double d2 = (double) pos.getZ() + 0.5D;
		double d3 = 0.22D;
		double d4 = 0.27D;

		if (enumfacing.getAxis().isHorizontal()) {
			EnumFacing enumfacing1 = enumfacing.getOpposite();
			if (rand.nextInt(3) == 0) worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4 * (double) enumfacing1.getFrontOffsetX(), d1 + d3, d2 + d4 * (double) enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D, new int[0]);
			worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4 * (double) enumfacing1.getFrontOffsetX(), d1 + d3, d2 + d4 * (double) enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D, new int[0]);
		} else {
			if (rand.nextInt(3) == 0) worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
			worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}
}
