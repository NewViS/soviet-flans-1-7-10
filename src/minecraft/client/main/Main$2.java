package net.minecraft.client.main;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public final class Main$2 extends Authenticator {

   // $FF: synthetic field
   final String field_152581_a;
   // $FF: synthetic field
   final String field_152582_b;


   public Main$2(String var1, String var2) {
      this.field_152581_a = var1;
      this.field_152582_b = var2;
   }

   protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(this.field_152581_a, this.field_152582_b.toCharArray());
   }
}
