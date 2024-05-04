package com.viaversion.viatestmod.mixin;

import com.viaversion.viatestmod.ViaTestMod;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerScreen.class)
public class MixinMultiplayerScreen extends Screen {

    public MixinMultiplayerScreen(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    public void addButton(CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(ViaTestMod.resourcePackDebug ? Text.of("ResourcePack Test: ON") : Text.of("ResourcePack Test: OFF"), button -> {
            ViaTestMod.resourcePackDebug = !ViaTestMod.resourcePackDebug;
            button.setMessage(ViaTestMod.resourcePackDebug ? Text.of("ResourcePack Test: ON") : Text.of("ResourcePack Test: OFF"));
        }).dimensions(0, 0, 150, 20).build());
    }

}
