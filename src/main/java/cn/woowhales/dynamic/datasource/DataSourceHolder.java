package cn.woowhales.dynamic.datasource;

import cn.woowhales.dynamic.enums.DataSourceEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 存放线程数据源
 * @author woodwhales
 */
@Slf4j
public class DataSourceHolder {

    private static final ThreadLocal<DataSourceEnum> threadLocal = new ThreadLocal<>();

    public static void setDataSource(DataSourceEnum key){
        threadLocal.set(key);
    }

    public static void loadDataSource(String dataSourceKey) {
        DataSourceEnum dataSourceEnum = DataSourceEnum.getDataSourceEnum(dataSourceKey);
        if(Objects.isNull(dataSourceEnum)) {
            log.error("数据源切换失败");
            throw new RuntimeException("数据源切换失败");
        }
        setDataSource(dataSourceEnum);
    }

    public static DataSourceEnum getDataSource() {
        return threadLocal.get();
    }

    public static void cleanDataSource(){
        threadLocal.remove();
    }
}
