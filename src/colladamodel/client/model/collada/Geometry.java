package com.hea3ven.colladamodel.client.model.collada;

import com.hea3ven.colladamodel.client.model.collada.Face;
import com.hea3ven.colladamodel.client.model.collada.Transform;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class Geometry {

   private String name = null;
   private List transforms = new LinkedList();
   private List faces = new LinkedList();
   private HashMap transformsById = new HashMap();


   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void addTransform(String id, Transform transform) {
      this.transforms.add(transform);
      this.transformsById.put(id, transform);
   }

   public Transform getTransform(String transId) {
      return (Transform)this.transformsById.get(transId);
   }

   public void addFace(Face face) {
      this.faces.add(face);
   }

   public void render(Tessellator tessellator) {
      GL11.glPushMatrix();
      Iterator i$ = this.transforms.iterator();

      while(i$.hasNext()) {
         Transform face = (Transform)i$.next();
         face.apply();
      }

      i$ = this.faces.iterator();

      while(i$.hasNext()) {
         Face face1 = (Face)i$.next();
         face1.render(tessellator);
      }

      GL11.glPopMatrix();
   }

   public void renderAnimation(Tessellator tessellator, double frame) {
      GL11.glPushMatrix();
      Iterator i$ = this.transforms.iterator();

      while(i$.hasNext()) {
         Transform face = (Transform)i$.next();
         face.applyAnimation(frame);
      }

      i$ = this.faces.iterator();

      while(i$.hasNext()) {
         Face face1 = (Face)i$.next();
         face1.render(tessellator);
      }

      GL11.glPopMatrix();
   }

   public double getAnimationLength() {
      double animationLength = 0.0D;
      Iterator i$ = this.transforms.iterator();

      while(i$.hasNext()) {
         Transform trans = (Transform)i$.next();
         if(trans.getAnimationLength() > animationLength) {
            animationLength = trans.getAnimationLength();
         }
      }

      return animationLength;
   }
}
