package pneumaticCrap.proxy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import pneumaticCraft.client.model.BaseModel;
import pneumaticCraft.client.model.IBaseModel;
import pneumaticCraft.client.render.tileentity.RenderModelBase;
import pneumaticCraft.common.block.BlockPneumaticCraft;
import pneumaticCrap.client.model.ModelAdvancedGasLift;
import pneumaticCrap.common.block.Blockss;
import pneumaticCrap.common.tileentity.TileEntityAdvancedGasLift;

public class ClientProxy extends CommonProxy{

	@Override
    public void registerRenders(){
		SPECIAL_RENDER_TYPE_VALUE = RenderingRegistry.getNextAvailableRenderId();
		registerBaseModelRenderer(Blockss.advancedGasLift, TileEntityAdvancedGasLift.class, new ModelAdvancedGasLift());	
	}
    
	public static void registerBaseModelRenderer(Block block, Class<? extends TileEntity> tileEntityClass, IBaseModel model){

        registerBaseModelRenderer(Item.getItemFromBlock(block), tileEntityClass, model);
    }
    
	private static void registerBaseModelRenderer(Item item, Class<? extends TileEntity> tileEntityClass, IBaseModel model){
        RenderModelBase renderer = new RenderModelBase(model);
        ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, renderer);
        MinecraftForgeClient.registerItemRenderer(item, renderer);
    }
	 @Override
    public void registerHandlers(){

	}
	 
   @Override
    public World getClientWorld(){
        return FMLClientHandler.instance().getClient().theWorld;
    }

    @Override
    public EntityPlayer getPlayer(){
        return FMLClientHandler.instance().getClient().thePlayer;
    }	 
	 
    
    
}
