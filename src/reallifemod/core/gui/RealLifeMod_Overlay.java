package de.ItsAMysterious.mods.reallifemod.core.gui;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import de.ItsAMysterious.mods.reallifemod.core.entities.cars.EntityJeep;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class RealLifeMod_Overlay extends Gui {

   private Minecraft mc;
   private FontRenderer fontRenderObj;
   private int width;
   private World world;
   private boolean showMessage = true;
   private boolean showTips = true;
   public boolean showAdditionalInfo = false;
   private static final ResourceLocation temperatureGuiEmpty = new ResourceLocation("reallifemod:textures/gui/guiThermometerEmpty.png");
   private static final ResourceLocation temperatureGui = new ResourceLocation("reallifemod:textures/gui/guiThermometer2.png");


   public RealLifeMod_Overlay(Minecraft mc) {
      this.mc = mc;
      this.fontRenderObj = mc.fontRenderer;
      this.width = mc.displayWidth;
      this.showMessage = true;
      this.showTips = true;
   }

   @SubscribeEvent(
      priority = EventPriority.NORMAL
   )
   public void RenderProps(RenderGameOverlayEvent event) {
      ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
      int k = scaledresolution.getScaledWidth();
      int l = scaledresolution.getScaledHeight();
      if(event.type == ElementType.ALL) {
         if(Keyboard.isKeyDown(35)) {
            this.showMessage = false;
         }

         this.world = this.mc.theWorld;
         this.mc = Minecraft.getMinecraft();
         GL11.glPushMatrix();
         GL11.glEnable('\u803a');
         if(this.mc.thePlayer.field_70154_o != null && this.mc.thePlayer.field_70154_o instanceof EntityJeep) {
            EntityJeep p = (EntityJeep)this.mc.thePlayer.field_70154_o;
            GL11.glPushMatrix();
            GL11.glTranslated((double)(k - 256), (double)(l - 256), 0.0D);
            GL11.glPushMatrix();
            this.mc.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/Speedometer.png"));
            this.func_73729_b(0, 0, 0, 0, 256, 256);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated((double)k, (double)(l + 24), 0.0D);
            GL11.glTranslated(17.0D - Math.sin(Math.abs(p.currentspeed)) * 17.0D, -Math.cos(Math.abs(p.currentspeed)) * 24.0D, 0.0D);
            GL11.glPushMatrix();
            GL11.glRotated(-230.0D + Math.abs(p.currentspeed * 100.0D), 0.0D, 0.0D, 1.0D);
            this.mc.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/SpeedometerNeedle.png"));
            this.func_73729_b(0, 0, 0, 0, 256, 256);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            this.func_73731_b(this.fontRenderObj, p.fuelvalue + "%", k - 20, l - 40, Color.white.getRGB());
         }

         if(!this.mc.thePlayer.field_71075_bZ.isCreativeMode) {
            EntityClientPlayerMP p1 = this.mc.thePlayer;
            RealLifeProperties props = (RealLifeProperties)p1.getExtendedProperties("RealLifeProperties");
            if(props != null) {
               if(Keyboard.isKeyDown(35) && !p1.capabilities.isCreativeMode) {
                  this.showTips = false;
               }

               GL11.glEnable(3042);
               GL11.glDepthMask(false);
               GL11.glBlendFunc(770, 771);
               this.mc.getTextureManager().bindTexture(temperatureGuiEmpty);
               this.func_73729_b(0, 0, 0, 0, 17, 68);
               this.mc.getTextureManager().bindTexture(temperatureGui);
               this.func_73729_b(0, 0, 0, 0, 17, 68);
               String mood = props.ColorOfMood(RealLifeProperties.mood) + RealLifeProperties.mood.name();
               FontRenderer var10001 = this.mc.fontRenderer;
               StringBuilder var10002 = (new StringBuilder()).append("Cash: ");
               financialProps var10003 = (financialProps)p1.getExtendedProperties("financialProps");
               this.func_73731_b(var10001, var10002.append(financialProps.Cash).append("$").toString(), 20, 5, Color.white.getRGB());
               this.func_73731_b(this.mc.fontRenderer, "Mood: " + mood, 20, 15, Color.white.getRGB());
               RealLifeProperties var10 = (RealLifeProperties)this.mc.thePlayer.getExtendedProperties("RealLifeProperties");
               int var9 = (int)(0.775F * (40.0F - RealLifeProperties.Temperature));
               RealLifeProperties var10005 = (RealLifeProperties)this.mc.thePlayer.getExtendedProperties("RealLifeProperties");
               func_73734_a(4, var9, 13, (int)(0.775F * (40.0F - RealLifeProperties.Temperature)) + 1, Color.blue.getRGB());
               if(this.showTips) {
                  this.func_73731_b(this.mc.fontRenderer, "Press tab to view additional information, like fatigue or courage!", 100, 0, Color.white.getRGB());
                  this.func_73731_b(this.mc.fontRenderer, "Press " + EnumChatFormatting.YELLOW + "\'H\'" + EnumChatFormatting.WHITE + " to hide this info!", 100, 10, Color.white.getRGB());
               }

               func_73734_a(20, 25, 100, 35, Color.black.getRGB());
               RealLifeProperties.get(this.mc.thePlayer);
               func_73734_a(20, 25, 100 - (int)(RealLifeProperties.thirst / 100.0F * 80.0F), 35, Color.blue.getRGB());
               this.func_73731_b(this.mc.fontRenderer, "water", 50, 26, Color.white.getRGB());
               func_73734_a(20, 40, 100, 50, Color.black.getRGB());
               RealLifeProperties.get(this.mc.thePlayer);
               func_73734_a(20, 40, 20 + (int)(RealLifeProperties.Toilet / 100.0D * 80.0D), 50, Color.orange.darker().darker().getRGB());
               this.func_73731_b(this.mc.fontRenderer, "toilet", 50, 41, Color.white.getRGB());
               if(Keyboard.isKeyDown(15)) {
                  func_73734_a(10, 70, 100, 80, Color.black.getRGB());
                  func_73734_a(10, 70, 100, 80, Color.red.darker().getRGB());
                  this.func_73731_b(this.mc.fontRenderer, "fatigue", 37, 71, Color.white.getRGB());
                  Gui.drawRect(10, 85, 100, 95, Color.black.getRGB());
                  Gui.drawRect(10, 85, 100, 95, Color.pink.getRGB());
                  this.func_73731_b(this.mc.fontRenderer, "sex-apeal", 30, 86, Color.white.getRGB());
                  Gui.drawRect(10, 100, 100, 110, Color.black.getRGB());
                  Gui.drawRect(10, 100, 100, 110, Color.green.getRGB());
                  this.func_73731_b(this.mc.fontRenderer, "fear", 45, 101, Color.white.getRGB());
               }

               if(p1.capabilities.isCreativeMode && this.showMessage) {
                  this.func_73731_b(this.mc.fontRenderer, "HUD is disabled in CreativeMode!", 0, 0, Color.white.getRGB());
                  this.func_73731_b(this.mc.fontRenderer, "Press " + EnumChatFormatting.YELLOW + "\'H\'" + EnumChatFormatting.WHITE + " to hide this info!", 0, 10, Color.white.getRGB());
               }
            }

            GL11.glColor3f(1.0F, 1.0F, 1.0F);
            GL11.glDisable(3042);
            GL11.glDepthMask(true);
         }

         GL11.glPopMatrix();
      }
   }

   @SubscribeEvent
   public void renderRefreshContent(RenderGameOverlayEvent event) {}

}
