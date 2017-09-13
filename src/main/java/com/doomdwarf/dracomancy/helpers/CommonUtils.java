package com.doomdwarf.dracomancy.helpers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CommonUtils
{
	/**
	 * THIS CLASS IS TAKEN FROM THEBRIGTHSPARK'S SPARK'S HAMMERS!
	 */
	
	/**
     * Capitalizes the first letter of the string
     */
    public static String capitaliseFirstLetter(String text)
    {
        if(text == null || text.length() <= 0)
            return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    public static Item getRegisteredItem(String itemId)
    {
        return Item.REGISTRY.getObject(new ResourceLocation(itemId));
    }

    public static String getRegisteredId(Item item)
    {
        ResourceLocation id = Item.REGISTRY.getNameForObject(item);
        return id == null ? null : id.toString();
    }

    public static boolean isStackEmptyOrNull(ItemStack stack)
    {
        return stack == null || stack.getItem() instanceof ItemAir;
    }
}
