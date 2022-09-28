package net.AbraXator.chakral.utils;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final String KEY_CATEGORY_chakral = "key.category.chakral.chakral";
    public static final String KEY_STONE_FUNCTION = "key.category.chakral.stone_function";

    public static final KeyMapping STONE_FUNCTION_KEY = new KeyMapping(KEY_STONE_FUNCTION, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_chakral);
}