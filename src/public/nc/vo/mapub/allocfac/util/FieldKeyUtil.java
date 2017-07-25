package nc.vo.mapub.allocfac.util;

/**
 * @since v6.3
 * @version 2013-9-11 ����04:23:40
 * @author xionghuic
 */
public class FieldKeyUtil {
    private String id;

    private String fields;

    public FieldKeyUtil() {

    }

    public FieldKeyUtil(String id, String fields) {
        this.id = id;
        this.fields = fields;
    }

    /**
     * ��� id ������ֵ
     */
    public String getId() {
        return this.id;
    }

    /**
     * ���� id ������ֵ
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * ��� fields ������ֵ
     */
    public String getFields() {
        return this.fields;
    }

    /**
     * ���� fields ������ֵ
     */
    public void setFields(String fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FieldKeyUtil) {
            FieldKeyUtil vo = (FieldKeyUtil) obj;
            boolean idEquals = this.id == null ? vo.getId() == null : this.id.equals(vo.getId());
            boolean fieldEquals = this.fields == null ? vo.getFields() == null : this.fields.equals(vo.getFields());
            return idEquals && fieldEquals;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + this.id == null ? 0 : this.id.hashCode();
        hash = hash * 31 + this.fields == null ? 0 : this.fields.hashCode();
        return hash;
    }
}
