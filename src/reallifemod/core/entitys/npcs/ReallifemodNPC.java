package de.ItsAMysterious.mods.reallifemod.core.entitys.npcs;

import de.ItsAMysterious.mods.reallifemod.core.entitys.ai.EntityAIExtinguish;
import de.ItsAMysterious.mods.reallifemod.core.handlers.SpeechHandler;
import java.util.Random;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ReallifemodNPC extends EntityAnimal implements INpc {

   public int jobIndex;
   public ReallifemodNPC.Job currentJob;
   private SpeechHandler handler;


   public ReallifemodNPC(World world) {
      super(world);
      this.jobIndex = 0;
      this.currentJob = ReallifemodNPC.Job.DOCTOR;
      this.handler = new SpeechHandler();
      this.func_70105_a(0.6F, 1.8F);
      this.func_70661_as().setBreakDoors(true);
      this.func_70661_as().setAvoidsWater(true);
      this.field_70714_bg.addTask(0, new EntityAISwimming(this));
      this.field_70158_ak = false;
   }

   public ReallifemodNPC(World world, double d, double e, double f) {
      super(world);
      this.jobIndex = 0;
      this.currentJob = ReallifemodNPC.Job.DOCTOR;
      this.func_70107_b(d, e, f);
      this.func_70606_j(19.0F);
      this.field_70714_bg.addTask(1, new EntityAIWander(this, 1.5D));
      this.field_70714_bg.addTask(2, new EntityAIMoveIndoors(this));
      this.field_70714_bg.addTask(3, new EntityAIRestrictOpenDoor(this));
      this.field_70714_bg.addTask(4, new EntityAIOpenDoor(this, true));
      this.field_70714_bg.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
      this.field_70714_bg.addTask(6, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
      this.field_70714_bg.addTask(7, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
      this.field_70714_bg.addTask(8, new EntityAIExtinguish(this, 10));
      this.field_70158_ak = false;
   }

   public ReallifemodNPC(World world, double d, double e, double f, ReallifemodNPC.Job thejob) {
      this(world, d, e, f);
      this.setJob(thejob);
      this.field_70178_ae = true;
   }

   private void setJob(ReallifemodNPC.Job thejob) {
      this.currentJob = thejob;
   }

   public void func_70014_b(NBTTagCompound tag) {
      NBTTagCompound comp = new NBTTagCompound();
      comp.setInteger("Age", this.func_70874_b());
      tag.setTag("reallifemodNPC", comp);
   }

   public void func_70037_a(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.func_70873_a(tag.getInteger("Age"));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D);
      this.func_110148_a(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
   }

   public ItemStack func_70694_bm() {
      return null;
   }

   public ItemStack func_71124_b(int p_71124_1_) {
      return null;
   }

   public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_) {
      super.func_70062_b(p_70062_1_, p_70062_2_);
   }

   public ItemStack[] func_70035_c() {
      return null;
   }

   public boolean func_70085_c(EntityPlayer player) {
      if(this.jobIndex < 4) {
         ++this.jobIndex;
      } else if(this.jobIndex == 4) {
         this.jobIndex = 0;
      }

      switch(this.jobIndex) {
      case 0:
         this.setJob(ReallifemodNPC.Job.BODYGUARD);
         break;
      case 1:
         this.setJob(ReallifemodNPC.Job.DOCTOR);
         break;
      case 2:
         this.setJob(ReallifemodNPC.Job.FIREMEN);
         break;
      case 3:
         this.setJob(ReallifemodNPC.Job.POLICEMEN);
      case 4:
         this.setJob(ReallifemodNPC.Job.PEDESTRIAN);
      }

      if(!this.field_70170_p.isRemote) {
         if(this.currentJob != ReallifemodNPC.Job.DOCTOR) {
            ;
         }

         player.addChatComponentMessage(new ChatComponentText("Hello, my name is Steve and i am a " + this.currentLine() + "."));
      }

      return true;
   }

   private String currentLine() {
      return this.currentJob.name().toLowerCase();
   }

   public void setDamageTaken(float p_70266_1_) {
      this.field_70180_af.updateObject(19, Float.valueOf(p_70266_1_));
   }

   public float getDamageTaken() {
      return this.field_70180_af.getWatchableObjectFloat(19);
   }

   public EntityAgeable func_90011_a(EntityAgeable entity) {
      return entity;
   }

   public boolean sendMariageRequest(EntityPlayer requestor) {
      Random rand = new Random();
      int r = rand.nextInt(1);
      return r == 1;
   }

   public static enum Job {

      BODYGUARD("BODYGUARD", 0),
      DOCTOR("DOCTOR", 1),
      FIREMEN("FIREMEN", 2),
      POLICEMEN("POLICEMEN", 3),
      MINOR("MINOR", 4),
      PEDESTRIAN("PEDESTRIAN", 5);
      // $FF: synthetic field
      private static final ReallifemodNPC.Job[] $VALUES = new ReallifemodNPC.Job[]{BODYGUARD, DOCTOR, FIREMEN, POLICEMEN, MINOR, PEDESTRIAN};


      private Job(String var1, int var2) {}

   }
}
