/**
 *
 */
package nc.bs.mapub.costtype.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.mapub.costtype.ICostTypeService;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015年5月18日 上午9:20:59
 * @author zhangshyb
 */
public class CostTypeEditableValidateRule implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        try {
            if (CMArrayUtil.isNotEmpty(vos)) {
                this.editableValidate(vos);
            }
        }
        catch (ValidationException e) {
            ExceptionUtils.wrappException(e);
        }
    }

    /**
     * 字段可编辑校验
     * 1.存在成本BOM数据的成本类型，只允许修改成本类型编码、成本类型名称、物料价格来源、生效日期、失效日期和备注，其他字段不允许编辑；
     * 2.存在成本BOM数据的成本类型，且成本BOM存在已计算的，不允许修改物料价格来源，否则可以修改；
     */
    public void editableValidate(CostTypeVO[] updateCostTypeVOs) throws ValidationException {
        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        StringBuffer errStr = new StringBuffer();
        StringBuffer errStr0 = new StringBuffer();
        StringBuffer errStr1 = new StringBuffer();
        ICostTypeService service = NCLocator.getInstance().lookup(ICostTypeService.class);
        // 存在bom数据但未计算
        Set<String> data = new HashSet<String>();
        // 存在bom数据且已经计算
        Set<String> calcDate = new HashSet<String>();
        // 初始化
        data = service.haveDate();
        calcDate = service.haveCalcDate();
        // 判空
        for (CostTypeVO costTypeVO : updateCostTypeVOs) {
            String ccosttypeid = costTypeVO.getCcosttypeid();
            String vmaterialpricesourcenum = costTypeVO.getVmaterialpricesourcenum();
            String scrapfactor = costTypeVO.getBscrapfactor().toString();
            String shrinkfactor = costTypeVO.getBshrinkfactor().toString();
            String pk_elementsystem = costTypeVO.getPk_elementsystem();
            String pk_factorchart = costTypeVO.getPk_factorchart();
            String pk_materialdocview = costTypeVO.getPk_materialdocview();
            // 存在已计算bom数据
            if (calcDate != null && calcDate.contains(ccosttypeid)) {
                if (service.isMaterialSrcChanged(ccosttypeid, scrapfactor, shrinkfactor, pk_elementsystem,
                        pk_factorchart, pk_materialdocview, vmaterialpricesourcenum)) {
                    // 编码[名称]
                    errStr1.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                    errStr1.append(",");
                }
            }
            else {
                // 存在bom数据
                if (data != null && data.contains(ccosttypeid)) {
                    if (service.isValueChanged(ccosttypeid, scrapfactor, shrinkfactor, pk_elementsystem,
                            pk_factorchart, pk_materialdocview)) {
                        // 编码[名称]
                        errStr0.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                        errStr0.append(",");
                    }
                }
            }
        }
        if (errStr0.length() > 0 || errStr1.length() > 0) {
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0301")/*
             * 存在不合法编辑！\
             * n
             */);
        }
        if (errStr0.length() > 0) {
            errStr0.deleteCharAt(errStr0.length() - 1); // 要去掉最后一个","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0302")/*
             * 存在成本BOM数据的成本类型
             * ，
             * 只允许修改成本类型编码
             * 、成本类型名称、
             * 材料价格来源
             * 、生效日期
             * 、失效日期和备注
             * ，
             * 其他字段不允许编辑
             * :
             */);
            errStr.append(errStr0.toString());
            errStr.append("\n");
        }
        if (errStr1.length() > 0) {
            errStr1.deleteCharAt(errStr1.length() - 1); // 要去掉最后一个","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0303")/*
                                                                                                              * 存在成本BOM数据的成本类型
                                                                                                              * ，
                                                                                                              * 且成本BOM已计算的
                                                                                                              * ，
                                                                                                              * 只允许修改成本类型编码
                                                                                                              * 、
                                                                                                              * 成本类型名称、生效日期
                                                                                                              * 、
                                                                                                              * 失效日期和备注，
                                                                                                              * 其他字段不允许编辑
                                                                                                              * :
                                                                                                              */);
            errStr.append(errStr1.toString());
            errStr.append("\n");
        }
        if (errStr.length() > 0) {
            // 返回完整的错误警告信息,创建校验警告
            failure.add(new ValidationFailure(errStr.toString()));
            throw new ValidationException(failure); // 校验警告输出
        }
    }

}
