package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.core.entitys.particles.EntityDrop;
import de.ItsAMysterious.mods.reallifemod.core.gui.Keyframe;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class FountainTE extends TileEntity {

   public boolean active;
   public int delta;
   public double FountainWidth = 0.25D;
   public double FountainHeight = 0.25D;
   public double RotationSpeed = 0.0D;
   public double rotation = 0.0D;
   public int rayCount = 16;
   public List Keyframes = new ArrayList();
   public int currentFrame;
   public int endFrame = 10;


   public FountainTE() {
      this.Keyframes.add(new Keyframe(20, 0.0D));
   }

   public void setKeyframelength(int length) {
      Keyframe[] copiedframes = new Keyframe[this.Keyframes.size()];

      int i;
      for(i = 0; i < length; ++i) {
         if(length <= this.Keyframes.size()) {
            copiedframes[i] = (Keyframe)this.Keyframes.get(i);
         }
      }

      this.Keyframes = new ArrayList(length);
      this.endFrame = length;

      for(i = 0; i < copiedframes.length; ++i) {
         this.Keyframes.add(copiedframes[i]);
      }

      this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
   }

   public void addFrame(Keyframe newFrame) {
      if(Keyframe.frameNumber <= this.endFrame) {
         this.Keyframes.add(Keyframe.frameNumber, newFrame);
      }

   }

   public int func_145832_p() {
      if(this.field_145850_b != null) {
         if(this.field_145847_g == -1) {
            this.field_145847_g = this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, this.field_145849_e);
         }

         return this.field_145847_g;
      } else {
         return 1;
      }
   }

   public void func_145845_h() {
      if(this.field_145850_b.isRemote) {
         ++this.currentFrame;
         double d1 = 1.0D;
         d1 = 6.283185307179586D / (double)this.rayCount;
         ++this.delta;
         this.rotation += this.RotationSpeed / 100.0D;

         for(int i = 0; i < this.rayCount; ++i) {
            this.field_145850_b.spawnEntityInWorld(new EntityDrop(this.field_145850_b, (double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.2D, (double)this.field_145849_e + 0.525D, Math.sin((double)i * d1 + this.rotation * 2.0D * 3.141592653589793D) * this.FountainWidth / 4.0D, this.FountainHeight, Math.cos((double)i * d1 + this.rotation * 2.0D * 3.141592653589793D) * this.FountainWidth / 4.0D, (float)this.rotation * 10.0F));
         }
      }

   }

   public double calculateDifferenceForKeyFrame(int numberWidth, int toFrame, double toValue) {
      Keyframe previousframe = new Keyframe(0, 0.0D);
      Keyframe nextFrame = new Keyframe(0, 0.0D);

      for(int i = 0; i < this.Keyframes.size(); ++i) {
         Keyframe var10000 = (Keyframe)this.Keyframes.get(i);
         if(Keyframe.frameNumber < toFrame) {
            previousframe = (Keyframe)this.Keyframes.get(i);
         } else {
            var10000 = (Keyframe)this.Keyframes.get(i);
            if(Keyframe.frameNumber > toFrame) {
               nextFrame = (Keyframe)this.Keyframes.get(i);
            }
         }
      }

      return Keyframe.value + Keyframe.value * (double)(Keyframe.frameNumber - Keyframe.frameNumber);
   }

   public void func_145839_a(NBTTagCompound compound) {
      super.readFromNBT(compound);
      NBTTagCompound tag = compound.getCompoundTag("FountainTag");
      this.active = tag.getBoolean("fountainactive");
      this.FountainWidth = tag.getDouble("FountainWidth");
      this.FountainHeight = tag.getDouble("FountainHeight");
      this.RotationSpeed = tag.getDouble("RotationSpeed");
      this.rotation = tag.getDouble("FountainRotation");
      this.rayCount = tag.getInteger("FountainRayCount");
      this.Keyframes = new ArrayList(tag.getInteger("KeyframeListLength"));

      for(int i = 0; i < this.Keyframes.size(); ++i) {
         this.Keyframes.add(new Keyframe(i, tag.getDouble("Keyframe_" + i)));
      }

   }

   public void func_145841_b(NBTTagCompound compound) {
      super.writeToNBT(compound);
      NBTTagCompound tag = new NBTTagCompound();
      tag.setBoolean("fountainactive", this.active);
      tag.setDouble("FountainWidth", this.FountainWidth);
      tag.setDouble("FountainHeight", this.FountainHeight);
      tag.setDouble("RotationSpeed", this.RotationSpeed);
      tag.setDouble("FountainRotation", this.rotation);
      tag.setInteger("FountainRayCount", this.rayCount);
      tag.setInteger("FountainAnimationLength", this.endFrame);
      tag.setInteger("KeyframeListLength", this.Keyframes.size());

      for(int i = 0; i < this.Keyframes.size(); ++i) {
         String var10001 = "Keyframe_" + i;
         Keyframe var10002 = (Keyframe)this.Keyframes.get(i);
         tag.setDouble(var10001, Keyframe.value);
      }

      compound.setTag("FountainTag", tag);
      this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
   }

   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
      NBTTagCompound nbttagcompound = pkt.func_148857_g();
      this.func_145839_a(nbttagcompound);
   }

   public Packet func_145844_m() {
      NBTTagCompound compound = new NBTTagCompound();
      compound.setBoolean("fountainactive", this.active);
      compound.setDouble("FountainWidth", this.FountainWidth);
      compound.setDouble("FountainHeight", this.FountainHeight);
      compound.setDouble("RotationSpeed", this.RotationSpeed);
      compound.setDouble("FountainRotation", this.rotation);
      compound.setInteger("FountainRayCount", this.rayCount);
      compound.setInteger("FountainAnimationLength", this.endFrame);
      compound.setInteger("KeyframeListLength", this.Keyframes.size());

      for(int i = 0; i < this.Keyframes.size(); ++i) {
         String var10001 = "Keyframe_" + i;
         Keyframe var10002 = (Keyframe)this.Keyframes.get(i);
         compound.setDouble(var10001, Keyframe.value);
      }

      this.func_145841_b(compound);
      return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145847_g, compound);
   }
}
