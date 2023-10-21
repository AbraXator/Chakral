package net.AbraXator.chakral.data;

import net.AbraXator.chakral.Chakral;
import net.AbraXator.chakral.server.blocks.Crystal;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    final ExistingFileHelper existingFileHelper;
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
        this.existingFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        DataGenerators.getCrystals().forEach(block -> {
            this.getVariantBuilder(block)
                    .partialState()
                    .with(Crystal.FACING, Direction.DOWN)
                    .modelForState()
                    .modelFile(modelFile(block))
                    .rotationX(180)
                    .addModel()
                    .partialState()
                    .with(Crystal.FACING, Direction.EAST)
                    .modelForState()
                    .modelFile(modelFile(block))
                    .rotationX(90)
                    .rotationY(90)
                    .addModel()
                    .partialState()
                    .with(Crystal.FACING, Direction.NORTH)
                    .modelForState()
                    .modelFile(modelFile(block))
                    .rotationX(90)
                    .addModel()
                    .partialState()
                    .with(Crystal.FACING, Direction.SOUTH)
                    .modelForState()
                    .modelFile(modelFile(block))
                    .rotationX(90)
                    .rotationY(180)
                    .addModel()
                    .partialState()
                    .with(Crystal.FACING, Direction.UP)
                    .modelForState()
                    .modelFile(modelFile(block))
                    .addModel()
                    .partialState()
                    .with(Crystal.FACING, Direction.WEST)
                    .modelForState()
                    .modelFile(modelFile(block))
                    .rotationX(90)
                    .rotationY(270)
                    .addModel();
        });
    }

    private ModelFile.ExistingModelFile modelFile(Block block){
        return new ModelFile.ExistingModelFile(new ResourceLocation(Chakral.MOD_ID,
                "block/" + DataGenerators.trimmedId(block.getDescriptionId())), existingFileHelper);
    }
}
