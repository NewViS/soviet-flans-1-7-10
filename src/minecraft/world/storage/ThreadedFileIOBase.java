package net.minecraft.world.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.world.storage.IThreadedFileIO;

public class ThreadedFileIOBase implements Runnable {

   public static final ThreadedFileIOBase threadedIOInstance = new ThreadedFileIOBase();
   private List threadedIOQueue = Collections.synchronizedList(new ArrayList());
   private volatile long writeQueuedCounter;
   private volatile long savedIOCounter;
   private volatile boolean isThreadWaiting;


   private ThreadedFileIOBase() {
      Thread var1 = new Thread(this, "File IO Thread");
      var1.setPriority(1);
      var1.start();
   }

   public void run() {
      this.processQueue();
   }

   private void processQueue() {
      for(int var1 = 0; var1 < this.threadedIOQueue.size(); ++var1) {
         IThreadedFileIO var2 = (IThreadedFileIO)this.threadedIOQueue.get(var1);
         boolean var3 = var2.writeNextIO();
         if(!var3) {
            this.threadedIOQueue.remove(var1--);
            ++this.savedIOCounter;
         }

         try {
            Thread.sleep(this.isThreadWaiting?0L:10L);
         } catch (InterruptedException var6) {
            var6.printStackTrace();
         }
      }

      if(this.threadedIOQueue.isEmpty()) {
         try {
            Thread.sleep(25L);
         } catch (InterruptedException var5) {
            var5.printStackTrace();
         }
      }

   }

   public void queueIO(IThreadedFileIO var1) {
      if(!this.threadedIOQueue.contains(var1)) {
         ++this.writeQueuedCounter;
         this.threadedIOQueue.add(var1);
      }
   }

   public void waitForFinish() {
      this.isThreadWaiting = true;

      while(this.writeQueuedCounter != this.savedIOCounter) {
         Thread.sleep(10L);
      }

      this.isThreadWaiting = false;
   }

}
