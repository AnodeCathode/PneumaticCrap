package pneumaticCrap.common.block;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pneumaticCraft.PneumaticCraft;
import pneumaticCraft.common.block.BlockPneumaticCraftModeled;
import pneumaticCraft.common.item.Itemss;
import pneumaticCraft.common.thirdparty.ModInteractionUtils;
import pneumaticCraft.common.util.FluidUtils;



public class BlockAdvancedGasLift extends BlockPneumaticCraftModeled{

    protected BlockAdvancedGasLift(Material par2Material){
        super(par2Material);
        setBlockBounds(0, 0, 0, 1, 10 / 16F, 1);
    }

    @Override
    protected Class<? extends TileEntity> getTileEntityClass(){
        return pneumaticCrap.common.tileentity.TileEntityAdvancedGasLift.class;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
        if(player.isSneaking() || isRotatable() && player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem().getItem() == Itemss.manometer || ModInteractionUtils.getInstance().isModdedWrench(player.getCurrentEquippedItem().getItem()))) return false;
        else {
            if(!world.isRemote) {
                TileEntity te = world.getTileEntity(x, y, z);

                List<ItemStack> returnedItems = new ArrayList<ItemStack>();
                if(te != null && !FluidUtils.tryInsertingLiquid(te, player.getCurrentEquippedItem(), player.capabilities.isCreativeMode, returnedItems)) {
                    player.openGui(pneumaticCrap.pneumaticCrap.instance, 0, world, x, y, z);
                } else {
                    if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().stackSize <= 0) {
                        player.setCurrentItemOrArmor(0, null);
                    }
                    for(ItemStack returnedItem : returnedItems) {
                        returnedItem = returnedItem.copy();
                        if(player.getCurrentEquippedItem() == null) {
                            player.setCurrentItemOrArmor(0, returnedItem);
                        } else {
                            player.inventory.addItemStackToInventory(returnedItem);
                        }
                    }
                }
            }

            return true;
        }
    }
    
    @Override
    public int getRenderType(){
        return PneumaticCraft.proxy.SPECIAL_RENDER_TYPE_VALUE;
    }


}
