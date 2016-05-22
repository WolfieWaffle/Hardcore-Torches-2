package com.github.wolfiewaffle.hardcoretorches.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.init.ModBlocks;
import com.github.wolfiewaffle.hardcoretorches.interfaces.IBlockTorchUnlit;
import com.github.wolfiewaffle.hardcoretorches.interfaces.ITileEntityTorchLit;
import com.github.wolfiewaffle.hardcoretorches.interfaces.ITileEntityTorchUnlit;
import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchCokeUnlit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTorchCokeUnlit extends BlockTorch implements ITileEntityProvider, IBlockTorchUnlit {

	public int MAX_FUEL = HardcoreTorches.configTorchCokeFuel;

	public BlockTorchCokeUnlit(String name) {
		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		this.setHardness(0.0f);
		this.setResistance(0.0f);
		this.setLightLevel(0.0f);
		this.isBlockContainer = true;
	}

	// Create tile entity
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTorchCokeUnlit();
	}

	// Gets the TileEntity of a block
	public ITileEntityTorchUnlit getTileEntity(World worldIn, BlockPos pos) {
		return worldIn.getTileEntity(pos)  instanceof ITileEntityTorchUnlit ? (ITileEntityTorchUnlit) worldIn.getTileEntity(pos) : null;
	}

	// Must have a lit version of the torch
	@Override
	public Block getLitVariant() {
		return ModBlocks.torch_coke_lit;
	}

    // Gets block drops in some special way so that it returns the right thing
    @Override
	public java.util.List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {

		// Create drop list
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();

		// Get TileEntity
		ITileEntityTorchUnlit te = getTileEntity((World) worldIn, pos);

		if (te != null) {
			// Get correct item meta
			// Item damage goes from 0 to 1000, TE fuel value goes from 1000
			// to 0
			// itemDamage + fuel = MAX_FUEL
			int itemMeta = MAX_FUEL - te.getFuelAmount();

			drop.add(new ItemStack(this, 1, itemMeta));
		}

		return drop;
	}

	// On block right click
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

		ITileEntityTorchUnlit te = (ITileEntityTorchUnlit) worldIn.getTileEntity(pos);

		if (te != null) {

			// Debug
			if (HardcoreTorches.configDebug) {
				if (!worldIn.isRemote) {
					System.out.printf("Right click. Fuel: %d\n", te.getFuelAmount());
				}
			}

			// Get the player's held itemStack
			 ItemStack itemStack = playerIn.getHeldItem(hand);

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

	// Make sure the new TE has the right fuel based of item meta
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
  
		// Get TileEntity to change and meta from item
		ITileEntityTorchUnlit te = worldIn.getTileEntity(pos) instanceof ITileEntityTorchUnlit ? (ITileEntityTorchUnlit) worldIn.getTileEntity(pos) : null;
    	int itemMeta = stack.getItemDamage();

		// Item damage goes from 0 to 1000, TE fuel value goes from 1000 to 0
		// itemDamage + fuel = MAX_FUEL
    	if (te != null) te.setFuel(MAX_FUEL - itemMeta);
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	// Makes sure the TE isn't deleted before the block
    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        if (willHarvest) return true; //If it will harvest, delay deletion of the block until after getDrops
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    // Makes sure the block is actually deleted
    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.setBlockToAir(pos);
    }

	// No particle effects
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	}

	// Lights torch, must provide world methods and the new TE and block
	public void lightTorch(World worldIn, BlockPos pos, Block block, IBlockState state, EnumFacing enumfacing, ITileEntityTorchUnlit te) {
		// Store old fuel value
		int oldFuel = ((ITileEntityTorchUnlit) worldIn.getTileEntity(pos)).getFuelAmount();

		// Set block
		worldIn.setBlockState(pos, block.getDefaultState());

		// Particle effects
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY() + 0.7D;
		double d2 = (double) pos.getZ() + 0.5D;
		double d3 = 0.22D;
		double d4 = 0.27D;

		if (enumfacing.getAxis().isHorizontal()) {
			EnumFacing enumfacing1 = enumfacing.getOpposite();
			for (int i = 0; i < 2; i++) {
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4 * (double) enumfacing1.getFrontOffsetX(), d1 + d3, d2 + d4 * (double) enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D, new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4 * (double) enumfacing1.getFrontOffsetX(), d1 + d3, d2 + d4 * (double) enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D, new int[0]);
			}
		} else {
			for (int i = 0; i < 2; i++) {
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}

		// Set new fuel value
		((ITileEntityTorchLit) worldIn.getTileEntity(pos)).setFuel(oldFuel);
	}
}
