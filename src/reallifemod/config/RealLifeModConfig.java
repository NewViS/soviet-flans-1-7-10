package de.ItsAMysterious.mods.reallifemod.config;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import net.minecraftforge.common.config.Configuration;

public class RealLifeModConfig {

   public static boolean mouseSteering;
   public static boolean minecraftstyle;
   public static boolean playermodel;
   public static boolean showHUD;
   public static boolean MOUSESTEERING_DEFAULT = false;
   public static boolean MINECRAFTSTYLE_DEFAULT = false;
   public static boolean PLAYERMODEL_DEFAULT = true;
   public static final String mousesteeringname = "En-/disables steering with mouse when flying";
   public static final String minecraftstylename = "Enable Minecraftstyled 3D-models";
   public static final String playermodelname = "Enable HD-Playermodel";


   public static void syncConfig() {
      StringBuilder var10000 = new StringBuilder();
      Configuration var10001 = RealLifeMod.config;
      var10000 = var10000.append("general");
      var10001 = RealLifeMod.config;
      String FEATURES = var10000.append(".").append("Features").toString();
      RealLifeMod.config.addCustomCategoryComment(FEATURES, "Enable or disable different features");
      mouseSteering = RealLifeMod.config.get(FEATURES, "En-/disables steering with mouse when flying", MOUSESTEERING_DEFAULT).getBoolean(MOUSESTEERING_DEFAULT);
      var10000 = new StringBuilder();
      var10001 = RealLifeMod.config;
      var10000 = var10000.append("general");
      var10001 = RealLifeMod.config;
      String THREED = var10000.append(".").append("3D-Settings").toString();
      RealLifeMod.config.addCustomCategoryComment(THREED, "Change 3D setting for Real Life Mod");
      minecraftstyle = RealLifeMod.config.get(THREED, "Enable Minecraftstyled 3D-models", MINECRAFTSTYLE_DEFAULT).getBoolean(MINECRAFTSTYLE_DEFAULT);
      playermodel = RealLifeMod.config.get(THREED, "Enable HD-Playermodel", PLAYERMODEL_DEFAULT).getBoolean(PLAYERMODEL_DEFAULT);
      RealLifeMod.config.save();
   }

}
