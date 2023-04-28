package net.AbraXator.chakral.chakra.chakralMastery.masteries;

import net.AbraXator.chakral.chakra.chakralMastery.ChakraMasteryTiers;
import net.AbraXator.chakral.chakra.chakralMastery.Mastery;
import net.AbraXator.chakral.init.ModItems;
import net.AbraXator.chakral.init.ModTags;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;

public class Novice extends Mastery {
    public Novice(ChakraMasteryTiers chakraMasteryTiers) {
        super(chakraMasteryTiers);
    }

    @Override
    public void unlockedBy(Player player) {
        hasStone(player, ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.FAINT), 2);
    }
}
