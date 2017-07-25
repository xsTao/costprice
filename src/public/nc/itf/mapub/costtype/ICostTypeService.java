package nc.itf.mapub.costtype;

import java.util.Set;

import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.uif2.LoginContext;

/**
 * 成本类型服务接口
 * <p>
 * 成本类型服务接口
 */
public interface ICostTypeService {

    /**
     * 根据是否有效获取成本类型数据
     *
     * @param pk_group
     *            集团
     * @param pk_org
     *            组织
     * @param currentDate
     *            当前日期
     * @param isLegal
     *            是否有效 true：有效 false：无效
     * @return 成本类型数据
     */
    ISuperVO[] queryCostType(LoginContext context, UFDate currentDate, UFBoolean isLegal) throws BusinessException;

    /**
     * 根据是否有效获取成本类型数据
     *
     * @param pk_group
     *            集团
     * @param pk_org
     *            组织
     * @param date
     *            前台客户端日期
     * @param isLegal
     *            是否有效 true：有效 false：无效
     * @return 成本类型数据
     */
    ISuperVO[] queryCostTypeByDate(LoginContext context, UFDate date, UFBoolean isLegal);

    BatchOperateVO batchSaveCostTypeVos(BatchOperateVO batchVO) throws Exception;

    ISuperVO[] queryCostTypeByDataVisibilitySetting(LoginContext context) throws Exception;

    /**
     * selectByWhereSql
     *
     * @param whereSql
     *            sql
     * @param clz
     *            clz
     * @return data
     * @throws Exception
     *             Exception
     */
    ISuperVO[] queryCostTypeVOsByWhereSql(String whereSql) throws Exception;

    // 判断价格库是否被引用：引用返回true，未被引用返回false
    public boolean isReferenced(String[] pks);

    /**
     * 判断该成本类型下是否存在bom数据
     */
    public Set<String> haveDate();

    /**
     * 判断该成本类型下是否存在bom数据且已计算的数据
     */
    public Set<String> haveCalcDate();

    /**
     * 判断材料价格来源是否修改
     */
    public boolean isMaterialSrcChanged(String ccosttypeid, String scrapfactor, String shrinkfactor,
            String pk_elementsystem, String pk_factorchart, String pk_materialdocview, String vmaterialpricesourcenum);

    /**
     * 考虑废品系数scrapfactor、考虑损耗系数shrinkfactor、核算要素体系pk_elementsystem、核算要素表pk_factorchart、要素与物料对照表pk_materialdocview是否被修改
     */
    public boolean isValueChanged(String ccosttypeid, String scrapfactor, String shrinkfactor, String pk_elementsystem,
            String pk_factorchart, String pk_materialdocview);
}
