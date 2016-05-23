package com.github.wolfiewaffle.hardcoretorches.blocks;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;
import com.github.wolfiewaffle.hardcoretorches.init.ModBlocks;
import com.github.wolfiewaffle.hardcoretorches.interfaces.IBlockTorchUnlit;
import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchUnlit;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTorchStoneUnlit extends BlockTorchUnlit implements ITileEntityProvider, IBlockTorchUnlit {

	//public int MAX_FUEL = ModConfig.configTorchFuel;

	public BlockTorchStoneUnlit(String name) {
		super(name);
	}

	// Create tile entity
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTorchUnlit();
	}

	// Must have a lit version of the torch
	@Override
	public Block getLitVariant() {
		return ModBlocks.torch_stone_lit;
	}
}
