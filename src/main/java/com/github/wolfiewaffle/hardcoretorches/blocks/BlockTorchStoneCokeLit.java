package com.github.wolfiewaffle.hardcoretorches.blocks;

import com.github.wolfiewaffle.hardcoretorches.init.ModBlocks;
import com.github.wolfiewaffle.hardcoretorches.interfaces.IBlockTorchLit;
import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchCokeLit;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTorchStoneCokeLit extends BlockTorchCokeLit implements ITileEntityProvider, IBlockTorchLit {

	//public int MAX_FUEL = ModConfig.configTorchCokeFuel;

	public BlockTorchStoneCokeLit(String name) {
		super(name);
	}

	// Create tile entity
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTorchCokeLit();
	}

    // Must have an unlit version of the torch
	public Block getUnlitVariant() {
		return ModBlocks.torch_stone_coke_unlit;
	}
}
