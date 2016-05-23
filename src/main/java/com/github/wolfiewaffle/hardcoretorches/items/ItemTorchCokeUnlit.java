package com.github.wolfiewaffle.hardcoretorches.items;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;
import com.github.wolfiewaffle.hardcoretorches.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemTorchCokeUnlit extends ItemBlock {
	public ItemTorchCokeUnlit(Block block) {
		super(block);
		this.hasSubtypes = true;
		this.setMaxDamage(ModConfig.configTorchCokeFuel);
		this.setRegistryName(ModBlocks.torch_coke_unlit.getRegistryName());
		this.setUnlocalizedName(this.getRegistryName().toString());
	}
}
