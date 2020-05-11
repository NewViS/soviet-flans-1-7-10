package net.minecraft.command.server;

import io.netty.buffer.ByteBuf;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IChatComponent$Serializer;
import net.minecraft.world.World;

public abstract class CommandBlockLogic implements ICommandSender {

   private static final SimpleDateFormat field_145766_a = new SimpleDateFormat("HH:mm:ss");
   private int field_145764_b;
   private boolean field_145765_c = true;
   private IChatComponent field_145762_d = null;
   private String field_145763_e = "";
   private String field_145761_f = "@";


   public int func_145760_g() {
      return this.field_145764_b;
   }

   public IChatComponent func_145749_h() {
      return this.field_145762_d;
   }

   public void func_145758_a(NBTTagCompound var1) {
      var1.setString("Command", this.field_145763_e);
      var1.setInteger("SuccessCount", this.field_145764_b);
      var1.setString("CustomName", this.field_145761_f);
      if(this.field_145762_d != null) {
         var1.setString("LastOutput", IChatComponent$Serializer.func_150696_a(this.field_145762_d));
      }

      var1.setBoolean("TrackOutput", this.field_145765_c);
   }

   public void func_145759_b(NBTTagCompound var1) {
      this.field_145763_e = var1.getString("Command");
      this.field_145764_b = var1.getInteger("SuccessCount");
      if(var1.hasKey("CustomName", 8)) {
         this.field_145761_f = var1.getString("CustomName");
      }

      if(var1.hasKey("LastOutput", 8)) {
         this.field_145762_d = IChatComponent$Serializer.func_150699_a(var1.getString("LastOutput"));
      }

      if(var1.hasKey("TrackOutput", 1)) {
         this.field_145765_c = var1.getBoolean("TrackOutput");
      }

   }

   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return var1 <= 2;
   }

   public void func_145752_a(String var1) {
      this.field_145763_e = var1;
   }

   public String func_145753_i() {
      return this.field_145763_e;
   }

   public void func_145755_a(World var1) {
      if(var1.isRemote) {
         this.field_145764_b = 0;
      }

      MinecraftServer var2 = MinecraftServer.getServer();
      if(var2 != null && var2.isCommandBlockEnabled()) {
         ICommandManager var3 = var2.getCommandManager();
         this.field_145764_b = var3.executeCommand(this, this.field_145763_e);
      } else {
         this.field_145764_b = 0;
      }

   }

   public String getCommandSenderName() {
      return this.field_145761_f;
   }

   public IChatComponent func_145748_c_() {
      return new ChatComponentText(this.getCommandSenderName());
   }

   public void func_145754_b(String var1) {
      this.field_145761_f = var1;
   }

   public void addChatMessage(IChatComponent var1) {
      if(this.field_145765_c && this.getEntityWorld() != null && !this.getEntityWorld().isRemote) {
         this.field_145762_d = (new ChatComponentText("[" + field_145766_a.format(new Date()) + "] ")).appendSibling(var1);
         this.func_145756_e();
      }

   }

   public abstract void func_145756_e();

   public abstract int func_145751_f();

   public abstract void func_145757_a(ByteBuf var1);

   public void func_145750_b(IChatComponent var1) {
      this.field_145762_d = var1;
   }

}
