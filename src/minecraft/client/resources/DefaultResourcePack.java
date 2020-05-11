package net.minecraft.client.resources;

import com.google.common.collect.ImmutableSet;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

public class DefaultResourcePack implements IResourcePack {

   public static final Set defaultResourceDomains = ImmutableSet.of("minecraft", "realms");
   private final Map field_152781_b;


   public DefaultResourcePack(Map var1) {
      this.field_152781_b = var1;
   }

   public InputStream getInputStream(ResourceLocation var1) {
      InputStream var2 = this.getResourceStream(var1);
      if(var2 != null) {
         return var2;
      } else {
         InputStream var3 = this.func_152780_c(var1);
         if(var3 != null) {
            return var3;
         } else {
            throw new FileNotFoundException(var1.getResourcePath());
         }
      }
   }

   public InputStream func_152780_c(ResourceLocation var1) {
      File var2 = (File)this.field_152781_b.get(var1.toString());
      return var2 != null && var2.isFile()?new FileInputStream(var2):null;
   }

   private InputStream getResourceStream(ResourceLocation var1) {
      return DefaultResourcePack.class.getResourceAsStream("/assets/" + var1.getResourceDomain() + "/" + var1.getResourcePath());
   }

   public boolean resourceExists(ResourceLocation var1) {
      return this.getResourceStream(var1) != null || this.field_152781_b.containsKey(var1.toString());
   }

   public Set getResourceDomains() {
      return defaultResourceDomains;
   }

   public IMetadataSection getPackMetadata(IMetadataSerializer var1, String var2) {
      try {
         FileInputStream var3 = new FileInputStream((File)this.field_152781_b.get("pack.mcmeta"));
         return AbstractResourcePack.readMetadata(var1, var3, var2);
      } catch (RuntimeException var4) {
         return null;
      } catch (FileNotFoundException var5) {
         return null;
      }
   }

   public BufferedImage getPackImage() {
      return ImageIO.read(DefaultResourcePack.class.getResourceAsStream("/" + (new ResourceLocation("pack.png")).getResourcePath()));
   }

   public String getPackName() {
      return "Default";
   }

}
