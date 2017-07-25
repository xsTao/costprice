package nc.ui.mapub.costtype.view;

import nc.ui.pub.beans.UIRefPane;

public class MMFactoryRefPane extends UIRefPane {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // private static final String FACTORYlANGCONST = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
    // "UC000-0001685")/* @res "工厂" */;
    private static final String FACTORYlANGCONST = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
            "UC000-000003")/* @res "业务单元" */;

    public MMFactoryRefPane() {

        this.setRefNodeName(MMFactoryRefPane.FACTORYlANGCONST);
    }

}
