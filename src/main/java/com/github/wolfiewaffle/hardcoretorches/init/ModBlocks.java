package com.github.wolfiewaffle.hardcoretorches.init;

import com.github.wolfiewaffle.hardcoretorches.blocks.BlockTorchBurnt;
import com.github.wolfiewaffle.hardcoretorches.blocks.BlockTorchCokeLit;
import com.github.wolfiewaffle.hardcoretorches.blocks.BlockTorchCokeUnlit;
import com.github.wolfiewaffle.hardcoretorches.blocks.BlockTorchLit;
import com.github.wolfiewaffle.hardcoretorches.blocks.BlockTorchUnlit;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {
	public static Block torch_burnt;
	public static Block torch_lit;
	public static Block torch_coke_lit;
	public static Block torch_unlit;
	public static Block torch_coke_unlit;

    public static void createBlocks() {
    	GameRegistry.register(torch_burnt = new BlockTorchBurnt("torch_burnt"));
    	GameRegistry.register(torch_lit = new BlockTorchLit("torch_lit"));
    	GameRegistry.register(torch_coke_lit = new BlockTorchCokeLit("torch_coke_lit"));
    	GameRegistry.register(torch_unlit = new BlockTorchUnlit("torch_unlit"));
    	GameRegistry.register(torch_coke_unlit = new BlockTorchCokeUnlit("torch_coke_unlit"));
    }
}
