package net.minecraft.world.gen.structure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;

public class StructureMineshaftPieces$Room extends StructureComponent {

   private List roomsLinkedToTheRoom = new LinkedList();


   public StructureMineshaftPieces$Room() {}

   public StructureMineshaftPieces$Room(int var1, Random var2, int var3, int var4) {
      super(var1);
      super.boundingBox = new StructureBoundingBox(var3, 50, var4, var3 + 7 + var2.nextInt(6), 54 + var2.nextInt(6), var4 + 7 + var2.nextInt(6));
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      int var4 = this.getComponentType();
      int var6 = super.boundingBox.getYSize() - 3 - 1;
      if(var6 <= 0) {
         var6 = 1;
      }

      int var5;
      StructureComponent var7;
      StructureBoundingBox var8;
      for(var5 = 0; var5 < super.boundingBox.getXSize(); var5 += 4) {
         var5 += var3.nextInt(super.boundingBox.getXSize());
         if(var5 + 3 > super.boundingBox.getXSize()) {
            break;
         }

         var7 = StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var3.nextInt(var6) + 1, super.boundingBox.minZ - 1, 2, var4);
         if(var7 != null) {
            var8 = var7.getBoundingBox();
            this.roomsLinkedToTheRoom.add(new StructureBoundingBox(var8.minX, var8.minY, super.boundingBox.minZ, var8.maxX, var8.maxY, super.boundingBox.minZ + 1));
         }
      }

      for(var5 = 0; var5 < super.boundingBox.getXSize(); var5 += 4) {
         var5 += var3.nextInt(super.boundingBox.getXSize());
         if(var5 + 3 > super.boundingBox.getXSize()) {
            break;
         }

         var7 = StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var3.nextInt(var6) + 1, super.boundingBox.maxZ + 1, 0, var4);
         if(var7 != null) {
            var8 = var7.getBoundingBox();
            this.roomsLinkedToTheRoom.add(new StructureBoundingBox(var8.minX, var8.minY, super.boundingBox.maxZ - 1, var8.maxX, var8.maxY, super.boundingBox.maxZ));
         }
      }

      for(var5 = 0; var5 < super.boundingBox.getZSize(); var5 += 4) {
         var5 += var3.nextInt(super.boundingBox.getZSize());
         if(var5 + 3 > super.boundingBox.getZSize()) {
            break;
         }

         var7 = StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY + var3.nextInt(var6) + 1, super.boundingBox.minZ + var5, 1, var4);
         if(var7 != null) {
            var8 = var7.getBoundingBox();
            this.roomsLinkedToTheRoom.add(new StructureBoundingBox(super.boundingBox.minX, var8.minY, var8.minZ, super.boundingBox.minX + 1, var8.maxY, var8.maxZ));
         }
      }

      for(var5 = 0; var5 < super.boundingBox.getZSize(); var5 += 4) {
         var5 += var3.nextInt(super.boundingBox.getZSize());
         if(var5 + 3 > super.boundingBox.getZSize()) {
            break;
         }

         var7 = StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY + var3.nextInt(var6) + 1, super.boundingBox.minZ + var5, 3, var4);
         if(var7 != null) {
            var8 = var7.getBoundingBox();
            this.roomsLinkedToTheRoom.add(new StructureBoundingBox(super.boundingBox.maxX - 1, var8.minY, var8.minZ, super.boundingBox.maxX, var8.maxY, var8.maxZ));
         }
      }

   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.isLiquidInStructureBoundingBox(var1, var3)) {
         return false;
      } else {
         this.fillWithBlocks(var1, var3, super.boundingBox.minX, super.boundingBox.minY, super.boundingBox.minZ, super.boundingBox.maxX, super.boundingBox.minY, super.boundingBox.maxZ, Blocks.dirt, Blocks.air, true);
         this.fillWithBlocks(var1, var3, super.boundingBox.minX, super.boundingBox.minY + 1, super.boundingBox.minZ, super.boundingBox.maxX, Math.min(super.boundingBox.minY + 3, super.boundingBox.maxY), super.boundingBox.maxZ, Blocks.air, Blocks.air, false);
         Iterator var4 = this.roomsLinkedToTheRoom.iterator();

         while(var4.hasNext()) {
            StructureBoundingBox var5 = (StructureBoundingBox)var4.next();
            this.fillWithBlocks(var1, var3, var5.minX, var5.maxY - 2, var5.minZ, var5.maxX, var5.maxY, var5.maxZ, Blocks.air, Blocks.air, false);
         }

         this.func_151547_a(var1, var3, super.boundingBox.minX, super.boundingBox.minY + 4, super.boundingBox.minZ, super.boundingBox.maxX, super.boundingBox.maxY, super.boundingBox.maxZ, Blocks.air, false);
         return true;
      }
   }

   protected void func_143012_a(NBTTagCompound var1) {
      NBTTagList var2 = new NBTTagList();
      Iterator var3 = this.roomsLinkedToTheRoom.iterator();

      while(var3.hasNext()) {
         StructureBoundingBox var4 = (StructureBoundingBox)var3.next();
         var2.appendTag(var4.func_151535_h());
      }

      var1.setTag("Entrances", var2);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      NBTTagList var2 = var1.getTagList("Entrances", 11);

      for(int var3 = 0; var3 < var2.tagCount(); ++var3) {
         this.roomsLinkedToTheRoom.add(new StructureBoundingBox(var2.func_150306_c(var3)));
      }

   }
}
