package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderGiantZombie extends RenderLiving {

   private static final ResourceLocation zombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");
   private float scale;


   public RenderGiantZombie(ModelBase var1, float var2, float var3) {
      super(var1, var2 * var3);
      this.scale = var3;
   }

   protected void preRenderCallback(EntityGiantZombie var1, float var2) {
      GL11.glScalef(this.scale, this.scale, this.scale);
   }

   protected ResourceLocation getEntityTexture(EntityGiantZombie var1) {
      return zombieTextures;
   }

}
