package pneumaticCrap.client.gui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import pneumaticCrap.common.block.Blockss;
import pneumaticCrap.common.inventory.ContainerAdvancedGasLift;
import pneumaticCrap.common.tileentity.TileEntityAdvancedGasLift;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import pneumaticCraft.client.gui.GuiButtonSpecial;
import pneumaticCraft.client.gui.GuiPneumaticContainerBase;
import pneumaticCraft.client.gui.widget.GuiAnimatedStat;
import pneumaticCraft.client.gui.widget.WidgetTank;
import pneumaticCraft.lib.Textures;


public class GuiAdvancedGasLift extends GuiPneumaticContainerBase<TileEntityAdvancedGasLift>{
    private GuiAnimatedStat statusStat;
    private final GuiButtonSpecial[] modeButtons = new GuiButtonSpecial[3];

    public GuiAdvancedGasLift(InventoryPlayer player, pneumaticCrap.common.tileentity.TileEntityAdvancedGasLift te){
        super(new ContainerAdvancedGasLift(player, te), te, Textures.GUI_GAS_LIFT);
    }

    @Override
    public void initGui(){
        super.initGui();
        addWidget(new WidgetTank(-1, guiLeft + 80, guiTop + 15, te.getTank()));
        statusStat = addAnimatedStat("gui.tab.status", new ItemStack(Blockss.advancedGasLift), 0xFFFFAA00, false);

        GuiAnimatedStat optionStat = addAnimatedStat("gui.tab.gasLift.mode", new ItemStack(pneumaticCraft.common.block.Blockss.advancedPressureTube), 0xFFFFCC00, false);
        List<String> text = new ArrayList<String>();
        for(int i = 0; i < 4; i++)
            text.add("                  ");
        optionStat.setTextWithoutCuttingString(text);

        GuiButtonSpecial button = new GuiButtonSpecial(1, 5, 20, 20, 20, "");
        button.setRenderStacks(new ItemStack(Items.bucket));
        button.setTooltipText(I18n.format("gui.tab.gasLift.mode.pumpEmpty"));
        optionStat.addWidget(button);
        modeButtons[0] = button;

        button = new GuiButtonSpecial(2, 30, 20, 20, 20, "");
        button.setRenderStacks(new ItemStack(Items.water_bucket));
        button.setTooltipText(I18n.format("gui.tab.gasLift.mode.pumpLeave"));
        optionStat.addWidget(button);
        modeButtons[1] = button;

        button = new GuiButtonSpecial(3, 55, 20, 20, 20, "");
        button.setRenderStacks(new ItemStack(pneumaticCraft.common.block.Blockss.advancedPressureTube));
        button.setTooltipText(I18n.format("gui.tab.gasLift.mode.drawIn"));
        optionStat.addWidget(button);
        modeButtons[2] = button;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y){

        super.drawGuiContainerForegroundLayer(x, y);
        fontRendererObj.drawString("Upgr.", 17, 19, 4210752);
    }

    @Override
    protected Point getInvNameOffset(){
        return new Point(0, -1);
    }

    @Override
    public void updateScreen(){
        super.updateScreen();
        statusStat.setText(getStatus());
        for(int i = 0; i < modeButtons.length; i++) {
            modeButtons[i].enabled = te.mode != i;
        }
    }

    private List<String> getStatus(){
        List<String> textList = new ArrayList<String>();
        textList.add(I18n.format("gui.tab.status.gasLift.action"));
        String status = "gui.tab.status.gasLift.action.";
        switch(te.status){
            case 0:
                status += "idling";
                break;
            case 1:
                status += "pumping";
                break;
            case 2:
                status += "diggingDown";
                break;
            case 3:
                status += "retracting";
        }
        textList.add(I18n.format(status, te.getTank().getFluid() != null ? te.getTank().getFluid().getLocalizedName() : ""));
        textList.add(I18n.format("gui.tab.status.gasLift.currentDepth", te.currentDepth));
        return textList;
    }

    @Override
    public void addProblems(List<String> curInfo){
        super.addProblems(curInfo);
        if(te.mode == 0 || te.mode == 1) {
            if(te.getTank().getCapacity() - te.getTank().getFluidAmount() < 1000) {
                curInfo.add(I18n.format("gui.tab.problems.gasLift.noLiquidSpace"));
            }
            if(te.getStackInSlot(4) == null) {
                curInfo.add(I18n.format("gui.tab.problems.gasLift.noTubes"));
            }
        } else {
            if(te.getStackInSlot(4) != null && te.getStackInSlot(4).stackSize == 64) {
                curInfo.add(I18n.format("gui.tab.problems.gasLift.noTubeSpace"));
            }
        }
    }
}
