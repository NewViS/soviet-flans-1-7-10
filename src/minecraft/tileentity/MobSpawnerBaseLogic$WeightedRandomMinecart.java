package net.minecraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.WeightedRandom$Item;

public class MobSpawnerBaseLogic$WeightedRandomMinecart extends WeightedRandom$Item {

   public final NBTTagCompound field_98222_b;
   public final String entityTypeName;
   // $FF: synthetic field
   final MobSpawnerBaseLogic field_98221_d;


   public MobSpawnerBaseLogic$WeightedRandomMinecart(MobSpawnerBaseLogic var1, NBTTagCompound var2) {
      super(var2.getInteger("Weight"));
      this.field_98221_d = var1;
      NBTTagCompound var3 = var2.getCompoundTag("Properties");
      String var4 = var2.getString("Type");
      if(var4.equals("Minecart")) {
         if(var3 != null) {
            switch(var3.getInteger("Type")) {
            case 0:
               var4 = "MinecartRideable";
               break;
            case 1:
               var4 = "MinecartChest";
               break;
            case 2:
               var4 = "MinecartFurnace";
            }
         } else {
            var4 = "MinecartRideable";
         }
      }

      this.field_98222_b = var3;
      this.entityTypeName = var4;
   }

   public MobSpawnerBaseLogic$WeightedRandomMinecart(MobSpawnerBaseLogic var1, NBTTagCompound var2, String var3) {
      super(1);
      this.field_98221_d = var1;
      if(var3.equals("Minecart")) {
         if(var2 != null) {
            switch(var2.getInteger("Type")) {
            case 0:
               var3 = "MinecartRideable";
               break;
            case 1:
               var3 = "MinecartChest";
               break;
            case 2:
               var3 = "MinecartFurnace";
            }
         } else {
            var3 = "MinecartRideable";
         }
      }

      this.field_98222_b = var2;
      this.entityTypeName = var3;
   }

   public NBTTagCompound func_98220_a() {
      NBTTagCompound var1 = new NBTTagCompound();
      var1.setTag("Properties", this.field_98222_b);
      var1.setString("Type", this.entityTypeName);
      var1.setInteger("Weight", super.itemWeight);
      return var1;
   }
}
