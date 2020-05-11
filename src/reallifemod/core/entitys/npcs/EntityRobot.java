package de.ItsAMysterious.mods.reallifemod.core.entitys.npcs;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.gui.guiHelperBot;
import de.ItsAMysterious.mods.reallifemod.core.handlers.SpeechHandler;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityRobot extends EntityAnimal {

   private SpeechHandler handler;
   public boolean firstClick;
   public boolean shouldFollow;
   public static String name;


   public EntityRobot(World world) {
      super(world);
      this.firstClick = true;
      this.handler = new SpeechHandler();
      this.func_70105_a(1.0F, 2.0F);
      this.func_94058_c("Helperbot Zanor");
      this.field_70714_bg.addTask(0, new EntityAIWander(this, 0.5D));
      this.field_70714_bg.addTask(1, new EntityAIPanic(this, 1.0D));
      this.field_70714_bg.addTask(2, new EntityAITempt(this, 1.0D, Items.iron_ingot, false));
      this.field_70714_bg.addTask(3, new EntityAIFollowParent(this, 1.1D));
   }

   public EntityRobot(World world, double d, double e, double f) {
      this(world);
      this.func_70107_b(d, e, f);
      this.field_70158_ak = false;
   }

   public boolean func_70650_aV() {
      return true;
   }

   public void func_70014_b(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      NBTTagCompound tag = new NBTTagCompound();
      tag.setBoolean("FirstClick", this.firstClick);
      tag.setBoolean("ShouldRobotFollow", this.shouldFollow);
      tag.setString("RobotName", name);
      compound.setTag("RoboProps", tag);
   }

   public void func_70037_a(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      NBTTagCompound tag = compound.getCompoundTag("RoboProps");
      this.firstClick = tag.getBoolean("FirstClick");
      this.shouldFollow = tag.getBoolean("ShouldRobotFollow");
      name = tag.getString("RobotName");
   }

   public void func_94058_c(String thename) {
      thename = "Zanor";
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D);
      this.func_110148_a(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
   }

   public boolean func_70085_c(EntityPlayer player) {
      if(!player.field_70170_p.isRemote && this.firstClick) {
         SpeechHandler var10000 = this.handler;
         SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "HI! I am your new Intelibot, just call me \'Zanor\'! I can help you with everything in the Real Life Mod! You just have to right click on me. If you want to change my name simply type \'Hello\' plus the new name in my commandbox!");
         this.firstClick = false;
         System.out.println("Firstclick:" + this.firstClick);
      } else if(!player.field_70170_p.isRemote) {
         player.openGui(RealLifeMod.instance, guiHelperBot.GUI_ID, this.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
      }

      return true;
   }

   public EntityAgeable func_90011_a(EntityAgeable p_90011_1_) {
      return new EntityRobot(this.field_70170_p);
   }
}
