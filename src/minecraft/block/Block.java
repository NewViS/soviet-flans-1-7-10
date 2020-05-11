package net.minecraft.block;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block$1;
import net.minecraft.block.Block$2;
import net.minecraft.block.Block$3;
import net.minecraft.block.Block$SoundType;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockCarrot;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockClay;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.BlockCompressedPowered;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.BlockDropper;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockGlowstone;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockHardenedClay;
import net.minecraft.block.BlockHay;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.BlockMelon;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockNote;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.BlockPotato;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.BlockPressurePlate$Sensitivity;
import net.minecraft.block.BlockPressurePlateWeighted;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockRail;
import net.minecraft.block.BlockRailDetector;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockSnowBlock;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.block.BlockSponge;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.BlockStem;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockTripWire;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.block.BlockVine;
import net.minecraft.block.BlockWall;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.BlockWood;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Block {

   public static final RegistryNamespaced blockRegistry = new RegistryNamespacedDefaultedByKey("air");
   private CreativeTabs displayOnCreativeTab;
   protected String textureName;
   public static final Block$SoundType soundTypeStone = new Block$SoundType("stone", 1.0F, 1.0F);
   public static final Block$SoundType soundTypeWood = new Block$SoundType("wood", 1.0F, 1.0F);
   public static final Block$SoundType soundTypeGravel = new Block$SoundType("gravel", 1.0F, 1.0F);
   public static final Block$SoundType soundTypeGrass = new Block$SoundType("grass", 1.0F, 1.0F);
   public static final Block$SoundType soundTypePiston = new Block$SoundType("stone", 1.0F, 1.0F);
   public static final Block$SoundType soundTypeMetal = new Block$SoundType("stone", 1.0F, 1.5F);
   public static final Block$SoundType soundTypeGlass = new Block$1("stone", 1.0F, 1.0F);
   public static final Block$SoundType soundTypeCloth = new Block$SoundType("cloth", 1.0F, 1.0F);
   public static final Block$SoundType soundTypeSand = new Block$SoundType("sand", 1.0F, 1.0F);
   public static final Block$SoundType soundTypeSnow = new Block$SoundType("snow", 1.0F, 1.0F);
   public static final Block$SoundType soundTypeLadder = new Block$2("ladder", 1.0F, 1.0F);
   public static final Block$SoundType soundTypeAnvil = new Block$3("anvil", 0.3F, 1.0F);
   protected boolean opaque;
   protected int lightOpacity;
   protected boolean canBlockGrass;
   protected int lightValue;
   protected boolean useNeighborBrightness;
   protected float blockHardness;
   protected float blockResistance;
   protected boolean blockConstructorCalled = true;
   protected boolean enableStats = true;
   protected boolean needsRandomTick;
   protected boolean isBlockContainer;
   protected double minX;
   protected double minY;
   protected double minZ;
   protected double maxX;
   protected double maxY;
   protected double maxZ;
   public Block$SoundType stepSound;
   public float blockParticleGravity;
   protected final Material blockMaterial;
   public float slipperiness;
   private String unlocalizedName;
   protected IIcon blockIcon;


   public static int getIdFromBlock(Block var0) {
      return blockRegistry.getIDForObject(var0);
   }

   public static Block getBlockById(int var0) {
      return (Block)blockRegistry.getObjectById(var0);
   }

   public static Block getBlockFromItem(Item var0) {
      return getBlockById(Item.getIdFromItem(var0));
   }

   public static Block getBlockFromName(String var0) {
      if(blockRegistry.containsKey(var0)) {
         return (Block)blockRegistry.getObject(var0);
      } else {
         try {
            return (Block)blockRegistry.getObjectById(Integer.parseInt(var0));
         } catch (NumberFormatException var2) {
            return null;
         }
      }
   }

   public boolean func_149730_j() {
      return this.opaque;
   }

   public int getLightOpacity() {
      return this.lightOpacity;
   }

   public boolean getCanBlockGrass() {
      return this.canBlockGrass;
   }

   public int getLightValue() {
      return this.lightValue;
   }

   public boolean getUseNeighborBrightness() {
      return this.useNeighborBrightness;
   }

   public Material getMaterial() {
      return this.blockMaterial;
   }

   public MapColor getMapColor(int var1) {
      return this.getMaterial().getMaterialMapColor();
   }

   public static void registerBlocks() {
      blockRegistry.addObject(0, "air", (new BlockAir()).setBlockName("air"));
      blockRegistry.addObject(1, "stone", (new BlockStone()).setHardness(1.5F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("stone").setBlockTextureName("stone"));
      blockRegistry.addObject(2, "grass", (new BlockGrass()).setHardness(0.6F).setStepSound(soundTypeGrass).setBlockName("grass").setBlockTextureName("grass"));
      blockRegistry.addObject(3, "dirt", (new BlockDirt()).setHardness(0.5F).setStepSound(soundTypeGravel).setBlockName("dirt").setBlockTextureName("dirt"));
      Block var0 = (new Block(Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("stonebrick").setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName("cobblestone");
      blockRegistry.addObject(4, "cobblestone", var0);
      Block var1 = (new BlockWood()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setBlockName("wood").setBlockTextureName("planks");
      blockRegistry.addObject(5, "planks", var1);
      blockRegistry.addObject(6, "sapling", (new BlockSapling()).setHardness(0.0F).setStepSound(soundTypeGrass).setBlockName("sapling").setBlockTextureName("sapling"));
      blockRegistry.addObject(7, "bedrock", (new Block(Material.rock)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(soundTypePiston).setBlockName("bedrock").disableStats().setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName("bedrock"));
      blockRegistry.addObject(8, "flowing_water", (new BlockDynamicLiquid(Material.water)).setHardness(100.0F).setLightOpacity(3).setBlockName("water").disableStats().setBlockTextureName("water_flow"));
      blockRegistry.addObject(9, "water", (new BlockStaticLiquid(Material.water)).setHardness(100.0F).setLightOpacity(3).setBlockName("water").disableStats().setBlockTextureName("water_still"));
      blockRegistry.addObject(10, "flowing_lava", (new BlockDynamicLiquid(Material.lava)).setHardness(100.0F).setLightLevel(1.0F).setBlockName("lava").disableStats().setBlockTextureName("lava_flow"));
      blockRegistry.addObject(11, "lava", (new BlockStaticLiquid(Material.lava)).setHardness(100.0F).setLightLevel(1.0F).setBlockName("lava").disableStats().setBlockTextureName("lava_still"));
      blockRegistry.addObject(12, "sand", (new BlockSand()).setHardness(0.5F).setStepSound(soundTypeSand).setBlockName("sand").setBlockTextureName("sand"));
      blockRegistry.addObject(13, "gravel", (new BlockGravel()).setHardness(0.6F).setStepSound(soundTypeGravel).setBlockName("gravel").setBlockTextureName("gravel"));
      blockRegistry.addObject(14, "gold_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreGold").setBlockTextureName("gold_ore"));
      blockRegistry.addObject(15, "iron_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreIron").setBlockTextureName("iron_ore"));
      blockRegistry.addObject(16, "coal_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreCoal").setBlockTextureName("coal_ore"));
      blockRegistry.addObject(17, "log", (new BlockOldLog()).setBlockName("log").setBlockTextureName("log"));
      blockRegistry.addObject(18, "leaves", (new BlockOldLeaf()).setBlockName("leaves").setBlockTextureName("leaves"));
      blockRegistry.addObject(19, "sponge", (new BlockSponge()).setHardness(0.6F).setStepSound(soundTypeGrass).setBlockName("sponge").setBlockTextureName("sponge"));
      blockRegistry.addObject(20, "glass", (new BlockGlass(Material.glass, false)).setHardness(0.3F).setStepSound(soundTypeGlass).setBlockName("glass").setBlockTextureName("glass"));
      blockRegistry.addObject(21, "lapis_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreLapis").setBlockTextureName("lapis_ore"));
      blockRegistry.addObject(22, "lapis_block", (new BlockCompressed(MapColor.lapisColor)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("blockLapis").setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName("lapis_block"));
      blockRegistry.addObject(23, "dispenser", (new BlockDispenser()).setHardness(3.5F).setStepSound(soundTypePiston).setBlockName("dispenser").setBlockTextureName("dispenser"));
      Block var2 = (new BlockSandStone()).setStepSound(soundTypePiston).setHardness(0.8F).setBlockName("sandStone").setBlockTextureName("sandstone");
      blockRegistry.addObject(24, "sandstone", var2);
      blockRegistry.addObject(25, "noteblock", (new BlockNote()).setHardness(0.8F).setBlockName("musicBlock").setBlockTextureName("noteblock"));
      blockRegistry.addObject(26, "bed", (new BlockBed()).setHardness(0.2F).setBlockName("bed").disableStats().setBlockTextureName("bed"));
      blockRegistry.addObject(27, "golden_rail", (new BlockRailPowered()).setHardness(0.7F).setStepSound(soundTypeMetal).setBlockName("goldenRail").setBlockTextureName("rail_golden"));
      blockRegistry.addObject(28, "detector_rail", (new BlockRailDetector()).setHardness(0.7F).setStepSound(soundTypeMetal).setBlockName("detectorRail").setBlockTextureName("rail_detector"));
      blockRegistry.addObject(29, "sticky_piston", (new BlockPistonBase(true)).setBlockName("pistonStickyBase"));
      blockRegistry.addObject(30, "web", (new BlockWeb()).setLightOpacity(1).setHardness(4.0F).setBlockName("web").setBlockTextureName("web"));
      blockRegistry.addObject(31, "tallgrass", (new BlockTallGrass()).setHardness(0.0F).setStepSound(soundTypeGrass).setBlockName("tallgrass"));
      blockRegistry.addObject(32, "deadbush", (new BlockDeadBush()).setHardness(0.0F).setStepSound(soundTypeGrass).setBlockName("deadbush").setBlockTextureName("deadbush"));
      blockRegistry.addObject(33, "piston", (new BlockPistonBase(false)).setBlockName("pistonBase"));
      blockRegistry.addObject(34, "piston_head", new BlockPistonExtension());
      blockRegistry.addObject(35, "wool", (new BlockColored(Material.cloth)).setHardness(0.8F).setStepSound(soundTypeCloth).setBlockName("cloth").setBlockTextureName("wool_colored"));
      blockRegistry.addObject(36, "piston_extension", new BlockPistonMoving());
      blockRegistry.addObject(37, "yellow_flower", (new BlockFlower(0)).setHardness(0.0F).setStepSound(soundTypeGrass).setBlockName("flower1").setBlockTextureName("flower_dandelion"));
      blockRegistry.addObject(38, "red_flower", (new BlockFlower(1)).setHardness(0.0F).setStepSound(soundTypeGrass).setBlockName("flower2").setBlockTextureName("flower_rose"));
      blockRegistry.addObject(39, "brown_mushroom", (new BlockMushroom()).setHardness(0.0F).setStepSound(soundTypeGrass).setLightLevel(0.125F).setBlockName("mushroom").setBlockTextureName("mushroom_brown"));
      blockRegistry.addObject(40, "red_mushroom", (new BlockMushroom()).setHardness(0.0F).setStepSound(soundTypeGrass).setBlockName("mushroom").setBlockTextureName("mushroom_red"));
      blockRegistry.addObject(41, "gold_block", (new BlockCompressed(MapColor.goldColor)).setHardness(3.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setBlockName("blockGold").setBlockTextureName("gold_block"));
      blockRegistry.addObject(42, "iron_block", (new BlockCompressed(MapColor.ironColor)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setBlockName("blockIron").setBlockTextureName("iron_block"));
      blockRegistry.addObject(43, "double_stone_slab", (new BlockStoneSlab(true)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("stoneSlab"));
      blockRegistry.addObject(44, "stone_slab", (new BlockStoneSlab(false)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("stoneSlab"));
      Block var3 = (new Block(Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("brick").setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName("brick");
      blockRegistry.addObject(45, "brick_block", var3);
      blockRegistry.addObject(46, "tnt", (new BlockTNT()).setHardness(0.0F).setStepSound(soundTypeGrass).setBlockName("tnt").setBlockTextureName("tnt"));
      blockRegistry.addObject(47, "bookshelf", (new BlockBookshelf()).setHardness(1.5F).setStepSound(soundTypeWood).setBlockName("bookshelf").setBlockTextureName("bookshelf"));
      blockRegistry.addObject(48, "mossy_cobblestone", (new Block(Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("stoneMoss").setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName("cobblestone_mossy"));
      blockRegistry.addObject(49, "obsidian", (new BlockObsidian()).setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypePiston).setBlockName("obsidian").setBlockTextureName("obsidian"));
      blockRegistry.addObject(50, "torch", (new BlockTorch()).setHardness(0.0F).setLightLevel(0.9375F).setStepSound(soundTypeWood).setBlockName("torch").setBlockTextureName("torch_on"));
      blockRegistry.addObject(51, "fire", (new BlockFire()).setHardness(0.0F).setLightLevel(1.0F).setStepSound(soundTypeWood).setBlockName("fire").disableStats().setBlockTextureName("fire"));
      blockRegistry.addObject(52, "mob_spawner", (new BlockMobSpawner()).setHardness(5.0F).setStepSound(soundTypeMetal).setBlockName("mobSpawner").disableStats().setBlockTextureName("mob_spawner"));
      blockRegistry.addObject(53, "oak_stairs", (new BlockStairs(var1, 0)).setBlockName("stairsWood"));
      blockRegistry.addObject(54, "chest", (new BlockChest(0)).setHardness(2.5F).setStepSound(soundTypeWood).setBlockName("chest"));
      blockRegistry.addObject(55, "redstone_wire", (new BlockRedstoneWire()).setHardness(0.0F).setStepSound(soundTypeStone).setBlockName("redstoneDust").disableStats().setBlockTextureName("redstone_dust"));
      blockRegistry.addObject(56, "diamond_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreDiamond").setBlockTextureName("diamond_ore"));
      blockRegistry.addObject(57, "diamond_block", (new BlockCompressed(MapColor.diamondColor)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setBlockName("blockDiamond").setBlockTextureName("diamond_block"));
      blockRegistry.addObject(58, "crafting_table", (new BlockWorkbench()).setHardness(2.5F).setStepSound(soundTypeWood).setBlockName("workbench").setBlockTextureName("crafting_table"));
      blockRegistry.addObject(59, "wheat", (new BlockCrops()).setBlockName("crops").setBlockTextureName("wheat"));
      Block var4 = (new BlockFarmland()).setHardness(0.6F).setStepSound(soundTypeGravel).setBlockName("farmland").setBlockTextureName("farmland");
      blockRegistry.addObject(60, "farmland", var4);
      blockRegistry.addObject(61, "furnace", (new BlockFurnace(false)).setHardness(3.5F).setStepSound(soundTypePiston).setBlockName("furnace").setCreativeTab(CreativeTabs.tabDecorations));
      blockRegistry.addObject(62, "lit_furnace", (new BlockFurnace(true)).setHardness(3.5F).setStepSound(soundTypePiston).setLightLevel(0.875F).setBlockName("furnace"));
      blockRegistry.addObject(63, "standing_sign", (new BlockSign(TileEntitySign.class, true)).setHardness(1.0F).setStepSound(soundTypeWood).setBlockName("sign").disableStats());
      blockRegistry.addObject(64, "wooden_door", (new BlockDoor(Material.wood)).setHardness(3.0F).setStepSound(soundTypeWood).setBlockName("doorWood").disableStats().setBlockTextureName("door_wood"));
      blockRegistry.addObject(65, "ladder", (new BlockLadder()).setHardness(0.4F).setStepSound(soundTypeLadder).setBlockName("ladder").setBlockTextureName("ladder"));
      blockRegistry.addObject(66, "rail", (new BlockRail()).setHardness(0.7F).setStepSound(soundTypeMetal).setBlockName("rail").setBlockTextureName("rail_normal"));
      blockRegistry.addObject(67, "stone_stairs", (new BlockStairs(var0, 0)).setBlockName("stairsStone"));
      blockRegistry.addObject(68, "wall_sign", (new BlockSign(TileEntitySign.class, false)).setHardness(1.0F).setStepSound(soundTypeWood).setBlockName("sign").disableStats());
      blockRegistry.addObject(69, "lever", (new BlockLever()).setHardness(0.5F).setStepSound(soundTypeWood).setBlockName("lever").setBlockTextureName("lever"));
      blockRegistry.addObject(70, "stone_pressure_plate", (new BlockPressurePlate("stone", Material.rock, BlockPressurePlate$Sensitivity.mobs)).setHardness(0.5F).setStepSound(soundTypePiston).setBlockName("pressurePlate"));
      blockRegistry.addObject(71, "iron_door", (new BlockDoor(Material.iron)).setHardness(5.0F).setStepSound(soundTypeMetal).setBlockName("doorIron").disableStats().setBlockTextureName("door_iron"));
      blockRegistry.addObject(72, "wooden_pressure_plate", (new BlockPressurePlate("planks_oak", Material.wood, BlockPressurePlate$Sensitivity.everything)).setHardness(0.5F).setStepSound(soundTypeWood).setBlockName("pressurePlate"));
      blockRegistry.addObject(73, "redstone_ore", (new BlockRedstoneOre(false)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreRedstone").setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName("redstone_ore"));
      blockRegistry.addObject(74, "lit_redstone_ore", (new BlockRedstoneOre(true)).setLightLevel(0.625F).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreRedstone").setBlockTextureName("redstone_ore"));
      blockRegistry.addObject(75, "unlit_redstone_torch", (new BlockRedstoneTorch(false)).setHardness(0.0F).setStepSound(soundTypeWood).setBlockName("notGate").setBlockTextureName("redstone_torch_off"));
      blockRegistry.addObject(76, "redstone_torch", (new BlockRedstoneTorch(true)).setHardness(0.0F).setLightLevel(0.5F).setStepSound(soundTypeWood).setBlockName("notGate").setCreativeTab(CreativeTabs.tabRedstone).setBlockTextureName("redstone_torch_on"));
      blockRegistry.addObject(77, "stone_button", (new BlockButtonStone()).setHardness(0.5F).setStepSound(soundTypePiston).setBlockName("button"));
      blockRegistry.addObject(78, "snow_layer", (new BlockSnow()).setHardness(0.1F).setStepSound(soundTypeSnow).setBlockName("snow").setLightOpacity(0).setBlockTextureName("snow"));
      blockRegistry.addObject(79, "ice", (new BlockIce()).setHardness(0.5F).setLightOpacity(3).setStepSound(soundTypeGlass).setBlockName("ice").setBlockTextureName("ice"));
      blockRegistry.addObject(80, "snow", (new BlockSnowBlock()).setHardness(0.2F).setStepSound(soundTypeSnow).setBlockName("snow").setBlockTextureName("snow"));
      blockRegistry.addObject(81, "cactus", (new BlockCactus()).setHardness(0.4F).setStepSound(soundTypeCloth).setBlockName("cactus").setBlockTextureName("cactus"));
      blockRegistry.addObject(82, "clay", (new BlockClay()).setHardness(0.6F).setStepSound(soundTypeGravel).setBlockName("clay").setBlockTextureName("clay"));
      blockRegistry.addObject(83, "reeds", (new BlockReed()).setHardness(0.0F).setStepSound(soundTypeGrass).setBlockName("reeds").disableStats().setBlockTextureName("reeds"));
      blockRegistry.addObject(84, "jukebox", (new BlockJukebox()).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("jukebox").setBlockTextureName("jukebox"));
      blockRegistry.addObject(85, "fence", (new BlockFence("planks_oak", Material.wood)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setBlockName("fence"));
      Block var5 = (new BlockPumpkin(false)).setHardness(1.0F).setStepSound(soundTypeWood).setBlockName("pumpkin").setBlockTextureName("pumpkin");
      blockRegistry.addObject(86, "pumpkin", var5);
      blockRegistry.addObject(87, "netherrack", (new BlockNetherrack()).setHardness(0.4F).setStepSound(soundTypePiston).setBlockName("hellrock").setBlockTextureName("netherrack"));
      blockRegistry.addObject(88, "soul_sand", (new BlockSoulSand()).setHardness(0.5F).setStepSound(soundTypeSand).setBlockName("hellsand").setBlockTextureName("soul_sand"));
      blockRegistry.addObject(89, "glowstone", (new BlockGlowstone(Material.glass)).setHardness(0.3F).setStepSound(soundTypeGlass).setLightLevel(1.0F).setBlockName("lightgem").setBlockTextureName("glowstone"));
      blockRegistry.addObject(90, "portal", (new BlockPortal()).setHardness(-1.0F).setStepSound(soundTypeGlass).setLightLevel(0.75F).setBlockName("portal").setBlockTextureName("portal"));
      blockRegistry.addObject(91, "lit_pumpkin", (new BlockPumpkin(true)).setHardness(1.0F).setStepSound(soundTypeWood).setLightLevel(1.0F).setBlockName("litpumpkin").setBlockTextureName("pumpkin"));
      blockRegistry.addObject(92, "cake", (new BlockCake()).setHardness(0.5F).setStepSound(soundTypeCloth).setBlockName("cake").disableStats().setBlockTextureName("cake"));
      blockRegistry.addObject(93, "unpowered_repeater", (new BlockRedstoneRepeater(false)).setHardness(0.0F).setStepSound(soundTypeWood).setBlockName("diode").disableStats().setBlockTextureName("repeater_off"));
      blockRegistry.addObject(94, "powered_repeater", (new BlockRedstoneRepeater(true)).setHardness(0.0F).setLightLevel(0.625F).setStepSound(soundTypeWood).setBlockName("diode").disableStats().setBlockTextureName("repeater_on"));
      blockRegistry.addObject(95, "stained_glass", (new BlockStainedGlass(Material.glass)).setHardness(0.3F).setStepSound(soundTypeGlass).setBlockName("stainedGlass").setBlockTextureName("glass"));
      blockRegistry.addObject(96, "trapdoor", (new BlockTrapDoor(Material.wood)).setHardness(3.0F).setStepSound(soundTypeWood).setBlockName("trapdoor").disableStats().setBlockTextureName("trapdoor"));
      blockRegistry.addObject(97, "monster_egg", (new BlockSilverfish()).setHardness(0.75F).setBlockName("monsterStoneEgg"));
      Block var6 = (new BlockStoneBrick()).setHardness(1.5F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("stonebricksmooth").setBlockTextureName("stonebrick");
      blockRegistry.addObject(98, "stonebrick", var6);
      blockRegistry.addObject(99, "brown_mushroom_block", (new BlockHugeMushroom(Material.wood, 0)).setHardness(0.2F).setStepSound(soundTypeWood).setBlockName("mushroom").setBlockTextureName("mushroom_block"));
      blockRegistry.addObject(100, "red_mushroom_block", (new BlockHugeMushroom(Material.wood, 1)).setHardness(0.2F).setStepSound(soundTypeWood).setBlockName("mushroom").setBlockTextureName("mushroom_block"));
      blockRegistry.addObject(101, "iron_bars", (new BlockPane("iron_bars", "iron_bars", Material.iron, true)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setBlockName("fenceIron"));
      blockRegistry.addObject(102, "glass_pane", (new BlockPane("glass", "glass_pane_top", Material.glass, false)).setHardness(0.3F).setStepSound(soundTypeGlass).setBlockName("thinGlass"));
      Block var7 = (new BlockMelon()).setHardness(1.0F).setStepSound(soundTypeWood).setBlockName("melon").setBlockTextureName("melon");
      blockRegistry.addObject(103, "melon_block", var7);
      blockRegistry.addObject(104, "pumpkin_stem", (new BlockStem(var5)).setHardness(0.0F).setStepSound(soundTypeWood).setBlockName("pumpkinStem").setBlockTextureName("pumpkin_stem"));
      blockRegistry.addObject(105, "melon_stem", (new BlockStem(var7)).setHardness(0.0F).setStepSound(soundTypeWood).setBlockName("pumpkinStem").setBlockTextureName("melon_stem"));
      blockRegistry.addObject(106, "vine", (new BlockVine()).setHardness(0.2F).setStepSound(soundTypeGrass).setBlockName("vine").setBlockTextureName("vine"));
      blockRegistry.addObject(107, "fence_gate", (new BlockFenceGate()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setBlockName("fenceGate"));
      blockRegistry.addObject(108, "brick_stairs", (new BlockStairs(var3, 0)).setBlockName("stairsBrick"));
      blockRegistry.addObject(109, "stone_brick_stairs", (new BlockStairs(var6, 0)).setBlockName("stairsStoneBrickSmooth"));
      blockRegistry.addObject(110, "mycelium", (new BlockMycelium()).setHardness(0.6F).setStepSound(soundTypeGrass).setBlockName("mycel").setBlockTextureName("mycelium"));
      blockRegistry.addObject(111, "waterlily", (new BlockLilyPad()).setHardness(0.0F).setStepSound(soundTypeGrass).setBlockName("waterlily").setBlockTextureName("waterlily"));
      Block var8 = (new Block(Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("netherBrick").setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName("nether_brick");
      blockRegistry.addObject(112, "nether_brick", var8);
      blockRegistry.addObject(113, "nether_brick_fence", (new BlockFence("nether_brick", Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("netherFence"));
      blockRegistry.addObject(114, "nether_brick_stairs", (new BlockStairs(var8, 0)).setBlockName("stairsNetherBrick"));
      blockRegistry.addObject(115, "nether_wart", (new BlockNetherWart()).setBlockName("netherStalk").setBlockTextureName("nether_wart"));
      blockRegistry.addObject(116, "enchanting_table", (new BlockEnchantmentTable()).setHardness(5.0F).setResistance(2000.0F).setBlockName("enchantmentTable").setBlockTextureName("enchanting_table"));
      blockRegistry.addObject(117, "brewing_stand", (new BlockBrewingStand()).setHardness(0.5F).setLightLevel(0.125F).setBlockName("brewingStand").setBlockTextureName("brewing_stand"));
      blockRegistry.addObject(118, "cauldron", (new BlockCauldron()).setHardness(2.0F).setBlockName("cauldron").setBlockTextureName("cauldron"));
      blockRegistry.addObject(119, "end_portal", (new BlockEndPortal(Material.portal)).setHardness(-1.0F).setResistance(6000000.0F));
      blockRegistry.addObject(120, "end_portal_frame", (new BlockEndPortalFrame()).setStepSound(soundTypeGlass).setLightLevel(0.125F).setHardness(-1.0F).setBlockName("endPortalFrame").setResistance(6000000.0F).setCreativeTab(CreativeTabs.tabDecorations).setBlockTextureName("endframe"));
      blockRegistry.addObject(121, "end_stone", (new Block(Material.rock)).setHardness(3.0F).setResistance(15.0F).setStepSound(soundTypePiston).setBlockName("whiteStone").setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName("end_stone"));
      blockRegistry.addObject(122, "dragon_egg", (new BlockDragonEgg()).setHardness(3.0F).setResistance(15.0F).setStepSound(soundTypePiston).setLightLevel(0.125F).setBlockName("dragonEgg").setBlockTextureName("dragon_egg"));
      blockRegistry.addObject(123, "redstone_lamp", (new BlockRedstoneLight(false)).setHardness(0.3F).setStepSound(soundTypeGlass).setBlockName("redstoneLight").setCreativeTab(CreativeTabs.tabRedstone).setBlockTextureName("redstone_lamp_off"));
      blockRegistry.addObject(124, "lit_redstone_lamp", (new BlockRedstoneLight(true)).setHardness(0.3F).setStepSound(soundTypeGlass).setBlockName("redstoneLight").setBlockTextureName("redstone_lamp_on"));
      blockRegistry.addObject(125, "double_wooden_slab", (new BlockWoodSlab(true)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setBlockName("woodSlab"));
      blockRegistry.addObject(126, "wooden_slab", (new BlockWoodSlab(false)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setBlockName("woodSlab"));
      blockRegistry.addObject(127, "cocoa", (new BlockCocoa()).setHardness(0.2F).setResistance(5.0F).setStepSound(soundTypeWood).setBlockName("cocoa").setBlockTextureName("cocoa"));
      blockRegistry.addObject(128, "sandstone_stairs", (new BlockStairs(var2, 0)).setBlockName("stairsSandStone"));
      blockRegistry.addObject(129, "emerald_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreEmerald").setBlockTextureName("emerald_ore"));
      blockRegistry.addObject(130, "ender_chest", (new BlockEnderChest()).setHardness(22.5F).setResistance(1000.0F).setStepSound(soundTypePiston).setBlockName("enderChest").setLightLevel(0.5F));
      blockRegistry.addObject(131, "tripwire_hook", (new BlockTripWireHook()).setBlockName("tripWireSource").setBlockTextureName("trip_wire_source"));
      blockRegistry.addObject(132, "tripwire", (new BlockTripWire()).setBlockName("tripWire").setBlockTextureName("trip_wire"));
      blockRegistry.addObject(133, "emerald_block", (new BlockCompressed(MapColor.emeraldColor)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setBlockName("blockEmerald").setBlockTextureName("emerald_block"));
      blockRegistry.addObject(134, "spruce_stairs", (new BlockStairs(var1, 1)).setBlockName("stairsWoodSpruce"));
      blockRegistry.addObject(135, "birch_stairs", (new BlockStairs(var1, 2)).setBlockName("stairsWoodBirch"));
      blockRegistry.addObject(136, "jungle_stairs", (new BlockStairs(var1, 3)).setBlockName("stairsWoodJungle"));
      blockRegistry.addObject(137, "command_block", (new BlockCommandBlock()).setBlockUnbreakable().setResistance(6000000.0F).setBlockName("commandBlock").setBlockTextureName("command_block"));
      blockRegistry.addObject(138, "beacon", (new BlockBeacon()).setBlockName("beacon").setLightLevel(1.0F).setBlockTextureName("beacon"));
      blockRegistry.addObject(139, "cobblestone_wall", (new BlockWall(var0)).setBlockName("cobbleWall"));
      blockRegistry.addObject(140, "flower_pot", (new BlockFlowerPot()).setHardness(0.0F).setStepSound(soundTypeStone).setBlockName("flowerPot").setBlockTextureName("flower_pot"));
      blockRegistry.addObject(141, "carrots", (new BlockCarrot()).setBlockName("carrots").setBlockTextureName("carrots"));
      blockRegistry.addObject(142, "potatoes", (new BlockPotato()).setBlockName("potatoes").setBlockTextureName("potatoes"));
      blockRegistry.addObject(143, "wooden_button", (new BlockButtonWood()).setHardness(0.5F).setStepSound(soundTypeWood).setBlockName("button"));
      blockRegistry.addObject(144, "skull", (new BlockSkull()).setHardness(1.0F).setStepSound(soundTypePiston).setBlockName("skull").setBlockTextureName("skull"));
      blockRegistry.addObject(145, "anvil", (new BlockAnvil()).setHardness(5.0F).setStepSound(soundTypeAnvil).setResistance(2000.0F).setBlockName("anvil"));
      blockRegistry.addObject(146, "trapped_chest", (new BlockChest(1)).setHardness(2.5F).setStepSound(soundTypeWood).setBlockName("chestTrap"));
      blockRegistry.addObject(147, "light_weighted_pressure_plate", (new BlockPressurePlateWeighted("gold_block", Material.iron, 15)).setHardness(0.5F).setStepSound(soundTypeWood).setBlockName("weightedPlate_light"));
      blockRegistry.addObject(148, "heavy_weighted_pressure_plate", (new BlockPressurePlateWeighted("iron_block", Material.iron, 150)).setHardness(0.5F).setStepSound(soundTypeWood).setBlockName("weightedPlate_heavy"));
      blockRegistry.addObject(149, "unpowered_comparator", (new BlockRedstoneComparator(false)).setHardness(0.0F).setStepSound(soundTypeWood).setBlockName("comparator").disableStats().setBlockTextureName("comparator_off"));
      blockRegistry.addObject(150, "powered_comparator", (new BlockRedstoneComparator(true)).setHardness(0.0F).setLightLevel(0.625F).setStepSound(soundTypeWood).setBlockName("comparator").disableStats().setBlockTextureName("comparator_on"));
      blockRegistry.addObject(151, "daylight_detector", (new BlockDaylightDetector()).setHardness(0.2F).setStepSound(soundTypeWood).setBlockName("daylightDetector").setBlockTextureName("daylight_detector"));
      blockRegistry.addObject(152, "redstone_block", (new BlockCompressedPowered(MapColor.tntColor)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setBlockName("blockRedstone").setBlockTextureName("redstone_block"));
      blockRegistry.addObject(153, "quartz_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("netherquartz").setBlockTextureName("quartz_ore"));
      blockRegistry.addObject(154, "hopper", (new BlockHopper()).setHardness(3.0F).setResistance(8.0F).setStepSound(soundTypeWood).setBlockName("hopper").setBlockTextureName("hopper"));
      Block var9 = (new BlockQuartz()).setStepSound(soundTypePiston).setHardness(0.8F).setBlockName("quartzBlock").setBlockTextureName("quartz_block");
      blockRegistry.addObject(155, "quartz_block", var9);
      blockRegistry.addObject(156, "quartz_stairs", (new BlockStairs(var9, 0)).setBlockName("stairsQuartz"));
      blockRegistry.addObject(157, "activator_rail", (new BlockRailPowered()).setHardness(0.7F).setStepSound(soundTypeMetal).setBlockName("activatorRail").setBlockTextureName("rail_activator"));
      blockRegistry.addObject(158, "dropper", (new BlockDropper()).setHardness(3.5F).setStepSound(soundTypePiston).setBlockName("dropper").setBlockTextureName("dropper"));
      blockRegistry.addObject(159, "stained_hardened_clay", (new BlockColored(Material.rock)).setHardness(1.25F).setResistance(7.0F).setStepSound(soundTypePiston).setBlockName("clayHardenedStained").setBlockTextureName("hardened_clay_stained"));
      blockRegistry.addObject(160, "stained_glass_pane", (new BlockStainedGlassPane()).setHardness(0.3F).setStepSound(soundTypeGlass).setBlockName("thinStainedGlass").setBlockTextureName("glass"));
      blockRegistry.addObject(161, "leaves2", (new BlockNewLeaf()).setBlockName("leaves").setBlockTextureName("leaves"));
      blockRegistry.addObject(162, "log2", (new BlockNewLog()).setBlockName("log").setBlockTextureName("log"));
      blockRegistry.addObject(163, "acacia_stairs", (new BlockStairs(var1, 4)).setBlockName("stairsWoodAcacia"));
      blockRegistry.addObject(164, "dark_oak_stairs", (new BlockStairs(var1, 5)).setBlockName("stairsWoodDarkOak"));
      blockRegistry.addObject(170, "hay_block", (new BlockHay()).setHardness(0.5F).setStepSound(soundTypeGrass).setBlockName("hayBlock").setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName("hay_block"));
      blockRegistry.addObject(171, "carpet", (new BlockCarpet()).setHardness(0.1F).setStepSound(soundTypeCloth).setBlockName("woolCarpet").setLightOpacity(0));
      blockRegistry.addObject(172, "hardened_clay", (new BlockHardenedClay()).setHardness(1.25F).setResistance(7.0F).setStepSound(soundTypePiston).setBlockName("clayHardened").setBlockTextureName("hardened_clay"));
      blockRegistry.addObject(173, "coal_block", (new Block(Material.rock)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("blockCoal").setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName("coal_block"));
      blockRegistry.addObject(174, "packed_ice", (new BlockPackedIce()).setHardness(0.5F).setStepSound(soundTypeGlass).setBlockName("icePacked").setBlockTextureName("ice_packed"));
      blockRegistry.addObject(175, "double_plant", new BlockDoublePlant());
      Iterator var10 = blockRegistry.iterator();

      while(var10.hasNext()) {
         Block var11 = (Block)var10.next();
         if(var11.blockMaterial == Material.air) {
            var11.useNeighborBrightness = false;
         } else {
            boolean var12 = false;
            boolean var13 = var11.getRenderType() == 10;
            boolean var14 = var11 instanceof BlockSlab;
            boolean var15 = var11 == var4;
            boolean var16 = var11.canBlockGrass;
            boolean var17 = var11.lightOpacity == 0;
            if(var13 || var14 || var15 || var16 || var17) {
               var12 = true;
            }

            var11.useNeighborBrightness = var12;
         }
      }

   }

   protected Block(Material var1) {
      this.stepSound = soundTypeStone;
      this.blockParticleGravity = 1.0F;
      this.slipperiness = 0.6F;
      this.blockMaterial = var1;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      this.opaque = this.isOpaqueCube();
      this.lightOpacity = this.isOpaqueCube()?255:0;
      this.canBlockGrass = !var1.getCanBlockGrass();
   }

   protected Block setStepSound(Block$SoundType var1) {
      this.stepSound = var1;
      return this;
   }

   protected Block setLightOpacity(int var1) {
      this.lightOpacity = var1;
      return this;
   }

   protected Block setLightLevel(float var1) {
      this.lightValue = (int)(15.0F * var1);
      return this;
   }

   protected Block setResistance(float var1) {
      this.blockResistance = var1 * 3.0F;
      return this;
   }

   public boolean isBlockNormalCube() {
      return this.blockMaterial.blocksMovement() && this.renderAsNormalBlock();
   }

   public boolean isNormalCube() {
      return this.blockMaterial.isOpaque() && this.renderAsNormalBlock() && !this.canProvidePower();
   }

   public boolean renderAsNormalBlock() {
      return true;
   }

   public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4) {
      return !this.blockMaterial.blocksMovement();
   }

   public int getRenderType() {
      return 0;
   }

   protected Block setHardness(float var1) {
      this.blockHardness = var1;
      if(this.blockResistance < var1 * 5.0F) {
         this.blockResistance = var1 * 5.0F;
      }

      return this;
   }

   protected Block setBlockUnbreakable() {
      this.setHardness(-1.0F);
      return this;
   }

   public float getBlockHardness(World var1, int var2, int var3, int var4) {
      return this.blockHardness;
   }

   protected Block setTickRandomly(boolean var1) {
      this.needsRandomTick = var1;
      return this;
   }

   public boolean getTickRandomly() {
      return this.needsRandomTick;
   }

   public boolean hasTileEntity() {
      return this.isBlockContainer;
   }

   protected final void setBlockBounds(float var1, float var2, float var3, float var4, float var5, float var6) {
      this.minX = (double)var1;
      this.minY = (double)var2;
      this.minZ = (double)var3;
      this.maxX = (double)var4;
      this.maxY = (double)var5;
      this.maxZ = (double)var6;
   }

   public int getMixedBrightnessForBlock(IBlockAccess var1, int var2, int var3, int var4) {
      Block var5 = var1.getBlock(var2, var3, var4);
      int var6 = var1.getLightBrightnessForSkyBlocks(var2, var3, var4, var5.getLightValue());
      if(var6 == 0 && var5 instanceof BlockSlab) {
         --var3;
         var5 = var1.getBlock(var2, var3, var4);
         return var1.getLightBrightnessForSkyBlocks(var2, var3, var4, var5.getLightValue());
      } else {
         return var6;
      }
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return var5 == 0 && this.minY > 0.0D?true:(var5 == 1 && this.maxY < 1.0D?true:(var5 == 2 && this.minZ > 0.0D?true:(var5 == 3 && this.maxZ < 1.0D?true:(var5 == 4 && this.minX > 0.0D?true:(var5 == 5 && this.maxX < 1.0D?true:!var1.getBlock(var2, var3, var4).isOpaqueCube())))));
   }

   public boolean isBlockSolid(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return var1.getBlock(var2, var3, var4).getMaterial().isSolid();
   }

   public IIcon getIcon(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return this.getIcon(var5, var1.getBlockMetadata(var2, var3, var4));
   }

   public IIcon getIcon(int var1, int var2) {
      return this.blockIcon;
   }

   public final IIcon getBlockTextureFromSide(int var1) {
      return this.getIcon(var1, 0);
   }

   public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return AxisAlignedBB.getBoundingBox((double)var2 + this.minX, (double)var3 + this.minY, (double)var4 + this.minZ, (double)var2 + this.maxX, (double)var3 + this.maxY, (double)var4 + this.maxZ);
   }

   public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
      AxisAlignedBB var8 = this.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
      if(var8 != null && var5.intersectsWith(var8)) {
         var6.add(var8);
      }

   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return AxisAlignedBB.getBoundingBox((double)var2 + this.minX, (double)var3 + this.minY, (double)var4 + this.minZ, (double)var2 + this.maxX, (double)var3 + this.maxY, (double)var4 + this.maxZ);
   }

   public boolean isOpaqueCube() {
      return true;
   }

   public boolean canCollideCheck(int var1, boolean var2) {
      return this.isCollidable();
   }

   public boolean isCollidable() {
      return true;
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {}

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {}

   public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5) {}

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {}

   public int tickRate(World var1) {
      return 10;
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {}

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {}

   public int quantityDropped(Random var1) {
      return 1;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Item.getItemFromBlock(this);
   }

   public float getPlayerRelativeBlockHardness(EntityPlayer var1, World var2, int var3, int var4, int var5) {
      float var6 = this.getBlockHardness(var2, var3, var4, var5);
      return var6 < 0.0F?0.0F:(!var1.canHarvestBlock(this)?var1.getCurrentPlayerStrVsBlock(this, false) / var6 / 100.0F:var1.getCurrentPlayerStrVsBlock(this, true) / var6 / 30.0F);
   }

   public final void dropBlockAsItem(World var1, int var2, int var3, int var4, int var5, int var6) {
      this.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, 1.0F, var6);
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      if(!var1.isRemote) {
         int var8 = this.quantityDroppedWithBonus(var7, var1.rand);

         for(int var9 = 0; var9 < var8; ++var9) {
            if(var1.rand.nextFloat() <= var6) {
               Item var10 = this.getItemDropped(var5, var1.rand, var7);
               if(var10 != null) {
                  this.dropBlockAsItem(var1, var2, var3, var4, new ItemStack(var10, 1, this.damageDropped(var5)));
               }
            }
         }

      }
   }

   protected void dropBlockAsItem(World var1, int var2, int var3, int var4, ItemStack var5) {
      if(!var1.isRemote && var1.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
         float var6 = 0.7F;
         double var7 = (double)(var1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
         double var9 = (double)(var1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
         double var11 = (double)(var1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
         EntityItem var13 = new EntityItem(var1, (double)var2 + var7, (double)var3 + var9, (double)var4 + var11, var5);
         var13.delayBeforeCanPickup = 10;
         var1.spawnEntityInWorld(var13);
      }
   }

   protected void dropXpOnBlockBreak(World var1, int var2, int var3, int var4, int var5) {
      if(!var1.isRemote) {
         while(var5 > 0) {
            int var6 = EntityXPOrb.getXPSplit(var5);
            var5 -= var6;
            var1.spawnEntityInWorld(new EntityXPOrb(var1, (double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, var6));
         }
      }

   }

   public int damageDropped(int var1) {
      return 0;
   }

   public float getExplosionResistance(Entity var1) {
      return this.blockResistance / 5.0F;
   }

   public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      var5 = var5.addVector((double)(-var2), (double)(-var3), (double)(-var4));
      var6 = var6.addVector((double)(-var2), (double)(-var3), (double)(-var4));
      Vec3 var7 = var5.getIntermediateWithXValue(var6, this.minX);
      Vec3 var8 = var5.getIntermediateWithXValue(var6, this.maxX);
      Vec3 var9 = var5.getIntermediateWithYValue(var6, this.minY);
      Vec3 var10 = var5.getIntermediateWithYValue(var6, this.maxY);
      Vec3 var11 = var5.getIntermediateWithZValue(var6, this.minZ);
      Vec3 var12 = var5.getIntermediateWithZValue(var6, this.maxZ);
      if(!this.isVecInsideYZBounds(var7)) {
         var7 = null;
      }

      if(!this.isVecInsideYZBounds(var8)) {
         var8 = null;
      }

      if(!this.isVecInsideXZBounds(var9)) {
         var9 = null;
      }

      if(!this.isVecInsideXZBounds(var10)) {
         var10 = null;
      }

      if(!this.isVecInsideXYBounds(var11)) {
         var11 = null;
      }

      if(!this.isVecInsideXYBounds(var12)) {
         var12 = null;
      }

      Vec3 var13 = null;
      if(var7 != null && (var13 == null || var5.squareDistanceTo(var7) < var5.squareDistanceTo(var13))) {
         var13 = var7;
      }

      if(var8 != null && (var13 == null || var5.squareDistanceTo(var8) < var5.squareDistanceTo(var13))) {
         var13 = var8;
      }

      if(var9 != null && (var13 == null || var5.squareDistanceTo(var9) < var5.squareDistanceTo(var13))) {
         var13 = var9;
      }

      if(var10 != null && (var13 == null || var5.squareDistanceTo(var10) < var5.squareDistanceTo(var13))) {
         var13 = var10;
      }

      if(var11 != null && (var13 == null || var5.squareDistanceTo(var11) < var5.squareDistanceTo(var13))) {
         var13 = var11;
      }

      if(var12 != null && (var13 == null || var5.squareDistanceTo(var12) < var5.squareDistanceTo(var13))) {
         var13 = var12;
      }

      if(var13 == null) {
         return null;
      } else {
         byte var14 = -1;
         if(var13 == var7) {
            var14 = 4;
         }

         if(var13 == var8) {
            var14 = 5;
         }

         if(var13 == var9) {
            var14 = 0;
         }

         if(var13 == var10) {
            var14 = 1;
         }

         if(var13 == var11) {
            var14 = 2;
         }

         if(var13 == var12) {
            var14 = 3;
         }

         return new MovingObjectPosition(var2, var3, var4, var14, var13.addVector((double)var2, (double)var3, (double)var4));
      }
   }

   private boolean isVecInsideYZBounds(Vec3 var1) {
      return var1 == null?false:var1.yCoord >= this.minY && var1.yCoord <= this.maxY && var1.zCoord >= this.minZ && var1.zCoord <= this.maxZ;
   }

   private boolean isVecInsideXZBounds(Vec3 var1) {
      return var1 == null?false:var1.xCoord >= this.minX && var1.xCoord <= this.maxX && var1.zCoord >= this.minZ && var1.zCoord <= this.maxZ;
   }

   private boolean isVecInsideXYBounds(Vec3 var1) {
      return var1 == null?false:var1.xCoord >= this.minX && var1.xCoord <= this.maxX && var1.yCoord >= this.minY && var1.yCoord <= this.maxY;
   }

   public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5) {}

   public int getRenderBlockPass() {
      return 0;
   }

   public boolean canReplace(World var1, int var2, int var3, int var4, int var5, ItemStack var6) {
      return this.canPlaceBlockOnSide(var1, var2, var3, var4, var5);
   }

   public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5) {
      return this.canPlaceBlockAt(var1, var2, var3, var4);
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return var1.getBlock(var2, var3, var4).blockMaterial.isReplaceable();
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      return false;
   }

   public void onEntityWalking(World var1, int var2, int var3, int var4, Entity var5) {}

   public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9) {
      return var9;
   }

   public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {}

   public void velocityToAddToEntity(World var1, int var2, int var3, int var4, Entity var5, Vec3 var6) {}

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

   public final double getBlockBoundsMinX() {
      return this.minX;
   }

   public final double getBlockBoundsMaxX() {
      return this.maxX;
   }

   public final double getBlockBoundsMinY() {
      return this.minY;
   }

   public final double getBlockBoundsMaxY() {
      return this.maxY;
   }

   public final double getBlockBoundsMinZ() {
      return this.minZ;
   }

   public final double getBlockBoundsMaxZ() {
      return this.maxZ;
   }

   public int getBlockColor() {
      return 16777215;
   }

   public int getRenderColor(int var1) {
      return 16777215;
   }

   public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
      return 16777215;
   }

   public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return 0;
   }

   public boolean canProvidePower() {
      return false;
   }

   public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {}

   public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return 0;
   }

   public void setBlockBoundsForItemRender() {}

   public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6) {
      var2.addStat(StatList.mineBlockStatArray[getIdFromBlock(this)], 1);
      var2.addExhaustion(0.025F);
      if(this.canSilkHarvest() && EnchantmentHelper.getSilkTouchModifier(var2)) {
         ItemStack var8 = this.createStackedBlock(var6);
         if(var8 != null) {
            this.dropBlockAsItem(var1, var3, var4, var5, var8);
         }
      } else {
         int var7 = EnchantmentHelper.getFortuneModifier(var2);
         this.dropBlockAsItem(var1, var3, var4, var5, var6, var7);
      }

   }

   protected boolean canSilkHarvest() {
      return this.renderAsNormalBlock() && !this.isBlockContainer;
   }

   protected ItemStack createStackedBlock(int var1) {
      int var2 = 0;
      Item var3 = Item.getItemFromBlock(this);
      if(var3 != null && var3.getHasSubtypes()) {
         var2 = var1;
      }

      return new ItemStack(var3, 1, var2);
   }

   public int quantityDroppedWithBonus(int var1, Random var2) {
      return this.quantityDropped(var2);
   }

   public boolean canBlockStay(World var1, int var2, int var3, int var4) {
      return true;
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {}

   public void onPostBlockPlaced(World var1, int var2, int var3, int var4, int var5) {}

   public Block setBlockName(String var1) {
      this.unlocalizedName = var1;
      return this;
   }

   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
   }

   public String getUnlocalizedName() {
      return "tile." + this.unlocalizedName;
   }

   public boolean onBlockEventReceived(World var1, int var2, int var3, int var4, int var5, int var6) {
      return false;
   }

   public boolean getEnableStats() {
      return this.enableStats;
   }

   protected Block disableStats() {
      this.enableStats = false;
      return this;
   }

   public int getMobilityFlag() {
      return this.blockMaterial.getMaterialMobility();
   }

   public float getAmbientOcclusionLightValue() {
      return this.isBlockNormalCube()?0.2F:1.0F;
   }

   public void onFallenUpon(World var1, int var2, int var3, int var4, Entity var5, float var6) {}

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Item.getItemFromBlock(this);
   }

   public int getDamageValue(World var1, int var2, int var3, int var4) {
      return this.damageDropped(var1.getBlockMetadata(var2, var3, var4));
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      var3.add(new ItemStack(var1, 1, 0));
   }

   public CreativeTabs getCreativeTabToDisplayOn() {
      return this.displayOnCreativeTab;
   }

   public Block setCreativeTab(CreativeTabs var1) {
      this.displayOnCreativeTab = var1;
      return this;
   }

   public void onBlockHarvested(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6) {}

   public void onBlockPreDestroy(World var1, int var2, int var3, int var4, int var5) {}

   public void fillWithRain(World var1, int var2, int var3, int var4) {}

   public boolean isFlowerPot() {
      return false;
   }

   public boolean func_149698_L() {
      return true;
   }

   public boolean canDropFromExplosion(Explosion var1) {
      return true;
   }

   public boolean isAssociatedBlock(Block var1) {
      return this == var1;
   }

   public static boolean isEqualTo(Block var0, Block var1) {
      return var0 != null && var1 != null?(var0 == var1?true:var0.isAssociatedBlock(var1)):false;
   }

   public boolean hasComparatorInputOverride() {
      return false;
   }

   public int getComparatorInputOverride(World var1, int var2, int var3, int var4, int var5) {
      return 0;
   }

   protected Block setBlockTextureName(String var1) {
      this.textureName = var1;
      return this;
   }

   protected String getTextureName() {
      return this.textureName == null?"MISSING_ICON_BLOCK_" + getIdFromBlock(this) + "_" + this.unlocalizedName:this.textureName;
   }

   public IIcon func_149735_b(int var1, int var2) {
      return this.getIcon(var1, var2);
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.blockIcon = var1.registerIcon(this.getTextureName());
   }

   public String getItemIconName() {
      return null;
   }

}
