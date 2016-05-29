package com.github.wolfiewaffle.hardcoretorches.init;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;
import com.github.wolfiewaffle.hardcoretorches.blocks.BlockTorchBasicBurnt;
import com.github.wolfiewaffle.hardcoretorches.blocks.BlockTorchBasicLit;
import com.github.wolfiewaffle.hardcoretorches.blocks.BlockTorchBasicUnlit;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {

	private static final int fuelReg = ModConfig.configTorchFuel;
	private static final int fuelCoke = ModConfig.configTorchCokeFuel;

	// Torches
	public static Block torch_burnt;
	public static Block torch_lit;
	public static Block torch_unlit;

	// Coke torches
	public static Block torch_coke_lit;
	public static Block torch_coke_unlit;

	// Stone torches
	public static Block torch_stone_burnt;
	public static Block torch_stone_lit;
	public static Block torch_stone_unlit;

	// Stone coke torches
	public static Block torch_stone_coke_lit;
	public static Block torch_stone_coke_unlit;

	public static void createBlocks() {

		// Torches
		GameRegistry.register(torch_burnt = new BlockTorchBasicBurnt("torch_burnt"));
		GameRegistry.register(torch_lit = new BlockTorchBasicLit("torch_lit", fuelReg, torch_burnt, torch_unlit));
		GameRegistry.register(torch_unlit = new BlockTorchBasicUnlit("torch_unlit", fuelReg, torch_lit));

		// Coke torches
		GameRegistry.register(torch_coke_lit = new BlockTorchBasicLit("torch_coke_lit", fuelCoke, torch_burnt, torch_coke_unlit));
		GameRegistry.register(torch_coke_unlit = new BlockTorchBasicUnlit("torch_coke_unlit", fuelCoke, torch_coke_lit));

		// Stone torches
		GameRegistry.register(torch_stone_burnt = new BlockTorchBasicBurnt("torch_stone_burnt"));
		GameRegistry.register(torch_stone_lit = new BlockTorchBasicLit("torch_stone_lit", fuelReg, torch_stone_burnt, torch_stone_unlit));
		GameRegistry.register(torch_stone_unlit = new BlockTorchBasicUnlit("torch_stone_unlit", fuelReg, torch_stone_lit));

		// Stone coke torches
		GameRegistry.register(torch_stone_coke_lit = new BlockTorchBasicLit("torch_stone_coke_lit", fuelCoke, torch_stone_burnt, torch_stone_coke_unlit));
		GameRegistry.register(torch_stone_coke_unlit = new BlockTorchBasicUnlit("torch_stone_coke_unlit", fuelCoke, torch_stone_coke_lit));
	}
}
