package net.minecraft.world;


public enum EnumDifficulty {

   PEACEFUL("PEACEFUL", 0, 0, "options.difficulty.peaceful"),
   EASY("EASY", 1, 1, "options.difficulty.easy"),
   NORMAL("NORMAL", 2, 2, "options.difficulty.normal"),
   HARD("HARD", 3, 3, "options.difficulty.hard");
   private static final EnumDifficulty[] difficultyEnums = new EnumDifficulty[values().length];
   private final int difficultyId;
   private final String difficultyResourceKey;
   // $FF: synthetic field
   private static final EnumDifficulty[] $VALUES = new EnumDifficulty[]{PEACEFUL, EASY, NORMAL, HARD};


   private EnumDifficulty(String var1, int var2, int var3, String var4) {
      this.difficultyId = var3;
      this.difficultyResourceKey = var4;
   }

   public int getDifficultyId() {
      return this.difficultyId;
   }

   public static EnumDifficulty getDifficultyEnum(int var0) {
      return difficultyEnums[var0 % difficultyEnums.length];
   }

   public String getDifficultyResourceKey() {
      return this.difficultyResourceKey;
   }

   static {
      EnumDifficulty[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         EnumDifficulty var3 = var0[var2];
         difficultyEnums[var3.difficultyId] = var3;
      }

   }
}
