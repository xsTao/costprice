/**
 *
 */
package nc.bs.mapub.costtype.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015��6��18�� ����3:52:28
 * @author zhangshyb
 */
public class IsReferencedCheckRule implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        // У�飺Ҫ�������϶��ձ���Ҫ������Ӧ��Ҫ�ر��뵥����Ҫ�ر���һ��
        try {
            if (CMArrayUtil.isNotEmpty(vos)) {
                // ���Ƚ�������У�飬�ж��Ƿ� ʧЧ�ڼ� >= ��Ч�ڼ� ����������¼2010-04-15�޸�
                this.isReferencedCheck(vos);
            }
        }
        catch (ValidationException e) {
            ExceptionUtils.wrappException(e);
        }
    }

    /**
     * ɾ������ʱ��������У�飬�ж��Ƿ�ҵ�������������
     *
     * @param costTypeVOs
     *            CostTypeVO[]
     * @throws ValidationException
     *             �쳣
     */
    public void isReferencedCheck(CostTypeVO[] vos) throws ValidationException {

        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        List<String> ids = new ArrayList<String>();
        // ����У�飬ֻҪ�����д���ģ��������������
        for (CostTypeVO vo : vos) {
            ids.add(vo.getCcosttypeid());
        }
        if (this.isReferenced(ids.toArray(new String[ids.size()]))) {
            // ���������Ĵ��󾯸���Ϣ,����У�龯��
            failure.add(new ValidationFailure(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_0",
                    "0101436401-0551")/*
                     * @res
                     * "�����ѱ����ã�����ɾ����"
                     */
                    ));
            throw new ValidationException(failure); // У�龯�����

        }
    }

    private boolean isReferenced(String[] ids) {
        CMSqlBuilder sqlBuilder = new CMSqlBuilder();
        sqlBuilder.append("SELECT count(1)");
        sqlBuilder.append(" FROM PUB_SYSINIT ");
        sqlBuilder.where();
        sqlBuilder.append("value", ids); // �ɱ���
        sqlBuilder.appendDr();

        DataAccessUtils bo = new DataAccessUtils();
        IRowSet rowset = bo.query(sqlBuilder.toString());
        String[] values = rowset.toOneDimensionStringArray();
        if (Integer.valueOf(values[0]) > 0) {
            return true;
        }
        return false;

    }

}
