package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Blocks;
import de.ItsAMysterious.mods.reallifemod.core.tiles.TileEntityTV;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class TelevisionTER extends TileEntitySpecialRenderer {

   private final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/furniture/TV.obj"));
   Tessellator tessellator;
   private ResourceLocation texture = new ResourceLocation("reallifemod:textures/tiles/modelTV.png");


   public void func_147500_a(TileEntity tileEntity, double var1, double var2, double var3, float var4) {
      this.renderModelAt((TileEntityTV)tileEntity, var1, var2, var3, var4);
   }

   public void renderModelAt(TileEntityTV tile, double x, double y, double z, float f) {
      GL11.glPushMatrix();
      Tessellator tessellator = Tessellator.instance;
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
      GL11.glRotatef((float)(tile.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      this.func_147499_a(this.texture);
      if(tile.func_145831_w() != null) {
         if(!(tile.func_145831_w().getBlock(tile.field_145851_c, tile.field_145848_d - 1, tile.field_145849_e) == Blocks.air | tile.func_145831_w().getBlock(tile.field_145851_c, tile.field_145848_d - 1, tile.field_145849_e).getBlockBoundsMaxY() < 1.0D) && tile.func_145831_w().getBlock(tile.field_145851_c, tile.field_145848_d - 1, tile.field_145849_e) != RealLifeMod_Blocks.television) {
            this.model.renderAll();
         } else {
            GL11.glTranslated(0.0D, 0.0D, -0.25D);
            this.model.renderOnly(new String[]{"Screen"});
         }
      } else {
         this.model.renderAll();
      }

      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }
}
