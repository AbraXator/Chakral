package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.init.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

//TODO: change some chakra names
public enum ChakraType implements StringRepresentable {
    CROWN("crown", ModTags.Items.CROWN, 1, Component.translatable("chakra_type.crown")),
    THIRD_EYE("third_eye", ModTags.Items.THIRD_EYE, 2, Component.translatable("chakra_type.third_eye")),
    THROAT("throat", ModTags.Items.THROAT, 3, Component.translatable("chakra_type.throat")),
    HEART("heart", ModTags.Items.HEART, 4, Component.translatable("chakra_type.heart")),
    SOLAR("solar", ModTags.Items.SOLAR, 5, Component.translatable("chakra_type.solar")),
    SACRAL("sacral", ModTags.Items.SACRAL, 6, Component.translatable("chakra_type.sacral")),
    ROOT("root", ModTags.Items.ROOT, 7, Component.translatable("chakra_type.root"));

    ChakraType(String name, TagKey<Item> tagKey, int index, Component localizedName){
        this.name = name;
        this.tagKey = tagKey;
        this.index = index;
        this.localizedName = localizedName;
    }

    private String name;
    private TagKey<Item> tagKey;
    private int index;
    private Component localizedName;

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }

    public TagKey<Item> getTagKey() {
        return this.tagKey;
    }

    public int getIndex(){
        return index;
    }

    public Component getLocalizedName(){
        return localizedName;
    }

    public ChatFormatting getColorFromChakra(ChakraType chakraType){
        switch (chakraType){
            case CROWN: return ChatFormatting.DARK_PURPLE;
            case THIRD_EYE: return ChatFormatting.BLUE;
            case THROAT: return ChatFormatting.AQUA;
            case HEART: return ChatFormatting.GREEN;
            case SOLAR: return ChatFormatting.YELLOW;
            case SACRAL: return ChatFormatting.GOLD;
            case ROOT: return ChatFormatting.RED;
            default: return ChatFormatting.DARK_GRAY;
        }
    }
}
