package net.minecraft.scoreboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardSaveData;
import net.minecraft.server.MinecraftServer;

public class ServerScoreboard extends Scoreboard {

   private final MinecraftServer scoreboardMCServer;
   private final Set field_96553_b = new HashSet();
   private ScoreboardSaveData field_96554_c;


   public ServerScoreboard(MinecraftServer var1) {
      this.scoreboardMCServer = var1;
   }

   public void func_96536_a(Score var1) {
      super.func_96536_a(var1);
      if(this.field_96553_b.contains(var1.func_96645_d())) {
         this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3CPacketUpdateScore(var1, 0));
      }

      this.func_96551_b();
   }

   public void func_96516_a(String var1) {
      super.func_96516_a(var1);
      this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3CPacketUpdateScore(var1));
      this.func_96551_b();
   }

   public void func_96530_a(int var1, ScoreObjective var2) {
      ScoreObjective var3 = this.func_96539_a(var1);
      super.func_96530_a(var1, var2);
      if(var3 != var2 && var3 != null) {
         if(this.func_96552_h(var3) > 0) {
            this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3DPacketDisplayScoreboard(var1, var2));
         } else {
            this.func_96546_g(var3);
         }
      }

      if(var2 != null) {
         if(this.field_96553_b.contains(var2)) {
            this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3DPacketDisplayScoreboard(var1, var2));
         } else {
            this.func_96549_e(var2);
         }
      }

      this.func_96551_b();
   }

   public boolean func_151392_a(String var1, String var2) {
      if(super.func_151392_a(var1, var2)) {
         ScorePlayerTeam var3 = this.getTeam(var2);
         this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3EPacketTeams(var3, Arrays.asList(new String[]{var1}), 3));
         this.func_96551_b();
         return true;
      } else {
         return false;
      }
   }

   public void removePlayerFromTeam(String var1, ScorePlayerTeam var2) {
      super.removePlayerFromTeam(var1, var2);
      this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3EPacketTeams(var2, Arrays.asList(new String[]{var1}), 4));
      this.func_96551_b();
   }

   public void func_96522_a(ScoreObjective var1) {
      super.func_96522_a(var1);
      this.func_96551_b();
   }

   public void func_96532_b(ScoreObjective var1) {
      super.func_96532_b(var1);
      if(this.field_96553_b.contains(var1)) {
         this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3BPacketScoreboardObjective(var1, 2));
      }

      this.func_96551_b();
   }

   public void func_96533_c(ScoreObjective var1) {
      super.func_96533_c(var1);
      if(this.field_96553_b.contains(var1)) {
         this.func_96546_g(var1);
      }

      this.func_96551_b();
   }

   public void broadcastTeamCreated(ScorePlayerTeam var1) {
      super.broadcastTeamCreated(var1);
      this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3EPacketTeams(var1, 0));
      this.func_96551_b();
   }

   public void broadcastTeamRemoved(ScorePlayerTeam var1) {
      super.broadcastTeamRemoved(var1);
      this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3EPacketTeams(var1, 2));
      this.func_96551_b();
   }

   public void func_96513_c(ScorePlayerTeam var1) {
      super.func_96513_c(var1);
      this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new S3EPacketTeams(var1, 1));
      this.func_96551_b();
   }

   public void func_96547_a(ScoreboardSaveData var1) {
      this.field_96554_c = var1;
   }

   protected void func_96551_b() {
      if(this.field_96554_c != null) {
         this.field_96554_c.markDirty();
      }

   }

   public List func_96550_d(ScoreObjective var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new S3BPacketScoreboardObjective(var1, 0));

      for(int var3 = 0; var3 < 3; ++var3) {
         if(this.func_96539_a(var3) == var1) {
            var2.add(new S3DPacketDisplayScoreboard(var3, var1));
         }
      }

      Iterator var5 = this.func_96534_i(var1).iterator();

      while(var5.hasNext()) {
         Score var4 = (Score)var5.next();
         var2.add(new S3CPacketUpdateScore(var4, 0));
      }

      return var2;
   }

   public void func_96549_e(ScoreObjective var1) {
      List var2 = this.func_96550_d(var1);
      Iterator var3 = this.scoreboardMCServer.getConfigurationManager().playerEntityList.iterator();

      while(var3.hasNext()) {
         EntityPlayerMP var4 = (EntityPlayerMP)var3.next();
         Iterator var5 = var2.iterator();

         while(var5.hasNext()) {
            Packet var6 = (Packet)var5.next();
            var4.playerNetServerHandler.sendPacket(var6);
         }
      }

      this.field_96553_b.add(var1);
   }

   public List func_96548_f(ScoreObjective var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new S3BPacketScoreboardObjective(var1, 1));

      for(int var3 = 0; var3 < 3; ++var3) {
         if(this.func_96539_a(var3) == var1) {
            var2.add(new S3DPacketDisplayScoreboard(var3, var1));
         }
      }

      return var2;
   }

   public void func_96546_g(ScoreObjective var1) {
      List var2 = this.func_96548_f(var1);
      Iterator var3 = this.scoreboardMCServer.getConfigurationManager().playerEntityList.iterator();

      while(var3.hasNext()) {
         EntityPlayerMP var4 = (EntityPlayerMP)var3.next();
         Iterator var5 = var2.iterator();

         while(var5.hasNext()) {
            Packet var6 = (Packet)var5.next();
            var4.playerNetServerHandler.sendPacket(var6);
         }
      }

      this.field_96553_b.remove(var1);
   }

   public int func_96552_h(ScoreObjective var1) {
      int var2 = 0;

      for(int var3 = 0; var3 < 3; ++var3) {
         if(this.func_96539_a(var3) == var1) {
            ++var2;
         }
      }

      return var2;
   }
}
