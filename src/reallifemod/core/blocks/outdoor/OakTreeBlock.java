package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import de.ItsAMysterious.mods.reallifemod.core.entitys.EntityFallingOak;
import de.ItsAMysterious.mods.reallifemod.core.tiles.OakTreeTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class OakTreeBlock extends BlockContainer implements ITileEntityProvider {

   private String soundName;


   public OakTreeBlock() {
      super(Material.wood);
      this.func_149663_c("OakTree");
      this.func_149658_d("reallifemod:oaktree");
      this.func_149672_a(new OakTreeBlock.SoundType("woodchopping", 1.0F, 1.0F));
      this.func_149711_c(10.0F);
   }

   public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
      if(world.getTileEntity(x, y, z) != null && ((OakTreeTE)world.getTileEntity(x, y, z)).stability > 1) {
         --((OakTreeTE)world.getTileEntity(x, y, z)).stability;
      }

   }

   public void func_149749_a(World world, int x, int y, int z, Block block, int par1) {
      OakTreeTE tile = (OakTreeTE)world.getTileEntity(x, y, z);
      world.spawnEntityInWorld(new EntityFallingOak(world, (double)tile.field_145851_c, (double)tile.field_145848_d, (double)tile.field_145849_e));
      Minecraft.getMinecraft().thePlayer.func_85030_a("reallifemod:tree_fall", 1.0F, 1.0F);
      world.markBlockForUpdate(x, y, z);
      super.breakBlock(world, x, y, z, block, par1);
   }

   public TileEntity func_149915_a(World world, int id) {
      return new OakTreeTE();
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
      int l = BlockPistonBase.determineOrientation(world, x, y, z, entity);
      world.setBlockMetadataWithNotify(x, y, z, l, 2);
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
