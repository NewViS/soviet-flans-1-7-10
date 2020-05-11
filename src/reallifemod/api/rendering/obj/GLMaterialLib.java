package de.ItsAMysterious.mods.reallifemod.api.rendering.obj;

import de.ItsAMysterious.mods.reallifemod.api.rendering.obj.GLMaterial;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GLMaterialLib {

   public String filepath = "";
   public String filename = "";
   GLMaterial[] materials;


   public GLMaterialLib(String mtlFilename) {
      if(mtlFilename != null && mtlFilename.length() > 0) {
         this.materials = this.loadMaterials(mtlFilename);
      }

   }

   public GLMaterial[] loadMaterials(String mtlFilename) {
      GLMaterial[] mtls = null;
      File f = new File(mtlFilename);
      String[] pathParts = f.list();
      this.filepath = pathParts[0];
      this.filename = pathParts[1];

      try {
         BufferedReader e = new BufferedReader(new FileReader(new File(f, mtlFilename)));
         mtls = this.loadMaterials(e);
         e.close();
      } catch (Exception var6) {
         System.out.println("GLMaterialLib.loadMaterials(): Exception when loading " + mtlFilename + ": " + var6);
      }

      return mtls;
   }

   public GLMaterial[] loadMaterials(BufferedReader br) {
      ArrayList mtlslist = new ArrayList();
      GLMaterial material = null;
      String line = "";

      try {
         while((line = br.readLine()) != null) {
            line = line.trim();
            if(line.length() > 0 && !line.startsWith("#")) {
               if(line.startsWith("newmtl")) {
                  material = new GLMaterial();
                  material.setName(line.substring(7));
                  mtlslist.add(material);
               } else {
                  float[] mtls;
                  if(line.startsWith("Kd")) {
                     if((mtls = this.read3Floats(line)) != null) {
                        material.setDiffuse(mtls);
                     }
                  } else if(line.startsWith("Ka")) {
                     if((mtls = this.read3Floats(line)) != null) {
                        material.setAmbient(mtls);
                     }
                  } else if(line.startsWith("Ks")) {
                     if((mtls = this.read3Floats(line)) != null) {
                        material.setSpecular(mtls);
                     }
                  } else if(line.startsWith("Ns")) {
                     if((mtls = this.read3Floats(line)) != null) {
                        int textureFile = (int)(mtls[0] / 1000.0F * 127.0F);
                        material.setShininess((float)textureFile);
                     }
                  } else if(line.startsWith("d")) {
                     if((mtls = this.read3Floats(line)) != null) {
                        material.setAlpha(mtls[0]);
                     }
                  } else if(line.startsWith("illum")) {
                     if(this.read3Floats(line) == null) {
                        ;
                     }
                  } else if(line.startsWith("map_Kd")) {
                     String textureFile1 = line.substring(7);
                     if(textureFile1 != null && !textureFile1.equals("")) {
                        byte textureHandle = 0;
                        material.setTextureFile(textureFile1);
                        material.setTexture(textureHandle);
                     }
                  }
               }
            }
         }
      } catch (Exception var8) {
         System.out.println("GLMaterialLib.loadMaterials() failed at line: " + line);
      }

      System.out.println("GLMaterialLib.loadMaterials(): loaded " + mtlslist.size() + " materials ");
      GLMaterial[] mtls1 = new GLMaterial[mtlslist.size()];
      mtlslist.toArray(mtls1);
      return mtls1;
   }

   private float[] read3Floats(String line) {
      StringTokenizer st = new StringTokenizer(line, " ");
      st.nextToken();
      if(st.countTokens() == 1) {
         return new float[]{Float.parseFloat(st.nextToken()), 0.0F, 0.0F, 0.0F};
      } else {
         try {
            if(st.countTokens() == 3) {
               return new float[]{Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()), 1.0F};
            }
         } catch (Exception var4) {
            System.out.println("GLMaterialLib.read3Floats(): error on line \'" + line + "\', " + var4);
         }

         return null;
      }
   }

   public void writeLibe(GLMaterial[] mtls, String filename) {
      try {
         PrintWriter e = new PrintWriter(new FileWriter(filename));
         this.writeLibe(mtls, e);
         e.close();
      } catch (IOException var4) {
         System.out.println("GLMaterialLib.writeLibe(): IOException:" + var4);
      }

   }

   public void writeLibe(GLMaterial[] mtls, PrintWriter out) {
      if(out != null) {
         out.println("#");
         out.println("# Wavefront material file for use with OBJ file");
         out.println("# Created by GLMaterialLib.java");
         out.println("#");
         out.println("");

         for(int i = 0; i < mtls.length; ++i) {
            this.write(out, mtls[i]);
         }
      }

   }

   public void write(PrintWriter out, GLMaterial mtl) {
      if(out != null) {
         out.println("newmtl " + mtl.mtlname);
         out.println("Ka " + mtl.ambient.get(0) + " " + mtl.ambient.get(1) + " " + mtl.ambient.get(2));
         out.println("Kd " + mtl.diffuse.get(0) + " " + mtl.diffuse.get(1) + " " + mtl.diffuse.get(2));
         out.println("Ks " + mtl.specular.get(0) + " " + mtl.specular.get(1) + " " + mtl.specular.get(2));
         out.println("Ns " + (double)mtl.shininess.get(0) / 128.0D * 1000.0D);
         if(mtl.textureFile != null && !mtl.textureFile.equals("")) {
            out.println("map_Kd " + mtl.textureFile);
         }

         if(mtl.getAlpha() != 1.0F) {
            out.println("d " + mtl.getAlpha());
         }

         out.println("");
      }

   }

   public GLMaterial getClone(GLMaterial mtl) {
      GLMaterial clone = new GLMaterial();
      clone.setDiffuse(new float[]{mtl.diffuse.get(0), mtl.diffuse.get(1), mtl.diffuse.get(2), mtl.diffuse.get(3)});
      clone.setAmbient(new float[]{mtl.ambient.get(0), mtl.ambient.get(1), mtl.ambient.get(2), mtl.ambient.get(3)});
      clone.setSpecular(new float[]{mtl.specular.get(0), mtl.specular.get(1), mtl.specular.get(2), mtl.specular.get(3)});
      clone.setGlowColor(new float[]{mtl.emission.get(0), mtl.emission.get(1), mtl.emission.get(2), mtl.emission.get(3)});
      clone.setShininess(mtl.shininess.get(0));
      clone.textureFile = mtl.textureFile;
      clone.textureHandle = mtl.textureHandle;
      clone.setName(mtl.mtlname + "-copy");
      return clone;
   }

   public GLMaterial find(String materialName) {
      int mtl_idx = this.findID(materialName);
      return mtl_idx >= 0?this.materials[mtl_idx]:null;
   }

   public int findID(String materialName) {
      if(this.materials != null && materialName != null) {
         for(int m = 0; m < this.materials.length; ++m) {
            if(this.materials[m].mtlname.equals(materialName)) {
               return m;
            }
         }
      }

      return -1;
   }
}
