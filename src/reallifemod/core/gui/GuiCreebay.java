package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Blocks;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiCreebay extends RLM_Gui {

   public static int GUI_ID = 57;
   public int currentEntry = 0;
   public int centerX;
   public int centerY;
   private float itemRotation = 0.0F;
   private EntityItem item;
   public Item theitem;
   private float currentPrice = 0.0F;
   private String state;


   public void func_73866_w_() {
      this.item = new EntityItem(this.field_146297_k.theWorld);
      this.item.setEntityItemStack(new ItemStack(RealLifeMod_Items.ak));
      this.theitem = new Item();
      this.state = "item.";
      super.func_73866_w_();
   }

   public void func_73863_a(int x, int y, float f) {
      this.centerX = this.field_146294_l / 2;
      this.centerY = this.field_146295_m / 2;
      this.field_146297_k.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/NameChangeGui.png"));
      this.func_73729_b(this.centerX - 100, this.centerY - 50, 0, 0, 200, 100);
      this.drawItem();
      --this.itemRotation;
      super.func_73863_a(x, y, f);
      this.field_146292_n.clear();
      this.field_146292_n.add(new GuiButton(0, this.centerX + 55, this.centerY + 25, 40, 20, "Buy!"));
      this.field_146292_n.add(new GuiButton(1, this.centerX + 55, this.centerY - 45, 15, 20, "<"));
      this.field_146292_n.add(new GuiButton(2, this.centerX + 80, this.centerY - 45, 15, 20, ">"));
      this.drawString(EnumChatFormatting.DARK_GREEN + "Price: " + EnumChatFormatting.WHITE + this.currentPrice + "$", this.centerX - 90, this.centerY - 25, Color.white.getRGB());
      if(this.item.getEntityItem().getItem().getUnlocalizedName().contains("item.")) {
         this.drawString(EnumChatFormatting.UNDERLINE + this.item.getEntityItem().getItem().getUnlocalizedName().split("item.")[1], this.centerX - 90, this.centerY - 40, Color.white.getRGB());
      } else if(this.item.getEntityItem().getItem().getUnlocalizedName().contains("block.")) {
         this.drawString(EnumChatFormatting.UNDERLINE + this.item.getEntityItem().getItem().getUnlocalizedName().split("block.")[1], this.centerX - 90, this.centerY - 40, Color.white.getRGB());
      }

      this.drawString("current item: " + this.currentEntry + "/" + (RealLifeMod_Items.itemList.size() + RealLifeMod_Blocks.rlmBlockList.size()), this.centerX - 90, this.centerY + 35, Color.white.getRGB());
   }

   public void func_73876_c() {
      super.func_73876_c();
   }

   public void func_146284_a(GuiButton button) {
      financialProps props = (financialProps)this.field_146297_k.thePlayer.getExtendedProperties("financialProps");
      if(button.id == 0) {
         if(financialProps.Cash - (double)this.currentPrice > 0.0D) {
            financialProps.Cash -= (double)this.currentPrice;
            this.field_146297_k.thePlayer.field_71071_by.addItemStackToInventory(new ItemStack(Item.getItemById(Item.getIdFromItem(this.theitem))));
            this.field_146297_k.thePlayer.field_71071_by.inventoryChanged = true;
         } else {
            this.field_146297_k.thePlayer.func_145747_a(new ChatComponentText("You don\'t have enough Money for this!"));
         }
      }

      ExtendedItem theItem;
      if(button.id == 1) {
         if(this.currentEntry > 0) {
            --this.currentEntry;
         } else {
            this.currentEntry = RealLifeMod_Items.itemList.size() + RealLifeMod_Blocks.rlmBlockList.size();
         }

         if(this.currentEntry > RealLifeMod_Items.itemList.size() && this.currentEntry - RealLifeMod_Items.itemList.size() < RealLifeMod_Blocks.rlmBlockList.size()) {
            this.item.setEntityItemStack(new ItemStack(ExtendedItem.func_150898_a((Block)RealLifeMod_Blocks.rlmBlockList.get(this.currentEntry - RealLifeMod_Items.itemList.size()))));
            this.theitem = ExtendedItem.func_150898_a((Block)RealLifeMod_Blocks.rlmBlockList.get(this.currentEntry - RealLifeMod_Items.itemList.size()));
         } else if(this.currentEntry < RealLifeMod_Items.itemList.size()) {
            this.item.setEntityItemStack(new ItemStack((Item)RealLifeMod_Items.itemList.get(this.currentEntry)));
            this.theitem = (Item)RealLifeMod_Items.itemList.get(this.currentEntry);
         } else {
            this.item.setEntityItemStack(new ItemStack(Items.apple));
            this.theitem = Items.apple;
         }

         if(this.item.getEntityItem().getItem() instanceof ExtendedItem || this.item.getEntityItem().getItem() instanceof ExtendedItem) {
            theItem = (ExtendedItem)this.item.getEntityItem().getItem();
            this.currentPrice = (float)theItem.price();
         }
      }

      if(button.id == 2) {
         if(this.currentEntry < RealLifeMod_Items.itemList.size() + RealLifeMod_Blocks.rlmBlockList.size()) {
            ++this.currentEntry;
         } else {
            this.currentEntry = 0;
         }

         if(this.currentEntry > RealLifeMod_Items.itemList.size() && this.currentEntry - RealLifeMod_Items.itemList.size() < RealLifeMod_Blocks.rlmBlockList.size()) {
            this.item.setEntityItemStack(new ItemStack(ExtendedItem.func_150898_a((Block)RealLifeMod_Blocks.rlmBlockList.get(this.currentEntry - RealLifeMod_Items.itemList.size()))));
            this.theitem = ExtendedItem.func_150898_a((Block)RealLifeMod_Blocks.rlmBlockList.get(this.currentEntry - RealLifeMod_Items.itemList.size()));
         } else if(this.currentEntry < RealLifeMod_Items.itemList.size()) {
            this.item.setEntityItemStack(new ItemStack((Item)RealLifeMod_Items.itemList.get(this.currentEntry)));
            this.theitem = (Item)RealLifeMod_Items.itemList.get(this.currentEntry);
         } else {
            this.item.setEntityItemStack(new ItemStack(Items.apple));
         }

         if(this.item.getEntityItem().getItem() instanceof ExtendedItem || this.item.getEntityItem().getItem() instanceof ExtendedItem) {
            theItem = (ExtendedItem)this.item.getEntityItem().getItem();
            this.currentPrice = (float)theItem.price();
         }
      }

      this.func_73878_a(true, button.id);
   }

   private void drawItem() {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)(this.centerX + 70), (float)(this.centerY + 12), 15.0F);
      GL11.glRotatef(this.itemRotation, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glPushMatrix();
      GL11.glScalef(50.0F, 50.0F, 50.0F);
      GL11.glPushMatrix();
      GL11.glEnable(3008);
      GL11.glDisable(2884);
      RenderManager.instance.renderEntityWithPosYaw(this.item, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }

}
