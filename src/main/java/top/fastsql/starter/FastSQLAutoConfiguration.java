package top.fastsql.starter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.fastsql.SQLFactory;
import top.fastsql.config.DataSourceType;

import javax.sql.DataSource;

/**
 * @author 陈佳志
 */
@Configuration
@EnableConfigurationProperties(value = {FastSQLConfig.class})
@ServletComponentScan
public class FastSQLAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(FastSQLAutoConfiguration.class);


    @ConditionalOnMissingBean(value = SQLFactory.class)
    @Bean
    public SQLFactory sqlFactory(FastSQLConfig fastSQLConfig, DataSource dataSource) {
        logger.info("create {SQLFactory} bean.");

        SQLFactory sqlFactory = new SQLFactory();
        sqlFactory.setDataSource(dataSource);
        DataSourceType dataSourceType = DataSourceType.getByCode(fastSQLConfig.getDataSourceType());
        if (dataSourceType == null) {
            throw new IllegalArgumentException("DataSourceType仅支持mysql/oracle/postgresql");
        }
        sqlFactory.setDataSourceType(dataSourceType);

        return sqlFactory;
    }
}
