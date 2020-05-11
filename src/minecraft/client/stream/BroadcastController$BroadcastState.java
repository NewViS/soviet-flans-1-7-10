package net.minecraft.client.stream;


public enum BroadcastController$BroadcastState {

   Uninitialized("Uninitialized", 0),
   Initialized("Initialized", 1),
   Authenticating("Authenticating", 2),
   Authenticated("Authenticated", 3),
   LoggingIn("LoggingIn", 4),
   LoggedIn("LoggedIn", 5),
   FindingIngestServer("FindingIngestServer", 6),
   ReceivedIngestServers("ReceivedIngestServers", 7),
   ReadyToBroadcast("ReadyToBroadcast", 8),
   Starting("Starting", 9),
   Broadcasting("Broadcasting", 10),
   Stopping("Stopping", 11),
   Paused("Paused", 12),
   IngestTesting("IngestTesting", 13);
   // $FF: synthetic field
   private static final BroadcastController$BroadcastState[] $VALUES = new BroadcastController$BroadcastState[]{Uninitialized, Initialized, Authenticating, Authenticated, LoggingIn, LoggedIn, FindingIngestServer, ReceivedIngestServers, ReadyToBroadcast, Starting, Broadcasting, Stopping, Paused, IngestTesting};


   private BroadcastController$BroadcastState(String var1, int var2) {}

}
