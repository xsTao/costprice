package nc.ui.mapub.materialpricebase.interceptor;

import java.util.Map;

import nc.bd.framework.base.CMMapUtil;
import nc.cmpub.business.adapter.BDAdapter;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDate;
import nc.vo.uif2.LoginContext;

/**
 * 材料价格库
 * 复制处理
 * 
 * @since 6.36
 * @version 2014-11-7 下午4:10:49
 * @author zhangchd
 */
/**
 * 
 * @since 6.5
 * @version 2014-11-7 下午4:12:37
 * @author zhangchd
 */
/**
 * @since 6.5
 * @version 2014-11-7 下午4:12:40
 * @author zhangchd
 */
public class CopyActionProcessor implements ICopyActionProcessor<MaterialPriceBaseAggVO> {
    /**
     * 默认失效日期
     */
    private static final UFDate DEFAULTENDDATE = new UFDate("9999-12-31");

    @Override
    public void processVOAfterCopy(MaterialPriceBaseAggVO billVO, LoginContext context) {
        this.processHeadVO(billVO, context);
        this.processBodyVO(billVO);
    }

    private void processBodyVO(MaterialPriceBaseAggVO vo) {
        vo.getParent().setAttributeValue(vo.getMetaData().getParent().getPrimaryAttribute().getName(), null);
        vo.getParent().setAttributeValue("ts", null);
        for (IVOMeta meta : vo.getMetaData().getChildren()) {
            if (vo.getChildren(meta) == null) {
                continue;
            }
            for (ISuperVO childvo : vo.getChildren(meta)) {
                childvo.setAttributeValue(meta.getPrimaryAttribute().getName(), null);
                childvo.setAttributeValue("pk_group", null);
                childvo.setAttributeValue("pk_org", null);
                childvo.setAttributeValue("ts", null);
            }
        }
    }

    private void processHeadVO(MaterialPriceBaseAggVO vo, LoginContext context) {
        MaterialPriceBaseHeadVO hvo = vo.getParentVO();
        // 获取当前登陆时间
        UFDate serverDate = AppUiContext.getInstance().getBusiDate();
        if (serverDate != null) {
            // 设置生效日期
            hvo.setDbegindate(serverDate);
        }
        // 设置失效日期
        hvo.setDenddate(CopyActionProcessor.DEFAULTENDDATE);
        hvo.setVpricecode(null);
        hvo.setVpricename(null);
        // 设置空处理
        hvo.setModifier(null);
        hvo.setModifiedtime(null);
        hvo.setCreator(null);
        hvo.setCreationtime(null);
        hvo.setTs(null);
        // 设置默认值
        hvo.setPk_group(context.getPk_group());
        hvo.setPk_org(context.getPk_org());
        // 组织最新版本
        Map<String, String> orgVid = BDAdapter.getNewVIDSByOrgIDS(new String[] {
            context.getPk_org()
        });
        if (CMMapUtil.isNotEmpty(orgVid)) {
            hvo.setPk_org_v(orgVid.get(context.getPk_org()));
        }
    }
}
