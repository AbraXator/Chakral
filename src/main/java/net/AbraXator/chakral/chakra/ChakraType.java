package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.common.Mod;
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

    public Item getTier4(ChakraType type){
        switch (type){
            case CROWN: return ModItems.LABBRADORITE.get();
            case THIRD_EYE: return ModItems.AZURITE.get();
            case THROAT: return ModItems.AQUAMARINE.get();
            case HEART: return ModItems.GREEN_OPAL.get();
            case SOLAR: return ModItems.FIRE_OPAL.get();
            case SACRAL: return ModItems.GOLDEN_TIGERS_EYE.get();
            case ROOT: return ModItems.TOURMALINE.get();
            default: return Items.AIR;
        }
    }

    public ChakraType getChakraFromString(String string){
        if(string.matches("crown")){
            return CROWN;
        }
        if(string.matches("third_eye")){
            return THIRD_EYE;
        }
        if(string.matches("throat")){
            return THROAT;
        }
        if(string.matches("heart")){
            return HEART;
        }
        if(string.matches("solar")){
            return SOLAR;
        }
        if(string.matches("sacral")){
            return SACRAL;
        }
        if(string.matches("root")){
            return ROOT;
        }
        return null;
    }

    public String ChakraToString(ChakraType chakraType){
        switch (chakraType){
            case CROWN: return "CROWN";
            case THIRD_EYE: return "THIRD_EYE";
            case THROAT: return "THROAT";
            case HEART: return "HEART";
            case SOLAR: return "SOLAR";
            case SACRAL: return "SACRAL";
            case ROOT: return "ROOT";
            default: return "";
        }
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
