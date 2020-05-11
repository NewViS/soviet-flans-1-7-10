package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.ReallifemodNPC;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderNPC extends RenderBiped {

   protected ResourceLocation NPCTexture;
   protected static ModelBiped model = new ModelBiped();


   public RenderNPC(float shadowsize) {
      super(model, shadowsize);
   }

   protected void func_77041_b(EntityLivingBase entity, float f) {
      this.preRenderCallbackSerpent((ReallifemodNPC)entity, f);
   }

   protected void preRenderCallbackSerpent(ReallifemodNPC entity, float f) {}

   protected ResourceLocation func_110775_a(Entity par1Entity) {
      return new ResourceLocation("reallifemod:textures/entity/workers/" + ((ReallifemodNPC)par1Entity).currentJob + ".png");
   }

}
