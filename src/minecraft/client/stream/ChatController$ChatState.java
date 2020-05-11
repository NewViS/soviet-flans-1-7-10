package net.minecraft.client.stream;


public enum ChatController$ChatState {

   Uninitialized("Uninitialized", 0),
   Initialized("Initialized", 1),
   Connecting("Connecting", 2),
   Connected("Connected", 3),
   Disconnected("Disconnected", 4);
   // $FF: synthetic field
   private static final ChatController$ChatState[] $VALUES = new ChatController$ChatState[]{Uninitialized, Initialized, Connecting, Connected, Disconnected};


   private ChatController$ChatState(String var1, int var2) {}

}
