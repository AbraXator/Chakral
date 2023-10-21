package net.AbraXator.chakral.server.chakra.chakralMastery;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum ChakraMasteryTiers implements StringRepresentable {
    OBLIVIOUS(0, "oblivious"),
    NOVICE(1, "novice"),
    ADEPT(2, "adept"),
    EXPERT(3, "expert"),
    MASTER(4, "master"),
    DIVINE(5, "divine");

    final int index;
    final String name;

    ChakraMasteryTiers(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name;
    }
}
