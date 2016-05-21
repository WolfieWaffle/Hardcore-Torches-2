package com.github.wolfiewaffle.hardcoretorches.blocks;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchLit;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTorchLit extends BlockTorchBasicLit implements ITileEntityProvider {

	public static int MAX_FUEL = HardcoreTorches.configTorchFuel;

	public BlockTorchLit(String name) {
		super(name);
	}

	/**
	 * Create tile entity (OVERRIDE THIS)
	 */
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTorchLit();
	}

	public Block getUnlitVariant() {
		return ModBlocks.torch_unlit;
	}
}
