/**
 *
 */
package nc.impl.mapub.allocfac;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import nc.bs.mapub.allocfac.bp.AllocfacDeleteBP;
import nc.bs.mapub.allocfac.bp.AllocfacInsertBP;
import nc.bs.mapub.allocfac.bp.AllocfacUpdateBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.mapub.allocfac.IAllocfacMaintainService;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.pub.BusinessException;

/**
 * ����ϵ����ɾ�Ĳ���ʵ����
 */
public class AllocfacMaintainServiceImpl implements IAllocfacMaintainService {

    @Override
    public AllocfacAggVO[] insert(AllocfacAggVO[] vos) throws BusinessException {
        // ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
        BillTransferTool<AllocfacAggVO> transferTool = new BillTransferTool<AllocfacAggVO>(vos);
        AllocfacAggVO[] mergedVO = transferTool.getClientFullInfoBill();

        // ����BP
        AllocfacInsertBP action = new AllocfacInsertBP();
        AllocfacAggVO[] retvos = action.insert(mergedVO);

        // ���췵������
        return transferTool.getBillForToClient(retvos);
    }

    @Override
    public AllocfacAggVO[] update(AllocfacAggVO[] vos) throws BusinessException {
        // ���� + ���ts
        BillTransferTool<AllocfacAggVO> transTool = new BillTransferTool<AllocfacAggVO>(vos);
        // ��ȫǰ̨VO
        AllocfacAggVO[] fullBills = transTool.getClientFullInfoBill();
        // ����޸�ǰvo
        AllocfacAggVO[] originBills = transTool.getOriginBills();
        // ����BP
        AllocfacUpdateBP bp = new AllocfacUpdateBP();
        fullBills[0].setMap(vos[0].getMap());
        AllocfacAggVO[] retBills = bp.update(fullBills, originBills);

        // ���췵������
        return transTool.getBillForToClient(retBills);
    }

    @Override
    public void allocDelete(AllocfacAggVO[] vos) throws BusinessException {
        // ���� �Ƚ���ts
        BillTransferTool<AllocfacAggVO> transferTool = new BillTransferTool<AllocfacAggVO>(vos);
        AllocfacAggVO[] fullBills = transferTool.getClientFullInfoBill();
        AllocfacDeleteBP deleteBP = new AllocfacDeleteBP();
        deleteBP.delete(fullBills);
    }

    @Override
    public String getDatabaseCFG(boolean needDetail) throws BusinessException {
        Connection con = null;
        try {
            String dataSource = null;
            PersistenceManager manager = PersistenceManager.getInstance(dataSource);
            JdbcSession session = manager.getJdbcSession();
            DatabaseMetaData metaData = session.getMetaData();
            con = metaData.getConnection();
        }
        catch (Exception e) {
            return "Get connection error";
        }
        if (needDetail) {
            return "Database message:\nclass:\n" + con.getClass().getName() + "\ndetails:\n" + con;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Database cfg:");
        builder.append("\nurl: ");
        try {
            String url = this.getFieldValue(con, "url");
            builder.append(url);
        }
        catch (Exception e) {
            builder.append("get url error,please contact xionghuic to solve it.");
        }
        builder.append("\nuserName: ");
        try {
            String userName = this.getFieldValue(con, "userName");
            builder.append(userName);
        }
        catch (Exception e) {
            builder.append("get userName error,please contact xionghuic to solve it.");
        }
        builder.append("\npassword: ");
        try {
            String password = this.getFieldValue(con, "password");
            builder.append(password);
        }
        catch (Exception e) {
            builder.append("get password error,please contact xionghuic to solve it.");
        }
        return builder.toString();
    }

    private String getFieldValue(Connection con, String name) throws Exception {
        Class<?> clazz = con.getClass();
        Exception ex = null;
        while (true) {
            try {
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                String value = (String) field.get(con);
                return value;
            }
            catch (Exception e) {
                ex = e;
            }
            clazz = clazz.getSuperclass();
            if (clazz == null || clazz.equals(Object.class)) {
                throw ex;
            }
        }
    }
}
