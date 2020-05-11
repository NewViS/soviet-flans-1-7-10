package net.minecraft.util;

import org.apache.commons.lang3.Validate;

public class ResourceLocation {

   private final String resourceDomain;
   private final String resourcePath;


   public ResourceLocation(String var1, String var2) {
      Validate.notNull(var2);
      if(var1 != null && var1.length() != 0) {
         this.resourceDomain = var1;
      } else {
         this.resourceDomain = "minecraft";
      }

      this.resourcePath = var2;
   }

   public ResourceLocation(String var1) {
      String var2 = "minecraft";
      String var3 = var1;
      int var4 = var1.indexOf(58);
      if(var4 >= 0) {
         var3 = var1.substring(var4 + 1, var1.length());
         if(var4 > 1) {
            var2 = var1.substring(0, var4);
         }
      }

      this.resourceDomain = var2.toLowerCase();
      this.resourcePath = var3;
   }

   public String getResourcePath() {
      return this.resourcePath;
   }

   public String getResourceDomain() {
      return this.resourceDomain;
   }

   public String toString() {
      return this.resourceDomain + ":" + this.resourcePath;
   }

   public boolean equals(Object var1) {
      if(this == var1) {
         return true;
      } else if(!(var1 instanceof ResourceLocation)) {
         return false;
      } else {
         ResourceLocation var2 = (ResourceLocation)var1;
         return this.resourceDomain.equals(var2.resourceDomain) && this.resourcePath.equals(var2.resourcePath);
      }
   }

   public int hashCode() {
      return 31 * this.resourceDomain.hashCode() + this.resourcePath.hashCode();
   }
}
