package pneumaticCrap.proxy;


import pneumaticCrap.client.gui.GuiAdvancedGasLift;
import pneumaticCrap.common.inventory.ContainerAdvancedGasLift;
import pneumaticCrap.common.tileentity.TileEntityAdvancedGasLift;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler{

    public int SPECIAL_RENDER_TYPE_VALUE;
    
	public World getClientWorld(){
        return null;
    }

    public EntityPlayer getPlayer(){
        return null;
    }
    
    public Side getSide(){
        return FMLCommonHandler.instance().getEffectiveSide();
    }
    
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        switch(ID){
	        case 0:
	        	return new ContainerAdvancedGasLift(player.inventory, (TileEntityAdvancedGasLift)world.getTileEntity(x, y, z)); 
        }
        return null;
    }
    
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
    	switch(ID){
	        case 0:
	        	return new GuiAdvancedGasLift(player.inventory, (TileEntityAdvancedGasLift)world.getTileEntity(x, y, z));
        }
        return null;
    }
    
    public void init(){}

	public void registerRenders() {
		// TODO Auto-generated method stub
		
	}

	public void registerHandlers() {
		// TODO Auto-generated method stub
		
	}
    
}
