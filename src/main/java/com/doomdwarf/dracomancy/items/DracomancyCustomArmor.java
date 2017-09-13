package com.doomdwarf.dracomancy.items;

import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class DracomancyCustomArmor
{
	public String name, localName;
    public ArmorMaterial material;
    public static int armorColour;
    public String dependantOreDic;
    public ItemStack dependantStack;
    
    private DracomancyCustomArmor(String name, ArmorMaterial material, int armorColour)
    {
        localName = name;
        this.name = name.toLowerCase().replaceAll("\\s", "");
        this.material = material;
        DracomancyCustomArmor.armorColour = armorColour;
    }
    
    public DracomancyCustomArmor(String name, ArmorMaterial material, int toolColour, Object dependantItem)
    {
        this(name, material, armorColour);
        if(dependantItem == null)
            return;
        if(dependantItem instanceof String)
            dependantOreDic = (String) dependantItem;
        else if(dependantItem instanceof ItemStack)
            dependantStack = (ItemStack) dependantItem;
        else
            throw new IllegalArgumentException("Dependant Item must be a String (ore dictionary) or an ItemStack!");
    }
}
