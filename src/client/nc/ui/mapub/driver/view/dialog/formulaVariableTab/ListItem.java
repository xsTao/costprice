package nc.ui.mapub.driver.view.dialog.formulaVariableTab;

/**
 * <b> �б��е���Ŀ </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-4-15
 * 
 * @author:wangtf
 */
public class ListItem {

    /**
     * ��ʾ���ı�
     */
    private String showText;

    private String[] showTexts;

    /**
     * ��ʾ�ı���
     */
    private String showCode;

    /**
     * ʵ��ֵ
     */
    private String keyValue;

    /**
     * ���췽��
     * 
     * @param showText ��ʾ���ı�
     * @param showCode ��ʾ�ı���
     * @param key key
     */
    public ListItem(String showText, String showCode, String key, String[] showTexts) {
        this.showText = showText;
        this.showTexts = showTexts;
        this.showCode = showCode;
        this.keyValue = key;
    }

    /**
     * �����ʾ�ı�
     * 
     * @return ��ʾ�ı�
     */
    public String getShowText() {
        return this.showText;
    }

    /**
     * ������ʾ���ı�
     * 
     * @param showText
     *            ��ʾ���ı�
     */
    public void setShowText(String showText) {
        this.showText = showText;
    }

    /**
     * ��ȡ��ʾ�ı���
     * 
     * @return ��ʾ�ı���
     */
    public String getShowCode() {
        return this.showCode;
    }

    /**
     * ������ʾ�ı���
     * 
     * @param showCode
     *            ��ʾ�ı���
     */
    public void setShowCode(String showCode) {
        this.showCode = showCode;
    }

    /**
     * ��ȡ��ʾ��key
     * 
     * @return key key
     */
    public String getKeyValue() {
        return this.keyValue;
    }

    /**
     * ����key
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
