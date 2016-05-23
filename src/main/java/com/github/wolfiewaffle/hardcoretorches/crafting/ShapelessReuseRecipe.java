package com.github.wolfiewaffle.hardcoretorches.crafting;

import java.util.Arrays;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.common.ForgeHooks;

/**
 * A shapeless recipe that keep specified items in the grid.
 *
 * @author Choonster (edited by Wolfie_Wafle)
 */
public class ShapelessReuseRecipe extends ShapelessRecipes {
	public ShapelessReuseRecipe(ItemStack output, ItemStack... input) {
		super(output, Arrays.asList(input));
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inventoryCrafting) {
		final ItemStack[] remainingItems = new ItemStack[inventoryCrafting.getSizeInventory()];

		for (int i = 0; i < remainingItems.length; ++i) {
			final ItemStack itemstack = inventoryCrafting.getStackInSlot(i);

			if (itemstack != null && ModConfig.freeLightItems.contains(itemstack.getItem().getRegistryName())) {
				remainingItems[i] = itemstack.copy();
			} else {
				remainingItems[i] = ForgeHooks.getContainerItem(itemstack);
			}
		}

		return remainingItems;
	}
}