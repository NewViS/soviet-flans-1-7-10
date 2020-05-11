package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.config.RealLifeModConfig;
import de.ItsAMysterious.mods.reallifemod.core.models.ModelLantern;
import de.ItsAMysterious.mods.reallifemod.core.tiles.LanternTE;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class LanternTER extends TileEntitySpecialRenderer {

   private final IModelCustom lantern = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/Lantern.obj"));
   private ModelLantern modelMC = new ModelLantern();


   public void renderModelAt(LanternTE tileEntity, double x, double y, double z, float f) {
      GL11.glPushMatrix();
      if(!RealLifeModConfig.minecraftstyle) {
         GL11.glPushMatrix();
         GL11.glTranslatef((float)x - 0.125F, (float)y, (float)z - 0.125F);
         if(tileEntity.func_145831_w() != null) {
            World world = tileEntity.func_145831_w();
            this.func_147499_a(this.texture(world.isDaytime()));
         } else {
            this.func_147499_a(this.texture(false));
         }

         GL11.glPushMatrix();
         GL11.glScalef(0.3F, 0.25F, 0.3F);
         this.lantern.renderAll();
         GL11.glPopMatrix();
         GL11.glPopMatrix();
      } else {
         GL11.glTranslatef((float)x + 0.5F, (float)y + 1.4F, (float)z + 0.5F);
         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef((float)(tileEntity.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
         this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/LanternMC.png"));
         this.modelMC.func_78088_a((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
      }

      GL11.glPopMatrix();
   }

   public void func_147500_a(TileEntity tileEntity, double var1, double var2, double var3, float var4) {
      this.renderModelAt((LanternTE)tileEntity, var1, var2, var3, var4);
   }

   private ResourceLocation texture(boolean isActive) {
      return isActive?new ResourceLocation("reallifemod:textures/tiles/LanternActive.png"):new ResourceLocation("reallifemod:textures/tiles/LanternInActive.png");
   }
}
