package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.IRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrySimple implements IRegistry {

   private static final Logger logger = LogManager.getLogger();
   protected final Map registryObjects = this.createUnderlyingMap();


   protected Map createUnderlyingMap() {
      return Maps.newHashMap();
   }

   public Object getObject(Object var1) {
      return this.registryObjects.get(var1);
   }

   public void putObject(Object var1, Object var2) {
      if(this.registryObjects.containsKey(var1)) {
         logger.debug("Adding duplicate key \'" + var1 + "\' to registry");
      }

      this.registryObjects.put(var1, var2);
   }

   public Set getKeys() {
      return Collections.unmodifiableSet(this.registryObjects.keySet());
   }

   public boolean containsKey(Object var1) {
      return this.registryObjects.containsKey(var1);
   }

}
