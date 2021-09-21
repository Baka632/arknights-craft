package com.baka632.arknightscraft.items.eggs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.List;
import net.minecraft.client.item.TooltipContext;

public class EurekaMedal extends Item {

    public EurekaMedal(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.arknightscraft.eureka_medal.tooltip").formatted(Formatting.GRAY,Formatting.ITALIC));
    }
}