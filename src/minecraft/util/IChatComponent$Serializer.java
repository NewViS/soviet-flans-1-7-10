package net.minecraft.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map.Entry;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChatStyle$Serializer;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.util.IChatComponent;

public class IChatComponent$Serializer implements JsonDeserializer, JsonSerializer {

   private static final Gson field_150700_a;


   public IChatComponent deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      if(var1.isJsonPrimitive()) {
         return new ChatComponentText(var1.getAsString());
      } else if(!var1.isJsonObject()) {
         if(var1.isJsonArray()) {
            JsonArray var11 = var1.getAsJsonArray();
            IChatComponent var13 = null;
            Iterator var14 = var11.iterator();

            while(var14.hasNext()) {
               JsonElement var16 = (JsonElement)var14.next();
               IChatComponent var17 = this.deserialize(var16, var16.getClass(), var3);
               if(var13 == null) {
                  var13 = var17;
               } else {
                  var13.appendSibling(var17);
               }
            }

            return var13;
         } else {
            throw new JsonParseException("Don\'t know how to turn " + var1.toString() + " into a Component");
         }
      } else {
         JsonObject var4 = var1.getAsJsonObject();
         Object var5;
         if(var4.has("text")) {
            var5 = new ChatComponentText(var4.get("text").getAsString());
         } else {
            if(!var4.has("translate")) {
               throw new JsonParseException("Don\'t know how to turn " + var1.toString() + " into a Component");
            }

            String var6 = var4.get("translate").getAsString();
            if(var4.has("with")) {
               JsonArray var7 = var4.getAsJsonArray("with");
               Object[] var8 = new Object[var7.size()];

               for(int var9 = 0; var9 < var8.length; ++var9) {
                  var8[var9] = this.deserialize(var7.get(var9), var2, var3);
                  if(var8[var9] instanceof ChatComponentText) {
                     ChatComponentText var10 = (ChatComponentText)var8[var9];
                     if(var10.getChatStyle().isEmpty() && var10.getSiblings().isEmpty()) {
                        var8[var9] = var10.getChatComponentText_TextValue();
                     }
                  }
               }

               var5 = new ChatComponentTranslation(var6, var8);
            } else {
               var5 = new ChatComponentTranslation(var6, new Object[0]);
            }
         }

         if(var4.has("extra")) {
            JsonArray var12 = var4.getAsJsonArray("extra");
            if(var12.size() <= 0) {
               throw new JsonParseException("Unexpected empty array of components");
            }

            for(int var15 = 0; var15 < var12.size(); ++var15) {
               ((IChatComponent)var5).appendSibling(this.deserialize(var12.get(var15), var2, var3));
            }
         }

         ((IChatComponent)var5).setChatStyle((ChatStyle)var3.deserialize(var1, ChatStyle.class));
         return (IChatComponent)var5;
      }
   }

   private void func_150695_a(ChatStyle var1, JsonObject var2, JsonSerializationContext var3) {
      JsonElement var4 = var3.serialize(var1);
      if(var4.isJsonObject()) {
         JsonObject var5 = (JsonObject)var4;
         Iterator var6 = var5.entrySet().iterator();

         while(var6.hasNext()) {
            Entry var7 = (Entry)var6.next();
            var2.add((String)var7.getKey(), (JsonElement)var7.getValue());
         }
      }

   }

   public JsonElement serialize(IChatComponent var1, Type var2, JsonSerializationContext var3) {
      if(var1 instanceof ChatComponentText && var1.getChatStyle().isEmpty() && var1.getSiblings().isEmpty()) {
         return new JsonPrimitive(((ChatComponentText)var1).getChatComponentText_TextValue());
      } else {
         JsonObject var4 = new JsonObject();
         if(!var1.getChatStyle().isEmpty()) {
            this.func_150695_a(var1.getChatStyle(), var4, var3);
         }

         if(!var1.getSiblings().isEmpty()) {
            JsonArray var5 = new JsonArray();
            Iterator var6 = var1.getSiblings().iterator();

            while(var6.hasNext()) {
               IChatComponent var7 = (IChatComponent)var6.next();
               var5.add(this.serialize(var7, var7.getClass(), var3));
            }

            var4.add("extra", var5);
         }

         if(var1 instanceof ChatComponentText) {
            var4.addProperty("text", ((ChatComponentText)var1).getChatComponentText_TextValue());
         } else {
            if(!(var1 instanceof ChatComponentTranslation)) {
               throw new IllegalArgumentException("Don\'t know how to serialize " + var1 + " as a Component");
            }

            ChatComponentTranslation var11 = (ChatComponentTranslation)var1;
            var4.addProperty("translate", var11.getKey());
            if(var11.getFormatArgs() != null && var11.getFormatArgs().length > 0) {
               JsonArray var12 = new JsonArray();
               Object[] var13 = var11.getFormatArgs();
               int var8 = var13.length;

               for(int var9 = 0; var9 < var8; ++var9) {
                  Object var10 = var13[var9];
                  if(var10 instanceof IChatComponent) {
                     var12.add(this.serialize((IChatComponent)var10, var10.getClass(), var3));
                  } else {
                     var12.add(new JsonPrimitive(String.valueOf(var10)));
                  }
               }

               var4.add("with", var12);
            }
         }

         return var4;
      }
   }

   public static String func_150696_a(IChatComponent var0) {
      return field_150700_a.toJson(var0);
   }

   public static IChatComponent func_150699_a(String var0) {
      return (IChatComponent)field_150700_a.fromJson(var0, IChatComponent.class);
   }

   // $FF: synthetic method
   public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
      return this.serialize((IChatComponent)var1, var2, var3);
   }

   // $FF: synthetic method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      return this.deserialize(var1, var2, var3);
   }

   static {
      GsonBuilder var0 = new GsonBuilder();
      var0.registerTypeHierarchyAdapter(IChatComponent.class, new IChatComponent$Serializer());
      var0.registerTypeHierarchyAdapter(ChatStyle.class, new ChatStyle$Serializer());
      var0.registerTypeAdapterFactory(new EnumTypeAdapterFactory());
      field_150700_a = var0.create();
   }
}
