package com.github.wolfiewaffle.hardcoretorches.items;

import com.github.wolfiewaffle.hardcoretorches.blocks.ModBlocks;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {
	public static ItemBlock torch_burnt = new ItemTorchBurnt(ModBlocks.torch_burnt);

    public static void createItems() {
    	GameRegistry.register(torch_burnt);
    }
}
