package com.teamidea.platform.technonikol.core.dataimport.bigpackage.finalizer;

import de.hybris.platform.servicelayer.type.TypeService;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.teamidea.platform.technonikol.core.dataimport.bigpackage.HotFolderPackageMessage;


/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public class StockLevelSQLFinalizer implements Finalizer
{
	private TypeService typeService;

	private TransactionTemplate transactionTemplate;
	private JdbcTemplate jdbcTemplate;

	private String sqlQuery = "UPDATE stocklevels SET p_available=0 where p_packageid<>?";

	@Override
	public void finalizeImport(final HotFolderPackageMessage object)
	{
		transactionTemplate.execute(new TransactionCallback<Object>()
		{
			@Override
			public Object doInTransaction(final TransactionStatus transactionStatus)
			{
				return getJdbcTemplate().update(sqlQuery, object.getPackageId());
			}
		});
	}

	public TypeService getTypeService()
	{
		return typeService;
	}

	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}

	public TransactionTemplate getTransactionTemplate()
	{
		return transactionTemplate;
	}

	public void setTransactionTemplate(final TransactionTemplate transactionTemplate)
	{
		this.transactionTemplate = transactionTemplate;
	}

	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}

	public void setJdbcTemplate(final JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getSqlQuery()
	{
		return sqlQuery;
	}

	public void setSqlQuery(final String sqlQuery)
	{
		this.sqlQuery = sqlQuery;
	}
}
