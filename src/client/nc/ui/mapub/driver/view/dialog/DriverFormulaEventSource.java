package nc.ui.mapub.driver.view.dialog;

import nc.ui.pub.formula.dialog.FormulaEventSource;

/**
 * <b> 成本动因FormulaEventSource </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-10-19
 * 
 * @author:leixia
 */
public class DriverFormulaEventSource extends FormulaEventSource {
    /**
     * eventSource
     */
    private Object eventSource;

    /**
     * eventType
     */
    private FormulaEventType eventType;

    /**
     * original String
     */
    private String originalString;

    /**
     * new String
     */
    private String newString;

    /**
     * context
     */
    private Object contextObject;

    /**
     * new Value String
     */
    private String newValueString;

    // private String[] multiLangs;

    /**
     * eventSource get method
     * 简要说明
     * 
     * @see nc.ui.pub.formula.dialog.FormulaEventSource#getEventSource()
     * @return Object
     */
    @Override
    public Object getEventSource() {
        return this.eventSource;
    }

    /**
     * eventSource set method
     * 简要说明
     * 
     * @see nc.ui.pub.formula.dialog.FormulaEventSource#setEventSource(java.lang.Object)
     * @param eventSource Object
     */
    @Override
    public void setEventSource(Object eventSource) {
        this.eventSource = eventSource;
    }

    /**
     * eventType get method
     * 简要说明
     * 
     * @see nc.ui.pub.formula.dialog.FormulaEventSource#getEventType()
     * @return FormulaEventType
     */
    @Override
    public FormulaEventType getEventType() {
        return this.eventType;
    }

    /**
     * eventType set method
     * 简要说明
     * 
     * @see nc.ui.pub.formula.dialog.FormulaEventSource#setEventType
     *      (nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType)
     * @param eventType FormulaEventType
     */
    @Override
    public void setEventType(FormulaEventType eventType) {
        this.eventType = eventType;
    }

    /**
     * originalString get method
     * 简要说明
     * 
     * @see nc.ui.pub.formula.dialog.FormulaEventSource#getOriginalString()
     * @return String
     */
    @Override
    public String getOriginalString() {
        return this.originalString;
    }

    /**
     * 简要说明
     * 
     * @see nc.ui.pub.formula.dialog.FormulaEventSource#setOriginalString(java.lang.String)
     * @param originalString String
     */
    @Override
    public void setOriginalString(String originalString) {
        this.originalString = originalString;
    }

    /**
     * newString get method
     * 简要说明
     * 
     * @see nc.ui.pub.formula.dialog.FormulaEventSource#getNewString()
     * @return String
     */
    @Override
    public String getNewString() {
        return this.newString;
    }

    /**
     * newString set method
     * 简要说明
     * 
     * @see nc.ui.pub.formula.dialog.FormulaEventSource#setNewString(java.lang.String)
     * @param newString String
     */
    @Override
    public void setNewString(String newString) {
        this.newString = newString;
    }

    /**
     * contextObject get method
     * 简要说明
     * 
     * @see nc.ui.pub.formula.dialog.FormulaEventSource#getContextObject()
     * @return Object
     */
    @Override
    public Object getContextObject() {
        return this.contextObject;
    }

    /**
     * contextObject set method
     * 简要说明
     * 
     * @see nc.ui.pub.formula.dialog.FormulaEventSource#setContextObject(java.lang.Object)
     * @param contextObject Object
     */
    @Override
    public void setContextObject(Object contextObject) {
        this.contextObject = contextObject;
    }

    /**
     * newValueString get method
     * 
     * @return String
     */
    public String getNewValueString() {
        return this.newValueString;
    }

    /**
     * newValueString set method
     * 
     * @param newValueString String
     */
    public void setNewValueString(String newValueString) {
        this.newValueString = newValueString;
    }

    // public String[] getMultiLangs() {
    // return this.multiLangs;
    // }
    //
    // public void setMultiLangs(String[] multiLangs) {
    // this.multiLangs = multiLangs;
    // }

}
