package net.minecraft.entity.ai.attributes;

import net.minecraft.entity.ai.attributes.IAttribute;

public abstract class BaseAttribute implements IAttribute {

   private final String unlocalizedName;
   private final double defaultValue;
   private boolean shouldWatch;


   protected BaseAttribute(String var1, double var2) {
      this.unlocalizedName = var1;
      this.defaultValue = var2;
      if(var1 == null) {
         throw new IllegalArgumentException("Name cannot be null!");
      }
   }

   public String getAttributeUnlocalizedName() {
      return this.unlocalizedName;
   }

   public double getDefaultValue() {
      return this.defaultValue;
   }

   public boolean getShouldWatch() {
      return this.shouldWatch;
   }

   public BaseAttribute setShouldWatch(boolean var1) {
      this.shouldWatch = var1;
      return this;
   }

   public int hashCode() {
      return this.unlocalizedName.hashCode();
   }
}
