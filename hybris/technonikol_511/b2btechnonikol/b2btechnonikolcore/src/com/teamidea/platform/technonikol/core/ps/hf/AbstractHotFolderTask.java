package com.teamidea.platform.technonikol.core.ps.hf;

import org.springframework.transaction.support.TransactionTemplate;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public abstract class AbstractHotFolderTask {

    private static final Pattern FILENAME_MATCHER= Pattern.compile("(\\D+?)(\\d+?)_(\\d+?)-(\\d+?).(\\w+)");

    private HotFolderPackageService hotFolderPackageService;
    private TransactionTemplate transactionTemplate;

    public HotFolderPackageService getHotFolderPackageService() {
        return hotFolderPackageService;
    }

    public void setHotFolderPackageService(HotFolderPackageService hotFolderPackageService) {
        this.hotFolderPackageService = hotFolderPackageService;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public HotFolderPackageMessage createMessage(Path filePath){
        return new HotFolderPackageMessage(filePath);
    }

    class HotFolderPackageMessage{
        private final String packageId;
        private final Path currentPath;
        private final String packageType;
        private final int sequenceNumber;
        private final int fileNumbers;
        private boolean isError = false;

        HotFolderPackageMessage(Path currentPath) {
            Matcher matcher = Pattern.compile("(\\D+?)(\\d+?)_(\\d+?)-(\\d+?).(\\w+)").matcher(currentPath.getFileName().toString());
            if(!matcher.matches()){
                throw new IllegalArgumentException("Cannot match currentPath with: "+currentPath+" pattern: "+matcher.pattern().toString());
            }
            this.currentPath = currentPath;
            this.packageId = matcher.group(2);
            this.packageType = matcher.group(1);
            this.sequenceNumber = Integer.valueOf(matcher.group(3));
            this.fileNumbers = Integer.valueOf(matcher.group(4));
        }

        public String getPackageId() {
            return packageId;
        }

        public Path getCurrentPath() {
            return currentPath;
        }

        public String getPackageType() {
            return packageType;
        }

        public int getSequenceNumber() {
            return sequenceNumber;
        }

        public int getFileNumbers() {
            return fileNumbers;
        }

        public boolean isError() {
            return isError;
        }

        public void setError(boolean isError) {
            this.isError = isError;
        }
    }
}
