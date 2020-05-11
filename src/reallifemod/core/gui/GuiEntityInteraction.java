package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.ReallifemodNPC;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class GuiEntityInteraction extends RLM_Gui {

   public static final int GUI_ID = 61;
   private Entity theEntity;
   private Entity actor;


   public GuiEntityInteraction(Entity actor, Entity entity) {
      this.actor = actor;
      this.theEntity = entity;
   }

   public boolean func_73868_f() {
      return false;
   }

   public void func_73866_w_() {
      if(this.theEntity instanceof EntityPlayer) {
         this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 125, this.field_146295_m / 2 - 30, 100, 20, "Send Mariage Request"));
         this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 - 50, this.field_146295_m / 2, 100, 20, "Trade"));
         this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 2, this.field_146295_m / 2, 100, 20, "See ya!"));
      } else if(this.theEntity instanceof EntitySheep) {
         this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 75, this.field_146295_m / 2 - 15, 150, 20, "Shear"));
         this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 - 75, this.field_146295_m / 2 + 5, 150, 20, "Stroke"));
         this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 2 - 75, this.field_146295_m / 2 + 25, 150, 20, "Give a name"));
      }

   }

   public void func_73863_a(int par1, int par2, float f) {
      super.func_73863_a(par1, par2, f);
      if(this.theEntity != null && this.theEntity instanceof EntityPlayer) {
         RealLifeProperties props = (RealLifeProperties)this.theEntity.getExtendedProperties("RealLifeProperties");
         this.drawCenteredString("Interact with" + RealLifeProperties.name, this.field_146294_l / 2, this.field_146295_m / 2 - 75, Color.white.getRGB());
      } else if(!(this.theEntity instanceof EntityLiving)) {
         this.drawCenteredString("Interact with anonymus", this.field_146294_l / 2, this.field_146295_m / 2 - 75, Color.white.getRGB());
      } else {
         this.drawCenteredString("Interact with " + EnumChatFormatting.GOLD + this.theEntity.getCommandSenderName(), this.field_146294_l / 2, this.field_146295_m / 2 - 75, Color.white.getRGB());
      }

   }

   public void func_146284_a(GuiButton button) {
      super.func_146284_a(button);
      if(this.theEntity instanceof EntityPlayer) {
         if(button.id == 0) {
            if(this.theEntity instanceof EntityPlayer && this.actor instanceof EntityPlayer) {
               RealLifeProperties s = (RealLifeProperties)this.theEntity.getExtendedProperties("RealLifeProperties");
               s.requestMariage((EntityPlayer)this.actor);
            } else if(this.theEntity instanceof ReallifemodNPC && ((ReallifemodNPC)this.theEntity).sendMariageRequest((EntityPlayer)this.actor)) {
               RealLifeProperties.get((EntityPlayer)this.actor).partner = ((ReallifemodNPC)this.theEntity).func_70005_c_();
            }
         }

         if(button.id == 2) {
            this.field_146297_k.displayGuiScreen((GuiScreen)null);
         }
      } else if(this.theEntity instanceof EntitySheep) {
         EntitySheep s1 = (EntitySheep)this.theEntity;
         if(button.id == 0 && Minecraft.getMinecraft().thePlayer.field_71075_bZ.isCreativeMode) {
            if(s1.isShearable(new ItemStack(Items.shears), s1.field_70170_p, s1.field_70176_ah, s1.field_70162_ai, s1.field_70164_aj)) {
               if(this.actor instanceof EntityPlayer) {
                  ArrayList stacks = ((EntitySheep)this.theEntity).onSheared(new ItemStack(Items.shears, 10), this.theEntity.worldObj, (int)this.theEntity.posX, (int)this.theEntity.posY, (int)this.theEntity.posZ, 1);
                  Iterator var4 = stacks.iterator();

                  while(var4.hasNext()) {
                     ItemStack stack = (ItemStack)var4.next();
                     ((EntityPlayer)this.actor).inventory.addItemStackToInventory(stack);
                  }
               }
            } else {
               Minecraft.getMinecraft().thePlayer.func_146105_b(new ChatComponentText("Not available in survival!"));
            }

            if(button.id == 1) {
               Minecraft.getMinecraft().thePlayer.func_146105_b(new ChatComponentText("This has no function yet!!"));
            }

            if(button.id == 2) {
               Minecraft.getMinecraft().thePlayer.func_146105_b(new ChatComponentText("This has no function yet!!"));
            }
         }

         this.func_73878_a(true, button.id);
      }

   }
}
