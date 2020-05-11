package de.ItsAMysterious.mods.reallifemod.api.entity.properties;

import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityBaby;
import de.ItsAMysterious.mods.reallifemod.core.enums.EnumDress;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
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
import net.minecraft.world.biome.BiomeGenSnow;
import net.minecraft.world.biome.BiomeGenSwamp;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraftforge.common.IExtendedEntityProperties;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import scala.util.Random;

public class RealLifeProperties implements IExtendedEntityProperties {

   public static double Toilet;
   public static float thirst;
   public static float fatigue;
   public static float fun;
   public static float courage;
   public static RealLifeProperties.Moods mood = RealLifeProperties.Moods.HAPPY;
   public static String name = "";
   public static String surname = "";
   protected static EntityPlayer player;
   protected World theWorld;
   private static int timewithoutwater = 0;
   public static boolean sleeping;
   private static boolean pissing = false;
   public static RealLifeProperties.phone ThePhone = new RealLifeProperties.phone();
   public static float Temperature;
   public static Vector3f haircolor = new Vector3f(1.0F, 0.5F, 0.25F);
   public RealLifeProperties.gender Gender;
   public String birthdate;
   private int coldCounter;
   private boolean showColdWarning;
   public boolean hascap;
   float TemperatureToHave;
   public EnumDress dressing;
   public Float age;
   public static int phonenumber;
   public static final String EXT_PROP_NAME = "RealLifeProperties";
   public int HoldingEntity;
   public EntityPlayer requester;
   public String partner;


   public RealLifeProperties(EntityPlayer entity) {
      this.Gender = RealLifeProperties.gender.male;
      this.hascap = false;
      this.dressing = EnumDress.NONE;
      this.HoldingEntity = -1;
      if(phonenumber == 0) {
         Random random = new Random();
         phonenumber = random.nextInt(10000);
      }

   }

   public static final void register(EntityPlayer player) {
      player.registerExtendedProperties("RealLifeProperties", new RealLifeProperties(player));
   }

   public void saveNBTData(NBTTagCompound parCompound) {
      NBTTagCompound compound = new NBTTagCompound();
      parCompound.setTag("RealLife", compound);
      if(!name.isEmpty()) {
         compound.setString("PLAYERNAME", name == " "?"PleaseSetAName":name);
      } else {
         compound.setString("PLAYERNAME", "Set");
      }

      if(!surname.isEmpty()) {
         compound.setString("SURNAME", surname);
      } else {
         compound.setString("SURNAME", "Asurname");
      }

      compound.setString("gender", this.Gender.name());
      if(haircolor.toString() != "Vector3f[x, y, z ]") {
         compound.setFloat("HairColorRed", haircolor.x);
         compound.setFloat("HairColorGreen", haircolor.y);
         compound.setFloat("HairColorBlue", haircolor.z);
      } else {
         compound.setFloat("HairColorRed", 0.5F);
         compound.setFloat("HairColorGreen", 0.5F);
         compound.setFloat("HairColorBlue", 0.5F);
      }

      compound.setString("DateOfBirth", this.birthdate == null?"0.0.0":this.birthdate);
      if(this.dressing != null) {
         compound.setString("Dressing", this.dressing.name());
      } else {
         compound.setString("Dressing", "None");
      }

      compound.setFloat("Age", this.age.floatValue());
      compound.setInteger("phonenumber", phonenumber);
      compound.setString("gender", this.Gender.name());
      if(this.HoldingEntity != -1) {
         compound.setInteger("HoldingEntityID", this.HoldingEntity);
      } else {
         compound.setInteger("HoldingEntityID", -1);
      }

      compound.setString("PartnerName", this.partner);
      System.out.println(String.valueOf(this.Gender.name()));
      player.writeEntityToNBT(parCompound);
   }

   public void loadNBTData(NBTTagCompound compound) {
      NBTTagCompound comp = compound.getCompoundTag("RealLife");
      name = comp.getString("PLAYERNAME");
      surname = comp.getString("SURNAME");
      String g = comp.getString("gender");
      if(g.equals("female")) {
         this.Gender = RealLifeProperties.gender.female;
      } else {
         this.Gender = RealLifeProperties.gender.male;
      }

      haircolor = new Vector3f(comp.getFloat("HairColorRed"), comp.getFloat("HairColorGreen"), comp.getFloat("HairColorBlue"));
      this.birthdate = comp.getString("DateOfBirth");
      EnumDress var10001 = this.dressing;
      this.dressing = EnumDress.valueOf(comp.getString("Dressing"));
      this.age = Float.valueOf(comp.getFloat("Age"));
      phonenumber = comp.getInteger("phonenumber");
      if(comp.getInteger("HoldingEntityID") != -1) {
         this.HoldingEntity = comp.getInteger("HoldingEntityID");
      }

      System.out.println(comp.getString("Dressing"));
   }

   public void init(Entity entity, World world) {
      if(entity instanceof EntityPlayer && entity.worldObj.isRemote) {
         player = (EntityPlayer)entity;
         if(this.birthdate == null) {
            Date zeitstempel = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd.yyyy");
            this.birthdate = simpleDateFormat.format(zeitstempel);
         }

         if(entity.getExtendedProperties("RealLifeProperties") == null) {
            name = "Set";
            surname = "Name!";
            thirst = 0.0F;
            Toilet = 0.0D;
            haircolor = new Vector3f(0.5F, 0.5F, 0.5F);
         }
      }

   }

   public void updateStates() {
      if(player != null) {
         if(thirst < 0.0F) {
            thirst = 0.0F;
         }

         if(sleeping && (player.field_70165_t != (double)player.getBedLocation().posX || player.field_70163_u != (double)player.getBedLocation().posY || player.field_70161_v != (double)player.getBedLocation().posZ)) {
            sleeping = false;
         }

         if(!player.capabilities.isCreativeMode) {
            if((double)player.func_110143_aJ() < 9.5D && player.getFoodStats().getSaturationLevel() < 2.5F) {
               mood = RealLifeProperties.Moods.WEAK;
            } else if(player.field_70170_p.getWorldTime() < 24000L && player.field_70170_p.getWorldTime() > 13000L) {
               mood = RealLifeProperties.Moods.TENSE;
            } else if(player.field_70170_p.getWorldTime() < 24000L && player.field_70170_p.getWorldTime() > 13000L && player.field_70170_p.difficultySetting != EnumDifficulty.PEACEFUL) {
               mood = RealLifeProperties.Moods.FRIGHTENED;
            } else if(player.field_70170_p.isRaining() && player.func_70090_H()) {
               mood = RealLifeProperties.Moods.UNCOMFORTABLE;
            } else {
               mood = RealLifeProperties.Moods.HAPPY;
            }

            thirst = (float)((double)thirst + 0.00138888889D);
            if(thirst > 90.0F) {
               player.func_70690_d(new PotionEffect(Potion.confusion.getId(), 100));
            } else if(player.func_70660_b(Potion.confusion) != null) {
               player.func_82170_o(Potion.confusion.id);
            }

            if(thirst >= 100.0F) {
               player.func_70690_d(new PotionEffect(Potion.weakness.getId(), 100));
               ++timewithoutwater;
               if(timewithoutwater == 100) {
                  player.addChatComponentMessage(new ChatComponentText("you\'re dying of" + EnumChatFormatting.BOLD + EnumChatFormatting.RED + "thirst" + EnumChatFormatting.WHITE + "!"));
                  player.func_70606_j(player.func_110143_aJ() - 1.0F);
               }
            }

            if((double)(100.0F - thirst) + (double)player.getFoodStats().getSaturationLevel() / 0.19D >= 50.0D) {
               ;
            }

            if(Toilet < 100.0D) {
               Toilet += 0.00415151515151515D;
            } else {
               player.func_70690_d(new PotionEffect(Potion.digSlowdown.getId(), 1));
            }
         }

         if(pissing) {
            player.field_70159_w = 0.0D;
            player.field_70181_x = 0.0D;
            player.field_70179_y = 0.0D;
            if(Toilet > 0.0D) {
               --Toilet;
               thirst = (float)((double)thirst + 0.5D);
            } else {
               player.func_82170_o(Potion.digSlowdown.getId());
               pissing = false;
            }
         }

         if(Toilet < 90.0D) {
            player.func_82170_o(Potion.digSlowdown.getId());
         }

         if(thirst < 0.0F) {
            thirst = 0.0F;
         }

         if(thirst > 100.0F) {
            thirst = 100.0F;
         }

         if(!player.func_70090_H()) {
            if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenSnow) {
               this.TemperatureToHave = -20.0F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenPlains) {
               this.TemperatureToHave = 28.0F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenDesert) {
               this.TemperatureToHave = 35.0F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenForest) {
               this.TemperatureToHave = 28.0F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenRiver) {
               this.TemperatureToHave = 25.0F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenJungle) {
               this.TemperatureToHave = 35.0F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenBeach) {
               this.TemperatureToHave = 30.0F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenEnd) {
               this.TemperatureToHave = 10.0F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenHell) {
               this.TemperatureToHave = 66.0F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenHills) {
               this.TemperatureToHave = 20.0F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenTaiga) {
               this.TemperatureToHave = 35.0F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenMesa) {
               this.TemperatureToHave = 25.0F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenMushroomIsland) {
               this.TemperatureToHave = 25.0F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenSwamp) {
               this.TemperatureToHave = 17.5F;
            } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenMutated) {
               this.TemperatureToHave = 20.0F;
            }
         } else if(player.field_70170_p.getWorldChunkManager().getBiomeGenAt((int)player.field_70165_t, (int)player.field_70161_v) instanceof BiomeGenOcean) {
            this.TemperatureToHave = 5.0F;
         } else {
            this.TemperatureToHave = 15.0F;
         }

         if(Temperature < this.TemperatureToHave) {
            Temperature = (float)((double)Temperature + 0.25D);
         } else if(Temperature != this.TemperatureToHave && Temperature > this.TemperatureToHave) {
            Temperature = (float)((double)Temperature - 0.25D);
         }

         if(Temperature < -10.0F && this.coldCounter < 1200) {
            if(this.showColdWarning) {
               player.func_145747_a(new ChatComponentText("It\'s to" + EnumChatFormatting.AQUA + "cold" + EnumChatFormatting.RESET + "here! Dress warmer or build a fire to get warm!"));
               this.showColdWarning = false;
            }

            player.func_70690_d(new PotionEffect(Potion.moveSlowdown.id, 10));
            ++this.coldCounter;
         } else {
            if(Temperature < -10.0F) {
               player.func_70690_d(new PotionEffect(Potion.harm.id, 10));
            }

            if(player.func_110143_aJ() <= 0.0F) {
               this.coldCounter = 0;
            }
         }

         if(player.field_70153_n instanceof EntityBaby && Keyboard.isKeyDown(29) && Keyboard.isKeyDown(42)) {
            ;
         }

         if(this.HoldingEntity != -1 && player.field_70170_p.isRemote) {
            ;
         }
      } else {
         System.out.println("Player isn\'t set!");
      }

   }

   public String ColorOfMood(RealLifeProperties.Moods theMood) {
      return theMood == RealLifeProperties.Moods.HAPPY?EnumChatFormatting.GREEN.toString():(theMood == RealLifeProperties.Moods.UNCOMFORTABLE?EnumChatFormatting.DARK_AQUA.toString():(theMood == RealLifeProperties.Moods.FRIGHTENED?EnumChatFormatting.YELLOW.toString():(theMood == RealLifeProperties.Moods.TENSE?EnumChatFormatting.DARK_GREEN.toString():(theMood == RealLifeProperties.Moods.COQUETTISH?EnumChatFormatting.LIGHT_PURPLE.toString():(theMood == RealLifeProperties.Moods.WEAK?EnumChatFormatting.GRAY.toString():(theMood == RealLifeProperties.Moods.ASHAMED?EnumChatFormatting.YELLOW.toString():""))))));
   }

   public void setPissing() {
      pissing = true;
   }

   public String getDressing() {
      return this.dressing != null?this.dressing.name():null;
   }

   public static final RealLifeProperties get(EntityPlayer player) {
      return (RealLifeProperties)player.getExtendedProperties("RealLifeProperties");
   }

   public void pickupChild(EntityBaby baby) {
      baby.func_70078_a(player);
   }

   public void setDownChild(double x, double y, double z) {
      player.field_70170_p.getEntityByID(this.HoldingEntity).mountEntity((Entity)null);
      this.HoldingEntity = -1;
   }

   public void setMariageRequestor(EntityPlayer entity) {
      this.requester = entity;
   }

   public void requestMariage(EntityPlayer requestor) {
      this.requester = requestor;
   }

   public String getFullName() {
      return name + " " + surname;
   }


   public static class phone {

      public static RealLifeProperties.phone.phonepartners partner = RealLifeProperties.phone.phonepartners.TUTORIALHOTLINE;



      public static enum phonepartners {

         POLICE("POLICE", 0),
         FIREFIGHTER("FIREFIGHTER", 1),
         PARAMEDICS("PARAMEDICS", 2),
         TUTORIALHOTLINE("TUTORIALHOTLINE", 3);
         // $FF: synthetic field
         private static final RealLifeProperties.phone.phonepartners[] $VALUES = new RealLifeProperties.phone.phonepartners[]{POLICE, FIREFIGHTER, PARAMEDICS, TUTORIALHOTLINE};


         private phonepartners(String var1, int var2) {}

      }
   }

   public static enum Moods {

      HAPPY("HAPPY", 0),
      UNCOMFORTABLE("UNCOMFORTABLE", 1),
      TENSE("TENSE", 2),
      FRIGHTENED("FRIGHTENED", 3),
      WEAK("WEAK", 4),
      SAD("SAD", 5),
      COQUETTISH("COQUETTISH", 6),
      PLAYFUL("PLAYFUL", 7),
      STIRRED("STIRRED", 8),
      ASHAMED("ASHAMED", 9);
      // $FF: synthetic field
      private static final RealLifeProperties.Moods[] $VALUES = new RealLifeProperties.Moods[]{HAPPY, UNCOMFORTABLE, TENSE, FRIGHTENED, WEAK, SAD, COQUETTISH, PLAYFUL, STIRRED, ASHAMED};


      private Moods(String var1, int var2) {}

   }

   public static enum gender {

      male("male", 0),
      female("female", 1);
      // $FF: synthetic field
      private static final RealLifeProperties.gender[] $VALUES = new RealLifeProperties.gender[]{male, female};


      private gender(String var1, int var2) {}

   }
}
