package net.AbraXator.chakral.client.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Vector3f;

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
            Vec3 pos = new Vec3(f, f1, f2);
            return new TravelingParticle(pParticleType, pos, i);
        }

        @Override
        public TravelingParticle fromNetwork(ParticleType<TravelingParticle> pParticleType, FriendlyByteBuf pBuffer) {
            Vector3f pos = pBuffer.readVector3f();
            int i = pBuffer.readVarInt();
            return new TravelingParticle(pParticleType , new Vec3(pos.x, pos.y, pos.z), i);
        }
    };

    public final ParticleType<?> type;
    public final Vec3 destination;
    public final int arrivalInTicks;

    public TravelingParticle(ParticleType<?> type, Vec3 destination, int arrivalInTicks) {
        this.type = type;
        this.destination = destination;
        this.arrivalInTicks = arrivalInTicks;
    }

    public static Codec<TravelingParticle> codec(ParticleType<TravelingParticle> type) {
        return RecordCodecBuilder.create(instance -> {
            return instance.group(
                    Vec3.CODEC.fieldOf("destination").forGetter(o -> o.destination),
                    Codec.INT.fieldOf("arrival_in_ticks").forGetter(o -> o.arrivalInTicks)
            ).apply(instance, (positionSource, integer) -> new TravelingParticle(type, positionSource, integer));
        });
    }

    @Override
    public ParticleType<?> getType() {
        return type;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        pBuffer.writeVector3f(destination.toVector3f());
        pBuffer.writeVarInt(this.arrivalInTicks);
    }

    @Override
    public String writeToString() {
        double d0 = destination.x();
        double d1 = destination.y();
        double d2 = destination.z();
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %d", ForgeRegistries.PARTICLE_TYPES.getKey(this.getType()), d0, d1, d2, this.arrivalInTicks);
    }
}
