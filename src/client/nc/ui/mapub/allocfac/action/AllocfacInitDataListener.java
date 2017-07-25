/**
 *
 */
package nc.ui.mapub.allocfac.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nc.bd.framework.base.CMCollectionUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.itf.pubapp.pub.smart.IQueryBillService;
import nc.ui.mapub.allocfac.model.AllocfacModelDataManage;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.linkoperate.ILinkApproveData;
import nc.ui.pub.linkoperate.ILinkMaintainData;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.PubExceptionHanler;
import nc.ui.pubapp.uif2app.model.pagination.PaginationModelDataManager;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.uif2.IExceptionHandler;
import nc.ui.uif2.IFuncNodeInitDataListener;
import nc.ui.uif2.UIState;
import nc.ui.uif2.components.IAutoShowUpComponent;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.queryscheme.SimpleQuerySchemeVO;
import nc.vo.uif2.LoginContext;

/**
 * @since v6.3
 * @version 2013-1-17 ����10:19:13
 * @author xionghuic
 */
public class AllocfacInitDataListener implements IFuncNodeInitDataListener {

    // ������ô˲��������齫ʹ�������ӿڣ���ʹ���ݵ��ǵ���������
    // ���������Ҫʵ��IQueryServiceWithFuncCodeExt��IQueryServiceExt
    private boolean multiLinkQueryEnable = false;

    private PaginationModelDataManager pmodelDataManager = null;

    public PaginationModelDataManager getPmodelDataManager() {
        return this.pmodelDataManager;
    }

    public void setPmodelDataManager(PaginationModelDataManager pmodelDataManager) {
        this.pmodelDataManager = pmodelDataManager;
    }

    public boolean isMultiLinkQueryEnable() {
        return this.multiLinkQueryEnable;
    }

    public void setMultiLinkQueryEnable(boolean multiLinkQueryEnable) {
        this.multiLinkQueryEnable = multiLinkQueryEnable;
    }

    private AllocfacModelDataManage dataManager;

    public AllocfacModelDataManage getDataManager() {
        return this.dataManager;
    }

    public void setDataManager(AllocfacModelDataManage dataManager) {
        this.dataManager = dataManager;
    }

    public static interface IInitDataProcessor {
        void process(FuncletInitData data);
    }

    public static interface IQueryService {
        Object queryByPk(String pk);
    }

    /**
     * �����ӿڣ��ں�̨��ѯ���ݼ��Ȩ�޵�ʱ���п�����Ҫ���ݹ��ܽڵ���
     *
     * @since 6.0
     * @version 2011-10-17 ����10:04:58
     * @author ������
     */
    public static interface IQueryServiceWithFunCode extends IQueryService {
        Object queryByPkWithFunCode(String pk, String nodeCode);
    }

    public static interface IQueryServiceWithFuncCodeExt extends IQueryServiceWithFunCode {

        Object[] queryByPksWithFunCode(String[] pks, String nodeCode);
    }

    public static interface IQueryServiceExt extends IQueryService {
        Object[] queryByPk(String[] pk);
    }

    class DefaultInitDataProcessor implements AllocfacInitDataListener.IInitDataProcessor {

        @Override
        public void process(FuncletInitData data) {
            if (data.getInitData() instanceof SimpleQuerySchemeVO) {
                AllocfacInitDataListener.this.doInitDataByQueryScheme(data);
                return;
            }

            switch (data.getInitType()) {
                case ILinkType.LINK_TYPE_ADD:
                    // ������
                    this.doLinkAdd();
                    return;
                case ILinkType.LINK_TYPE_APPROVE:
                    this.doLinkApprove((ILinkApproveData) data.getInitData());
                    break;
                case ILinkType.LINK_TYPE_MAINTAIN:
                    this.doLinkMaintain((ILinkMaintainData) data.getInitData());
                    break;
                case ILinkType.LINK_TYPE_QUERY:
                    if (AllocfacInitDataListener.this.isMultiLinkQueryEnable()) {
                        this.doLinkQuerys(data.getInitData());
                    }
                    else {
                        this.doLinkQuery((ILinkQueryData) data.getInitData());
                    }
                    break;
                case -100:
                    // ҵ����־�򿪣���
                    // nc.ui.sm.busilog.action.BusiobjDetailAction.doAction(ActionEvent
                    // e)����
                    this.doBussinessLog((String) data.getInitData());
                    break;
                default:
                    break;
            }
            if (null != AllocfacInitDataListener.this.getAutoShowUpComponent()) {
                AllocfacInitDataListener.this.getAutoShowUpComponent().showMeUp();
            }
        }

        protected void doBussinessLog(String billPk) {
            Object obj = AllocfacInitDataListener.this.getService().queryByPk(billPk);
            AllocfacInitDataListener.this.getModel().initModel(obj);
        }

        protected void doLinkAdd() {
            AllocfacInitDataListener.this.getModel().initModel(null);
        }

        protected void doLinkApprove(ILinkApproveData approveData) {
            Object obj = AllocfacInitDataListener.this.getService().queryByPk(approveData.getBillID());
            if (AllocfacInitDataListener.this.getPmodelDataManager() != null) {
                AllocfacInitDataListener.this.getPmodelDataManager().initModelByPks(new String[] {
                        approveData.getBillID()
                });
            }
            else {
                AllocfacInitDataListener.this.getModel().initModel(obj);
            }
        }

        protected void doLinkMaintain(ILinkMaintainData initData) {
            Object obj = AllocfacInitDataListener.this.getService().queryByPk(initData.getBillID());
            if (AllocfacInitDataListener.this.getPmodelDataManager() != null) {
                AllocfacInitDataListener.this.getPmodelDataManager().initModelByPks(new String[] {
                        initData.getBillID()
                });
            }
            else {
                AllocfacInitDataListener.this.getModel().initModel(obj);
            }
        }

        protected void doLinkQuerys(Object iLinkQueryData) {
            ILinkQueryData[] datas = null;
            if (iLinkQueryData.getClass().isArray()) {
                datas = (ILinkQueryData[]) iLinkQueryData;
            }
            else {
                datas = new ILinkQueryData[] {
                        (ILinkQueryData) iLinkQueryData
                };
            }

            if (!(AllocfacInitDataListener.this.getService() instanceof IQueryServiceWithFuncCodeExt)
                    && !(AllocfacInitDataListener.this.getService() instanceof IQueryServiceExt)) {
                throw new IllegalStateException("wrong!!!");// �����������ö���������ʱ����ע��������ѯ�ӿڣ�
            }
            String[] pks = new String[datas.length];
            for (int i = 0; i < pks.length; i++) {
                pks[i] = datas[i].getBillID();
            }
            Object[] objs = null;
            if (AllocfacInitDataListener.this.getService() instanceof IQueryServiceWithFuncCodeExt) {
                objs =
                        ((IQueryServiceWithFuncCodeExt) AllocfacInitDataListener.this.getService())
                        .queryByPksWithFunCode(pks, AllocfacInitDataListener.this.getContext().getNodeCode());
            }
            else {
                objs = ((IQueryServiceExt) AllocfacInitDataListener.this.getService()).queryByPk(pks);
            }
            if (objs == null) {
                MessageDialog.showErrorDlg(AllocfacInitDataListener.this.getModel().getContext().getEntranceUI(), null,
                        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0", "0pubapp-0337")/*
                         * @res
                         * "û�в鿴�õ��ݵ�Ȩ��"
                         */);
            }
            if (AllocfacInitDataListener.this.getPmodelDataManager() != null) {
                AllocfacInitDataListener.this.getPmodelDataManager().initModelByPks(
                        objs == null ? new String[] {} : pks);
            }
            else {
                AllocfacInitDataListener.this.getModel().initModel(objs);
            }
        }

        protected void doLinkQuery(ILinkQueryData iLinkQueryData) {

            Object obj = null;
            if (AllocfacInitDataListener.this.getService() instanceof IQueryServiceWithFunCode
                    && AllocfacInitDataListener.this.getContext() != null) {
                IQueryServiceWithFunCode serv = (IQueryServiceWithFunCode) AllocfacInitDataListener.this.getService();
                obj =
                        serv.queryByPkWithFunCode(iLinkQueryData.getBillID(), AllocfacInitDataListener.this
                                .getContext().getNodeCode());
            }
            else {
                obj = AllocfacInitDataListener.this.getService().queryByPk(iLinkQueryData.getBillID());
            }
            if (null == obj) {
                MessageDialog.showErrorDlg(AllocfacInitDataListener.this.getModel().getContext().getEntranceUI(), null,
                        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0", "0pubapp-0337")/*
                         * @res
                         * "û�в鿴�õ��ݵ�Ȩ��"
                         */);
            }

            if (AllocfacInitDataListener.this.getPmodelDataManager() != null) {
                AllocfacInitDataListener.this.getPmodelDataManager().initModelByPks(obj != null ? new String[] {
                        iLinkQueryData.getBillID()
                } : new String[] {});
            }
            else {
                AllocfacInitDataListener.this.getModel().initModel(obj);
            }
        }

    }

    class SimpleQueryByPk implements AllocfacInitDataListener.IQueryServiceWithFunCode, IQueryServiceWithFuncCodeExt,
    IQueryServiceExt {

        @Override
        public Object queryByPk(String pk) {
            try {
                Class<?> voClass = Class.forName(AllocfacInitDataListener.this.getVoClassName());
                IQueryBillService queryBillService = NCLocator.getInstance().lookup(IQueryBillService.class);
                return queryBillService.queryBill(voClass, pk);
            }
            catch (Exception e) {
                ExceptionUtils.wrappException(e);
            }
            return null;
        }

        @Override
        public Object queryByPkWithFunCode(String pk, String nodeCode) {
            try {
                Class<?> voClass = Class.forName(AllocfacInitDataListener.this.getVoClassName());
                IQueryBillService queryBillService = NCLocator.getInstance().lookup(IQueryBillService.class);
                return queryBillService.queryBill(voClass, pk, nodeCode);
            }
            catch (Exception e) {
                ExceptionUtils.wrappException(e);
            }
            return null;
        }

        @Override
        public Object[] queryByPk(String[] pk) {
            try {
                Class<?> voClass = Class.forName(AllocfacInitDataListener.this.getVoClassName());
                IQueryBillService queryBillService = NCLocator.getInstance().lookup(IQueryBillService.class);
                return queryBillService.queryBills(voClass, pk);
            }
            catch (Exception e) {
                ExceptionUtils.wrappException(e);
            }
            return null;
        }

        @Override
        public Object[] queryByPksWithFunCode(String[] pks, String nodeCode) {
            try {
                Class<?> voClass = Class.forName(AllocfacInitDataListener.this.getVoClassName());
                IQueryBillService queryBillService = NCLocator.getInstance().lookup(IQueryBillService.class);
                return queryBillService.queryBills(voClass, pks, nodeCode);
            }
            catch (Exception e) {
                ExceptionUtils.wrappException(e);
            }
            return null;
        }

    }

    private IAutoShowUpComponent autoShowUpComponent;

    private LoginContext context;

    private IExceptionHandler exceptionHandler;

    private BillManageModel model;

    private Map<Integer, AllocfacInitDataListener.IInitDataProcessor> processorMap;

    private DefaultQueryAction queryAction;

    private IQueryService service;

    private String voClassName;

    public IAutoShowUpComponent getAutoShowUpComponent() {
        return this.autoShowUpComponent;
    }

    public LoginContext getContext() {
        if (this.context == null) {
            if (this.getModel() != null) {
                return this.getModel().getContext();
            }
        }
        return this.context;
    }

    public BillManageModel getModel() {
        return this.model;
    }

    public Map<Integer, AllocfacInitDataListener.IInitDataProcessor> getProcessorMap() {
        return this.processorMap;
    }

    public DefaultQueryAction getQueryAction() {
        return this.queryAction;
    }

    public IQueryService getService() {
        if (null == this.service) {
            this.service = new SimpleQueryByPk();
        }
        return this.service;
    }

    public String getVoClassName() {
        return this.voClassName;
    }

    @Override
    public void initData(FuncletInitData data) {
        // ���һ���ڵ��Ѿ��򿪲������ڱ༭����, Ĭ�ϲ��ٴ�����������
        if (UIState.EDIT.equals(this.getModel().getUiState()) || UIState.ADD.equals(this.getModel().getUiState())) {
            return;
        }

        if (null == data) {
            this.getDataManager().initModel();
            if (CMStringUtil.isNotEmpty(this.getModel().getContext().getPk_org())
                    && CMCollectionUtil.isNotEmpty(this.getModel().getData())) {
                this.getModel().setSelectedRow(0);
            }
            return;
        }

        IInitDataProcessor processor = null;
        if (null != this.getProcessorMap()) {
            processor = this.getProcessorMap().get(Integer.valueOf(data.getInitType()));
        }
        if (null == processor) {
            processor = this.getDefaultProcessor();
        }
        try {
            processor.process(data);
        }
        catch (Exception e) {
            this.getExceptionHandler(true).handlerExeption(e);
            // ���������Ժ󣬽ڵ㲻Ӧ���ٱ��򿪣���Ҫ���쳣�׳�ȥ����ֹ������������
            ExceptionUtils.wrappException(e);
        }
    }

    public void setAutoShowUpComponent(IAutoShowUpComponent autoShowUpComponent) {
        this.autoShowUpComponent = autoShowUpComponent;
    }

    public void setContext(LoginContext context) {
        this.context = context;
    }

    public void setModel(BillManageModel model) {
        this.model = model;
    }

    public void setProcessorMap(Map<String, AllocfacInitDataListener.IInitDataProcessor> processorStringMap) {
        Set<String> keys = processorStringMap.keySet();
        Map<Integer, AllocfacInitDataListener.IInitDataProcessor> procMap =
                new HashMap<Integer, AllocfacInitDataListener.IInitDataProcessor>();
        for (String strKey : keys) {
            AllocfacInitDataListener.IInitDataProcessor processor = processorStringMap.get(strKey);
            Integer key = Integer.valueOf(strKey);
            procMap.put(key, processor);
        }

        this.processorMap = procMap;
    }

    public void setQueryAction(DefaultQueryAction queryAction) {
        this.queryAction = queryAction;
    }

    public void setService(IQueryService service) {
        this.service = service;
    }

    public void setVoClassName(String voClassName) {
        this.voClassName = voClassName;
    }

    protected void doInitDataByQueryScheme(FuncletInitData data) {
        SimpleQuerySchemeVO vo = (SimpleQuerySchemeVO) data.getInitData();
        if (this.getQueryAction() == null) {
            throw new IllegalArgumentException(NCLangRes.getInstance().getStrByID("uif2",
                    "AllocfacInitDataListener-000000")/* AllocfacInitDataListener���queryAction���Ա���ע�룡 */);
        }
        this.getQueryAction().doSimpleSchemeQuery(vo);
    }

    private IInitDataProcessor getDefaultProcessor() {
        return new DefaultInitDataProcessor();
    }

    private IExceptionHandler getExceptionHandler(boolean showErrorDlg) {
        if (this.exceptionHandler == null) {
            this.exceptionHandler = new PubExceptionHanler(this.context, showErrorDlg);
        }
        return this.exceptionHandler;
    }
}
