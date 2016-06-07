package com.deltek.integration.trafficlive.domain;

import java.util.Calendar;

public class BaseTO {
	
	public Long id;
	public Integer version;
	public Calendar dateCreated;
	public Calendar dateModified;
	
	@Override
	public String toString() {
		return "BaseTO [id=" + id + ", dateCreated=" + (dateCreated != null ? dateCreated.getTime() : dateCreated) + "]";
	}

}
