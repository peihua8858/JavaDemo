package com.fz.demo.plist;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;

import java.io.File;

/**
 * 生成IOS 下载用的plist
 * 文件名规则：app名称+版本号+编译号.plist
 *
 * @author dingpeihua
 * @version 1.0
 * @date 2020/1/16 17:07
 */
public class PlistGenerate {
    /**
     * 创建一个plist文件
     * 文件名规则：app名称+版本号+编译号.plist,如：Zaful_5.6.0_25.plist
     * @param appBean         app 信息
     * @param downloadUrlHost Jenkins 下载地址域名
     * @param plistFilePath   plist文件存储路径
     * @author dingpeihua
     * @date 2020/1/16 17:47
     * @version 1.0
     */
    public static File generatePlist(AppBean appBean, String downloadUrlHost, String plistFilePath) throws Exception {
        NSArray assetsItems = new NSArray(1);
        NSDictionary assetsDic = new NSDictionary();
        assetsDic.put("kind", "software-package");
        //http://10.36.5.84/zaful/zf-v4.5.7.ipa
        assetsDic.put("url", downloadUrlHost + appBean.getDownloadUrl());
        assetsItems.setValue(0, assetsDic);

        NSDictionary metadataDic = new NSDictionary();
        metadataDic.put("bundle-identifier", appBean.getBundleId());
        metadataDic.put("bundle-version", appBean.getVersionName());
        metadataDic.put("kind", "software");
        metadataDic.put("title", appBean.getName());

        NSDictionary itemsDic = new NSDictionary();
        itemsDic.put("assets", assetsItems);
        itemsDic.put("metadata", metadataDic);

        NSArray items = new NSArray(1);
        items.setValue(0, itemsDic);
        NSDictionary root = new NSDictionary();
        root.put("items", items);
        File parentFile = createFolder(plistFilePath, appBean.getProjectName());
        //文件名规则：app名称+版本号+编译号.plist,如：Zaful_5.6.0_25.plist
        String plistFile = replaceName(appBean.getName()) + "_" + appBean.getVersionName() + "_" + appBean.getBuildNumber() + ".plist";
        System.out.println("Plugin>>plistFileName: " + plistFile);
        System.out.println("Plugin>>plistParentFile: " + parentFile.getAbsolutePath());
        File file = new File(parentFile, plistFile);
        System.out.println("Plugin>>plistFile: " + file);
        PropertyListParser.saveAsXML(root, file);
        return file;
    }

    /**
     * 根据项目名创建一个父级文件夹
     *
     * @param parentFile  父文件夹路径
     * @param projectName 当前项目名称
     * @author dingpeihua
     * @date 2020/1/19 9:33
     * @version 1.0
     */
    static File createFolder(String parentFile, String projectName) {
        File file = new File(parentFile, projectName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    static String replaceName(String name) {
        return name.replaceAll(" ", "_");
    }
}
