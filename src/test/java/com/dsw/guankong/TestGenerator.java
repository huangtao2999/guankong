package com.dsw.guankong;

import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试mybatis-generate自定义
 *
 * @author huangt
 * @create 2018-01-07 11:05
 **/
public class TestGenerator {

    private File configFile;

    @Before
    public void before() {
        //读取mybatis参数
        configFile = new File("D:\\dsw_java_workspaces\\guankong\\src\\main\\resources\\generatorConfig.xml");
    }

    @Test
    public void test() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}