package net.minecraft.client.resources.data;

import net.minecraft.client.resources.data.IMetadataSectionSerializer;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.IMetadataSerializer$1;

class IMetadataSerializer$Registration {

   final IMetadataSectionSerializer field_110502_a;
   final Class field_110500_b;
   // $FF: synthetic field
   final IMetadataSerializer field_110501_c;


   private IMetadataSerializer$Registration(IMetadataSerializer var1, IMetadataSectionSerializer var2, Class var3) {
      this.field_110501_c = var1;
      this.field_110502_a = var2;
      this.field_110500_b = var3;
   }

   // $FF: synthetic method
   IMetadataSerializer$Registration(IMetadataSerializer var1, IMetadataSectionSerializer var2, Class var3, IMetadataSerializer$1 var4) {
      this(var1, var2, var3);
   }
}
