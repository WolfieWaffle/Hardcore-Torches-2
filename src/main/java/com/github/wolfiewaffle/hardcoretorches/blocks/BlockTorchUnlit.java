package com.github.wolfiewaffle.hardcoretorches.blocks;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.interfaces.ITorchUnlit;
import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchUnlit;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTorchUnlit extends BlockTorchBasicUnlit implements ITileEntityProvider {

	public static int MAX_FUEL = HardcoreTorches.configTorchFuel;

	public BlockTorchUnlit(String name) {
		super(name);
	}

	/**
	 * Create tile entity (OVERRIDE THIS)
	 */
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTorchUnlit();
	}

	/**
	 * On block right click. (OVERRIDE THIS)
	 */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

		ITorchUnlit te = (ITorchUnlit) worldIn.getTileEntity(pos);

		if (te != null) {
			if (HardcoreTorches.configDebug && !worldIn.isRemote) System.out.printf("Right click. Fuel: %d\n", te.getFuelAmount());
			ItemStack itemStack;

			// Get the player's held itemStack
			itemStack = playerIn.getHeldItem(hand);

			if(itemStack != null) {
				// For each item in the config for lighter items, do logic
				for (String item : HardcoreTorches.configLightItems) {
					// If item is on the list
					if (itemStack.getItem() == Item.getByNameOrId(item)) {
						// Light the torch and consume or damage item
						if (itemStack.isItemStackDamageable()) {
							itemStack.attemptDamageItem(1, RANDOM);
						} else {
							playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, new ItemStack(itemStack.getItem(), itemStack.stackSize-1, itemStack.getMetadata()));
						}

						lightTorch(worldIn, pos, getLitVariant(), state, state.getValue(FACING), te);
						return true;
					}
				}
				// Same as above, but for free lighter items
				for (String item : HardcoreTorches.configFreeLightItems) {
					// If item is on the list
					if (itemStack.getItem() == Item.getByNameOrId(item)) {
						lightTorch(worldIn, pos, getLitVariant(), state, state.getValue(FACING), te);
						return true;
					}
				}
			}
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}

	public Block getLitVariant() {
		return ModBlocks.torch_lit;
	}
}
