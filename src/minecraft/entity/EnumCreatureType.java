package net.minecraft.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;

public enum EnumCreatureType {

   monster("monster", 0, IMob.class, 70, Material.air, false, false),
   creature("creature", 1, EntityAnimal.class, 10, Material.air, true, true),
   ambient("ambient", 2, EntityAmbientCreature.class, 15, Material.air, true, false),
   waterCreature("waterCreature", 3, EntityWaterMob.class, 5, Material.water, true, false);
   private final Class creatureClass;
   private final int maxNumberOfCreature;
   private final Material creatureMaterial;
   private final boolean isPeacefulCreature;
   private final boolean isAnimal;
   // $FF: synthetic field
   private static final EnumCreatureType[] $VALUES = new EnumCreatureType[]{monster, creature, ambient, waterCreature};


   private EnumCreatureType(String var1, int var2, Class var3, int var4, Material var5, boolean var6, boolean var7) {
      this.creatureClass = var3;
      this.maxNumberOfCreature = var4;
      this.creatureMaterial = var5;
      this.isPeacefulCreature = var6;
      this.isAnimal = var7;
   }

   public Class getCreatureClass() {
      return this.creatureClass;
   }

   public int getMaxNumberOfCreature() {
      return this.maxNumberOfCreature;
   }

   public Material getCreatureMaterial() {
      return this.creatureMaterial;
   }

   public boolean getPeacefulCreature() {
      return this.isPeacefulCreature;
   }

   public boolean getAnimal() {
      return this.isAnimal;
   }

}
