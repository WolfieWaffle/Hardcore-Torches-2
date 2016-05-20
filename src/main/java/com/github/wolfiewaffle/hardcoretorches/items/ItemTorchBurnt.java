package com.github.wolfiewaffle.hardcoretorches.items;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemTorchBurnt extends ItemBlock {
	public ItemTorchBurnt(Block block) {
		super(block);
		this.setMaxDamage(HardcoreTorches.configTorchFuel);
		this.setRegistryName(block.getRegistryName());
	}
}
