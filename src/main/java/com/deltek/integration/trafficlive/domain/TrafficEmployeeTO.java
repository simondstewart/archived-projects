package com.deltek.integration.trafficlive.domain;

public class TrafficEmployeeTO extends BaseTO {

	public Long locationId;
	public Long departmentId;
	public Long ownerCompanyId;
	public Boolean active;
	public Long userId;
	public String userName;
	public String externalCode;
	
	@Override
	public String toString() {
		return "TrafficEmployeeTO [userName=" + userName + ", externalCode=" + externalCode + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
