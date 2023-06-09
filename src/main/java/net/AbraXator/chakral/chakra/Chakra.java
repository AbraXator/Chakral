package net.AbraXator.chakral.chakra;

import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Chakra implements IChakra {
    private ResourceLocation id;
    private boolean isEnabled;
    private RegistryObject<Item> stone;
    private final ChakraType type;
    private final ChakraStrength strenght;
    private ItemStack necklace;
    private int cooldown;
    private int maxCooldown;
    public List<Style> style;
    public boolean showSideTip = false;
    public final RandomSource random = RandomSource.create();

    public Chakra(ChakraType type, ChakraStrength chakraStrength){
        this.type = type;
        this.strenght = chakraStrength;
    }

    public AbstractWidget openInfoSidePanel(int x, int y) {
        return null;
    }

    public ResourceLocation getId() {
        return ChakraUtil.getIdForChakra(this);
    }

    public Item getItem(){
        return RegistryObject.create(new ChakralLocation(getId().getPath() + "_chakra"), ForgeRegistries.ITEMS).get();
    }

    public ChakraType getType(){
        return type;
    }

    public ChakraStrength getStrenght() {
        return strenght;
    }

    public ItemStack getNecklace(){
        return necklace;
    }

    public void setNecklace(ItemStack stack){
        this.necklace = stack;
    }

    @NotNull
    public List<Item> stones(){
        if (necklace != null && necklace.hasTag()) {
            CompoundTag tag = necklace.getTag();
            Item stone1 = tag.get("Stone1") != null || ItemStack.of(tag.getCompound("Stone1")).is(Items.AIR)
                    ? ItemStack.of(tag.getCompound("Stone1")).getItem() : Items.AIR;
            Item stone2 = tag.get("Stone2") != null || ItemStack.of(tag.getCompound("Stone2")).is(Items.AIR)
                    ? ItemStack.of(tag.getCompound("Stone2")).getItem() : Items.AIR;
            Item stone3 = tag.get("Stone3") != null || ItemStack.of(tag.getCompound("Stone3")).is(Items.AIR)
                    ? ItemStack.of(tag.getCompound("Stone3")).getItem() : Items.AIR;
            Item stone4 = tag.get("Stone4") != null || ItemStack.of(tag.getCompound("Stone4")).is(Items.AIR)
                    ? ItemStack.of(tag.getCompound("Stone4")).getItem() : Items.AIR;
            List<Item> list = List.of(stone1, stone2, stone3, stone4);
            return list;
        }
        return List.of(Items.AIR);
    }

    public boolean isEnabled(){
        return this.isEnabled;
    }

    public void setEnabled(boolean enabled){
        this.isEnabled = enabled;
    }

    public void setMaxCooldown(int maxCooldown){
        this.maxCooldown = maxCooldown;
    }

    public void setCooldown(int cooldown){
        this.cooldown = cooldown;
    }

    public void onFunctionKeyPress(Player player, Level level){}

    public void onFunctionKeyPressUpgraded(Player player, Level level){}

    /*public boolean isUpgraded(){
        return stones().contains(this.type.getTier4(this.type));
    }*/

    public void tick(Player player, Level level){}

    public void onEquip(Player player, Level level){}

    public void onEquipUpgraded(Player player, Level level){}

    public void onUnequip(Player player, Level level){}

    public void onUnequipUpgraded(Player player, Level level){}

    public void onRightClickBlock(Player player, Level level, PlayerInteractEvent.RightClickBlock event){}

    public void onHurt(LivingHurtEvent event){}

    public void onDamage(LivingDamageEvent event){}

    public void onDestroyBlock(BlockEvent.BreakEvent event){}

    public void onAttack(AttackEntityEvent event){}

    public void leftClick(PlayerInteractEvent.LeftClickEmpty event){}

    public void interact(PlayerInteractEvent event){}


    public List<Style> chakraColor(String... hex){
        List<Style> list = new ArrayList<>();
        Arrays.stream(hex).forEach(s -> list.add(Style.EMPTY.withColor(TextColor.parseColor("#" + s))));
        this.style = list;
        return this.style;
    }
}
