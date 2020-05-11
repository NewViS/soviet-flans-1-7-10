package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.TrafficlightTE;
import java.nio.FloatBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class TrafficlightTER extends TileEntitySpecialRenderer {

   private final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/TrafficLight.obj"));
   ResourceLocation green = new ResourceLocation("reallifemod:textures/tiles/TrafficLightGreen.png");
   ResourceLocation orange = new ResourceLocation("reallifemod:textures/tiles/TrafficLightOrange.png");
   ResourceLocation red = new ResourceLocation("reallifemod:textures/tiles/TrafficLightRed.png");
   ResourceLocation inactive = new ResourceLocation("reallifemod:textures/tiles/TrafficLightInactive.png");
   private FloatBuffer color;


   public void renderModelAt(TrafficlightTE tileEntity, double x, double y, double z, float f) {
      Tessellator tessellator = Tessellator.instance;
      GL11.glDisable(3553);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 1);
      GL11.glEnable('\u803a');
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
      GL11.glRotatef((float)(tileEntity.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      if(tileEntity.func_145832_p() * 90 == 180 || tileEntity.func_145832_p() * 90 == 0) {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      }

      GL11.glScalef(0.3F, 0.3F, 0.3F);
      switch(null.$SwitchMap$de$ItsAMysterious$mods$reallifemod$core$tiles$TrafficlightTE$State[tileEntity.state.ordinal()]) {
      case 1:
         this.func_147499_a(this.green);
         GL11.glPushMatrix();
         GL11.glTranslated(x - 0.0D, y + 1.2999999523162842D, z - 10.0D);
         tessellator.setColorRGBA(0, 255, 0, 120);
         GL11.glPopMatrix();
         break;
      case 2:
         this.func_147499_a(this.orange);
         GL11.glPushMatrix();
         GL11.glTranslated(x + 0.5D, y + 1.5D, z - 0.5D);
         GL11.glPopMatrix();
         break;
      case 3:
         this.func_147499_a(this.red);
         GL11.glPushMatrix();
         GL11.glTranslated(x + 0.5D, y + 1.7000000476837158D, z + 0.5D);
         GL11.glPopMatrix();
         break;
      case 4:
         this.func_147499_a(this.inactive);
      }

      GL11.glDisable(3042);
      GL11.glEnable(2896);
      GL11.glEnable(3553);
      this.model.renderAll();
      GL11.glPopMatrix();
   }

   public void func_147500_a(TileEntity tileEntity, double var1, double var2, double var3, float var4) {
      this.renderModelAt((TrafficlightTE)tileEntity, var1, var2, var3, var4);
   }

   private static FloatBuffer asFloatBuffer(float[] values) {
      FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
      buffer.put(values);
      buffer.flip();
      return buffer;
   }
}
