package com.github.wolfiewaffle.hardcoretorches.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

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
}
