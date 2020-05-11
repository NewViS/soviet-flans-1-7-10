package de.ItsAMysterious.mods.reallifemod.core.driveables;

import cpw.mods.fml.common.registry.GameRegistry;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.driveables.TypeFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class InfoType {

   public static List infoTypes = new ArrayList();
   public String contentPack;
   public Item item;
   public int colour = 16777215;
   public int itemID;
   public String itemTexture;
   public Object[] recipe;
   public String[] recipeLine;
   public int recipeOutput = 1;
   public boolean shapeless;
   public String smeltableFrom = null;
   public String name;
   public String shortName;
   public String texture;
   public String modelString;
   public String description;
   public float modelScale = 1.0F;
   public String brand;
   private boolean Has3DItem;


   public InfoType(TypeFile file) {
      this.contentPack = file.name;
      infoTypes.add(this);
   }

   public void read(TypeFile file) {
      this.preRead();

      while(true) {
         String line = null;
         line = file.readLine();
         if(line == null) {
            this.postRead();
            return;
         }

         if(!line.startsWith("//")) {
            String[] split = line.split(" ");
            if(split.length >= 2) {
               this.read(split, file);
            }
         }
      }
   }

   protected void preRead() {}

   protected void postRead() {}

   protected void read(String[] split, TypeFile file) {
      if(split[0].equals("Name")) {
         this.shortName = split[1];
      }

      if(split[0].equals("Brand")) {
         this.brand = split[1];
      }

      if(split[0].equals("ItemTexture")) {
         this.itemTexture = split[1];
      }

      if(split[0].equals("3DItem")) {
         this.Has3DItem = Boolean.valueOf(split[1]).booleanValue();
      }

      if(split[0].equals("ModelName")) {
         this.modelString = Minecraft.getMinecraft().mcDataDir.getAbsolutePath() + "/RealLifeMod/models/" + split[1];
      }

   }

   public void addRecipe() {
      this.addRecipe(this.getItem());
   }

   public void addRecipe(Item par1Item) {
      if(this.smeltableFrom != null) {
         GameRegistry.addSmelting(getRecipeElement(this.smeltableFrom, 0), new ItemStack(this.item), 0.0F);
      }

      if(this.recipeLine != null) {
         try {
            int e;
            if(!this.shapeless) {
               e = 3;
               int last;
               if(((String)this.recipe[0]).charAt(0) == 32 && ((String)this.recipe[1]).charAt(0) == 32 && ((String)this.recipe[2]).charAt(0) == 32) {
                  for(last = 0; last < 3; ++last) {
                     this.recipe[last] = ((String)this.recipe[last]).substring(1);
                  }

                  if(((String)this.recipe[0]).charAt(0) == 32 && ((String)this.recipe[1]).charAt(0) == 32 && ((String)this.recipe[2]).charAt(0) == 32) {
                     for(last = 0; last < 3; ++last) {
                        this.recipe[last] = ((String)this.recipe[last]).substring(1);
                     }
                  }
               }

               last = ((String)this.recipe[0]).length() - 1;
               int i;
               if(((String)this.recipe[0]).charAt(last) == 32 && ((String)this.recipe[1]).charAt(last) == 32 && ((String)this.recipe[2]).charAt(last) == 32) {
                  for(i = 0; i < 3; ++i) {
                     this.recipe[i] = ((String)this.recipe[i]).substring(0, last);
                  }

                  --last;
                  if(((String)this.recipe[0]).charAt(last) == 32 && ((String)this.recipe[1]).charAt(last) == 32 && ((String)this.recipe[2]).charAt(last) == 32) {
                     for(i = 0; i < 3; ++i) {
                        this.recipe[i] = ((String)this.recipe[i]).substring(0, 0);
                     }
                  }
               }

               Object[] newRecipe1;
               Object[] var7;
               if(this.recipe[0].equals(" ") || this.recipe[0].equals("  ") || this.recipe[0].equals("   ")) {
                  var7 = new Object[this.recipe.length - 1];
                  var7[0] = this.recipe[1];
                  var7[1] = this.recipe[2];
                  this.recipe = var7;
                  --e;
                  if(this.recipe[0].equals(" ") || this.recipe[0].equals("  ") || this.recipe[0].equals("   ")) {
                     newRecipe1 = new Object[this.recipe.length - 1];
                     newRecipe1[0] = this.recipe[1];
                     this.recipe = newRecipe1;
                     --e;
                  }
               }

               if(this.recipe[e - 1].equals(" ") || this.recipe[e - 1].equals("  ") || this.recipe[e - 1].equals("   ")) {
                  var7 = new Object[this.recipe.length - 1];
                  var7[0] = this.recipe[0];
                  var7[1] = this.recipe[1];
                  this.recipe = var7;
                  --e;
                  if(this.recipe[e - 1].equals(" ") || this.recipe[e - 1].equals("  ") || this.recipe[e - 1].equals("   ")) {
                     newRecipe1 = new Object[this.recipe.length - 1];
                     newRecipe1[0] = this.recipe[0];
                     this.recipe = newRecipe1;
                     --e;
                  }
               }

               for(i = 0; i < (this.recipeLine.length - 1) / 2; ++i) {
                  this.recipe[i * 2 + e] = Character.valueOf(this.recipeLine[i * 2 + 1].charAt(0));
                  if(this.recipeLine[i * 2 + 2].contains(".")) {
                     this.recipe[i * 2 + e + 1] = getRecipeElement(this.recipeLine[i * 2 + 2].split("\\.")[0], Integer.valueOf(this.recipeLine[i * 2 + 2].split("\\.")[1]).intValue());
                  } else {
                     this.recipe[i * 2 + e + 1] = getRecipeElement(this.recipeLine[i * 2 + 2], 0);
                  }
               }

               GameRegistry.addRecipe(new ItemStack(this.item, this.recipeOutput), this.recipe);
            } else {
               this.recipe = new Object[this.recipeLine.length - 1];

               for(e = 0; e < this.recipeLine.length - 1; ++e) {
                  if(this.recipeLine[e + 1].contains(".")) {
                     this.recipe[e] = getRecipeElement(this.recipeLine[e + 1].split("\\.")[0], Integer.valueOf(this.recipeLine[e + 1].split("\\.")[1]).intValue());
                  } else {
                     this.recipe[e] = getRecipeElement(this.recipeLine[e + 1], 0);
                  }
               }

               GameRegistry.addShapelessRecipe(new ItemStack(this.item, this.recipeOutput), this.recipe);
            }
         } catch (Exception var6) {
            RealLifeMod.log("Failed to add recipe for : " + this.shortName);
            var6.printStackTrace();
         }

      }
   }

   public Item getItem() {
      return this.item;
   }

   public static ItemStack getRecipeElement(String s, int damage) {
      return getRecipeElement(s, 1, damage);
   }

   public static ItemStack getRecipeElement(String s, int amount, int damage) {
      if(s.equals("doorIron")) {
         return new ItemStack(Items.iron_door, amount);
      } else if(s.equals("doorWood")) {
         return new ItemStack(Items.wooden_door, amount);
      } else if(s.equals("clayItem")) {
         return new ItemStack(Items.clay_ball, amount);
      } else {
         Iterator i$ = Item.itemRegistry.iterator();

         Item item;
         do {
            do {
               do {
                  if(!i$.hasNext()) {
                     i$ = infoTypes.iterator();

                     InfoType type1;
                     do {
                        if(!i$.hasNext()) {
                           if(s.equals("gunpowder")) {
                              return new ItemStack(Items.gunpowder, amount);
                           }

                           if(s.equals("iron")) {
                              return new ItemStack(Items.iron_ingot, amount);
                           }

                           RealLifeMod.log("Could not find " + s + " when adding recipe");
                           return null;
                        }

                        type1 = (InfoType)i$.next();
                     } while(!type1.shortName.equals(s));

                     return new ItemStack(type1.item, amount, damage);
                  }

                  Object type = i$.next();
                  item = (Item)type;
               } while(item == null);
            } while(item.getContainerItem() == null);
         } while(!item.getUnlocalizedName().equals("item." + s) && !item.getUnlocalizedName().equals("tile." + s));

         return new ItemStack(item, amount, damage);
      }
   }

   public void reloadModel() {}

   public static InfoType getType(String s) {
      Iterator i$ = infoTypes.iterator();

      InfoType type;
      do {
         if(!i$.hasNext()) {
            return null;
         }

         type = (InfoType)i$.next();
      } while(!type.shortName.equals(s));

      return type;
   }

   public void onWorldLoad(World world1) {}

}
