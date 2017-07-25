package nc.bs.mapub.materialpricebase.rule;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * У��ʧЧʱ���Ƿ���ڻ������Чʱ��
 *
 * @since 6.36
 * @version 2014-11-10 ����2:46:02
 * @author zhangchd
 */
public class CheckDateValidate implements IRule<MaterialPriceBaseAggVO> {

    @Override
    public void process(MaterialPriceBaseAggVO[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.CHECK_NULL_VO());
        }
        this.setDate(vos);
        // �жϣ�ʧЧ�����Ƿ���ڻ��ߵ�����Ч����
        this.dateValidate(vos[0]);
    }

    /**
     * ����ʱ����ʼʱ��Ϊ��yyyy-mm-dd 00:00:00;��ֹʱ��Ϊ��yyyy-mm-dd 23:59:59;
     *
     * @param aggvos
     *            zhangchd
     */
    private void setDate(MaterialPriceBaseAggVO[] aggvos) {
        for (MaterialPriceBaseAggVO aggvo : aggvos) {
            MaterialPriceBaseHeadVO headVO = aggvo.getParentVO();
            UFDate beginDate = headVO.getDbegindate();
            UFDate endDate = headVO.getDenddate();
            // ��ȡ��ǰ��½ʱ��
            UFDate loginDate = AppContext.getInstance().getBusiDate();

            Calendar cBegin = Calendar.getInstance();
            Calendar cEnd = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if (CMValueCheck.isEmpty(beginDate)) {
                cBegin.set(loginDate.getYear(), loginDate.getMonth() - 1, loginDate.getDay(), 0, 0, 0);
                headVO.setDbegindate(new UFDate(cBegin.getTime()));
            }
            if (CMValueCheck.isEmpty(endDate)) {
                cEnd.set(9999, 11, 31, 23, 59, 59);
                headVO.setDenddate(new UFDate(df.format(cEnd.getTime())));
            }
        }
    }

    /**
     * �жϣ�ʧЧ����>=��Ч���ڡ�
     *
     * @param aggvo
     */
    private void dateValidate(MaterialPriceBaseAggVO aggvo) {

        MaterialPriceBaseHeadVO headVO = aggvo.getParentVO();

        UFDate beginDate = headVO.getDbegindate();
        UFDate endDate = headVO.getDenddate();

        if (beginDate != null && endDate != null) {
            if (endDate.before(beginDate)) {
                // ��Ч������ʧЧ���ڵıȽ�
                ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.GET_ERRO_BEGIN_ENDDATE());
            }
        }
    }
}
