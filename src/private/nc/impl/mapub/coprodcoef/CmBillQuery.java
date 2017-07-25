package nc.impl.mapub.coprodcoef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bd.framework.db.CMSqlBuilder;
import nc.impl.pubapp.pattern.data.bill.IAttributeOrderConvert;
import nc.impl.pubapp.pattern.data.table.TableIDQueryCondition;
import nc.impl.pubapp.pattern.data.vo.SchemeVOQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.pubapp.pattern.model.tool.MetaTool;
import nc.vo.pubapp.pattern.pub.Constructor;

public class CmBillQuery<E extends IBill> {

    /**
     * 单据类型Class
     */
    private Class<E> billClass;

    /**
     * 单据元数据
     */
    private IBillMeta billMeta;

    /**
     * 单据子实体的集合
     */
    private List<IVOMeta> itemList = new ArrayList<IVOMeta>();

    /**
     * 排序字段的处理器
     */
    private Map<IAttributeMeta, IAttributeOrderConvert> orderConvertIndex =
            new HashMap<IAttributeMeta, IAttributeOrderConvert>();

    /**
     * 排序字段sql加工器的索引
     */
    private Map<Class<? extends ISuperVO>, IAttributeMeta[]> orderIndex =
            new HashMap<Class<? extends ISuperVO>, IAttributeMeta[]>();

            /**
             * 单据懒加载构造函数
             *
             * @param clazz 单据类型Class
             */
            public CmBillQuery(Class<E> clazz) {
                this.init(clazz);
                this.billClass = clazz;
                IVOMeta[] children = this.billMeta.getChildren();
                for (IVOMeta meta : children) {
                    this.itemList.add(meta);
                }
            }

            /**
             * 单据懒加载构造函数
             *
             * @param clazz 单据类型Class
             * @param itemClazz 单据子实体类型Class
             */
            public CmBillQuery(Class<E> clazz, Class<? extends ISuperVO> itemClazz) {
                this.init(clazz);
                this.billClass = clazz;
                this.itemList.add(this.billMeta.getVOMeta(itemClazz));
            }

            /**
             * 单据懒加载构造函数
             *
             * @param clazz 单据类型Class
             * @param itemClazzs 单据子实体类型Class数组
             */
            public CmBillQuery(Class<E> clazz, Class<? extends ISuperVO>[] itemClazzs) {
                this.init(clazz);
                this.billClass = clazz;
                for (Class<? extends ISuperVO> itemClazz : itemClazzs) {
                    this.itemList.add(this.billMeta.getVOMeta(itemClazz));
                }
            }

            private List<String[]> constructData(ISuperVO[] vos, IAttributeMeta field) {
                int size = vos.length;
                String[] row = new String[size];
                List<String[]> list = new ArrayList<String[]>();
                list.add(row);
                for (int i = 0; i < size; i++) {
                    String name = field.getName();
                    String value = (String) vos[i].getAttributeValue(name);
                    row[i] = value;
                }

                return list;
            }

            private String getOrderSql(Class<? extends ISuperVO> clazz) {
                if (!this.orderIndex.containsKey(clazz)) {
                    return null;
                }
                CMSqlBuilder sql = new CMSqlBuilder();
                sql.append(" order by ");
                IAttributeMeta[] attributes = this.orderIndex.get(clazz);
                for (IAttributeMeta attribute : attributes) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(attribute.getColumn().getTable().getName());
                    buffer.append(".");
                    buffer.append(attribute.getColumn().getName());
                    if (this.orderConvertIndex.containsKey(attribute)) {
                        IAttributeOrderConvert convertor = this.orderConvertIndex.get(attribute);
                        String name = convertor.convert(buffer.toString());
                        sql.append(name);
                    }
                    else {
                        sql.append(buffer.toString());
                    }
                    sql.append(",");
                }
                sql.deleteLastChar();

                return sql.toString();
            }

            private void init(Class<E> clazz) {
                IBill bill = Constructor.construct(clazz);
                this.billMeta = bill.getMetaData();
            }

            @SuppressWarnings({
                "unchecked", "rawtypes"
            })
            private ISuperVO[] loadChild(Class<? extends ISuperVO> clazz, IAttributeMeta field,
                    TableIDQueryCondition conditionBuilder) {
                IAttributeMeta[] feilds = new IAttributeMeta[] {
                        field
                };
                String condition = conditionBuilder.build(feilds);

                TimeLog.logStart();
                String order = this.getOrderSql(clazz);
                VOQuery query = new VOQuery(clazz);
                ISuperVO[] vos = query.queryWithWhereKeyWord(condition, order);
                IVOMeta meta = null;
                if (vos.length > 0) {
                    meta = vos[0].getMetaData();
                }
                else {
                    ISuperVO vo = Constructor.construct(clazz);
                    meta = vo.getMetaData();
                }
                String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0088")/*
                 * @res
                 * "从数据库中加载【"
                 */
                        + meta.getLabel(); /* -=notranslate=- */
                message = message + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0089")/*
                 * @res
                 * "】数据"
                 */
                        + vos.length; /* -=notranslate=- */
                TimeLog.info(message);
                return vos;
            }

            private void loadChild(E bill) {
                // 只查询第一个表头的数据
                ISuperVO[] headers = new ISuperVO[] {
                        bill.getParent()
                };
                TimeLog.logStart();
                String[] ids = new String[] {
                        bill.getPrimaryKey()
                };
                TableIDQueryCondition conditionBuilder = new TableIDQueryCondition(ids);
                TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0090")/*
                 * @res
                 * "构造查询条件"
                 */); /*
                 * -=
                 * notranslate
                 * =
                 * -
                 */

                if (this.itemList.size() == 0) {
                    return;
                }

                IAttributeMeta parentField = this.billMeta.getParent().getPrimaryAttribute();
                IAttributeMeta[] childForeignKeys = this.billMeta.getChildForeignKeys();
                Map<IVOMeta, IAttributeMeta> foreignKeyIndex = new HashMap<IVOMeta, IAttributeMeta>();

                for (IAttributeMeta attribute : childForeignKeys) {
                    foreignKeyIndex.put(attribute.getVOMeta(), attribute);
                }

                for (IVOMeta child : this.itemList) {
                    IAttributeMeta attribute = foreignKeyIndex.get(child);
                    boolean flag = attribute.getColumn().getName().equals(parentField.getColumn().getName());
                    if (!flag) {
                        List<String[]> listData = this.constructData(headers, parentField);
                        conditionBuilder = new TableIDQueryCondition(listData);
                    }
                    Class<? extends ISuperVO> childClass = this.billMeta.getVOClass(child);
                    ISuperVO[] childrenVO = this.loadChild(childClass, attribute, conditionBuilder);
                    bill.setChildren(child, childrenVO);
                }
            }

            /**
             * 根据单据主键加载子实体数据
             *
             * @param key 单据主键
             * @return 包含子实体数据的单据，主实体只设置主键属性
             */
            public E loadChild(String key) {
                TimeLog.logStart();
                IVOMeta parent = this.billMeta.getParent();
                Class<? extends ISuperVO> parentClass = this.billMeta.getVOClass(parent);
                ISuperVO parentVO = Constructor.construct(parentClass);
                String name = parent.getPrimaryAttribute().getName();
                parentVO.setAttributeValue(name, key);

                E bill = Constructor.construct(this.billClass);
                bill.setParent(parentVO);
                TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0091")/*
                 * @res
                 * "构造表头VO"
                 */); /*
                 * -=
                 * notranslate
                 * =
                 * -
                 */

                TimeLog.logStart();
                this.loadChild(bill);
                TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0092")/*
                 * @res
                 * "查询表体VO"
                 */); /*
                 * -=
                 * notranslate
                 * =
                 * -
                 */

                return bill;
            }

            /**
             * 根据传入的查询条件查询懒加载的单据实体
             * <p>
             * 主实体：根据查询条件的设置查询出相应数目的主实体，防止内存溢出<br>
             * 子实体：当前第一个主实体的子实体才会被加载
             *
             * @param scheme 查询条件
             * @param order 排序条件 必须以order by开始
             * @return 懒加载的单据实体
             */
            @SuppressWarnings({
                "unchecked", "rawtypes"
            })
            public E[] query(IQueryScheme scheme, String order) {
                IVOMeta parentMeta = this.billMeta.getParent();
                Class<? extends ISuperVO> parentClazz = this.billMeta.getVOClass(parentMeta);
                SchemeVOQuery query = new SchemeVOQuery(parentClazz);
                query.setUsedistinct(true);
                ISuperVO[] parents = query.query(scheme, order);

                int length = parents.length;
                E[] bills = Constructor.construct(this.billClass, length);
                for (int i = 0; i < length; i++) {
                    bills[i].setParent(parents[i]);
                }
                // 加载第一个单句的子实体
                if (length > 0) {
                    /*
                     * for (E ebill : bills) {
                     * this.loadChild(ebill);
                     * }
                     */
                    this.loadChild(bills[0]);
                }
                return bills;
            }

            /**
             * 设置子表加载时的排序字段
             *
             * @param clazz 子表对应的Class类型
             * @param attributeNames 排序字段名称数组
             */
            public void setOrderAttribute(Class<? extends ISuperVO> clazz, String[] attributeNames) {
                IVOMeta meta = MetaTool.getVOMeta(clazz);
                int length = attributeNames.length;
                IAttributeMeta[] attributes = new IAttributeMeta[length];
                for (int i = 0; i < length; i++) {
                    IAttributeMeta attribute = meta.getAttribute(attributeNames[i]);
                    attributes[i] = attribute;
                    if (!attribute.isPersistence()) {
                        ExceptionUtils.unSupported();
                    }
                }
                this.orderIndex.put(clazz, attributes);
            }

            /**
             * 设置排序字段的处理器
             *
             * @param clazz 子表对应的Class类型
             * @param attributeNames 排序字段名称数组
             * @param convertor 排序字段的处理器
             */
            public void setOrderMap(Class<? extends ISuperVO> clazz, String name, IAttributeOrderConvert convertor) {
                IVOMeta voMeta = MetaTool.getVOMeta(clazz);
                IAttributeMeta attribute = voMeta.getAttribute(name);
                this.orderIndex.put(clazz, new IAttributeMeta[] {
                        attribute
                });
                this.orderConvertIndex.put(attribute, convertor);
            }

}
