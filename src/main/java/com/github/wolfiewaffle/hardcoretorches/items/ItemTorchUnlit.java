package com.github.wolfiewaffle.hardcoretorches.items;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemTorchUnlit extends ItemBlock {
	public ItemTorchUnlit(Block block) {
		super(block);
		this.hasSubtypes = true;
		this.setMaxDamage(ModConfig.configTorchFuel);
		this.setRegistryName(block.getRegistryName());
		this.setUnlocalizedName(this.getRegistryName().toString());
	}
}
