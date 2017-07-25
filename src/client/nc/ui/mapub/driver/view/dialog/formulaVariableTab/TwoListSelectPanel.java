package nc.ui.mapub.driver.view.dialog.formulaVariableTab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIList;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIScrollPane;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.uif2.LoginContext;

/**
 * <b> 两个列表之间选择数据 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-4-15
 *
 * @author:wangtf
 */
/**
 * <b> 简要描述功能 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-4-15
 *
 * @author:wangtf
 */
@SuppressWarnings({
    "rawtypes", "unchecked"
})
public class TwoListSelectPanel extends UIPanel {
    /**
     * 高
     */
    private static final int HEIGHT = 407;

    /**
     * 宽
     */
    private static final int WIDTH = 494;

    /**
     * 选择的项目
     */
    List<ListItem> selectListItmes = null;

    /**
     * 环境变量
     */
    LoginContext context;

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 可选择项目的panel
     */
    private UIPanel panelWest = null;

    /**
     * 选择按钮的panel
     */
    private UIPanel panelCenter = null;

    /**
     * 已经选择的项目panel
     */
    private UIPanel panelEast = null;

    /**
     * 确认 取消的panel
     */
    private UIPanel panelSouth = null;

    /**
     * 可选标签
     */
    private UILabel lbNotSelect = null;

    /**
     * 可选的列表
     */
    private UIList listNotSelect = null;

    /**
     * "添加"按钮
     */
    private UIButton btnAdd = null;

    /**
     * 已选标签
     */
    private UILabel lbSelected = null;

    /**
     * 已选列表
     */
    private UIList listSelected = null;

    /**
     * 确认
     */
    private UIButton btnConfirm = null;

    /**
     * 取消
     */
    private UIButton btnCancel = null;

    /**
     * 添加所有
     */
    private UIButton btnAddAll = null;

    /**
     * 删除
     */
    private UIButton btnDelete = null;

    /**
     * 输出所有
     */
    private UIButton btnDeleteAll = null;

    /**
     * 容器dialog
     */
    private UIDialog containerDialg;

    /**
     * 界面的数据
     */
    private Map<String, Object> data;

    /**
     * This is the default constructor
     *
     * @param context
     *            环境变量
     * @param dialog
     *            包含panel的对话框
     */
    public TwoListSelectPanel(LoginContext context, UIDialog dialog) {
        super();
        this.containerDialg = dialog;
        this.context = context;
        this.initialize();
    }

    /**
     * This method initializes this
     */
    private void initialize() {
        this.setSize(TwoListSelectPanel.WIDTH, TwoListSelectPanel.HEIGHT);
        this.setLayout(new BorderLayout());
        this.add(this.getPanelWest(), BorderLayout.WEST);
        this.add(this.getPanelCenter(), BorderLayout.CENTER);
        this.add(this.getPanelEast(), BorderLayout.EAST);
        this.add(this.getPanelSouth(), BorderLayout.SOUTH);

        this.initUI();
    }

    /**
     * 额外的初始化操作
     */
    protected void initUI() {
        return;
    }

    /**
     * This method initializes panelWest
     *
     * @return javax.swing.UIPanel
     */
    private UIPanel getPanelWest() {
        if (this.panelWest == null) {
            this.lbNotSelect = new UILabel();
            this.lbNotSelect.setText(CMDriverLangConst.getNOTSELECT());
            this.panelWest = new UIPanel();
            this.panelWest.setLayout(new BorderLayout());
            this.panelWest.add(this.lbNotSelect, BorderLayout.NORTH);
            UIScrollPane scrollPane = new UIScrollPane();
            scrollPane.setViewportView(this.getListNotSelect());
            this.panelWest.add(scrollPane, BorderLayout.CENTER);
        }
        return this.panelWest;
    }

    /**
     * This method initializes panelCenter
     *
     * @return javax.swing.UIPanel
     */
    private UIPanel getPanelCenter() {
        if (this.panelCenter == null) {
            GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            gridBagConstraints4.fill = GridBagConstraints.BOTH;
            GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.gridx = 0;
            gridBagConstraints3.gridy = 3;
            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.gridx = 0;
            gridBagConstraints2.fill = GridBagConstraints.BOTH;
            gridBagConstraints2.gridy = 2;
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.gridx = 0;
            gridBagConstraints1.gridy = 1;
            this.panelCenter = new UIPanel();
            this.panelCenter.setLayout(new GridBagLayout());
            this.panelCenter.add(this.getBtnAdd(), gridBagConstraints4);
            this.panelCenter.add(this.getBtnAddAll(), gridBagConstraints1);
            this.panelCenter.add(this.getBtnDelete(), gridBagConstraints2);
            this.panelCenter.add(this.getBtnDeleteAll(), gridBagConstraints3);
        }
        return this.panelCenter;
    }

    /**
     * This method initializes panelEast
     *
     * @return javax.swing.UIPanel
     */
    private UIPanel getPanelEast() {
        if (this.panelEast == null) {

            this.panelEast = new UIPanel();
            this.panelEast.setLayout(new BorderLayout());
            this.lbSelected = new UILabel();
            // this.lbSelected.setText("已选");
            this.lbSelected.setText(CMDriverLangConst.getSELECTED());
            this.panelEast.add(this.lbSelected, BorderLayout.NORTH);

            UIScrollPane scrollPane = new UIScrollPane();
            scrollPane.setViewportView(this.getListSelected());

            this.panelEast.add(scrollPane, BorderLayout.CENTER);
        }
        return this.panelEast;
    }

    /**
     * This method initializes panelSouth
     *
     * @return javax.swing.UIPanel
     */
    private UIPanel getPanelSouth() {
        if (this.panelSouth == null) {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlignment(FlowLayout.RIGHT);
            this.panelSouth = new UIPanel();
            this.panelSouth.setLayout(flowLayout);
            this.panelSouth.add(this.getBtnConfirm(), null);
            this.panelSouth.add(this.getBtnCancel(), null);
        }
        return this.panelSouth;
    }

    /**
     * This method initializes listNotSelect
     *
     * @return javax.swing.UIList
     */
    protected UIList getListNotSelect() {
        if (this.listNotSelect == null) {
            this.listNotSelect = new UIList();
            this.listNotSelect.setPreferredSize(new Dimension(220, 0));
            this.listNotSelect.setModel(new DefaultListModel());
            this.listNotSelect.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    int count = e.getClickCount();

                    if (count == 2) {
                        TwoListSelectPanel.this.onAddClicked(null);
                    }
                }
            });
        }
        return this.listNotSelect;
    }

    /**
     * This method initializes btnAdd
     *
     * @return javax.swing.UIButton
     */
    private UIButton getBtnAdd() {
        if (this.btnAdd == null) {
            this.btnAdd = new UIButton();
            this.btnAdd.setText(">");

            this.btnAdd.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    TwoListSelectPanel.this.onAddClicked(arg0);

                }
            });

        }
        return this.btnAdd;
    }

    /**
     * 添加选择项
     *
     * @param arg0
     *            事件
     */
    protected void onAddClicked(ActionEvent arg0) {
        DefaultListModel notSelectModel = this.getListNotSelectModel();

        int selectIndex = this.getListNotSelect().getSelectedIndex();

        Object listItem = notSelectModel.getElementAt(selectIndex);

        DefaultListModel selectedModel = this.getListSelectedModel();

        selectedModel.addElement(listItem);

        notSelectModel.removeElementAt(selectIndex);

    }

    /**
     * This method initializes listSelected
     *
     * @return javax.swing.UIList
     */
    protected UIList getListSelected() {
        if (this.listSelected == null) {
            this.listSelected = new UIList();
            this.listSelected.setPreferredSize(new Dimension(220, 0));
            this.listSelected.setModel(new DefaultListModel());
            this.listSelected.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    int count = e.getClickCount();

                    if (count == 2) {
                        TwoListSelectPanel.this.onDeleteClicked(null);
                    }
                }
            });
        }
        return this.listSelected;
    }

    /**
     * This method initializes btnConfirm
     *
     * @return javax.swing.UIButton
     */
    private UIButton getBtnConfirm() {
        if (this.btnConfirm == null) {
            this.btnConfirm = new UIButton();
            // this.btnConfirm.setText("确定");
            this.btnConfirm.setText(CMDriverLangConst.getCONFIRM());
            this.btnConfirm.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    TwoListSelectPanel.this.onConfirmClicked(arg0);

                }
            });
        }
        return this.btnConfirm;
    }

    /**
     * 确认
     *
     * @param arg0
     *            事件
     */
    protected void onConfirmClicked(ActionEvent arg0) {
        this.getContainerDialg().closeOK();

        // this.getTopLevelAncestor().setVisible(false);
    }

    /**
     * This method initializes btnCancel
     *
     * @return javax.swing.UIButton
     */
    private UIButton getBtnCancel() {
        if (this.btnCancel == null) {
            this.btnCancel = new UIButton();
            // this.btnCancel.setText("取消");
            this.btnCancel.setText(CMDriverLangConst.getCANCEL());

            this.btnCancel.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    TwoListSelectPanel.this.onCancelClicked(arg0);

                }
            });
        }
        return this.btnCancel;
    }

    /**
     * 取消
     *
     * @param arg0
     *            事件
     */
    protected void onCancelClicked(ActionEvent arg0) {
        this.getContainerDialg().closeCancel();

        // this.getTopLevelAncestor().setVisible(false);

    }

    /**
     * This method initializes btnAddAll
     *
     * @return javax.swing.UIButton
     */
    private UIButton getBtnAddAll() {
        if (this.btnAddAll == null) {
            this.btnAddAll = new UIButton();
            this.btnAddAll.setText(">>");

            this.btnAddAll.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    TwoListSelectPanel.this.onAddAllClicked(arg0);

                }
            });
        }
        return this.btnAddAll;
    }

    /**
     * 添加所有选择项目
     *
     * @param arg0
     *            事件
     */
    protected void onAddAllClicked(ActionEvent arg0) {
        List<ListItem> items = this.getAllSelectItem(this.getListNotSelect());
        if (items == null || items.isEmpty()) {
            return;
        }

        DefaultListModel listModel = this.getListSelectedModel();
        for (ListItem listItem : items) {
            listModel.addElement(listItem);
        }

        // 清空原来的数据
        DefaultListModel listNotSelectModel = this.getListNotSelectModel();
        listNotSelectModel.clear();

    }

    /**
     * This method initializes btnDelete
     *
     * @return javax.swing.UIButton
     */
    private UIButton getBtnDelete() {
        if (this.btnDelete == null) {
            this.btnDelete = new UIButton();
            this.btnDelete.setText("<");

            this.btnDelete.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    TwoListSelectPanel.this.onDeleteClicked(arg0);

                }
            });
        }
        return this.btnDelete;
    }

    /**
     * 删除当前选择项目
     *
     * @param arg0
     *            事件
     */
    protected void onDeleteClicked(ActionEvent arg0) {
        DefaultListModel selectedModel = this.getListSelectedModel();

        int selectIndex = this.getListSelected().getSelectedIndex();

        Object listItem = selectedModel.getElementAt(selectIndex);

        DefaultListModel notSelectModel = this.getListNotSelectModel();

        notSelectModel.addElement(listItem);
        selectedModel.removeElementAt(selectIndex);

    }

    /**
     * This method initializes btnDeleteAll
     *
     * @return javax.swing.UIButton
     */
    private UIButton getBtnDeleteAll() {
        if (this.btnDeleteAll == null) {
            this.btnDeleteAll = new UIButton();
            this.btnDeleteAll.setText("<<");

            this.btnDeleteAll.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    TwoListSelectPanel.this.onDeleteAllClicked(arg0);

                }
            });
        }
        return this.btnDeleteAll;
    }

    /**
     * 删除所有已选择项目
     *
     * @param arg0
     *            事件
     */
    protected void onDeleteAllClicked(ActionEvent arg0) {
        List<ListItem> items = this.getAllSelectItem(this.getListSelected());
        if (items == null || items.isEmpty()) {
            return;
        }

        DefaultListModel listNotModel = this.getListNotSelectModel();
        for (ListItem listItem : items) {
            listNotModel.addElement(listItem);
        }

        // 清空原来的数据
        DefaultListModel listSelectModel = this.getListSelectedModel();
        listSelectModel.clear();

    }

    /**
     * 获取list控件中所有的项目
     *
     * @param list
     *            获取项目的list
     * @return 列表中的项目
     */
    protected List<ListItem> getAllSelectItem(JList list) {

        int listSize = list.getModel().getSize();
        if (listSize <= 0) {
            return new ArrayList<ListItem>();
        }

        List<ListItem> listItems = new ArrayList<ListItem>();
        for (int i = 0; i < listSize; i++) {
            listItems.add((ListItem) list.getModel().getElementAt(i));
        }

        return listItems;
    }

    /**
     * 获取没有选择list的model
     *
     * @return listmodel
     */
    protected DefaultListModel getListNotSelectModel() {

        return (DefaultListModel) this.getListNotSelect().getModel();

    }

    /**
     * 获取已经选择list的model
     *
     * @return listmodel
     */
    protected DefaultListModel getListSelectedModel() {
        return (DefaultListModel) this.getListSelected().getModel();

    }

    /**
     * 获取所有选择的项目
     *
     * @return 选择的项目
     */
    public List<ListItem> getSelectListItmes() {
        if (this.selectListItmes == null) {
            this.selectListItmes = new ArrayList<ListItem>();
        }
        this.selectListItmes = this.getAllSelectItem(this.getListSelected());
        return this.selectListItmes;
    }

    /**
     * 获取环境变量
     *
     * @return 环境变量
     */
    protected LoginContext getContext() {
        return this.context;
    }

    /**
     * 得到panel的容器
     *
     * @return 包含panel的对话框
     */
    private UIDialog getContainerDialg() {
        return this.containerDialg;
    }

    /**
     * 获得界面中的数据
     *
     * @return 界面中需要保存的数据
     */
    public Map<String, Object> getData() {
        if (this.data == null) {
            this.data = new HashMap<String, Object>();
        }
        return this.data;
    }

    /**
     * 设置界面保存的数据
     *
     * @param data
     *            保存的数据
     */
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
