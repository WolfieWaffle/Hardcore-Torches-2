package com.github.wolfiewaffle.hardcoretorches.crafting;

import com.github.wolfiewaffle.hardcoretorches.HardcoreTorches;
import com.github.wolfiewaffle.hardcoretorches.ModConfig;
import com.github.wolfiewaffle.hardcoretorches.init.ModBlocks;
import com.github.wolfiewaffle.hardcoretorches.init.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes {
	public static void init() {
		final ItemStack coal = new ItemStack(Items.COAL, 1, OreDictionary.WILDCARD_VALUE);

		// Remove recipes
		RecipeRemover.removeAnyRecipe(new ItemStack(Blocks.TORCH));
		if (HardcoreTorches.isTconInstalled) {
			// TODO: Fix this
		}

		// Torch recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.torch_unlit)), "A", "B", 'A', coal, 'B', "stickWood"));
		if (ModConfig.configCraftCokeTorches) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.torch_coke_unlit)), "A", "B", 'A', "fuelCoke", 'B', "stickWood"));
		if (ModConfig.configCraftStoneTorches && HardcoreTorches.isTconInstalled) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.torch_stone_unlit)), "A", "B", 'A', coal, 'B', "stickStone"));
			if (ModConfig.configCraftCokeTorches) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.torch_stone_coke_unlit)), "A", "B", 'A', "fuelCoke", 'B', "stickStone"));
			}
		}

		// Light in inventory
		if (ModConfig.configLightInInventory) {

			// Free lighter items
			for (String string : ModConfig.configFreeLightItems) {
				// Shorten names
				ItemStack itemStack = new ItemStack(Item.getByNameOrId(string));

				// Torches
				GameRegistry.addRecipe(new ShapelessReuseRecipe(new ItemStack(ModItems.torch_lit), new ItemStack(ModItems.torch_unlit), itemStack));

				// Coke torches
				if (ModConfig.configCraftCokeTorches) GameRegistry.addRecipe(new ShapelessReuseRecipe(new ItemStack(ModItems.torch_coke_lit), new ItemStack(ModItems.torch_coke_unlit), itemStack));

				// Stone torches
				if (ModConfig.configCraftStoneTorches && HardcoreTorches.isTconInstalled) {
					GameRegistry.addRecipe(new ShapelessReuseRecipe(new ItemStack(ModItems.torch_stone_lit), new ItemStack(ModItems.torch_stone_unlit), itemStack));

					// Stone coke torches
					if (ModConfig.configCraftCokeTorches) GameRegistry.addRecipe(new ShapelessReuseRecipe(new ItemStack(ModItems.torch_stone_coke_lit), new ItemStack(ModItems.torch_stone_coke_unlit), itemStack));
				}
			}

			// Consumed or damaged lighter items
			for (String string : ModConfig.configLightItems) {
				// Shorten names
				ItemStack itemStack = new ItemStack(Item.getByNameOrId(string), 1, OreDictionary.WILDCARD_VALUE);

				// Is item damageable?
				if (itemStack.getItem().isDamageable()) {

					// Torches
					GameRegistry.addRecipe(new ShapelessDamageRecipe(new ItemStack(ModItems.torch_lit), new ItemStack(ModItems.torch_unlit), itemStack));

					// Coke torches
					if (ModConfig.configCraftCokeTorches) GameRegistry.addRecipe(new ShapelessDamageRecipe(new ItemStack(ModItems.torch_coke_lit), new ItemStack(ModItems.torch_coke_unlit), itemStack));

					// Stone torches
					if (ModConfig.configCraftStoneTorches && HardcoreTorches.isTconInstalled) {
						GameRegistry.addRecipe(new ShapelessDamageRecipe(new ItemStack(ModItems.torch_stone_lit), new ItemStack(ModItems.torch_stone_unlit), itemStack));

						// Stone coke torches
						if (ModConfig.configCraftCokeTorches) GameRegistry.addRecipe(new ShapelessDamageRecipe(new ItemStack(ModItems.torch_stone_coke_lit), new ItemStack(ModItems.torch_stone_coke_unlit), itemStack));
					}

				// Item is not damageable
				} else {

					// Torches
					GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.torch_lit), new ItemStack(ModItems.torch_unlit), itemStack));

					// Coke torches
					if (ModConfig.configCraftCokeTorches) GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.torch_coke_lit), new ItemStack(ModItems.torch_coke_unlit), itemStack));

					// Stone torches
					if (ModConfig.configCraftStoneTorches && HardcoreTorches.isTconInstalled) {
						GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.torch_stone_lit), new ItemStack(ModItems.torch_stone_unlit), itemStack));

						// Stone coke torches
						if (ModConfig.configCraftCokeTorches) GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.torch_stone_coke_lit), new ItemStack(ModItems.torch_stone_coke_unlit), itemStack));
					}
				}
			}
		}

		//GameRegistry.addShapelessRecipe(new ItemStack(Items.stick), new ItemStack(Item.getItemFromBlock(ModBlocks.torch_burnt)));
	}
}