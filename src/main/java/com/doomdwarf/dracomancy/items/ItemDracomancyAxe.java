package com.doomdwarf.dracomancy.items;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.doomdwarf.dracomancy.Dracomancy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemDracomancyAxe extends ItemTool implements IColourable
{
		// This is a list of blocks harvest-able by it
		private static Set<Block> blocks = null;
		
		/**
		* This initializes the ItemDracomancyAxe object.
		 * @param name 
		* @param ToolMaterial material
		*/
		public final ToolMaterial toolMaterial;
		protected int textureColour = -1;
		private String dependantOreDic;
		private ItemStack dependantStack;
	    protected String localName;
	    
	    public ItemDracomancyAxe(DracomancyCustomTool tool)
	    {
	    	super(tool.material, getEffectedBlocks());
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

		/**
		* This create a list of vanilla blocks that the custom
		* axe can be used on.
		* @return Set<Block>
		*/
		private static Set<Block> getEffectedBlocks() {
			
			if(blocks == null) {
				blocks = new HashSet<Block>();
				// Acacia
				blocks.add(Blocks.ACACIA_DOOR);
				blocks.add(Blocks.ACACIA_FENCE);
				blocks.add(Blocks.ACACIA_FENCE_GATE);
				blocks.add(Blocks.ACACIA_STAIRS);
				// Birch
				blocks.add(Blocks.BIRCH_DOOR);
				blocks.add(Blocks.BIRCH_FENCE);
				blocks.add(Blocks.BIRCH_FENCE_GATE);
				blocks.add(Blocks.BIRCH_STAIRS);
				// Dark Oak
				blocks.add(Blocks.DARK_OAK_DOOR);
				blocks.add(Blocks.DARK_OAK_FENCE);
				blocks.add(Blocks.DARK_OAK_FENCE_GATE);
				blocks.add(Blocks.DARK_OAK_STAIRS);
				// Jungle
				blocks.add(Blocks.JUNGLE_DOOR);
				blocks.add(Blocks.JUNGLE_FENCE);
				blocks.add(Blocks.JUNGLE_FENCE_GATE);
				blocks.add(Blocks.JUNGLE_STAIRS);
				// Oak
				blocks.add(Blocks.OAK_DOOR);
				blocks.add(Blocks.OAK_FENCE);
				blocks.add(Blocks.OAK_FENCE_GATE);
				blocks.add(Blocks.OAK_STAIRS);
				// Spruce
				blocks.add(Blocks.SPRUCE_DOOR);
				blocks.add(Blocks.SPRUCE_FENCE);
				blocks.add(Blocks.SPRUCE_FENCE_GATE);
				blocks.add(Blocks.SPRUCE_STAIRS);
				// Logs
				blocks.add(Blocks.LOG);
				blocks.add(Blocks.LOG2);
				// Leaves
				blocks.add(Blocks.LEAVES);
				blocks.add(Blocks.LEAVES2);
				// Planks
				blocks.add(Blocks.PLANKS);
				// Crafting Table
				blocks.add(Blocks.CRAFTING_TABLE);
				// Pumpkin
				blocks.add(Blocks.PUMPKIN);
				// Lit Pumpkin
				blocks.add(Blocks.LIT_PUMPKIN);
				// Vines
				blocks.add(Blocks.VINE);
				// Melon
				blocks.add(Blocks.MELON_BLOCK);
			}
			return blocks;
		}

		/**
		* This check if the block can be mined by the custom axe
		* @param ItemStack stack
		* @param IBlockState state
		* @return
		*/
		private boolean checkStrVsBlock(ItemStack stack, IBlockState state)
		{
			boolean b = false;
			
			// Check Block List that the axe can mine...
			Iterator<Block> it = blocks.iterator();
			
			while(it.hasNext()) {
				Block block = it.next();
				
				if(block == state.getBlock()) {
					b = true;
					break;
				}
			}
			
			
			// Check Materials
			Material material = state.getMaterial();
			
			// Added in harvest tool and harvest level
			return b ||
				   material == Material.WOOD ||
				   material == Material.PLANTS ||
				   material == Material.VINE ||
				   (((state.getBlock().getHarvestTool(state) != null && state.getBlock().getHarvestTool(state).equals("axe"))? true : false) && state.getBlock().getHarvestLevel(state) <= this.toolMaterial.getHarvestLevel());
		}
		
		
		@Override
		public float getStrVsBlock(ItemStack stack, IBlockState state)
		{
			return (!checkStrVsBlock(stack, state))? super.getStrVsBlock(stack, state) : this.toolMaterial.getEfficiencyOnProperMaterial();
		}
}
