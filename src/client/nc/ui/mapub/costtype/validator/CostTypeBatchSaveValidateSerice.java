package nc.ui.mapub.costtype.validator;

import nc.bs.uif2.validation.ValidationException;
import nc.ui.uif2.model.DefaultBatchValidationService;

/**
 * 保存前校验
 * 要素与物料对照表的来源档案字段校验：
 * 1.物料基本信息、物料基本分类、物料成本分类、单据类型和成本中心五选多；
 * 2.物料基本信息、物料基本分类、物料成本分类必选其一；
 * 3.物料基本分类、物料成本分类互斥。
 * 
 * @since 6.36
 * @version 2014-12-16 下午3:01:41
 * @author zhangshyb
 */
public class CostTypeBatchSaveValidateSerice extends DefaultBatchValidationService {

    @Override
    public void validate(Object obj) throws ValidationException {
        for (int i = 0; i < 100; i++) {

        }
    }
}
