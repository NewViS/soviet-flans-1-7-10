package com.hea3ven.colladamodel.client.model.collada;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;

public class Face {

   private Vec3[] vertex = null;
   private Vec3[] vertexNormals = null;
   private Vec3[] vertexTexCoord = null;


   public void render(Tessellator tessellator) {
      tessellator.startDrawing(9);
      Vec3 faceNormal = this.calculateFaceNormal();
      tessellator.setNormal((float)(-faceNormal.xCoord), (float)(-faceNormal.yCoord), (float)(-faceNormal.zCoord));
      float averageU = 0.0F;
      float averageV = 0.0F;

      for(int offsetU = 0; offsetU < this.vertexTexCoord.length; ++offsetU) {
         averageU = (float)((double)averageU + this.vertexTexCoord[offsetU].xCoord);
         averageV = (float)((double)averageV + this.vertexTexCoord[offsetU].yCoord);
      }

      averageU /= (float)this.vertexTexCoord.length;
      averageV /= (float)this.vertexTexCoord.length;

      for(int i = 0; i < this.vertex.length; ++i) {
         float var8 = 5.0E-4F;
         float offsetV = 5.0E-4F;
         if(this.vertexTexCoord[i].xCoord > (double)averageU) {
            var8 = -var8;
         }

         if(this.vertexTexCoord[i].yCoord > (double)averageV) {
            offsetV = -offsetV;
         }

         tessellator.addVertexWithUV(this.vertex[i].xCoord, this.vertex[i].yCoord, this.vertex[i].zCoord, this.vertexTexCoord[i].xCoord + (double)var8, 1.0D - this.vertexTexCoord[i].yCoord - (double)offsetV);
      }

      tessellator.draw();
   }

   private Vec3 calculateFaceNormal() {
      double sumX = 0.0D;
      double sumY = 0.0D;
      double sumZ = 0.0D;

      for(int i = 0; i < this.vertexNormals.length; ++i) {
         sumX += this.vertexNormals[i].xCoord;
         sumY += this.vertexNormals[i].yCoord;
         sumZ += this.vertexNormals[i].zCoord;
      }

      return Vec3.createVectorHelper(sumX / (double)this.vertexNormals.length, sumY / (double)this.vertexNormals.length, sumZ / (double)this.vertexNormals.length);
   }

   public void setVertex(Vec3[] vertex, Vec3[] normal, Vec3[] texCoords) {
      this.vertex = vertex;
      this.vertexNormals = normal;
      this.vertexTexCoord = texCoords;
   }
}
