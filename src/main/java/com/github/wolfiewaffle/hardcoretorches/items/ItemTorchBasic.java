package com.github.wolfiewaffle.hardcoretorches.items;

import com.github.wolfiewaffle.hardcoretorches.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemTorchBasic extends ItemBlock {

	private Item litVariant;

	/**
	 * Constructor for a basic torch item.
	 * @param block 		The base block
	 * @param fuel 			The fuel of the torch.
	 * @param stacksize 	The max stack size of the item.
	 * @param litVariant	The lit variant of the torch. Null if cannot be lit.
	 */
	public ItemTorchBasic(Block block, String name, int fuel, int stacksize, Item litVariant) {
		this(block, name, fuel, litVariant);
		this.setMaxStackSize(stacksize);
	}

	/**
	 * Constructor for a basic torch item without stacksize.
	 * @param block 		The base block
	 * @param fuel 			The fuel of the torch.
	 * @param litVariant	The lit variant of the torch. Null if cannot be lit.
	 */
	public ItemTorchBasic(Block block, String name, int fuel, Item litVariant) {
		super(block);
		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		this.litVariant = litVariant;
		if (fuel > 0) {
			this.setHasSubtypes(hasSubtypes);
			this.setMaxDamage(fuel);
		}
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ResourceLocation rl = worldIn.getBlockState(pos).getBlock().getRegistryName();
		if (ModConfig.inWorldLightItems.contains(rl)) {
			lightTorch(playerIn, hand);
			return EnumActionResult.SUCCESS;
		}
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	public void lightTorch(EntityPlayer playerIn, EnumHand hand) {
		int oldFuel = playerIn.inventory.getStackInSlot(playerIn.inventory.currentItem).getItemDamage();
		Item heldTorch = playerIn.inventory.getCurrentItem().getItem();
		int count = 0;

		Item torch1;
		Item torch2;

		// Decide what torch to use
		if (((ItemTorchBasic) heldTorch).getLitVariant() != null) {
			System.out.println(((ItemTorchBasic) heldTorch).getLitVariant());
			torch1 = ((ItemTorchBasic) heldTorch).getLitVariant();
			torch2 = heldTorch;
	
			// Get the amount of held items
			if(playerIn.getHeldItem(hand) != null) {
				count = playerIn.getHeldItem(hand).stackSize;
			}
	
			// If there is only one torch, just light it
			if (count == 1) {
				playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, new ItemStack(torch1, count, oldFuel));
			} else if (count > 1) {
				// Subtract one torch from the stack and give a lit torch to the player
				playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, new ItemStack(torch2, count-1, oldFuel));
	
				if (playerIn.inventory.addItemStackToInventory(new ItemStack(torch1, 1, oldFuel)) == true) {
				} else {
					playerIn.dropItem(torch1, 1);
				}
			}
		}
	}

	public Item getLitVariant() {
		return litVariant;
	}
}
