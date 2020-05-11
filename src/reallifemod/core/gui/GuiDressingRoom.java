package de.ItsAMysterious.mods.reallifemod.core.gui;

import cpw.mods.fml.client.config.GuiSlider;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiCharacterSetup;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiDressUp;
import java.awt.Color;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiDressingRoom extends RLM_Gui {

   GuiCharacterSetup previousScreen;
   private int mouseX;
   private int mouseY;
   private GuiSlider rotation;


   public GuiDressingRoom(GuiCharacterSetup prevScreen) {
      this.previousScreen = prevScreen;
   }

   public void func_73866_w_() {
      super.func_73866_w_();
      this.rotation = new GuiSlider(2, this.field_146294_l / 2 - 50, this.field_146295_m - 25, 100, 20, "rotation ", " ", -180.0D, 180.0D, 0.0D, true, true);
      this.field_146292_n.add(new GuiButton(1, 0, 30, 70, 20, "Has Cap"));
      this.field_146292_n.add(new GuiButton(2, 0, 50, 70, 20, "Is Angel"));
      this.field_146292_n.add(new GuiButton(3, 0, 50, 70, 20, "Dress up!"));
      this.field_146292_n.add(new GuiButton(0, this.field_146294_l - 75, this.field_146295_m - 25, 70, 20, "OK"));
      this.field_146292_n.add(this.rotation);
   }

   public void func_73863_a(int par1, int par2, float f) {
      this.func_146278_c(0);
      this.func_73733_a(0, 30, this.field_146294_l, 40, Color.black.getRGB(), 0);
      this.func_73733_a(0, this.field_146295_m - 40, this.field_146294_l, this.field_146295_m - 30, 0, Color.black.getRGB());
      GuiCharacterSetup.renderEntity(this.field_146294_l / 2, this.field_146295_m / 2 + 90, 90, (float)this.rotation.getValueInt(), (float)(this.mouseY + this.field_146295_m / 2 - 40), this.previousScreen.thePlayer);
      this.func_73732_a(this.field_146289_q, "Dressing Room", this.field_146294_l / 2, 15, Color.white.getRGB());
      super.func_73863_a(par1, par2, f);
   }

   public void func_146284_a(GuiButton button) {
      if(button.id == 1) {
         ((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).hascap = !((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).hascap;
      }

      if(button.id == 2) {
         this.field_146297_k.displayGuiScreen(new GuiDressUp(this));
      }

      if(button.id == 0) {
         this.field_146297_k.displayGuiScreen(this.previousScreen);
      }

   }

   public void func_146274_d() {
      super.func_146274_d();
      this.mouseX = -(Mouse.getEventX() * this.field_146294_l / this.field_146297_k.displayWidth);
      this.mouseY = -(this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.displayHeight - 1);
   }

   public static void renderEntity(int xPos, int yPos, int scale, float rot, float p_147046_4_, EntityLivingBase theEntity) {
      GL11.glPushMatrix();
      GL11.glBlendFunc(0, 771);
      GL11.glTranslatef((float)xPos, (float)yPos, 100.0F);
      GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
      GL11.glScalef((float)scale, (float)scale, (float)scale);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      float f2 = theEntity.renderYawOffset;
      float f3 = theEntity.field_70177_z;
      float f4 = theEntity.field_70125_A;
      float f5 = theEntity.prevRotationYawHead;
      float f6 = theEntity.rotationYawHead;
      GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
      theEntity.renderYawOffset = (float)Math.atan((double)(rot / 40.0F)) * 20.0F;
      theEntity.field_70177_z = (float)Math.atan((double)(rot / 40.0F)) * 40.0F;
      theEntity.field_70125_A = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
      theEntity.rotationYawHead = theEntity.field_70177_z;
      theEntity.prevRotationYawHead = theEntity.field_70177_z;
      GL11.glTranslatef(0.0F, theEntity.field_70129_M, 0.0F);
      RenderManager.instance.playerViewY = rot;
      RenderManager.instance.renderEntityWithPosYaw(theEntity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
      theEntity.renderYawOffset = f2;
      theEntity.field_70177_z = f3;
      theEntity.field_70125_A = f4;
      theEntity.prevRotationYawHead = f5;
      theEntity.rotationYawHead = f6;
      GL11.glPopMatrix();
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable('\u803a');
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glDisable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }
}
