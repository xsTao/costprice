package nc.ui.mapub.driver.view.dialog.formulaVariableTab;

/**
 * <b> 列表中的项目 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-4-15
 * 
 * @author:wangtf
 */
public class ListItem {

    /**
     * 显示的文本
     */
    private String showText;

    private String[] showTexts;

    /**
     * 显示的编码
     */
    private String showCode;

    /**
     * 实际值
     */
    private String keyValue;

    /**
     * 构造方法
     * 
     * @param showText 显示的文本
     * @param showCode 显示的编码
     * @param key key
     */
    public ListItem(String showText, String showCode, String key, String[] showTexts) {
        this.showText = showText;
        this.showTexts = showTexts;
        this.showCode = showCode;
        this.keyValue = key;
    }

    /**
     * 获得显示文本
     * 
     * @return 显示文本
     */
    public String getShowText() {
        return this.showText;
    }

    /**
     * 设置显示的文本
     * 
     * @param showText
     *            显示的文本
     */
    public void setShowText(String showText) {
        this.showText = showText;
    }

    /**
     * 获取显示的编码
     * 
     * @return 显示的编码
     */
    public String getShowCode() {
        return this.showCode;
    }

    /**
     * 设置显示的编码
     * 
     * @param showCode
     *            显示的编码
     */
    public void setShowCode(String showCode) {
        this.showCode = showCode;
    }

    /**
     * 获取显示的key
     * 
     * @return key key
     */
    public String getKeyValue() {
        return this.keyValue;
    }

    /**
     * 设置key
     * 
     * @param key
     *            key
     */
    public void setKeyValue(String key) {
        this.keyValue = key;
    }

    public String[] getShowTexts() {
        return this.showTexts;
    }

    @Override
    public String toString() {
        return this.getShowText();
    }

}
