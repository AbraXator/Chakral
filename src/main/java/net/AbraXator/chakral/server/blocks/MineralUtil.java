package net.AbraXator.chakral.server.blocks;

import net.minecraft.util.StringRepresentable;

public class MineralUtil {
    public enum MineralType implements StringRepresentable {
        BLOCK("mineral"),
        CRYSTAL("crystal"),
        BUDDING("budding");

        public final String name;

        MineralType(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }

    public enum MineralColor implements StringRepresentable {
        PURPLE("purple"),
        BLUE("blue"),
        LIGHT_BLUE("light_blue"),
        GREEN("green"),
        YELLOW("yellow"),
        ORANGE("orange"),
        RED("red");

        public final String name;

        MineralColor(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
