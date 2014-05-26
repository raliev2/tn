package com.teamidea.platform.technonikol.core.ps.hf;

import com.teamidea.platform.technonikol.core.ps.hf.model.HFPackageModel;
import de.hybris.platform.core.Registry;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public class PackageFileRegistrationTask extends AbstractHotFolderTask {

    private static final Logger LOG = Logger.getLogger(PackageFileRegistrationTask.class);

    public HotFolderPackageMessage onExecute(final File file) {

        final Path filePath = file.toPath();
        Registry.activateMasterTenant();
        final HotFolderPackageMessage message = createMessage(filePath);
        getTransactionTemplate().execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                HFPackageModel model = getHotFolderPackageService().getPackage(message.getPackageId());

                if (model != null) {
                    LOG.debug("Package already exists: " + message.getPackageId() + " for file: " + filePath);
                    if(model.getFinished()){
                        throw new IllegalArgumentException("Cannot process file for finished package: "+model.getFinished());
                    }
                    getHotFolderPackageService().registerNewFile(message.getPackageId(), message.getSequenceNumber(), filePath);
                } else {
                    LOG.debug("Package: "+message.getPackageId()+" doesnt exist for file: " + filePath + " create new one...");
                    getHotFolderPackageService().registerPackage(message.getPackageId(), message.getPackageType(), message.getFileNumbers(), message.getSequenceNumber(), filePath);
                }
            }
        });
        return message;
    }
}
