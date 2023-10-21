package net.AbraXator.chakral.server.chakra.chakralMastery.masteries;

import net.AbraXator.chakral.server.chakra.chakralMastery.ChakraMasteryTiers;
import net.AbraXator.chakral.server.chakra.chakralMastery.Mastery;
import net.AbraXator.chakral.server.init.ModTags;
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
