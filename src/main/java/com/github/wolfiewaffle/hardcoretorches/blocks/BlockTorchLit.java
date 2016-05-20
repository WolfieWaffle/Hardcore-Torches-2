package com.github.wolfiewaffle.hardcoretorches.blocks;

import java.util.ArrayList;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchLit;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTorchLit extends BlockTorch implements ITileEntityProvider {

	public static int MAX_FUEL = HardcoreTorches.configTorchFuel;

	public BlockTorchLit(String name) {
		this();
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	public BlockTorchLit() {
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(0.0f);
		this.setResistance(0.0f);
		this.setLightLevel(1.0f);
		this.isBlockContainer = true;
	}

	// Create tile entity
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTorchLit();
	}

	/**
	 * Required TE method. This cleans up the TE when the block is broken
	 * 
	 * @see net.minecraft.block.Block#breakBlock(net.minecraft.world.World,
	 *      net.minecraft.util.BlockPos, net.minecraft.block.state.IBlockState)
	 */
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		super.breakBlock(world, pos, state);
		world.removeTileEntity(pos);
	}

	/**
	 * Required TE method. Passes events to the TE
	 * 
	 * @see net.minecraft.block.Block#onBlockEventReceived(net.minecraft.world.World,
	 *      net.minecraft.util.BlockPos, net.minecraft.block.state.IBlockState,
	 *      int, int)
	 */
	@Override
	public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
		super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
	}

	/**
	 * Don't use the special TE rendering. This requires us to override this
	 * method and return 3
	 * 
	 * @see net.minecraft.block.Block#getRenderType()
	 */
	@Override
	public int getRenderType() {
		return 3;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityTorchLit te = (TileEntityTorchLit) world.getTileEntity(pos);

		// Main logic, must make sure TE isn't null
		if (te != null) {
			if (!world.isRemote) {
				if (HardcoreTorches.configDebug) System.out.printf("Right click. Fuel: %d\n", te.getFuelAmount());
	
				// Light held torches
				if (player.getHeldItem() == new ItemStack(ModBlocks.torch_unlit)) {
					//System.out.println("Holding burnt torch");
				}
			}
		}

		return super.onBlockActivated(world, pos, state, player, side, hitX, hitY, hitZ);
	}

	// Set the fuel level on the TE when placed depending on the held itemStack damage
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    	// Get tileEntity to change and meta from item
		TileEntityTorchLit te = (TileEntityTorchLit) worldIn.getTileEntity(pos);
    	int itemMeta = stack.getItemDamage();

		// Item damage goes from 0 to 1000, TE fuel value goes from 1000 to 0
		// itemDamage + fuel = MAX_FUEL
    	te.setFuel(MAX_FUEL - itemMeta);
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	// Makes sure the TE isn't deleted before the block
    @Override
    public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        if (willHarvest) return true; //If it will harvest, delay deletion of the block until after getDrops
        return super.removedByPlayer(world, pos, player, willHarvest);
    }

    // Makes sure the block is actually deleted
    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
        super.harvestBlock(world, player, pos, state, te);
        world.setBlockToAir(pos);
    }

    // Gets block drops in some special way so that it returns the right thing
    @Override
    public java.util.List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
    	ArrayList<ItemStack> drop = new ArrayList<ItemStack>();
        TileEntityTorchLit te = world.getTileEntity(pos) instanceof TileEntityTorchLit ? (TileEntityTorchLit)world.getTileEntity(pos) : null;
        if (te != null) {
        	if (HardcoreTorches.configTorchDropMode != 2) {
        		// Item damage goes from 0 to 1000, TE fuel value goes from 1000 to 0
        		// itemDamage + fuel = MAX_FUEL
        		int itemMeta = MAX_FUEL - te.getFuelAmount();

        		// 0 - Drop as lit torch, 1 - drop as unlit torch
        		if (HardcoreTorches.configTorchDropMode == 0) {
        			drop.add(new ItemStack(ModBlocks.torch_lit, 1, itemMeta));
        		} else {
        			drop.add(new ItemStack(ModBlocks.torch_unlit, 1, itemMeta));
        		}
        	} else {
        		drop.add(new ItemStack(ModBlocks.torch_burnt, 1, 0));
        	}
        }
        return drop;
    }
}
