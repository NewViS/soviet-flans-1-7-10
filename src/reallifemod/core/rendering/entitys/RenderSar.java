package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class RenderSar extends Render {

   public static IModelCustom OBJModel;
   @SideOnly(Side.CLIENT)
   public static ResourceLocation textur;


   public RenderSar(String Texture, String OBJFile) {
      textur = new ResourceLocation(Texture);
      ResourceLocation objfile = new ResourceLocation(OBJFile);
      OBJModel = AdvancedModelLoader.loadModel(objfile);
   }

   public void func_76986_a(Entity var1, double x, double y, double z, float var8, float var9) {
      GL11.glTranslated(x, y, z);
      this.func_110776_a(textur);
      GL11.glRotatef(var1.rotationYaw, 0.0F, 1.0F, 0.0F);
      GL11.glRotated((double)var1.rotationPitch, 1.0D, 0.0D, 1.0D);
      OBJModel.renderAll();
      GL11.glPopMatrix();
   }

   protected ResourceLocation func_110775_a(Entity var1) {
      return textur;
   }
}
