package com.github.wolfiewaffle.hardcoretorches.items;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemTorchUnlit extends ItemBlock {
	public ItemTorchUnlit(Block block) {
		super(block);
		this.hasSubtypes = true;
		this.setMaxDamage(HardcoreTorches.configTorchFuel);
		//this.setRegistryName(ModBlocks.torch_coke_unlit.getRegistryName());
		this.setMaxStackSize(1);
	}
}
