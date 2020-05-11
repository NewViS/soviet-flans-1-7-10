package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.world.World;

public class BlockNote extends BlockContainer {

   public BlockNote() {
      super(Material.wood);
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      boolean var6 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4);
      TileEntityNote var7 = (TileEntityNote)var1.getTileEntity(var2, var3, var4);
      if(var7 != null && var7.previousRedstoneState != var6) {
         if(var6) {
            var7.triggerNote(var1, var2, var3, var4);
         }

         var7.previousRedstoneState = var6;
      }

   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(var1.isRemote) {
         return true;
      } else {
         TileEntityNote var10 = (TileEntityNote)var1.getTileEntity(var2, var3, var4);
         if(var10 != null) {
            var10.changePitch();
            var10.triggerNote(var1, var2, var3, var4);
         }

         return true;
      }
   }

   public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {
      if(!var1.isRemote) {
         TileEntityNote var6 = (TileEntityNote)var1.getTileEntity(var2, var3, var4);
         if(var6 != null) {
            var6.triggerNote(var1, var2, var3, var4);
         }

      }
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityNote();
   }

   public boolean onBlockEventReceived(World var1, int var2, int var3, int var4, int var5, int var6) {
      float var7 = (float)Math.pow(2.0D, (double)(var6 - 12) / 12.0D);
      String var8 = "harp";
      if(var5 == 1) {
         var8 = "bd";
      }

      if(var5 == 2) {
         var8 = "snare";
      }

      if(var5 == 3) {
         var8 = "hat";
      }

      if(var5 == 4) {
         var8 = "bassattack";
      }

      var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "note." + var8, 3.0F, var7);
      var1.spawnParticle("note", (double)var2 + 0.5D, (double)var3 + 1.2D, (double)var4 + 0.5D, (double)var6 / 24.0D, 0.0D, 0.0D);
      return true;
   }
}
