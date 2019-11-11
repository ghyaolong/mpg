package com.yao.mpg.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Data
@Slf4j
public class CodeGeneratorTwo {


//    @Autowired
//    private CodeTwoGeneratorConfig codeTwoGeneratorConfig;

    public static void main(String[] args) throws IOException {
        CodeGeneratorTwo t = new CodeGeneratorTwo();
        t.generateCode();
    }

    private final String path = System.getProperty("user.dir");

    public void generateCode() throws IOException {

//        String author = codeTwoGeneratorConfig.getAuthor();
//        String packagePath = codeTwoGeneratorConfig.getPackagePath();
//        String[] tables = codeTwoGeneratorConfig.getTables().split(",");

        String author = "tutuut";
        String packagePath = "com.yao.mpg";
        String[] tables = "t_user,t_role".split(",");

        //获取数据库连接
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://localhost:3306/cube_mall";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.POSTGRE_SQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("root")
                .setDriverName("com.mysql.jdbc.Driver");

//        //类型转换
//        dataSourceConfig.setTypeConvert(new PostgreSqlTypeConvert() {
//            @Override
//            public PropertyInfo processTypeConvert(GlobalConfig globalConfig, String fieldType) {
//                System.out.println("转换类型：" + fieldType);
//                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
//                if (fieldType.contains("numeric")) {
//                    return DbColumnType.DOUBLE;
//                } else {
//                    return super.processTypeConvert(globalConfig, fieldType);
//                }
//            }
//        });

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                //.setLogicDeleteFieldName("rec_status")
                .setEntityLombokModel(true)
                .setNaming(NamingStrategy.underline_to_camel)
//                .setTablePrefix("eff_","ent_i_","ent_s_","ent_r_","eqp_i_","eqp_r_","eqp_s_","prod_i_","prod_r_","prod_s_","sys_i_")
                .setInclude(tables)//修改替换成你需要的表名，多个表名传数组
                .setEntityLombokModel(true);//是否使用lombok
        config.setActiveRecord(false)
                .setAuthor(author)
                .setOutputDir("src/main/java")
                .setEnableCache(false)
                .setBaseColumnList(false)
                .setBaseColumnList(false)
                .setIdType(IdType.UUID)//主键类型
                .setFileOverride(true)
                .setServiceName("%sService");
                //.setSwagger2(true);  //是否使用Swagger

        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() {
                this.setMap(getMap());
            }
        };
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null)
                .setServiceImpl(null)
                .setController("template/comtroller.java.vm")
                .setEntity("template/entity.java.vm")
                .setService("mytemplate/serviceImpl.java.vm");

        //添加add 请求对象
        List<FileOutConfig> foc = new ArrayList<>();
//        foc.add(new FileOutConfig("/template/addVo.java.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return path + "/src/main/java/com/poi/code/creator/vo/req/" + "Add" + tableInfo.getEntityName() + "Req"
//                        + StringPool.DOT_JAVA;
//            }
//        });
//        //添加update 请求对象
//        foc.add(new FileOutConfig("/template/updateVo.java.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return path + "/src/main/java/com/poi/code/creator/vo/req/" + "Update" + tableInfo.getEntityName() + "Req"
//                        + StringPool.DOT_JAVA;
//            }
//        });
//        //添加list请求对象
//        foc.add(new FileOutConfig("/template/listVo.java.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return path + "/src/main/java/com/poi/code/creator/vo/req/" + "List" + tableInfo.getEntityName() + "Req"
//                        + StringPool.DOT_JAVA;
//            }
//        });
//        //添加分页请求对象
//        foc.add(new FileOutConfig("/template/pageVo.java.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return path + "/src/main/java/com/poi/code/creator/vo/req/" + "Page" + tableInfo.getEntityName() + "Req"
//                        + StringPool.DOT_JAVA;
//            }
//        });
        ic.setFileOutConfigList(foc);
        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setTemplate(tc)
                .setCfg(ic)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packagePath)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }
}