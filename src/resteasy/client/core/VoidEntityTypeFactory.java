package resteasy.client.core;


import javax.ws.rs.core.MultivaluedMap;

import resteasy.client.EntityTypeFactory;


public class VoidEntityTypeFactory implements EntityTypeFactory
{

   @SuppressWarnings("unchecked")
   public Class getEntityType(int status,
                              MultivaluedMap<String, Object> metadata)
   {
      return Void.class;
   }

}
