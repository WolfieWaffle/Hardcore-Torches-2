package com.github.wolfiewaffle.hardcoretorches.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchUnlit;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTorchUnlit extends BlockTorchLit implements ITileEntityProvider, ITorchUnlit {

	public static int MAX_FUEL = HardcoreTorches.configTorchFuel;

	public BlockTorchUnlit(String name) {
		this();
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	public BlockTorchUnlit() {
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(0.0f);
		this.setResistance(0.0f);
		this.setLightLevel(0.0f);
		this.isBlockContainer = true;
	}

	// Create tile entity
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTorchUnlit();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {

		TileEntityTorchUnlit te = (TileEntityTorchUnlit) world.getTileEntity(pos);

		if (te != null) {
			if (HardcoreTorches.configDebug && !world.isRemote) System.out.printf("Right click. Fuel: %d\n", te.getFuelAmount());
			ItemStack itemStack;

			// Get the player's held itemStack
			itemStack = player.getCurrentEquippedItem();

			if(itemStack != null) {
				// For each item in the config for lighter items, do logic
				for (String item : HardcoreTorches.configLightItems) {
					// If item is on the list
					if (itemStack.getItem() == Item.getByNameOrId(item)) {
						// Light the torch and consume or damage item
						if (itemStack.isItemStackDamageable()) {
							itemStack.attemptDamageItem(1, RANDOM);
						} else {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(itemStack.getItem(), itemStack.stackSize-1, itemStack.getMetadata()));
						}

						lightTorch(world, pos, ModBlocks.torch_lit, state, state.getValue(FACING), te);
					}
				}
				// Same as above, but for free lighter items
				for (String item : HardcoreTorches.configFreeLightItems) {
					// If item is on the list
					if (itemStack.getItem() == Item.getByNameOrId(item)) {
						lightTorch(world, pos, ModBlocks.torch_lit, state, state.getValue(FACING), te);
					}
				}
			}
		}

		return true;
	}

	// Set the fuel level on the TE when placed depending on the held itemStack damage
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

		// Get tileEntity to change and meta from item
		TileEntityTorchUnlit te = (TileEntityTorchUnlit) worldIn.getTileEntity(pos);
		int itemMeta = stack.getItemDamage();

		// Item damage goes from 0 to 1000, TE fuel value goes from 1000 to 0
		// itemDamage + fuel = MAX_FUEL
		te.setFuel(MAX_FUEL - itemMeta);
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

    // Gets block drops in some special way so that it return the right thing
    @Override
    public java.util.List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
    	ArrayList<ItemStack> drop = new ArrayList<ItemStack>();
		TileEntityTorchUnlit te = world.getTileEntity(pos) instanceof TileEntityTorchUnlit ? (TileEntityTorchUnlit) world.getTileEntity(pos) : null;
        if (te != null) {
        	if (HardcoreTorches.configTorchDropMode == 0) {
        		// Item damage goes from 0 to 1000, TE fuel value goes from 1000 to 0
        		// itemDamage + fuel = MAX_FUEL
        		int itemMeta = MAX_FUEL - te.getFuelAmount();
        		drop.add(new ItemStack(ModBlocks.torch_unlit, 1, itemMeta));
        	}
        }
        return drop;
    }

    // No particle effects
	@Override
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
	}
}
