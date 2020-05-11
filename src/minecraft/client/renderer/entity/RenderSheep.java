package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSheep extends RenderLiving {

   private static final ResourceLocation sheepTextures = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
   private static final ResourceLocation shearedSheepTextures = new ResourceLocation("textures/entity/sheep/sheep.png");


   public RenderSheep(ModelBase var1, ModelBase var2, float var3) {
      super(var1, var3);
      this.setRenderPassModel(var2);
   }

   protected int shouldRenderPass(EntitySheep var1, int var2, float var3) {
      if(var2 == 0 && !var1.getSheared()) {
         this.bindTexture(sheepTextures);
         if(var1.hasCustomNameTag() && "jeb_".equals(var1.getCustomNameTag())) {
            boolean var9 = true;
            int var5 = var1.ticksExisted / 25 + var1.getEntityId();
            int var6 = var5 % EntitySheep.fleeceColorTable.length;
            int var7 = (var5 + 1) % EntitySheep.fleeceColorTable.length;
            float var8 = ((float)(var1.ticksExisted % 25) + var3) / 25.0F;
            GL11.glColor3f(EntitySheep.fleeceColorTable[var6][0] * (1.0F - var8) + EntitySheep.fleeceColorTable[var7][0] * var8, EntitySheep.fleeceColorTable[var6][1] * (1.0F - var8) + EntitySheep.fleeceColorTable[var7][1] * var8, EntitySheep.fleeceColorTable[var6][2] * (1.0F - var8) + EntitySheep.fleeceColorTable[var7][2] * var8);
         } else {
            int var4 = var1.getFleeceColor();
            GL11.glColor3f(EntitySheep.fleeceColorTable[var4][0], EntitySheep.fleeceColorTable[var4][1], EntitySheep.fleeceColorTable[var4][2]);
         }

         return 1;
      } else {
         return -1;
      }
   }

   protected ResourceLocation getEntityTexture(EntitySheep var1) {
      return shearedSheepTextures;
   }

}
