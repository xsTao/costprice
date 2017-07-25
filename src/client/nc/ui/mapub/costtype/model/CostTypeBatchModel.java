package nc.ui.mapub.costtype.model;

import java.util.ArrayList;
import java.util.List;

import nc.itf.org.IOrgConst;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.bd.pub.NODE_TYPE;
import nc.vo.mapub.costtype.entity.CostTypeVO;

public class CostTypeBatchModel extends BatchBillTableModel {

    private CostTypeModelDataManager modelDataManager;

    @Override
    public void save() throws Exception {
        this.beforeSaveProcess();

        BatchOperateVO vo = this.getCurrentSaveObject();

        // Object[] adds = vo.getAddObjs();
        Object[] upds = vo.getUpdObjs();
        Object[] dels = vo.getDelObjs();

        // ��������Ĭ������
        // if (adds != null && adds.length > 0) {
        // for (Object o : adds) {
        // ((CostTypeVO) o).setEnablestate(TaxKindConst.enable);
        // }
        // }
        if (NODE_TYPE.ORG_NODE.equals(this.getContext().getNodeType())) {
            if (upds.length > 0) {
                ArrayList<Object> updlist = new ArrayList<Object>();
                for (Object o : upds) {
                    if (!((CostTypeVO) o).getPk_group().equals(((CostTypeVO) o).getPk_org())) {
                        updlist.add(o);
                    }
                }
                vo.setUpdObjs(updlist.toArray(new Object[0]));
            }

            if (dels.length > 0) {
                ArrayList<Object> dellist = new ArrayList<Object>();
                for (Object o : dels) {
                    if (!IOrgConst.GROUPORGTYPE.equals(((CostTypeVO) o).getPk_org())) {
                        dellist.add(o);
                    }
                }
                vo.setDelObjs(dellist.toArray(new Object[0]));
            }
        }

        vo = this.getService().batchSave(vo);
        this.directSave(vo);
        // this.modelDataManager.initModel();
    }

    /**
     * ģ��ɾ�в���
     */
    @Override
    public void delLine(int delIndex) throws Exception {
        Integer[] indexs = this.getDelLineIndex(delIndex);
        ArrayList<Integer> sellist = new ArrayList<Integer>();
        // �ܿ�ģʽ-ҵ��Ԫ���ڵ㲻��ɾ�����ż�����(�������ַ�ʽҲ����ʵ�ֲ���ɾ���������õĲ���ɾ����δ�����õ�ɾ����)
        if (NODE_TYPE.ORG_NODE.equals(this.getContext().getNodeType())) {
            for (Integer index : indexs) {
                Object delObj = this.getRow(index.intValue());
                if (!((CostTypeVO) delObj).getPk_group().equals(((CostTypeVO) delObj).getPk_org())) {
                    sellist.add(index);
                }
            }
            if (sellist.size() < 1) {
                return;
            }

            this.setSelectedIndex(sellist.get(0).intValue());

            int[] resultlist = new int[sellist.size()];
            for (int i = 0; i < sellist.size(); i++) {
                resultlist[i] = sellist.get(i).intValue();
            }

            this.setSelectedOperaRows(resultlist);
        }

        super.delLine(delIndex);
    }

    public CostTypeModelDataManager getModelDataManager() {
        return this.modelDataManager;
    }

    public void setModelDataManager(CostTypeModelDataManager modelDataManager) {
        this.modelDataManager = modelDataManager;
    }

    @Override
    protected void processDelLineInEdit(Integer[] indexs, List<Object> list) {
        super.processDelLineInEdit(indexs, list);
        // ����ѡ����
        if (list != null && list.size() > 0) {
            this.setSelectedOperaRows(new int[] {
                indexs[0].intValue() - 1
            });
            this.setSelectedIndex(indexs[0].intValue() - 1);
        }
    }

    @Override
    protected void processDelLindeInNotEdit(Integer[] indexs, List<Object> list) throws Exception {
        super.processDelLindeInNotEdit(indexs, list);
        // ����ѡ����
        if (list != null && list.size() > 0) {
            this.setSelectedOperaRows(new int[] {
                indexs[0].intValue() - 1
            });
            this.setSelectedIndex(indexs[0].intValue() - 1);
        }
    }
}
