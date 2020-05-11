package net.minecraft.network.play.server;

import java.util.List;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S0FPacketSpawnMob extends Packet {

   private int field_149042_a;
   private int field_149040_b;
   private int field_149041_c;
   private int field_149038_d;
   private int field_149039_e;
   private int field_149036_f;
   private int field_149037_g;
   private int field_149047_h;
   private byte field_149048_i;
   private byte field_149045_j;
   private byte field_149046_k;
   private DataWatcher field_149043_l;
   private List field_149044_m;


   public S0FPacketSpawnMob() {}

   public S0FPacketSpawnMob(EntityLivingBase var1) {
      this.field_149042_a = var1.getEntityId();
      this.field_149040_b = (byte)EntityList.getEntityID(var1);
      this.field_149041_c = var1.myEntitySize.multiplyBy32AndRound(var1.posX);
      this.field_149038_d = MathHelper.floor_double(var1.posY * 32.0D);
      this.field_149039_e = var1.myEntitySize.multiplyBy32AndRound(var1.posZ);
      this.field_149048_i = (byte)((int)(var1.rotationYaw * 256.0F / 360.0F));
      this.field_149045_j = (byte)((int)(var1.rotationPitch * 256.0F / 360.0F));
      this.field_149046_k = (byte)((int)(var1.rotationYawHead * 256.0F / 360.0F));
      double var2 = 3.9D;
      double var4 = var1.motionX;
      double var6 = var1.motionY;
      double var8 = var1.motionZ;
      if(var4 < -var2) {
         var4 = -var2;
      }

      if(var6 < -var2) {
         var6 = -var2;
      }

      if(var8 < -var2) {
         var8 = -var2;
      }

      if(var4 > var2) {
         var4 = var2;
      }

      if(var6 > var2) {
         var6 = var2;
      }

      if(var8 > var2) {
         var8 = var2;
      }

      this.field_149036_f = (int)(var4 * 8000.0D);
      this.field_149037_g = (int)(var6 * 8000.0D);
      this.field_149047_h = (int)(var8 * 8000.0D);
      this.field_149043_l = var1.getDataWatcher();
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149042_a = var1.readVarIntFromBuffer();
      this.field_149040_b = var1.readByte() & 255;
      this.field_149041_c = var1.readInt();
      this.field_149038_d = var1.readInt();
      this.field_149039_e = var1.readInt();
      this.field_149048_i = var1.readByte();
      this.field_149045_j = var1.readByte();
      this.field_149046_k = var1.readByte();
      this.field_149036_f = var1.readShort();
      this.field_149037_g = var1.readShort();
      this.field_149047_h = var1.readShort();
      this.field_149044_m = DataWatcher.readWatchedListFromPacketBuffer(var1);
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.field_149042_a);
      var1.writeByte(this.field_149040_b & 255);
      var1.writeInt(this.field_149041_c);
      var1.writeInt(this.field_149038_d);
      var1.writeInt(this.field_149039_e);
      var1.writeByte(this.field_149048_i);
      var1.writeByte(this.field_149045_j);
      var1.writeByte(this.field_149046_k);
      var1.writeShort(this.field_149036_f);
      var1.writeShort(this.field_149037_g);
      var1.writeShort(this.field_149047_h);
      this.field_149043_l.func_151509_a(var1);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSpawnMob(this);
   }

   public List func_149027_c() {
      if(this.field_149044_m == null) {
         this.field_149044_m = this.field_149043_l.getAllWatched();
      }

      return this.field_149044_m;
   }

   public String serialize() {
      return String.format("id=%d, type=%d, x=%.2f, y=%.2f, z=%.2f, xd=%.2f, yd=%.2f, zd=%.2f", new Object[]{Integer.valueOf(this.field_149042_a), Integer.valueOf(this.field_149040_b), Float.valueOf((float)this.field_149041_c / 32.0F), Float.valueOf((float)this.field_149038_d / 32.0F), Float.valueOf((float)this.field_149039_e / 32.0F), Float.valueOf((float)this.field_149036_f / 8000.0F), Float.valueOf((float)this.field_149037_g / 8000.0F), Float.valueOf((float)this.field_149047_h / 8000.0F)});
   }

   public int func_149024_d() {
      return this.field_149042_a;
   }

   public int func_149025_e() {
      return this.field_149040_b;
   }

   public int func_149023_f() {
      return this.field_149041_c;
   }

   public int func_149034_g() {
      return this.field_149038_d;
   }

   public int func_149029_h() {
      return this.field_149039_e;
   }

   public int func_149026_i() {
      return this.field_149036_f;
   }

   public int func_149033_j() {
      return this.field_149037_g;
   }

   public int func_149031_k() {
      return this.field_149047_h;
   }

   public byte func_149028_l() {
      return this.field_149048_i;
   }

   public byte func_149030_m() {
      return this.field_149045_j;
   }

   public byte func_149032_n() {
      return this.field_149046_k;
   }
}
