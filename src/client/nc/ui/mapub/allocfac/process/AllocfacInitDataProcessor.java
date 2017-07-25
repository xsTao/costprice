/**
 * 
 */
package nc.ui.mapub.allocfac.process;

import nc.funcnode.ui.FuncletInitData;
import nc.pub.templet.converter.util.helper.ExceptionUtils;
import nc.ui.mapub.allocfac.action.AllocfacInitDataListener;
import nc.ui.mapub.allocfac.action.CardRefreshAction;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.vo.querytemplate.queryscheme.SimpleQuerySchemeVO;

public class AllocfacInitDataProcessor implements AllocfacInitDataListener.IInitDataProcessor {
    private DefaultQueryAction queryAction;

    private CardRefreshAction refreshAction;

    public DefaultQueryAction getQueryAction() {
        return this.queryAction;
    }

    public void setQueryAction(DefaultQueryAction queryAction) {
        this.queryAction = queryAction;
    }

    public CardRefreshAction getRefreshAction() {
        return this.refreshAction;
    }

    public void setRefreshAction(CardRefreshAction refreshAction) {
        this.refreshAction = refreshAction;
    }

    @Override
    public void process(FuncletInitData data) {
        this.getQueryAction().createQueryArea();
        if (data.getInitData() instanceof SimpleQuerySchemeVO) {
            this.doInitDataByQueryScheme(data);
            return;
        }
    }

    private void doInitDataByQueryScheme(FuncletInitData data) {
        SimpleQuerySchemeVO vo = (SimpleQuerySchemeVO) data.getInitData();
        if (this.getQueryAction() == null) {
            throw new IllegalArgumentException(NCLangRes.getInstance().getStrByID("uif2",
                    "DefaultFuncNodeInitDataListener-000000")/* DefaultFuncNodeInitDataListener类的queryAction属性必须注入！ */);
        }
        this.getQueryAction().doSimpleSchemeQuery(vo);
        try {
            this.getRefreshAction().doAction(null);
        }
        catch (Exception e) {
            ExceptionUtils.wrapException(e);
        }
    }
}
