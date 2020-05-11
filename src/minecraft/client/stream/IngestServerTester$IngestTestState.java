package net.minecraft.client.stream;


public enum IngestServerTester$IngestTestState {

   Uninitalized("Uninitalized", 0),
   Starting("Starting", 1),
   ConnectingToServer("ConnectingToServer", 2),
   TestingServer("TestingServer", 3),
   DoneTestingServer("DoneTestingServer", 4),
   Finished("Finished", 5),
   Cancelled("Cancelled", 6),
   Failed("Failed", 7);
   // $FF: synthetic field
   private static final IngestServerTester$IngestTestState[] $VALUES = new IngestServerTester$IngestTestState[]{Uninitalized, Starting, ConnectingToServer, TestingServer, DoneTestingServer, Finished, Cancelled, Failed};


   private IngestServerTester$IngestTestState(String var1, int var2) {}

}
