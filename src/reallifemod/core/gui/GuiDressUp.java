package de.ItsAMysterious.mods.reallifemod.core.gui;

import cpw.mods.fml.client.config.GuiSlider;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.core.enums.EnumDress;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiCharacterSetup;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiDressingRoom;
import java.awt.Color;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiDressUp extends GuiScreen {

   private GuiDressingRoom previousScreen;
   private int mouseX;
   private int mouseY;
   private GuiSlider rotation;


   public GuiDressUp(GuiDressingRoom prevScreen) {
      this.previousScreen = prevScreen;
   }

   public void func_73866_w_() {
      super.initGui();
      this.rotation = new GuiSlider(this.field_146292_n.size(), this.field_146294_l / 2 - 50, this.field_146295_m - 25, 100, 20, "rotation ", " ", -180.0D, 180.0D, 0.0D, true, true);
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, this.field_146294_l - 80, this.field_146295_m - 25, 75, 20, "Done"));
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, 0, 45, 75, 20, "Creepy look"));
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, 0, 25 + this.field_146292_n.size() * 20, 75, 20, "Sceletal look"));
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, 0, 25 + this.field_146292_n.size() * 20, 75, 20, "Zombietronic"));
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, 0, 25 + this.field_146292_n.size() * 20, 75, 20, "Witch"));
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, 0, 25 + this.field_146292_n.size() * 20, 75, 20, "Chicken"));
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, 0, 25 + this.field_146292_n.size() * 20, 75, 20, "Pig"));
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, 0, 25 + this.field_146292_n.size() * 20, 75, 20, "Sheep"));
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, 0, 25 + this.field_146292_n.size() * 20, 75, 20, "Enderman"));
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, 0, 25 + this.field_146292_n.size() * 20, 75, 20, "Creeper Hoodie"));
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, 0, 25 + this.field_146292_n.size() * 20, 75, 20, "Robot"));
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, 0, 25 + this.field_146292_n.size() * 20, 75, 20, "No Dress"));
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, 0, 30, 75, 20, EnumChatFormatting.GREEN + "Up"));
      this.field_146292_n.add(new GuiButton(this.field_146292_n.size() + 1, 0, this.field_146295_m - 25, 75, 20, EnumChatFormatting.RED + "Down"));
      this.field_146292_n.add(this.rotation);
   }

   public void func_73863_a(int par1, int par2, float f) {
      this.func_146278_c(0);
      GuiCharacterSetup.renderEntity(this.field_146294_l / 2, this.field_146295_m / 2 + 90, 90, (float)this.rotation.getValueInt(), (float)(this.mouseY + this.field_146295_m / 2 - 40), this.previousScreen.previousScreen.thePlayer);
      this.func_73732_a(this.field_146289_q, "Dress Up", this.field_146294_l / 2, 15, Color.white.getRGB());
      this.func_73732_a(this.field_146289_q, "About this Dress", this.field_146294_l / 4 * 3, 45, Color.white.getRGB());
      this.func_73733_a(0, 30, this.field_146294_l, 40, Color.black.getRGB(), 0);
      this.func_73733_a(0, this.field_146295_m - 40, this.field_146294_l, this.field_146295_m - 30, 0, Color.black.getRGB());
      super.drawScreen(par1, par2, f);
   }

   public void func_146284_a(GuiButton button) {
      if(button.id == 1) {
         this.field_146297_k.displayGuiScreen(this.previousScreen);
      }

      if(button.id == 2) {
         ((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).dressing = EnumDress.Creeper;
      }

      if(button.id == 3) {
         ((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).dressing = EnumDress.Skeleton;
      }

      if(button.id == 4) {
         ((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).dressing = EnumDress.Zombie;
      }

      if(button.id == 5) {
         ((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).dressing = EnumDress.Witch;
      }

      if(button.id == 6) {
         ((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).dressing = EnumDress.Chicken;
      }

      if(button.id == 7) {
         ((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).dressing = EnumDress.Pig;
      }

      if(button.id == 8) {
         ((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).dressing = EnumDress.Sheep;
      }

      if(button.id == 9) {
         ((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).dressing = EnumDress.Enderman;
      }

      if(button.id == 10) {
         ((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).dressing = EnumDress.CreeperHoodie;
      }

      if(button.id == 11) {
         ((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).dressing = EnumDress.Robot;
      }

      if(button.id == 12) {
         ((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).dressing = EnumDress.NONE;
      }

      int i;
      GuiButton b;
      if(button.id == this.field_146292_n.size() - 2) {
         for(i = 0; i < this.field_146292_n.size(); ++i) {
            b = null;
            if(this.field_146292_n.get(i) instanceof GuiButton) {
               b = (GuiButton)this.field_146292_n.get(i);
               if(b != null && b.id != 0 && b.id != 1 && b.id != this.field_146292_n.size() - 1 && b.id != this.field_146292_n.size() - 2) {
                  b.yPosition -= 20;
                  if(b.yPosition - 20 < 45) {
                     b.enabled = false;
                  } else {
                     b.enabled = true;
                  }
               }
            }
         }
      }

      if(button.id == this.field_146292_n.size() - 1) {
         for(i = 0; i < this.field_146292_n.size(); ++i) {
            b = null;
            if(this.field_146292_n.get(i) instanceof GuiButton) {
               b = (GuiButton)this.field_146292_n.get(i);
               if(b != null && b.id != 0 && b.id != 1 && b.id != this.field_146292_n.size() - 1 && b.id != this.field_146292_n.size() - 2) {
                  if(b.yPosition + 20 > this.field_146295_m - 45) {
                     b.enabled = false;
                  } else {
                     b.enabled = true;
                  }

                  b.yPosition += 20;
               }
            }
         }
      }

   }

   public void func_146274_d() {
      super.handleMouseInput();
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
