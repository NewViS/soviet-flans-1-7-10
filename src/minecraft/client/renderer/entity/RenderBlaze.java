package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.ResourceLocation;

public class RenderBlaze extends RenderLiving {

   private static final ResourceLocation blazeTextures = new ResourceLocation("textures/entity/blaze.png");
   private int field_77068_a;


   public RenderBlaze() {
      super(new ModelBlaze(), 0.5F);
      this.field_77068_a = ((ModelBlaze)super.mainModel).func_78104_a();
   }

   public void doRender(EntityBlaze var1, double var2, double var4, double var6, float var8, float var9) {
      int var10 = ((ModelBlaze)super.mainModel).func_78104_a();
      if(var10 != this.field_77068_a) {
         this.field_77068_a = var10;
         super.mainModel = new ModelBlaze();
      }

      super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
   }

   protected ResourceLocation getEntityTexture(EntityBlaze var1) {
      return blazeTextures;
   }

}
