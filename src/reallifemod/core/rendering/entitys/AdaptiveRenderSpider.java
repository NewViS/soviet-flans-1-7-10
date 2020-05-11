package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class AdaptiveRenderSpider extends RenderSpider {

   private static final ResourceLocation spiderEyesTextures = new ResourceLocation("textures/entity/spider_eyes.png");
   private static final ResourceLocation spiderTextures = new ResourceLocation("textures/entity/spider/spider.png");
   private static final String __OBFID = "CL_00001027";


   public AdaptiveRenderSpider() {
      this.func_77042_a(new ModelSpider());
   }

   protected float func_77037_a(EntitySpider p_77037_1_) {
      return 180.0F;
   }

   protected ResourceLocation func_110775_a(EntitySpider p_110775_1_) {
      return spiderTextures;
   }

   protected ResourceLocation func_110775_a(Entity p_110775_1_) {
      return this.func_110775_a((EntitySpider)p_110775_1_);
   }

}
