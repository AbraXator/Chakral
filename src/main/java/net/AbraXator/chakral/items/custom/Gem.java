package net.AbraXator.chakral.items.custom;

import net.AbraXator.chakral.chakra.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class Gem extends Item {
    public ChakraType chakraType;
    public ChakraStrength chakraStrength;

    public Gem(Properties pProperties, Chakra chakra) {
        super(pProperties);
        this.chakraType = chakra.type;
        this.chakraStrength = chakra.strenght;
    }

    public Gem(Properties pProperties) {
        super(pProperties);
    }

    public Chakra setChakra() {
        for (Map.Entry<ResourceKey<Chakra>, Chakra> resourceKeyChakraEntry : ChakraRegistries.CHAKRA_REGISTRY.get().getEntries()) {
            if (this.asItem() == resourceKeyChakraEntry.getValue().getItem()) {
                continue;
            }
            return resourceKeyChakraEntry.getValue();
        }
        return null;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        Chakra chakra = setChakra();
        if (chakra != null) {
            ChakraType type = chakra.getType();
            ChakraStrength strength = chakra.getStrenght();
            pTooltipComponents.add(Component.literal("Type: " + type).withStyle(type.getColorFromChakra(type)));
            pTooltipComponents.add(Component.literal("Strength: " + strength).withStyle(ChakraStrength.getColorFromChakra(strength)));
            Component moreInfo = Component.literal("<Hold shift for more info>").withStyle(ChatFormatting.GOLD);
            pTooltipComponents.add(moreInfo);
            if (Screen.hasShiftDown()) {
                Component gemProperties = Component.translatable("tooltip.chakral." + pStack.getDescriptionId().replaceAll("item.chakral.", "")).withStyle(type.getColorFromChakra(type));
                if (gemProperties == null) {
                    pTooltipComponents.remove(moreInfo);
                    pTooltipComponents.add(Component.literal("WIP").withStyle(ChatFormatting.GOLD));
                } else {
                    pTooltipComponents.remove(moreInfo);
                    pTooltipComponents.add(gemProperties);
                }
            }
        }else {
            pTooltipComponents.add(Component.literal("WIP").withStyle(ChatFormatting.GOLD));
        }
    }
}
