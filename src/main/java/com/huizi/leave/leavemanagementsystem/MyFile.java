package com.huizi.leave.leavemanagementsystem;

import java.io.File;

/** 文件管理页面使用的展示对象。 */
public class MyFile {
    private final File file;
    private final String iconUrl;
    private final boolean imageType;
    private final String relativePath;

    public MyFile(File file, String iconUrl, boolean imageType, String relativePath) {
        this.file = file;
        this.iconUrl = iconUrl;
        this.imageType = imageType;
        this.relativePath = relativePath;
    }

    public File getFile() { return file; }
    public String getIconUrl() { return iconUrl; }
    public boolean isImageType() { return imageType; }
    public String getRelativePath() { return relativePath; }
}
