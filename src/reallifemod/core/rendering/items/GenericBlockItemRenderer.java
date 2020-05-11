package de.ItsAMysterious.mods.reallifemod.core.rendering.items;

import de.ItsAMysterious.mods.reallifemod.config.RealLifeModConfig;
import de.ItsAMysterious.mods.reallifemod.core.tiles.BabybedTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.BilboardTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.BlastfurnaceTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.CabinetTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ChairTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ChristmasTreeTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.CookingRodTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.CupboardTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.DeskTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.DishwasherTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.EspressoTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FirTreeTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FishtankTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FreezerTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FridgeTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.HeatingTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.LanternTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.NeonlampTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.OakTreeTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.PissoirTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.RadioTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ShelfTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ShowcaseTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.SinkTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.TileEntityTV;
import de.ItsAMysterious.mods.reallifemod.core.tiles.TrafficlightTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.WashbasinTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.atmTE;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class GenericBlockItemRenderer implements IItemRenderer {

   TileEntitySpecialRenderer render;
   private TileEntity entity;


   public GenericBlockItemRenderer(TileEntitySpecialRenderer render, TileEntity entity) {
      this.entity = entity;
      this.render = render;
   }

   public boolean handleRenderType(ItemStack item, ItemRenderType type) {
      return true;
   }

   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      return true;
   }

   public void renderItem(ItemRenderType type, ItemStack item, Object ... data) {
      if(type == ItemRenderType.ENTITY) {
         GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
      }

      GL11.glPushMatrix();
      if(this.entity instanceof TileEntityTV) {
         GL11.glTranslatef(-0.15F, -0.35F, -0.25F);
         GL11.glScaled(0.9D, 0.9D, 0.9D);
      }

      if(this.entity instanceof CupboardTE) {
         GL11.glTranslatef(-0.25F, -0.6F, -0.275F);
         GL11.glScaled(0.45D, 0.44D, 0.45D);
      }

      if(this.entity instanceof FreezerTE) {
         GL11.glTranslatef(-0.55F, -0.6F, -0.275F);
         GL11.glScaled(0.6D, 0.6D, 0.6D);
      }

      if(this.entity instanceof ChairTE) {
         GL11.glTranslatef(-0.3F, -0.6F, -0.275F);
         GL11.glScaled(0.75D, 0.75D, 0.75D);
      }

      if(this.entity instanceof FishtankTE) {
         GL11.glScaled(0.75D, 0.75D, 0.75D);
      }

      if(this.entity instanceof CabinetTE) {
         GL11.glTranslatef(0.0F, -0.1F, 0.0F);
         GL11.glScaled(0.9D, 0.9D, 0.9D);
      }

      if(this.entity instanceof ShelfTE) {
         GL11.glTranslated(0.15000000596046448D, -0.10000000149011612D, 0.0D);
         GL11.glScaled(0.9D, 0.9D, 0.9D);
      }

      if(this.entity instanceof ShowcaseTE) {
         GL11.glDisable(2896);
         GL11.glTranslated(0.0D, -0.3499999940395355D, 0.0D);
         GL11.glScaled(0.45D, 0.45D, 0.45D);
         GL11.glEnable(2896);
      }

      if(this.entity instanceof DishwasherTE) {
         GL11.glTranslated(0.0D, -0.1D, 0.0D);
         GL11.glScaled(0.9D, 0.9D, 0.9D);
      }

      if(this.entity instanceof SinkTE) {
         GL11.glTranslated(0.0D, -0.1D, 0.0D);
         GL11.glScaled(0.9D, 0.9D, 0.9D);
      }

      if(this.entity instanceof HeatingTE) {
         GL11.glTranslated(0.25D, -0.10000000149011612D, 0.0D);
      }

      if(this.entity instanceof RadioTE) {
         GL11.glTranslated(0.0D, -0.10000000149011612D, 0.0D);
         GL11.glScaled(0.9D, 0.9D, 0.9D);
      }

      if(this.entity instanceof NeonlampTE) {
         GL11.glTranslated(0.0D, -0.25D, 0.0D);
         GL11.glScaled(0.8D, 0.75D, 0.8D);
      }

      if(this.entity instanceof FridgeTE) {
         GL11.glTranslated(0.0D, -0.25D, 0.0D);
         GL11.glScaled(0.55D, 0.55D, 0.55D);
      }

      if(this.entity instanceof FirTreeTE) {
         GL11.glTranslated(0.0D, -0.75D, 0.0D);
         GL11.glScaled(0.085D, 0.085D, 0.085D);
      }

      if(this.entity instanceof OakTreeTE) {
         GL11.glTranslated(0.05000000074505806D, -0.75D, 0.0D);
         GL11.glScaled(0.175D, 0.175D, 0.175D);
      }

      if(this.entity instanceof LanternTE) {
         GL11.glTranslated(-0.5D, -1.0D, 0.0D);
         GL11.glScaled(0.5D, 0.5D, 0.5D);
      }

      if(this.entity instanceof TrafficlightTE) {
         GL11.glTranslated(0.05000000074505806D, -0.6499999761581421D, 0.0D);
         GL11.glScaled(0.5D, 0.5D, 0.5D);
      }

      if(this.entity instanceof DeskTE) {
         GL11.glTranslated(-0.3499999940395355D, -0.5D, 0.0D);
         GL11.glScaled(0.7D, 0.7D, 0.7D);
      }

      if(this.entity instanceof EspressoTE) {
         GL11.glTranslated(0.05000000074505806D, -0.15000000596046448D, 0.0D);
         GL11.glScaled(0.85D, 0.85D, 0.85D);
      }

      if(this.entity instanceof PissoirTE) {
         if(!RealLifeModConfig.minecraftstyle) {
            GL11.glTranslated(-0.75D, -0.75D, 0.0D);
         } else {
            GL11.glTranslated(0.15D, -0.6D, 0.0D);
            GL11.glRotated(-90.0D, 0.0D, 1.0D, 0.0D);
         }

         GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
      }

      if(this.entity instanceof ChristmasTreeTE) {
         GL11.glTranslated(0.0D, -0.45D, 0.0D);
         GL11.glScaled(0.6D, 0.6D, 0.6D);
      }

      if(this.entity instanceof WashbasinTE) {
         if(!RealLifeModConfig.minecraftstyle) {
            GL11.glScaled(1.25D, 1.25D, 1.25D);
            GL11.glTranslated(0.25D, -0.6D, 0.0D);
         } else {
            GL11.glTranslated(0.25D, -0.25D, 0.0D);
         }
      }

      if(this.entity instanceof BilboardTE) {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glTranslated(0.25D, -0.7D, 0.0D);
      }

      if(this.entity instanceof BlastfurnaceTE) {
         GL11.glTranslated(-0.275D, -0.575D, 0.0D);
         GL11.glScaled(0.65D, 0.65D, 0.65D);
      }

      if(this.entity instanceof atmTE) {
         GL11.glTranslated(0.0D, -0.35D, 0.0D);
         GL11.glScaled(0.65D, 0.65D, 0.65D);
      }

      if(this.entity instanceof BabybedTE) {
         GL11.glScaled(0.65D, 0.65D, 0.65D);
      }

      if(this.entity instanceof CookingRodTE) {
         if(type == ItemRenderType.EQUIPPED) {
            GL11.glScaled(2.0D, 2.0D, 2.0D);
         } else {
            GL11.glTranslated(0.0D, 0.35D, 0.0D);
            GL11.glScaled(0.65D, 0.65D, 0.65D);
         }
      }

      TileEntityRendererDispatcher.instance.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F);
      GL11.glPopMatrix();
   }
}
