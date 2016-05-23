package com.github.wolfiewaffle.hardcoretorches.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemTorchStoneBurnt extends ItemBlock {
	public ItemTorchStoneBurnt(Block block) {
		super(block);
		this.setRegistryName(block.getRegistryName());
	}
}
