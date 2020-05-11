package net.minecraft.client.renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.lwjgl.opengl.GL11;

public class GLAllocation {

   private static final Map mapDisplayLists = new HashMap();
   private static final List listDummy = new ArrayList();


   public static synchronized int generateDisplayLists(int var0) {
      int var1 = GL11.glGenLists(var0);
      mapDisplayLists.put(Integer.valueOf(var1), Integer.valueOf(var0));
      return var1;
   }

   public static synchronized void deleteDisplayLists(int var0) {
      GL11.glDeleteLists(var0, ((Integer)mapDisplayLists.remove(Integer.valueOf(var0))).intValue());
   }

   public static synchronized void deleteTexturesAndDisplayLists() {
      Iterator var0 = mapDisplayLists.entrySet().iterator();

      while(var0.hasNext()) {
         Entry var1 = (Entry)var0.next();
         GL11.glDeleteLists(((Integer)var1.getKey()).intValue(), ((Integer)var1.getValue()).intValue());
      }

      mapDisplayLists.clear();
   }

   public static synchronized ByteBuffer createDirectByteBuffer(int var0) {
      return ByteBuffer.allocateDirect(var0).order(ByteOrder.nativeOrder());
   }

   public static IntBuffer createDirectIntBuffer(int var0) {
      return createDirectByteBuffer(var0 << 2).asIntBuffer();
   }

   public static FloatBuffer createDirectFloatBuffer(int var0) {
      return createDirectByteBuffer(var0 << 2).asFloatBuffer();
   }

}
