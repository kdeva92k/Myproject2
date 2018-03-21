package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ndc_pkg_product")
public class NdcPkgProduct implements Serializable{
	private static final long serialVersionUID = 1L;


	@Column(name="id")
	private Integer id;
	
	
	@Column(name="pkg_product_id")
	private String pkgProductId;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="genproduct_id",referencedColumnName="genproduct_id",insertable=false, updatable=false)
	@JsonManagedReference
	CoreGenproduct coregenproduct;
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="drug_syn_id",referencedColumnName="drug_syn_id",insertable=false, updatable=false)
	@JsonManagedReference
	XrefGenproductSynRxnorm xrefGenproductSynRxnorm;

	public XrefGenproductSynRxnorm getXrefGenproductSynRxnorm() {
		return xrefGenproductSynRxnorm;
	}

	public void setXrefGenproductSynRxnorm(
			XrefGenproductSynRxnorm xrefGenproductSynRxnorm) {
		this.xrefGenproductSynRxnorm = xrefGenproductSynRxnorm;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPkgProductId() {
		return pkgProductId;
	}

	public void setPkgProductId(String pkgProductId) {
		this.pkgProductId = pkgProductId;
	}

	public String getGenproductId() {
		return genproductId;
	}

	public void setGenproductId(String genproductId) {
		this.genproductId = genproductId;
	}

	public String getCore9() {
		return core9;
	}

	public void setCore9(String core9) {
		this.core9 = core9;
	}

	public String getTradeNameId() {
		return tradeNameId;
	}

	public void setTradeNameId(String tradeNameId) {
		this.tradeNameId = tradeNameId;
	}

	public String getRxOtcStatusCode() {
		return rxOtcStatusCode;
	}

	public void setRxOtcStatusCode(String rxOtcStatusCode) {
		this.rxOtcStatusCode = rxOtcStatusCode;
	}

	public String getInnerPackSize() {
		return innerPackSize;
	}

	public void setInnerPackSize(String innerPackSize) {
		this.innerPackSize = innerPackSize;
	}

	public String getInnerPackUnitId() {
		return innerPackUnitId;
	}

	public void setInnerPackUnitId(String innerPackUnitId) {
		this.innerPackUnitId = innerPackUnitId;
	}

	public String getOuterPackSize() {
		return outerPackSize;
	}

	public void setOuterPackSize(String outerPackSize) {
		this.outerPackSize = outerPackSize;
	}

	public String getLabelerId() {
		return labelerId;
	}

	public void setLabelerId(String labelerId) {
		this.labelerId = labelerId;
	}

	public String getOrangeBookId() {
		return orangeBookId;
	}

	public void setOrangeBookId(String orangeBookId) {
		this.orangeBookId = orangeBookId;
	}

	public String getUnitDoseCode() {
		return unitDoseCode;
	}

	public void setUnitDoseCode(String unitDoseCode) {
		this.unitDoseCode = unitDoseCode;
	}

	public String getIsRepackaged() {
		return isRepackaged;
	}

	public void setIsRepackaged(String isRepackaged) {
		this.isRepackaged = isRepackaged;
	}

	public String getBrandGenericCode() {
		return brandGenericCode;
	}

	public void setBrandGenericCode(String brandGenericCode) {
		this.brandGenericCode = brandGenericCode;
	}

	public String getFormattedNdc() {
		return formattedNdc;
	}

	public void setFormattedNdc(String formattedNdc) {
		this.formattedNdc = formattedNdc;
	}

	public String getDateObsolete() {
		return dateObsolete;
	}

	public void setDateObsolete(String dateObsolete) {
		this.dateObsolete = dateObsolete;
	}

	public String getIsTop200() {
		return isTop200;
	}

	public void setIsTop200(String isTop200) {
		this.isTop200 = isTop200;
	}

	public String getShapeId() {
		return shapeId;
	}

	public void setShapeId(String shapeId) {
		this.shapeId = shapeId;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public String getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(String flavorId) {
		this.flavorId = flavorId;
	}

	public String getAddlDoseformId() {
		return addlDoseformId;
	}

	public void setAddlDoseformId(String addlDoseformId) {
		this.addlDoseformId = addlDoseformId;
	}

	public String getMarkingSide1() {
		return markingSide1;
	}

	public void setMarkingSide1(String markingSide1) {
		this.markingSide1 = markingSide1;
	}

	public String getMarkingSide2() {
		return markingSide2;
	}

	public void setMarkingSide2(String markingSide2) {
		this.markingSide2 = markingSide2;
	}

	public String getIsScored() {
		return isScored;
	}

	public void setIsScored(String isScored) {
		this.isScored = isScored;
	}

	public String getImageFilename() {
		return imageFilename;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}

	public String getSearchMarkings() {
		return searchMarkings;
	}

	public void setSearchMarkings(String searchMarkings) {
		this.searchMarkings = searchMarkings;
	}

	public String getCore10() {
		return core10;
	}

	public void setCore10(String core10) {
		this.core10 = core10;
	}

	public String getFormattedCore10() {
		return formattedCore10;
	}

	public void setFormattedCore10(String formattedCore10) {
		this.formattedCore10 = formattedCore10;
	}

	public String getDrugSynId() {
		return drugSynId;
	}

	public void setDrugSynId(String drugSynId) {
		this.drugSynId = drugSynId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	@Id
	@Column(name="genproduct_id")
	private String genproductId;

	@Column(name="core_9")
	private String core9;

	@Column(name="trade_name_id")
	private String tradeNameId;

	@Column(name="rx_otc_status_code")
	private String rxOtcStatusCode;

	@Column(name="inner_pack_size")
	private String innerPackSize;

	@Column(name="inner_pack_unit_id")
	private String innerPackUnitId;

	@Column(name="outer_pack_size")
	private String outerPackSize;

	@Column(name="labeler_id")
	private String labelerId;

	@Column(name="orange_book_id")
	private String orangeBookId;

	@Column(name="unit_dose_code")
	private String unitDoseCode;

	@Column(name="is_repackaged")
	private String isRepackaged;

	@Column(name="brand_generic_code")
	private String brandGenericCode;

	@Column(name="formatted_ndc")
	private String formattedNdc;

	@Column(name="date_obsolete")
	private String dateObsolete;

	@Column(name="is_top_200")
	private String isTop200;

	@Column(name="shape_id")
	private String shapeId;

	@Column(name="color_id")
	private String colorId;

	@Column(name="flavor_id")
	private String flavorId;

	@Column(name="addl_doseform_id")
	private String addlDoseformId;

	@Column(name="marking_side_1")
	private String markingSide1;

	@Column(name="marking_side_2")
	private String markingSide2;

	@Column(name="is_scored")
	private String isScored;

	@Column(name="image_filename")
	private String imageFilename;

	@Column(name="search_markings")
	private String searchMarkings;

	@Column(name="core_10")
	private String core10;

	@Column(name="formatted_core_10")
	private String formattedCore10;

	@Column(name="drug_syn_id")
	private String drugSynId;

	@Column(name="create_date")
	private String createDate;
}