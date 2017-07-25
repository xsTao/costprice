package nc.bs.mapub.driver.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 检查编码唯一性
 *
 * @since 6.0
 * @version 2012-9-4 上午09:37:17
 * @author liyjf
 */
@SuppressWarnings("rawtypes")
public class CheckCodeUniqueRule implements IRule {

    @Override
    public void process(Object[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }
        String pk_org = ((DriverVO) ((DriverAggVO) vos[0]).getParent()).getPk_org();
        List<String> codeList = new ArrayList<String>();
        List<String> idList = new ArrayList<String>();
        for (Object vo : vos) {
            String code = ((DriverVO) ((DriverAggVO) vo).getParent()).getVcode();
            if (codeList.contains(code)) {
                ExceptionUtils.wrappBusinessException(CMDriverLangConst.getERR_SAME_CODE());
            }
            codeList.add(code);
            String id = ((DriverVO) ((DriverAggVO) vo).getParent()).getCdriverid();
            if (CMStringUtil.isNotEmpty(id)) {
                idList.add(id);
            }
        }

        CMSqlBuilder sql = new CMSqlBuilder();
        sql.and();
        sql.l();
        sql.append(DriverVO.PK_ORG, pk_org);
        sql.or();
        sql.append(DriverVO.PK_GROUP, CMDriverLangConst.GLFLAG);
        sql.r();
        sql.and();
        sql.append(DriverVO.VCODE, codeList);
        sql.appendDr();
        if (idList.size() > 0) {
            sql.and();
            sql.appendNot(DriverVO.CDRIVERID, idList.toArray(new String[idList.size()]));
        }

        DriverVO[] driverVOs = new VOQuery<DriverVO>(DriverVO.class).query(sql.toString(), null);
        if (CMArrayUtil.isNotEmpty(driverVOs)) {
            ExceptionUtils.wrappBusinessException(CMDriverLangConst.getERRO_DRIVERCODE_EXIT());
        }
    }
}
