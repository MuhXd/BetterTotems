package better_totems.viper.protogen.mixin.client;
import better_totems.viper.protogen.Particles.ParticlesMain;
import net.minecraft.item.Item;
import better_totems.viper.protogen.Items.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.client.network.ClientConnectionState;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.TickablePacketListener;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import org.apache.commons.lang3.ObjectUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static better_totems.viper.protogen.Items.TotemPacketsIds.GetRealObject;
import static better_totems.viper.protogen.Items.TotemPacketsIds.getObject;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin extends ClientCommonNetworkHandler implements ClientPlayPacketListener, TickablePacketListener {
    @Shadow
    private ClientWorld world;

    public ClientPlayNetworkHandlerMixin(MinecraftClient client, ClientConnection connection, ClientConnectionState connectionState) {
        super(client, connection, connectionState);
    }
    // rather than overriding it Inject to keep version support
    @Inject(at = @At("HEAD"), method = "onEntityStatus", cancellable = true)
    public void onEntityStatus(EntityStatusS2CPacket packet, CallbackInfo info) {
        NetworkThreadUtils.forceMainThread(packet, this, this.client);
        Entity entity = packet.getEntity(this.world);
        if (entity != null) {
            Item Gotten = GetRealObject(packet.getStatus());
            if (Gotten != null) {
                    if (Gotten == ModItems.EvilTotem) {
                        this.client.particleManager.addEmitter(entity, ParticlesMain.EvilParticleOfDoomAndGloom, 30);
                        this.world.playSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0f, 1.0f, false);
                        if (entity != this.client.player) {
                            info.cancel();
                            return;
                        }
                    }
                    if (Gotten == ModItems.LevitationTotem) {
                        this.client.particleManager.addEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30);
                        this.world.playSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0f, 1.0f, false);
                        if (entity != this.client.player) {
                            info.cancel();
                            return;
                        }
                    }
                    this.client.gameRenderer.showFloatingItem(Gotten.getDefaultStack());
                    info.cancel();
                    }
                }
            }
        }