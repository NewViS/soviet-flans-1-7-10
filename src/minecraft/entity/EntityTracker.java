package net.minecraft.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityTracker$1;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.network.Packet;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityTracker {

   private static final Logger logger = LogManager.getLogger();
   private final WorldServer theWorld;
   private Set trackedEntities = new HashSet();
   private IntHashMap trackedEntityIDs = new IntHashMap();
   private int entityViewDistance;


   public EntityTracker(WorldServer var1) {
      this.theWorld = var1;
      this.entityViewDistance = var1.func_73046_m().getConfigurationManager().getEntityViewDistance();
   }

   public void addEntityToTracker(Entity var1) {
      if(var1 instanceof EntityPlayerMP) {
         this.addEntityToTracker(var1, 512, 2);
         EntityPlayerMP var2 = (EntityPlayerMP)var1;
         Iterator var3 = this.trackedEntities.iterator();

         while(var3.hasNext()) {
            EntityTrackerEntry var4 = (EntityTrackerEntry)var3.next();
            if(var4.myEntity != var2) {
               var4.tryStartWachingThis(var2);
            }
         }
      } else if(var1 instanceof EntityFishHook) {
         this.addEntityToTracker(var1, 64, 5, true);
      } else if(var1 instanceof EntityArrow) {
         this.addEntityToTracker(var1, 64, 20, false);
      } else if(var1 instanceof EntitySmallFireball) {
         this.addEntityToTracker(var1, 64, 10, false);
      } else if(var1 instanceof EntityFireball) {
         this.addEntityToTracker(var1, 64, 10, false);
      } else if(var1 instanceof EntitySnowball) {
         this.addEntityToTracker(var1, 64, 10, true);
      } else if(var1 instanceof EntityEnderPearl) {
         this.addEntityToTracker(var1, 64, 10, true);
      } else if(var1 instanceof EntityEnderEye) {
         this.addEntityToTracker(var1, 64, 4, true);
      } else if(var1 instanceof EntityEgg) {
         this.addEntityToTracker(var1, 64, 10, true);
      } else if(var1 instanceof EntityPotion) {
         this.addEntityToTracker(var1, 64, 10, true);
      } else if(var1 instanceof EntityExpBottle) {
         this.addEntityToTracker(var1, 64, 10, true);
      } else if(var1 instanceof EntityFireworkRocket) {
         this.addEntityToTracker(var1, 64, 10, true);
      } else if(var1 instanceof EntityItem) {
         this.addEntityToTracker(var1, 64, 20, true);
      } else if(var1 instanceof EntityMinecart) {
         this.addEntityToTracker(var1, 80, 3, true);
      } else if(var1 instanceof EntityBoat) {
         this.addEntityToTracker(var1, 80, 3, true);
      } else if(var1 instanceof EntitySquid) {
         this.addEntityToTracker(var1, 64, 3, true);
      } else if(var1 instanceof EntityWither) {
         this.addEntityToTracker(var1, 80, 3, false);
      } else if(var1 instanceof EntityBat) {
         this.addEntityToTracker(var1, 80, 3, false);
      } else if(var1 instanceof IAnimals) {
         this.addEntityToTracker(var1, 80, 3, true);
      } else if(var1 instanceof EntityDragon) {
         this.addEntityToTracker(var1, 160, 3, true);
      } else if(var1 instanceof EntityTNTPrimed) {
         this.addEntityToTracker(var1, 160, 10, true);
      } else if(var1 instanceof EntityFallingBlock) {
         this.addEntityToTracker(var1, 160, 20, true);
      } else if(var1 instanceof EntityHanging) {
         this.addEntityToTracker(var1, 160, Integer.MAX_VALUE, false);
      } else if(var1 instanceof EntityXPOrb) {
         this.addEntityToTracker(var1, 160, 20, true);
      } else if(var1 instanceof EntityEnderCrystal) {
         this.addEntityToTracker(var1, 256, Integer.MAX_VALUE, false);
      }

   }

   public void addEntityToTracker(Entity var1, int var2, int var3) {
      this.addEntityToTracker(var1, var2, var3, false);
   }

   public void addEntityToTracker(Entity var1, int var2, int var3, boolean var4) {
      if(var2 > this.entityViewDistance) {
         var2 = this.entityViewDistance;
      }

      try {
         if(this.trackedEntityIDs.containsItem(var1.getEntityId())) {
            throw new IllegalStateException("Entity is already tracked!");
         }

         EntityTrackerEntry var5 = new EntityTrackerEntry(var1, var2, var3, var4);
         this.trackedEntities.add(var5);
         this.trackedEntityIDs.addKey(var1.getEntityId(), var5);
         var5.sendEventsToPlayers(this.theWorld.playerEntities);
      } catch (Throwable var11) {
         CrashReport var6 = CrashReport.makeCrashReport(var11, "Adding entity to track");
         CrashReportCategory var7 = var6.makeCategory("Entity To Track");
         var7.addCrashSection("Tracking range", var2 + " blocks");
         var7.addCrashSectionCallable("Update interval", new EntityTracker$1(this, var3));
         var1.addEntityCrashInfo(var7);
         CrashReportCategory var8 = var6.makeCategory("Entity That Is Already Tracked");
         ((EntityTrackerEntry)this.trackedEntityIDs.lookup(var1.getEntityId())).myEntity.addEntityCrashInfo(var8);

         try {
            throw new ReportedException(var6);
         } catch (ReportedException var10) {
            logger.error("\"Silently\" catching entity tracking error.", var10);
         }
      }

   }

   public void removeEntityFromAllTrackingPlayers(Entity var1) {
      if(var1 instanceof EntityPlayerMP) {
         EntityPlayerMP var2 = (EntityPlayerMP)var1;
         Iterator var3 = this.trackedEntities.iterator();

         while(var3.hasNext()) {
            EntityTrackerEntry var4 = (EntityTrackerEntry)var3.next();
            var4.removeFromWatchingList(var2);
         }
      }

      EntityTrackerEntry var5 = (EntityTrackerEntry)this.trackedEntityIDs.removeObject(var1.getEntityId());
      if(var5 != null) {
         this.trackedEntities.remove(var5);
         var5.informAllAssociatedPlayersOfItemDestruction();
      }

   }

   public void updateTrackedEntities() {
      ArrayList var1 = new ArrayList();
      Iterator var2 = this.trackedEntities.iterator();

      while(var2.hasNext()) {
         EntityTrackerEntry var3 = (EntityTrackerEntry)var2.next();
         var3.sendLocationToAllClients(this.theWorld.playerEntities);
         if(var3.playerEntitiesUpdated && var3.myEntity instanceof EntityPlayerMP) {
            var1.add((EntityPlayerMP)var3.myEntity);
         }
      }

      for(int var6 = 0; var6 < var1.size(); ++var6) {
         EntityPlayerMP var7 = (EntityPlayerMP)var1.get(var6);
         Iterator var4 = this.trackedEntities.iterator();

         while(var4.hasNext()) {
            EntityTrackerEntry var5 = (EntityTrackerEntry)var4.next();
            if(var5.myEntity != var7) {
               var5.tryStartWachingThis(var7);
            }
         }
      }

   }

   public void func_151247_a(Entity var1, Packet var2) {
      EntityTrackerEntry var3 = (EntityTrackerEntry)this.trackedEntityIDs.lookup(var1.getEntityId());
      if(var3 != null) {
         var3.func_151259_a(var2);
      }

   }

   public void func_151248_b(Entity var1, Packet var2) {
      EntityTrackerEntry var3 = (EntityTrackerEntry)this.trackedEntityIDs.lookup(var1.getEntityId());
      if(var3 != null) {
         var3.func_151261_b(var2);
      }

   }

   public void removePlayerFromTrackers(EntityPlayerMP var1) {
      Iterator var2 = this.trackedEntities.iterator();

      while(var2.hasNext()) {
         EntityTrackerEntry var3 = (EntityTrackerEntry)var2.next();
         var3.removePlayerFromTracker(var1);
      }

   }

   public void func_85172_a(EntityPlayerMP var1, Chunk var2) {
      Iterator var3 = this.trackedEntities.iterator();

      while(var3.hasNext()) {
         EntityTrackerEntry var4 = (EntityTrackerEntry)var3.next();
         if(var4.myEntity != var1 && var4.myEntity.chunkCoordX == var2.xPosition && var4.myEntity.chunkCoordZ == var2.zPosition) {
            var4.tryStartWachingThis(var1);
         }
      }

   }

}
