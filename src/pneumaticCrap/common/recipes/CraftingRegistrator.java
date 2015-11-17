package pneumaticCrap.common.recipes;

import pneumaticCraft.common.block.Blockss;
import pneumaticCraft.lib.Names;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingRegistrator {

	public static void init(){
		addRecipe(new ItemStack(pneumaticCrap.common.block.Blockss.advancedGasLift), " t ", "tgt", "iii", 'i', Names.BLOCK_IRON_COMPRESSED, 'g', "blockGlass", 't', Blockss.advancedPressureTube);	
	}
	
	
    private static void addRecipe(ItemStack result, Object... recipe){
        ShapedOreRecipe newRecipe = new ShapedOreRecipe(result, recipe);
        GameRegistry.addRecipe(newRecipe);
    }
}
