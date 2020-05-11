package net.minecraft.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.DataWatcher$WatchableObject;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.ReportedException;
import org.apache.commons.lang3.ObjectUtils;

public class DataWatcher {

   private final Entity field_151511_a;
   private boolean isBlank = true;
   private static final HashMap dataTypes = new HashMap();
   private final Map watchedObjects = new HashMap();
   private boolean objectChanged;
   private ReadWriteLock lock = new ReentrantReadWriteLock();


   public DataWatcher(Entity var1) {
      this.field_151511_a = var1;
   }

   public void addObject(int var1, Object var2) {
      Integer var3 = (Integer)dataTypes.get(var2.getClass());
      if(var3 == null) {
         throw new IllegalArgumentException("Unknown data type: " + var2.getClass());
      } else if(var1 > 31) {
         throw new IllegalArgumentException("Data value id is too big with " + var1 + "! (Max is " + 31 + ")");
      } else if(this.watchedObjects.containsKey(Integer.valueOf(var1))) {
         throw new IllegalArgumentException("Duplicate id value for " + var1 + "!");
      } else {
         DataWatcher$WatchableObject var4 = new DataWatcher$WatchableObject(var3.intValue(), var1, var2);
         this.lock.writeLock().lock();
         this.watchedObjects.put(Integer.valueOf(var1), var4);
         this.lock.writeLock().unlock();
         this.isBlank = false;
      }
   }

   public void addObjectByDataType(int var1, int var2) {
      DataWatcher$WatchableObject var3 = new DataWatcher$WatchableObject(var2, var1, (Object)null);
      this.lock.writeLock().lock();
      this.watchedObjects.put(Integer.valueOf(var1), var3);
      this.lock.writeLock().unlock();
      this.isBlank = false;
   }

   public byte getWatchableObjectByte(int var1) {
      return ((Byte)this.getWatchedObject(var1).getObject()).byteValue();
   }

   public short getWatchableObjectShort(int var1) {
      return ((Short)this.getWatchedObject(var1).getObject()).shortValue();
   }

   public int getWatchableObjectInt(int var1) {
      return ((Integer)this.getWatchedObject(var1).getObject()).intValue();
   }

   public float getWatchableObjectFloat(int var1) {
      return ((Float)this.getWatchedObject(var1).getObject()).floatValue();
   }

   public String getWatchableObjectString(int var1) {
      return (String)this.getWatchedObject(var1).getObject();
   }

   public ItemStack getWatchableObjectItemStack(int var1) {
      return (ItemStack)this.getWatchedObject(var1).getObject();
   }

   private DataWatcher$WatchableObject getWatchedObject(int var1) {
      this.lock.readLock().lock();

      DataWatcher$WatchableObject var2;
      try {
         var2 = (DataWatcher$WatchableObject)this.watchedObjects.get(Integer.valueOf(var1));
      } catch (Throwable var6) {
         CrashReport var4 = CrashReport.makeCrashReport(var6, "Getting synched entity data");
         CrashReportCategory var5 = var4.makeCategory("Synched entity data");
         var5.addCrashSection("Data ID", Integer.valueOf(var1));
         throw new ReportedException(var4);
      }

      this.lock.readLock().unlock();
      return var2;
   }

   public void updateObject(int var1, Object var2) {
      DataWatcher$WatchableObject var3 = this.getWatchedObject(var1);
      if(ObjectUtils.notEqual(var2, var3.getObject())) {
         var3.setObject(var2);
         this.field_151511_a.func_145781_i(var1);
         var3.setWatched(true);
         this.objectChanged = true;
      }

   }

   public void setObjectWatched(int var1) {
      DataWatcher$WatchableObject.access$002(this.getWatchedObject(var1), true);
      this.objectChanged = true;
   }

   public boolean hasChanges() {
      return this.objectChanged;
   }

   public static void writeWatchedListToPacketBuffer(List var0, PacketBuffer var1) {
      if(var0 != null) {
         Iterator var2 = var0.iterator();

         while(var2.hasNext()) {
            DataWatcher$WatchableObject var3 = (DataWatcher$WatchableObject)var2.next();
            writeWatchableObjectToPacketBuffer(var1, var3);
         }
      }

      var1.writeByte(127);
   }

   public List getChanged() {
      ArrayList var1 = null;
      if(this.objectChanged) {
         this.lock.readLock().lock();
         Iterator var2 = this.watchedObjects.values().iterator();

         while(var2.hasNext()) {
            DataWatcher$WatchableObject var3 = (DataWatcher$WatchableObject)var2.next();
            if(var3.isWatched()) {
               var3.setWatched(false);
               if(var1 == null) {
                  var1 = new ArrayList();
               }

               var1.add(var3);
            }
         }

         this.lock.readLock().unlock();
      }

      this.objectChanged = false;
      return var1;
   }

   public void func_151509_a(PacketBuffer var1) {
      this.lock.readLock().lock();
      Iterator var2 = this.watchedObjects.values().iterator();

      while(var2.hasNext()) {
         DataWatcher$WatchableObject var3 = (DataWatcher$WatchableObject)var2.next();
         writeWatchableObjectToPacketBuffer(var1, var3);
      }

      this.lock.readLock().unlock();
      var1.writeByte(127);
   }

   public List getAllWatched() {
      ArrayList var1 = null;
      this.lock.readLock().lock();

      DataWatcher$WatchableObject var3;
      for(Iterator var2 = this.watchedObjects.values().iterator(); var2.hasNext(); var1.add(var3)) {
         var3 = (DataWatcher$WatchableObject)var2.next();
         if(var1 == null) {
            var1 = new ArrayList();
         }
      }

      this.lock.readLock().unlock();
      return var1;
   }

   private static void writeWatchableObjectToPacketBuffer(PacketBuffer var0, DataWatcher$WatchableObject var1) {
      int var2 = (var1.getObjectType() << 5 | var1.getDataValueId() & 31) & 255;
      var0.writeByte(var2);
      switch(var1.getObjectType()) {
      case 0:
         var0.writeByte(((Byte)var1.getObject()).byteValue());
         break;
      case 1:
         var0.writeShort(((Short)var1.getObject()).shortValue());
         break;
      case 2:
         var0.writeInt(((Integer)var1.getObject()).intValue());
         break;
      case 3:
         var0.writeFloat(((Float)var1.getObject()).floatValue());
         break;
      case 4:
         var0.writeStringToBuffer((String)var1.getObject());
         break;
      case 5:
         ItemStack var4 = (ItemStack)var1.getObject();
         var0.writeItemStackToBuffer(var4);
         break;
      case 6:
         ChunkCoordinates var3 = (ChunkCoordinates)var1.getObject();
         var0.writeInt(var3.posX);
         var0.writeInt(var3.posY);
         var0.writeInt(var3.posZ);
      }

   }

   public static List readWatchedListFromPacketBuffer(PacketBuffer var0) {
      ArrayList var1 = null;

      for(byte var2 = var0.readByte(); var2 != 127; var2 = var0.readByte()) {
         if(var1 == null) {
            var1 = new ArrayList();
         }

         int var3 = (var2 & 224) >> 5;
         int var4 = var2 & 31;
         DataWatcher$WatchableObject var5 = null;
         switch(var3) {
         case 0:
            var5 = new DataWatcher$WatchableObject(var3, var4, Byte.valueOf(var0.readByte()));
            break;
         case 1:
            var5 = new DataWatcher$WatchableObject(var3, var4, Short.valueOf(var0.readShort()));
            break;
         case 2:
            var5 = new DataWatcher$WatchableObject(var3, var4, Integer.valueOf(var0.readInt()));
            break;
         case 3:
            var5 = new DataWatcher$WatchableObject(var3, var4, Float.valueOf(var0.readFloat()));
            break;
         case 4:
            var5 = new DataWatcher$WatchableObject(var3, var4, var0.readStringFromBuffer(32767));
            break;
         case 5:
            var5 = new DataWatcher$WatchableObject(var3, var4, var0.readItemStackFromBuffer());
            break;
         case 6:
            int var6 = var0.readInt();
            int var7 = var0.readInt();
            int var8 = var0.readInt();
            var5 = new DataWatcher$WatchableObject(var3, var4, new ChunkCoordinates(var6, var7, var8));
         }

         var1.add(var5);
      }

      return var1;
   }

   public void updateWatchedObjectsFromList(List var1) {
      this.lock.writeLock().lock();
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         DataWatcher$WatchableObject var3 = (DataWatcher$WatchableObject)var2.next();
         DataWatcher$WatchableObject var4 = (DataWatcher$WatchableObject)this.watchedObjects.get(Integer.valueOf(var3.getDataValueId()));
         if(var4 != null) {
            var4.setObject(var3.getObject());
            this.field_151511_a.func_145781_i(var3.getDataValueId());
         }
      }

      this.lock.writeLock().unlock();
      this.objectChanged = true;
   }

   public boolean getIsBlank() {
      return this.isBlank;
   }

   public void func_111144_e() {
      this.objectChanged = false;
   }

   static {
      dataTypes.put(Byte.class, Integer.valueOf(0));
      dataTypes.put(Short.class, Integer.valueOf(1));
      dataTypes.put(Integer.class, Integer.valueOf(2));
      dataTypes.put(Float.class, Integer.valueOf(3));
      dataTypes.put(String.class, Integer.valueOf(4));
      dataTypes.put(ItemStack.class, Integer.valueOf(5));
      dataTypes.put(ChunkCoordinates.class, Integer.valueOf(6));
   }
}
