package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityBaby;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderBaby extends RenderBiped {

   protected ResourceLocation NPCTexture;
   protected static ModelBiped model = new ModelBiped();
   FontRenderer renderer;


   public RenderBaby(float shadowSize) {
      super(model, shadowSize);
      this.renderer = Minecraft.getMinecraft().fontRenderer;
   }

   protected void func_77041_b(EntityLivingBase entity, float f) {
      super.func_77041_b(entity, f);
      this.preRenderCallbackSerpent(entity, f);
   }

   protected void preRenderCallbackSerpent(Entity entity, float f) {
      if(entity.ridingEntity instanceof EntityPlayer) {
         GL11.glTranslated(0.25D, 1.0D, -0.25D);
         GL11.glRotated(-90.0D, 0.0D, 1.0D, 0.0D);
         GL11.glRotated(-45.0D, 1.0D, 0.0D, 0.0D);
      }

      GL11.glScaled(0.5D, 0.5D, 0.5D);
   }

   public void func_76986_a(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
      super.doRender((EntityBaby)entity, x, y, z, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(Entity par1Entity) {
      return new ResourceLocation("reallifemod:textures/entity/Baby.png");
   }

}
