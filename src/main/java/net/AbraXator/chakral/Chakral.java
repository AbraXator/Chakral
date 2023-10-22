package net.AbraXator.chakral;

import com.mojang.logging.LogUtils;
import net.AbraXator.chakral.client.ClientProxy;
import net.AbraXator.chakral.server.CommonProxy;
import net.AbraXator.chakral.server.config.ModServerConfig;
import net.AbraXator.chakral.server.init.*;
import net.AbraXator.chakral.server.networking.ModPacketHandler;
import net.AbraXator.chakral.server.recipes.ModRecipes;
import net.AbraXator.chakral.server.world.biome.ModBiomeGeneration;
import net.AbraXator.chakral.server.world.biome.ModBiomes;
import net.AbraXator.chakral.server.world.surfacerule.ModSurfaceRuleData;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;

@Mod("chakral")
public class Chakral {
    public static final String MOD_ID = "chakral";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    private static final Pair<ModServerConfig, ForgeConfigSpec> serverPair = new ForgeConfigSpec.Builder().configure(ModServerConfig::new);
    public static final ModServerConfig COMMON_CONFIG = serverPair.getLeft();
    private static final ForgeConfigSpec COMMON_CONFIG_SPEC = serverPair.getRight();

    public Chakral() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG_SPEC, "chakral-general.toml");
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::loadConfigs);
        eventBus.addListener(this::reloadConfigs);

        ModItems.           register(eventBus);
        ModBlocks.          register(eventBus);
        ModBlockEntities.   register(eventBus);
        ModMenuTypes.       register(eventBus);
        ModRecipes.         register(eventBus);
        ModEntities.        register(eventBus);
        ModParticles.       register(eventBus);
        ModChakras.         register(eventBus);
        ModSoundEvents.     register(eventBus);
        ModFeatures.        register(eventBus);
        ModGameEvents.      register(eventBus);
        ModCreativeTabs.    register(eventBus);

        ModBiomes.          init();

        PROXY.commonInit();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void loadConfigs(final ModConfigEvent.Loading event) {
        ModBiomeGeneration.reloadConfig();
    }

    private void reloadConfigs(final ModConfigEvent.Reloading event) {
        ModBiomeGeneration.reloadConfig();
    }

    public void clientSetup(final FMLClientSetupEvent event){
        event.enqueueWork(PROXY::clientInit);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModPacketHandler.register();
            ModSurfaceRuleData.setup();
            VanillaCompat.register();
        });
    }

    public static ResourceLocation loc(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static <T> ResourceKey<T> createKey(ResourceKey<Registry<T>> registry, String key){
        return ResourceKey.create(registry, loc(key));
    }
}