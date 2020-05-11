package de.ItsAMysterious.mods.reallifemod.api.enums;

import net.minecraft.entity.player.EntityPlayer;

public class Job {

   public static String name;
   private EntityPlayer jobber;


   public Job(String Jobname) {
      if(Jobname.equalsIgnoreCase(Job.names.valueOf(Jobname).toString())) {
         name = Jobname;
      }

   }

   public static String name() {
      return name;
   }

   public void setName(String name) {
      name = name;
   }

   public static enum names {

      MINOR("MINOR", 0),
      POLICEMEN("POLICEMEN", 1),
      FIREMEN("FIREMEN", 2);
      // $FF: synthetic field
      private static final Job.names[] $VALUES = new Job.names[]{MINOR, POLICEMEN, FIREMEN};


      private names(String var1, int var2) {}

   }
}
