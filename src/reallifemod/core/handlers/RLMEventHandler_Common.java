package de.ItsAMysterious.mods.reallifemod.core.handlers;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityBaby;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class RLMEventHandler_Common {

   public boolean wasMined;
   public Block lastBlockPlaced;


   @SubscribeEvent
   public void updateRealLifeProps(PlayerTickEvent event) {
      EntityPlayer player = event.player;
      if(event.player.getExtendedProperties("RealLifeProperties") != null) {
         ((RealLifeProperties)player.getExtendedProperties("RealLifeProperties")).updateStates();
      }

      if(player.field_70153_n instanceof EntityBaby && !player.field_70170_p.isRemote && Mouse.isButtonDown(1) && Keyboard.isKeyDown(42) && Keyboard.isKeyDown(29)) {
         ((EntityBaby)player.field_70153_n).field_70145_X = false;
         ((EntityBaby)player.field_70153_n).isLying = false;
         ((EntityBaby)player.field_70153_n).func_70078_a((Entity)null);
      }

   }

   @SubscribeEvent(
      priority = EventPriority.NORMAL,
      receiveCanceled = true
   )
   public void onEvent(KeyInputEvent event) {
      if(ClientProxy.Key_Horn.isPressed()) {
         System.out.println("Works");
      }

   }

   @SubscribeEvent
   public void onLogin(PlayerLoggedInEvent event) {}

   @SubscribeEvent
   public void onLogout(PlayerLoggedOutEvent event) {}

   @SubscribeEvent
   public void onItemPickup(ItemPickupEvent event) {
      financialProps var10000;
      if(event.pickedUp.getEntityItem().getItem() == Item.getItemFromBlock(Blocks.iron_ore)) {
         var10000 = (financialProps)event.player.getExtendedProperties("financialProps");
         financialProps.Cash += 20.0D;
      }

      if(event.pickedUp.getEntityItem().getItem() == Item.getItemFromBlock(Blocks.gold_ore)) {
         var10000 = (financialProps)event.player.getExtendedProperties("financialProps");
         financialProps.Cash += 100.0D;
      }

      if(event.pickedUp.getEntityItem().getItem() == Items.diamond) {
         var10000 = (financialProps)event.player.getExtendedProperties("financialProps");
         financialProps.Cash += 500.0D;
      }

      if(event.pickedUp.getEntityItem().getItem() == Item.getItemFromBlock(Blocks.emerald_ore)) {
         var10000 = (financialProps)event.player.getExtendedProperties("financialProps");
         financialProps.Cash += 1000.0D;
      }

      this.wasMined = false;
   }

   public double itemPrice(Item item) {
      return 0.0D;
   }

   @SubscribeEvent
   public void onBlockBreak(BreakEvent event) {
      if(event.block.getClass() != this.lastBlockPlaced.getClass()) {
         this.wasMined = true;
      }

   }

   @SubscribeEvent
   public void onBLockPlaced(PlaceEvent event) {
      this.lastBlockPlaced = event.block;
   }
}
