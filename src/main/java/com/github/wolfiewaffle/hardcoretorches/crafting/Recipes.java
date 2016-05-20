package com.github.wolfiewaffle.hardcoretorches.crafting;

import com.github.wolfiewaffle.hardcoretorches.blocks.ModBlocks;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Recipes {
	public static void init()
	{
		// Shaped
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.torch_unlit)), "A", "B", 'A', new ItemStack(Items.coal, 1, OreDictionary.WILDCARD_VALUE), 'B', "stickWood"));
		//GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.torchUnlitCoke)), "A", "B", 'A', "fuelCoke", 'B', "stickWood"));

		// Light in inventory
		/*if (HardcoreTorches.configLightInInventory) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.torchLit)), new ItemStack(Item.getItemFromBlock(ModBlocks.torchUnlit)), new ItemStack(Items.flint)));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.torchLitCoke)), new ItemStack(Item.getItemFromBlock(ModBlocks.torchUnlitCoke)), new ItemStack(Items.flint)));
		}*/

		// Shapeless
		//GameRegistry.addShapelessRecipe(new ItemStack(Items.stick), new ItemStack(Item.getItemFromBlock(ModBlocks.torch_burnt)));
	}
}