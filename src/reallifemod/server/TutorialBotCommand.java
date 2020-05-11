package de.ItsAMysterious.mods.reallifemod.server;

import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityRobot;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class TutorialBotCommand implements ICommand {

   private final List aliases = new ArrayList();


   public TutorialBotCommand() {
      this.aliases.add("spawnHelperbot");
   }

   public int compareTo(Object o) {
      return 0;
   }

   public String func_71517_b() {
      return "spawnHelperbot";
   }

   public String func_71518_a(ICommandSender var1) {
      return "spawnHelperbot";
   }

   public List func_71514_a() {
      return this.aliases;
   }

   public void func_71515_b(ICommandSender sender, String[] argString) {
      if(sender instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)sender;
         if(!player.field_70170_p.isRemote) {
            EntityRobot bot = new EntityRobot(player.field_70170_p, player.field_70165_t + 1.0D, player.field_70163_u, player.field_70161_v);
            player.field_70170_p.spawnEntityInWorld(bot);
         }
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
