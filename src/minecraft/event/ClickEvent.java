package net.minecraft.event;

import net.minecraft.event.ClickEvent$Action;

public class ClickEvent {

   private final ClickEvent$Action action;
   private final String value;


   public ClickEvent(ClickEvent$Action var1, String var2) {
      this.action = var1;
      this.value = var2;
   }

   public ClickEvent$Action getAction() {
      return this.action;
   }

   public String getValue() {
      return this.value;
   }

   public boolean equals(Object var1) {
      if(this == var1) {
         return true;
      } else if(var1 != null && this.getClass() == var1.getClass()) {
         ClickEvent var2 = (ClickEvent)var1;
         if(this.action != var2.action) {
            return false;
         } else {
            if(this.value != null) {
               if(!this.value.equals(var2.value)) {
                  return false;
               }
            } else if(var2.value != null) {
               return false;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public String toString() {
      return "ClickEvent{action=" + this.action + ", value=\'" + this.value + '\'' + '}';
   }

   public int hashCode() {
      int var1 = this.action.hashCode();
      var1 = 31 * var1 + (this.value != null?this.value.hashCode():0);
      return var1;
   }
}
