/* indexcode: i_mapub_mbase_o */
create  index i_mapub_mbase_o on mapub_materialpricebase (pk_org asc)
;

/* indexcode: i_mapub_mbaseb_o */
create  index i_mapub_mbaseb_o on mapub_materialpricebase_b (pk_org asc)
;

/* indexcode: i_mapub_mbaseb_fk */
create  index i_mapub_mbaseb_fk on mapub_materialpricebase_b (cmaterialpriceid asc)
;

/* indexcode: i_cm_allocfac_o */
create  index i_cm_allocfac_o on cm_allocfac (pk_org asc)
;

/* indexcode: i_cm_allocfacb_o */
create  index i_cm_allocfacb_o on cm_allocfac_b (pk_org asc)
;

/* indexcode: i_cm_allocfacb_fk */
create  index i_cm_allocfacb_fk on cm_allocfac_b (callocfacid asc)
;

/* indexcode: i_mapub_coeb_o */
create  index i_mapub_coeb_o on mapub_coprodcoef_b (pk_org asc)
;

/* indexcode: i_mapub_coeb_fk */
create  index i_mapub_coeb_fk on mapub_coprodcoef_b (ccoprodcoefid asc)
;

/* indexcode: i_mapub_coe_o */
create  index i_mapub_coe_o on mapub_coprodcoef (pk_org asc)
;

