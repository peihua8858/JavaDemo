package com.fz.demo.plist;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AppBean {
    private int buildNumber;
    private String projectName;
    private String downloadUrl;
    private String fileName;
    private String filePath;
    private long fileSize;
    private String changeLog;
    private long duration;
    private String iconPath;
    /**
     * 项目包名
     */
    private String bundleId;
    private String versionName;
    private String versionCode;
    private String platform;
    private String name;
    private String buildType;
    /**
     * {@link AppPublisher#isUploadApk}为true，或者Jenkins打包未归档，则当前apk文件已经上传到服务器，该参数应该取非1；
     * 当前APP文件没有上传到服务器，则表示是Jenkins目录，应该取1
     */
    private int filePathType;
    private final String pluginVersion = "2.0.0";
    /**
     * IOS plist文件地址
     */
    private String plistUrl;
}
