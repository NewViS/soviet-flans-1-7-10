package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import de.ItsAMysterious.mods.reallifemod.core.entitys.EntityFirFalling;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FirTreeTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFirTree extends BlockContainer {

   private String soundName;


   public BlockFirTree() {
      super(Material.wood);
      this.func_149663_c("spruce_tree");
      this.func_149658_d("reallifemod:spruce_tree");
      this.func_149672_a(new BlockFirTree.SoundType("woodchopping", 1.0F, 1.0F));
      this.func_149711_c(8.0F);
      this.field_149758_A = true;
      this.func_149722_s();
   }

   public void func_149719_a(IBlockAccess world, int x, int y, int z) {
      FirTreeTE tile = (FirTreeTE)world.getTileEntity(x, y, z);
      float scale = (float)tile.scale;
      this.func_149676_a(0.5F - scale / 2.0F, 0.0F, 0.5F - scale / 2.0F, 0.5F + scale / 2.0F, scale * 100.0F, 0.5F + scale / 2.0F);
   }

   public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
      if(!world.isRemote) {
         FirTreeTE tile = (FirTreeTE)world.getTileEntity(x, y, z);
         --tile.stability;
         if(tile.stability == 1.0F) {
            tile.func_145831_w().spawnEntityInWorld(new EntityFirFalling(tile.func_145831_w(), (double)x, (double)(y + 1), (double)z));
            Minecraft.getMinecraft().thePlayer.func_85030_a("reallifemod:tree_fall", 1.0F, 1.0F);
            tile.isTrunk = true;
         }

         world.markBlockForUpdate(x, y, z);
      }

   }

   public TileEntity func_149915_a(World world, int id) {
      return new FirTreeTE();
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149662_c() {
      return false;
   }

   public int func_149645_b() {
      return -1;
   }

   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
      if(entity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entity;
         if(player != null && world != null) {
            int le = MathHelper.floor_double((double)(player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3;
            world.setBlockMetadataWithNotify(x, y, z, le, 2);
         }

         world.markBlockForUpdate(x, y, z);
      }

   }

   public static class SoundType extends net.minecraft.block.Block.SoundType {

      public final String soundNameStep;
      public final String soundNameBreak;
      public final String soundNamePlace;


      public SoundType(String soundName, float volume, float frequency) {
         super(soundName, volume, frequency);
         this.soundNameStep = null;
         this.soundNameBreak = null;
         this.soundNamePlace = null;
      }

      public SoundType(String soundNameBreak, String soundNameStep, float volume, float frequency) {
         super(soundNameStep, volume, frequency);
         this.soundNameStep = soundNameStep;
         this.soundNameBreak = soundNameBreak;
         this.soundNamePlace = null;
      }

      public SoundType(String soundNameBreak, String soundNameStep, String soundNamePlace, float volume, float frequency) {
         super(soundNameStep, volume, frequency);
         this.soundNameStep = soundNameStep;
         this.soundNameBreak = soundNameBreak;
         this.soundNamePlace = soundNamePlace;
      }

      public String func_150495_a() {
         return this.soundNameBreak == null?"reallifemod:" + this.field_150501_a:this.soundNameBreak;
      }

      public String func_150498_e() {
         return this.soundNameStep == null?"reallifemod:" + this.field_150501_a:this.soundNameStep;
      }

      public String func_150496_b() {
         return this.soundNamePlace == null?this.func_150495_a():this.soundNamePlace;
      }
   }
}
