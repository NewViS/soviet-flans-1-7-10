package de.ItsAMysterious.mods.reallifemod.init;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionCategoryElement;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionGuiHandler;
import de.ItsAMysterious.mods.reallifemod.init.ConfigGui;
import java.util.Set;
import net.minecraft.client.Minecraft;

public class GuiFactory implements IModGuiFactory {

   public void initialize(Minecraft minecraftInstance) {}

   public Class mainConfigGuiClass() {
      return ConfigGui.class;
   }

   public Set runtimeGuiCategories() {
      return null;
   }

   public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
      return null;
   }
}
