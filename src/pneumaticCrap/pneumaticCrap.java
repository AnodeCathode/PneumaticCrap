package pneumaticCrap;



import pneumaticCrap.common.block.Blockss;
import pneumaticCrap.common.recipes.CraftingRegistrator;
import pneumaticCrap.common.tileentity.TileEntityRegistrator;
import pneumaticCrap.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = "PneumaticCrap", name = "PneumaticCrap", dependencies = "required-after:PneumaticCraft")
public class pneumaticCrap {
	
	@SidedProxy(clientSide = "pneumaticCrap.proxy.ClientProxy", serverSide = "pneumaticCrap.proxy.CommonProxy")
    public static CommonProxy proxy;
	
	@Instance("PneumaticCrap")
	public static pneumaticCrap instance;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event){
		 NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		 Blockss.init();
		 TileEntityRegistrator.init();
		 CraftingRegistrator.init();
		 proxy.registerRenders();
		 
		 
	}
	@EventHandler
	public void load(FMLInitializationEvent event){
		proxy.init();
	}

		 
	 

}
