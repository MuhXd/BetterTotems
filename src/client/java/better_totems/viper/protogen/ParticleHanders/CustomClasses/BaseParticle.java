package better_totems.viper.protogen.ParticleHanders.CustomClasses;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class BaseParticle extends SpriteBillboardParticle {

    protected BaseParticle(ClientWorld level, double xCoord, double yCoord, double zCoord, SpriteProvider spriteSet, double xd, double yd, double zd) {
            super(level, xCoord, yCoord, zCoord, xd, yd, zd);
            this.velocityMultiplier = -1F;
            this.x = xd;
            this.y = yd;
            this.z = zd;
            this.scale = 0.75F;
            this.maxAge = 20;
            this.setSpriteForAge(spriteSet);

            this.red = 1f;
            this.green = 1f;
            this.blue = 1f;
        }

        @Override
        public void tick() {
            super.tick();
            this.velocityMultiplier = -1F - ( (this.age / this.maxAge) * 3);
        }

        @Override
        public ParticleTextureSheet getType() {
            return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
        }

        @Environment(EnvType.CLIENT)
        public static class Factory implements ParticleFactory<SimpleParticleType> {
            private final SpriteProvider sprites;

            public Factory(SpriteProvider spriteSet) {
                this.sprites = spriteSet;
            }

            public Particle createParticle(SimpleParticleType particleType, ClientWorld level, double x, double y, double z, double dx, double dy, double dz) {
                return new BaseParticle(level, x, y, z, this.sprites, dx, dy, dz);
            }
        }
}
