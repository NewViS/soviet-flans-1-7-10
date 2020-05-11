package net.minecraft.world;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.GameRules$Value;

public class GameRules {

   private TreeMap theGameRules = new TreeMap();


   public GameRules() {
      this.addGameRule("doFireTick", "true");
      this.addGameRule("mobGriefing", "true");
      this.addGameRule("keepInventory", "false");
      this.addGameRule("doMobSpawning", "true");
      this.addGameRule("doMobLoot", "true");
      this.addGameRule("doTileDrops", "true");
      this.addGameRule("commandBlockOutput", "true");
      this.addGameRule("naturalRegeneration", "true");
      this.addGameRule("doDaylightCycle", "true");
   }

   public void addGameRule(String var1, String var2) {
      this.theGameRules.put(var1, new GameRules$Value(var2));
   }

   public void setOrCreateGameRule(String var1, String var2) {
      GameRules$Value var3 = (GameRules$Value)this.theGameRules.get(var1);
      if(var3 != null) {
         var3.setValue(var2);
      } else {
         this.addGameRule(var1, var2);
      }

   }

   public String getGameRuleStringValue(String var1) {
      GameRules$Value var2 = (GameRules$Value)this.theGameRules.get(var1);
      return var2 != null?var2.getGameRuleStringValue():"";
   }

   public boolean getGameRuleBooleanValue(String var1) {
      GameRules$Value var2 = (GameRules$Value)this.theGameRules.get(var1);
      return var2 != null?var2.getGameRuleBooleanValue():false;
   }

   public NBTTagCompound writeGameRulesToNBT() {
      NBTTagCompound var1 = new NBTTagCompound();
      Iterator var2 = this.theGameRules.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         GameRules$Value var4 = (GameRules$Value)this.theGameRules.get(var3);
         var1.setString(var3, var4.getGameRuleStringValue());
      }

      return var1;
   }

   public void readGameRulesFromNBT(NBTTagCompound var1) {
      Set var2 = var1.func_150296_c();
      Iterator var3 = var2.iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         String var6 = var1.getString(var4);
         this.setOrCreateGameRule(var4, var6);
      }

   }

   public String[] getRules() {
      return (String[])this.theGameRules.keySet().toArray(new String[0]);
   }

   public boolean hasRule(String var1) {
      return this.theGameRules.containsKey(var1);
   }
}
