package com.github.wolfiewaffle.hardcoretorches.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemTorchBasic extends ItemBlock {

	/**
	 * Constructor for a basic torch item.
	 * @param block 	The base block
	 * @param fuel 		The fuel of the torch.
	 * @param stacksize The max stack size of the item.
	 */
	public ItemTorchBasic(Block block, String name, int fuel, int stacksize) {
		this(block, name, fuel);
		this.setMaxStackSize(stacksize);
	}

	/**
	 * Constructor for a basic torch item without stacksize.
	 * @param block 	The base block
	 * @param fuel 		The fuel of the torch.
	 */
	public ItemTorchBasic(Block block, String name, int fuel) {
		super(block);
		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		if (fuel > 0) {
			this.setHasSubtypes(hasSubtypes);
			this.setMaxDamage(fuel);
		}
	}

	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return EnumActionResult.SUCCESS;
	}
}
