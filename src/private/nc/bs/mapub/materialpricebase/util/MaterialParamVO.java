package nc.bs.mapub.materialpricebase.util;

import nc.vo.cmpub.framework.assistant.CMAssInfoHeadVO;

public class MaterialParamVO extends CMAssInfoHeadVO {

    private static final long serialVersionUID = 1L;

    // 出入库类型
    private Integer finstoragetype;

    private String ccostcenterid;

    private String cmaterialid;

    public Integer getFinstoragetype() {
        return this.finstoragetype;
    }

    public void setFinstoragetype(Integer finstoragetype) {
        this.finstoragetype = finstoragetype;
    }

    public String getCcostcenterid() {
        return this.ccostcenterid;
    }

    public void setCcostcenterid(String ccostcenterid) {
        this.ccostcenterid = ccostcenterid;
    }

    public String getCmaterialid() {
        return this.cmaterialid;
    }

    public void setCmaterialid(String cmaterialid) {
        this.cmaterialid = cmaterialid;
    }

    @Override
    public int hashCode() {
        int hashcode = 17;
        hashcode += this.finstoragetype == null ? 31 * hashcode : 31 * hashcode + this.finstoragetype.hashCode();
        hashcode += this.ccostcenterid == null ? 31 * hashcode : 31 * hashcode + this.ccostcenterid.hashCode();
        hashcode += this.cmaterialid == null ? 31 * hashcode : 31 * hashcode + this.cmaterialid.hashCode();

        hashcode +=
                CMAssInfoHeadVO.CPROJECTID == null ? 31 * hashcode : 31 * hashcode
                        + CMAssInfoHeadVO.CPROJECTID.hashCode();
        hashcode +=
                CMAssInfoHeadVO.CVENDORID == null ? 31 * hashcode : 31 * hashcode
                        + CMAssInfoHeadVO.CVENDORID.hashCode();
        hashcode +=
                CMAssInfoHeadVO.CPRODUCTORID == null ? 31 * hashcode : 31 * hashcode
                        + CMAssInfoHeadVO.CPRODUCTORID.hashCode();
        hashcode +=
                CMAssInfoHeadVO.CCUSTOMERID == null ? 31 * hashcode : 31 * hashcode
                        + CMAssInfoHeadVO.CCUSTOMERID.hashCode();

        hashcode += CMAssInfoHeadVO.VFREE1 == null ? 31 * hashcode : 31 * hashcode + CMAssInfoHeadVO.VFREE1.hashCode();
        hashcode += CMAssInfoHeadVO.VFREE2 == null ? 31 * hashcode : 31 * hashcode + CMAssInfoHeadVO.VFREE2.hashCode();
        hashcode += CMAssInfoHeadVO.VFREE3 == null ? 31 * hashcode : 31 * hashcode + CMAssInfoHeadVO.VFREE3.hashCode();
        hashcode += CMAssInfoHeadVO.VFREE4 == null ? 31 * hashcode : 31 * hashcode + CMAssInfoHeadVO.VFREE4.hashCode();
        hashcode += CMAssInfoHeadVO.VFREE5 == null ? 31 * hashcode : 31 * hashcode + CMAssInfoHeadVO.VFREE5.hashCode();
        hashcode += CMAssInfoHeadVO.VFREE6 == null ? 31 * hashcode : 31 * hashcode + CMAssInfoHeadVO.VFREE6.hashCode();
        hashcode += CMAssInfoHeadVO.VFREE7 == null ? 31 * hashcode : 31 * hashcode + CMAssInfoHeadVO.VFREE7.hashCode();
        hashcode += CMAssInfoHeadVO.VFREE8 == null ? 31 * hashcode : 31 * hashcode + CMAssInfoHeadVO.VFREE8.hashCode();
        hashcode += CMAssInfoHeadVO.VFREE9 == null ? 31 * hashcode : 31 * hashcode + CMAssInfoHeadVO.VFREE9.hashCode();
        hashcode +=
                CMAssInfoHeadVO.VFREE10 == null ? 31 * hashcode : 31 * hashcode + CMAssInfoHeadVO.VFREE10.hashCode();
        return hashcode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MaterialParamVO)) {
            return false;
        }
        MaterialParamVO vo = (MaterialParamVO) obj;
        if (vo.getFinstoragetype() == null ? this.getFinstoragetype() != null : !vo.getFinstoragetype().equals(
                this.getFinstoragetype())) {
            return false;
        }
        if (vo.getCcostcenterid() == null ? this.getCcostcenterid() != null : !vo.getCcostcenterid().equals(
                this.getCcostcenterid())) {
            return false;
        }
        if (vo.getCmaterialid() == null ? this.getCmaterialid() != null : !vo.getCmaterialid().equals(
                this.getCmaterialid())) {
            return false;
        }
        if (vo.getCprojectid() == null ? this.getCprojectid() != null : !vo.getCprojectid()
                .equals(this.getCprojectid())) {
            return false;
        }
        if (vo.getCvendorid() == null ? this.getCvendorid() != null : !vo.getCvendorid().equals(this.getCvendorid())) {
            return false;
        }
        if (vo.getCproductorid() == null ? this.getCproductorid() != null : !vo.getCproductorid().equals(
                this.getCproductorid())) {
            return false;
        }
        if (vo.getCcustomerid() == null ? this.getCcustomerid() != null : !vo.getCcustomerid().equals(
                this.getCcustomerid())) {
            return false;
        }
        if (vo.getVfree1() == null ? this.getVfree1() != null : !vo.getVfree1().equals(this.getVfree1())) {
            return false;
        }
        if (vo.getVfree2() == null ? this.getVfree2() != null : !vo.getVfree2().equals(this.getVfree2())) {
            return false;
        }
        if (vo.getVfree3() == null ? this.getVfree3() != null : !vo.getVfree3().equals(this.getVfree3())) {
            return false;
        }
        if (vo.getVfree4() == null ? this.getVfree4() != null : !vo.getVfree4().equals(this.getVfree4())) {
            return false;
        }
        if (vo.getVfree5() == null ? this.getVfree5() != null : !vo.getVfree5().equals(this.getVfree5())) {
            return false;
        }
        if (vo.getVfree6() == null ? this.getVfree6() != null : !vo.getVfree6().equals(this.getVfree6())) {
            return false;
        }
        if (vo.getVfree7() == null ? this.getVfree7() != null : !vo.getVfree7().equals(this.getVfree7())) {
            return false;
        }
        if (vo.getVfree8() == null ? this.getVfree8() != null : !vo.getVfree8().equals(this.getVfree8())) {
            return false;
        }
        if (vo.getVfree9() == null ? this.getVfree9() != null : !vo.getVfree9().equals(this.getVfree9())) {
            return false;
        }
        if (vo.getVfree10() == null ? this.getVfree10() != null : !vo.getVfree10().equals(this.getVfree10())) {
            return false;
        }
        return true;
    }

}
