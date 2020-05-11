package de.ItsAMysterious.mods.reallifemod.api.rendering.obj;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.api.rendering.obj.Face;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.renderer.Tessellator;

public class GroupObject {

   public String name;
   public ArrayList faces;
   public int glDrawingMode;


   public GroupObject() {
      this("");
   }

   public GroupObject(String name) {
      this(name, -1);
   }

   public GroupObject(String name, int glDrawingMode) {
      this.faces = new ArrayList();
      this.name = name;
      this.glDrawingMode = glDrawingMode;
   }

   @SideOnly(Side.CLIENT)
   public void render() {
      if(this.faces.size() > 0) {
         Tessellator tessellator = Tessellator.instance;
         tessellator.startDrawing(this.glDrawingMode);
         this.render(tessellator);
         tessellator.draw();
      }

   }

   public void render(Tessellator tessellator) {
      if(this.faces.size() > 0) {
         Iterator var2 = this.faces.iterator();

         while(var2.hasNext()) {
            Face face = (Face)var2.next();
            face.addFaceForRendering(tessellator);
         }
      }

   }
}
