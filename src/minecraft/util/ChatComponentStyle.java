package net.minecraft.util;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.ChatComponentStyle$1;
import net.minecraft.util.ChatComponentStyle$2;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public abstract class ChatComponentStyle implements IChatComponent {

   protected List siblings = Lists.newArrayList();
   private ChatStyle style;


   public IChatComponent appendSibling(IChatComponent var1) {
      var1.getChatStyle().setParentStyle(this.getChatStyle());
      this.siblings.add(var1);
      return this;
   }

   public List getSiblings() {
      return this.siblings;
   }

   public IChatComponent appendText(String var1) {
      return this.appendSibling(new ChatComponentText(var1));
   }

   public IChatComponent setChatStyle(ChatStyle var1) {
      this.style = var1;
      Iterator var2 = this.siblings.iterator();

      while(var2.hasNext()) {
         IChatComponent var3 = (IChatComponent)var2.next();
         var3.getChatStyle().setParentStyle(this.getChatStyle());
      }

      return this;
   }

   public ChatStyle getChatStyle() {
      if(this.style == null) {
         this.style = new ChatStyle();
         Iterator var1 = this.siblings.iterator();

         while(var1.hasNext()) {
            IChatComponent var2 = (IChatComponent)var1.next();
            var2.getChatStyle().setParentStyle(this.style);
         }
      }

      return this.style;
   }

   public Iterator iterator() {
      return Iterators.concat(Iterators.forArray(new ChatComponentStyle[]{this}), createDeepCopyIterator(this.siblings));
   }

   public final String getUnformattedText() {
      StringBuilder var1 = new StringBuilder();
      Iterator var2 = this.iterator();

      while(var2.hasNext()) {
         IChatComponent var3 = (IChatComponent)var2.next();
         var1.append(var3.getUnformattedTextForChat());
      }

      return var1.toString();
   }

   public final String getFormattedText() {
      StringBuilder var1 = new StringBuilder();
      Iterator var2 = this.iterator();

      while(var2.hasNext()) {
         IChatComponent var3 = (IChatComponent)var2.next();
         var1.append(var3.getChatStyle().getFormattingCode());
         var1.append(var3.getUnformattedTextForChat());
         var1.append(EnumChatFormatting.RESET);
      }

      return var1.toString();
   }

   public static Iterator createDeepCopyIterator(Iterable var0) {
      Iterator var1 = Iterators.concat(Iterators.transform(var0.iterator(), new ChatComponentStyle$1()));
      var1 = Iterators.transform(var1, new ChatComponentStyle$2());
      return var1;
   }

   public boolean equals(Object var1) {
      if(this == var1) {
         return true;
      } else if(!(var1 instanceof ChatComponentStyle)) {
         return false;
      } else {
         ChatComponentStyle var2 = (ChatComponentStyle)var1;
         return this.siblings.equals(var2.siblings) && this.getChatStyle().equals(var2.getChatStyle());
      }
   }

   public int hashCode() {
      return 31 * this.style.hashCode() + this.siblings.hashCode();
   }

   public String toString() {
      return "BaseComponent{style=" + this.style + ", siblings=" + this.siblings + '}';
   }
}
