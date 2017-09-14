package com.doomdwarf.dracomancy.items;

import net.minecraft.item.Item.ToolMaterial;

public class DracomancyCustomTool
{
	public String name, localName;
    public ToolMaterial material;
    
    public DracomancyCustomTool(String name, ToolMaterial material)
    {
        localName = name;
        this.name = name.toLowerCase().replaceAll("\\s", "");
        this.material = material;
    }
}
