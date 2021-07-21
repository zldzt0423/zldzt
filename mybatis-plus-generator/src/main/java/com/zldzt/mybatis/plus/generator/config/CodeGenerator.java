package com.zldzt.mybatis.plus.generator.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
    /**
     * 生成config bean
     */
    private static ConfigBean getConfigBean(){
        Properties props = new Properties();
        InputStream in =CodeGenerator.class.getClassLoader().getResourceAsStream("generator.properties");
        ConfigBean configBean = new ConfigBean();
        try {
            props.load(in);
            configBean.setDriverClassName(props.getProperty("driverClassName"));
            configBean.setUrl(props.getProperty("url"));
            configBean.setUsername(props.getProperty("username"));
            configBean.setPassword(props.getProperty("password"));
            configBean.setProjectName(props.getProperty("project-name"));
            configBean.setCreateUserName(props.getProperty("create-user-name"));
            configBean.setTableName(props.getProperty("tableName"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configBean;
    }
    /**
     * 开始
     * @param args
     */
    public static void main(String[] args) {
        ConfigBean configBean = getConfigBean();
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/mybatis-plus-generator/src/main/java");
        gc.setAuthor(configBean.getCreateUserName());
        gc.setOpen(false);
        gc.setIdType(IdType.AUTO);
        gc.setFileOverride(true); //是否覆盖已有文件
//         gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(configBean.getUrl());
        dsc.setDriverName(configBean.getDriverClassName());
        dsc.setUsername(configBean.getUsername());
        dsc.setPassword(configBean.getPassword());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(configBean.getProjectName());
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
/*
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/check-service/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        */

        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController(null);  //不生成controller
        templateConfig.setService(null);    //不生成service
        templateConfig.setServiceImpl(null);//不生成serviceImpl
        templateConfig.setXml(null);        //不生成xml
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(configBean.getTableName());
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
