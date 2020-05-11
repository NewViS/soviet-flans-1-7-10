package net.minecraft.client.settings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IntHashMap;

public class KeyBinding implements Comparable {

   private static final List keybindArray = new ArrayList();
   private static final IntHashMap hash = new IntHashMap();
   private static final Set keybindSet = new HashSet();
   private final String keyDescription;
   private final int keyCodeDefault;
   private final String keyCategory;
   private int keyCode;
   private boolean pressed;
   private int pressTime;


   public static void onTick(int var0) {
      if(var0 != 0) {
         KeyBinding var1 = (KeyBinding)hash.lookup(var0);
         if(var1 != null) {
            ++var1.pressTime;
         }

      }
   }

   public static void setKeyBindState(int var0, boolean var1) {
      if(var0 != 0) {
         KeyBinding var2 = (KeyBinding)hash.lookup(var0);
         if(var2 != null) {
            var2.pressed = var1;
         }

      }
   }

   public static void unPressAllKeys() {
      Iterator var0 = keybindArray.iterator();

      while(var0.hasNext()) {
         KeyBinding var1 = (KeyBinding)var0.next();
         var1.unpressKey();
      }

   }

   public static void resetKeyBindingArrayAndHash() {
      hash.clearMap();
      Iterator var0 = keybindArray.iterator();

      while(var0.hasNext()) {
         KeyBinding var1 = (KeyBinding)var0.next();
         hash.addKey(var1.keyCode, var1);
      }

   }

   public static Set getKeybinds() {
      return keybindSet;
   }

   public KeyBinding(String var1, int var2, String var3) {
      this.keyDescription = var1;
      this.keyCode = var2;
      this.keyCodeDefault = var2;
      this.keyCategory = var3;
      keybindArray.add(this);
      hash.addKey(var2, this);
      keybindSet.add(var3);
   }

   public boolean getIsKeyPressed() {
      return this.pressed;
   }

   public String getKeyCategory() {
      return this.keyCategory;
   }

   public boolean isPressed() {
      if(this.pressTime == 0) {
         return false;
      } else {
         --this.pressTime;
         return true;
      }
   }

   private void unpressKey() {
      this.pressTime = 0;
      this.pressed = false;
   }

   public String getKeyDescription() {
      return this.keyDescription;
   }

   public int getKeyCodeDefault() {
      return this.keyCodeDefault;
   }

   public int getKeyCode() {
      return this.keyCode;
   }

   public void setKeyCode(int var1) {
      this.keyCode = var1;
   }

   public int compareTo(KeyBinding var1) {
      int var2 = I18n.format(this.keyCategory, new Object[0]).compareTo(I18n.format(var1.keyCategory, new Object[0]));
      if(var2 == 0) {
         var2 = I18n.format(this.keyDescription, new Object[0]).compareTo(I18n.format(var1.keyDescription, new Object[0]));
      }

      return var2;
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.compareTo((KeyBinding)var1);
   }

}
