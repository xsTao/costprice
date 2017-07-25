/**
 *
 */
package nc.bs.mapub.costtype.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMCollectionUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.fip.docview.IDocViewListService;
import nc.vo.fip.docview.DocViewListVO;
import nc.vo.fip.docview.SrcDocGroupVO;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015��5��18�� ����9:23:03
 * @author zhangshyb
 */
public class DocViewValidateRule implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        try {
            if (CMArrayUtil.isNotEmpty(vos)) {
                // ������Ҫ�ض��ձ���Դ����У��
                this.docViewValidate(vos);
            }
        }
        catch (ValidationException e) {
            ExceptionUtils.wrappException(e);
        }
    }

    /**
     * ����ǰУ��
     * Ҫ�������϶��ձ����Դ�����ֶ�У�飺
     * 1.���ϻ�����Ϣ�����ϻ������ࡢ���ϳɱ����ࡢ�������ͺͳɱ�������ѡ�ࣻ
     * 2.���ϻ�����Ϣ�����ϻ������ࡢ���ϳɱ������ѡ��һ��
     * 3.���ϻ������ࡢ���ϳɱ����໥�⡣
     */
    public void docViewValidate(CostTypeVO[] costTypeVOs) throws ValidationException {
        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        StringBuffer errStr = new StringBuffer();
        StringBuffer errStr0 = new StringBuffer();
        StringBuffer errStr1 = new StringBuffer();
        StringBuffer errStr2 = new StringBuffer();
        if (CMArrayUtil.isNotEmpty(costTypeVOs)) {
            // ��ѡ�Ļ�������
            Set<String> setEnableSrcdoc = new HashSet<String>();
            setEnableSrcdoc = this.getEnableSrcdocid();
            // ����У�飬ֻҪ�����д���ģ��������������
            for (CostTypeVO costTypeVO : costTypeVOs) {
                // ��ѡ�Ļ�������
                Set<String> setSrcdoc = new HashSet<String>();
                String Pk_materialdocview = costTypeVO.getPk_materialdocview();
                if (CMStringUtil.isNotEmpty(Pk_materialdocview)) {
                    setSrcdoc = this.getSrcdocgroup(costTypeVO.getPk_materialdocview());
                    if (CMCollectionUtil.isEmpty(setSrcdoc)) {
                        errStr1.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                        errStr1.append(",");
                    }
                    else {
                        // У��-��ѡ��
                        if (this.srcdocNotLegal0(setEnableSrcdoc, setSrcdoc)) {
                            // ����[����]
                            errStr0.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                            errStr0.append(",");
                        }
                        // У��-��ѡһ
                        if (this.srcdocNotLegal1(setSrcdoc)) {
                            // ����[����]
                            errStr1.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                            errStr1.append(",");
                        }
                        // У��-����
                        if (this.srcdocNotLegal2(setSrcdoc)) {
                            // ����[����]
                            errStr2.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                            errStr2.append(",");
                        }
                    }
                    // if (this.srcdocNotLegal(setEnableSrcdoc, setSrcdoc)) {
                    // // ����[����]
                    // errStr.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                    // errStr.append(",");
                    // // ExceptionUtils.wrappBusinessException("���룺" + costTypeVO.getVcosttypecode() + "����"
                    // // + costTypeVO.getVcosttypename() + "Ҫ�������϶��ձ��Ϸ���");
                    // }
                }
            }
        }
        if (errStr0.length() > 0 || errStr1.length() > 0 || errStr2.length() > 0) {
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0304")/*
             * Ҫ�������϶��ձ��Ϸ���
             * \n
             */);
        }
        if (errStr0.length() > 0) {
            errStr0.deleteCharAt(errStr0.length() - 1); // Ҫȥ�����һ��","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0305")/*
             * ���ϻ�����Ϣ��
             * ���ϻ�������
             * �����ϳɱ����ࡢ
             * �������ͺͳɱ���������ѡ���
             * :
             */);
            errStr.append(errStr0.toString());
            errStr.append("\n");
        }
        if (errStr1.length() > 0) {
            errStr1.deleteCharAt(errStr1.length() - 1); // Ҫȥ�����һ��","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0306")/*
             * ���ϻ�����Ϣ��
             * ���ϻ������ࡢ
             * ���ϳɱ��������߱�ѡ��һ
             * :
             */);
            errStr.append(errStr1.toString());
            errStr.append("\n");
        }
        if (errStr2.length() > 0) {
            errStr2.deleteCharAt(errStr2.length() - 1); // Ҫȥ�����һ��","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0307")/*
                                                                                                              * ���ϻ������ࡢ
                                                                                                              * ���ϳɱ����໥��
                                                                                                              * :
                                                                                                              */);
            errStr.append(errStr2.toString());
            errStr.append("\n");
        }
        if (errStr.length() > 0) {
            // ���������Ĵ��󾯸���Ϣ,����У�龯��
            failure.add(new ValidationFailure(errStr.toString()));
            throw new ValidationException(failure); // У�龯�����
        }
    }

    public boolean srcdocNotLegal0(Set<String> enableSrcdoc, Set<String> srcdoc) {
        // // ���ȣ�1<= length <=4
        // if (srcdoc.size() > 4) {
        // return true;
        // }
        // ��ѡ��
        for (String docid : srcdoc) {
            if (!enableSrcdoc.contains(docid)) {
                return true;
            }
        }
        return false;
    }

    public boolean srcdocNotLegal1(Set<String> srcdoc) {
        // ����ѡһ
        if (!srcdoc.contains("eae040f4-3c88-413d-abc9-b15774463d46")
                && !srcdoc.contains("c099f7d7-52a9-4b98-bee7-2e3a6c3ea263")
                && !srcdoc.contains("fbb3c484-63bc-4624-9f2e-3627221f0036")) {
            return true;
        }
        return false;
    }

    public boolean srcdocNotLegal2(Set<String> srcdoc) {
        // ����
        if (srcdoc.contains("c099f7d7-52a9-4b98-bee7-2e3a6c3ea263")
                && srcdoc.contains("fbb3c484-63bc-4624-9f2e-3627221f0036")) {
            return true;
        }
        return false;
    }

    /**
     * ��ȡ���п�ѡ����Դ����id
     */
    private Set<String> getEnableSrcdocid() {
        Set<String> setEnableSrcdocid = new HashSet<String>();
        // ��������-���ϻ�����Ϣ
        setEnableSrcdocid.add("eae040f4-3c88-413d-abc9-b15774463d46");
        // ��������-���ϻ�������
        setEnableSrcdocid.add("c099f7d7-52a9-4b98-bee7-2e3a6c3ea263");
        // ��������-���ϳɱ�����
        setEnableSrcdocid.add("fbb3c484-63bc-4624-9f2e-3627221f0036");
        // ��������-��������
        setEnableSrcdocid.add("b0fa41cd-a649-4309-b685-d341a5d1b0ed");
        // ��������-�ɱ�����
        setEnableSrcdocid.add("de9796b5-bccd-42a1-97dd-808847bfddbd");
        return setEnableSrcdocid;
    }

    /**
     * ��ȡָ�����ձ����Դ������������ѡ�������������Set��
     *
     * @param pk_org
     * @return
     */
    private Set<String> getSrcdocgroup(String pk_docviewid) {
        // �洢��ѡ����������Ԫ����ID
        Set<String> setSrcdoc = new HashSet<String>();
        DocViewListVO docViewListVO = this.getDocViewListVO(pk_docviewid);
        // Ҫ�ض��ձ���Դ������Ϣ
        SrcDocGroupVO[] srcDocGroupVOs = docViewListVO.getSrcdocgroup();
        if (CMArrayUtil.isNotEmpty(srcDocGroupVOs)) {
            for (SrcDocGroupVO srcDocGroup : srcDocGroupVOs) {
                setSrcdoc.add(srcDocGroup.getPk_srcdocid());
            }
            return setSrcdoc;
        }
        return null;
    }

    /**
     * ���ݹ�������֯�����õ�Ҫ�ض��ձ��voֵ
     *
     * @param pk_org
     * @return DocViewListVO
     */
    protected DocViewListVO getDocViewListVO(String pk_docviewid) {
        DocViewListVO docViewListVO = null;
        try {
            docViewListVO = NCLocator.getInstance().lookup(IDocViewListService.class).queryByPK(pk_docviewid);
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }

        return docViewListVO;
    }

}
