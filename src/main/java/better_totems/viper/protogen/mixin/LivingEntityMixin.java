package better_totems.viper.protogen.mixin;

import better_totems.viper.protogen.BetterTotems;
import better_totems.viper.protogen.Items.ModItems;
import better_totems.viper.protogen.Items.TotemPacketsIds;
import better_totems.viper.protogen.VerifyParticles.ModdedPotionEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void damage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (source.getName().equals("fall") && entity.hasStatusEffect(ModdedPotionEffects.NoFall)) {
            entity.removeStatusEffect(ModdedPotionEffects.NoFall);
            cir.setReturnValue(true);
        }
    };

    @Inject(method = "tryUseDeathProtector", at = @At("HEAD"), cancellable = true)
    private void tryUseDeathProtector(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        int SendRequest = 0;
            ItemStack itemStack = null;

            for (Hand hand : Hand.values()) {
                ItemStack stackInHand = entity.getStackInHand(hand);
                SendRequest = TotemPacketsIds.getObject(stackInHand.getItem());
                if (SendRequest != -1) {
                    if (source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY) && stackInHand.getItem() != ModItems.LevitationTotem) {
                        continue;
                    }
                    itemStack = stackInHand.copy();
                    stackInHand.decrement(1);
                    break;
                }
            }

            if (itemStack != null) {
                if (itemStack.getItem() == ModItems.EvilTotem) {
                    entity.setHealth(entity.getMaxHealth());
                    entity.clearStatusEffects();
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BAD_OMEN, 1000, 4, false, true));
                    if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
                        Criteria.USED_TOTEM.trigger(serverPlayerEntity, itemStack);
                    }
                }
                if (itemStack.getItem() == ModItems.LevitationTotem) {
                    entity.setHealth(entity.getMaxHealth());
                    entity.clearStatusEffects();
                    Vec3d Pos = entity.getPos();
                    int BottomY = entity.getEntityWorld().getBottomY();
                    if (Pos.y < BottomY) {
                        Vec3d newpos = new Vec3d(Pos.x, BottomY + 15, Pos.z);
                        if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
                            serverPlayerEntity.networkHandler.requestTeleport(newpos.getX(), newpos.getY(), newpos.getZ(), entity.getYaw(), entity.getPitch());
                        } else {
                            entity.setPosition(newpos);
                        }
                    }
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 450, 3, false, true));
                    entity.addStatusEffect(new StatusEffectInstance(ModdedPotionEffects.NoFall, 750, 0, false, true));
                    if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
                        Criteria.USED_TOTEM.trigger(serverPlayerEntity, itemStack);
                    }
                }
                entity.getWorld().sendEntityStatus(entity, (byte) SendRequest); // Totem animation
                cir.setReturnValue(true);
                //cir.cancel();
            }
        }
}
