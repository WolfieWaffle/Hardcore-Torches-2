package com.github.wolfiewaffle.hardcoretorches.crafting;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;
import com.github.wolfiewaffle.hardcoretorches.init.ModBlocks;
import com.github.wolfiewaffle.hardcoretorches.init.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes {
	public static void init() {
		// Torch recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.torch_unlit)), "A", "B", 'A', new ItemStack(Items.COAL, 1, OreDictionary.WILDCARD_VALUE), 'B', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.torch_coke_unlit)), "A", "B", 'A', "fuelCoke", 'B', "stickWood"));

		// Light in inventory
		if (ModConfig.configLightInInventory) {

			// Free lighter items
			for (String item : ModConfig.configFreeLightItems) {
				GameRegistry.addRecipe(new ShapelessReuseRecipe(new ItemStack(ModItems.torch_lit), new ItemStack(ModItems.torch_unlit), new ItemStack(Item.getByNameOrId(item))));
				if (ModConfig.configRegCokeTorches) GameRegistry.addRecipe(new ShapelessReuseRecipe(new ItemStack(ModItems.torch_coke_lit), new ItemStack(ModItems.torch_coke_unlit), new ItemStack(Item.getByNameOrId(item))));
			}

			// Consumed or damaged lighter items
			for (String item : ModConfig.configLightItems) {
				// Is item damageable?
				if (Item.getByNameOrId(item).isDamageable()) {
					GameRegistry.addRecipe(new ShapelessDamageRecipe(new ItemStack(ModItems.torch_lit), new ItemStack(ModItems.torch_unlit), new ItemStack(Item.getByNameOrId(item), 1, OreDictionary.WILDCARD_VALUE)));
					if (ModConfig.configRegCokeTorches) GameRegistry.addRecipe(new ShapelessDamageRecipe(new ItemStack(ModItems.torch_coke_lit), new ItemStack(ModItems.torch_coke_unlit), new ItemStack(Item.getByNameOrId(item), 1, OreDictionary.WILDCARD_VALUE)));
				} else {
					GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.torch_lit), new ItemStack(ModItems.torch_unlit), new ItemStack(Item.getByNameOrId(item))));
					if (ModConfig.configRegCokeTorches) GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.torch_coke_lit), new ItemStack(ModItems.torch_coke_unlit), new ItemStack(Item.getByNameOrId(item))));
				}
			}
		}

		//GameRegistry.addShapelessRecipe(new ItemStack(Items.stick), new ItemStack(Item.getItemFromBlock(ModBlocks.torch_burnt)));
	}
}