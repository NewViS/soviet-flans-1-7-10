package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityLanz;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class RenderLanz extends Render {

   public static IModelCustom modelLanz;
   static ResourceLocation MainTexture = new ResourceLocation("reallifemod:textures/entity/LanzTexture.png");


   public RenderLanz() {
      modelLanz = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/agriculture/entitys/lanz/modelLanz8Bit.obj"));
   }

   public ResourceLocation getTexture(EntityLanz lanz) {
      return MainTexture;
   }

   public ResourceLocation func_110775_a(Entity entity) {
      return this.func_110775_a(entity);
   }

   public void renderLanz(EntityLanz entity, double x, double y, double z) {
      GL11.glPushMatrix();
      GL11.glTranslated(x, y, z);
      GL11.glScalef(1.0F, 1.0F, 1.0F);
      GL11.glRotatef(entity.field_70177_z, 0.0F, 1.0F, 0.0F);
      GL11.glRotated((double)entity.field_70125_A, 1.0D, 0.0D, 1.0D);
      GL11.glPushMatrix();
      FMLClientHandler.instance().getClient().getTextureManager().bindTexture(MainTexture);
      modelLanz.renderAll();
      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }

   public void func_76986_a(Entity entity, double x, double y, double z, float scaleX, float scaleY) {
      this.renderLanz((EntityLanz)entity, x, y, z);
   }

}
