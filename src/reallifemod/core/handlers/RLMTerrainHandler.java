package de.ItsAMysterious.mods.reallifemod.core.handlers;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Blocks;
import de.ItsAMysterious.mods.reallifemod.core.blocks.BlockAirExtended;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Pre;

public class RLMTerrainHandler {

   @SubscribeEvent
   public void onTreeGrow(SaplingGrowTreeEvent event) {
      int posX = event.x;
      int posY = event.y;
      int posZ = event.z;
      World var10000 = event.world;
      new RealLifeMod_Blocks();
      var10000.setBlock(posX, posY, posZ, RealLifeMod_Blocks.firtree, 0, 0);
      System.out.println("Grown tree!");
   }

   @SubscribeEvent(
      priority = EventPriority.HIGH,
      receiveCanceled = false
   )
   public void onChunkLoad(Pre event) {
      Chunk chunk = event.world.getChunkFromChunkCoords(event.chunkX, event.chunkZ);
      BlockAirExtended toBlock = new BlockAirExtended();
      Block FromBlock = Blocks.air;
      ExtendedBlockStorage[] var5 = chunk.getBlockStorageArray();
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         ExtendedBlockStorage storage = var5[var7];
         if(storage != null) {
            for(int x = 0; x < 16; ++x) {
               for(int y = 0; y < 16; ++y) {
                  for(int z = 0; z < 16; ++z) {
                     if(storage.getBlockByExtId(x, y, z) == FromBlock) {
                        storage.func_150818_a(x, y, z, toBlock);
                     }
                  }
               }
            }
         }
      }

      chunk.isModified = true;
   }
}
