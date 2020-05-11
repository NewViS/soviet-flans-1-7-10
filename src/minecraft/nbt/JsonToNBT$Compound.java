package net.minecraft.nbt;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.nbt.JsonToNBT$Any;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

class JsonToNBT$Compound extends JsonToNBT$Any {

   protected ArrayList field_150491_b = new ArrayList();


   public JsonToNBT$Compound(String var1) {
      super.field_150490_a = var1;
   }

   public NBTBase func_150489_a() {
      NBTTagCompound var1 = new NBTTagCompound();
      Iterator var2 = this.field_150491_b.iterator();

      while(var2.hasNext()) {
         JsonToNBT$Any var3 = (JsonToNBT$Any)var2.next();
         var1.setTag(var3.field_150490_a, var3.func_150489_a());
      }

      return var1;
   }
}
