package net.minecraft.server;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IProgressUpdate;

public class MinecraftServer$1 implements IProgressUpdate {

   private long field_96245_b;
   // $FF: synthetic field
   final MinecraftServer mcServer;


   public MinecraftServer$1(MinecraftServer var1) {
      this.mcServer = var1;
      this.field_96245_b = MinecraftServer.getSystemTimeMillis();
   }

   public void displayProgressMessage(String var1) {}

   public void resetProgressAndMessage(String var1) {}

   public void setLoadingProgress(int var1) {
      if(MinecraftServer.getSystemTimeMillis() - this.field_96245_b >= 1000L) {
         this.field_96245_b = MinecraftServer.getSystemTimeMillis();
         MinecraftServer.access$000().info("Converting... " + var1 + "%");
      }

   }

   public void func_146586_a() {}

   public void resetProgresAndWorkingMessage(String var1) {}
}
