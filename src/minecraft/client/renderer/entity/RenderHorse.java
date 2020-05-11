package net.minecraft.client.renderer.entity;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderHorse extends RenderLiving {

   private static final Map field_110852_a = Maps.newHashMap();
   private static final ResourceLocation whiteHorseTextures = new ResourceLocation("textures/entity/horse/horse_white.png");
   private static final ResourceLocation muleTextures = new ResourceLocation("textures/entity/horse/mule.png");
   private static final ResourceLocation donkeyTextures = new ResourceLocation("textures/entity/horse/donkey.png");
   private static final ResourceLocation zombieHorseTextures = new ResourceLocation("textures/entity/horse/horse_zombie.png");
   private static final ResourceLocation skeletonHorseTextures = new ResourceLocation("textures/entity/horse/horse_skeleton.png");


   public RenderHorse(ModelBase var1, float var2) {
      super(var1, var2);
   }

   protected void preRenderCallback(EntityHorse var1, float var2) {
      float var3 = 1.0F;
      int var4 = var1.getHorseType();
      if(var4 == 1) {
         var3 *= 0.87F;
      } else if(var4 == 2) {
         var3 *= 0.92F;
      }

      GL11.glScalef(var3, var3, var3);
      super.preRenderCallback(var1, var2);
   }

   protected void renderModel(EntityHorse var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      if(var1.isInvisible()) {
         super.mainModel.setRotationAngles(var2, var3, var4, var5, var6, var7, var1);
      } else {
         this.bindEntityTexture(var1);
         super.mainModel.render(var1, var2, var3, var4, var5, var6, var7);
      }

   }

   protected ResourceLocation getEntityTexture(EntityHorse var1) {
      if(!var1.func_110239_cn()) {
         switch(var1.getHorseType()) {
         case 0:
         default:
            return whiteHorseTextures;
         case 1:
            return donkeyTextures;
         case 2:
            return muleTextures;
         case 3:
            return zombieHorseTextures;
         case 4:
            return skeletonHorseTextures;
         }
      } else {
         return this.func_110848_b(var1);
      }
   }

   private ResourceLocation func_110848_b(EntityHorse var1) {
      String var2 = var1.getHorseTexture();
      ResourceLocation var3 = (ResourceLocation)field_110852_a.get(var2);
      if(var3 == null) {
         var3 = new ResourceLocation(var2);
         Minecraft.getMinecraft().getTextureManager().loadTexture(var3, new LayeredTexture(var1.getVariantTexturePaths()));
         field_110852_a.put(var2, var3);
      }

      return var3;
   }

}
