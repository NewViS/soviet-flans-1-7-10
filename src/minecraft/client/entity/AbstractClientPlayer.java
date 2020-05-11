package net.minecraft.client.entity;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer$SwitchType;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.client.resources.SkinManager$SkinAvailableCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

public abstract class AbstractClientPlayer extends EntityPlayer implements SkinManager$SkinAvailableCallback {

   public static final ResourceLocation locationStevePng = new ResourceLocation("textures/entity/steve.png");
   private ResourceLocation locationSkin;
   private ResourceLocation locationCape;


   public AbstractClientPlayer(World var1, GameProfile var2) {
      super(var1, var2);
      String var3 = this.getCommandSenderName();
      if(!var3.isEmpty()) {
         SkinManager var4 = Minecraft.getMinecraft().func_152342_ad();
         var4.func_152790_a(var2, this, true);
      }

   }

   public boolean func_152122_n() {
      return this.locationCape != null;
   }

   public boolean func_152123_o() {
      return this.locationSkin != null;
   }

   public ResourceLocation getLocationSkin() {
      return this.locationSkin == null?locationStevePng:this.locationSkin;
   }

   public ResourceLocation getLocationCape() {
      return this.locationCape;
   }

   public static ThreadDownloadImageData getDownloadImageSkin(ResourceLocation var0, String var1) {
      TextureManager var2 = Minecraft.getMinecraft().getTextureManager();
      Object var3 = var2.getTexture(var0);
      if(var3 == null) {
         var3 = new ThreadDownloadImageData((File)null, String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", new Object[]{StringUtils.stripControlCodes(var1)}), locationStevePng, new ImageBufferDownload());
         var2.loadTexture(var0, (ITextureObject)var3);
      }

      return (ThreadDownloadImageData)var3;
   }

   public static ResourceLocation getLocationSkin(String var0) {
      return new ResourceLocation("skins/" + StringUtils.stripControlCodes(var0));
   }

   public void func_152121_a(Type var1, ResourceLocation var2) {
      switch(AbstractClientPlayer$SwitchType.field_152630_a[var1.ordinal()]) {
      case 1:
         this.locationSkin = var2;
         break;
      case 2:
         this.locationCape = var2;
      }

   }

}
