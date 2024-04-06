package com.viaversion.viatestmod.mixin;

import com.viaversion.viatestmod.ViaTestMod;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public abstract class MixinClientConnection {

    @Inject(method = "exceptionCaught", at = @At("TAIL"))
    public void printNetworkErrors(ChannelHandlerContext context, Throwable ex, CallbackInfo ci) {
        ViaTestMod.LOGGER.error("Packet error", ex);
    }
}