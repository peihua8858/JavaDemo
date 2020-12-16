package com.fz.demo.excel;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelTest {
    public static void main(String[] args) {
//        String excelFilePath = "C:\\WorkSpace\\Project\\Android\\Demo\\xml-data1.xlsx";
// "E:\\Workspace\\Java\\JavaTest\\src\\com\\fz\\demo\\excel\\移动组的日志系统1.xlsx";
//        System.out.println("ToolsSettings>>" + excelFilePath);
//        File excelFile = new File(excelFilePath);
//        if (!excelFile.exists()) {
//            System.out.println("文件路径" + excelFilePath + "不存在！");
//            return;
//        }
//        String extension = FilenameUtils.getExtension(excelFile.getName());
//        System.out.println("ToolsSettings>>extension:" + extension);
//        Workbook wb = null;
//        //根据文件后缀（xls/xlsx）进行判断
//        try {
//            if ("xls".equals(extension)) {
//                wb = new HSSFWorkbook(new FileInputStream(excelFile));
//            } else if ("xlsx".equals(extension)) {
//                wb = new XSSFWorkbook(excelFile);
//            } else {
//                System.out.println("文件类型错误");
//                return;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("出现错误:" + e.getMessage());
//        }
//        if (wb == null) {
//            return;
//        }
//        String sheet1 = "Sheet1";
//        System.out.println("ToolsSettings>>sheet1" + sheet1);
//        if (StringUtils.isEmpty(sheet1)) {
//            sheet1 = "Sheet1";
//        }
//        Workbook finalWb = wb;
//        String finalSheet = sheet1;
//        Sheet sheet = finalWb.getSheet(finalSheet);
//        File rootDir = new File("C:\\WorkSpace\\Project\\Android\\Zaful\\");
        //强制替换，不显示对比列表
//        Map<String, List<ElementBean>> excelDatas = ExcelUtil.parseExcelForMap2(sheet);
//        TreeNode treeNode = XmlUtil.createTreeNode(rootDir, excelDatas);
//        System.out.println(XmlUtil.createXml(excelDatas.get("en"),false));
//        XmlUtil.forceReplace(excelDatas, rootDir, Configs.PROJECT_APP_FOLDER);
//        Map<String, List<MultiLanguageBean>> datas = XmlUtil.uploadMultiLanguage(rootDir, null);
//        File outputDir = new File("E:\\Temp\\");
//        if (outputDir.isDirectory()) {
//            String dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date());
//            outputDir = new File(outputDir, "strings-" + dateFormat + ".xls");
//        }
//        ExcelUtil.generateExcelFile(outputDir, datas);
        File rootDir = new File("C:\\WorkSpace\\Project\\Zaful-Android");
        List<String> moduleFiles = FileUtils.scanFolder(rootDir, Configs.PROJECT_MODULE_SRC_FOLDER,
                "**/.*/**,**/*build*/**");
//        Map<String, List<ElementBean>> excelDatas = ExcelUtil.parseExcelForMap2(sheet);
        String moduleFolderPath = moduleFiles.get(0);
        final File file = new File(rootDir, moduleFolderPath);
        final File parentFile = file.getParentFile();
        moduleFolderPath = parentFile.getAbsolutePath();
//        File moduleFile;
//        if (StringUtils.isNotEmpty(moduleFolderPath)) {
//            moduleFile = new File(moduleFolderPath);
//        } else {
//            moduleFile = new File(rootDir, Configs.DEFAULT_PROJECT_MAIN_FOLDER);
//        }
//        if (false) {
//            //强制替换，不显示对比列表
//            XmlUtil.forceReplace(excelDatas, true, moduleFile);
//        } else {
//            TreeNode treeNodes = XmlUtil.createTreeNode(moduleFile, excelDatas);
//            int count = treeNodes.getChildCount();
//            for (int i = 0; i < count; i++) {
//                final TreeNode treeNode = treeNodes.getChildAt(i);
//                DefaultMutableTreeNode node= (DefaultMutableTreeNode) treeNode;
//                Object object = node.getUserObject();
//                if (object instanceof TreeModelBean) {
//                    System.out.println("ToolsSettings>>treeNode" + ((TreeModelBean) object).getFile()
//                    .getAbsolutePath());
//                }
//            }
//        }
        File moduleFile;
        if (StringUtils.isNotEmpty(moduleFolderPath)) {
            moduleFile = new File(moduleFolderPath);
        } else {
            moduleFile = new File(rootDir, Configs.PROJECT_APP_FOLDER);
        }
        Map<String, List<MultiLanguageBean>> languages = XmlUtil.paresXmlMultiLanguage(moduleFile,true);
        File outputFile = new File("C:\\WorkSpace\\Project\\Android\\HHH");
        if (outputFile.isDirectory()) {
            String dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date());
            outputFile = new File(outputFile, "Zaful-" + dateFormat + ".xls");
        }
        ExcelUtil.generateExcelFile(outputFile, languages);
    }
}
