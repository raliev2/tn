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
package com.teamidea.platform.technonikol.core.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.teamidea.platform.technonikol.core.constants.B2btechnonikolCoreConstants;
import com.teamidea.platform.technonikol.core.setup.CoreSystemSetup;

import org.apache.log4j.Logger;


/**
 * Don't use. User {@link CoreSystemSetup} instead.
 */
@SuppressWarnings("PMD")
public class B2btechnonikolCoreManager extends GeneratedB2btechnonikolCoreManager
{
	@SuppressWarnings("unused")
	private static Logger LOG = Logger.getLogger(B2btechnonikolCoreManager.class.getName());

	public static final B2btechnonikolCoreManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (B2btechnonikolCoreManager) em.getExtension(B2btechnonikolCoreConstants.EXTENSIONNAME);
	}
}
