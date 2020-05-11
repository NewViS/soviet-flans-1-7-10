package de.ItsAMysterious.mods.reallifemod.init;

import cpw.mods.fml.client.config.GuiConfig;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.config.RealLifeModConfig;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiChangelog;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import org.lwjgl.input.Keyboard;

public class ConfigGui extends GuiConfig {

   public int openchangelog;


   public ConfigGui(GuiScreen parent) {
      super(parent, (new ConfigElement(RealLifeMod.config.getCategory("general"))).getChildElements(), "Real Life Mod", false, false, GuiConfig.getAbridgedConfigPath(RealLifeMod.config.toString()));
   }

   public void func_73866_w_() {
      super.func_73866_w_();
   }

   public void func_146281_b() {
      this.entryList.onGuiClosed();
      if(this.openchangelog != 1) {
         if(this.configID != null) {
            GuiConfig parentGuiConfig = (GuiConfig)this.parentScreen;
            parentGuiConfig.needsRefresh = true;
            parentGuiConfig.func_73866_w_();
         }

         if(!(this.parentScreen instanceof GuiConfig)) {
            Keyboard.enableRepeatEvents(false);
         }
      }

   }

   public void func_146284_a(GuiButton button) {
      RealLifeModConfig.syncConfig();
      super.func_146284_a(button);
      if(button.id == 10) {
         this.openchangelog = 1;
         this.field_146297_k.displayGuiScreen(new GuiChangelog(this));
      }

      RealLifeMod.config.save();
      RealLifeModConfig.syncConfig();
   }
}
