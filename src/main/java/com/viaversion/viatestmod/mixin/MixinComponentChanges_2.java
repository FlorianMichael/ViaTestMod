package com.viaversion.viatestmod.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.viaversion.viatestmod.ViaTestMod;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentType;
import net.minecraft.network.RegistryByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(targets = "net/minecraft/component/ComponentChanges$2")
public class MixinComponentChanges_2 {

    /**
     * @author
     * @reason
     */
    @Overwrite
    public ComponentChanges decode(RegistryByteBuf registryByteBuf) {
        int i = registryByteBuf.readVarInt();
        int j = registryByteBuf.readVarInt();
        if (i == 0 && j == 0) {
            return ComponentChanges.EMPTY;
        } else {
            Reference2ObjectMap<DataComponentType<?>, Optional<?>> reference2ObjectMap = new Reference2ObjectArrayMap(i + j);

            try {
                int k;
                DataComponentType dataComponentType;
                for(k = 0; k < i; ++k) {
                    dataComponentType = (DataComponentType)DataComponentType.PACKET_CODEC.decode(registryByteBuf);
                    Object object = dataComponentType.getPacketCodec().decode(registryByteBuf);
                    reference2ObjectMap.put(dataComponentType, Optional.of(object));
                }

                for(k = 0; k < j; ++k) {
                    dataComponentType = (DataComponentType)DataComponentType.PACKET_CODEC.decode(registryByteBuf);
                    reference2ObjectMap.put(dataComponentType, Optional.empty());
                }
            } catch (Exception e) {
                ViaTestMod.LOGGER.error("Failed to decode component changes", e);
            }

            return new ComponentChanges(reference2ObjectMap);
        }
    }

}
