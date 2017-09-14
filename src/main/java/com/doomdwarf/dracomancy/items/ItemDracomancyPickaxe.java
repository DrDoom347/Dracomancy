package com.doomdwarf.dracomancy.items;

import com.doomdwarf.dracomancy.Dracomancy;

import net.minecraft.item.ItemPickaxe;

public class ItemDracomancyPickaxe extends ItemPickaxe
{
	public final ToolMaterial toolMaterial;
    protected String localName;
    
    public ItemDracomancyPickaxe(DracomancyCustomTool tool)
    {
    	super(tool.material);
    	this.toolMaterial = tool.material;
    	setRegistryName(tool.name);
        setUnlocalizedName(this.getRegistryName().toString());
        setCreativeTab(Dracomancy.DRACO_TAB);
    }
}
