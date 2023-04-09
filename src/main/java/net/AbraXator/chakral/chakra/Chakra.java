package net.AbraXator.chakral.chakra;

import com.mojang.blaze3d.vertex.PoseStack;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.AbraXator.chakral.items.ModItems;
import net.AbraXator.chakral.utils.ChakralLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Chakra implements IChakraSidePanel{
    private final ResourceLocation id;
    private boolean isEnabled;
    private final RegistryObject<Item> stone;
    private final ChakraType type;
    private final ChakraStrength strenght;
    private ItemStack necklace;
    private int cooldown;
    private int maxCooldown;

    public Chakra(ResourceLocation id, ChakraType type, ChakraStrength chakraStrength){
        this.id = id;
        this.stone = RegistryObject.create(new ChakralLocation(id.getPath() + "_chakra"), ForgeRegistries.ITEMS);
        this.type = type;
        this.strenght = chakraStrength;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public Item getItem(){
        return stone.get();
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

    public boolean isUpgraded(){
        return stones().contains(this.type.getTier4(this.type));
    }

    public void tick(Player player, Level level){}

    public void onEquip(Player player, Level level){}

    public void onEquipUpgraded(Player player, Level level){}

    public void onUnequip(Player player, Level level){}

    public void onUnequipUpgraded(Player player, Level level){}

    public void onRightClickBlock(Player player, Level level, PlayerInteractEvent.RightClickBlock event){}

    public void onHurt(LivingHurtEvent event){}

    public void onDestroyBlock(BlockEvent.BreakEvent event){};

    @Override
    public void openInfoSidePanel(ChakralNexusScreen screen, PoseStack poseStack, int x, int y) {}
}
