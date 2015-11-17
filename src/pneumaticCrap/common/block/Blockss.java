package pneumaticCrap.common.block;


import pneumaticCraft.common.itemBlock.ItemBlockPneumaticCraft;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Blockss {
    public static Block advancedGasLift;
    
    public static void init(){
        advancedGasLift = new BlockAdvancedGasLift(Material.iron).setBlockName("advGasLift");
        
        registerBlocks();
        
    }

	private static void registerBlocks() {
        registerBlock(advancedGasLift);
		
	}
    public static void registerBlock(Block block){
        registerBlock(block, ItemBlockPneumaticCraft.class);
    }

    private static void registerBlock(Block block, Class<? extends ItemBlockPneumaticCraft> itemBlockClass){
    	GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().substring("tile.".length()));
    }
}
