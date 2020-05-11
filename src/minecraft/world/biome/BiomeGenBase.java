package net.minecraft.world.biome;

import com.google.common.collect.Sets;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase$Height;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.minecraft.world.biome.BiomeGenBase$TempCategory;
import net.minecraft.world.biome.BiomeGenBeach;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenHell;
import net.minecraft.world.biome.BiomeGenHills;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenMesa;
import net.minecraft.world.biome.BiomeGenMushroomIsland;
import net.minecraft.world.biome.BiomeGenMutated;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenRiver;
import net.minecraft.world.biome.BiomeGenSavanna;
import net.minecraft.world.biome.BiomeGenSnow;
import net.minecraft.world.biome.BiomeGenStoneBeach;
import net.minecraft.world.biome.BiomeGenSwamp;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BiomeGenBase {

   private static final Logger logger = LogManager.getLogger();
   protected static final BiomeGenBase$Height height_Default = new BiomeGenBase$Height(0.1F, 0.2F);
   protected static final BiomeGenBase$Height height_ShallowWaters = new BiomeGenBase$Height(-0.5F, 0.0F);
   protected static final BiomeGenBase$Height height_Oceans = new BiomeGenBase$Height(-1.0F, 0.1F);
   protected static final BiomeGenBase$Height height_DeepOceans = new BiomeGenBase$Height(-1.8F, 0.1F);
   protected static final BiomeGenBase$Height height_LowPlains = new BiomeGenBase$Height(0.125F, 0.05F);
   protected static final BiomeGenBase$Height height_MidPlains = new BiomeGenBase$Height(0.2F, 0.2F);
   protected static final BiomeGenBase$Height height_LowHills = new BiomeGenBase$Height(0.45F, 0.3F);
   protected static final BiomeGenBase$Height height_HighPlateaus = new BiomeGenBase$Height(1.5F, 0.025F);
   protected static final BiomeGenBase$Height height_MidHills = new BiomeGenBase$Height(1.0F, 0.5F);
   protected static final BiomeGenBase$Height height_Shores = new BiomeGenBase$Height(0.0F, 0.025F);
   protected static final BiomeGenBase$Height height_RockyWaters = new BiomeGenBase$Height(0.1F, 0.8F);
   protected static final BiomeGenBase$Height height_LowIslands = new BiomeGenBase$Height(0.2F, 0.3F);
   protected static final BiomeGenBase$Height height_PartiallySubmerged = new BiomeGenBase$Height(-0.2F, 0.1F);
   private static final BiomeGenBase[] biomeList = new BiomeGenBase[256];
   public static final Set explorationBiomesList = Sets.newHashSet();
   public static final BiomeGenBase ocean = (new BiomeGenOcean(0)).setColor(112).setBiomeName("Ocean").setHeight(height_Oceans);
   public static final BiomeGenBase plains = (new BiomeGenPlains(1)).setColor(9286496).setBiomeName("Plains");
   public static final BiomeGenBase desert = (new BiomeGenDesert(2)).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setHeight(height_LowPlains);
   public static final BiomeGenBase extremeHills = (new BiomeGenHills(3, false)).setColor(6316128).setBiomeName("Extreme Hills").setHeight(height_MidHills).setTemperatureRainfall(0.2F, 0.3F);
   public static final BiomeGenBase forest = (new BiomeGenForest(4, 0)).setColor(353825).setBiomeName("Forest");
   public static final BiomeGenBase taiga = (new BiomeGenTaiga(5, 0)).setColor(747097).setBiomeName("Taiga").func_76733_a(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(height_MidPlains);
   public static final BiomeGenBase swampland = (new BiomeGenSwamp(6)).setColor(522674).setBiomeName("Swampland").func_76733_a(9154376).setHeight(height_PartiallySubmerged).setTemperatureRainfall(0.8F, 0.9F);
   public static final BiomeGenBase river = (new BiomeGenRiver(7)).setColor(255).setBiomeName("River").setHeight(height_ShallowWaters);
   public static final BiomeGenBase hell = (new BiomeGenHell(8)).setColor(16711680).setBiomeName("Hell").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
   public static final BiomeGenBase sky = (new BiomeGenEnd(9)).setColor(8421631).setBiomeName("Sky").setDisableRain();
   public static final BiomeGenBase frozenOcean = (new BiomeGenOcean(10)).setColor(9474208).setBiomeName("FrozenOcean").setEnableSnow().setHeight(height_Oceans).setTemperatureRainfall(0.0F, 0.5F);
   public static final BiomeGenBase frozenRiver = (new BiomeGenRiver(11)).setColor(10526975).setBiomeName("FrozenRiver").setEnableSnow().setHeight(height_ShallowWaters).setTemperatureRainfall(0.0F, 0.5F);
   public static final BiomeGenBase icePlains = (new BiomeGenSnow(12, false)).setColor(16777215).setBiomeName("Ice Plains").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F).setHeight(height_LowPlains);
   public static final BiomeGenBase iceMountains = (new BiomeGenSnow(13, false)).setColor(10526880).setBiomeName("Ice Mountains").setEnableSnow().setHeight(height_LowHills).setTemperatureRainfall(0.0F, 0.5F);
   public static final BiomeGenBase mushroomIsland = (new BiomeGenMushroomIsland(14)).setColor(16711935).setBiomeName("MushroomIsland").setTemperatureRainfall(0.9F, 1.0F).setHeight(height_LowIslands);
   public static final BiomeGenBase mushroomIslandShore = (new BiomeGenMushroomIsland(15)).setColor(10486015).setBiomeName("MushroomIslandShore").setTemperatureRainfall(0.9F, 1.0F).setHeight(height_Shores);
   public static final BiomeGenBase beach = (new BiomeGenBeach(16)).setColor(16440917).setBiomeName("Beach").setTemperatureRainfall(0.8F, 0.4F).setHeight(height_Shores);
   public static final BiomeGenBase desertHills = (new BiomeGenDesert(17)).setColor(13786898).setBiomeName("DesertHills").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setHeight(height_LowHills);
   public static final BiomeGenBase forestHills = (new BiomeGenForest(18, 0)).setColor(2250012).setBiomeName("ForestHills").setHeight(height_LowHills);
   public static final BiomeGenBase taigaHills = (new BiomeGenTaiga(19, 0)).setColor(1456435).setBiomeName("TaigaHills").func_76733_a(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(height_LowHills);
   public static final BiomeGenBase extremeHillsEdge = (new BiomeGenHills(20, true)).setColor(7501978).setBiomeName("Extreme Hills Edge").setHeight(height_MidHills.attenuate()).setTemperatureRainfall(0.2F, 0.3F);
   public static final BiomeGenBase jungle = (new BiomeGenJungle(21, false)).setColor(5470985).setBiomeName("Jungle").func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.9F);
   public static final BiomeGenBase jungleHills = (new BiomeGenJungle(22, false)).setColor(2900485).setBiomeName("JungleHills").func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.9F).setHeight(height_LowHills);
   public static final BiomeGenBase jungleEdge = (new BiomeGenJungle(23, true)).setColor(6458135).setBiomeName("JungleEdge").func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.8F);
   public static final BiomeGenBase deepOcean = (new BiomeGenOcean(24)).setColor(48).setBiomeName("Deep Ocean").setHeight(height_DeepOceans);
   public static final BiomeGenBase stoneBeach = (new BiomeGenStoneBeach(25)).setColor(10658436).setBiomeName("Stone Beach").setTemperatureRainfall(0.2F, 0.3F).setHeight(height_RockyWaters);
   public static final BiomeGenBase coldBeach = (new BiomeGenBeach(26)).setColor(16445632).setBiomeName("Cold Beach").setTemperatureRainfall(0.05F, 0.3F).setHeight(height_Shores).setEnableSnow();
   public static final BiomeGenBase birchForest = (new BiomeGenForest(27, 2)).setBiomeName("Birch Forest").setColor(3175492);
   public static final BiomeGenBase birchForestHills = (new BiomeGenForest(28, 2)).setBiomeName("Birch Forest Hills").setColor(2055986).setHeight(height_LowHills);
   public static final BiomeGenBase roofedForest = (new BiomeGenForest(29, 3)).setColor(4215066).setBiomeName("Roofed Forest");
   public static final BiomeGenBase coldTaiga = (new BiomeGenTaiga(30, 0)).setColor(3233098).setBiomeName("Cold Taiga").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(-0.5F, 0.4F).setHeight(height_MidPlains).func_150563_c(16777215);
   public static final BiomeGenBase coldTaigaHills = (new BiomeGenTaiga(31, 0)).setColor(2375478).setBiomeName("Cold Taiga Hills").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(-0.5F, 0.4F).setHeight(height_LowHills).func_150563_c(16777215);
   public static final BiomeGenBase megaTaiga = (new BiomeGenTaiga(32, 1)).setColor(5858897).setBiomeName("Mega Taiga").func_76733_a(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_MidPlains);
   public static final BiomeGenBase megaTaigaHills = (new BiomeGenTaiga(33, 1)).setColor(4542270).setBiomeName("Mega Taiga Hills").func_76733_a(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_LowHills);
   public static final BiomeGenBase extremeHillsPlus = (new BiomeGenHills(34, true)).setColor(5271632).setBiomeName("Extreme Hills+").setHeight(height_MidHills).setTemperatureRainfall(0.2F, 0.3F);
   public static final BiomeGenBase savanna = (new BiomeGenSavanna(35)).setColor(12431967).setBiomeName("Savanna").setTemperatureRainfall(1.2F, 0.0F).setDisableRain().setHeight(height_LowPlains);
   public static final BiomeGenBase savannaPlateau = (new BiomeGenSavanna(36)).setColor(10984804).setBiomeName("Savanna Plateau").setTemperatureRainfall(1.0F, 0.0F).setDisableRain().setHeight(height_HighPlateaus);
   public static final BiomeGenBase mesa = (new BiomeGenMesa(37, false, false)).setColor(14238997).setBiomeName("Mesa");
   public static final BiomeGenBase mesaPlateau_F = (new BiomeGenMesa(38, false, true)).setColor(11573093).setBiomeName("Mesa Plateau F").setHeight(height_HighPlateaus);
   public static final BiomeGenBase mesaPlateau = (new BiomeGenMesa(39, false, false)).setColor(13274213).setBiomeName("Mesa Plateau").setHeight(height_HighPlateaus);
   protected static final NoiseGeneratorPerlin temperatureNoise;
   protected static final NoiseGeneratorPerlin plantNoise;
   protected static final WorldGenDoublePlant genTallFlowers;
   public String biomeName;
   public int color;
   public int field_150609_ah;
   public Block topBlock;
   public int field_150604_aj;
   public Block fillerBlock;
   public int field_76754_C;
   public float rootHeight;
   public float heightVariation;
   public float temperature;
   public float rainfall;
   public int waterColorMultiplier;
   public BiomeDecorator theBiomeDecorator;
   protected List spawnableMonsterList;
   protected List spawnableCreatureList;
   protected List spawnableWaterCreatureList;
   protected List spawnableCaveCreatureList;
   protected boolean enableSnow;
   protected boolean enableRain;
   public final int biomeID;
   protected WorldGenTrees worldGeneratorTrees;
   protected WorldGenBigTree worldGeneratorBigTree;
   protected WorldGenSwamp worldGeneratorSwamp;


   protected BiomeGenBase(int var1) {
      this.topBlock = Blocks.grass;
      this.field_150604_aj = 0;
      this.fillerBlock = Blocks.dirt;
      this.field_76754_C = 5169201;
      this.rootHeight = height_Default.rootHeight;
      this.heightVariation = height_Default.variation;
      this.temperature = 0.5F;
      this.rainfall = 0.5F;
      this.waterColorMultiplier = 16777215;
      this.spawnableMonsterList = new ArrayList();
      this.spawnableCreatureList = new ArrayList();
      this.spawnableWaterCreatureList = new ArrayList();
      this.spawnableCaveCreatureList = new ArrayList();
      this.enableRain = true;
      this.worldGeneratorTrees = new WorldGenTrees(false);
      this.worldGeneratorBigTree = new WorldGenBigTree(false);
      this.worldGeneratorSwamp = new WorldGenSwamp();
      this.biomeID = var1;
      biomeList[var1] = this;
      this.theBiomeDecorator = this.createBiomeDecorator();
      this.spawnableCreatureList.add(new BiomeGenBase$SpawnListEntry(EntitySheep.class, 12, 4, 4));
      this.spawnableCreatureList.add(new BiomeGenBase$SpawnListEntry(EntityPig.class, 10, 4, 4));
      this.spawnableCreatureList.add(new BiomeGenBase$SpawnListEntry(EntityChicken.class, 10, 4, 4));
      this.spawnableCreatureList.add(new BiomeGenBase$SpawnListEntry(EntityCow.class, 8, 4, 4));
      this.spawnableMonsterList.add(new BiomeGenBase$SpawnListEntry(EntitySpider.class, 100, 4, 4));
      this.spawnableMonsterList.add(new BiomeGenBase$SpawnListEntry(EntityZombie.class, 100, 4, 4));
      this.spawnableMonsterList.add(new BiomeGenBase$SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
      this.spawnableMonsterList.add(new BiomeGenBase$SpawnListEntry(EntityCreeper.class, 100, 4, 4));
      this.spawnableMonsterList.add(new BiomeGenBase$SpawnListEntry(EntitySlime.class, 100, 4, 4));
      this.spawnableMonsterList.add(new BiomeGenBase$SpawnListEntry(EntityEnderman.class, 10, 1, 4));
      this.spawnableMonsterList.add(new BiomeGenBase$SpawnListEntry(EntityWitch.class, 5, 1, 1));
      this.spawnableWaterCreatureList.add(new BiomeGenBase$SpawnListEntry(EntitySquid.class, 10, 4, 4));
      this.spawnableCaveCreatureList.add(new BiomeGenBase$SpawnListEntry(EntityBat.class, 10, 8, 8));
   }

   protected BiomeDecorator createBiomeDecorator() {
      return new BiomeDecorator();
   }

   protected BiomeGenBase setTemperatureRainfall(float var1, float var2) {
      if(var1 > 0.1F && var1 < 0.2F) {
         throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
      } else {
         this.temperature = var1;
         this.rainfall = var2;
         return this;
      }
   }

   protected final BiomeGenBase setHeight(BiomeGenBase$Height var1) {
      this.rootHeight = var1.rootHeight;
      this.heightVariation = var1.variation;
      return this;
   }

   protected BiomeGenBase setDisableRain() {
      this.enableRain = false;
      return this;
   }

   public WorldGenAbstractTree func_150567_a(Random var1) {
      return (WorldGenAbstractTree)(var1.nextInt(10) == 0?this.worldGeneratorBigTree:this.worldGeneratorTrees);
   }

   public WorldGenerator getRandomWorldGenForGrass(Random var1) {
      return new WorldGenTallGrass(Blocks.tallgrass, 1);
   }

   public String func_150572_a(Random var1, int var2, int var3, int var4) {
      return var1.nextInt(3) > 0?BlockFlower.field_149858_b[0]:BlockFlower.field_149859_a[0];
   }

   protected BiomeGenBase setEnableSnow() {
      this.enableSnow = true;
      return this;
   }

   protected BiomeGenBase setBiomeName(String var1) {
      this.biomeName = var1;
      return this;
   }

   protected BiomeGenBase func_76733_a(int var1) {
      this.field_76754_C = var1;
      return this;
   }

   protected BiomeGenBase setColor(int var1) {
      this.func_150557_a(var1, false);
      return this;
   }

   protected BiomeGenBase func_150563_c(int var1) {
      this.field_150609_ah = var1;
      return this;
   }

   protected BiomeGenBase func_150557_a(int var1, boolean var2) {
      this.color = var1;
      if(var2) {
         this.field_150609_ah = (var1 & 16711422) >> 1;
      } else {
         this.field_150609_ah = var1;
      }

      return this;
   }

   public int getSkyColorByTemp(float var1) {
      var1 /= 3.0F;
      if(var1 < -1.0F) {
         var1 = -1.0F;
      }

      if(var1 > 1.0F) {
         var1 = 1.0F;
      }

      return Color.getHSBColor(0.62222224F - var1 * 0.05F, 0.5F + var1 * 0.1F, 1.0F).getRGB();
   }

   public List getSpawnableList(EnumCreatureType var1) {
      return var1 == EnumCreatureType.monster?this.spawnableMonsterList:(var1 == EnumCreatureType.creature?this.spawnableCreatureList:(var1 == EnumCreatureType.waterCreature?this.spawnableWaterCreatureList:(var1 == EnumCreatureType.ambient?this.spawnableCaveCreatureList:null)));
   }

   public boolean getEnableSnow() {
      return this.func_150559_j();
   }

   public boolean canSpawnLightningBolt() {
      return this.func_150559_j()?false:this.enableRain;
   }

   public boolean isHighHumidity() {
      return this.rainfall > 0.85F;
   }

   public float getSpawningChance() {
      return 0.1F;
   }

   public final int getIntRainfall() {
      return (int)(this.rainfall * 65536.0F);
   }

   public final float getFloatRainfall() {
      return this.rainfall;
   }

   public final float getFloatTemperature(int var1, int var2, int var3) {
      if(var2 > 64) {
         float var4 = (float)temperatureNoise.func_151601_a((double)var1 * 1.0D / 8.0D, (double)var3 * 1.0D / 8.0D) * 4.0F;
         return this.temperature - (var4 + (float)var2 - 64.0F) * 0.05F / 30.0F;
      } else {
         return this.temperature;
      }
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      this.theBiomeDecorator.decorateChunk(var1, var2, this, var3, var4);
   }

   public int getBiomeGrassColor(int var1, int var2, int var3) {
      double var4 = (double)MathHelper.clamp_float(this.getFloatTemperature(var1, var2, var3), 0.0F, 1.0F);
      double var6 = (double)MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
      return ColorizerGrass.getGrassColor(var4, var6);
   }

   public int getBiomeFoliageColor(int var1, int var2, int var3) {
      double var4 = (double)MathHelper.clamp_float(this.getFloatTemperature(var1, var2, var3), 0.0F, 1.0F);
      double var6 = (double)MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
      return ColorizerFoliage.getFoliageColor(var4, var6);
   }

   public boolean func_150559_j() {
      return this.enableSnow;
   }

   public void genTerrainBlocks(World var1, Random var2, Block[] var3, byte[] var4, int var5, int var6, double var7) {
      this.genBiomeTerrain(var1, var2, var3, var4, var5, var6, var7);
   }

   public final void genBiomeTerrain(World var1, Random var2, Block[] var3, byte[] var4, int var5, int var6, double var7) {
      boolean var9 = true;
      Block var10 = this.topBlock;
      byte var11 = (byte)(this.field_150604_aj & 255);
      Block var12 = this.fillerBlock;
      int var13 = -1;
      int var14 = (int)(var7 / 3.0D + 3.0D + var2.nextDouble() * 0.25D);
      int var15 = var5 & 15;
      int var16 = var6 & 15;
      int var17 = var3.length / 256;

      for(int var18 = 255; var18 >= 0; --var18) {
         int var19 = (var16 * 16 + var15) * var17 + var18;
         if(var18 <= 0 + var2.nextInt(5)) {
            var3[var19] = Blocks.bedrock;
         } else {
            Block var20 = var3[var19];
            if(var20 != null && var20.getMaterial() != Material.air) {
               if(var20 == Blocks.stone) {
                  if(var13 == -1) {
                     if(var14 <= 0) {
                        var10 = null;
                        var11 = 0;
                        var12 = Blocks.stone;
                     } else if(var18 >= 59 && var18 <= 64) {
                        var10 = this.topBlock;
                        var11 = (byte)(this.field_150604_aj & 255);
                        var12 = this.fillerBlock;
                     }

                     if(var18 < 63 && (var10 == null || var10.getMaterial() == Material.air)) {
                        if(this.getFloatTemperature(var5, var18, var6) < 0.15F) {
                           var10 = Blocks.ice;
                           var11 = 0;
                        } else {
                           var10 = Blocks.water;
                           var11 = 0;
                        }
                     }

                     var13 = var14;
                     if(var18 >= 62) {
                        var3[var19] = var10;
                        var4[var19] = var11;
                     } else if(var18 < 56 - var14) {
                        var10 = null;
                        var12 = Blocks.stone;
                        var3[var19] = Blocks.gravel;
                     } else {
                        var3[var19] = var12;
                     }
                  } else if(var13 > 0) {
                     --var13;
                     var3[var19] = var12;
                     if(var13 == 0 && var12 == Blocks.sand) {
                        var13 = var2.nextInt(4) + Math.max(0, var18 - 63);
                        var12 = Blocks.sandstone;
                     }
                  }
               }
            } else {
               var13 = -1;
            }
         }
      }

   }

   protected BiomeGenBase createMutation() {
      return new BiomeGenMutated(this.biomeID + 128, this);
   }

   public Class getBiomeClass() {
      return this.getClass();
   }

   public boolean isEqualTo(BiomeGenBase var1) {
      return var1 == this?true:(var1 == null?false:this.getBiomeClass() == var1.getBiomeClass());
   }

   public BiomeGenBase$TempCategory getTempCategory() {
      return (double)this.temperature < 0.2D?BiomeGenBase$TempCategory.COLD:((double)this.temperature < 1.0D?BiomeGenBase$TempCategory.MEDIUM:BiomeGenBase$TempCategory.WARM);
   }

   public static BiomeGenBase[] getBiomeGenArray() {
      return biomeList;
   }

   public static BiomeGenBase getBiome(int var0) {
      if(var0 >= 0 && var0 <= biomeList.length) {
         return biomeList[var0];
      } else {
         logger.warn("Biome ID is out of bounds: " + var0 + ", defaulting to 0 (Ocean)");
         return ocean;
      }
   }

   static {
      plains.createMutation();
      desert.createMutation();
      forest.createMutation();
      taiga.createMutation();
      swampland.createMutation();
      icePlains.createMutation();
      jungle.createMutation();
      jungleEdge.createMutation();
      coldTaiga.createMutation();
      savanna.createMutation();
      savannaPlateau.createMutation();
      mesa.createMutation();
      mesaPlateau_F.createMutation();
      mesaPlateau.createMutation();
      birchForest.createMutation();
      birchForestHills.createMutation();
      roofedForest.createMutation();
      megaTaiga.createMutation();
      extremeHills.createMutation();
      extremeHillsPlus.createMutation();
      biomeList[megaTaigaHills.biomeID + 128] = biomeList[megaTaiga.biomeID + 128];
      BiomeGenBase[] var0 = biomeList;
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         BiomeGenBase var3 = var0[var2];
         if(var3 != null && var3.biomeID < 128) {
            explorationBiomesList.add(var3);
         }
      }

      explorationBiomesList.remove(hell);
      explorationBiomesList.remove(sky);
      explorationBiomesList.remove(frozenOcean);
      explorationBiomesList.remove(extremeHillsEdge);
      temperatureNoise = new NoiseGeneratorPerlin(new Random(1234L), 1);
      plantNoise = new NoiseGeneratorPerlin(new Random(2345L), 1);
      genTallFlowers = new WorldGenDoublePlant();
   }
}
