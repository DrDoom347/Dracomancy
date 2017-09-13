package com.doomdwarf.dracomancy.items;

import com.doomdwarf.dracomancy.Dracomancy;

import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class ItemDracomancyShovel extends ItemSpade implements IColourable
{
	public final ToolMaterial toolMaterial;
	protected int textureColour = -1;
	private String dependantOreDic;
	private ItemStack dependantStack;
    protected String localName;
    
    public ItemDracomancyShovel(DracomancyCustomTool tool)
    {
    	super(tool.material);
    	this.toolMaterial = tool.material;
    	setRegistryName(tool.name);
        setUnlocalizedName(this.getRegistryName().toString());
        setCreativeTab(Dracomancy.DRACO_TAB);
    	localName = tool.localName;
        textureColour = tool.toolColour;
        dependantOreDic = tool.dependantOreDic;
        dependantStack = tool.dependantStack;
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
