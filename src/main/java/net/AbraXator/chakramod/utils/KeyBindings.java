package net.AbraXator.chakramod.utils;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.searchtree.SearchRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final String KEY_CATEGORY_CHAKRAMOD = "key.category.chakramod.chakramod";
    public static final String KEY_STONE_FUNCTION = "key.category.chakramod.stone_function";

    public static final KeyMapping STONE_FUNCTION_KEY = new KeyMapping(KEY_STONE_FUNCTION, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_CHAKRAMOD);
}
