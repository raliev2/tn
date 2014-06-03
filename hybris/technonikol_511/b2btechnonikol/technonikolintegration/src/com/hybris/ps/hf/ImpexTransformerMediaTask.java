package com.hybris.ps.hf;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.converter.ImpexConverter;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.ImpexTransformerTask;
import de.hybris.platform.core.Registry;
import de.hybris.platform.util.CSVReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class ImpexTransformerMediaTask extends ImpexTransformerTask {
    private static final Logger LOG = Logger.getLogger(ImpexTransformerMediaTask.class);
    private String converterkey;

    @Override
    public BatchHeader execute(final BatchHeader header) throws UnsupportedEncodingException, FileNotFoundException
    {
        Registry.activateMasterTenant();

        return super.execute(header);
    }

    /**
     * Retrieves the converter for a file.
     *
     * @throws IllegalArgumentException
     *            if no converter was found
     * @return the configured converter
     */
    @Override
    protected List<ImpexConverter> getConverters(final File file)
    {
        for (final Map.Entry<String, List<ImpexConverter>> entry : getConverterMap().entrySet())
        {
            if (entry.getKey().equals(converterkey))
            {
                return entry.getValue();
            }
        }
        throw new IllegalArgumentException("Converters with prefix not found: " + converterkey);
    }


    /**
     * Converts the CSV file to an impex file using the given converter
     *
     * @param header
     * @param file
     * @param impexFile
     * @param converter
     * @return true, if the file contains at least one converted row
     * @throws java.io.UnsupportedEncodingException
     * @throws java.io.FileNotFoundException
     */
    protected boolean convertFile(final BatchHeader header, final File file, final File impexFile, final ImpexConverter converter)
            throws UnsupportedEncodingException, FileNotFoundException
    {
        boolean result = false;
        PrintWriter writer = null;
        PrintWriter errorWriter = null;
        final Map<Integer, String> row = new HashMap<>();
        row.put(1, file.getName().split("\\.")[0]);
        row.put(2, header.getFile().getParentFile().getAbsolutePath());

        try
        {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(impexFile), getEncoding())));
            writer.println(getReplacedHeader(header, converter));

                if (converter.filter(row))
                {
                    try
                    {
                        writer.println(converter.convert(row, header.getSequenceId()));
                        result = true;
                    }
                    catch (final IllegalArgumentException exc)
                    {
                        errorWriter = writeErrorLine(file, null, errorWriter, exc);
                    }
                }
        } catch (IOException ioException) {
            LOG.warn("Exception while converting impex file",ioException);
        }
        finally
        {
            IOUtils.closeQuietly(writer);
            IOUtils.closeQuietly(errorWriter);
        }
        return result;
    }

    @Override
    protected PrintWriter writeErrorLine(final File file, final CSVReader csvReader, final PrintWriter errorWriter,
                                         final IllegalArgumentException exc) throws UnsupportedEncodingException, FileNotFoundException
    {
        PrintWriter result = errorWriter;
        if (result == null)
        {
            result = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getErrorFile(file)), getEncoding())));
        }
        result.print(exc.getMessage());
        return result;
    }

    public String getConverterkey() {
        return converterkey;
    }

    public void setConverterkey(String converterkey) {
        this.converterkey = converterkey;
    }
}
