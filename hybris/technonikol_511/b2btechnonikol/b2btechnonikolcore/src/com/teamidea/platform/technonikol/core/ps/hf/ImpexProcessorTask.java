package com.teamidea.platform.technonikol.core.ps.hf;

import de.hybris.platform.servicelayer.impex.ImpExResource;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionService;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public class ImpexProcessorTask extends AbstractHotFolderTask {

    private static final Logger LOG = Logger.getLogger(ImpexProcessorTask.class);

    private SessionService sessionService;
    private ImportService importService;
    private ImportConfig importConfig;

    private String encoding = "UTF-8";

    public HotFolderPackageMessage onExecute(HotFolderPackageMessage message)  {
        Assert.notNull(message);
        Assert.notNull(message.getCurrentPath());
        final Session localSession = getSessionService().createNewSession();
        try (InputStream fis = new FileInputStream(message.getCurrentPath().toFile())) {
            final ImportConfig config = getImportConfig();
            final ImpExResource resource = new StreamBasedImpExResource(fis, getEncoding());
            config.setScript(resource);
            final ImportResult importResult = getImportService().importData(config);
            if (importResult.isError() && importResult.hasUnresolvedLines()) {
                LOG.error(importResult.getUnresolvedLines().getPreview());
            }
            message.setError(importResult.isError());
            return message;
        }  catch (IOException e) {
            LOG.error(e,e);
            message.setError(true);
            return message;
        } finally {
            getSessionService().closeSession(localSession);
        }
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public ImportService getImportService() {
        return importService;
    }

    public void setImportService(ImportService importService) {
        this.importService = importService;
    }

    public ImportConfig getImportConfig() {
        return importConfig;
    }

    public void setImportConfig(ImportConfig importConfig) {
        this.importConfig = importConfig;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
