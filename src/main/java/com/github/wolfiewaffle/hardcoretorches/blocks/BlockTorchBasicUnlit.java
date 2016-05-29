package com.github.wolfiewaffle.hardcoretorches.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;
import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchLit;
import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchUnlit;

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

public class BlockTorchBasicUnlit extends BlockTorch implements ITileEntityProvider {

	private int maxFuel;
	private Block litVariant;

	public BlockTorchBasicUnlit(String name, int maxFuel, Block litVariant) {
		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		this.setHardness(0.0f);
		this.setResistance(0.0f);
		this.setLightLevel(0.0f);
		this.isBlockContainer = true;

		this.maxFuel = maxFuel;
		this.litVariant = litVariant;
	}

	// Gets the TileEntity of a block
	public TileEntityTorchUnlit getTileEntity(IBlockAccess worldIn, BlockPos pos) {
		return worldIn.getTileEntity(pos) 
				instanceof TileEntityTorchUnlit ? (TileEntityTorchUnlit) worldIn.getTileEntity(pos) : null;
	}

	// Create tile entity
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTorchUnlit();
	}

    // Gets the burnt version of the torch
	public Block getLitVariant() {
		return litVariant;
	}

	// Gets block drops in some special way so that it returns the right thing
	@Override
	public java.util.List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {

		// Create drop list
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();

		// Get TileEntity
		TileEntityTorchUnlit te = getTileEntity(worldIn, pos);

		if (te != null) {

			// Get correct item meta
			// Item damage goes from 0 to 1000, TE fuel value goes from 1000 to 0
			// itemDamage + fuel = MAX_FUEL
			int itemMeta = maxFuel - te.getFuelAmount();

			// 0 - Drop as lit torch, 1 - drop as unlit torch
			drop.add(new ItemStack(this, 1, itemMeta));
		}

		return drop;
	}

	// On block right click
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

		TileEntityTorchUnlit te = (TileEntityTorchUnlit) worldIn.getTileEntity(pos);

		if (te != null) {

			// Debug
			if (ModConfig.configDebug) {
				if (!worldIn.isRemote) {
					System.out.printf("Right click. Fuel: %d\n", te.getFuelAmount());
				}
			}

			// Get the player's held itemStack
			 ItemStack itemStack = playerIn.getHeldItem(hand);

			if(itemStack != null) {
				// For each item in the config for lighter items, do logic
				for (String item : ModConfig.configLightItems) {
					// If item is on the list
					if (itemStack.getItem() == Item.getByNameOrId(item)) {
						// Light the torch and consume or damage item
						if (itemStack.isItemStackDamageable()) {
							itemStack.attemptDamageItem(1, RANDOM);
						} else {
							playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, new ItemStack(itemStack.getItem(), itemStack.stackSize-1, itemStack.getMetadata()));
						}

						lightTorch(worldIn, pos, state.getValue(FACING));
						return true;
					}
				}
				// Same as above, but for free lighter items
				for (String item : ModConfig.configFreeLightItems) {
					// If item is on the list
					if (itemStack.getItem() == Item.getByNameOrId(item)) {
						lightTorch(worldIn, pos, state.getValue(FACING));
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
		TileEntityTorchUnlit te = worldIn.getTileEntity(pos) instanceof TileEntityTorchUnlit ? (TileEntityTorchUnlit) worldIn.getTileEntity(pos) : null;
    	int itemMeta = stack.getItemDamage();

		// Item damage goes from 0 to 1000, TE fuel value goes from 1000 to 0
		// itemDamage + fuel = MAX_FUEL
    	if (te != null) te.setFuel(maxFuel - itemMeta);
    	System.out.println("FUEL IS " + maxFuel);
		//super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
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

	// Lights torch
	public void lightTorch(World worldIn, BlockPos pos, EnumFacing enumfacing) {
		// Store old fuel value
		int oldFuel = ((TileEntityTorchUnlit) worldIn.getTileEntity(pos)).getFuelAmount();

		// Set block
		worldIn.setBlockState(pos, getLitVariant().getDefaultState());

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
		((TileEntityTorchLit) worldIn.getTileEntity(pos)).setFuel(oldFuel);
	}

	// No particles
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	}
}
