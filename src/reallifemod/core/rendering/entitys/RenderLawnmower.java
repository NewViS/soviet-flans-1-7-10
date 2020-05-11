package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import de.ItsAMysterious.mods.reallifemod.core.entitys.EntityLawnMower;
import de.ItsAMysterious.mods.reallifemod.core.models.ModelLawnmower;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderLawnmower extends Render {

   private final ModelLawnmower model = new ModelLawnmower();


   public void func_76986_a(Entity entity, double x, double y, double z, float rotationYaw, float rotationPitch) {
      this.renderLawnmower((EntityLawnMower)entity, x, y, z, rotationYaw, rotationPitch);
   }

   private void renderLawnmower(EntityLawnMower entity, double x, double y, double z, float rotationYaw, float rotationPitch) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)x, (float)y + 1.5F, (float)z);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(rotationYaw, 0.0F, 1.0F, 0.0F);
      this.func_110776_a(this.func_110775_a(entity));
      this.model.func_78088_a((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      GL11.glPopMatrix();
   }

   protected ResourceLocation func_110775_a(Entity p_110775_1_) {
      return new ResourceLocation("reallifemod:textures/entity/Lawnmower.png");
   }
}
