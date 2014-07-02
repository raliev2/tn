package com.teamidea.platform.technonikol.services.jalo;

import java.util.concurrent.ThreadFactory;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.Tenant;
import de.hybris.platform.jalo.JaloSession;

public class JaloThreadFactory implements ThreadFactory
{
   @Override
   public Thread newThread(final Runnable runnable)
   {
      return new Thread()
      {
         // get session from callers scope
         final JaloSession session = JaloSession.getCurrentSession();
          
         // get tenant from callers scope
         final Tenant tenant = Registry.getCurrentTenant();
 
         protected void prepareThread()
         {
            Registry.setCurrentTenant(this.tenant);
            this.session.activate();
         }
 
         protected void unprepareThread()
         {
            JaloSession.deactivate();
            Registry.unsetCurrentTenant();
         }
 
         @Override
         public void run()
         {
            try
            {
               prepareThread();
                
               runnable.run();
            }
            finally
            {
               unprepareThread();
            }
         }
      };
   }
}
