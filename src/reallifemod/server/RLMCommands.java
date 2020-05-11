package de.ItsAMysterious.mods.reallifemod.server;

import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class RLMCommands implements ICommand {

   private final List aliases = new ArrayList();


   public RLMCommands() {
      this.aliases.add("freeMoney");
   }

   public int compareTo(Object o) {
      return 0;
   }

   public String func_71517_b() {
      return "freeMoney";
   }

   public String func_71518_a(ICommandSender var1) {
      return "freeMoney";
   }

   public List func_71514_a() {
      return this.aliases;
   }

   public void func_71515_b(ICommandSender sender, String[] argString) {
      if(sender instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)sender;
         financialProps var10000 = (financialProps)player.getExtendedProperties("financialProps");
         financialProps.Cash += 1000.0D;
      }

   }

   public boolean func_71519_b(ICommandSender var1) {
      return true;
   }

   public List func_71516_a(ICommandSender var1, String[] var2) {
      return null;
   }

   public boolean func_82358_a(String[] var1, int var2) {
      return false;
   }
}
