package net.minecraft.util;

import net.minecraft.util.RegistryNamespaced;

public class RegistryNamespacedDefaultedByKey extends RegistryNamespaced {

   private final String field_148760_d;
   private Object field_148761_e;


   public RegistryNamespacedDefaultedByKey(String var1) {
      this.field_148760_d = var1;
   }

   public void addObject(int var1, String var2, Object var3) {
      if(this.field_148760_d.equals(var2)) {
         this.field_148761_e = var3;
      }

      super.addObject(var1, var2, var3);
   }

   public Object getObject(String var1) {
      Object var2 = super.getObject(var1);
      return var2 == null?this.field_148761_e:var2;
   }

   public Object getObjectById(int var1) {
      Object var2 = super.getObjectById(var1);
      return var2 == null?this.field_148761_e:var2;
   }
}
