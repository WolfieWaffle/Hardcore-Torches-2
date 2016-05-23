package com.github.wolfiewaffle.hardcoretorches.items;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemTorchBurnt extends ItemBlock {
	public ItemTorchBurnt(Block block) {
		super(block);
		this.setMaxDamage(ModConfig.configTorchFuel);
		this.setRegistryName(block.getRegistryName());
	}
}
