package com.teamidea.platform.technonikol.core.ps.hf;

import de.hybris.platform.acceleratorservices.dataimport.batch.util.BatchDirectoryUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public class CleanupTask extends AbstractHotFolderTask {

    private static final Logger LOG = Logger.getLogger(CleanupTask.class);

    public HotFolderPackageMessage onExecute(HotFolderPackageMessage message){
        String dirName = message.isError() ? BatchDirectoryUtils.getRelativeErrorDirectory(message.getCurrentPath().toFile()) :
         BatchDirectoryUtils.getRelativeArchiveDirectory(message.getCurrentPath().toFile());
        try {
            Files.move(message.getCurrentPath(),Paths.get(dirName, message.getCurrentPath().getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            LOG.error(e,e);
        }
        return message;
    }
}
