package net.AbraXator.chakral.lightray;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;

public class LightraySelector {
    public static final Codec<LightraySelector> CODEC = RecordCodecBuilder.create(lightraySelectorInstance -> {
        return lightraySelectorInstance.group(LightrayData.CODEC.optionalFieldOf("data").forGetter(o -> {
            return o.currentLightray.map(Pair::getLeft);
        }), Codec.LONG.fieldOf("tick").forGetter(o -> {
            return o.currentLightray.map(Pair::getRight).orElse(-1L);
        })).apply(lightraySelectorInstance, LightraySelector::new);
    });

    private Optional<Pair<LightrayData, Long>> currentLightray;

    public LightraySelector(Optional<LightrayData> data, long ticks) {
        this.currentLightray = data.map(lightrayData -> {
            return Pair.of(lightrayData, ticks);
        });
    }

    public LightraySelector() {
        this.currentLightray = Optional.empty();
    }

    public void addRay(LightrayData data, long ticks){
        if(this.shouldReplaceRay(data, ticks)) {
            this.currentLightray = Optional.of(Pair.of(data, ticks));
        }
    }

    private boolean shouldReplaceRay(LightrayData data, long ticks) {
        if (this.currentLightray.isEmpty()) return false;
        else {
            Pair<LightrayData, Long> pair = this.currentLightray.get();
            long ticks1 = pair.getRight();
            if (ticks != ticks1) return false;
            else {
                LightrayData data1 = pair.getLeft();
                return data.distance() < data1.distance();
            }
        }
    }

    public Optional<LightrayData> chosenRay(long ticks){
        if (this.currentLightray.isEmpty()){
            return Optional.empty();
        }else {
            return this.currentLightray.get().getRight() < ticks ? Optional.of(currentLightray.get().getLeft()) : Optional.empty();
        }
    }

    public void startOver(){
        this.currentLightray = Optional.empty();
    }
}
