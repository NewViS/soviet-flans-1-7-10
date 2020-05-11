package net.minecraft.item;

import net.minecraft.util.EnumChatFormatting;

public enum EnumRarity {

   common("common", 0, EnumChatFormatting.WHITE, "Common"),
   uncommon("uncommon", 1, EnumChatFormatting.YELLOW, "Uncommon"),
   rare("rare", 2, EnumChatFormatting.AQUA, "Rare"),
   epic("epic", 3, EnumChatFormatting.LIGHT_PURPLE, "Epic");
   public final EnumChatFormatting rarityColor;
   public final String rarityName;
   // $FF: synthetic field
   private static final EnumRarity[] $VALUES = new EnumRarity[]{common, uncommon, rare, epic};


   private EnumRarity(String var1, int var2, EnumChatFormatting var3, String var4) {
      this.rarityColor = var3;
      this.rarityName = var4;
   }

}
