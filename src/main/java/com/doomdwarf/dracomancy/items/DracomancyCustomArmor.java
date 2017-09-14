package com.doomdwarf.dracomancy.items;

import net.minecraft.item.ItemArmor.ArmorMaterial;

public class DracomancyCustomArmor
{
	public String name, localName;
    public ArmorMaterial material;
    
    public DracomancyCustomArmor(String name, ArmorMaterial material)
    {
        localName = name;
        this.name = name.toLowerCase().replaceAll("\\s", "");
        this.material = material;
    }
}
