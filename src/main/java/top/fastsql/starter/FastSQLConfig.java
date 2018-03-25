package top.fastsql.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fastsql")
public class FastSQLConfig {

    /**
     * mysql  oracle postgresql
     */
    private String dataSourceType = "mysql";


    public String getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }
}
