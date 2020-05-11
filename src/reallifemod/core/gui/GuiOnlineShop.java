package de.ItsAMysterious.mods.reallifemod.core.gui;

import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityLanz;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiEMail;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiHelp;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiInternet;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiManagement;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiSettings;
import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityTruck;
import java.awt.Color;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiOnlineShop extends RLM_Gui implements GuiYesNoCallback {

   private float d = 0.0F;
   public GuiOnlineShop.Vehicle currentVehicle;
   private EntityClientPlayerMP player;


   public GuiOnlineShop() {
      this.currentVehicle = GuiOnlineShop.Vehicle.LANZ;
      this.player = Minecraft.getMinecraft().thePlayer;
   }

   public void func_73863_a(int par1, int par2, float f1) {
      if(this.currentVehicle == GuiOnlineShop.Vehicle.LANZ) {
         new EntityLanz(Minecraft.getMinecraft().theWorld);
      } else if(this.currentVehicle == GuiOnlineShop.Vehicle.TRUCK) {
         new EntityTruck(Minecraft.getMinecraft().theWorld);
      } else if(this.currentVehicle == GuiOnlineShop.Vehicle.JEEP) {
         ;
      }

      drawRect(0, 20, this.field_146294_l, this.field_146295_m, Color.WHITE.getRGB(), -150.0F);
      this.field_146292_n.add(new GuiButton(0, 0, 0, this.field_146294_l / 5, 20, "Home"));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 5, 0, this.field_146294_l / 5, 20, "E-Mail"));
      this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 5 * 2, 0, this.field_146294_l / 5, 20, "Help"));
      this.field_146292_n.add(new GuiButton(3, this.field_146294_l / 5 * 3, 0, this.field_146294_l / 5, 20, "Settings"));
      this.field_146292_n.add(new GuiButton(4, this.field_146294_l / 5 * 4, 0, this.field_146294_l / 5, 20, "Management"));
      this.field_146292_n.add(new GuiButton(6, 20, this.field_146295_m - 30, 20, 20, "<"));
      this.field_146292_n.add(new GuiButton(7, 220, this.field_146295_m - 30, 20, 20, ">"));
      this.field_146292_n.add(new GuiButton(8, this.field_146294_l / 2 + 20, this.field_146295_m - 30, 100, 20, "Buy Vehicle"));
      this.field_146292_n.add(new GuiButton(9, this.field_146294_l / 2 + 160, this.field_146295_m - 30, 100, 20, "Sell Vehicle"));
      this.renderEntity(new EntityTruck(FMLClientHandler.instance().getWorldClient()));
      this.func_73731_b(this.field_146289_q, "Vehicle Facts", 400, 20, Color.gray.getRGB());
      super.func_73863_a(par1, par2, f1);
   }

   public void func_73876_c() {
      ++this.d;
      this.renderEntity(new EntityTruck(FMLClientHandler.instance().getWorldClient()));
      super.func_73876_c();
   }

   public void func_146284_a(GuiButton button) {
      if(button.id == 0) {
         this.field_146297_k.displayGuiScreen(new GuiInternet());
      }

      if(button.id == 1) {
         this.field_146297_k.displayGuiScreen(new GuiEMail());
      }

      if(button.id == 2) {
         this.field_146297_k.displayGuiScreen(new GuiHelp());
      }

      if(button.id == 3) {
         this.field_146297_k.displayGuiScreen(new GuiSettings());
      }

      if(button.id == 4) {
         this.field_146297_k.displayGuiScreen(new GuiManagement());
      }

      if(button.id == 6) {
         if(this.currentVehicle == GuiOnlineShop.Vehicle.LANZ) {
            this.currentVehicle = GuiOnlineShop.Vehicle.TRUCK;
         } else if(this.currentVehicle == GuiOnlineShop.Vehicle.TRUCK) {
            this.currentVehicle = GuiOnlineShop.Vehicle.JEEP;
         } else if(this.currentVehicle == GuiOnlineShop.Vehicle.JEEP) {
            this.currentVehicle = GuiOnlineShop.Vehicle.LANZ;
         }
      }

      if(button.id == 7) {
         if(this.currentVehicle == GuiOnlineShop.Vehicle.LANZ) {
            this.currentVehicle = GuiOnlineShop.Vehicle.JEEP;
         } else if(this.currentVehicle == GuiOnlineShop.Vehicle.TRUCK) {
            this.currentVehicle = GuiOnlineShop.Vehicle.LANZ;
         } else if(this.currentVehicle == GuiOnlineShop.Vehicle.JEEP) {
            this.currentVehicle = GuiOnlineShop.Vehicle.TRUCK;
         }
      }

      if(button.id == 8) {
         if(this.currentVehicle == GuiOnlineShop.Vehicle.LANZ) {
            this.spawnEntity(new EntityLanz(Minecraft.getMinecraft().theWorld, this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v + 2.0D), Minecraft.getMinecraft().theWorld, this.player);
         } else if(this.currentVehicle == GuiOnlineShop.Vehicle.TRUCK) {
            this.spawnEntity(new EntityTruck(Minecraft.getMinecraft().theWorld, this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v + 2.0D), Minecraft.getMinecraft().theWorld, this.player);
         }

         if(this.currentVehicle == GuiOnlineShop.Vehicle.JEEP) {
            ;
         }
      }

      this.func_73878_a(true, button.id);
   }

   private void renderEntity(Entity entity) {
      GL11.glPushMatrix();
      GL11.glTranslatef(150.0F, 250.0F, 5.0F);
      GL11.glRotatef(this.d, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glScalef(20.0F, 20.0F, 20.0F);
      GL11.glPushMatrix();
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glPushMatrix();
      RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      RenderHelper.disableStandardItemLighting();
      GL11.glPopMatrix();
   }

   public void spawnEntity(Entity entity, World world, EntityPlayer player) {
      float f = 1.0F;
      float f1 = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * f;
      float f2 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * f;
      double d0 = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)f;
      double d1 = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)f + 1.62D - (double)player.field_70129_M;
      double d2 = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)f;
      Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
      float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
      float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
      float f5 = -MathHelper.cos(-f1 * 0.017453292F);
      float f6 = MathHelper.sin(-f1 * 0.017453292F);
      float f7 = f4 * f5;
      float f8 = f3 * f5;
      double d3 = 5.0D;
      Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
      MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3, vec31, true);
      Vec3 vec32 = player.func_70676_i(f);
      boolean flag = false;
      float f9 = 1.0F;
      List list = world.getEntitiesWithinAABBExcludingEntity(player, player.field_70121_D.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand((double)f9, (double)f9, (double)f9));

      int i;
      for(i = 0; i < list.size(); ++i) {
         Entity j = (Entity)list.get(i);
         if(entity.canBeCollidedWith()) {
            float k = entity.getCollisionBorderSize();
            AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)k, (double)k, (double)k);
            if(axisalignedbb.isVecInside(vec3)) {
               flag = true;
            }
         }
      }

      if(movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
         i = movingobjectposition.blockX;
         int var32 = movingobjectposition.blockY;
         int var33 = movingobjectposition.blockZ;
         if(world.getBlock(i, var32, var33) == Blocks.snow_layer) {
            --var32;
         }

         if(!world.isRemote) {
            world.spawnEntityInWorld(entity);
         }
      }

   }

   public static void drawRect(int x1, int y1, int x2, int y2, int color, float zPos) {
      int j1;
      if(x1 < x2) {
         j1 = x1;
         x1 = x2;
         x2 = j1;
      }

      if(y1 < y2) {
         j1 = y1;
         y1 = y2;
         y2 = j1;
      }

      float f3 = (float)(color >> 24 & 255) / 255.0F;
      float f = (float)(color >> 16 & 255) / 255.0F;
      float f1 = (float)(color >> 8 & 255) / 255.0F;
      float f2 = (float)(color & 255) / 255.0F;
      Tessellator tessellator = Tessellator.instance;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glColor4f(f, f1, f2, f3);
      tessellator.startDrawingQuads();
      tessellator.addVertex((double)x1, (double)y2, (double)zPos);
      tessellator.addVertex((double)x2, (double)y2, (double)zPos);
      tessellator.addVertex((double)x2, (double)y1, (double)zPos);
      tessellator.addVertex((double)x1, (double)y1, (double)zPos);
      tessellator.draw();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
   }

   public static enum Vehicle {

      LANZ("LANZ", 0),
      TRUCK("TRUCK", 1),
      JEEP("JEEP", 2);
      // $FF: synthetic field
      private static final GuiOnlineShop.Vehicle[] $VALUES = new GuiOnlineShop.Vehicle[]{LANZ, TRUCK, JEEP};


      private Vehicle(String var1, int var2) {}

   }
}
