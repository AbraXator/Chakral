package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.blocks.entity.custom.MineralEnricherBlockEntity;
import net.AbraXator.chakral.screen.enricher.MineralEnricherMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FluidSyncS2CPacket {
    private final FluidStack fluidStack;
    private final BlockPos pos;

    public FluidSyncS2CPacket(FluidStack fluidStack, BlockPos pos) {
        this.fluidStack = fluidStack;
        this.pos = pos;
    }
    public FluidSyncS2CPacket(FriendlyByteBuf buf){
        this.fluidStack = buf.readFluidStack();
        this.pos = buf.readBlockPos();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeFluidStack(fluidStack);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof MineralEnricherBlockEntity blockEntity){
                blockEntity.setFluid(this.fluidStack);
                if(Minecraft.getInstance().player.containerMenu instanceof MineralEnricherMenu menu &&
                    menu.blockEntity.getBlockPos().equals(pos)){
                    menu.setFluid(this.fluidStack);
                }
            }
        });
        return true;
    }
}
