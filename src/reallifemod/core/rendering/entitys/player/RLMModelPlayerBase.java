package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.player;

import api.player.model.ModelPlayerAPI;
import api.player.model.ModelPlayerBase;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.core.entities.cars.EntityJeep;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityBaby;
import de.ItsAMysterious.mods.reallifemod.core.items.AK47Item;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class RLMModelPlayerBase extends ModelPlayerBase {

   public RLMModelPlayerBase(ModelPlayerAPI par1ModelPlayerAPI) {
      super(par1ModelPlayerAPI);
   }

   public void setRotationAngles(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, Entity paramEntity) {
      super.setRotationAngles(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramEntity);
      if(paramEntity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)paramEntity;
         if(player.field_70153_n instanceof EntityBaby) {
            this.modelPlayer.field_78113_g.rotateAngleX = -45.0F;
            this.modelPlayer.field_78112_f.rotateAngleX = -0.6F;
            this.modelPlayer.field_78112_f.rotateAngleZ = 12.0F;
         }

         if(player.field_70154_o != null && player.field_70154_o instanceof EntityJeep) {
            EntityJeep vehicle = (EntityJeep)player.field_70154_o;
            if(vehicle.deltasteer > 0.0D) {
               this.modelPlayer.field_78113_g.rotateAngleX = 45.0F - (float)Math.cos(Math.toRadians(vehicle.deltasteer)) * 2.5F;
               this.modelPlayer.field_78113_g.rotateAngleZ = (float)Math.sin(Math.toRadians(vehicle.deltasteer));
               this.modelPlayer.field_78112_f.rotateAngleX = -45.0F - (float)Math.sin(Math.toRadians(vehicle.deltasteer));
               this.modelPlayer.field_78112_f.rotateAngleZ = (float)Math.sin(Math.toRadians(vehicle.deltasteer));
            } else {
               this.modelPlayer.field_78113_g.rotateAngleX = -45.0F + (float)Math.sin(Math.toRadians(vehicle.deltasteer));
               this.modelPlayer.field_78113_g.rotateAngleZ = (float)Math.sin(Math.toRadians(vehicle.deltasteer));
               this.modelPlayer.field_78112_f.rotateAngleX = -((float)Math.cos(Math.toRadians(vehicle.deltasteer)));
               this.modelPlayer.field_78112_f.rotateAngleZ = (float)Math.sin(Math.toRadians(vehicle.deltasteer));
            }
         }

         if(player.func_70090_H()) {
            GL11.glTranslated(0.0D, 1.0D, 0.0D);
            Minecraft.getMinecraft().playerController.enableEverythingIsScrewedUpMode();
            GL11.glRotated(90.0D, 1.0D, 0.0D, 0.0D);
         }

         RealLifeProperties.get(player);
         if(RealLifeProperties.Toilet > 10.0D && !player.capabilities.isCreativeMode) {
            player.func_70095_a(true);
            this.modelPlayer.field_78112_f.rotateAngleX = -0.5F;
            this.modelPlayer.field_78112_f.rotateAngleZ = -0.5F;
            this.modelPlayer.field_78113_g.rotateAngleX = -0.5F;
            this.modelPlayer.field_78113_g.rotateAngleZ = 0.5F;
         }

         if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof AK47Item && ((AK47Item)player.getCurrentEquippedItem().getItem()).aiming) {
            this.modelPlayer.field_78112_f.rotateAngleX = (float)(-1.5D + (double)(player.field_70125_A / 100.0F));
            this.modelPlayer.field_78112_f.rotateAngleY = -0.5F;
            this.modelPlayer.field_78113_g.rotateAngleX = (float)(-1.5D + (double)(player.field_70125_A / 100.0F));
            this.modelPlayer.field_78113_g.rotateAngleY = 0.5F;
         }
      }

   }
}
