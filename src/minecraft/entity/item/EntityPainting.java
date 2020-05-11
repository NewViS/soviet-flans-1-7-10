package net.minecraft.entity.item;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityPainting$EnumArt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityPainting extends EntityHanging {

   public EntityPainting$EnumArt art;


   public EntityPainting(World var1) {
      super(var1);
   }

   public EntityPainting(World var1, int var2, int var3, int var4, int var5) {
      super(var1, var2, var3, var4, var5);
      ArrayList var6 = new ArrayList();
      EntityPainting$EnumArt[] var7 = EntityPainting$EnumArt.values();
      int var8 = var7.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         EntityPainting$EnumArt var10 = var7[var9];
         this.art = var10;
         this.setDirection(var5);
         if(this.onValidSurface()) {
            var6.add(var10);
         }
      }

      if(!var6.isEmpty()) {
         this.art = (EntityPainting$EnumArt)var6.get(super.rand.nextInt(var6.size()));
      }

      this.setDirection(var5);
   }

   public EntityPainting(World var1, int var2, int var3, int var4, int var5, String var6) {
      this(var1, var2, var3, var4, var5);
      EntityPainting$EnumArt[] var7 = EntityPainting$EnumArt.values();
      int var8 = var7.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         EntityPainting$EnumArt var10 = var7[var9];
         if(var10.title.equals(var6)) {
            this.art = var10;
            break;
         }
      }

      this.setDirection(var5);
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setString("Motive", this.art.title);
      super.writeEntityToNBT(var1);
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      String var2 = var1.getString("Motive");
      EntityPainting$EnumArt[] var3 = EntityPainting$EnumArt.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EntityPainting$EnumArt var6 = var3[var5];
         if(var6.title.equals(var2)) {
            this.art = var6;
         }
      }

      if(this.art == null) {
         this.art = EntityPainting$EnumArt.Kebab;
      }

      super.readEntityFromNBT(var1);
   }

   public int getWidthPixels() {
      return this.art.sizeX;
   }

   public int getHeightPixels() {
      return this.art.sizeY;
   }

   public void onBroken(Entity var1) {
      if(var1 instanceof EntityPlayer) {
         EntityPlayer var2 = (EntityPlayer)var1;
         if(var2.capabilities.isCreativeMode) {
            return;
         }
      }

      this.entityDropItem(new ItemStack(Items.painting), 0.0F);
   }
}
