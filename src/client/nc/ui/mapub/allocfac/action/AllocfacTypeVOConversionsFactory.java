/**
 * 
 */
package nc.ui.mapub.allocfac.action;

import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.trade.excelimport.conversion.IntegerEnumConversion;
import nc.vo.trade.excelimport.conversion.VOConversionsFactory;

/**
 * @since v6.3
 * @version 2013-5-20 обнГ04:22:25
 * @author xionghuic
 */
public class AllocfacTypeVOConversionsFactory extends VOConversionsFactory {
    @Override
    protected void initConversions() {
        this.getConversions().add(
                new IntegerEnumConversion("073aa2b7-0127-4a80-a94e-a14db7ab6e60", AllocfacHeadVO.IALLOCTYPE, null));
    }
}
