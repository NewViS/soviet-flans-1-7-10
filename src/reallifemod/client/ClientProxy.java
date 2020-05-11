package de.ItsAMysterious.mods.reallifemod.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Blocks;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Recipes;
import de.ItsAMysterious.mods.reallifemod.UpdateChecker;
import de.ItsAMysterious.mods.reallifemod.api.entity.EntitySeat;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.api.util.RLMResourceListener;
import de.ItsAMysterious.mods.reallifemod.core.entities.cars.EntityJeep;
import de.ItsAMysterious.mods.reallifemod.core.entities.cars.EntityRidingmower;
import de.ItsAMysterious.mods.reallifemod.core.entities.cars.VehicleData;
import de.ItsAMysterious.mods.reallifemod.core.entitys.EntityFallingOak;
import de.ItsAMysterious.mods.reallifemod.core.entitys.EntityFirFalling;
import de.ItsAMysterious.mods.reallifemod.core.entitys.EntityFlyingBlock;
import de.ItsAMysterious.mods.reallifemod.core.entitys.EntityLawnMower;
import de.ItsAMysterious.mods.reallifemod.core.entitys.bullets.EntityBullet;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityBaby;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityLanz;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityRobot;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.ReallifemodNPC;
import de.ItsAMysterious.mods.reallifemod.core.entitys.particles.EntityAdvancedFlameFX;
import de.ItsAMysterious.mods.reallifemod.core.entitys.particles.EntityDrop;
import de.ItsAMysterious.mods.reallifemod.core.enums.EnumDress;
import de.ItsAMysterious.mods.reallifemod.core.gui.RealLifeMod_Overlay;
import de.ItsAMysterious.mods.reallifemod.core.handlers.TLMKeysHandler;
import de.ItsAMysterious.mods.reallifemod.core.items.industrial.ItemCoke;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.AdaptiveRenderSpider;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderAdvancedFlame;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderBaby;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderBullet;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderDrop;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderFallingTree;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderFlyingBlock;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderJeep;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderLanz;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderLawnmower;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderNPC;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderRidingmower;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderRobot;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderTGX;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderTrailer;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderVehicle;
import de.ItsAMysterious.mods.reallifemod.core.rendering.items.AK47Renderer;
import de.ItsAMysterious.mods.reallifemod.core.rendering.items.GenericBlockItemRenderer;
import de.ItsAMysterious.mods.reallifemod.core.rendering.items.RPGRenderer;
import de.ItsAMysterious.mods.reallifemod.core.rendering.items.uziRenderer;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.AtmTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.BabybedTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.BankTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.BilboardTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.BlastfurnaceTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.CabinetTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.ChairTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.ChristmaspyramidTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.ChristmastreeTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.ComputerTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.CookingRodTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.CrashbarrierTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.CupboardTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.DeskTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.DishwasherTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.DoorwreathTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.DrawerTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.DrinksmachineTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.EspressoTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.FirTreeTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.FireplaceTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.FishtankTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.FountainTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.FreezerTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.FridgeTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.GrowpotTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.HeatingTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.LanternTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.NeonlampTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.OakTreeTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.PillarTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.RadioTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.RailingTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.RooftileTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.ShelfTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.ShowcaseTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.SideboardTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.SinkTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.TableTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.TelevisionTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.ToasterTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.ToiletTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.TrafficlightTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.UrinalTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.WashbasinTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.WindowTER;
import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityTrailer;
import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityTruck;
import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityVehicle;
import de.ItsAMysterious.mods.reallifemod.core.tiles.BabybedTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.BankTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.BilboardTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.BlastfurnaceTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.CabinetTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ChairTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ChristmasTreeTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ChristmaspyramidTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ComputerTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.CookingRodTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.CrashbarrierTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.CupboardTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.DeskTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.DishwasherTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.DoorwreathTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.DrawerTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.DrinksmachineTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.EspressoTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FirTreeTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FireplaceTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FishtankTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FountainTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FreezerTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FridgeTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.GrowpotTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.HeatingTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.LanternTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.NeonlampTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.OakTreeTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.PillarTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.PissoirTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.RadioTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.RailingTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.RooftileTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ShelfTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ShowcaseTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.SideboardTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.SinkTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.TableTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.TileEntityTV;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ToasterTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ToiletTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.TrafficlightTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.TreeTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.WashbasinTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.WindowTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.atmTE;
import de.ItsAMysterious.mods.reallifemod.items.VehicleItem;
import de.ItsAMysterious.mods.reallifemod.server.ServerProxy;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.ExplosionEvent;

public class ClientProxy extends ServerProxy {

   public static boolean isPlacingTable;
   public static int bedTime = 0;
   public static KeyBinding character;
   public static KeyBinding Key_Phone;
   public static KeyBinding Key_Horn;
   public static KeyBinding Key_CarInv;
   public static KeyBinding Key_VehicleSettings;
   public static KeyBinding Key_Info;
   public static boolean rattling;
   private int modEntityID;
   private final Minecraft mc = Minecraft.getMinecraft();


   public ClientProxy() {
      this.createFolders();
      this.registerKeybindings();
      File dir = Minecraft.getMinecraft().mcDataDir;
      RealLifeMod.Dir = new File(dir, "reallifemod");
      this.createFolders();
      FMLCommonHandler.instance().bus().register(new TLMKeysHandler());
   }

   public void registerKeybindings() {
      character = new KeyBinding("Open chararcter setup", 38, "Real Life Mod");
      Key_Phone = new KeyBinding("Open Key_Phone", 25, "Real Life Mod");
      Key_Horn = new KeyBinding("Horn", 23, "Real Life Mod");
      Key_CarInv = new KeyBinding("Vehicle Inventory", 19, "Real Life Mod");
      Key_VehicleSettings = new KeyBinding("Modify Vehicle", 50, "Real Life Mod");
      Key_Info = new KeyBinding("Help and Info", 22, "Real Life Mod");
      ClientRegistry.registerKeyBinding(character);
      ClientRegistry.registerKeyBinding(Key_Phone);
      ClientRegistry.registerKeyBinding(Key_Horn);
      ClientRegistry.registerKeyBinding(Key_CarInv);
      ClientRegistry.registerKeyBinding(Key_VehicleSettings);
      ClientRegistry.registerKeyBinding(Key_Info);
   }

   public void doStartUp() {
      this.registerBlockHandlers();
      this.registerRenderThings();
      this.registerEntitys();
      this.registerThings();
      RealLifeMod_Recipes.addRecipes();
      RealLifeMod_Recipes.addSmeltings();
   }

   public void registerBlockHandlers() {
      super.registerBlockHandlers();
      MinecraftForgeClient.registerItemRenderer(RealLifeMod_Items.ak, new AK47Renderer());
      MinecraftForgeClient.registerItemRenderer(RealLifeMod_Items.uzi, new uziRenderer());
      MinecraftForgeClient.registerItemRenderer(RealLifeMod_Items.RPG, new RPGRenderer());
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.PC), new GenericBlockItemRenderer(new ComputerTER(), new ComputerTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.television), new GenericBlockItemRenderer(new TelevisionTER(), new TileEntityTV()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.cupboard), new GenericBlockItemRenderer(new CupboardTER(), new CupboardTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.kitchencabinet), new GenericBlockItemRenderer(new CabinetTER(), new CabinetTE(0)));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.rooftile), new GenericBlockItemRenderer(new RooftileTER(), new RooftileTE(0)));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.rooftile1), new GenericBlockItemRenderer(new RooftileTER(), new RooftileTE(1)));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.rooftile2), new GenericBlockItemRenderer(new RooftileTER(), new RooftileTE(2)));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.chair), new GenericBlockItemRenderer(new ChairTER(), new ChairTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.desk), new GenericBlockItemRenderer(new DeskTER(), new DeskTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.dishwasher), new GenericBlockItemRenderer(new DishwasherTER(), new DishwasherTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.drawer), new GenericBlockItemRenderer(new DrawerTER(), new DrawerTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.espresso), new GenericBlockItemRenderer(new EspressoTER(), new EspressoTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.fishtank), new GenericBlockItemRenderer(new FishtankTER(), new FishtankTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.freezer), new GenericBlockItemRenderer(new FreezerTER(), new FreezerTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.fridge), new GenericBlockItemRenderer(new FridgeTER(), new FridgeTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.growpot), new GenericBlockItemRenderer(new GrowpotTER(), new GrowpotTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.heating), new GenericBlockItemRenderer(new HeatingTER(), new HeatingTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.NeonLamp), new GenericBlockItemRenderer(new NeonlampTER(), new NeonlampTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.radio), new GenericBlockItemRenderer(new RadioTER(), new RadioTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.railing), new GenericBlockItemRenderer(new RailingTER(), new RailingTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.shelf), new GenericBlockItemRenderer(new ShelfTER(), new ShelfTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.firtree), new GenericBlockItemRenderer(new FirTreeTER(), new FirTreeTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.oaktree), new GenericBlockItemRenderer(new OakTreeTER(), new OakTreeTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.sink), new GenericBlockItemRenderer(new SinkTER(), new SinkTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.toaster), new GenericBlockItemRenderer(new ToasterTER(), new ToasterTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.urinal), new GenericBlockItemRenderer(new UrinalTER(), new PissoirTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.toilet), new GenericBlockItemRenderer(new ToiletTER(), new ToiletTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.blastFurnace), new GenericBlockItemRenderer(new BlastfurnaceTER(), new BlastfurnaceTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.advertisingShield), new GenericBlockItemRenderer(new BilboardTER(), new BilboardTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.lantern), new GenericBlockItemRenderer(new LanternTER(), new LanternTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.TrafficLight), new GenericBlockItemRenderer(new TrafficlightTER(), new TrafficlightTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.basin), new GenericBlockItemRenderer(new WashbasinTER(), new WashbasinTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.christmastree), new GenericBlockItemRenderer(new ChristmastreeTER(), new ChristmasTreeTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.Doorwreath), new GenericBlockItemRenderer(new DoorwreathTER(), new DoorwreathTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.atm), new GenericBlockItemRenderer(new AtmTER(), new atmTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.fountain), new GenericBlockItemRenderer(new FountainTER(), new FountainTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.window), new GenericBlockItemRenderer(new WindowTER(), new WindowTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.babybed), new GenericBlockItemRenderer(new BabybedTER(), new BabybedTE()));
      MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RealLifeMod_Blocks.cookingrod), new GenericBlockItemRenderer(new CookingRodTER(), new CookingRodTE()));
   }

   public void registerEntitys() {
      this.modEntityID = EntityRegistry.findGlobalUniqueEntityId();
      EntityRegistry.registerModEntity(EntityLanz.class, "LANZ", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.registerModEntity(EntityTruck.class, "TGX", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.registerModEntity(EntityBullet.class, "BULLET", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.registerModEntity(ReallifemodNPC.class, "worker", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.registerModEntity(EntityRobot.class, "Tutorialbot", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.registerModEntity(EntityFallingOak.class, "FallingOak", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.registerModEntity(EntityFirFalling.class, "FallingFir", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.registerModEntity(EntityFlyingBlock.class, "FlyingBlock", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.registerModEntity(EntityDrop.class, "WaterDropFX", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.registerModEntity(EntitySeat.class, "EntitySeat", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.registerModEntity(EntityLawnMower.class, "EntityLawnmower", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.registerModEntity(EntityRidingmower.class, "EntityRidingmower", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.registerModEntity(EntityBaby.class, "BabyEntity", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.registerModEntity(EntityJeep.class, "EntityJeep", this.modEntityID++, RealLifeMod.instance, 80, 1, true);
      EntityRegistry.removeSpawn(EntitySkeleton.class, EnumCreatureType.monster, new BiomeGenBase[]{BiomeGenBase.plains});
   }

   @SideOnly(Side.CLIENT)
   public void registerRenderThings() {
      UpdateChecker renderfr = new UpdateChecker();
      renderfr.start();
      FreezerTER renderfr1 = new FreezerTER();
      ClientRegistry.bindTileEntitySpecialRenderer(FreezerTE.class, renderfr1);
      ClientRegistry.bindTileEntitySpecialRenderer(BilboardTE.class, new BilboardTER());
      ClientRegistry.bindTileEntitySpecialRenderer(ComputerTE.class, new ComputerTER());
      ClientRegistry.bindTileEntitySpecialRenderer(BlastfurnaceTE.class, new BlastfurnaceTER());
      ClientRegistry.bindTileEntitySpecialRenderer(CupboardTE.class, new CupboardTER());
      ClientRegistry.bindTileEntitySpecialRenderer(ChairTE.class, new ChairTER());
      ClientRegistry.bindTileEntitySpecialRenderer(WashbasinTE.class, new WashbasinTER());
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTV.class, new TelevisionTER());
      ClientRegistry.bindTileEntitySpecialRenderer(LanternTE.class, new LanternTER());
      ClientRegistry.bindTileEntitySpecialRenderer(TrafficlightTE.class, new TrafficlightTER());
      ClientRegistry.bindTileEntitySpecialRenderer(DrinksmachineTE.class, new DrinksmachineTER());
      ClientRegistry.bindTileEntitySpecialRenderer(NeonlampTE.class, new NeonlampTER());
      ClientRegistry.bindTileEntitySpecialRenderer(PissoirTE.class, new UrinalTER());
      ClientRegistry.bindTileEntitySpecialRenderer(ChristmaspyramidTE.class, new ChristmaspyramidTER());
      ClientRegistry.bindTileEntitySpecialRenderer(ChristmasTreeTE.class, new ChristmastreeTER());
      ClientRegistry.bindTileEntitySpecialRenderer(DoorwreathTE.class, new DoorwreathTER());
      ClientRegistry.bindTileEntitySpecialRenderer(CrashbarrierTE.class, new CrashbarrierTER());
      ClientRegistry.bindTileEntitySpecialRenderer(PillarTE.class, new PillarTER());
      ClientRegistry.bindTileEntitySpecialRenderer(ShowcaseTE.class, new ShowcaseTER());
      ClientRegistry.bindTileEntitySpecialRenderer(BankTE.class, new BankTER());
      ClientRegistry.bindTileEntitySpecialRenderer(TableTE.class, new TableTER());
      ClientRegistry.bindTileEntitySpecialRenderer(RadioTE.class, new RadioTER());
      ClientRegistry.bindTileEntitySpecialRenderer(FireplaceTE.class, new FireplaceTER());
      ClientRegistry.bindTileEntitySpecialRenderer(ToasterTE.class, new ToasterTER());
      ClientRegistry.bindTileEntitySpecialRenderer(atmTE.class, new AtmTER());
      ClientRegistry.bindTileEntitySpecialRenderer(HeatingTE.class, new HeatingTER());
      ClientRegistry.bindTileEntitySpecialRenderer(RooftileTE.class, new RooftileTER());
      ClientRegistry.bindTileEntitySpecialRenderer(ToiletTE.class, new ToiletTER());
      ClientRegistry.bindTileEntitySpecialRenderer(FishtankTE.class, new FishtankTER());
      ClientRegistry.bindTileEntitySpecialRenderer(CabinetTE.class, new CabinetTER());
      ClientRegistry.bindTileEntitySpecialRenderer(ShelfTE.class, new ShelfTER());
      ClientRegistry.bindTileEntitySpecialRenderer(DishwasherTE.class, new DishwasherTER());
      ClientRegistry.bindTileEntitySpecialRenderer(SinkTE.class, new SinkTER());
      ClientRegistry.bindTileEntitySpecialRenderer(GrowpotTE.class, new GrowpotTER());
      ClientRegistry.bindTileEntitySpecialRenderer(EspressoTE.class, new EspressoTER());
      ClientRegistry.bindTileEntitySpecialRenderer(DeskTE.class, new DeskTER());
      ClientRegistry.bindTileEntitySpecialRenderer(RailingTE.class, new RailingTER());
      ClientRegistry.bindTileEntitySpecialRenderer(DrawerTE.class, new DrawerTER());
      ClientRegistry.bindTileEntitySpecialRenderer(SideboardTE.class, new SideboardTER());
      ClientRegistry.bindTileEntitySpecialRenderer(FirTreeTE.class, new FirTreeTER());
      ClientRegistry.bindTileEntitySpecialRenderer(FridgeTE.class, new FridgeTER());
      ClientRegistry.bindTileEntitySpecialRenderer(OakTreeTE.class, new OakTreeTER());
      ClientRegistry.bindTileEntitySpecialRenderer(FountainTE.class, new FountainTER());
      ClientRegistry.bindTileEntitySpecialRenderer(WindowTE.class, new WindowTER());
      ClientRegistry.bindTileEntitySpecialRenderer(BabybedTE.class, new BabybedTER());
      ClientRegistry.bindTileEntitySpecialRenderer(CookingRodTE.class, new CookingRodTER());
      RenderingRegistry.registerEntityRenderingHandler(EntityLanz.class, new RenderLanz());
      RenderingRegistry.registerEntityRenderingHandler(EntityTruck.class, new RenderTGX());
      RenderingRegistry.registerEntityRenderingHandler(EntityTrailer.class, new RenderTrailer());
      RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet());
      RenderingRegistry.registerEntityRenderingHandler(ReallifemodNPC.class, new RenderNPC(0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityRobot.class, new RenderRobot(0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityAdvancedFlameFX.class, new RenderAdvancedFlame());
      RenderingRegistry.registerEntityRenderingHandler(EntityVehicle.class, new RenderVehicle());
      RenderingRegistry.registerEntityRenderingHandler(EntityFirFalling.class, new RenderFallingTree(new FirTreeTE()));
      RenderingRegistry.registerEntityRenderingHandler(EntityFallingOak.class, new RenderFallingTree(new OakTreeTE()));
      RenderingRegistry.registerEntityRenderingHandler(EntityFlyingBlock.class, new RenderFlyingBlock());
      RenderingRegistry.registerEntityRenderingHandler(EntityLawnMower.class, new RenderLawnmower());
      RenderingRegistry.registerEntityRenderingHandler(EntityRidingmower.class, new RenderRidingmower());
      RenderingRegistry.registerEntityRenderingHandler(EntitySpider.class, new AdaptiveRenderSpider());
      RenderingRegistry.registerEntityRenderingHandler(EntityDrop.class, new RenderDrop());
      RenderingRegistry.registerEntityRenderingHandler(EntityBaby.class, new RenderBaby(0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityJeep.class, new RenderJeep());
      MinecraftForge.EVENT_BUS.register(new RealLifeMod_Overlay(this.mc));
   }

   public void registerThings() {
      GameRegistry.registerFuelHandler(new ItemCoke());
   }

   public void registerTileEntities() {
      GameRegistry.registerTileEntity(FreezerTE.class, "freezer");
      GameRegistry.registerTileEntity(CupboardTE.class, "cupboard");
      GameRegistry.registerTileEntity(ChairTE.class, "blockChair");
      GameRegistry.registerTileEntity(TileEntityTV.class, "blockTelevision");
      GameRegistry.registerTileEntity(BlastfurnaceTE.class, "blockBlastFurnace");
      GameRegistry.registerTileEntity(ComputerTE.class, "TileEntityComputer");
      GameRegistry.registerTileEntity(WashbasinTE.class, "blockBasin");
      GameRegistry.registerTileEntity(PissoirTE.class, "blockPissoir");
      GameRegistry.registerTileEntity(TrafficlightTE.class, "trafficlight");
      GameRegistry.registerTileEntity(LanternTE.class, "lantern");
      GameRegistry.registerTileEntity(DrinksmachineTE.class, "drinksmachine");
      GameRegistry.registerTileEntity(NeonlampTE.class, "NeonLamp");
      GameRegistry.registerTileEntity(ChristmaspyramidTE.class, "christmaspyramid");
      GameRegistry.registerTileEntity(ChristmasTreeTE.class, "christmastree");
      GameRegistry.registerTileEntity(DoorwreathTE.class, "doorwreathte");
      GameRegistry.registerTileEntity(CrashbarrierTE.class, "crashbarrierRS");
      GameRegistry.registerTileEntity(PillarTE.class, "pillar");
      GameRegistry.registerTileEntity(ShowcaseTE.class, "safe");
      GameRegistry.registerTileEntity(BankTE.class, "bankeree");
      GameRegistry.registerTileEntity(TableTE.class, "TableTE");
      GameRegistry.registerTileEntity(RadioTE.class, "RadioTE");
      GameRegistry.registerTileEntity(ToasterTE.class, "toaster");
      GameRegistry.registerTileEntity(atmTE.class, "atm");
      GameRegistry.registerTileEntity(HeatingTE.class, "HeatingTE");
      GameRegistry.registerTileEntity(RooftileTE.class, "RooftileTE");
      GameRegistry.registerTileEntity(ToiletTE.class, "ToiletTE");
      GameRegistry.registerTileEntity(CabinetTE.class, "cabinet");
      GameRegistry.registerTileEntity(ShelfTE.class, "ShelfTE");
      GameRegistry.registerTileEntity(DishwasherTE.class, "DishwasherTE");
      GameRegistry.registerTileEntity(FishtankTE.class, "FishtankTE");
      GameRegistry.registerTileEntity(GrowpotTE.class, "GrowpotTE");
      GameRegistry.registerTileEntity(RailingTE.class, "RailingTSE");
      GameRegistry.registerTileEntity(DrawerTE.class, "DrawerTE");
      GameRegistry.registerTileEntity(TreeTE.class, "TreeTE");
      GameRegistry.registerTileEntity(FireplaceTE.class, "FireplaceTE");
      GameRegistry.registerTileEntity(SideboardTE.class, "SideboardTE");
      GameRegistry.registerTileEntity(FirTreeTE.class, "GenericTree");
      GameRegistry.registerTileEntity(FridgeTE.class, "FridgeTE");
      GameRegistry.registerTileEntity(OakTreeTE.class, "OakTreeTE");
      GameRegistry.registerTileEntity(FountainTE.class, "TileEntityFountain");
      GameRegistry.registerTileEntity(WindowTE.class, "TileEntityWindow");
      GameRegistry.registerTileEntity(BabybedTE.class, "TileEntityBabybed");
      GameRegistry.registerTileEntity(CookingRodTE.class, "TileEntityCookingRod");
   }

   public void setIDs() {}

   @SubscribeEvent
   public void onEntitySpawn(EntityJoinWorldEvent event) {}

   @SubscribeEvent
   public void onCreeperExplode(ExplosionEvent event) {
      if(((RealLifeProperties)FMLClientHandler.instance().getClientPlayerEntity().getExtendedProperties("RealLifeProperties")).dressing == EnumDress.Creeper) {
         if(event.isCancelable()) {
            event.setCanceled(true);
         }

         if(event.explosion.exploder instanceof EntityCreeper) {
            EntityCreeper creeper = (EntityCreeper)event.explosion.exploder;
            EntityCreeper NewCreeper = new EntityCreeper(Minecraft.getMinecraft().theWorld);
            NewCreeper.func_70107_b(creeper.field_70165_t, creeper.field_70163_u, creeper.field_70161_v);
            NewCreeper.setCreeperState(0);
            event.world.spawnEntityInWorld(NewCreeper);
            creeper.setCreeperState(0);
         }
      }

   }

   public void createFolders() {
      File file = new File(RealLifeMod.Dir, "assets/reallifemod");
      if(!file.exists()) {
         file.mkdirs();
      }

      File check = new File(file, "sounds");
      if(!check.exists()) {
         check.mkdir();
      }

      new File(RealLifeMod.Dir, "sound.json");
      File json = new File(file, "sound.json");
      if(!json.exists()) {
         try {
            BufferedWriter e = new BufferedWriter(new FileWriter(json));
            json.createNewFile();
            e.write("{");
            if(check.listFiles() != null) {
               File[] var6 = check.listFiles();
               int var7 = var6.length;

               for(int var8 = 0; var8 < var7; ++var8) {
                  File f = var6[var8];
                  String g = String.valueOf('\"');
                  e.write(g + f.getName().substring(0, f.getName().length() - 4) + g + ":" + "{" + g + "category" + g + ":" + g + "ambient" + g + "," + g + "sounds" + g + ":" + "[{" + g + "name" + g + ":" + f.getName().substring(0, f.getName().length() - 4) + g + "," + g + "stream" + g + ":" + "true}]},");
                  e.write("\n");
               }
            }

            e.write("}");
            e.close();
         } catch (IOException var11) {
            ;
         }
      }

      ((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new RLMResourceListener());
   }

   public void registerVehicles() {}

   public void registerVehicle(VehicleData data, String texturename) {
      RealLifeMod_Items.registerItem((VehicleItem)(new VehicleItem(data, texturename.replaceAll("minecraft", "reallifemod"))).func_77637_a(RealLifeMod.Streets));
   }

}
