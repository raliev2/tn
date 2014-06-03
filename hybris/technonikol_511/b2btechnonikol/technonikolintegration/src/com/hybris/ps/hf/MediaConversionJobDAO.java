package com.hybris.ps.hf;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.link.LinkModel;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.mediaconversion.constants.MediaConversionConstants;
import de.hybris.platform.mediaconversion.model.ConversionMediaFormatModel;
import de.hybris.platform.mediaconversion.model.job.MediaConversionCronJobModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.mediaconversion.job.DefaultMediaConversionJobDao;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Required;

import java.util.*;

/**
 * Created by elena on 5/26/14.
 */
public class MediaConversionJobDAO extends DefaultMediaConversionJobDao
{
    /**
     * Query parameter name as set by {@link #queryParams(de.hybris.platform.mediaconversion.model.job.MediaConversionCronJobModel)}.
     */
    public static final String CATALOGVERSION_PARAMETER = "catVersion";
    /**
     * Query parameter name as set by {@link #queryParams(de.hybris.platform.mediaconversion.model.job.MediaConversionCronJobModel)}.
     */
    public static final String MEDIAFORMATS_PARAMETER = "formats";

    Map<String, Object> queryParams(final MediaConversionCronJobModel cronJob)
    {
        final Map<String, Object> params = new TreeMap<String, Object>();
        if (cronJob.getIncludedFormats() != null && !cronJob.getIncludedFormats().isEmpty())
        {
            params.put(MEDIAFORMATS_PARAMETER, cronJob.getIncludedFormats());
        }
        if (cronJob.getCatalogVersion() != null)
        {
            params.put(CATALOGVERSION_PARAMETER, cronJob.getCatalogVersion());
        }
        return params;
    }

    @Override
    public Collection<List<PK>> queryFormatsPerContainerToConvert(final MediaConversionCronJobModel cronJob)
    {
        final Map<String, Object> params = queryParams(cronJob);
        // params are set by super class
        @SuppressWarnings("deprecation")
        final FlexibleSearchQuery query = new FlexibleSearchQuery( //

//                "SELECT un.container, un.format " + //
//                        "FROM ( " + //

                        // medias that are missing:
//                        "{{" +
                                "SELECT sub.container, sub.format FROM ({{" + //
                        "SELECT {a." + MediaContainerModel.PK + "} AS container, " + //
                        "{tf." +  LinkModel.TARGET + "} AS format, " + //
                        "{m." + MediaModel.PK + "} AS media " + //
                        "FROM {" + MediaContainerModel._TYPECODE + " AS a " + //
                        "JOIN " + MediaConversionConstants.Relations.CONVERSIONGROUPTOFORMATREL + " AS tf " + //
                        "ON {a." + MediaContainerModel.CONVERSIONGROUP + "} = {tf." + LinkModel.SOURCE + "} " + //
                        "LEFT JOIN " + MediaModel._TYPECODE + " AS m " + //
                        "ON {m." + MediaModel.MEDIACONTAINER + "} = {a." + MediaContainerModel.PK + "} " + //
                        "AND {m." + MediaModel.MEDIAFORMAT + "} = {tf." + LinkModel.TARGET + "} " + //

                        "} " + //
                        "WHERE 1 = 1 " + //

                        // options
                        (params.containsKey(MEDIAFORMATS_PARAMETER) ? //
                                "AND {tf." + LinkModel.TARGET + "} IN " + //
                                        "(?" + MEDIAFORMATS_PARAMETER + ") "
                                : "") + //
                        (params.containsKey(CATALOGVERSION_PARAMETER) ? //
                                "AND {a." + MediaContainerModel.CATALOGVERSION + "} = " + //
                                        "?" + CATALOGVERSION_PARAMETER + " "
                                : "") + //

                        "}}) AS sub " + //
                        "WHERE sub.media IS NULL "
//                +

//                        "}} " + //
//                        "UNION ALL " + //
//                        "{{" + //
//                        this.outdatedMediaQuery(params) + "}} " + //
//                        ") as un " + //
//                        "ORDER BY un.container", //

                ,params);
        query.setResultClassList(Arrays.asList(new Class[]
                {PK.class, PK.class}));
        return this.getFlexibleSearchService().<List<PK>> search(query).getResult();
    }

    private String outdatedMediaQuery(final Map<String, Object> params)
    {
        return
                // medias that needs to be updated:
                "SELECT {ua." + MediaContainerModel.PK + "} AS container, "
                        + //
                        "{cm." + MediaModel.MEDIAFORMAT + "} AS format "
                        + //
                        "FROM {" + MediaContainerModel._TYPECODE + " AS ua "
                        + //
                        "JOIN " + MediaModel._TYPECODE + " as cm "
                        + //
                        "ON {cm." + MediaModel.MEDIACONTAINER + "} = {ua." + MediaContainerModel.PK
                        + "} "
                        + //
                        // must be a ConversionMediaFormat otherwise it's up-to-date
                        "JOIN " + ConversionMediaFormatModel._TYPECODE + " as cmf "
                        + //
                        "ON {cm." + MediaModel.MEDIAFORMAT + "} = {cmf." + ConversionMediaFormatModel.PK + "} "
                        + //
                        "LEFT JOIN " + MediaModel._TYPECODE + " as om "
                        + //
                        "ON {om." + MediaModel.MEDIACONTAINER + "} = {ua." + MediaContainerModel.PK + "} "
                        + //
                        "AND {om." + MediaModel.PK + "} = {cm." + MediaModel.ORIGINAL + "} "
                        + //
                        "} "
                        + //
                        "WHERE {cm." + MediaModel.ORIGINALDATAPK + "} IS NOT NULL "
                        + // is no original data pk present it was never converted
                        "AND ({om." + MediaModel.DATAPK + "} IS NULL OR {cm." + MediaModel.ORIGINALDATAPK + "} <> {om." + MediaModel.DATAPK
                        + "}) " + // om.dataPK might be null as we are outer joining

                        // options
                        (params.containsKey(MEDIAFORMATS_PARAMETER) ? //
                                "AND {cm." + MediaModel.MEDIAFORMAT + "} IN " + //
                                        "(?" + MEDIAFORMATS_PARAMETER + ") "
                                : "") + //
                        (params.containsKey(CATALOGVERSION_PARAMETER) ? //
                                "AND {ua." + MediaContainerModel.CATALOGVERSION + "} = " + //
                                        "?" + CATALOGVERSION_PARAMETER + " "
                                : "");
    }

 }
