package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.util.Iterator;
import net.minecraft.server.management.UserList;
import net.minecraft.server.management.UserListEntry;
import net.minecraft.server.management.UserListOpsEntry;

public class UserListOps extends UserList {

   public UserListOps(File var1) {
      super(var1);
   }

   protected UserListEntry func_152682_a(JsonObject var1) {
      return new UserListOpsEntry(var1);
   }

   public String[] func_152685_a() {
      String[] var1 = new String[this.func_152688_e().size()];
      int var2 = 0;

      UserListOpsEntry var4;
      for(Iterator var3 = this.func_152688_e().values().iterator(); var3.hasNext(); var1[var2++] = ((GameProfile)var4.func_152640_f()).getName()) {
         var4 = (UserListOpsEntry)var3.next();
      }

      return var1;
   }

   protected String func_152699_b(GameProfile var1) {
      return var1.getId().toString();
   }

   public GameProfile func_152700_a(String var1) {
      Iterator var2 = this.func_152688_e().values().iterator();

      UserListOpsEntry var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (UserListOpsEntry)var2.next();
      } while(!var1.equalsIgnoreCase(((GameProfile)var3.func_152640_f()).getName()));

      return (GameProfile)var3.func_152640_f();
   }

   // $FF: synthetic method
   protected String func_152681_a(Object var1) {
      return this.func_152699_b((GameProfile)var1);
   }
}
