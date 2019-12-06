package net.darkhax.botanypots.addons.hwyla;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.darkhax.botanypots.BotanyPotHelper;
import net.darkhax.botanypots.block.tileentity.TileEntityBotanyPot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class BotanyPotComponentProvider implements IComponentProvider {
    
    private static final DecimalFormat format = new DecimalFormat("#.##");
    
    @Override
    public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {

        final TileEntity tile = accessor.getTileEntity();
                
        if (tile instanceof TileEntityBotanyPot) {
            
            final TileEntityBotanyPot pot = (TileEntityBotanyPot) tile;
            
            if (config.get(BotanyPotsHwylaPlugin.KEY_SHOW_SOIL)) {
                
                tooltip.add(new TranslationTextComponent("botanypots.tooltip.soil", BotanyPotHelper.getSoilName(pot.getSoil())));
            }
            
            if (config.get(BotanyPotsHwylaPlugin.KEY_SHOW_CROP)) {
                
                tooltip.add(new TranslationTextComponent("botanypots.tooltip.crop", BotanyPotHelper.getCropName(pot.getCrop())));
            }
            
            if (config.get(BotanyPotsHwylaPlugin.KEY_SHOW_PROGRESS)) {
                
                tooltip.add(new TranslationTextComponent("botanypots.tooltip.growth_progress", format.format(pot.getGrowthPercent() * 100f)));
            }
            
            if (config.get(BotanyPotsHwylaPlugin.KEY_SHOW_DEBUG)) {
                
                if (pot.getCrop() != null) {
                    
                    tooltip.add(new TranslationTextComponent("botanypots.tooltip.crop.id", pot.getCrop().getId().toString()).applyTextStyle(TextFormatting.LIGHT_PURPLE));
                    tooltip.add(new TranslationTextComponent("botanypots.tooltip.crop.ticks", pot.getCrop().getGrowthTicks()).applyTextStyle(TextFormatting.LIGHT_PURPLE));
                    tooltip.add(new TranslationTextComponent("botanypots.tooltip.crop.multiplier", pot.getCrop().getGrowthMultiplier()).applyTextStyle(TextFormatting.LIGHT_PURPLE));
                    tooltip.add(new TranslationTextComponent("botanypots.tooltip.crop.categories", Arrays.toString(pot.getCrop().getSoilCategories())).applyTextStyle(TextFormatting.LIGHT_PURPLE));
                }
                
                if (pot.getSoil() != null) {
                    
                    tooltip.add(new TranslationTextComponent("botanypots.tooltip.soil.id", pot.getSoil().getId().toString()).applyTextStyle(TextFormatting.AQUA));
                    tooltip.add(new TranslationTextComponent("botanypots.tooltip.soil.ticks", pot.getSoil().getTickRate()).applyTextStyle(TextFormatting.AQUA));
                    tooltip.add(new TranslationTextComponent("botanypots.tooltip.soil.categories", Arrays.toString(pot.getSoil().getCategories())).applyTextStyle(TextFormatting.AQUA));
                }
                
                tooltip.add(new TranslationTextComponent("botanypots.tooltip.pot.totalticks", pot.getTotalGrowthTicks()).applyTextStyle(TextFormatting.GREEN));
                tooltip.add(new TranslationTextComponent("botanypots.tooltip.pot.growticks", pot.getCurrentGrowthTicks()).applyTextStyle(TextFormatting.GREEN));
            }
        }
    }
}