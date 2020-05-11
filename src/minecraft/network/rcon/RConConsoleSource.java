package net.minecraft.network.rcon;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class RConConsoleSource implements ICommandSender {

   public static final RConConsoleSource field_70010_a = new RConConsoleSource();
   private StringBuffer field_70009_b = new StringBuffer();


   public String getCommandSenderName() {
      return "Rcon";
   }

   public IChatComponent func_145748_c_() {
      return new ChatComponentText(this.getCommandSenderName());
   }

   public void addChatMessage(IChatComponent var1) {
      this.field_70009_b.append(var1.getUnformattedText());
   }

   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return true;
   }

   public ChunkCoordinates getPlayerCoordinates() {
      return new ChunkCoordinates(0, 0, 0);
   }

   public World getEntityWorld() {
      return MinecraftServer.getServer().getEntityWorld();
   }

}
