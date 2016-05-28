package com.github.wolfiewaffle.hardcoretorches.blocks;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;
import com.github.wolfiewaffle.hardcoretorches.init.ModBlocks;
import com.github.wolfiewaffle.hardcoretorches.interfaces.IBlockTorchLit;
import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchLit;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTorchStoneLit extends BlockTorchLit implements ITileEntityProvider, IBlockTorchLit {

	public int MAX_FUEL = ModConfig.configTorchFuel;

	public BlockTorchStoneLit(String name) {
		super(name);
	}

	// Create tile entity
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTorchLit();
	}

    // Must have an unlit version of the torch
	public Block getUnlitVariant() {
		return ModBlocks.torch_unlit;
	}


	/**
	 * @return The Block type for this torches burnt variant.
	 */
	@Override
	public Block getBurntVariant() {
		return ModBlocks.torch_stone_burnt;
	}
}
