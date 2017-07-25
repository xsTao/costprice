package nc.ui.mapub.driver.action.process;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;

/**
 * 单据复制时表头表体处理
 * 
 * @since 6.0
 * @version 2011-7-7 下午02:31:23
 * @author duy
 */
public class CopyActionProcessor implements ICopyActionProcessor<DriverAggVO> {

    @Override
    public void processVOAfterCopy(DriverAggVO billVO, LoginContext context) {
        this.processHeadVO(billVO, context);
    }

    private void processHeadVO(DriverAggVO vo, LoginContext context) {
        DriverVO hvo = vo.getParentVO();
        hvo.setAttributeValue(DriverVO.CDRIVERID, null);
        hvo.setAttributeValue("ts", null);
        // 设置空处理
        hvo.setCreator(null);
        hvo.setCreationtime(null);
        hvo.setModifier(null);
        hvo.setModifiedtime(null);
        hvo.setTs(null);
        // 设置默认值
        hvo.setPk_group(context.getPk_group());
        String pk_org = context.getPk_org();
        hvo.setPk_org(pk_org);
        // 自制动因不支持公式全语言复制
        hvo.setVformulavalue2(null);
        hvo.setVformulavalue3(null);
        hvo.setVformulavalue4(null);
        hvo.setVformulavalue5(null);
        hvo.setVformulavalue6(null);
        // 组织最新版本
        Map<String, String> orgVid = null;
        try {
            orgVid = NCLocator.getInstance().lookup(IOrgUnitPubService_C.class).getNewVIDSByOrgIDS(new String[] {
                context.getPk_org()
            });
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        if (null != orgVid && orgVid.size() > 0) {
            hvo.setPk_org_v(orgVid.get(context.getPk_org()));
        }
    }

}
