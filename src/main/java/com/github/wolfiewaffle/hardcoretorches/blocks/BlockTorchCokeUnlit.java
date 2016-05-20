package com.github.wolfiewaffle.hardcoretorches.blocks;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.tileentity.TileEntityTorchUnlit;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTorchCokeUnlit extends BlockTorchUnlit implements ITileEntityProvider, ITorchUnlit {

	public static int MAX_FUEL = HardcoreTorches.configTorchFuel;

	public BlockTorchCokeUnlit(String name) {
		this();
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	public BlockTorchCokeUnlit() {
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
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
/*
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {

		TileEntityTorchUnlit te = (TileEntityTorchUnlit) world.getTileEntity(pos);

		if (te != null) {
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
*/
	// Set the fuel level on the TE when placed depending on the held itemStack damage
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

		// Get tileEntity to change and meta from item
		TileEntityTorchUnlit te = (TileEntityTorchUnlit) worldIn.getTileEntity(pos);
		int itemMeta = stack.getItemDamage();

		// Item damage goes from 0 to 1000, TE fuel value goes from 1000 to 0
		// itemDamage + fuel = MAX_FUEL
		te.setFuel(HardcoreTorches.configTorchFuel - itemMeta);
	}
}
