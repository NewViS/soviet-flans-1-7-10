package de.ItsAMysterious.mods.reallifemod.core.gui;

import cpw.mods.fml.client.config.GuiSlider;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiCharacterSetup;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiHairColor extends RLM_Gui {

   public static GuiSlider red;
   public static GuiSlider green;
   public static GuiSlider blue;
   private GuiScreen previousScreen;
   private IModelCustom modelHead;
   private int middleX;
   private int mouseX;
   private int middleY;
   private int mouseY;


   public GuiHairColor(GuiCharacterSetup prevScreen) {
      this.previousScreen = prevScreen;
      this.modelHead = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/characterhead.obj"));
   }

   public void func_73866_w_() {
      GuiSlider var10000 = new GuiSlider;
      int var10003 = this.field_146294_l - 115;
      RealLifeProperties var10011 = (RealLifeProperties)Minecraft.getMinecraft().thePlayer.getExtendedProperties("RealLifeProperties");
      var10000.<init>(0, var10003, 30, 100, 20, "red:  ", " ", 0.0D, 255.0D, (double)RealLifeProperties.haircolor.x, true, true);
      red = var10000;
      var10000 = new GuiSlider;
      var10003 = this.field_146294_l - 115;
      var10011 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
      var10000.<init>(1, var10003, 55, 100, 20, "green:  ", " ", 0.0D, 255.0D, (double)RealLifeProperties.haircolor.y, true, true);
      green = var10000;
      var10000 = new GuiSlider;
      var10003 = this.field_146294_l - 115;
      var10011 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
      var10000.<init>(2, var10003, 80, 100, 20, "blue:  ", " ", 0.0D, 255.0D, (double)RealLifeProperties.haircolor.z, true, true);
      blue = var10000;
      this.field_146292_n.add(new GuiButton(4, this.field_146294_l - 90, this.field_146295_m - 25, 75, 20, "OK"));
      this.field_146292_n.add(red);
      this.field_146292_n.add(green);
      this.field_146292_n.add(blue);
   }

   public void func_73876_c() {
      RealLifeProperties var10000 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
      RealLifeProperties.haircolor.x = (float)red.getValue();
      var10000 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
      RealLifeProperties.haircolor.y = (float)green.getValue();
      var10000 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
      RealLifeProperties.haircolor.z = (float)blue.getValue();
   }

   public void func_73863_a(int par1, int par2, float par3) {
      GuiCharacterSetup.renderEntity(100, this.field_146295_m + 430, 300, 0.0F, 0.0F, this.field_146297_k.thePlayer);
      new Color(red.getValueInt(), green.getValueInt(), blue.getValueInt());
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc('\u8001', '\u8002');
      GL11.glColor3d(red.getValue(), green.getValue(), blue.getValue());
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslatef((float)(this.field_146294_l / 2), (float)(this.field_146295_m / 2), 1.0F);
      GL11.glScalef(100.0F, 100.0F, 100.0F);
      this.modelHead.renderAll();
      GL11.glPopMatrix();
      super.func_73863_a(par1, par2, par3);
   }

   public void func_146284_a(GuiButton button) {
      if(button.id == 4) {
         RealLifeProperties var10000 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
         RealLifeProperties.haircolor.x = (float)red.getValue();
         var10000 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
         RealLifeProperties.haircolor.y = (float)green.getValue();
         var10000 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
         RealLifeProperties.haircolor.z = (float)blue.getValue();
         this.field_146297_k.displayGuiScreen(this.previousScreen);
      }

   }

   public static void renderEntity(int p_147046_0_, int p_147046_1_, int p_147046_2_, float rot, float p_147046_4_, EntityLivingBase p_147046_5_) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)p_147046_0_, (float)p_147046_1_, 100.0F);
      GL11.glScalef((float)(-p_147046_2_), (float)p_147046_2_, (float)p_147046_2_);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      float f2 = p_147046_5_.renderYawOffset;
      float f3 = 0.0F;
      float f4 = p_147046_5_.field_70125_A;
      float f5 = 0.0F;
      float f6 = 0.0F;
      GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      p_147046_5_.renderYawOffset = 0.0F;
      p_147046_5_.field_70177_z = 180.0F;
      p_147046_5_.field_70125_A = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
      p_147046_5_.rotationYawHead = 0.0F;
      p_147046_5_.prevRotationYawHead = 0.0F;
      GL11.glTranslatef(0.0F, p_147046_5_.field_70129_M, 0.0F);
      RenderManager.instance.playerViewY = rot;
      RenderManager.instance.renderEntityWithPosYaw(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
      p_147046_5_.renderYawOffset = f2;
      p_147046_5_.field_70177_z = f3;
      p_147046_5_.field_70125_A = f4;
      p_147046_5_.prevRotationYawHead = 0.0F;
      p_147046_5_.rotationYawHead = 0.0F;
      GL11.glPopMatrix();
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable('\u803a');
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glDisable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   public void func_146274_d() {
      super.func_146274_d();
      this.mouseX = -(Mouse.getEventX() * this.field_146294_l / this.field_146297_k.displayWidth);
      this.mouseY = -(this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.displayHeight - 1);
   }
}
