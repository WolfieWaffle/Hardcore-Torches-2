package com.github.wolfiewaffle.hardcoretorches.items;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemTorchCokeLit extends ItemBlock {
	public ItemTorchCokeLit(Block block) {
		super(block);
		this.hasSubtypes = true;
		this.setMaxDamage(ModConfig.configTorchCokeFuel);
		this.setRegistryName(block.getRegistryName());
		this.setMaxStackSize(1);
	}
}
