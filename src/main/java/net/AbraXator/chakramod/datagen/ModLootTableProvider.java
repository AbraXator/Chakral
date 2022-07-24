package net.AbraXator.chakramod.datagen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.AbraXator.chakramod.datagen.loot.ModBlockLootTables;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModLootTableProvider extends LootTableProvider {
    //private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable>>>, LootContextParamSet>>
    //    loot_tables = ImmutableList.of(Pair.of(ModBlockLootTables::new, lootContextSets.BLOCK));

    public ModLootTableProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }
}
