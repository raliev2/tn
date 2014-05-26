package com.teamidea.platform.technonikol.core.ps.hf;

import de.hybris.platform.servicelayer.type.TypeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public class SQLFinalizer implements Finalizer {
    private TypeService typeService;

    private TransactionTemplate transactionTemplate;
    private JdbcTemplate jdbcTemplate;

    private String sqlQuery = "UPDATE STOCKLEVELS SET p_available=0 where p_packageid<>?";

    @Override
    public void finalizeImport(final AbstractHotFolderTask.HotFolderPackageMessage object) {
         transactionTemplate.execute(new TransactionCallback<Object>() {
             @Override
             public Object doInTransaction(TransactionStatus transactionStatus) {
                 return getJdbcTemplate().update(sqlQuery,object.getPackageId());
             }
         });
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }
}
