package com.hybris.ps.hf;

/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public interface Finalizer {
    void finalizeImport(AbstractHotFolderTask.HotFolderPackageMessage object);
}
