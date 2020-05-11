package de.ItsAMysterious.mods.reallifemod;

import api.player.model.ModelPlayerAPI;
import api.player.render.RenderPlayerAPI;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Blocks;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import de.ItsAMysterious.mods.reallifemod.api.util.RLMResourceListener;
import de.ItsAMysterious.mods.reallifemod.api.util.TLMCustomCreativeTabs;
import de.ItsAMysterious.mods.reallifemod.config.RealLifeModConfig;
import de.ItsAMysterious.mods.reallifemod.core.handlers.ForgeEventHandler;
import de.ItsAMysterious.mods.reallifemod.core.handlers.GuiHandler;
import de.ItsAMysterious.mods.reallifemod.core.handlers.RLMEventHandler_Common;
import de.ItsAMysterious.mods.reallifemod.core.handlers.RLMTerrainHandler;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RealLifeModPlayerRenderer;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.player.RLMModelPlayerBase;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.player.RLMRenderPlayerBase;
import de.ItsAMysterious.mods.reallifemod.server.RLMCommands;
import de.ItsAMysterious.mods.reallifemod.server.ServerProxy;
import de.ItsAMysterious.mods.reallifemod.server.TutorialBotCommand;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;

@Mod(
   modid = "reallifemod",
   name = "Real Life Mod",
   version = "0.2",
   guiFactory = "de.ItsAMysterious.mods.reallifemod.init.GuiFactory"
)
public class RealLifeMod {

   public static final String ID = "reallifemod";
   public static final String NAME = "Real Life Mod";
   public static final String VERSION = "0.26";
   @Instance("reallifemod")
   public static RealLifeMod instance;
   @SidedProxy(
      clientSide = "de.ItsAMysterious.mods.reallifemod.client.ClientProxy",
      serverSide = "de.ItsAMysterious.mods.reallifemod.server.ServerProxy"
   )
   public static ServerProxy proxy;
   public static Logger log = Logger.getLogger("Minecraft");
   public static ToolMaterial Krupp = EnumHelper.addToolMaterial("Krupp", 4, 3000, 4.0F, 12.0F, 10);
   public static final TLMCustomCreativeTabs TLMAgriculture = new TLMCustomCreativeTabs("reallifemod-agriculture");
   public static final TLMCustomCreativeTabs rlmfood = new TLMCustomCreativeTabs("reallifemod-food") {
      public Item func_78016_d() {
         return RealLifeMod_Items.noodlesoup;
      }
   };
   public static final TLMCustomCreativeTabs RLMTechnologie = new TLMCustomCreativeTabs("reallifemod-technology") {
      public Item func_78016_d() {
         return RealLifeMod_Items.circuitBrd;
      }
   };
   public static final TLMCustomCreativeTabs Furniture = new TLMCustomCreativeTabs("reallifemod-furniture") {
      public Item func_78016_d() {
         return Item.getItemFromBlock(RealLifeMod_Blocks.cupboard);
      }
   };
   public static final TLMCustomCreativeTabs Outdoor = new TLMCustomCreativeTabs("reallifemod-outdoor") {
      public Item func_78016_d() {
         return Item.getItemFromBlock(RealLifeMod_Blocks.oaktree);
      }
   };
   public static final TLMCustomCreativeTabs medicine = new TLMCustomCreativeTabs("reallifemod-medicine") {
      public Item func_78016_d() {
         return RealLifeMod_Items.syringe;
      }
   };
   public static final TLMCustomCreativeTabs TLMEverydaylife = new TLMCustomCreativeTabs("reallifemod-everydalife stuff") {
      public Item func_78016_d() {
         return RealLifeMod_Items.ID;
      }
   };
   public static final TLMCustomCreativeTabs Streets = new TLMCustomCreativeTabs("reallifemod-roads") {
      public Item func_78016_d() {
         return RealLifeMod_Items.Lowry;
      }
   };
   public static final TLMCustomCreativeTabs Work = new TLMCustomCreativeTabs("Weapons and Tools") {
      public Item func_78016_d() {
         return RealLifeMod_Items.ak;
      }
   };
   public static final TLMCustomCreativeTabs Toys = new TLMCustomCreativeTabs("Real Life Mod - Toys") {
      public Item func_78016_d() {
         return RealLifeMod_Items.rattle;
      }
   };
   public static Configuration config;
   public static File Dir;


   public RealLifeMod() {
      Dir = Minecraft.getMinecraft().mcDataDir;
   }

   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      try {
         Class.forName("net/minecraft/entity/Entity$1");
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      proxy.registerTileEntities();
      proxy.setIDs();
      proxy.registerVehicles();
      RealLifeMod_Blocks.defineBlocks();
      RealLifeMod_Items.defineItems();
      config = new Configuration(event.getSuggestedConfigurationFile());
      RealLifeModConfig.syncConfig();
   }

   @EventHandler
   public void init(FMLInitializationEvent event) {
      if(RealLifeModConfig.playermodel) {
         RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RealLifeModPlayerRenderer());
         RenderingRegistry.registerEntityRenderingHandler(EntityOtherPlayerMP.class, new RealLifeModPlayerRenderer());
      } else {
         RenderPlayerAPI.register("reallifemod", RLMRenderPlayerBase.class);
         ModelPlayerAPI.register("reallifemod", RLMModelPlayerBase.class);
      }

      FMLCommonHandler.instance().bus().register(instance);
      MinecraftForge.EVENT_BUS.register(proxy);
      MinecraftForge.EVENT_BUS.register(new ServerProxy());
      MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
      MinecraftForge.EVENT_BUS.register(new RLMTerrainHandler());
      MinecraftForge.EVENT_BUS.register(new RLMResourceListener());
      FMLCommonHandler.instance().bus().register(new RLMEventHandler_Common());
      NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
      proxy.doStartUp();
   }

   @EventHandler
   public void PostInit(FMLPostInitializationEvent event) {
      proxy.doPostInit();
      config.load();
   }

   @SubscribeEvent
   public void onConfigChanged(OnConfigChangedEvent event) {
      if(event.modID.equals("reallifemod")) {
         RealLifeModConfig.syncConfig();
      }

   }

   @EventHandler
   public void onServerStart(FMLServerStartingEvent event) {
      event.registerServerCommand(new RLMCommands());
      event.registerServerCommand(new TutorialBotCommand());
   }

   public void createRequiredFolders() {
      File mc = Minecraft.getMinecraft().mcDataDir;
      File folder = new File(mc, "RealLifeMod");

      try {
         if(!folder.exists()) {
            folder.createNewFile();
         }
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }

   public static void log(String string) {
      log.info("Real Life Mod" + string);
   }

}
