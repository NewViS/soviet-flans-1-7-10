package de.ItsAMysterious.mods.reallifemod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatComponentTranslation;

public class UpdateChecker extends Thread {

   private int revision = 1;


   public void run() {
      String name = "§2Real Life Mod§f";
      String link = "§9§nClick here";
      String text = name + " installed. More info at " + link;
      if(this.hasUpdate()) {
         text = name + '\u00a7' + "4 update available " + link;
      }

      EntityClientPlayerMP player;
      try {
         player = Minecraft.getMinecraft().thePlayer;
      } catch (NoSuchMethodError var7) {
         return;
      }

      while((player = Minecraft.getMinecraft().thePlayer) == null) {
         try {
            Thread.sleep(2000L);
         } catch (InterruptedException var6) {
            var6.printStackTrace();
         }
      }

      ChatComponentTranslation message = new ChatComponentTranslation(text, new Object[0]);
      message.func_150256_b().setChatClickEvent(new ClickEvent(Action.OPEN_URL, "http://cloudolympus.ca/projects/rlm/"));
      player.addChatComponentMessage(message);
   }

   private boolean hasUpdate() {
      String inputLine;
      try {
         URL e = new URL("http://minecraft.curseforge.com/mc-mods/227922-real-life-mod-everydaylife-in-minecraft/files/2235059/RealLifeMod.txt");
         URLConnection yc = e.openConnection();
         BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
         inputLine = in.readLine();
         if(inputLine == null) {
            return false;
         }
      } catch (Exception var6) {
         return false;
      }

      int newVersion = Integer.parseInt(inputLine);
      return this.revision < newVersion;
   }
}
