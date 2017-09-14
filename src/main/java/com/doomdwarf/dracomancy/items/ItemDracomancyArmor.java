package com.doomdwarf.dracomancy.items;

import com.doomdwarf.dracomancy.Dracomancy;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemDracomancyArmor extends ItemArmor
{
    protected String localName;
    
	public ItemDracomancyArmor(ArmorMaterial material, int renderIndex, EntityEquipmentSlot ArmorType, String name) 
    {
        super(material, renderIndex, ArmorType);
        setRegistryName(name);
        setCreativeTab(Dracomancy.DRACO_TAB);
    }
}
