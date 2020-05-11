package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityRobot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderRobot extends RenderBiped {

   protected ResourceLocation NPCTexture;
   protected static ModelBiped model = new ModelBiped();
   FontRenderer renderer;


   public RenderRobot(float shadowsize) {
      super(model, shadowsize);
      this.renderer = Minecraft.getMinecraft().fontRenderer;
   }

   protected void func_77041_b(EntityLivingBase entity, float f) {
      super.func_77041_b(entity, f);
      this.preRenderCallbackSerpent((EntityRobot)entity, f);
   }

   protected void preRenderCallbackSerpent(EntityRobot entity, float f) {}

   public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.func_76986_a((EntityRobot)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(Entity par1Entity) {
      return new ResourceLocation("reallifemod:textures/entity/robot.png");
   }

}
