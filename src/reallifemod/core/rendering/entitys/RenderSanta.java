package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityLanz;
import de.ItsAMysterious.mods.reallifemod.core.entitys.seasonalstuff.entitySanta;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSanta extends Render {

   public static ModelBiped model;
   static ResourceLocation MainTexture = new ResourceLocation("reallifemod:textures/entity/santa-claus.png");


   public RenderSanta() {
      model = new ModelBiped();
   }

   public ResourceLocation getTexture(EntityLanz lanz) {
      return MainTexture;
   }

   public ResourceLocation func_110775_a(Entity entity) {
      return this.func_110775_a(entity);
   }

   public void renderLanz(entitySanta entity, double x, double y, double z, float rotationYaw, float rotationPitch) {
      GL11.glPushMatrix();
      FMLClientHandler.instance().getClient().renderEngine.bindTexture(MainTexture);
      model.render(entity, (float)x, (float)y, (float)z, rotationYaw, rotationPitch, 1.0F);
      GL11.glPopMatrix();
   }

   public void func_76986_a(Entity entity, double x, double y, double z, float rotationYaw, float rotationPitch) {
      this.renderLanz((entitySanta)entity, x, y, z, rotationYaw, rotationPitch);
   }

}
