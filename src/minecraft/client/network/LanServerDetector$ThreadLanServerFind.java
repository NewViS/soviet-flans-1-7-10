package net.minecraft.client.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import net.minecraft.client.network.LanServerDetector;
import net.minecraft.client.network.LanServerDetector$LanServerList;

public class LanServerDetector$ThreadLanServerFind extends Thread {

   private final LanServerDetector$LanServerList localServerList;
   private final InetAddress broadcastAddress;
   private final MulticastSocket socket;


   public LanServerDetector$ThreadLanServerFind(LanServerDetector$LanServerList var1) {
      super("LanServerDetector #" + LanServerDetector.access$000().incrementAndGet());
      this.localServerList = var1;
      this.setDaemon(true);
      this.socket = new MulticastSocket(4445);
      this.broadcastAddress = InetAddress.getByName("224.0.2.60");
      this.socket.setSoTimeout(5000);
      this.socket.joinGroup(this.broadcastAddress);
   }

   public void run() {
      byte[] var2 = new byte[1024];

      while(!this.isInterrupted()) {
         DatagramPacket var1 = new DatagramPacket(var2, var2.length);

         try {
            this.socket.receive(var1);
         } catch (SocketTimeoutException var5) {
            continue;
         } catch (IOException var6) {
            LanServerDetector.access$100().error("Couldn\'t ping server", var6);
            break;
         }

         String var3 = new String(var1.getData(), var1.getOffset(), var1.getLength());
         LanServerDetector.access$100().debug(var1.getAddress() + ": " + var3);
         this.localServerList.func_77551_a(var3, var1.getAddress());
      }

      try {
         this.socket.leaveGroup(this.broadcastAddress);
      } catch (IOException var4) {
         ;
      }

      this.socket.close();
   }
}
