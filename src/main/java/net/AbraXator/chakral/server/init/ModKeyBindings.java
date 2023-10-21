package net.AbraXator.chakral.server.init;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {
    public static final String KEY_CATEGORY_CHAKRAL = "key.category.chakral.chakral";
    public static final String KEY_STONE_FUNCTION = "key.chakral.stone_function";
    public static final String KEY_NECKLACE_EQUIP = "key.chakral.necklace_equip";

    public static final KeyMapping STONE_FUNCTION_KEY = new KeyMapping(KEY_STONE_FUNCTION, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_CHAKRAL);
    public static final KeyMapping NECKLACE_EQUIP_KEY = new KeyMapping(KEY_NECKLACE_EQUIP, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_J, KEY_CATEGORY_CHAKRAL);
}
