package nc.bs.mapub.allocfac.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 分配系数编码相同校验
 */
public class AllocfacSaveRule implements IRule<AllocfacAggVO> {

    @Override
    public void process(AllocfacAggVO[] vos) {
        if (vos == null || vos.length == 0) {
            return;
        }
        // 集团
        String pk_group = vos[0].getParentVO().getPk_group();
        // 工厂
        String pk_org = vos[0].getParentVO().getPk_org();
        String code = vos[0].getParentVO().getVcode();
        List<ValidationFailure> errorList = new ArrayList<ValidationFailure>();
        try {
            UFBoolean isSame = this.isSameCode(pk_group, pk_org, code, vos[0].getParentVO().getPrimaryKey());
            if (isSame.booleanValue()) {
                String allocfacCodeMsg =
                        CMDriverLangConst.getALLOCFAC() + CMDriverLangConst.getCODE()
                        + CMDriverLangConst.FORMULA_SPLIT_START + code + CMDriverLangConst.FORMULA_SPLIT_END
                        + CMDriverLangConst.getEXIST();
                errorList.add(new ValidationFailure(allocfacCodeMsg));
            }
        }
        catch (DAOException e) {
            ExceptionUtils.wrappException(e);
        }
        if (errorList.size() > 0) {
            this.errorMessageProcess(errorList);
        }
    }

    /**
     * 错误信息处理
     *
     * @param errMsgList
     *            错误信息
     */
    private void errorMessageProcess(List<ValidationFailure> errMsgList) {
        StringBuffer strerr = new StringBuffer();
        for (ValidationFailure errmsg : errMsgList) {
            strerr = strerr.append(errmsg.getMessage()).append('\n');
        }
        ExceptionUtils.wrappBusinessException(strerr.toString());
    }

    /**
     * 根据库存组织和集团,编码得到相同的分配系数
     *
     * @param pk_group
     * @param pk_org
     * @param allocfacHeadVO
     * @return
     * @throws DAOException
     */
    @SuppressWarnings("unchecked")
    private UFBoolean isSameCode(String pk_group, String pk_org, String code, String allocfacHeadId)
            throws DAOException {
        CMSqlBuilder sqlBuilder = new CMSqlBuilder();
        sqlBuilder.select();
        sqlBuilder.append("count(t.CALLOCFACID) time ");
        sqlBuilder.from("cm_Allocfac t");
        sqlBuilder.where();
        sqlBuilder.append(" t.dr=0");
        sqlBuilder.append(" and t.pk_group", pk_group);
        sqlBuilder.append(" and t.pk_org", pk_org);
        sqlBuilder.append(" and t.VCODE ", code);
        if (CMStringUtil.isNotEmpty(allocfacHeadId)) {
            sqlBuilder.append(" and t.CALLOCFACID<>'" + allocfacHeadId + "' ");
        }

        BaseDAO dao = new BaseDAO();
        List<Object[]> codelst = (List<Object[]>) dao.executeQuery(sqlBuilder.toString(), new ArrayListProcessor());
        if (codelst.size() > 0) {
            for (Object[] objs : codelst) {
                if (objs != null && objs.length > 0) {
                    Integer time = Integer.parseInt(objs[0].toString());
                    if (time.intValue() > 0) {
                        return UFBoolean.TRUE;
                    }
                }
            }
        }
        return UFBoolean.FALSE;
    }
}
