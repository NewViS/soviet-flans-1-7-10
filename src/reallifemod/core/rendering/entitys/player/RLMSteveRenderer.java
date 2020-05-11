package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.player;

import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityBaby;
import de.ItsAMysterious.mods.reallifemod.core.enums.EnumDress;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

public class RLMSteveRenderer extends RenderPlayer {

   public void func_76986_a(AbstractClientPlayer player, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      if(player.field_70153_n instanceof EntityBaby) {
         this.field_77109_a.bipedLeftArm.rotateAngleX = 90.0F;
      }

      super.doRender(player, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(AbstractClientPlayer player) {
      if(RealLifeProperties.get(player) != null) {
         String textureName = RealLifeProperties.get(player).getDressing();
         return RealLifeProperties.get(player).dressing == EnumDress.NONE?player.getLocationSkin():new ResourceLocation("reallifemod", "textures/entity/Dresses/" + textureName + "Skin.png");
      } else {
         return player.getLocationSkin();
      }
   }

   protected void func_77041_b(AbstractClientPlayer player, float p_77041_2_) {
      float f1 = 0.9375F;
      GL11.glScalef(f1, f1, f1);
   }

   public float getSize(Float age) {
      return (float)((double)(age.floatValue() / 12.0F) * 0.7D);
   }

   protected int func_77032_a(AbstractClientPlayer p_77032_1_, int p_77032_2_, float p_77032_3_) {
      ItemStack itemstack = p_77032_1_.field_71071_by.armorItemInSlot(3 - p_77032_2_);
      SetArmorModel event = new SetArmorModel(p_77032_1_, this, 3 - p_77032_2_, p_77032_3_, itemstack);
      MinecraftForge.EVENT_BUS.post(event);
      if(event.result != -1) {
         return event.result;
      } else if(p_77032_1_.field_70153_n instanceof EntityBaby) {
         this.field_77109_a.bipedLeftArm.rotateAngleX += 90.0F;
         return 0;
      } else {
         return -1;
      }
   }
}
