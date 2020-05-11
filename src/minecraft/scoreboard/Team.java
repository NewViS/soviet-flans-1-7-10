package net.minecraft.scoreboard;


public abstract class Team {

   public boolean isSameTeam(Team var1) {
      return var1 == null?false:this == var1;
   }

   public abstract String getRegisteredName();

   public abstract String formatString(String var1);

   public abstract boolean func_98297_h();

   public abstract boolean getAllowFriendlyFire();
}
