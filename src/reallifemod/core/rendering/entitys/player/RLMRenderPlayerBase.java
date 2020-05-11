package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.player;

import api.player.render.RenderPlayerAPI;
import api.player.render.RenderPlayerBase;
import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.core.enums.EnumDress;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ResourceLocation;

public class RLMRenderPlayerBase extends RenderPlayerBase {

   public RLMRenderPlayerBase(RenderPlayerAPI renderPlayerAPI) {
      super(renderPlayerAPI);
   }

   public void loadTexture(ResourceLocation paramResourceLocation) {
      if(FMLClientHandler.instance().getSide().isClient()) {
         EntityClientPlayerMP player = FMLClientHandler.instance().getClientPlayerEntity();
         if(RealLifeProperties.get(player) != null) {
            String textureName = RealLifeProperties.get(player).getDressing();
            if(RealLifeProperties.get(player).dressing == EnumDress.NONE) {
               super.loadTexture(paramResourceLocation);
            } else {
               super.loadTexture(new ResourceLocation("reallifemod", "textures/entity/Dresses/" + textureName + "Skin.png"));
            }
         }
      }

   }
}
