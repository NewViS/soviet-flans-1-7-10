package de.ItsAMysterious.mods.reallifemod.core.gui;

import cpw.mods.fml.client.config.GuiSlider;
import de.ItsAMysterious.mods.reallifemod.core.entities.cars.EntityJeep;
import java.awt.Color;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class GuiVehicleModification extends GuiScreen {

   public static int GUI_ID;
   private EntityJeep vehicle;
   private boolean isDragging = false;
   private Vector2f rotation = new Vector2f(0.0F, 0.0F);
   private int configIndex = 0;
   private GuiSlider red;
   private GuiSlider green;
   private GuiSlider blue;


   public GuiVehicleModification() {
      GUI_ID = 59;
   }

   public GuiVehicleModification(EntityJeep j) {
      this.vehicle = j;
      this.red = new GuiSlider(3, this.field_146294_l / 2 - 120, this.field_146295_m / 2 + 25, 75, 20, "Red ", ": ", (double)j.getColor().x, 255.0D, 1.0D, true, true);
      this.green = new GuiSlider(4, this.field_146294_l / 2 - 35, this.field_146295_m / 2 + 25, 75, 20, "Green ", ": ", (double)j.getColor().y, 255.0D, 1.0D, true, true);
      this.blue = new GuiSlider(5, this.field_146294_l / 2 + 50, this.field_146295_m / 2 + 25, 75, 20, "Blue ", ": ", (double)j.getColor().z, 255.0D, 1.0D, true, true);
   }

   public void func_73866_w_() {
      super.initGui();
      this.field_146292_n.clear();
      this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 + 72, this.field_146295_m / 2 - 102 + 25, 60, 20, "Colors"));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 + 72, this.field_146295_m / 2 - 102 + 45, 60, 20, "Interior"));
      this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 2 + 72, this.field_146295_m / 2 - 102 + 65, 60, 20, "Accessoirs"));
      ((GuiButton)this.field_146292_n.get(1)).enabled = false;
      ((GuiButton)this.field_146292_n.get(2)).enabled = false;
      if(this.configIndex == 0) {
         this.red.field_146128_h = this.field_146294_l / 2 - 120;
         this.green.field_146128_h = this.field_146294_l / 2 - 35;
         this.blue.field_146128_h = this.field_146294_l / 2 + 50;
         this.red.field_146129_i = this.field_146295_m / 2 + 25;
         this.green.field_146129_i = this.field_146295_m / 2 + 25;
         this.blue.field_146129_i = this.field_146295_m / 2 + 25;
         this.field_146292_n.add(this.red);
         this.field_146292_n.add(this.green);
         this.field_146292_n.add(this.blue);
      }

      if(this.configIndex == 1) {
         ;
      }

      if(this.configIndex == 2) {
         ;
      }

   }

   public void func_73876_c() {
      super.updateScreen();
      if(!this.isDragging) {
         if(this.rotation.x + 1.0F > 360.0F) {
            this.rotation.x = 1.0F;
         } else {
            ++this.rotation.x;
         }
      }

      if(!Mouse.isButtonDown(0) && Mouse.getX() < this.field_146294_l / 2 - 100 + this.field_146294_l && Mouse.getX() > this.field_146294_l / 2 + 100 + this.field_146294_l) {
         ;
      }

      if(this.configIndex == 0) {
         this.vehicle.setColor(new Vector3f((float)this.red.sliderValue, (float)this.green.sliderValue, (float)this.blue.sliderValue));
      }

   }

   public boolean func_73868_f() {
      return false;
   }

   public void func_73863_a(int i, int j, float k) {
      GL11.glPushMatrix();
      GL11.glScaled(1.6D, 1.5D, 1.0D);
      GL11.glTranslated((double)(-this.field_146294_l) / 4.85D, 0.0D, 0.0D);
      this.field_146297_k.renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/gui/VehicleCustomization.png"));
      this.func_73729_b(this.field_146294_l / 2 - 80, this.field_146295_m / 2 - 102, 0, 0, this.field_146294_l / 2 + 160, this.field_146295_m / 2 + 101);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glPushMatrix();
      GL11.glTranslated((double)(this.field_146294_l / 2), (double)(this.field_146295_m / 2), 250.0D);
      GL11.glTranslated(-Math.sin((double)this.rotation.x * 3.141592653589793D / 180.0D) * 19.1D, 0.0D, -Math.cos((double)this.rotation.x * 3.141592653589793D / 180.0D) * 18.5D);
      GL11.glScaled(17.5D, 17.5D, 17.5D);
      this.renderEntity(this.vehicle);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      this.func_73732_a(this.field_146289_q, "Customize colorsettings", this.field_146294_l / 2, this.field_146295_m / 2 + 10, Color.white.getRGB());
      this.func_73731_b(this.field_146289_q, EnumChatFormatting.UNDERLINE + "Info:", this.field_146294_l / 2 - 74 - this.field_146289_q.getStringWidth("Vehicle Info"), this.field_146295_m / 2 - 100 + 15, Color.white.getRGB());
      this.func_73731_b(this.field_146289_q, "Age: " + this.vehicle.field_70173_aa / 20 / 1000, this.field_146294_l / 2 - 74 - this.field_146289_q.getStringWidth("Vehicle Info"), this.field_146295_m / 2 - 100 + 27, Color.white.getRGB());
      super.drawScreen(i, j, k);
   }

   protected void func_73864_a(int x, int y, int id) {
      super.mouseClicked(x, y, id);
      if(Mouse.isButtonDown(0) && x > this.field_146294_l / 2 - 100 && x < this.field_146294_l / 2 + 100 && y < this.field_146295_m / 2 + 30 && y > this.field_146295_m / 2 - 102) {
         this.isDragging = true;
      }

   }

   public void func_146284_a(GuiButton b) {
      super.actionPerformed(b);
   }

   private void renderEntity(Entity entity) {
      GL11.glPushMatrix();
      GL11.glRotatef(this.rotation.x, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(this.rotation.y, 1.0F, 0.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glPushMatrix();
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glPushMatrix();
      RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      RenderHelper.disableStandardItemLighting();
      GL11.glPopMatrix();
   }
}
