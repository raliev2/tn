/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.hybris.ps.hf.jalo;

import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.hybris.platform.util.JspContext;

import java.util.Map;

import org.apache.log4j.Logger;

import com.hybris.ps.hf.constants.TechnonikolintegrationConstants;

import static com.hybris.ps.hf.constants.TechnonikolintegrationConstants.*;


/**
 * Don't use. User {@link CoreSystemSetup} instead.
 */
public class TechnonikolintegrationManager extends GeneratedTechnonikolintegrationManager
{
	/** Edit the local|project.properties to change logging behavior (properties 'log4j.*'). */
	private static final Logger LOG = Logger.getLogger(TechnonikolintegrationManager.class.getName());


	/**
	 * Get the valid instance of this manager.
	 * 
	 * @return the current instance of this manager
	 */
    public static final TechnonikolintegrationManager getInstance()
    {
        final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
        return (TechnonikolintegrationManager) em.getExtension(EXTENSIONNAME);
    }

}
