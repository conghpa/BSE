package com.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "product_category")
public class ProductCategory implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_CODE", referencedColumnName="PRODUCT_CODE", insertable = false, updatable = false)
	private Product product;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,  generator = "retailerRaw_seq")
	@SequenceGenerator(name = "retailerRaw_seq", sequenceName = "product_category_seq", allocationSize=1)
	@Column(name="SEQ")
	private int seq;
	
	@Column(name="CATEGORY_CODE")
	private String categoryCode;
	
	@Column(name="PRODUCT_CODE")
	private String productCode;
	
	@Column(name="CREATER")
	private String creater;
	
	@Column(name="UPDATER")
	private String updater;
	
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="DUE_DATE")
	private Date dueDate;
	
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	@Column(name="UPDATE_DATE")
	private Date updateDate;
	
	@Column(name="DELETE_FLG")
	private int deleteFlg;
	
	public int getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
