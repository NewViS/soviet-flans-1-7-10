package net.minecraft.server.network;


enum NetHandlerLoginServer$LoginState {

   HELLO("HELLO", 0),
   KEY("KEY", 1),
   AUTHENTICATING("AUTHENTICATING", 2),
   READY_TO_ACCEPT("READY_TO_ACCEPT", 3),
   ACCEPTED("ACCEPTED", 4);
   // $FF: synthetic field
   private static final NetHandlerLoginServer$LoginState[] $VALUES = new NetHandlerLoginServer$LoginState[]{HELLO, KEY, AUTHENTICATING, READY_TO_ACCEPT, ACCEPTED};


   private NetHandlerLoginServer$LoginState(String var1, int var2) {}

}
