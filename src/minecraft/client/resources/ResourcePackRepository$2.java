package net.minecraft.client.resources;

import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.HttpUtil$DownloadListener;

class ResourcePackRepository$2 implements HttpUtil$DownloadListener {

   // $FF: synthetic field
   final ResourcePackRepository field_148523_a;


   ResourcePackRepository$2(ResourcePackRepository var1) {
      this.field_148523_a = var1;
   }

   public void func_148522_a(File var1) {
      if(ResourcePackRepository.access$100(this.field_148523_a)) {
         ResourcePackRepository.access$102(this.field_148523_a, false);
         ResourcePackRepository.access$202(this.field_148523_a, new FileResourcePack(var1));
         Minecraft.getMinecraft().scheduleResourcesRefresh();
      }
   }
}
