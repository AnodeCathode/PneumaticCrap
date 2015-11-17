package pneumaticCrap.common.tileentity;


import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityRegistrator {
    public static void init(){
        GameRegistry.registerTileEntity(TileEntityAdvancedGasLift.class, "pneumaticCrap_advGasLift");
    }

}
