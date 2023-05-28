package net.AbraXator.chakral.client.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.AbraXator.chakral.init.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.PositionSourceType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

public record LightRayParticleOption(PositionSource destination, int arrivalInTicks) implements ParticleOptions {
    public static final Codec<LightRayParticleOption> CODEC = RecordCodecBuilder.create(objectInstance -> {
        return objectInstance.group(
                PositionSource.CODEC.fieldOf("destination").forGetter(o -> o.destination),
                Codec.INT.fieldOf("arrival_in_ticks").forGetter(o -> o.arrivalInTicks)
        ).apply(objectInstance, LightRayParticleOption::new);
    });

    public static final Deserializer<LightRayParticleOption> DESERIALIZER = new Deserializer<LightRayParticleOption>() {
        @Override
        public LightRayParticleOption fromCommand(ParticleType<LightRayParticleOption> pParticleType, StringReader pReader) throws CommandSyntaxException {
            pReader.expect(' ');
            float f = ((float) pReader.readDouble());
            pReader.expect(' ');
            float f1 = ((float) pReader.readDouble());
            pReader.expect(' ');
            float f2 = ((float) pReader.readDouble());
            pReader.expect(' ');
            int i = pReader.readInt();
            BlockPos blockPos = BlockPos.containing(f, f1, f2);
            return new LightRayParticleOption(new BlockPositionSource(blockPos), i);
        }

        @Override
        public LightRayParticleOption fromNetwork(ParticleType<LightRayParticleOption> pParticleType, FriendlyByteBuf pBuffer) {
            PositionSource positionSource = PositionSourceType.fromNetwork(pBuffer);
            int i = pBuffer.readVarInt();
            return new LightRayParticleOption(positionSource, i);
        }
    };

    @Override
    public ParticleType<?> getType() {
        return ModParticles.LIGHT_RAY.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        PositionSourceType.toNetwork(this.destination, pBuffer);
        pBuffer.writeVarInt(this.arrivalInTicks);
    }

    @Override
    public String writeToString() {
        Vec3 vec3 = this.destination.getPosition((Level) null).get();
        double d0 = vec3.x();
        double d1 = vec3.y();
        double d2 = vec3.z();
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %d", ForgeRegistries.PARTICLE_TYPES.getKey(this.getType()), d0, d1, d2, this.arrivalInTicks);
    }
}
