package net.AbraXator.chakral.server.blocks;

import net.AbraXator.chakral.server.blocks.entity.BaseLightrayBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SculkSensorPhase;
import net.minecraft.world.level.gameevent.GameEventListener;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public abstract class BaseLightrayBlock extends BaseEntityBlock {
    public static final EnumProperty<SculkSensorPhase> PHASE = BlockStateProperties.SCULK_SENSOR_PHASE;
    private final Supplier<BlockEntityType<?>> TYPE;

    protected BaseLightrayBlock(Properties pProperties, Supplier<BlockEntityType<?>> type) {
        super(pProperties);
        this.TYPE = type;
        this.registerDefaultState(this.getStateDefinition().any().setValue(PHASE, SculkSensorPhase.INACTIVE));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    private SculkSensorPhase getPhase(BlockState state){
        return state.getValue(PHASE);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getListener(ServerLevel pLevel, T pBlockEntity) {
        return pBlockEntity instanceof BaseLightrayBlockEntity ? ((BaseLightrayBlockEntity) pBlockEntity).getListener() : null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return !pLevel.isClientSide ? createTickerHelper(pBlockEntityType, TYPE.get(), (pLevel1, pPos, pState1, pBlockEntity) -> {
            tick(pState1, pLevel1, pPos, pLevel1.getRandom());
            if(TYPE.get().getBlockEntity(pLevel1, pPos) instanceof BaseLightrayBlockEntity entity) entity.getListener().tick(pLevel, pPos, pState, pBlockEntity);
        }) : null;
    }

    public void tick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom){
        if (getPhase(pState) != SculkSensorPhase.ACTIVE) {
            if (getPhase(pState) == SculkSensorPhase.COOLDOWN) {
                pLevel.setBlock(pPos, pState.setValue(PHASE, SculkSensorPhase.INACTIVE), 3);
            }

        } else {
            deactivate(pLevel, pPos, pState);
        }
    }

    public static void deactivate(Level level, BlockPos pos, BlockState state){
        level.setBlock(pos, state.setValue(PHASE, SculkSensorPhase.COOLDOWN), 3);
        level.scheduleTick(pos, state.getBlock(), 1);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(PHASE);
    }
}
