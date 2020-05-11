package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.api.gui.GuiEdit;
import de.ItsAMysterious.mods.reallifemod.api.gui.GuiRadioGroup;
import de.ItsAMysterious.mods.reallifemod.api.gui.GuiRadiobutton;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiDressingRoom;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiHairColor;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiRelationships;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiCharacterSetup extends GuiScreen {

   private GuiButton NameChanging;
   EntityPlayer thePlayer;
   private int GuiLifePage;
   private int middleX;
   private int mouseX;
   private int middleY;
   private int mouseY;
   private float playerRot;
   public int state;
   public String date;
   public static String theGender;
   private GuiEdit Name;
   private GuiEdit Surname;
   public GuiRadioGroup Gender;
   private float rotation;


   public GuiCharacterSetup() {
      this.Gender = new GuiRadioGroup(130, this.field_146295_m / 2 + 50);
      this.rotation = 0.0F;
      this.thePlayer = Minecraft.getMinecraft().thePlayer;
      Keyboard.enableRepeatEvents(true);
   }

   public void func_73876_c() {
      this.Gender.doUpdate();
      this.Name.func_146178_a();
      this.Surname.func_146178_a();
      super.updateScreen();
   }

   public void func_73866_w_() {
      this.date = ((RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties")).birthdate;
      this.doGenderSetup();
      RealLifeProperties var10000 = (RealLifeProperties)Minecraft.getMinecraft().thePlayer.getExtendedProperties("RealLifeProperties");
      if(RealLifeProperties.name == null) {
         this.state = 0;
      } else {
         this.state = 1;
      }

      this.field_146292_n.clear();
      this.field_146292_n.add(new GuiButton(0, this.field_146294_l - 45, this.field_146295_m - 25, 40, 20, "Next"));
      this.Name = new GuiEdit(this.field_146289_q, 180, 50, 100, 15);
      this.Surname = new GuiEdit(this.field_146289_q, 180, 75, 100, 15);
      var10000 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
      if(RealLifeProperties.name != null) {
         GuiEdit var1 = this.Name;
         RealLifeProperties var10001 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
         var1.func_146180_a(RealLifeProperties.name);
         var1 = this.Surname;
         var10001 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
         var1.func_146180_a(RealLifeProperties.surname);
      }

      this.date = ((RealLifeProperties)Minecraft.getMinecraft().thePlayer.getExtendedProperties("RealLifeProperties")).birthdate;
      super.initGui();
   }

   public boolean func_73868_f() {
      return false;
   }

   public void doGenderSetup() {
      theGender = RealLifeProperties.get(this.field_146297_k.thePlayer).Gender.name();
      GuiRadiobutton male = new GuiRadiobutton(130, this.field_146295_m / 2 + 50, "male");
      GuiRadiobutton female = new GuiRadiobutton(130 + this.field_146289_q.getStringWidth("female") + 5, this.field_146295_m / 2 + 50, "female");
      GuiRadioGroup var10000 = this.Gender;
      GuiRadioGroup.buttonList.put(Integer.valueOf(0), male);
      var10000 = this.Gender;
      GuiRadioGroup.buttonList.put(Integer.valueOf(1), female);
      if(theGender.equals("male")) {
         male.checked = true;
         var10000 = this.Gender;
         GuiRadioGroup.currentButton = male;
      } else if(theGender.equals("female")) {
         female.checked = true;
         var10000 = this.Gender;
         GuiRadioGroup.currentButton = female;
      }

   }

   public void func_73863_a(int p1, int p2, float p3) {
      if(this.state == 0) {
         this.func_73732_a(this.field_146289_q, "Welcome " + Minecraft.getMinecraft().thePlayer.getDisplayName(), this.field_146294_l / 2, 50, Color.white.getRGB());
         this.func_73732_a(this.field_146289_q, "Thank you for downloading the Real life mod! You will now", this.field_146294_l / 2, 65, Color.white.getRGB());
         this.func_73732_a(this.field_146289_q, "have to choose your ingame name and setup your character.", this.field_146294_l / 2, 80, Color.white.getRGB());
      } else {
         this.field_146292_n.clear();
         this.field_146292_n.add(new GuiButton(0, this.field_146294_l - 75, 75, 75, 25, "Relationships"));
         this.field_146292_n.add(new GuiButton(1, this.field_146294_l - 45, this.field_146295_m - 25, 40, 20, "Finish"));
         this.field_146292_n.add(new GuiButton(2, 25, this.field_146295_m - 25, 40, 20, "<"));
         this.field_146292_n.add(new GuiButton(3, 90, this.field_146295_m - 25, 40, 20, ">"));
         this.field_146292_n.add(new GuiButton(4, 129, 110, 75, 20, EnumChatFormatting.YELLOW + "Haircolor"));
         this.field_146292_n.add(new GuiButton(5, 129, 130, 75, 20, EnumChatFormatting.RED + "Skincolor"));
         this.field_146292_n.add(new GuiButton(6, 129, 150, 75, 20, "Eyecolor"));
         this.field_146292_n.add(new GuiButton(7, 129, this.field_146295_m / 2 + 70, 75, 20, "Dressing Room"));
         this.func_146278_c(0);
         this.func_73733_a(0, 30, this.field_146294_l, 40, Color.black.getRGB(), 0);
         this.func_73733_a(0, this.field_146295_m - 40, this.field_146294_l, this.field_146295_m - 30, 0, Color.black.getRGB());
         renderEntity(70, this.field_146295_m / 2 + 90, 90, (float)(100 - (this.mouseX + 100)), (float)(this.mouseY + this.field_146295_m / 2 - 40), this.thePlayer);
         this.func_73732_a(this.field_146289_q, "Character Setup", this.field_146294_l / 2, 15, Color.white.getRGB());
         this.func_73731_b(this.field_146289_q, EnumChatFormatting.BLUE + "Name: ", 130, 50, Color.white.getRGB());
         this.func_73731_b(this.field_146289_q, EnumChatFormatting.BLUE + "Surname: ", 130, 75, Color.white.getRGB());
         this.func_73731_b(this.field_146289_q, EnumChatFormatting.BLUE + "Date of birth: " + EnumChatFormatting.WHITE + this.date, 130, 100, Color.white.getRGB());
         RealLifeProperties var10000 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
         String number = String.valueOf(RealLifeProperties.phonenumber);
         this.func_73731_b(this.field_146289_q, number, this.field_146294_l - this.field_146289_q.getStringWidth(number) - 5, 100, Color.white.getRGB());
         this.Name.func_146194_f();
         this.Surname.func_146194_f();
         this.Gender.draw();
      }

      super.drawScreen(p1, p2, p3);
   }

   protected void func_146284_a(GuiButton button) {
      if(this.state == 0) {
         ++this.state;
      }

      if(this.state == 1) {
         if(button.id == 0) {
            this.field_146297_k.displayGuiScreen(new GuiRelationships(this.thePlayer, this));
         }

         if(button.id == 1) {
            RealLifeProperties var10000 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
            RealLifeProperties.name = this.Name.func_146179_b();
            var10000 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProperties");
            RealLifeProperties.surname = this.Surname.func_146179_b();
            if(theGender.equals("female")) {
               RealLifeProperties.get(this.field_146297_k.thePlayer).Gender = RealLifeProperties.gender.female;
            } else {
               RealLifeProperties.get(this.field_146297_k.thePlayer).Gender = RealLifeProperties.gender.male;
            }

            this.field_146297_k.displayGuiScreen((GuiScreen)null);
         }
      }

      if(button.id == 4) {
         this.field_146297_k.displayGuiScreen(new GuiHairColor(this));
      }

      if(button.id == 7) {
         this.field_146297_k.displayGuiScreen(new GuiDressingRoom(this));
      }

      super.actionPerformed(button);
   }

   public void func_73869_a(char taste, int id) {
      if(this.state == 1) {
         if(this.Name.func_146201_a(taste, id)) {
            ;
         }

         if(id == 15) {
            if(this.Name.func_146206_l()) {
               this.Name.func_146195_b(false);
               this.Surname.func_146195_b(true);
            } else if(this.Surname.func_146206_l()) {
               this.Surname.func_146195_b(false);
               this.Name.func_146195_b(true);
            } else if(!this.Name.func_146206_l() || !this.Surname.func_146206_l()) {
               this.Name.func_146195_b(true);
            }
         }

         if(this.Surname.func_146201_a(taste, id)) {
            ;
         }

         if(id == 28) {
            this.func_146284_a((GuiButton)this.field_146292_n.get(0));
         }
      }

      super.keyTyped(taste, id);
   }

   public static void renderEntity(int p_147046_0_, int p_147046_1_, int p_147046_2_, float rot, float p_147046_4_, EntityLivingBase p_147046_5_) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)p_147046_0_, (float)p_147046_1_, 100.0F);
      GL11.glScalef((float)(-p_147046_2_), (float)p_147046_2_, (float)p_147046_2_);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      float f2 = p_147046_5_.renderYawOffset;
      float f3 = p_147046_5_.field_70177_z;
      float f4 = p_147046_5_.field_70125_A;
      float f5 = p_147046_5_.prevRotationYawHead;
      float f6 = p_147046_5_.rotationYawHead;
      GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
      p_147046_5_.renderYawOffset = (float)Math.atan((double)(rot / 40.0F)) * 20.0F;
      p_147046_5_.field_70177_z = (float)Math.atan((double)(rot / 40.0F)) * 40.0F;
      p_147046_5_.field_70125_A = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
      p_147046_5_.rotationYawHead = p_147046_5_.field_70177_z;
      p_147046_5_.prevRotationYawHead = p_147046_5_.field_70177_z;
      GL11.glTranslatef(0.0F, p_147046_5_.field_70129_M, 0.0F);
      RenderManager.instance.playerViewY = rot;
      RenderManager.instance.renderEntityWithPosYaw(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
      p_147046_5_.renderYawOffset = f2;
      p_147046_5_.field_70177_z = f3;
      p_147046_5_.field_70125_A = f4;
      p_147046_5_.prevRotationYawHead = f5;
      p_147046_5_.rotationYawHead = f6;
      GL11.glPopMatrix();
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable('\u803a');
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glDisable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   public void func_146274_d() {
      super.handleMouseInput();
      Mouse.poll();
      this.mouseX = -(Mouse.getEventX() * this.field_146294_l / this.field_146297_k.displayWidth);
      this.mouseY = -(this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.displayHeight - 1);
   }

   public void func_73864_a(int x, int y, int id) {
      this.Gender.onMouseClicked(x, y, id);
      this.Name.func_146192_a(x, y, id);
      this.Surname.func_146192_a(x, y, id);
      super.mouseClicked(x, y, id);
   }
}
