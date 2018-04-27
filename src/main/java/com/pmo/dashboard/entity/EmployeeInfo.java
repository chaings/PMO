package com.pmo.dashboard.entity;

public class EmployeeInfo
{
    private String employeeId;
    private String hsbcStaffId;
    private String eHr;
    private String staffName;
    private String ln;
    private String csDeptName;
    private String csSubDeptName;
    private String lob;
    private String resourceStatus;
    private String rmUserId;
    private String nickname;
    private String engagementType;
    
    private String chsoftiProNumber;
    private String chsoftiProStartdate;
    private String chsoftiProName;
    
    
    
	public String getChsoftiProNumber() {
		return chsoftiProNumber;
	}
	public void setChsoftiProNumber(String chsoftiProNumber) {
		this.chsoftiProNumber = chsoftiProNumber;
	}
	public String getChsoftiProStartdate() {
		return chsoftiProStartdate;
	}
	public void setChsoftiProStartdate(String chsoftiProStartdate) {
		this.chsoftiProStartdate = chsoftiProStartdate;
	}
	public String getChsoftiProName() {
		return chsoftiProName;
	}
	public void setChsoftiProName(String chsoftiProName) {
		this.chsoftiProName = chsoftiProName;
	}
	public String getEngagementType() {
		return engagementType;
	}
	public void setEngagementType(String engagementType) {
		this.engagementType = engagementType;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmployeeId()
    {
        return employeeId;
    }
    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }
    public String getHsbcStaffId()
    {
        return hsbcStaffId;
    }
    public void setHsbcStaffId(String hsbcStaffId)
    {
        this.hsbcStaffId = hsbcStaffId;
    }
    public String geteHr()
    {
        return eHr;
    }
    public void seteHr(String eHr)
    {
        this.eHr = eHr;
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
    public String getCsDeptName()
    {
        return csDeptName;
    }
    public void setCsDeptName(String csDeptName)
    {
        this.csDeptName = csDeptName;
    }
    public String getCsSubDeptName()
    {
        return csSubDeptName;
    }
    public void setCsSubDeptName(String csSubDeptName)
    {
        this.csSubDeptName = csSubDeptName;
    }
    public String getLob()
    {
        return lob;
    }
    public void setLob(String lob)
    {
        this.lob = lob;
    }
    public String getResourceStatus()
    {
        return resourceStatus;
    }
    public void setResourceStatus(String resourceStatus)
    {
        this.resourceStatus = resourceStatus;
    }
    
    
    public String getRmUserId() {
		return rmUserId;
	}
	public void setRmUserId(String rmUserId) {
		this.rmUserId = rmUserId;
	}
	public EmployeeInfo()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public EmployeeInfo(String employeeId, String hsbcStaffId, String eHr,
            String staffName, String ln, String csDeptName,
            String csSubDeptName, String lob)
    {
        super();
        this.employeeId = employeeId;
        this.hsbcStaffId = hsbcStaffId;
        this.eHr = eHr;
        this.staffName = staffName;
        this.ln = ln;
        this.csDeptName = csDeptName;
        this.csSubDeptName = csSubDeptName;
        this.lob = lob;
    }
    public EmployeeInfo(String employeeId, String hsbcStaffId, String eHr,
            String staffName, String ln, String csDeptName,
            String csSubDeptName, String lob, String resourceStatus)
    {
        super();
        this.employeeId = employeeId;
        this.hsbcStaffId = hsbcStaffId;
        this.eHr = eHr;
        this.staffName = staffName;
        this.ln = ln;
        this.csDeptName = csDeptName;
        this.csSubDeptName = csSubDeptName;
        this.lob = lob;
        this.resourceStatus = resourceStatus;
    }
    
    
    
}
