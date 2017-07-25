package nc.pubimpl.mapub.allocfac.cm.allocate;

import java.util.List;

import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pubitf.mapub.allocfac.cm.allocate.IAllocfacQueryForAllocateService;
import nc.vo.cmpub.framework.assistant.CMAssInfoItemVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;
import nc.vo.mapub.allocfac.util.AllocfacItemUtil;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2013-9-18 ÉÏÎç09:59:04
 * @author xionghuic
 */
@SuppressWarnings("unchecked")
public class AllocfacQueryServiceForAllocateImpl implements IAllocfacQueryForAllocateService {
    @Override
    public AllocfacItemVO[] queryAllocaFacForActivity(String pk_group, String pk_org, String[] facids,
            AllocfacEnum activity) throws BusinessException {
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.select();
        sql.select(AllocfacItemUtil.getDefaultTableName(), new String[] {
            AllocfacItemVO.CACTIVITYID, AllocfacItemVO.NFACTOR, AllocfacItemVO.CALLOCFACID
        });
        sql.from(AllocfacItemUtil.getDefaultTableName());
        sql.where();
        sql.append(AllocfacItemUtil.getDefaultTableName() + "." + AllocfacItemVO.PK_GROUP, pk_group);
        sql.and();
        sql.append(AllocfacItemUtil.getDefaultTableName() + "." + AllocfacItemVO.PK_ORG, pk_org);
        sql.appendDr();
        sql.and();
        sql.append(AllocfacItemVO.CALLOCFACID, facids);

        IUAPQueryBS querybs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
        List<AllocfacItemVO> result =
                (List<AllocfacItemVO>) querybs
                        .executeQuery(sql.toString(), new BeanListProcessor(AllocfacItemVO.class));
        if (result == null) {
            return new AllocfacItemVO[0];
        }
        return result.toArray(new AllocfacItemVO[0]);
    }

    @Override
    public AllocfacItemVO[] queryAllocaFac(String pk_group, String pk_org, String facid, AllocfacEnum allocfacType,
            String[] facItemids) throws BusinessException {
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.select();
        sql.select(AllocfacItemUtil.getDefaultTableName(), new String[] {
            AllocfacItemVO.CCOSTCENTERID, AllocfacItemVO.CMATERIALID, AllocfacItemVO.CSTUFFID,
            AllocfacItemVO.CMARCOSTCLASSID, AllocfacItemVO.CMARBASECLASSID, CMAssInfoItemVO.CCUSTOMERID,
            CMAssInfoItemVO.CVENDORID, CMAssInfoItemVO.CPRODUCTORID, CMAssInfoItemVO.CPROJECTID,
            CMAssInfoItemVO.VBFREE1, CMAssInfoItemVO.VBFREE2, CMAssInfoItemVO.VBFREE3, CMAssInfoItemVO.VBFREE4,
            CMAssInfoItemVO.VBFREE5, CMAssInfoItemVO.VBFREE6, CMAssInfoItemVO.VBFREE7, CMAssInfoItemVO.VBFREE8,
            CMAssInfoItemVO.VBFREE9, CMAssInfoItemVO.VBFREE10, AllocfacItemVO.NFACTOR
        });
        sql.from(AllocfacItemUtil.getDefaultTableName());
        sql.where();
        sql.append(AllocfacItemVO.CALLOCFACID, facid);
        sql.appendDr();
        sql.and();
        if (AllocfacEnum.costcenter.equals(allocfacType)) {
            sql.append(AllocfacItemVO.CCOSTCENTERID, facItemids);
        }
        else if (AllocfacEnum.costclass.equals(allocfacType)) {
            sql.append(AllocfacItemVO.CMARCOSTCLASSID, facItemids);
        }
        else if (AllocfacEnum.baseclass.equals(allocfacType)) {
            sql.append(AllocfacItemVO.CMARBASECLASSID, facItemids);
        }
        else if (AllocfacEnum.activity.equals(allocfacType)) {
            sql.append(AllocfacItemVO.CACTIVITYID, facItemids);
        }
        else if (AllocfacEnum.stuff.equals(allocfacType)) {
            sql.append("1=1");
        }
        else {
            sql.append(AllocfacItemVO.CMATERIALID, facItemids);
        }

        IUAPQueryBS querybs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
        List<AllocfacItemVO> result =
                (List<AllocfacItemVO>) querybs
                        .executeQuery(sql.toString(), new BeanListProcessor(AllocfacItemVO.class));
        if (result == null) {
            return new AllocfacItemVO[0];
        }
        return result.toArray(new AllocfacItemVO[0]);
    }
}
