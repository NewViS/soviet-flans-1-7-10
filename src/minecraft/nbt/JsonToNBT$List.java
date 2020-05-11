package net.minecraft.nbt;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.nbt.JsonToNBT$Any;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;

class JsonToNBT$List extends JsonToNBT$Any {

   protected ArrayList field_150492_b = new ArrayList();


   public JsonToNBT$List(String var1) {
      super.field_150490_a = var1;
   }

   public NBTBase func_150489_a() {
      NBTTagList var1 = new NBTTagList();
      Iterator var2 = this.field_150492_b.iterator();

      while(var2.hasNext()) {
         JsonToNBT$Any var3 = (JsonToNBT$Any)var2.next();
         var1.appendTag(var3.func_150489_a());
      }

      return var1;
   }
}
