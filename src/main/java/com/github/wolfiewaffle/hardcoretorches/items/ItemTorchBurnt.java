package com.github.wolfiewaffle.hardcoretorches.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemTorchBurnt extends ItemBlock {
	public ItemTorchBurnt(Block block) {
		super(block);
		this.setRegistryName(block.getRegistryName());
	}
}
