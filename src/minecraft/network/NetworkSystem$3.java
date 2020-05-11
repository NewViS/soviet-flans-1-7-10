package net.minecraft.network;

import java.util.concurrent.Callable;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkSystem;

class NetworkSystem$3 implements Callable {

   // $FF: synthetic field
   final NetworkManager field_151280_a;
   // $FF: synthetic field
   final NetworkSystem field_151279_b;


   NetworkSystem$3(NetworkSystem var1, NetworkManager var2) {
      this.field_151279_b = var1;
      this.field_151280_a = var2;
   }

   public String call() {
      return this.field_151280_a.toString();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
