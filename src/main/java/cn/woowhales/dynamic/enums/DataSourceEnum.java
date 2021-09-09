package cn.woowhales.dynamic.enums;

import org.woodwhales.common.business.DataTool;

import java.util.Map;

/**
 * 数据源别名枚举
 * @author woodwhales
 */
public enum DataSourceEnum {
    /**
     * master数据源
     */
    MASTER,

    /**
     * slave数据源
     */
    SLAVE,
    ;

    private static final Map<String, DataSourceEnum> map = DataTool.enumMap(DataSourceEnum.class);

    public static DataSourceEnum getDataSourceEnum(String dataSourceKey) {
        return map.get(dataSourceKey);
    }
}
