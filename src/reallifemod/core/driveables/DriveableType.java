package de.ItsAMysterious.mods.reallifemod.core.driveables;

import de.ItsAMysterious.mods.reallifemod.api.entity.EntitySeat;
import de.ItsAMysterious.mods.reallifemod.core.driveables.ModelDrivable;
import de.ItsAMysterious.mods.reallifemod.core.driveables.TypeFile;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class DriveableType {

   public static ArrayList allTypes = new ArrayList();
   public ModelDrivable model;
   public int numPassengers = 0;
   public EntitySeat[] seats;
   public float minSpeed;
   public float maxSpeed;
   public float mass;
   public int numCargo;
   public int numAmmo;
   public String name;


   public DriveableType(TypeFile file) {
      Iterator i$ = file.lines.iterator();

      while(i$.hasNext()) {
         String line = (String)i$.next();
         if(line == null) {
            break;
         }

         if(!line.startsWith("//")) {
            String[] split = line.split(" ");
            if(split.length >= 2 && split[0].equals("Passengers")) {
               this.numPassengers = Integer.parseInt(split[1]);
               this.seats = new EntitySeat[this.numPassengers + 1];
            }
         }
      }

      allTypes.add(this);
   }

   protected void loadFromFile(String[] split, File file) {}

   protected static DriveableType getDriveable(String find) {
      Iterator i$ = allTypes.iterator();

      DriveableType type;
      do {
         if(!i$.hasNext()) {
            return null;
         }

         type = (DriveableType)i$.next();
      } while(!type.name.equals(find));

      return type;
   }

}
