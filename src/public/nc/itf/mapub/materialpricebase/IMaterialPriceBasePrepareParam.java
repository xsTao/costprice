package nc.itf.mapub.materialpricebase;

/**
 * <b> 在价格库中查询子表价格时的数据准备接口 </b>
 * <p>
 * 需要返回表名和SQL语句中需要查询的Fields
 * </p>
 * 
 * @since 6.0
 * @version 2014-9-23 下午2:30:07
 * @author baoxina
 */
public interface IMaterialPriceBasePrepareParam {

    /**
     * 返回表名
     * 
     * @return 表名
     */
    String getTableName();

    /**
     * 返回查询的域，注意String[]最后一项必须是价格/费率
     * 
     * @return 属性域
     */
    String[] getFieldsForSQL();
}
