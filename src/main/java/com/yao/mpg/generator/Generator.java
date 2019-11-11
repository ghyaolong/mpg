package com.yao.mpg.generator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tututu
 * @description
 * @date 2019/11/10 22:50
 * @email 289911401@qq.com
 * @since V1.0.0
 */
public class Generator {

    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.generateCode();
    }

    public void generateCode(){

        String projectPath = System.getProperty("user.dir");
        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        gc
                .setActiveRecord(false)
                .setAuthor("tututu")
                .setOutputDir(System.getProperty("user.dir")+"/src/main/java")
                .setFileOverride(true)
                .setIdType(IdType.UUID)
                .setServiceName("%sService")
                .setEntityName("%sPo")
                .setBaseResultMap(true)
                .setOpen(false)   //生产完之后是否打开生产文件所在目录  默认true
                .setBaseColumnList(true);

        //2.数据源配置
        DataSourceConfig dsCofnig = new DataSourceConfig();
        dsCofnig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/cube_mall")
                .setUsername("root")
                .setPassword("root")
                .setTypeConvert((x,y) -> {
                        String t = y.toUpperCase();
                        if (t.contains("CHAR")) {
                            return DbColumnType.STRING;
                        } else if (t.contains("DATE") || t.contains("TIMESTAMP")||t.contains("DATETIME")) {
                            return DbColumnType.DATE;
                        } else if (t.contains("NUMBER")) {
                            if (t.matches("NUMBER\\(+\\d\\)")) {
                                return DbColumnType.INTEGER;
                            } else if (t.matches("NUMBER\\(+\\d{2}+\\)")) {
                                return DbColumnType.LONG;
                            }
                            return DbColumnType.INTEGER;
                        } else if (t.contains("FLOAT")) {
                            return DbColumnType.FLOAT;
                        } else if (t.contains("clob")) {
                            return DbColumnType.CLOB;
                        } else if (t.contains("BLOB")) {
                            return DbColumnType.OBJECT;
                        } else if (t.contains("binary")) {
                            return DbColumnType.BYTE_ARRAY;
                        } else if (t.contains("RAW")) {
                            return DbColumnType.BYTE_ARRAY;
                        }
                        return DbColumnType.STRING;

                })
        ;

        //3.策略配置

        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true)
                .setEntityLombokModel(true)
                .setNaming(NamingStrategy.underline_to_camel)
                //.setTablePrefix("t_")
                .setRestControllerStyle(true)
                .setInclude("t_user,t_role".split(","));


        //4.包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.yao.mpg")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller")
                .setEntity("po")
                .setXml("mapper");


        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                //to do noting
//            }
//        };
//
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig() {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/java/" + pkConfig.getModuleName()+"/service/impl"
//                        + "/" + tableInfo.getEntityName() + "ServiceImpl" + ".java";
//            }
//        });
//
//        cfg.setFileOutConfigList(focList);



        TemplateConfig temConfig = new TemplateConfig();
        temConfig.setServiceImpl("mytemplate/serviceImpl.java.vm");
        temConfig.setXml(null);

        //5.整合配置

        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(gc)
                .setDataSource(dsCofnig)
                .setStrategy(stConfig)
                .setTemplate(temConfig)
                .setTemplateEngine(new VelocityTemplateEngine())
                //.setCfg(cfg)
                .setPackageInfo(pkConfig);
        ag.execute();
    }
}
