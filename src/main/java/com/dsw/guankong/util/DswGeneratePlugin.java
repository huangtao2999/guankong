package com.dsw.guankong.util;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.xml.Document;

import java.util.List;

/**
 * myabtis-generate自定义生成
 *
 * @author huangt
 * @create 2018-01-07 10:45
 **/
public class DswGeneratePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> generatedJavaFiles =  introspectedTable.getGeneratedJavaFiles();
//        GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile();
        generatedJavaFiles.stream().filter(generatedJavaFile ->generatedJavaFile.getCompilationUnit().getType().getShortName().endsWith("Mapper")).forEach(generatedJavaFile -> {
            //生成mapperExt
            FullyQualifiedJavaType mapperFullType = generatedJavaFile.getCompilationUnit().getType();
            String mapperName = mapperFullType.getShortName();
            String mapperPackage = mapperFullType.getPackageName();
            Interface mapperExt = new Interface(mapperPackage+"."+mapperName+"Ext");
            mapperExt.setVisibility(JavaVisibility.PUBLIC);
            mapperExt.addSuperInterface(mapperFullType);
            mapperExt.addJavaDocLine("/**");
            mapperExt.addJavaDocLine("* @description ");
            mapperExt.addJavaDocLine("* @author  ");
            mapperExt.addJavaDocLine("*");
            mapperExt.addJavaDocLine("*/");
            GeneratedJavaFile mapperExtFile = new GeneratedJavaFile(mapperExt,generatedJavaFile.getTargetProject());
            generatedJavaFiles.add(mapperExtFile);
            //修改默认mapper 的包名
            generatedJavaFile.getCompilationUnit().getType();


        });
        return generatedJavaFiles;
    }

    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable) {

        return super.contextGenerateAdditionalXmlFiles(introspectedTable);
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }
}
