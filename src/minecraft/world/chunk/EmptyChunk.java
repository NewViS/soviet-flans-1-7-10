package net.minecraft.world.chunk;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class EmptyChunk extends Chunk {

   public EmptyChunk(World var1, int var2, int var3) {
      super(var1, var2, var3);
   }

   public boolean isAtLocation(int var1, int var2) {
      return var1 == super.xPosition && var2 == super.zPosition;
   }

   public int getHeightValue(int var1, int var2) {
      return 0;
   }

   public void generateHeightMap() {}

   public void generateSkylightMap() {}

   public Block getBlock(int var1, int var2, int var3) {
      return Blocks.air;
   }

   public int func_150808_b(int var1, int var2, int var3) {
      return 255;
   }

   public boolean func_150807_a(int var1, int var2, int var3, Block var4, int var5) {
      return true;
   }

   public int getBlockMetadata(int var1, int var2, int var3) {
      return 0;
   }

   public boolean setBlockMetadata(int var1, int var2, int var3, int var4) {
      return false;
   }

   public int getSavedLightValue(EnumSkyBlock var1, int var2, int var3, int var4) {
      return 0;
   }

   public void setLightValue(EnumSkyBlock var1, int var2, int var3, int var4, int var5) {}

   public int getBlockLightValue(int var1, int var2, int var3, int var4) {
      return 0;
   }

   public void addEntity(Entity var1) {}

   public void removeEntity(Entity var1) {}

   public void removeEntityAtIndex(Entity var1, int var2) {}

   public boolean canBlockSeeTheSky(int var1, int var2, int var3) {
      return false;
   }

   public TileEntity func_150806_e(int var1, int var2, int var3) {
      return null;
   }

   public void addTileEntity(TileEntity var1) {}

   public void func_150812_a(int var1, int var2, int var3, TileEntity var4) {}

   public void removeTileEntity(int var1, int var2, int var3) {}

   public void onChunkLoad() {}

   public void onChunkUnload() {}

   public void setChunkModified() {}

   public void getEntitiesWithinAABBForEntity(Entity var1, AxisAlignedBB var2, List var3, IEntitySelector var4) {}

   public void getEntitiesOfTypeWithinAAAB(Class var1, AxisAlignedBB var2, List var3, IEntitySelector var4) {}

   public boolean needsSaving(boolean var1) {
      return false;
   }

   public Random getRandomWithSeed(long var1) {
      return new Random(super.worldObj.getSeed() + (long)(super.xPosition * super.xPosition * 4987142) + (long)(super.xPosition * 5947611) + (long)(super.zPosition * super.zPosition) * 4392871L + (long)(super.zPosition * 389711) ^ var1);
   }

   public boolean isEmpty() {
      return true;
   }

   public boolean getAreLevelsEmpty(int var1, int var2) {
      return true;
   }
}
