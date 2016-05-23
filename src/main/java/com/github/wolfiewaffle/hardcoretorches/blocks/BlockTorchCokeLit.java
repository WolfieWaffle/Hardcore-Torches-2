package com.github.wolfiewaffle.hardcoretorches.blocks;

import java.util.ArrayList;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;
import com.github.wolfiewaffle.hardcoretorches.init.ModBlocks;
import com.github.wolfiewaffle.hardcoretorches.interfaces.IBlockTorchLit;
import com.github.wolfiewaffle.hardcoretorches.interfaces.ITileEntityTorchLit;
import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchCokeLit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTorchCokeLit extends BlockTorch implements ITileEntityProvider, IBlockTorchLit {

	public int MAX_FUEL = ModConfig.configTorchCokeFuel;

	public BlockTorchCokeLit(String name) {
		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		this.setHardness(0.0f);
		this.setResistance(0.0f);
		this.setLightLevel(1.0f);
		this.isBlockContainer = true;
	}

	// Create tile entity
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTorchCokeLit();
	}

	// Gets the TileEntity of a block
	public ITileEntityTorchLit getTileEntity(IBlockAccess worldIn, BlockPos pos) {
		return worldIn.getTileEntity(pos) instanceof ITileEntityTorchLit ? (ITileEntityTorchLit) worldIn.getTileEntity(pos) : null;
	}

    // Must have an unlit version of the torch
	public Block getUnlitVariant() {
		return ModBlocks.torch_coke_unlit;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

		ITileEntityTorchLit te = (ITileEntityTorchLit) worldIn.getTileEntity(pos);

		if (te != null) {
			if (ModConfig.configDebug && !worldIn.isRemote) System.out.printf("Right click. Fuel: %d\n", te.getFuelAmount());
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}

	// Make sure the new TE has the right fuel based of item meta
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
  
		// Get TileEntity to change and meta from item
		ITileEntityTorchLit te = worldIn.getTileEntity(pos) instanceof ITileEntityTorchLit ? (ITileEntityTorchLit) worldIn.getTileEntity(pos) : null;
    	int itemMeta = stack.getItemDamage();

		// Item damage goes from 0 to 1000, TE fuel value goes from 1000 to 0
		// itemDamage + fuel = MAX_FUEL
    	if (te != null) te.setFuel(MAX_FUEL - itemMeta);
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

	// Gets block drops in some special way so that it returns the right thing
	@Override
	public java.util.List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {

		// Create drop list
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();

		// Get TileEntity
    	ITileEntityTorchLit te = getTileEntity(worldIn, pos);

		if (te != null) {

			// If we aren't dropping burnt torch
			if (ModConfig.configTorchDropMode != 2) {

				// Get correct item meta
				// Item damage goes from 0 to 1000, TE fuel value goes from 1000 to 0
				// itemDamage + fuel = MAX_FUEL
				int itemMeta = MAX_FUEL - te.getFuelAmount();

        		// 0 - Drop as lit torch, 1 - drop as unlit torch
        		if (ModConfig.configTorchDropMode == 0) {
        			drop.add(new ItemStack(this, 1, itemMeta));
				} else {
					drop.add(new ItemStack(getUnlitVariant(), 1, itemMeta));
				}
			}
			// Else do drop burnt torch, we don't need te data for this
			else {
				drop.add(new ItemStack(ModBlocks.torch_burnt, 1, 0));
			}
 
        }

        return drop;
    }
}
