package de.ItsAMysterious.mods.reallifemod.api.rendering.obj;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.api.rendering.obj.Face;
import de.ItsAMysterious.mods.reallifemod.api.rendering.obj.GroupObject;
import de.ItsAMysterious.mods.reallifemod.api.rendering.obj.TextureCoordinate;
import de.ItsAMysterious.mods.reallifemod.api.rendering.obj.Vertex;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelFormatException;

public class WavefrontObject {

   private static Pattern vertexPattern = Pattern.compile("(v( (\\-){0,1}\\d+\\.\\d+){3,4} *\\n)|(v( (\\-){0,1}\\d+\\.\\d+){3,4} *$)");
   private static Pattern vertexNormalPattern = Pattern.compile("(vn( (\\-){0,1}\\d+\\.\\d+){3,4} *\\n)|(vn( (\\-){0,1}\\d+\\.\\d+){3,4} *$)");
   private static Pattern textureCoordinatePattern = Pattern.compile("(vt( (\\-){0,1}\\d+\\.\\d+){2,3} *\\n)|(vt( (\\-){0,1}\\d+\\.\\d+){2,3} *$)");
   private static Pattern face_V_VT_VN_Pattern = Pattern.compile("(f( \\d+/\\d+/\\d+){3,4} *\\n)|(f( \\d+/\\d+/\\d+){3,4} *$)");
   private static Pattern face_V_VT_Pattern = Pattern.compile("(f( \\d+/\\d+){3,4} *\\n)|(f( \\d+/\\d+){3,4} *$)");
   private static Pattern face_V_VN_Pattern = Pattern.compile("(f( \\d+//\\d+){3,4} *\\n)|(f( \\d+//\\d+){3,4} *$)");
   private static Pattern face_V_Pattern = Pattern.compile("(f( \\d+){3,4} *\\n)|(f( \\d+){3,4} *$)");
   private static Pattern groupObjectPattern = Pattern.compile("([go]( [\\w\\d\\.]+) *\\n)|([go]( [\\w\\d\\.]+) *$)");
   private static Matcher vertexMatcher;
   private static Matcher vertexNormalMatcher;
   private static Matcher textureCoordinateMatcher;
   private static Matcher face_V_VT_VN_Matcher;
   private static Matcher face_V_VT_Matcher;
   private static Matcher face_V_VN_Matcher;
   private static Matcher face_V_Matcher;
   private static Matcher groupObjectMatcher;
   public ArrayList vertices = new ArrayList();
   public ArrayList vertexNormals = new ArrayList();
   public ArrayList textureCoordinates = new ArrayList();
   public ArrayList groupObjects = new ArrayList();
   private GroupObject currentGroupObject;
   private String fileName;


   public WavefrontObject(ResourceLocation resource) throws ModelFormatException {
      this.fileName = resource.toString();

      try {
         IResource e = Minecraft.getMinecraft().getResourceManager().getResource(resource);
         this.loadObjModel(e.getInputStream());
      } catch (IOException var3) {
         throw new ModelFormatException("IO Exception reading model format", var3);
      }
   }

   public WavefrontObject(String filename, InputStream inputStream) throws ModelFormatException {
      this.fileName = filename;
      this.loadObjModel(inputStream);
   }

   private void loadObjModel(InputStream inputStream) throws ModelFormatException {
      BufferedReader reader = null;
      String currentLine = null;
      int lineCount = 0;

      try {
         reader = new BufferedReader(new InputStreamReader(inputStream));

         while((currentLine = reader.readLine()) != null) {
            ++lineCount;
            currentLine = currentLine.replaceAll("\\s+", " ").trim();
            if(!currentLine.startsWith("#") && currentLine.length() != 0) {
               Vertex e;
               if(currentLine.startsWith("v ")) {
                  e = this.parseVertex(currentLine, lineCount);
                  if(e != null) {
                     this.vertices.add(e);
                  }
               } else if(currentLine.startsWith("vn ")) {
                  e = this.parseVertexNormal(currentLine, lineCount);
                  if(e != null) {
                     this.vertexNormals.add(e);
                  }
               } else if(currentLine.startsWith("vt ")) {
                  TextureCoordinate var18 = this.parseTextureCoordinate(currentLine, lineCount);
                  if(var18 != null) {
                     this.textureCoordinates.add(var18);
                  }
               } else if(currentLine.startsWith("f ")) {
                  if(this.currentGroupObject == null) {
                     this.currentGroupObject = new GroupObject("Default");
                  }

                  Face var19 = this.parseFace(currentLine, lineCount);
                  if(var19 != null) {
                     this.currentGroupObject.faces.add(var19);
                  }
               } else if(currentLine.startsWith("g ") | currentLine.startsWith("o ")) {
                  GroupObject var20 = this.parseGroupObject(currentLine, lineCount);
                  if(var20 != null && this.currentGroupObject != null) {
                     this.groupObjects.add(this.currentGroupObject);
                  }

                  this.currentGroupObject = var20;
               }
            }
         }

         this.groupObjects.add(this.currentGroupObject);
      } catch (IOException var16) {
         throw new ModelFormatException("IO Exception reading model format", var16);
      } finally {
         try {
            reader.close();
         } catch (IOException var15) {
            ;
         }

         try {
            inputStream.close();
         } catch (IOException var14) {
            ;
         }

      }
   }

   @SideOnly(Side.CLIENT)
   public void renderAll() {
      Tessellator tessellator = Tessellator.instance;
      if(this.currentGroupObject != null) {
         tessellator.startDrawing(this.currentGroupObject.glDrawingMode);
      } else {
         tessellator.startDrawing(4);
      }

      this.tessellateAll(tessellator);
      tessellator.draw();
   }

   @SideOnly(Side.CLIENT)
   public void renderAllLines() {
      Tessellator tessellator = Tessellator.instance;
      if(this.currentGroupObject != null) {
         tessellator.startDrawing(this.currentGroupObject.glDrawingMode);
      } else {
         tessellator.startDrawing(4);
      }

      this.tessellateAll(tessellator);
      tessellator.draw();
   }

   @SideOnly(Side.CLIENT)
   public void tessellateAll(Tessellator tessellator) {
      Iterator var2 = this.groupObjects.iterator();

      while(var2.hasNext()) {
         GroupObject groupObject = (GroupObject)var2.next();
         groupObject.render(tessellator);
      }

   }

   @SideOnly(Side.CLIENT)
   public void renderOnly(String ... groupNames) {
      Iterator var2 = this.groupObjects.iterator();

      while(var2.hasNext()) {
         GroupObject groupObject = (GroupObject)var2.next();
         String[] var4 = groupNames;
         int var5 = groupNames.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            String groupName = var4[var6];
            if(groupName.equalsIgnoreCase(groupObject.name)) {
               groupObject.render();
            }
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void tessellateOnly(Tessellator tessellator, String ... groupNames) {
      Iterator var3 = this.groupObjects.iterator();

      while(var3.hasNext()) {
         GroupObject groupObject = (GroupObject)var3.next();
         String[] var5 = groupNames;
         int var6 = groupNames.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            String groupName = var5[var7];
            if(groupName.equalsIgnoreCase(groupObject.name)) {
               groupObject.render(tessellator);
            }
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void renderPart(String partName) {
      Iterator var2 = this.groupObjects.iterator();

      while(var2.hasNext()) {
         GroupObject groupObject = (GroupObject)var2.next();
         if(partName.equalsIgnoreCase(groupObject.name)) {
            groupObject.render();
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void tessellatePart(Tessellator tessellator, String partName) {
      Iterator var3 = this.groupObjects.iterator();

      while(var3.hasNext()) {
         GroupObject groupObject = (GroupObject)var3.next();
         if(partName.equalsIgnoreCase(groupObject.name)) {
            groupObject.render(tessellator);
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void renderAllExcept(String ... excludedGroupNames) {
      Iterator var2 = this.groupObjects.iterator();

      while(var2.hasNext()) {
         GroupObject groupObject = (GroupObject)var2.next();
         boolean skipPart = false;
         String[] var5 = excludedGroupNames;
         int var6 = excludedGroupNames.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            String excludedGroupName = var5[var7];
            if(excludedGroupName.equalsIgnoreCase(groupObject.name)) {
               skipPart = true;
            }
         }

         if(!skipPart) {
            groupObject.render();
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void tessellateAllExcept(Tessellator tessellator, String ... excludedGroupNames) {
      Iterator var4 = this.groupObjects.iterator();

      while(var4.hasNext()) {
         GroupObject groupObject = (GroupObject)var4.next();
         boolean exclude = false;
         String[] var6 = excludedGroupNames;
         int var7 = excludedGroupNames.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            String excludedGroupName = var6[var8];
            if(excludedGroupName.equalsIgnoreCase(groupObject.name)) {
               exclude = true;
            }
         }

         if(!exclude) {
            groupObject.render(tessellator);
         }
      }

   }

   private Vertex parseVertex(String line, int lineCount) throws ModelFormatException {
      Object vertex = null;
      if(isValidVertexLine(line)) {
         line = line.substring(line.indexOf(" ") + 1);
         String[] tokens = line.split(" ");

         try {
            return (Vertex)(tokens.length == 2?new Vertex(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1])):(tokens.length == 3?new Vertex(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2])):vertex));
         } catch (NumberFormatException var6) {
            throw new ModelFormatException(String.format("Number formatting error at line %d", new Object[]{Integer.valueOf(lineCount)}), var6);
         }
      } else {
         throw new ModelFormatException("Error parsing entry (\'" + line + "\'" + ", line " + lineCount + ") in file \'" + this.fileName + "\' - Incorrect format");
      }
   }

   private Vertex parseVertexNormal(String line, int lineCount) throws ModelFormatException {
      Object vertexNormal = null;
      if(isValidVertexNormalLine(line)) {
         line = line.substring(line.indexOf(" ") + 1);
         String[] tokens = line.split(" ");

         try {
            return (Vertex)(tokens.length == 3?new Vertex(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2])):vertexNormal);
         } catch (NumberFormatException var6) {
            throw new ModelFormatException(String.format("Number formatting error at line %d", new Object[]{Integer.valueOf(lineCount)}), var6);
         }
      } else {
         throw new ModelFormatException("Error parsing entry (\'" + line + "\'" + ", line " + lineCount + ") in file \'" + this.fileName + "\' - Incorrect format");
      }
   }

   private TextureCoordinate parseTextureCoordinate(String line, int lineCount) throws ModelFormatException {
      Object textureCoordinate = null;
      if(isValidTextureCoordinateLine(line)) {
         line = line.substring(line.indexOf(" ") + 1);
         String[] tokens = line.split(" ");

         try {
            return (TextureCoordinate)(tokens.length == 2?new TextureCoordinate(Float.parseFloat(tokens[0]), 1.0F - Float.parseFloat(tokens[1])):(tokens.length == 3?new TextureCoordinate(Float.parseFloat(tokens[0]), 1.0F - Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2])):textureCoordinate));
         } catch (NumberFormatException var6) {
            throw new ModelFormatException(String.format("Number formatting error at line %d", new Object[]{Integer.valueOf(lineCount)}), var6);
         }
      } else {
         throw new ModelFormatException("Error parsing entry (\'" + line + "\'" + ", line " + lineCount + ") in file \'" + this.fileName + "\' - Incorrect format");
      }
   }

   private Face parseFace(String line, int lineCount) throws ModelFormatException {
      Face face = null;
      if(isValidFaceLine(line)) {
         face = new Face();
         String trimmedLine = line.substring(line.indexOf(" ") + 1);
         String[] tokens = trimmedLine.split(" ");
         String[] subTokens = null;
         if(tokens.length == 3) {
            if(this.currentGroupObject.glDrawingMode == -1) {
               this.currentGroupObject.glDrawingMode = 4;
            } else if(this.currentGroupObject.glDrawingMode != 4) {
               throw new ModelFormatException("Error parsing entry (\'" + line + "\'" + ", line " + lineCount + ") in file \'" + this.fileName + "\' - Invalid number of points for face (expected 4, found " + tokens.length + ")");
            }
         } else if(tokens.length == 4) {
            if(this.currentGroupObject.glDrawingMode == -1) {
               this.currentGroupObject.glDrawingMode = 7;
            } else if(this.currentGroupObject.glDrawingMode != 7) {
               throw new ModelFormatException("Error parsing entry (\'" + line + "\'" + ", line " + lineCount + ") in file \'" + this.fileName + "\' - Invalid number of points for face (expected 3, found " + tokens.length + ")");
            }
         }

         int i;
         if(isValidFace_V_VT_VN_Line(line)) {
            face.vertices = new Vertex[tokens.length];
            face.textureCoordinates = new TextureCoordinate[tokens.length];
            face.vertexNormals = new Vertex[tokens.length];

            for(i = 0; i < tokens.length; ++i) {
               subTokens = tokens[i].split("/");
               face.vertices[i] = (Vertex)this.vertices.get(Integer.parseInt(subTokens[0]) - 1);
               face.textureCoordinates[i] = (TextureCoordinate)this.textureCoordinates.get(Integer.parseInt(subTokens[1]) - 1);
               face.vertexNormals[i] = (Vertex)this.vertexNormals.get(Integer.parseInt(subTokens[2]) - 1);
            }

            face.faceNormal = face.calculateNormal();
         } else if(isValidFace_V_VT_Line(line)) {
            face.vertices = new Vertex[tokens.length];
            face.textureCoordinates = new TextureCoordinate[tokens.length];

            for(i = 0; i < tokens.length; ++i) {
               subTokens = tokens[i].split("/");
               face.vertices[i] = (Vertex)this.vertices.get(Integer.parseInt(subTokens[0]) - 1);
               face.textureCoordinates[i] = (TextureCoordinate)this.textureCoordinates.get(Integer.parseInt(subTokens[1]) - 1);
            }

            face.faceNormal = face.calculateNormal();
         } else if(isValidFace_V_VN_Line(line)) {
            face.vertices = new Vertex[tokens.length];
            face.vertexNormals = new Vertex[tokens.length];

            for(i = 0; i < tokens.length; ++i) {
               subTokens = tokens[i].split("//");
               face.vertices[i] = (Vertex)this.vertices.get(Integer.parseInt(subTokens[0]) - 1);
               face.vertexNormals[i] = (Vertex)this.vertexNormals.get(Integer.parseInt(subTokens[1]) - 1);
            }

            face.faceNormal = face.calculateNormal();
         } else {
            if(!isValidFace_V_Line(line)) {
               throw new ModelFormatException("Error parsing entry (\'" + line + "\'" + ", line " + lineCount + ") in file \'" + this.fileName + "\' - Incorrect format");
            }

            face.vertices = new Vertex[tokens.length];

            for(i = 0; i < tokens.length; ++i) {
               face.vertices[i] = (Vertex)this.vertices.get(Integer.parseInt(tokens[i]) - 1);
            }

            face.faceNormal = face.calculateNormal();
         }

         return face;
      } else {
         throw new ModelFormatException("Error parsing entry (\'" + line + "\'" + ", line " + lineCount + ") in file \'" + this.fileName + "\' - Incorrect format");
      }
   }

   private GroupObject parseGroupObject(String line, int lineCount) throws ModelFormatException {
      GroupObject group = null;
      if(isValidGroupObjectLine(line)) {
         String trimmedLine = line.substring(line.indexOf(" ") + 1);
         if(trimmedLine.length() > 0) {
            group = new GroupObject(trimmedLine);
         }

         return group;
      } else {
         throw new ModelFormatException("Error parsing entry (\'" + line + "\'" + ", line " + lineCount + ") in file \'" + this.fileName + "\' - Incorrect format");
      }
   }

   private static boolean isValidVertexLine(String line) {
      if(vertexMatcher != null) {
         vertexMatcher.reset();
      }

      vertexMatcher = vertexPattern.matcher(line);
      return vertexMatcher.matches();
   }

   private static boolean isValidVertexNormalLine(String line) {
      if(vertexNormalMatcher != null) {
         vertexNormalMatcher.reset();
      }

      vertexNormalMatcher = vertexNormalPattern.matcher(line);
      return vertexNormalMatcher.matches();
   }

   private static boolean isValidTextureCoordinateLine(String line) {
      if(textureCoordinateMatcher != null) {
         textureCoordinateMatcher.reset();
      }

      textureCoordinateMatcher = textureCoordinatePattern.matcher(line);
      return textureCoordinateMatcher.matches();
   }

   private static boolean isValidFace_V_VT_VN_Line(String line) {
      if(face_V_VT_VN_Matcher != null) {
         face_V_VT_VN_Matcher.reset();
      }

      face_V_VT_VN_Matcher = face_V_VT_VN_Pattern.matcher(line);
      return face_V_VT_VN_Matcher.matches();
   }

   private static boolean isValidFace_V_VT_Line(String line) {
      if(face_V_VT_Matcher != null) {
         face_V_VT_Matcher.reset();
      }

      face_V_VT_Matcher = face_V_VT_Pattern.matcher(line);
      return face_V_VT_Matcher.matches();
   }

   private static boolean isValidFace_V_VN_Line(String line) {
      if(face_V_VN_Matcher != null) {
         face_V_VN_Matcher.reset();
      }

      face_V_VN_Matcher = face_V_VN_Pattern.matcher(line);
      return face_V_VN_Matcher.matches();
   }

   private static boolean isValidFace_V_Line(String line) {
      if(face_V_Matcher != null) {
         face_V_Matcher.reset();
      }

      face_V_Matcher = face_V_Pattern.matcher(line);
      return face_V_Matcher.matches();
   }

   private static boolean isValidFaceLine(String line) {
      return isValidFace_V_VT_VN_Line(line) || isValidFace_V_VT_Line(line) || isValidFace_V_VN_Line(line) || isValidFace_V_Line(line);
   }

   private static boolean isValidGroupObjectLine(String line) {
      if(groupObjectMatcher != null) {
         groupObjectMatcher.reset();
      }

      groupObjectMatcher = groupObjectPattern.matcher(line);
      return groupObjectMatcher.matches();
   }

   public String getType() {
      return "obj";
   }

}
