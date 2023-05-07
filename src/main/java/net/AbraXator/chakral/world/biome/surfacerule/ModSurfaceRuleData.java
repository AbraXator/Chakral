package net.AbraXator.chakral.world.biome.surfacerule;

import net.AbraXator.chakral.init.ModBiomes;
import net.AbraXator.chakral.init.ModBlocks;
import net.AbraXator.chakral.world.noise.ModNoises;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRuleData {
    private static final SurfaceRules.RuleSource AMETHYST = makeStateRule(Blocks.AMETHYST_BLOCK);
    private static final SurfaceRules.RuleSource BLUE = makeStateRule(ModBlocks.BLUE_MINERAL.get());
    private static final SurfaceRules.RuleSource LIGHT_BLUE = makeStateRule(ModBlocks.LIGHT_BLUE_MINERAL.get());
    private static final SurfaceRules.RuleSource GREEN = makeStateRule(ModBlocks.GREEN_MINERAL.get());
    private static final SurfaceRules.RuleSource YELLOW = makeStateRule(ModBlocks.YELLOW_MINERAL.get());
    private static final SurfaceRules.RuleSource ORANGE = makeStateRule(ModBlocks.ORANGE_MINERAL.get());
    private static final SurfaceRules.RuleSource RED = makeStateRule(ModBlocks.RED_MINERAL.get());

    public static SurfaceRules.RuleSource overworld() {
        return SurfaceRules.sequence(
            SurfaceRules.ifTrue(SurfaceRules.isBiome(
                    ModBiomes.MINRAL_CAVERNS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(ModNoises.PURPLE_NOISE, -1, 0),
                                        AMETHYST
                                ),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(ModNoises.BLUE_NOISE, -1, 0),
                                        BLUE
                                ),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(ModNoises.LIGHT_BLUE_NOISE, -1, 0),
                                        LIGHT_BLUE
                                ),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(ModNoises.GREEN_NOISE, -1, 0),
                                        GREEN
                                ),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(ModNoises.YELLOW_NOISE, -1, 0),
                                        YELLOW
                                ),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(ModNoises.ORANGE_NOISE, -1, 0),
                                        ORANGE
                                ),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(ModNoises.RED_NOISE, -1, 0),
                                        RED
                                )
                        )
            )
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block p_194811_) {
        return SurfaceRules.state(p_194811_.defaultBlockState());
    }
}