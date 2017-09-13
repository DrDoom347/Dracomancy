package com.doomdwarf.dracomancy.items;

import com.doomdwarf.dracomancy.Dracomancy;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemDracomancyArmor extends ItemArmor implements IColourable
{
	protected int textureColour = -1;
	private String dependantOreDic;
    private ItemStack dependantStack;
    protected String localName;
    
	public ItemDracomancyArmor(ArmorMaterial material, int renderIndex, EntityEquipmentSlot ArmorType, String name) 
    {
        super(material, renderIndex, ArmorType);
        setRegistryName(name);
        setCreativeTab(Dracomancy.DRACO_TAB);
    }
	
	@Override
    public int getTextureColour()
    {
        return textureColour;
    }

    public String getDependantOreDic()
    {
        return dependantOreDic;
    }

    public ItemStack getDependantStack()
    {
        return dependantStack;
    }
}
