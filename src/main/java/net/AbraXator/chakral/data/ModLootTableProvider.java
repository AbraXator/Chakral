package net.AbraXator.chakral.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ModLootTableProvider extends LootTableProvider{
   //private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> loot_tables = ImmutableList.of(Pair.of(LootTableGen::new,
   //        LootContextParamSets.BLOCK));

    public ModLootTableProvider(PackOutput output) {
        super(output, Set.of(), subProviderEntries());
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationcontext) {
        map.forEach((resourceLocation, lootTable) -> LootTables.validate(validationcontext, resourceLocation, lootTable));
    }

    static List<SubProviderEntry> subProviderEntries(){
        return List.of(new SubProviderEntry(BlockLoottableProvider::new, LootContextParamSets.BLOCK));
    }
}
