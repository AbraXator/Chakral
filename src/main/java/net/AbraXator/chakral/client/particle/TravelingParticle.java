package net.AbraXator.chakral.client.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.PositionSourceType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

public class TravelingParticle implements ParticleOptions {
    public static final Deserializer<TravelingParticle> DESERIALIZER = new Deserializer<>() {
        @Override
        public TravelingParticle fromCommand(ParticleType<TravelingParticle> pParticleType, StringReader pReader) throws CommandSyntaxException {
            pReader.expect(' ');
            float f = ((float) pReader.readDouble());
            pReader.expect(' ');
            float f1 = ((float) pReader.readDouble());
            pReader.expect(' ');
            float f2 = ((float) pReader.readDouble());
            pReader.expect(' ');
            int i = pReader.readInt();
            BlockPos blockPos = BlockPos.containing(f, f1, f2);
            return new TravelingParticle(pParticleType, new BlockPositionSource(blockPos), i);
        }

        @Override
        public TravelingParticle fromNetwork(ParticleType<TravelingParticle> pParticleType, FriendlyByteBuf pBuffer) {
            PositionSource positionSource = PositionSourceType.fromNetwork(pBuffer);
            int i = pBuffer.readVarInt();
            return new TravelingParticle(pParticleType , positionSource, i);
        }
    };

    public final ParticleType<?> type;
    public final PositionSource destination;
    public final int arrivalInTicks;

    public TravelingParticle(ParticleType<?> type, PositionSource destination, int arrivalInTicks) {
        this.type = type;
        this.destination = destination;
        this.arrivalInTicks = arrivalInTicks;
    }

    @Override
    public ParticleType<?> getType() {
        return type;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        PositionSourceType.toNetwork(this.destination, pBuffer);
        pBuffer.writeVarInt(this.arrivalInTicks);
    }

    @Override
    public String writeToString() {
        Vec3 vec3 = this.destination.getPosition(null).get();
        double d0 = vec3.x();
        double d1 = vec3.y();
        double d2 = vec3.z();
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %d", ForgeRegistries.PARTICLE_TYPES.getKey(this.getType()), d0, d1, d2, this.arrivalInTicks);
    }
}
