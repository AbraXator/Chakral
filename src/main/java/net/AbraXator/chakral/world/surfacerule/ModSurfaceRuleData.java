package net.AbraXator.chakral.world.surfacerule;

import net.AbraXator.chakral.init.ModBiomes;
import net.AbraXator.chakral.init.ModBlocks;
import net.AbraXator.chakral.world.noise.ModNoises;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRuleData {
    private static final SurfaceRules.RuleSource BLACK = makeStateRule(ModBlocks.BLACK_MINERAL.get());
    private static final SurfaceRules.RuleSource AMETHYST = makeStateRule(Blocks.AMETHYST_BLOCK);
    private static final SurfaceRules.RuleSource BUDDING_AMETHYST = makeStateRule(Blocks.BUDDING_AMETHYST);
    private static final SurfaceRules.RuleSource BLUE = makeStateRule(ModBlocks.BLUE_MINERAL.get());
    private static final SurfaceRules.RuleSource LIGHT_BLUE = makeStateRule(ModBlocks.LIGHT_BLUE_MINERAL.get());
    private static final SurfaceRules.RuleSource GREEN = makeStateRule(ModBlocks.GREEN_MINERAL.get());
    private static final SurfaceRules.RuleSource YELLOW = makeStateRule(ModBlocks.YELLOW_MINERAL.get());
    private static final SurfaceRules.RuleSource ORANGE = makeStateRule(ModBlocks.ORANGE_MINERAL.get());
    private static final SurfaceRules.RuleSource RED = makeStateRule(ModBlocks.RED_MINERAL.get());

    private static final SurfaceRules.ConditionSource MINERAL_CAVERNS = SurfaceRules.isBiome(ModBiomes.MINRAL_CAVERNS);
    private static final SurfaceRules.ConditionSource AMETHYST_CAVERNS = SurfaceRules.isBiome(ModBiomes.AMETHYST_CAVERNS);

    private static final SurfaceRules.ConditionSource COLORED_MINERAL_NOISE = SurfaceRules.noiseCondition(ModNoises.COLORED_MINERAL_NOISE, 0D, 1);
    private static final SurfaceRules.ConditionSource BUDDING_NOISE = SurfaceRules.noiseCondition(ModNoises.BUDDING_NOISE, 0.2D, 0.3D);


    public static SurfaceRules.RuleSource overworld() {
        return SurfaceRules.sequence(
            SurfaceRules.ifTrue(AMETHYST_CAVERNS,
                    SurfaceRules.sequence(
                        SurfaceRules.ifTrue(COLORED_MINERAL_NOISE, AMETHYST),
                        BLACK
                    )
            )
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block p_194811_) {
        return SurfaceRules.state(p_194811_.defaultBlockState());
    }
}