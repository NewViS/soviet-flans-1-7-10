package net.minecraft.client.resources;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

public interface IResourcePack {

   InputStream getInputStream(ResourceLocation var1);

   boolean resourceExists(ResourceLocation var1);

   Set getResourceDomains();

   IMetadataSection getPackMetadata(IMetadataSerializer var1, String var2);

   BufferedImage getPackImage();

   String getPackName();
}
