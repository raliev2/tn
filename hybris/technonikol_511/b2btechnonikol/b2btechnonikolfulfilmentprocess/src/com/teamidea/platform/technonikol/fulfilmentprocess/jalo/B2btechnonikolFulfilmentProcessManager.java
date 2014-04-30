/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  
 */
package com.teamidea.platform.technonikol.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.teamidea.platform.technonikol.fulfilmentprocess.constants.B2btechnonikolFulfilmentProcessConstants;

import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class B2btechnonikolFulfilmentProcessManager extends GeneratedB2btechnonikolFulfilmentProcessManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( B2btechnonikolFulfilmentProcessManager.class.getName() );
	
	public static final B2btechnonikolFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (B2btechnonikolFulfilmentProcessManager) em.getExtension(B2btechnonikolFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
