package net.minecraft.client.gui;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.client.gui.MapItemRenderer$1;
import net.minecraft.client.gui.MapItemRenderer$Instance;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;

public class MapItemRenderer {

   private static final ResourceLocation field_148253_a = new ResourceLocation("textures/map/map_icons.png");
   private final TextureManager field_148251_b;
   private final Map field_148252_c = Maps.newHashMap();


   public MapItemRenderer(TextureManager var1) {
      this.field_148251_b = var1;
   }

   public void func_148246_a(MapData var1) {
      MapItemRenderer$Instance.access$000(this.func_148248_b(var1));
   }

   public void func_148250_a(MapData var1, boolean var2) {
      MapItemRenderer$Instance.access$100(this.func_148248_b(var1), var2);
   }

   private MapItemRenderer$Instance func_148248_b(MapData var1) {
      MapItemRenderer$Instance var2 = (MapItemRenderer$Instance)this.field_148252_c.get(var1.mapName);
      if(var2 == null) {
         var2 = new MapItemRenderer$Instance(this, var1, (MapItemRenderer$1)null);
         this.field_148252_c.put(var1.mapName, var2);
      }

      return var2;
   }

   public void func_148249_a() {
      Iterator var1 = this.field_148252_c.values().iterator();

      while(var1.hasNext()) {
         MapItemRenderer$Instance var2 = (MapItemRenderer$Instance)var1.next();
         this.field_148251_b.deleteTexture(MapItemRenderer$Instance.access$300(var2));
      }

      this.field_148252_c.clear();
   }

   // $FF: synthetic method
   static TextureManager access$400(MapItemRenderer var0) {
      return var0.field_148251_b;
   }

   // $FF: synthetic method
   static ResourceLocation access$500() {
      return field_148253_a;
   }

}
