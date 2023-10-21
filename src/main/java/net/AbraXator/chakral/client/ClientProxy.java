package net.AbraXator.chakral.client;

import net.AbraXator.chakral.client.event.ClientEvents;
import net.AbraXator.chakral.client.gui.chakralnexus.ChakralNexusScreen;
import net.AbraXator.chakral.client.gui.enricher.MineralEnricherScreen;
import net.AbraXator.chakral.client.gui.necklace.inserter.NecklaceInserterScreen;
import net.AbraXator.chakral.client.gui.necklace.slotter.NecklaceSlotterScreen;
import net.AbraXator.chakral.client.gui.refiner.ShardRefinerScreen;
import net.AbraXator.chakral.client.particle.HagstoneFragmentiumParticle;
import net.AbraXator.chakral.client.particle.LightRayParticle;
import net.AbraXator.chakral.client.particle.StemshroomSporeParticle;
import net.AbraXator.chakral.client.renderer.MineralEnricherRenderer;
import net.AbraXator.chakral.client.renderer.entity.ChakralBoatRenderer;
import net.AbraXator.chakral.server.CommonProxy;
import net.AbraXator.chakral.server.chakra.ChakraType;
import net.AbraXator.chakral.server.chakra.NecklaceType;
import net.AbraXator.chakral.server.entity.dwider.DwiderRenderer;
import net.AbraXator.chakral.server.entity.stemspore.MenacingStemshroomSporeRenderer;
import net.AbraXator.chakral.server.init.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

public class ClientProxy extends CommonProxy {
    @Override
    public void commonInit() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setupParticles);
        eventBus.addListener(this::registerKeybindings);
    }

    @Override
    public void clientInit() {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());

        EntityRenderers.register(ModEntities.DWIDER.get(), DwiderRenderer::new);
        EntityRenderers.register(ModEntities.MENACING_STEMSHROOM_SPORE.get(), MenacingStemshroomSporeRenderer::new);
        EntityRenderers.register(ModEntities.BOAT.get(), pContext -> new ChakralBoatRenderer(pContext, false));
        EntityRenderers.register(ModEntities.CHEST_BOAT.get(), pContext -> new ChakralBoatRenderer(pContext, true));

        BlockEntityRenderers.register(ModBlockEntities.MINERAL_ENRICHER_BLOCK_ENTITY.get(), MineralEnricherRenderer::new);

        MenuScreens.register(ModMenuTypes.NECKLACE_SLOTTER_MENU.get(), NecklaceSlotterScreen::new);
        MenuScreens.register(ModMenuTypes.NECKLACE_INSERTER_MENU.get(), NecklaceInserterScreen::new);
        MenuScreens.register(ModMenuTypes.SHARD_REFINER_MENU.get(), ShardRefinerScreen::new);
        MenuScreens.register(ModMenuTypes.MINERAL_ENRICHER_MENU.get(), MineralEnricherScreen::new);
        MenuScreens.register(ModMenuTypes.CHAKRAL_NEXUS_MENU.get(), ChakralNexusScreen::new);

        for (ChakraType chakraType : ChakraType.values()) {
            for (NecklaceType necklaceType : NecklaceType.values()) {
                ItemProperties.register(necklaceType.getItem().get(), new ResourceLocation(chakraType.getSerializedName()), (itemStack, p_234979_, p_234980_, p_234981_) -> {
                    ITag<Item> list = ForgeRegistries.ITEMS.tags().getTag(chakraType.getTagKey());
                    return list.contains(evaluateItem(itemStack, necklaceType.getNumber())) ? 1.0F : 0.0F;
                });
            }
        }

    }

    public void setupParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.HAGSTONE_FRAGMNETIUM.get(), HagstoneFragmentiumParticle.Provider::new);
        event.registerSpriteSet(ModParticles.STEMSHROOM_SPORE.get(), StemshroomSporeParticle.Provider::new);
        event.registerSpriteSet(ModParticles.LIGHT_RAY.get(), LightRayParticle.Provider::new);
    }

    public void registerKeybindings(RegisterKeyMappingsEvent event) {
        event.register(ModKeyBindings.STONE_FUNCTION_KEY);
        event.register(ModKeyBindings.NECKLACE_EQUIP_KEY);
    }

    public static boolean isKeyDown(int keyType) {
        return switch (keyType) {
            case -1 -> Minecraft.getInstance().options.keyLeft.isDown() || Minecraft.getInstance().options.keyRight.isDown() || Minecraft.getInstance().options.keyUp.isDown() || Minecraft.getInstance().options.keyDown.isDown() || Minecraft.getInstance().options.keyJump.isDown();
            case 0 -> Minecraft.getInstance().options.keyJump.isDown();
            case 1 -> Minecraft.getInstance().options.keySprint.isDown();
            case 2 -> ModKeyBindings.NECKLACE_EQUIP_KEY.isDown();
            case 3 -> ModKeyBindings.STONE_FUNCTION_KEY.isDown();
            case 4 -> Minecraft.getInstance().options.keyAttack.isDown();
            case 5 -> Minecraft.getInstance().options.keyShift.isDown();
            default -> false;
        };
    }

    private Item evaluateItem(ItemStack itemStack, int number){
        String s = "Stone" + number;
        return itemStack != null && itemStack.hasTag() && itemStack.getTag().get(s) != null ? ItemStack.of(itemStack.getTag().getCompound(s)).getItem() : ItemStack.EMPTY.getItem();
    }
}
