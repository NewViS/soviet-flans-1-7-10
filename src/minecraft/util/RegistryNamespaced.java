package net.minecraft.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.util.IObjectIntIterable;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.RegistrySimple;

public class RegistryNamespaced extends RegistrySimple implements IObjectIntIterable {

   protected final ObjectIntIdentityMap underlyingIntegerMap = new ObjectIntIdentityMap();
   protected final Map field_148758_b;


   public RegistryNamespaced() {
      this.field_148758_b = ((BiMap)super.registryObjects).inverse();
   }

   public void addObject(int var1, String var2, Object var3) {
      this.underlyingIntegerMap.func_148746_a(var3, var1);
      this.putObject(ensureNamespaced(var2), var3);
   }

   protected Map createUnderlyingMap() {
      return HashBiMap.create();
   }

   public Object getObject(String var1) {
      return super.getObject(ensureNamespaced(var1));
   }

   public String getNameForObject(Object var1) {
      return (String)this.field_148758_b.get(var1);
   }

   public boolean containsKey(String var1) {
      return super.containsKey(ensureNamespaced(var1));
   }

   public int getIDForObject(Object var1) {
      return this.underlyingIntegerMap.func_148747_b(var1);
   }

   public Object getObjectById(int var1) {
      return this.underlyingIntegerMap.func_148745_a(var1);
   }

   public Iterator iterator() {
      return this.underlyingIntegerMap.iterator();
   }

   public boolean containsId(int var1) {
      return this.underlyingIntegerMap.func_148744_b(var1);
   }

   private static String ensureNamespaced(String var0) {
      return var0.indexOf(58) == -1?"minecraft:" + var0:var0;
   }

   // $FF: synthetic method
   public boolean containsKey(Object var1) {
      return this.containsKey((String)var1);
   }
}
