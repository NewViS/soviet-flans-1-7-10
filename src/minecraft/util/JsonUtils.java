package net.minecraft.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;

public class JsonUtils {

   public static boolean jsonObjectFieldTypeIsString(JsonObject var0, String var1) {
      return !jsonObjectFieldTypeIsPrimitive(var0, var1)?false:var0.getAsJsonPrimitive(var1).isString();
   }

   public static boolean jsonElementTypeIsString(JsonElement var0) {
      return !var0.isJsonPrimitive()?false:var0.getAsJsonPrimitive().isString();
   }

   public static boolean jsonObjectFieldTypeIsArray(JsonObject var0, String var1) {
      return !jsonObjectHasNamedField(var0, var1)?false:var0.get(var1).isJsonArray();
   }

   public static boolean jsonObjectFieldTypeIsPrimitive(JsonObject var0, String var1) {
      return !jsonObjectHasNamedField(var0, var1)?false:var0.get(var1).isJsonPrimitive();
   }

   public static boolean jsonObjectHasNamedField(JsonObject var0, String var1) {
      return var0 == null?false:var0.get(var1) != null;
   }

   public static String getJsonElementStringValue(JsonElement var0, String var1) {
      if(var0.isJsonPrimitive()) {
         return var0.getAsString();
      } else {
         throw new JsonSyntaxException("Expected " + var1 + " to be a string, was " + getJsonElementTypeDescription(var0));
      }
   }

   public static String getJsonObjectStringFieldValue(JsonObject var0, String var1) {
      if(var0.has(var1)) {
         return getJsonElementStringValue(var0.get(var1), var1);
      } else {
         throw new JsonSyntaxException("Missing " + var1 + ", expected to find a string");
      }
   }

   public static String getJsonObjectStringFieldValueOrDefault(JsonObject var0, String var1, String var2) {
      return var0.has(var1)?getJsonElementStringValue(var0.get(var1), var1):var2;
   }

   public static boolean getJsonElementBooleanValue(JsonElement var0, String var1) {
      if(var0.isJsonPrimitive()) {
         return var0.getAsBoolean();
      } else {
         throw new JsonSyntaxException("Expected " + var1 + " to be a Boolean, was " + getJsonElementTypeDescription(var0));
      }
   }

   public static boolean getJsonObjectBooleanFieldValue(JsonObject var0, String var1) {
      if(var0.has(var1)) {
         return getJsonElementBooleanValue(var0.get(var1), var1);
      } else {
         throw new JsonSyntaxException("Missing " + var1 + ", expected to find a Boolean");
      }
   }

   public static boolean getJsonObjectBooleanFieldValueOrDefault(JsonObject var0, String var1, boolean var2) {
      return var0.has(var1)?getJsonElementBooleanValue(var0.get(var1), var1):var2;
   }

   public static float getJsonElementFloatValue(JsonElement var0, String var1) {
      if(var0.isJsonPrimitive() && var0.getAsJsonPrimitive().isNumber()) {
         return var0.getAsFloat();
      } else {
         throw new JsonSyntaxException("Expected " + var1 + " to be a Float, was " + getJsonElementTypeDescription(var0));
      }
   }

   public static float getJsonObjectFloatFieldValue(JsonObject var0, String var1) {
      if(var0.has(var1)) {
         return getJsonElementFloatValue(var0.get(var1), var1);
      } else {
         throw new JsonSyntaxException("Missing " + var1 + ", expected to find a Float");
      }
   }

   public static float getJsonObjectFloatFieldValueOrDefault(JsonObject var0, String var1, float var2) {
      return var0.has(var1)?getJsonElementFloatValue(var0.get(var1), var1):var2;
   }

   public static int getJsonElementIntegerValue(JsonElement var0, String var1) {
      if(var0.isJsonPrimitive() && var0.getAsJsonPrimitive().isNumber()) {
         return var0.getAsInt();
      } else {
         throw new JsonSyntaxException("Expected " + var1 + " to be a Int, was " + getJsonElementTypeDescription(var0));
      }
   }

   public static int getJsonObjectIntegerFieldValue(JsonObject var0, String var1) {
      if(var0.has(var1)) {
         return getJsonElementIntegerValue(var0.get(var1), var1);
      } else {
         throw new JsonSyntaxException("Missing " + var1 + ", expected to find a Int");
      }
   }

   public static int getJsonObjectIntegerFieldValueOrDefault(JsonObject var0, String var1, int var2) {
      return var0.has(var1)?getJsonElementIntegerValue(var0.get(var1), var1):var2;
   }

   public static JsonObject getJsonElementAsJsonObject(JsonElement var0, String var1) {
      if(var0.isJsonObject()) {
         return var0.getAsJsonObject();
      } else {
         throw new JsonSyntaxException("Expected " + var1 + " to be a JsonObject, was " + getJsonElementTypeDescription(var0));
      }
   }

   public static JsonObject func_152754_s(JsonObject var0, String var1) {
      if(var0.has(var1)) {
         return getJsonElementAsJsonObject(var0.get(var1), var1);
      } else {
         throw new JsonSyntaxException("Missing " + var1 + ", expected to find a JsonObject");
      }
   }

   public static JsonObject getJsonObjectFieldOrDefault(JsonObject var0, String var1, JsonObject var2) {
      return var0.has(var1)?getJsonElementAsJsonObject(var0.get(var1), var1):var2;
   }

   public static JsonArray getJsonElementAsJsonArray(JsonElement var0, String var1) {
      if(var0.isJsonArray()) {
         return var0.getAsJsonArray();
      } else {
         throw new JsonSyntaxException("Expected " + var1 + " to be a JsonArray, was " + getJsonElementTypeDescription(var0));
      }
   }

   public static JsonArray getJsonObjectJsonArrayField(JsonObject var0, String var1) {
      if(var0.has(var1)) {
         return getJsonElementAsJsonArray(var0.get(var1), var1);
      } else {
         throw new JsonSyntaxException("Missing " + var1 + ", expected to find a JsonArray");
      }
   }

   public static JsonArray getJsonObjectJsonArrayFieldOrDefault(JsonObject var0, String var1, JsonArray var2) {
      return var0.has(var1)?getJsonElementAsJsonArray(var0.get(var1), var1):var2;
   }

   public static String getJsonElementTypeDescription(JsonElement var0) {
      String var1 = org.apache.commons.lang3.StringUtils.abbreviateMiddle(String.valueOf(var0), "...", 10);
      if(var0 == null) {
         return "null (missing)";
      } else if(var0.isJsonNull()) {
         return "null (json)";
      } else if(var0.isJsonArray()) {
         return "an array (" + var1 + ")";
      } else if(var0.isJsonObject()) {
         return "an object (" + var1 + ")";
      } else {
         if(var0.isJsonPrimitive()) {
            JsonPrimitive var2 = var0.getAsJsonPrimitive();
            if(var2.isNumber()) {
               return "a number (" + var1 + ")";
            }

            if(var2.isBoolean()) {
               return "a boolean (" + var1 + ")";
            }
         }

         return var1;
      }
   }
}
