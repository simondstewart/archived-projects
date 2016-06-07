package com.deltek.integration.trafficlive.domain;

import java.math.BigDecimal;
import java.util.Calendar;

public class JobTaskTimeEntryTO extends BaseTO {

	public Identifier jobTaskId;
	public Boolean billable;
	public Boolean exported;
	public Boolean lockedByApproval;
	public String comment;
	public Calendar endTime;
	public Integer minutes;
	public Identifier trafficEmployeeId;
	public String taskDescription;
	public Boolean taskComplete;
	public MoneyTO taskRate;
	public MoneyTO valueOfTimeEntry;
	public Identifier jobId;
	public Identifier allocationGroupId;
	public Identifier chargeBandId;
	public MoneyTO timeEntryCost;
	public MoneyTO timeEntryPersonalRate;
	public String jobStageDescription;
    public Identifier lockedByApprovalEmployeeId;
    public Calendar lockedByApprovalDate;
    public String exportError;
    public BigDecimal workPoints;
    public String externalCode;
    public String secondaryExternalCode;
    public Boolean submitted;
    public Boolean rejected;
	public Boolean readyForExport;
    public String rejectedComment;
}
