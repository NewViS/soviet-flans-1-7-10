package net.minecraft.world.gen.structure;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.minecraft.world.gen.structure.MapGenNetherBridge$Start;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenNetherBridge extends MapGenStructure {

   private List spawnList = new ArrayList();


   public MapGenNetherBridge() {
      this.spawnList.add(new BiomeGenBase$SpawnListEntry(EntityBlaze.class, 10, 2, 3));
      this.spawnList.add(new BiomeGenBase$SpawnListEntry(EntityPigZombie.class, 5, 4, 4));
      this.spawnList.add(new BiomeGenBase$SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
      this.spawnList.add(new BiomeGenBase$SpawnListEntry(EntityMagmaCube.class, 3, 4, 4));
   }

   public String func_143025_a() {
      return "Fortress";
   }

   public List getSpawnList() {
      return this.spawnList;
   }

   protected boolean canSpawnStructureAtCoords(int var1, int var2) {
      int var3 = var1 >> 4;
      int var4 = var2 >> 4;
      super.rand.setSeed((long)(var3 ^ var4 << 4) ^ super.worldObj.getSeed());
      super.rand.nextInt();
      return super.rand.nextInt(3) != 0?false:(var1 != (var3 << 4) + 4 + super.rand.nextInt(8)?false:var2 == (var4 << 4) + 4 + super.rand.nextInt(8));
   }

   protected StructureStart getStructureStart(int var1, int var2) {
      return new MapGenNetherBridge$Start(super.worldObj, super.rand, var1, var2);
   }
}
