package net.minecraft.network.play.server;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S0CPacketSpawnPlayer extends Packet {

   private int field_148957_a;
   private GameProfile field_148955_b;
   private int field_148956_c;
   private int field_148953_d;
   private int field_148954_e;
   private byte field_148951_f;
   private byte field_148952_g;
   private int field_148959_h;
   private DataWatcher field_148960_i;
   private List field_148958_j;


   public S0CPacketSpawnPlayer() {}

   public S0CPacketSpawnPlayer(EntityPlayer var1) {
      this.field_148957_a = var1.getEntityId();
      this.field_148955_b = var1.getGameProfile();
      this.field_148956_c = MathHelper.floor_double(var1.posX * 32.0D);
      this.field_148953_d = MathHelper.floor_double(var1.posY * 32.0D);
      this.field_148954_e = MathHelper.floor_double(var1.posZ * 32.0D);
      this.field_148951_f = (byte)((int)(var1.rotationYaw * 256.0F / 360.0F));
      this.field_148952_g = (byte)((int)(var1.rotationPitch * 256.0F / 360.0F));
      ItemStack var2 = var1.inventory.getCurrentItem();
      this.field_148959_h = var2 == null?0:Item.getIdFromItem(var2.getItem());
      this.field_148960_i = var1.getDataWatcher();
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_148957_a = var1.readVarIntFromBuffer();
      UUID var2 = UUID.fromString(var1.readStringFromBuffer(36));
      this.field_148955_b = new GameProfile(var2, var1.readStringFromBuffer(16));
      int var3 = var1.readVarIntFromBuffer();

      for(int var4 = 0; var4 < var3; ++var4) {
         String var5 = var1.readStringFromBuffer(32767);
         String var6 = var1.readStringFromBuffer(32767);
         String var7 = var1.readStringFromBuffer(32767);
         this.field_148955_b.getProperties().put(var5, new Property(var5, var6, var7));
      }

      this.field_148956_c = var1.readInt();
      this.field_148953_d = var1.readInt();
      this.field_148954_e = var1.readInt();
      this.field_148951_f = var1.readByte();
      this.field_148952_g = var1.readByte();
      this.field_148959_h = var1.readShort();
      this.field_148958_j = DataWatcher.readWatchedListFromPacketBuffer(var1);
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.field_148957_a);
      UUID var2 = this.field_148955_b.getId();
      var1.writeStringToBuffer(var2 == null?"":var2.toString());
      var1.writeStringToBuffer(this.field_148955_b.getName());
      var1.writeVarIntToBuffer(this.field_148955_b.getProperties().size());
      Iterator var3 = this.field_148955_b.getProperties().values().iterator();

      while(var3.hasNext()) {
         Property var4 = (Property)var3.next();
         var1.writeStringToBuffer(var4.getName());
         var1.writeStringToBuffer(var4.getValue());
         var1.writeStringToBuffer(var4.getSignature());
      }

      var1.writeInt(this.field_148956_c);
      var1.writeInt(this.field_148953_d);
      var1.writeInt(this.field_148954_e);
      var1.writeByte(this.field_148951_f);
      var1.writeByte(this.field_148952_g);
      var1.writeShort(this.field_148959_h);
      this.field_148960_i.func_151509_a(var1);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSpawnPlayer(this);
   }

   public List func_148944_c() {
      if(this.field_148958_j == null) {
         this.field_148958_j = this.field_148960_i.getAllWatched();
      }

      return this.field_148958_j;
   }

   public String serialize() {
      return String.format("id=%d, gameProfile=\'%s\', x=%.2f, y=%.2f, z=%.2f, carried=%d", new Object[]{Integer.valueOf(this.field_148957_a), this.field_148955_b, Float.valueOf((float)this.field_148956_c / 32.0F), Float.valueOf((float)this.field_148953_d / 32.0F), Float.valueOf((float)this.field_148954_e / 32.0F), Integer.valueOf(this.field_148959_h)});
   }

   public int func_148943_d() {
      return this.field_148957_a;
   }

   public GameProfile func_148948_e() {
      return this.field_148955_b;
   }

   public int func_148942_f() {
      return this.field_148956_c;
   }

   public int func_148949_g() {
      return this.field_148953_d;
   }

   public int func_148946_h() {
      return this.field_148954_e;
   }

   public byte func_148941_i() {
      return this.field_148951_f;
   }

   public byte func_148945_j() {
      return this.field_148952_g;
   }

   public int func_148947_k() {
      return this.field_148959_h;
   }
}
