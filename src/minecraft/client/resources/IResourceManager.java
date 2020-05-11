package net.minecraft.client.resources;

import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

public interface IResourceManager {

   Set getResourceDomains();

   IResource getResource(ResourceLocation var1);

   List getAllResources(ResourceLocation var1);
}
