package com.github.wolfiewaffle.hardcoretorches.crafting;

import java.util.Arrays;
import java.util.Random;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

/**
 * A shapeless recipe that damages specified ingredients.
 *
 * @author Choonster (eited by Wolfie_Waffle)
 */
public class ShapelessDamageRecipe extends ShapelessRecipes {
	private final Random random = new Random();

	public ShapelessDamageRecipe(ItemStack output, ItemStack... input) {
		super(output, Arrays.asList(input));
	}

	private ItemStack damageItem(ItemStack stack) {
		if (stack.attemptDamageItem(1, random)) {
			ForgeEventFactory.onPlayerDestroyItem(ForgeHooks.getCraftingPlayer(), stack, null);
			return null;
		}

		return stack;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inventoryCrafting) {
		final ItemStack[] remainingItems = new ItemStack[inventoryCrafting.getSizeInventory()];

		for (int i = 0; i < remainingItems.length; ++i) {
			final ItemStack itemstack = inventoryCrafting.getStackInSlot(i);

			if (itemstack != null && ModConfig.lightItems.contains(itemstack.getItem().getRegistryName())) {
				remainingItems[i] = damageItem(itemstack.copy());
			} else {
				remainingItems[i] = ForgeHooks.getContainerItem(itemstack);
			}
		}

		return remainingItems;
	}
}