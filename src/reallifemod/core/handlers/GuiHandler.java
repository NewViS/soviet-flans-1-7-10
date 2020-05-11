package de.ItsAMysterious.mods.reallifemod.core.handlers;

import cpw.mods.fml.common.network.IGuiHandler;
import de.ItsAMysterious.mods.reallifemod.core.entities.cars.EntityJeep;
import de.ItsAMysterious.mods.reallifemod.core.entities.cars.EntityVehicle;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiAdvert;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiCabinet;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiCookingRod;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiCreebay;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiDrawer;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiFreezer;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiFridge;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiJeep;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiMariageRequest;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiSideboard;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiVehicleModification;
import de.ItsAMysterious.mods.reallifemod.core.gui.containers.ContainerCabinet;
import de.ItsAMysterious.mods.reallifemod.core.gui.containers.ContainerCookingRod;
import de.ItsAMysterious.mods.reallifemod.core.gui.containers.ContainerDrawer;
import de.ItsAMysterious.mods.reallifemod.core.gui.containers.ContainerFreezer;
import de.ItsAMysterious.mods.reallifemod.core.gui.containers.ContainerFridge;
import de.ItsAMysterious.mods.reallifemod.core.gui.containers.ContainerJeep;
import de.ItsAMysterious.mods.reallifemod.core.gui.containers.ContainerSideboard;
import de.ItsAMysterious.mods.reallifemod.core.tiles.BilboardTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.CabinetTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ComputerTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.CookingRodTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.DrawerTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FreezerTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FridgeTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.SideboardTE;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

   private int pressedKey;


   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      if(ID == 50) {
         DrawerTE j6 = (DrawerTE)world.getTileEntity(x, y, z);
         return new ContainerDrawer(player.inventory, j6);
      } else if(ID == GuiCabinet.GUI_ID) {
         CabinetTE j5 = (CabinetTE)world.getTileEntity(x, y, z);
         return new ContainerCabinet(player.inventory, j5);
      } else if(ID == GuiFreezer.GUI_ID) {
         FreezerTE j4 = (FreezerTE)world.getTileEntity(x, y, z);
         return new ContainerFreezer(player.inventory, j4);
      } else if(ID == GuiSideboard.GUI_ID) {
         SideboardTE j3 = (SideboardTE)world.getTileEntity(x, y, z);
         return new ContainerSideboard(player.inventory, j3);
      } else if(ID == 56) {
         CookingRodTE j2 = (CookingRodTE)world.getTileEntity(x, y, z);
         return new ContainerCookingRod(player.inventory, j2);
      } else if(ID == GuiCreebay.GUI_ID) {
         return null;
      } else if(ID == GuiFridge.GUI_ID) {
         FridgeTE j1 = (FridgeTE)world.getTileEntity(x, y, z);
         return new ContainerFridge(player.inventory, j1);
      } else {
         EntityJeep j;
         if(player.func_70115_ae() && player.field_70154_o instanceof EntityVehicle && world.getEntityByID(ID - GuiVehicleModification.GUI_ID) instanceof EntityJeep) {
            j = (EntityJeep)world.getEntityByID(ID);
            return null;
         } else if(player.func_70115_ae() && player.field_70153_n instanceof EntityJeep && world.getEntityByID(ID) instanceof EntityJeep) {
            j = (EntityJeep)world.getEntityByID(ID);
            return new ContainerJeep(player.inventory, j);
         } else {
            return ID == 60?null:null;
         }
      }
   }

   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      if(ID == 50) {
         DrawerTE j8 = (DrawerTE)world.getTileEntity(x, y, z);
         return new GuiDrawer(player.inventory, j8);
      } else if(ID == GuiCabinet.GUI_ID) {
         CabinetTE j7 = (CabinetTE)world.getTileEntity(x, y, z);
         return new GuiCabinet(player.inventory, j7);
      } else if(ID == GuiFreezer.GUI_ID) {
         FreezerTE j6 = (FreezerTE)world.getTileEntity(x, y, z);
         return new GuiFreezer(player.inventory, j6);
      } else if(ID == GuiSideboard.GUI_ID) {
         SideboardTE j5 = (SideboardTE)world.getTileEntity(x, y, z);
         return new GuiSideboard(player.inventory, j5);
      } else {
         FridgeTE j1;
         if(ID == GuiFridge.GUI_ID) {
            j1 = (FridgeTE)world.getTileEntity(x, y, z);
            return new GuiFridge(player.inventory, j1);
         } else if(ID == GuiAdvert.GUI_ID) {
            BilboardTE j4 = (BilboardTE)world.getTileEntity(x, y, z);
            return new GuiAdvert(j4);
         } else if(ID == 56) {
            CookingRodTE j3 = (CookingRodTE)world.getTileEntity(x, y, z);
            return new GuiCookingRod(player.inventory, j3);
         } else if(ID == GuiCreebay.GUI_ID) {
            ComputerTE j2 = (ComputerTE)world.getTileEntity(x, y, z);
            return new GuiCreebay();
         } else if(ID == GuiFridge.GUI_ID) {
            j1 = (FridgeTE)world.getTileEntity(x, y, z);
            return new GuiFridge(player.inventory, j1);
         } else if(player.func_70115_ae() && player.field_70153_n instanceof EntityJeep && world.getEntityByID(ID - GuiVehicleModification.GUI_ID) instanceof EntityJeep) {
            EntityJeep j = (EntityJeep)world.getEntityByID(ID - GuiVehicleModification.GUI_ID);
            return new GuiVehicleModification(j);
         } else {
            return world.getEntityByID(ID) instanceof EntityVehicle?new GuiJeep(player.inventory, (EntityVehicle)world.getEntityByID(ID)):(ID == 60?new GuiMariageRequest(player.getCommandSenderName(), Minecraft.getMinecraft().objectMouseOver.entityHit.getCommandSenderName()):null);
         }
      }
   }

   public void openGui(EntityPlayer player, Object mod, int modGuiId, World world, int x, int y, int z, int KeyID) {
      player.openGui(mod, modGuiId, world, x, y, z);
      this.pressedKey = KeyID;
   }
}
