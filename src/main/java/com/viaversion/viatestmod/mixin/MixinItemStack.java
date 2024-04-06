package com.viaversion.viatestmod.mixin;

import net.minecraft.client.item.TooltipType;
import net.minecraft.component.ComponentMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public class MixinItemStack {

    @Shadow
    protected ComponentMap getComponents() {
        return null;
    }

    @Inject(method = "Lnet/minecraft/item/ItemStack;getTooltip(Lnet/minecraft/item/Item$TooltipContext;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/client/item/TooltipType;)Ljava/util/List;", at = @At("RETURN"))
    private void showItemComponents(Item.TooltipContext context, @Nullable PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> ci) {
        ci.getReturnValue().addAll(getComponents().stream().map(component -> Text.of(component.toString())).toList());
    }

}
