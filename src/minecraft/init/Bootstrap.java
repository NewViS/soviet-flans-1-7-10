package net.minecraft.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockFire;
import net.minecraft.init.Blocks;
import net.minecraft.init.Bootstrap$1;
import net.minecraft.init.Bootstrap$10;
import net.minecraft.init.Bootstrap$11;
import net.minecraft.init.Bootstrap$12;
import net.minecraft.init.Bootstrap$13;
import net.minecraft.init.Bootstrap$14;
import net.minecraft.init.Bootstrap$2;
import net.minecraft.init.Bootstrap$3;
import net.minecraft.init.Bootstrap$4;
import net.minecraft.init.Bootstrap$5;
import net.minecraft.init.Bootstrap$6;
import net.minecraft.init.Bootstrap$7;
import net.minecraft.init.Bootstrap$8;
import net.minecraft.init.Bootstrap$9;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.stats.StatList;

public class Bootstrap {

   private static boolean field_151355_a = false;


   static void func_151353_a() {
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.arrow, new Bootstrap$1());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.egg, new Bootstrap$2());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.snowball, new Bootstrap$3());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.experience_bottle, new Bootstrap$4());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.potionitem, new Bootstrap$5());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.spawn_egg, new Bootstrap$6());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.fireworks, new Bootstrap$7());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.fire_charge, new Bootstrap$8());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.boat, new Bootstrap$9());
      Bootstrap$10 var0 = new Bootstrap$10();
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.lava_bucket, var0);
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.water_bucket, var0);
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.bucket, new Bootstrap$11());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.flint_and_steel, new Bootstrap$12());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.dye, new Bootstrap$13());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Item.getItemFromBlock(Blocks.tnt), new Bootstrap$14());
   }

   public static void func_151354_b() {
      if(!field_151355_a) {
         field_151355_a = true;
         Block.registerBlocks();
         BlockFire.func_149843_e();
         Item.registerItems();
         StatList.func_151178_a();
         func_151353_a();
      }
   }

}
