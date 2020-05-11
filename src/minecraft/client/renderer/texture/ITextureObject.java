package net.minecraft.client.renderer.texture;

import net.minecraft.client.resources.IResourceManager;

public interface ITextureObject {

   void loadTexture(IResourceManager var1);

   int getGlTextureId();
}
