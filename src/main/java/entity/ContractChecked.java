/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ContractChecked   implements Serializable {

	private static final long serialVersionUID = 1L;



	/**
	* 完善合同ID
	*/
		private Long contractCompleteId;
	/**
	* 已核准买方名称（英文）
	*/
		private String buyerName;
	/**
	* 已核准买方地址（英文）
	*/
		private String buyerAddress;
	/**
	* 融资比例上限
	*/
		private BigDecimal upperRatio;
	/**
	* 已核准买方限额（币种、金额）
	*/
		private String quotaChecked;
	/**
	* 融资期限
	*/
		private Integer loanDays;
	/**
	* 已核准买方额度到期日
	*/
		private Date quotaEndDate;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getContractCompleteId() {
		return contractCompleteId;
	}

	public void setContractCompleteId(Long contractCompleteId) {
		this.contractCompleteId = contractCompleteId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public BigDecimal getUpperRatio() {
		return upperRatio;
	}

	public void setUpperRatio(BigDecimal upperRatio) {
		this.upperRatio = upperRatio;
	}

	public String getQuotaChecked() {
		return quotaChecked;
	}

	public void setQuotaChecked(String quotaChecked) {
		this.quotaChecked = quotaChecked;
	}

	public Integer getLoanDays() {
		return loanDays;
	}

	public void setLoanDays(Integer loanDays) {
		this.loanDays = loanDays;
	}

	public Date getQuotaEndDate() {
		return quotaEndDate;
	}

	public void setQuotaEndDate(Date quotaEndDate) {
		this.quotaEndDate = quotaEndDate;
	}
}
