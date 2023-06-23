package net.AbraXator.chakral.world.surfacerule;

import net.AbraXator.chakral.init.ModBiomes;
import net.AbraXator.chakral.init.ModBlocks;
import net.AbraXator.chakral.world.noise.ModNoises;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

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
    private static final SurfaceRules.RuleSource MINERAL_RICH_DIRT = makeStateRule(ModBlocks.MINERAL_RICH_DIRT.get());
    private static final SurfaceRules.RuleSource BROWN = makeStateRule(ModBlocks.BROWNSTONE.get());

    private static final SurfaceRules.ConditionSource MINERAL_CAVERNS = SurfaceRules.isBiome(ModBiomes.MINRAL_CAVERNS);
    private static final SurfaceRules.ConditionSource AMETHYST_CAVERNS = SurfaceRules.isBiome(ModBiomes.AMETHYST_CAVERNS);

    private static final SurfaceRules.ConditionSource BLACK_MINERAL_NOISE = SurfaceRules.noiseCondition(ModNoises.COLORED_MINERAL_NOISE, -0.5D, 0);
    private static final SurfaceRules.ConditionSource COLORED_MINERAL_NOISE = SurfaceRules.noiseCondition(ModNoises.COLORED_MINERAL_NOISE, 0D, 0.5);
    private static final SurfaceRules.ConditionSource BUDDING_NOISE = SurfaceRules.noiseCondition(ModNoises.BUDDING_NOISE, 0.2D, 0.3D);


    public static SurfaceRules.RuleSource overworld() {
        return SurfaceRules.sequence(
            SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), makeStateRule(Blocks.BEDROCK)),
            SurfaceRules.ifTrue(AMETHYST_CAVERNS, SurfaceRules.sequence(
                            SurfaceRules.ifTrue(COLORED_MINERAL_NOISE, SurfaceRules.sequence(
                                    onCeilingOrFloorOrDownABit(AMETHYST)
                            )),
                            SurfaceRules.ifTrue(BLACK_MINERAL_NOISE, BLACK),
                            SurfaceRules.ifTrue(SurfaceRules.noiseCondition(ModNoises.MINERAL_RICH_DIRT_NOISE, 0.41D, 0.5D), BROWN),
                            SurfaceRules.ifTrue(SurfaceRules.noiseCondition(ModNoises.MINERAL_RICH_DIRT_NOISE, 0D, 0.4D), MINERAL_RICH_DIRT),
                            BROWN
                    )
            )
        );
    }

    private static SurfaceRules.RuleSource onCeilingOrFloorOrDownABit(SurfaceRules.RuleSource block){
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, block),
                SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, block),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, block),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, block)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block p_194811_) {
        return SurfaceRules.state(p_194811_.defaultBlockState());
    }
}