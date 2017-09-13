package com.doomdwarf.dracomancy.items;

import com.doomdwarf.dracomancy.helpers.MaterialNames;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

public class DracomancyCustomTool
{
	public String name, localName;
    public ToolMaterial material;
    public int toolColour;
    public String dependantOreDic;
    public ItemStack dependantStack;
    
    private DracomancyCustomTool(String name, ToolMaterial material, int toolColour)
    {
        localName = name;
        this.name = name.toLowerCase().replaceAll("\\s", "");
        this.material = material;
        this.toolColour = toolColour;
    }
    
    public DracomancyCustomTool(String name, ToolMaterial material, int toolColour, Object dependantItem)
    {
        this(name, material, toolColour);
        if(dependantItem == null)
            return;
        if(dependantItem instanceof String)
            dependantOreDic = (String) dependantItem;
        else if(dependantItem instanceof ItemStack)
            dependantStack = (ItemStack) dependantItem;
        else
            throw new IllegalArgumentException("Dependant Item must be a String (ore dictionary) or an ItemStack!");
    }
    
    public DracomancyCustomTool(MaterialNames.EnumMaterials enumMaterial)
    {
        this(enumMaterial.getMaterialName(), enumMaterial.material, enumMaterial.colour);
        if(enumMaterial.dependantOreDic != null)
            dependantOreDic = enumMaterial.dependantOreDic;
    }
}
