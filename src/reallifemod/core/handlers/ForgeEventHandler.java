package de.ItsAMysterious.mods.reallifemod.core.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.ReallifemodNPC;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiEntityInteraction;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.ExplosionEvent.Detonate;
import org.lwjgl.input.Keyboard;

public class ForgeEventHandler {

   public static Random random;
   public static int dropped;
   private RealLifeProperties PlayerProps;


   @SubscribeEvent
   public void onEntityConstructing(EntityConstructing event) {
      if(event.entity instanceof EntityPlayer) {
         EntityPlayer entity = (EntityPlayer)event.entity;
         if(RealLifeProperties.get(entity) == null) {
            RealLifeProperties.register(entity);
         }
      }

   }

   @SubscribeEvent
   public void onEntityDrop(LivingDropsEvent event) {
      random = new Random();
      dropped = random.nextInt(2) + 1;
      if(event.entityLiving instanceof EntityZombie) {
         event.entityLiving.func_70099_a(new ItemStack(RealLifeMod_Items.circuitBrd), (float)dropped);
      }

   }

   @SubscribeEvent
   public void onEntityInteract(EntityInteractEvent event) {
      if(event.entityPlayer.field_70170_p.isRemote && Keyboard.isKeyDown(29)) {
         Minecraft.getMinecraft().displayGuiScreen(new GuiEntityInteraction(event.entityPlayer, event.target));
         if((event.target instanceof EntityPlayer || event.target instanceof ReallifemodNPC) && event.target instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer)event.target;
            p.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + event.entity.getCommandSenderName() + EnumChatFormatting.WHITE + " want\'s to become your husband!" + EnumChatFormatting.GREEN + " ACCEPT?"));
            ((RealLifeProperties)event.entity.getExtendedProperties("RealLifeProperties")).requestMariage(event.entityPlayer);
         }
      }

   }

   @SubscribeEvent
   public void onKillAnimal(LivingDeathEvent event) {}

   @SubscribeEvent
   public void onSleep(PlayerSleepInBedEvent event) {}

   @SubscribeEvent
   public void onExplosion(Detonate event) {}

   @SubscribeEvent
   public void onGuiOpened(GuiOpenEvent event) {
      if(event.gui instanceof GuiInventory) {
         ;
      }

   }
}
