package nc.ui.mapub.costtype.validator;

import nc.bs.uif2.validation.ValidationException;
import nc.ui.uif2.model.DefaultBatchValidationService;

/**
 * ����ǰУ��
 * Ҫ�������϶��ձ����Դ�����ֶ�У�飺
 * 1.���ϻ�����Ϣ�����ϻ������ࡢ���ϳɱ����ࡢ�������ͺͳɱ�������ѡ�ࣻ
 * 2.���ϻ�����Ϣ�����ϻ������ࡢ���ϳɱ������ѡ��һ��
 * 3.���ϻ������ࡢ���ϳɱ����໥�⡣
 * 
 * @since 6.36
 * @version 2014-12-16 ����3:01:41
 * @author zhangshyb
 */
public class CostTypeBatchSaveValidateSerice extends DefaultBatchValidationService {

    @Override
    public void validate(Object obj) throws ValidationException {
        for (int i = 0; i < 100; i++) {

        }
    }
}
