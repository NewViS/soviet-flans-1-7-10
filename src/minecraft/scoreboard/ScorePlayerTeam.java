package net.minecraft.scoreboard;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;

public class ScorePlayerTeam extends Team {

   private final Scoreboard theScoreboard;
   private final String field_96675_b;
   private final Set membershipSet = new HashSet();
   private String teamNameSPT;
   private String namePrefixSPT = "";
   private String colorSuffix = "";
   private boolean allowFriendlyFire = true;
   private boolean canSeeFriendlyInvisibles = true;


   public ScorePlayerTeam(Scoreboard var1, String var2) {
      this.theScoreboard = var1;
      this.field_96675_b = var2;
      this.teamNameSPT = var2;
   }

   public String getRegisteredName() {
      return this.field_96675_b;
   }

   public String func_96669_c() {
      return this.teamNameSPT;
   }

   public void setTeamName(String var1) {
      if(var1 == null) {
         throw new IllegalArgumentException("Name cannot be null");
      } else {
         this.teamNameSPT = var1;
         this.theScoreboard.broadcastTeamRemoved(this);
      }
   }

   public Collection getMembershipCollection() {
      return this.membershipSet;
   }

   public String getColorPrefix() {
      return this.namePrefixSPT;
   }

   public void setNamePrefix(String var1) {
      if(var1 == null) {
         throw new IllegalArgumentException("Prefix cannot be null");
      } else {
         this.namePrefixSPT = var1;
         this.theScoreboard.broadcastTeamRemoved(this);
      }
   }

   public String getColorSuffix() {
      return this.colorSuffix;
   }

   public void setNameSuffix(String var1) {
      if(var1 == null) {
         throw new IllegalArgumentException("Suffix cannot be null");
      } else {
         this.colorSuffix = var1;
         this.theScoreboard.broadcastTeamRemoved(this);
      }
   }

   public String formatString(String var1) {
      return this.getColorPrefix() + var1 + this.getColorSuffix();
   }

   public static String formatPlayerName(Team var0, String var1) {
      return var0 == null?var1:var0.formatString(var1);
   }

   public boolean getAllowFriendlyFire() {
      return this.allowFriendlyFire;
   }

   public void setAllowFriendlyFire(boolean var1) {
      this.allowFriendlyFire = var1;
      this.theScoreboard.broadcastTeamRemoved(this);
   }

   public boolean func_98297_h() {
      return this.canSeeFriendlyInvisibles;
   }

   public void setSeeFriendlyInvisiblesEnabled(boolean var1) {
      this.canSeeFriendlyInvisibles = var1;
      this.theScoreboard.broadcastTeamRemoved(this);
   }

   public int func_98299_i() {
      int var1 = 0;
      if(this.getAllowFriendlyFire()) {
         var1 |= 1;
      }

      if(this.func_98297_h()) {
         var1 |= 2;
      }

      return var1;
   }

   public void func_98298_a(int var1) {
      this.setAllowFriendlyFire((var1 & 1) > 0);
      this.setSeeFriendlyInvisiblesEnabled((var1 & 2) > 0);
   }
}
