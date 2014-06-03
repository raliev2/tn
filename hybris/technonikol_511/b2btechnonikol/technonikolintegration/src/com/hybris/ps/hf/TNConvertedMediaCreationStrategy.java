package com.hybris.ps.hf;

import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.mediaconversion.conversion.DefaultConvertedMediaCreationStrategy;
import de.hybris.platform.mediaconversion.model.ConversionMediaFormatModel;
import de.hybris.platform.servicelayer.media.MediaIOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class TNConvertedMediaCreationStrategy extends DefaultConvertedMediaCreationStrategy {
    private static final Logger LOG = Logger.getLogger(TNConvertedMediaCreationStrategy.class);

    private String mediaFolder;

    @Override
    public MediaModel createOrUpdate(MediaModel parent, ConversionMediaFormatModel format, InputStream content) throws MediaIOException {
        return super.createOrUpdate(parent, format, content);
    }

    @Override
    protected void loadContents(final MediaModel dmm, final MediaModel parent, final ConversionMediaFormatModel format,
                                final InputStream inputStream) {
        FileInputStream fis = null;
        try {
            File file = new File(getMediaFolder() + createFileName(parent, format));
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileUtils.copyInputStreamToFile(inputStream, file);
            fis = new FileInputStream(file);
            super.loadContents(dmm, parent, format, fis);
        } catch (IOException e) {
            LOG.error(e, e);

        } finally {
            IOUtils.closeQuietly(fis);
        }

    }

    @Override
    protected String createCode(MediaModel parent, MediaFormatModel format) {
        return "/" + format.getQualifier() + "/" + getFileName(parent);
    }

    private String getFileName(MediaModel parent) {
        return (parent.getRealFileName().contains("/")
                ? parent.getRealFileName().substring(parent.getRealFileName().lastIndexOf('/') + 1)
                : parent.getRealFileName());
    }

    public String getMediaFolder() {
        return mediaFolder;
    }

    public void setMediaFolder(String mediaFolder) {
        this.mediaFolder = mediaFolder;
    }
}
