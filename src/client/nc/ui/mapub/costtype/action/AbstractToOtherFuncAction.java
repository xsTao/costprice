package nc.ui.mapub.costtype.action;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.sm.funcreg.FuncRegisterVO;

/**
 * �������ܴ������ڵ�
 * 
 * @since 6.0
 * @version 2011-9-26 ����09:25:09
 * @author liwzh
 */
public abstract class AbstractToOtherFuncAction extends NCAction {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -606584191880391001L;

    /**
     * MMBillManageModel
     */
    private AbstractAppModel model = null;

    public AbstractAppModel getModel() {
        return this.model;
    }

    public void setModel(AbstractAppModel model) {
        this.model = model;
    }

    /**
     * �򿪽ڵ�
     * 
     * @param funCode �ڵ��
     * @param key ��ʱ�ĳ�ʼ������--�����������ַ�����������ʱ��null����
     * @param data Object ���ݿ����кܶ��֣���������ʱ��Ϊnull����vo����vo�������飬�ۺ�vo���󣬾ۺ�vo���ݣ�
     */
    public void openFuncNode(String funCode, String key, Object data) {
        FuncletInitData initData = new FuncletInitData();
        if (key != null) {
            initData.setInitType(Integer.valueOf(key).intValue());
        }
        initData.setInitData(data);
        WorkbenchEnvironment instance = WorkbenchEnvironment.getInstance();
        FuncRegisterVO funvo = instance.getFuncRegisterVO(funCode);
        FuncletWindowLauncher.openFuncNodeInTabbedPane(null, funvo, initData, null, true);
    }

    /**
     * add by hupeng �˷���Ϊ�������ݹ���
     * 
     * @param funCode �ڵ��
     */
    public void openFuncNode(String funCode) {
        this.openFuncNode(funCode, null, null);
    }
}
