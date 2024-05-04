package com.viaversion.viatestmod.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipType;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.Unit;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Mixin(ItemStack.class)
public class MixinItemStack {

    @Shadow
    protected ComponentMap getComponents() {
        return null;
    }

    @Inject(method = "Lnet/minecraft/item/ItemStack;getTooltip(Lnet/minecraft/item/Item$TooltipContext;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/client/item/TooltipType;)Ljava/util/List;", at = @At("RETURN"))
    private void showItemComponents(Item.TooltipContext context, @Nullable PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> ci) {
        ci.getReturnValue().addAll(getComponents().stream().map(component -> Text.of(component.toString())).toList());

//        AttributeModifiersComponent test = new AttributeModifiersComponent(
//                Arrays.asList(new AttributeModifiersComponent.Entry(Registries.ATTRIBUTE.getEntry(1).get(), new EntityAttributeModifier(
//                        "test", 1.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
//                ), AttributeModifierSlot.ANY)), true
//        );

//        try {
//            final PotionContentsComponent test = new PotionContentsComponent(Registries.POTION.getEntry(1).get());
//            final DataResult<NbtElement> a = PotionContentsComponent.CODEC.encodeStart(NbtOps.INSTANCE, test);
//            System.out.println(a.getOrThrow().getClass());
//            System.out.println(a.getOrThrow());
//        } catch ( Exception e) {
//        }
    }

}
