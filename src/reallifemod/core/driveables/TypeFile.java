package de.ItsAMysterious.mods.reallifemod.core.driveables;

import de.ItsAMysterious.mods.reallifemod.core.driveables.EnumTypes;
import java.util.ArrayList;
import java.util.HashMap;

public class TypeFile {

   public EnumTypes type;
   public String name;
   public ArrayList lines;
   public static HashMap files = new HashMap();
   private int readerPosition;


   public TypeFile(EnumTypes t, String s) {
      this(t, s, true);
   }

   public TypeFile(EnumTypes t, String s, boolean addToTypeFileList) {
      this.readerPosition = 0;
      this.type = t;
      this.name = s;
      this.lines = new ArrayList();
      if(addToTypeFileList) {
         ((ArrayList)files.get(this.type)).add(this);
      }

   }

   public String readLine() {
      return this.readerPosition == this.lines.size()?null:(String)this.lines.get(this.readerPosition++);
   }

   static {
      EnumTypes[] arr$ = EnumTypes.values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         EnumTypes type = arr$[i$];
         files.put(type, new ArrayList());
      }

   }
}
