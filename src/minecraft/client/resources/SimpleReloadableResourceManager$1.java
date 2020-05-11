package net.minecraft.client.resources;

import com.google.common.base.Function;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager;

class SimpleReloadableResourceManager$1 implements Function {

   // $FF: synthetic field
   final SimpleReloadableResourceManager theSimpleReloadableResourceManager;


   SimpleReloadableResourceManager$1(SimpleReloadableResourceManager var1) {
      this.theSimpleReloadableResourceManager = var1;
   }

   public String apply(IResourcePack var1) {
      return var1.getPackName();
   }

   // $FF: synthetic method
   public Object apply(Object var1) {
      return this.apply((IResourcePack)var1);
   }
}
