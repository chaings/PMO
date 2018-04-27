package com.pmo.dashboard.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Employee
{
    
    private String employeeId;
    private String eHr;
    private String lob;
    private String hsbcStaffId;
    private String staffName;
    private String ln;
    private String staffRegion;
    private String staffLocation;
    private String locationType;
    private String onshoreOrOffshore;
    private String csSubDept;
    private String hsbcSubDept;
    private String projectName;
    private String projectManager;
    private String sow;
    private String sowExpiredDate;
    private String staffCategory;
    private String engagementType;
    private String hsbcDOJ;
    private String graduationDate;
    private String role;
    private String skill;
    private String billingCurrency;
    private String billRate;
    private String resourceStatus;
    private String terminatedDate;
    private String terminationReason;
    private String interviewStatus;
    
    private  String email ;
    private String gbGf;
    private String entryDate;
    private String rmUserId;
    private String csSubDeptName;
    
    private Timestamp createTime;
    private Timestamp updateTime;
    private String nickname;
    
    private String chsoftiProNumber;
    private String chsoftiProStartdate;
    private String chsoftiProName;
    
    private String itindustryWorkYear;
    
	public String getChsoftiProStartdate() {
		return chsoftiProStartdate;
	}
	public void setChsoftiProStartdate(String chsoftiProStartdate) {
		this.chsoftiProStartdate = chsoftiProStartdate;
	}
	public String getChsoftiProNumber() {
		return chsoftiProNumber;
	}
	public void setChsoftiProNumber(String chsoftiProNumber) {
		this.chsoftiProNumber = chsoftiProNumber;
	}
	public String getChsoftiProName() {
		return chsoftiProName;
	}
	public void setChsoftiProName(String chsoftiProName) {
		this.chsoftiProName = chsoftiProName;
	}
	public String getItindustryWorkYear() {
		return itindustryWorkYear;
	}
	public void setItindustryWorkYear(String itindustryWorkYear) {
		this.itindustryWorkYear = itindustryWorkYear;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCsSubDeptName() {
		return csSubDeptName;
	}
	public void setCsSubDeptName(String csSubDeptName) {
		this.csSubDeptName = csSubDeptName;
	}
	public String getRmUserId() {
		return rmUserId;
	}
	public void setRmUserId(String rmUserId) {
		this.rmUserId = rmUserId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGbGf() {
		return gbGf;
	}
	public void setGbGf(String gbGf) {
		this.gbGf = gbGf;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getEmployeeId()
    {
        return employeeId;
    }
    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }
    public String geteHr()
    {
        return eHr;
    }
    public void seteHr(String eHr)
    {
        this.eHr = eHr;
    }
    public String getLob()
    {
        return lob;
    }
    public void setLob(String lob)
    {
        this.lob = lob;
    }
    public String getHsbcStaffId()
    {
        return hsbcStaffId;
    }
    public void setHsbcStaffId(String hsbcStaffId)
    {
        this.hsbcStaffId = hsbcStaffId;
    }
    public String getStaffName()
    {
        return staffName;
    }
    public void setStaffName(String staffName)
    {
        this.staffName = staffName;
    }
    public String getLn()
    {
        return ln;
    }
    public void setLn(String ln)
    {
        this.ln = ln;
    }
    public String getStaffRegion()
    {
        return staffRegion;
    }
    public void setStaffRegion(String staffRegion)
    {
        this.staffRegion = staffRegion;
    }
    public String getStaffLocation()
    {
        return staffLocation;
    }
    public void setStaffLocation(String staffLocation)
    {
        this.staffLocation = staffLocation;
    }
    public String getLocationType()
    {
        return locationType;
    }
    public void setLocationType(String locationType)
    {
        this.locationType = locationType;
    }
    public String getOnshoreOrOffshore()
    {
        return onshoreOrOffshore;
    }
    public void setOnshoreOrOffshore(String onshoreOrOffshore)
    {
        this.onshoreOrOffshore = onshoreOrOffshore;
    }
    public String getCsSubDept()
    {
        return csSubDept;
    }
    public void setCsSubDept(String csSubDept)
    {
        this.csSubDept = csSubDept;
    }
    public String getHsbcSubDept()
    {
        return hsbcSubDept;
    }
    public void setHsbcSubDept(String hsbcSubDept)
    {
        this.hsbcSubDept = hsbcSubDept;
    }
    public String getProjectName()
    {
        return projectName;
    }
    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }
    public String getProjectManager()
    {
        return projectManager;
    }
    public void setProjectManager(String projectManager)
    {
        this.projectManager = projectManager;
    }
    public String getSow()
    {
        return sow;
    }
    public void setSow(String sow)
    {
        this.sow = sow;
    }
    public String getSowExpiredDate()
    {
        return sowExpiredDate;
    }
    public void setSowExpiredDate(String sowExpiredDate)
    {
        this.sowExpiredDate = sowExpiredDate;
    }
    public String getStaffCategory()
    {
        return staffCategory;
    }
    public void setStaffCategory(String staffCategory)
    {
        this.staffCategory = staffCategory;
    }
    public String getEngagementType()
    {
        return engagementType;
    }
    public void setEngagementType(String engagementType)
    {
        this.engagementType = engagementType;
    }
    
	public String getHsbcDOJ()
    {
        return hsbcDOJ;
    }
    public void setHsbcDOJ(String hsbcDOJ)
    {
        this.hsbcDOJ = hsbcDOJ;
    }
    public String getGraduationDate()
    {
        return graduationDate;
    }
    public void setGraduationDate(String graduationDate)
    {
        this.graduationDate = graduationDate;
    }
    public String getRole()
    {
        return role;
    }
    public void setRole(String role)
    {
        this.role = role;
    }
    public String getSkill()
    {
        return skill;
    }
    public void setSkill(String skill)
    {
        this.skill = skill;
    }
    public String getBillingCurrency()
    {
        return billingCurrency;
    }
    public void setBillingCurrency(String billingCurrency)
    {
        this.billingCurrency = billingCurrency;
    }
    public String getBillRate()
    {
        return billRate;
    }
    public void setBillRate(String billRate)
    {
        this.billRate = billRate;
    }
    public String getResourceStatus()
    {
        return resourceStatus;
    }
    public void setResourceStatus(String resourceStatus)
    {
        this.resourceStatus = resourceStatus;
    }
    public String getTerminatedDate()
    {
        return terminatedDate;
    }
    public void setTerminatedDate(String terminatedDate)
    {
        this.terminatedDate = terminatedDate;
    }
    public String getTerminationReason()
    {
        return terminationReason;
    }
    public void setTerminationReason(String terminationReason)
    {
        this.terminationReason = terminationReason;
    }
    public String getInterviewStatus()
    {
        return interviewStatus;
    }
    public void setInterviewStatus(String interviewStatus)
    {
        this.interviewStatus = interviewStatus;
    }
    public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Employee()
    {
        super();
    }
    public Employee(String employeeId, String eHr, String lob,
            String hsbcStaffId, String staffName, String ln, String staffRegion,
            String staffLocation, String locationType, String onshoreOrOffshore,
            String csSubDept, String hsbcSubDept, String projectName,
            String projectManager, String sow, String sowExpiredDate,
            String staffCategory, String engagementType, String hsbcDOJ,
            String graduationDate, String role, String skill,
            String billingCurrency, String billRate, String resourceStatus,
            String terminatedDate, String terminationReason,String email, String gbGf,
            String entryDate,String rmUserId, Timestamp createTime, Timestamp updateTime,String itindustryWorkYear,
            String chsoftiProNumber,String chsoftiProStartDate, String chsoftiProName)
    {
        super();
        this.employeeId = employeeId;
        this.eHr = eHr;
        this.lob = lob;
        this.hsbcStaffId = hsbcStaffId;
        this.staffName = staffName;
        this.ln = ln;
        this.staffRegion = staffRegion;
        this.staffLocation = staffLocation;
        this.locationType = locationType;
        this.onshoreOrOffshore = onshoreOrOffshore;
        this.csSubDept = csSubDept;
        this.hsbcSubDept = hsbcSubDept;
        this.projectName = projectName;
        this.projectManager = projectManager;
        this.sow = sow;
        this.sowExpiredDate = sowExpiredDate;
        this.staffCategory = staffCategory;
        this.engagementType = engagementType;
        this.hsbcDOJ = hsbcDOJ;
        this.graduationDate = graduationDate;
        this.role = role;
        this.skill = skill;
        this.billingCurrency = billingCurrency;
        this.billRate = billRate;
        this.resourceStatus = resourceStatus;
        this.terminatedDate = terminatedDate;
        this.terminationReason = terminationReason;
        this.email = email;
        this.gbGf = gbGf;
        this.entryDate = entryDate;
        this.rmUserId = rmUserId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.itindustryWorkYear = itindustryWorkYear;
        this.chsoftiProNumber = chsoftiProNumber;
        this.chsoftiProStartdate = chsoftiProStartDate;
        this.chsoftiProName = chsoftiProName;
    }
    public Employee(String employeeId, String eHr, String lob,
            String hsbcStaffId, String staffName, String ln, String staffRegion,
            String staffLocation, String locationType, String onshoreOrOffshore,
            String csSubDept, String hsbcSubDept, String projectName,
            String projectManager, String sow, String sowExpiredDate,
            String staffCategory, String engagementType, String hsbcDOJ,
            String graduationDate, String role, String skill,
            String billingCurrency, String billRate, String resourceStatus,
            String terminatedDate, String terminationReason,String email, String gbGf,
            String entryDate,String rmUserId, Timestamp updateTime,String itindustryWorkYear,
            String chsoftiProNumber,String chsoftiProStartDate, String chsoftiProName
    		)
    {
        super();
        this.employeeId = employeeId;
        this.eHr = eHr;
        this.lob = lob;
        this.hsbcStaffId = hsbcStaffId;
        this.staffName = staffName;
        this.ln = ln;
        this.staffRegion = staffRegion;
        this.staffLocation = staffLocation;
        this.locationType = locationType;
        this.onshoreOrOffshore = onshoreOrOffshore;
        this.csSubDept = csSubDept;
        this.hsbcSubDept = hsbcSubDept;
        this.projectName = projectName;
        this.projectManager = projectManager;
        this.sow = sow;
        this.sowExpiredDate = sowExpiredDate;
        this.staffCategory = staffCategory;
        this.engagementType = engagementType;
        this.hsbcDOJ = hsbcDOJ;
        this.graduationDate = graduationDate;
        this.role = role;
        this.skill = skill;
        this.billingCurrency = billingCurrency;
        this.billRate = billRate;
        this.resourceStatus = resourceStatus;
        this.terminatedDate = terminatedDate;
        this.terminationReason = terminationReason;
        this.email = email;
        this.gbGf = gbGf;
        this.entryDate = entryDate;
        this.rmUserId = rmUserId;
        this.updateTime = updateTime;
        this.itindustryWorkYear = itindustryWorkYear;
        this.chsoftiProNumber = chsoftiProNumber;
        this.chsoftiProStartdate = chsoftiProStartDate;
        this.chsoftiProName = chsoftiProName;
    }
    
}
