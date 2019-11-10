package com.yao.mpg.generator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

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
        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        gc
                .setActiveRecord(false)
                .setAuthor("tututu")
                .setOutputDir(System.getProperty("user.dir")+"/src/main/java")
                .setFileOverride(true)
                .setIdType(IdType.UUID)
                .setServiceName("%sService")
                .setBaseResultMap(true)
                .setBaseColumnList(true);

        //2.数据源配置
        DataSourceConfig dsCofnig = new DataSourceConfig();
        dsCofnig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/cube_mall")
                .setUsername("root")
                .setPassword("root");

        //3.策略配置

        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true)
                .setEntityLombokModel(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("t_")
                .setRestControllerStyle(true)
                .setInclude("t_user","t_role");


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
        InjectionConfig ijc = new InjectionConfig() {
            @Override
            public void initMap() {
                //to do noting
            }
        };

        //5.整合配置

        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(gc)
                .setDataSource(dsCofnig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);
        ag.execute();

    }
}
