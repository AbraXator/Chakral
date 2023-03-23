package net.AbraXator.chakral.networking.packet;

import net.AbraXator.chakral.blocks.entity.custom.MineralEnricherBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class EnricherSyncS2CPacket {
    private final ItemStackHandler itemStackHandler;
    private final BlockPos pos;
    private final int size;

    public EnricherSyncS2CPacket(ItemStackHandler itemStackHandler, BlockPos pos, int size) {
        this.itemStackHandler = itemStackHandler;
        this.pos = pos;
        this.size = size;
    }

    public EnricherSyncS2CPacket(FriendlyByteBuf buf){
        List<ItemStack> collection = buf.readCollection(ArrayList::new, FriendlyByteBuf::readItem);
        itemStackHandler = new ItemStackHandler(collection.size());
        for (int i = 0; i < collection.size(); i++) {
            itemStackHandler.insertItem(i, collection.get(i), false);
        }
        this.pos = buf.readBlockPos();
        this.size = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        Collection<ItemStack> list = new ArrayList<>();
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            list.add(itemStackHandler.getStackInSlot(i));
        }

        buf.writeCollection(list, FriendlyByteBuf::writeItem);
        buf.writeBlockPos(pos);
        buf.writeInt(size);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
           if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof MineralEnricherBlockEntity blockEntity){
               blockEntity.setHandler(this.itemStackHandler);
               blockEntity.data.set(0, size);
           }
        });

        return true;
    }
}
