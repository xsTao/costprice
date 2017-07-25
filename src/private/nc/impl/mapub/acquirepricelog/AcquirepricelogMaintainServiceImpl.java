package nc.impl.mapub.acquirepricelog;

import java.util.ArrayList;
import java.util.List;

import nc.bs.bd.baseservice.md.BatchBaseService;
import nc.bs.bd.cache.CacheProxy;
import nc.bs.mapub.acquirepricelog.bp.AcquirepricelogDeleteBP;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.itf.mapub.acquirepricelog.IAcquirepricelogMaintainService;
import nc.md.MDBaseQueryFacade;
import nc.md.model.MetaDataException;
import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ȡ�۴�����־ά������ʵ����
 *
 * @since 6.0
 * @version 2012-10-23 ����04:10:57
 * @author liyjf
 */
public class AcquirepricelogMaintainServiceImpl extends BatchBaseService<AcquirePriceLogVO> implements
        IAcquirepricelogMaintainService {

    /**
     * Ԫ����ID
     */
    private static String mdId;
    static {
        try {
            AcquirepricelogMaintainServiceImpl.mdId =
                    MDBaseQueryFacade.getInstance().getBeanByFullClassName(AcquirePriceLogVO.class.getName()).getID();
        }
        catch (MetaDataException e) {
        }
    }

    /**
     * Constructor
     */
    public AcquirepricelogMaintainServiceImpl() {
        super(AcquirepricelogMaintainServiceImpl.mdId);
    }

    // ɾ��
    @Override
    public void deleteAcquirepricelog(AcquirePriceLogVO[] vos) throws BusinessException {
        try {
            // ����+���TS
            VOConcurrentTool tool = new VOConcurrentTool();
            tool.checkTSWithDB(vos);

            // ɾ��ǰ���ö���У��
            this.deleteValidateVO(vos);

            AcquirepricelogDeleteBP deleteBP = new AcquirepricelogDeleteBP();
            deleteBP.delete(vos);

            List<String> pkList = new ArrayList<String>();
            for (AcquirePriceLogVO vo : vos) {
                pkList.add(vo.getPrimaryKey());
            }
            // ֪ͨ����
            CacheProxy.fireDataDeletedBatch(AcquirePriceLogVO.getDefaultTableName(), pkList.toArray(new String[0]));
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }
    }

}
