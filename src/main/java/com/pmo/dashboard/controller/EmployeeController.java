package com.pmo.dashboard.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.CandidateInfo;
import com.pmo.dashboard.entity.Demand;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeeGraphParam;
import com.pmo.dashboard.entity.EmployeeLog;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pmo.dashboard.entity.HSBCDept;
import com.pmo.dashboard.entity.StayinCandidate;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Constants;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.CandidateService;
import com.pom.dashboard.service.EmployeeLogService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.HSBCDeptService;
import com.pom.dashboard.service.HSBCProjectService;
import com.pom.dashboard.service.UserService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Controller
@RequestMapping(value="/employee")
public class EmployeeController {

	private static Logger logger = LoggerFactory
            .getLogger(EmployeeController.class);

	@Resource
    private EmployeeService employeeService;

	@Resource
    private CSDeptService csDeptService;

	@Resource
    private HSBCDeptService hsbcDeptService;

	@Resource
    private HSBCProjectService hsbcProjectService;

	@Resource
	private UserService userService;

	@Resource
	private EmployeeLogService  employeeLogService;

	@Resource
	private CandidateService candidateService;



    @RequestMapping("/welcome")
    public String welcome(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "welcome";
    }

    @SuppressWarnings("unchecked")
	@RequestMapping("/index")
    public String index(final HttpServletRequest request,
            final HttpServletResponse response,Model model,String candId)
    {
    	Employee em = new Employee();
		List<StayinCandidate> list1 = (List<StayinCandidate>) request.getSession().getAttribute("candidateList");
    	List<Demand> list2 = (List<Demand>) request.getSession().getAttribute("demandList");
    	//拿到需求和待入职的人员信息  gkf
    	if(list1!=null &&list1.size()>0&&list2!=null &&list2.size()>0&&candId!=null) {
    		for(StayinCandidate candidate :list1 ) {
    			if(candidate.getCandidateId().equals(candId)) {
    				em.setStaffName(candidate.getCandidateName());
    				//em.setBillRate();
    				em.setGraduationDate(candidate.getGraduateDate());
    			}
    		}
    		for(Demand demand:list2) {
    			if(candId.equals(demand.getCandidateId())) {
    				Demand demandinfo=(Demand) request.getAttribute("onboardInfo");
    				em.setStaffRegion(demand.getLocation());
    				em.setStaffLocation("China");
    				em.setSow(demand.getDoNumber());
    				em.setRole(demand.getPosition());
    				em.setSkill(demand.getSkill());
    				em.setResourceStatus("Active");
    			}
    		}
    		model.addAttribute("employee", em);
			return "OnboardEmployeeInsert";
    	}

        return "indexa";
    }

    @RequestMapping("/employeeInfo")
    public String employeeInfo(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/employeeInfo";
    }

    @RequestMapping("/updateEmployeeInfo")
    public String updateEmployeeInfo(final HttpServletRequest request,
            final HttpServletResponse response,String type)
    {
        String employeeId = request.getParameter("employeeId");
        request.setAttribute("employeeId", employeeId);

        if("1".equals(type)) {
        	return "employee/updateEmployeeInfo";
    	}else if("2".equals(type)) {
    		return "employee/updateEmployeeInfo2";
    	}else {
    		return "employee/updateEmployeeInfo3";
    	}
    }
    public static Timestamp str2TimeStamp(String date){ Timestamp ts = new Timestamp(System.currentTimeMillis()); try { ts = Timestamp.valueOf(date); } catch (Exception e) { e.printStackTrace(); } return ts; }

    @RequestMapping("/queryEmployeeById")
    @ResponseBody
    public Object queryEmployeeById(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String employeeId = request.getParameter("employeeId");

        Employee employee = employeeService.queryEmployeeById(employeeId);

        return employee;
    }


    @RequestMapping("/addEmployee")
    @ResponseBody
    public boolean addEmployee(final HttpServletRequest request,
            final HttpServletResponse response)
    {

        String employeeId = Utils.getUUID();
        String eHr = request.getParameter("eHr");
        String lob = request.getParameter("lob");
        String hsbcStaffId = request.getParameter("hsbcStaffId");
        String staffName = request.getParameter("staffName");
        String LN = request.getParameter("LN");
        String staffRegion = request.getParameter("staffRegion");
        String staffLocation = request.getParameter("staffLocation");
        String locationType = request.getParameter("locationType");
        String onshoreOrOffshore = request.getParameter("onshoreOrOffshore");
        String csSubDept = request.getParameter("csSubDept");
        String hsbcSubDept = request.getParameter("hsbcSubDept");
        String projectName = request.getParameter("projectName");
        String projectManager = request.getParameter("projectManager");
        String sow = request.getParameter("sow");
        String sowExpiredDate = request.getParameter("sowExpiredDate");
        String staffCategory = request.getParameter("staffCategory");
        String engagementType = request.getParameter("engagementType");
        String hsbcDOJ = request.getParameter("hsbcDOJ");
        String graduationDate = request.getParameter("graduationDate");
        String role = request.getParameter("role");
        String skill = request.getParameter("skill");
        String billingCurrency = request.getParameter("billingCurrency");
        String billRate = request.getParameter("billRate");
        String resourceStatus = request.getParameter("resourceStatus");
        String terminatedDate = request.getParameter("terminatedDate");
        String terminationReason = request.getParameter("terminationReason");

        String email = request.getParameter("email");
        String gbGf = request.getParameter("gbGf");
        String entryDate = request.getParameter("entryDate");
        String rmUserId = request.getParameter("rmUserId");
        String itindustryWorkYear = request.getParameter("itindustryWorkYear");

        //add begin
        String chsoftiProNumber = request.getParameter("chsoftiProNumber");
        String chsoftiProStartDate1 = request.getParameter("chsoftiProStartDate");
        String chsoftiProName = request.getParameter("chsoftiProName");
        //add end

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date();
        Timestamp createTime = null;
		try {
			createTime = new Timestamp(sdf.parse(sdf.format(curDate)).getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		
		
        Timestamp updateTime = createTime;
        Employee employee = new Employee(employeeId,eHr,lob,
                hsbcStaffId, staffName, LN, staffRegion,
                staffLocation, locationType, onshoreOrOffshore,
                csSubDept, hsbcSubDept, projectName,
                projectManager, sow, sowExpiredDate,
                staffCategory, engagementType, hsbcDOJ,
                graduationDate, role, skill,
                billingCurrency, billRate, resourceStatus,
                terminatedDate, terminationReason,email,gbGf,
                entryDate,rmUserId, createTime, updateTime,itindustryWorkYear,
                chsoftiProNumber,chsoftiProStartDate1,chsoftiProName);

        boolean resultFlag = employeeService.addEmployee(employee);
        if(resultFlag) {
        	String candidateId=(String) request.getSession().getAttribute("onboardCandidateId");
    		candidateService.updateOnboardCandidate(candidateId);

    		/**
             * 添加日志
             */
            try{
            	User user = (User)request.getSession().getAttribute("loginUser");
            	EmployeeLog log = getEmployeeLog(employee,user,"0");
            	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	String ts = sf.format(new Date());
            	log.setUpdateDate(Timestamp.valueOf(ts));
        		@SuppressWarnings({ "unused", "unchecked" })
    			boolean flag = employeeLogService.save(log);
            }catch(Exception e){
            	e.printStackTrace();
            }
        }
        return resultFlag;
    }


    @RequestMapping("/updateEmployee")
    @ResponseBody
    public boolean updateEmployee(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String employeeId = request.getParameter("employeeId");
        String eHr = request.getParameter("eHr");
        String lob = request.getParameter("lob");
        String hsbcStaffId = request.getParameter("hsbcStaffId");
        String staffName = request.getParameter("staffName");
        String LN = request.getParameter("LN");
        String staffRegion = request.getParameter("staffRegion");
        String staffLocation = request.getParameter("staffLocation");
        String locationType = request.getParameter("locationType");
        String onshoreOrOffshore = request.getParameter("onshoreOrOffshore");
        String csSubDept = request.getParameter("csSubDept");
        String hsbcSubDept = request.getParameter("hsbcSubDept");
        String projectName = request.getParameter("projectName");
        String projectManager = request.getParameter("projectManager");
        String sow = request.getParameter("sow");
        String sowExpiredDate = request.getParameter("sowExpiredDate");
        String staffCategory = request.getParameter("staffCategory");
        String engagementType = request.getParameter("engagementType");
        String hsbcDOJ = request.getParameter("hsbcDOJ");
        String graduationDate = request.getParameter("graduationDate");
        String role = request.getParameter("role");
        String skill = request.getParameter("skill");
        String billingCurrency = request.getParameter("billingCurrency");
        String billRate = request.getParameter("billRate");
        String resourceStatus = request.getParameter("resourceStatus");
        String terminatedDate = request.getParameter("terminatedDate");
        String terminationReason = request.getParameter("terminationReason");

        String email = request.getParameter("email");
        String gbGf = request.getParameter("gbGf");
        String entryDate = request.getParameter("entryDate");
        String rmUserId = request.getParameter("rmUserId");
        String itindustryWorkYear = request.getParameter("itindustryWorkYear");
        
        //add begin
        String chsoftiProNumber = request.getParameter("chsoftiProNumber");
        String chsoftiProStartdate = request.getParameter("chsoftiProStartDate");
        String chsoftiProName = request.getParameter("chsoftiProName");
        //add end
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date();
        Timestamp updateTime = null;
        //Timestamp chsoftiProStartDate = null;
		try {
			updateTime = new Timestamp(sdf.parse(sdf.format(curDate)).getTime());
			//chsoftiProStartDate = new Timestamp(sdf.parse(chsoftiProStartDate1).getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}

        //修改后的employee

        Employee employee = new Employee(employeeId,eHr,lob,
                hsbcStaffId, staffName, LN, staffRegion,
                staffLocation, locationType, onshoreOrOffshore,
                csSubDept, hsbcSubDept, projectName,
                projectManager, sow, sowExpiredDate,
                staffCategory, engagementType, hsbcDOJ,
                graduationDate, role, skill,
                billingCurrency, billRate, resourceStatus,
                terminatedDate, terminationReason,email,gbGf,entryDate,rmUserId,updateTime,itindustryWorkYear,
                chsoftiProNumber,chsoftiProStartdate,chsoftiProName
        		);

        /**
         * 添加日志
         */
        try{
        	User user = (User)request.getSession().getAttribute("loginUser");
        	//修改前的employee
        	Employee em = employeeService.queryEmployeeById(employee.getEmployeeId());
        	EmployeeLog log = getEmployeeLog(employee,user,"1");
    		@SuppressWarnings({ "unused", "unchecked" })
    		StringBuffer[] result=checkFieldChange(em,employee);
    		if(result[0].length()>0){
    			@SuppressWarnings({ "unchecked", "unused" })
    			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	String ts = sf.format(new Date());
            	log.setUpdateDate(Timestamp.valueOf(ts));
				boolean flag = employeeLogService.save(log);
    		}

        }catch(Exception e){
        	e.printStackTrace();
        }

        boolean resultFlag = employeeService.updateEmployee(employee);

        return resultFlag;
    }

    /**
     * 校验Ehr是否存在
     * gkf
     * @param eHr
     * @return
     */
    @RequestMapping("/checkEhr")
    @ResponseBody
	public String checkEhr(String eHr){
		boolean result = true;

		List<Employee> e = employeeService.selectByEhr(eHr);

		if(e!=null&&e.size()>0){
			result = false;
		}
		Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException je) {
            je.printStackTrace();
        }
        return resultString;
	}
    /**
     * 校验员工id是否存在
     * gkf
     * @param hsbcStaffId
     * @return
     */
    @RequestMapping("/checkHSBCStaffID")
	@ResponseBody
	public String checkHSBCStaffID(String hsbcStaffId){
		boolean result = true;

		List<Employee> e = employeeService.selectByHSBCStaffID(hsbcStaffId);

		if(e!=null&&e.size()>0){
			result = false;
		}
		Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException je) {
            je.printStackTrace();
        }
        return resultString;
	}
    /**
     * 校验lob是否存在
     * gkf
     * @param lob
     * @return
     */
    @RequestMapping("/checkLob")
	@ResponseBody
	public String checkLob(String lob){
		boolean result = true;

		List<Employee> e = employeeService.selectByLob(lob);

		if(e!=null&&e.size()>0){
			result = false;
		}
		Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException je) {
            je.printStackTrace();
        }
        return resultString;
    }
@RequestMapping("/exportExcel")
    @ResponseBody
    public HttpServletResponse exportExcel(HttpServletRequest request,
             HttpServletResponse response)
    {
        //EmployeePageCondition empListCondition = (EmployeePageCondition) request.getSession().getAttribute("empListCondition");

        EmployeePageCondition empListCondition = (EmployeePageCondition) request.getSession().getAttribute("employeePageCondition");

        List<String> conditionList = (List<String>) request.getSession().getAttribute("conditionList");

        try {
               //获取当前日期
               SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
               String date = df.format(new Date());

               String fileName =  Constants.PATH+""+Utils.getUUID()+".xls";


               WritableWorkbook wwb = null;

               // 创建可写入的Excel工作簿

               //创建 Constants.PATH文件夹
               File folder = new File( Constants.PATH);
               if(!folder.exists()){
            	   folder.mkdirs();
               }

               //创建  file
               File file=new File(fileName);
               if (!file.exists()) {
                   file.createNewFile();
               }
               //以fileName为文件名来创建一个Workbook
               wwb = Workbook.createWorkbook(file);

               // 创建工作表
               WritableSheet ws = wwb.createSheet("Headcount", 0);

               //查询数据库中所有的数据
               List<Employee> listE = employeeService.queryEmployeeList(empListCondition);

               //要插入到的Excel表格的行号，默认从0开始

                   Label labelSL= new Label(0, 0, "SL#");
                   ws.addCell(labelSL);

               for(int k=0;k<conditionList.size();k++){
                   Label label = new Label(k+1, 0, conditionList.get(k));

                   ws.addCell(label);
               }

               List<CSDept> allCSDept = csDeptService.queryAllCSDept();
               List<HSBCDept> allHSBCDept = hsbcDeptService.queryAllHSBCDept();
               List<User> allRM = userService.getUserForRM();

               CSDept csDept = null;
               HSBCDept hsbcDept = null;
               List<String> subDeptIdColl = new ArrayList<String>();
               String  listEtoCsSubDept = "";
               for (CSDept csD : allCSDept) {
					if(csD.getCsSubDeptId()!=null||!"".equals(csD.getCsSubDeptId())) {
						subDeptIdColl.add(csD.getCsSubDeptId());
					}
               }
               for (int i = 1; i - 1 < listE.size(); i++) {
            	 listEtoCsSubDept = listE.get(i - 1).getCsSubDept();
            	 if(listEtoCsSubDept!=null||!"".equals(listEtoCsSubDept)){
					for (CSDept csD : allCSDept) {
						if(!subDeptIdColl.contains(listEtoCsSubDept)) {
							csDept = new CSDept();
							csDept.setCsSubDeptName(listEtoCsSubDept);
							break;
						}else {
							if(csD.getCsSubDeptId().equals(listEtoCsSubDept)) {
								csDept = csD;
								break;
							}
						}

					}
				}

				if(listE.get(i - 1).getHsbcSubDept()!=null){
					for (HSBCDept hsbcD : allHSBCDept) {
						if (hsbcD.getHsbcSubDeptId().equals(listE.get(i - 1).getHsbcSubDept())) {
							hsbcDept = hsbcD;
							break;
						}
					}
				}else {
					hsbcDept = null;
				}

                   int j = 0;

                   Label labelSL_i= new Label(j, i, i+"");
                   ws.addCell(labelSL_i);

                   if(conditionList.contains("HSBC Staff ID")){
                       Label label= new Label(++j, i, listE.get(i-1).getHsbcStaffId());
                       ws.addCell(label);
                   }


                   if(conditionList.contains("Staff Name")){
                       Label label= new Label(++j, i, listE.get(i-1).getStaffName());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("LN")){
                       Label label= new Label(++j, i, listE.get(i-1).getLn());
                       ws.addCell(label);
                   }


                   if(conditionList.contains("E-Mail")){
                       Label label= new Label(++j, i, listE.get(i-1).getEmail());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Staff Region")){
                       Label label= new Label(++j, i, listE.get(i-1).getStaffRegion());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Staff Location (country)")){
                       Label label= new Label(++j, i, listE.get(i-1).getStaffLocation());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Location Type (HSBCOffice/ODC)")){
                       Label label= new Label(++j, i, listE.get(i-1).getLocationType());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Onshore or Offshore")){
                       Label label= new Label(++j, i, listE.get(i-1).getOnshoreOrOffshore());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("GB/GF")){
                       Label label= new Label(++j, i, listE.get(i-1).getGbGf());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("HSBC Dept")){
                       Label label = null;
                       if(hsbcDept == null){
                           label= new Label(++j, i, "");
                       }else{
                           label= new Label(++j, i, hsbcDept.getHsbcDeptName());
                       }
                       ws.addCell(label);
                   }

                   if(conditionList.contains("HSBC Sub Dept")){
                       Label label = null;
                       if(hsbcDept == null){
                           label= new Label(++j, i, "");
                       }else if(hsbcDept.getHsbcSubDeptName() == null || ("").equals(hsbcDept.getHsbcSubDeptName())){
                           label= new Label(++j, i, hsbcDept.getHsbcDeptName());
                       }else{
                           label= new Label(++j, i, hsbcDept.getHsbcSubDeptName());
                       }
                       ws.addCell(label);
                   }

                   if(conditionList.contains("HSBC Manager")){
                       Label label= new Label(++j, i, listE.get(i-1).getProjectManager());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Project Name")){
                       Label label= new Label(++j, i, listE.get(i-1).getProjectName());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("SOW#")){
                       Label label= new Label(++j, i, listE.get(i-1).getSow());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("SOW# Expired Date")){
                       Label label= new Label(++j, i, listE.get(i-1).getSowExpiredDate());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Staff Category(CATG/CATB)")){
                       Label label= new Label(++j, i, listE.get(i-1).getStaffCategory());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Engagement Type (T&M/FixedCost)")){
                       Label label= new Label(++j, i, listE.get(i-1).getEngagementType());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("HSBC DOJ")){
                       Label label= new Label(++j, i, listE.get(i-1).getHsbcDOJ());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Experience on HSBC account in Months")){
                       Label label = null;
                       if(listE.get(i-1).getHsbcDOJ() == null){
                           label= new Label(++j, i, "");
                       }else{
                           label= new Label(++j, i, Utils.caculateMonth(listE.get(i-1).getHsbcDOJ())+"");
                       }
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Graduation Date")){
                       Label label = null;
                       if(listE.get(i-1).getGraduationDate() == null){
                           label= new Label(++j, i, "");
                       }else{
                           label= new Label(++j, i, listE.get(i-1).getGraduationDate());
                       }
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Total Experience in Months")){
                       Label label = null;
                       if(listE.get(i-1).getGraduationDate() == null){
                           label= new Label(++j, i, "");
                       }else{
                           label= new Label(++j, i, Utils.caculateMonth(listE.get(i-1).getGraduationDate())+"");
                       }

                       ws.addCell(label);
                   }

                   if(conditionList.contains("MSA Role")){
                       Label label= new Label(++j, i, listE.get(i-1).getRole());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Skills/Technology")){
                       Label label= new Label(++j, i, listE.get(i-1).getSkill());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Entry Date")){
                       Label label= new Label(++j, i, listE.get(i-1).getEntryDate());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Billing Currency")){
                       Label label= new Label(++j, i, listE.get(i-1).getBillingCurrency());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Bill Rate")){
                       Label label= new Label(++j, i, listE.get(i-1).getBillRate());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Resource Status (Active/Terminated)")){
                       Label label= new Label(++j, i, listE.get(i-1).getResourceStatus());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("If terminated mention LWD")){
                       Label label= new Label(++j, i, listE.get(i-1).getTerminatedDate());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("Reason for Termination")){
                       Label label= new Label(++j, i, listE.get(i-1).getTerminationReason());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("E-HR")){
                       Label label= new Label(++j, i, listE.get(i-1).geteHr());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("LOB")){
                       Label label= new Label(++j, i, listE.get(i-1).getLob());
                       ws.addCell(label);
                   }

                   if(conditionList.contains("RM")){
						String RMName = "";
						for (User user : allRM) {
							if (user.getUserId().equalsIgnoreCase(listE.get(i - 1).getRmUserId())) {
								RMName = user.getNickname();
								break;
							}
						}
                       Label label= new Label(++j, i, RMName);
                       ws.addCell(label);
                   }

                   if(conditionList.contains("AM")){
                	   if(csDept!=null) {
                		   Label label= new Label(++j, i, csDept.getAm());
                           ws.addCell(label);
                	   }else {
                		   Label label= new Label(++j, i, "");
                           ws.addCell(label);
                	   }

                   }

                   if(conditionList.contains("CS Dept")){
                	   String temp = "";
                	   String[] t = null;
                	   if(listE.get(i-1).getCsSubDept()!=null && !"".equals(listE.get(i-1).getCsSubDept())){
                		   if(listE.get(i-1).getCsSubDept().indexOf(",")!=-1){
                			   t = listE.get(i-1).getCsSubDept().split(",");
                		   }
                	   }
                	   if(allCSDept!=null && allCSDept.size()>0){
                		   for(int k=0;k<allCSDept.size();k++){
                			   if(t!=null && t.length>0){
                				   for(int n=0;n<t.length;n++){
                					   if(t[n].equals(allCSDept.get(k).getCsSubDeptId())){
                						   temp+=allCSDept.get(k).getCsSubDeptName()+",";
                					   }
                				   }
                			   }else{
                				   if(listE.get(i-1).getCsSubDept()!=null && !"".equals(listE.get(i-1).getCsSubDept())){
                					   if(listE.get(i-1).getCsSubDept().equals(allCSDept.get(k).getCsSubDeptId())){
                						   temp=allCSDept.get(k).getCsSubDeptName();
                					   }
                				   }
                			   }

                		   }
                		   if(temp.indexOf(",")!=-1){
                			   Label label= new Label(++j, i, temp.substring(0, temp.length()-1));
                               ws.addCell(label);
                		   }else{
                			   Label label= new Label(++j, i, temp);
                               ws.addCell(label);
                		   }
                	   }
                	   if("".equals(temp)) {
                		   Label label= new Label(++j, i, "");
                           ws.addCell(label);
                	   }
                   }

                   if(conditionList.contains("Itworkyear")){
                       Label label= new Label(++j, i, listE.get(i-1).getItindustryWorkYear());
                       ws.addCell(label);
                   }

                   //中软项目编号
                   if(conditionList.contains("Ch Pro Number")){
                       Label label= new Label(++j, i, listE.get(i-1).getChsoftiProNumber());
                       ws.addCell(label);
                   }

                   //项目开始日期
                   if(conditionList.contains("Ch Pro StartDate")){
                	   if(listE.get(i-1).getChsoftiProStartdate()!=null && !"".equals(listE.get(i-1).getChsoftiProStartdate())){
                		   Label label= new Label(++j, i, listE.get(i-1).getChsoftiProStartdate().toString());
                           ws.addCell(label);
                	   }
                   }

                   //中软项目名称
                   if(conditionList.contains("Ch Pro Name")){
                       Label label= new Label(++j, i, listE.get(i-1).getChsoftiProName());
                       ws.addCell(label);
                   }

               }

              //写进文档
               wwb.write();
              // 关闭Excel工作簿对象
               wwb.close();

               String filename = "GSV Engagement Dashboard_"+date+".xls";

               //fileName = URLEncoder.encode(filename,"UTF-8");
               //fileName = fileName.replace("+", " ");

               // 以流的形式下载文件。
               InputStream fis = new BufferedInputStream(new FileInputStream(fileName));
               byte[] buffer = new byte[fis.available()];
               fis.read(buffer);
               fis.close();
               // 清空response
               response.reset();
               // 设置response的Header
               response.addHeader("Content-Disposition", "attachment;filename=" + filename);
               //response.setContentType("application/octet-stream");
               response.setContentType("application/vnd.ms-excel");
               response.addHeader("Content-Length", "" + file.length());
               OutputStream toClient = new BufferedOutputStream(response.getOutputStream());

               toClient.write(buffer);
               toClient.flush();
               toClient.close();

              file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



/**
 * 校验员工id是否存在
 * @param hsbcStaffId
 * @return
 */
@RequestMapping("/getTMemployee")
public String getTMemployee(final HttpServletRequest request,
        final HttpServletResponse response,String status){

	if("1".equals(status)){
		return "index1";
	/*}else if("2".equals(status)){
		return "index2";*/
	}else if("3".equals(status)){
		return "index3";
	}else if("4".equals(status)){		return "index4";
	}
	return "index2";
}


@RequestMapping("/entryEmployee")
public String entryEmployee(final HttpServletRequest request,
        final HttpServletResponse response,String candidateId){
	CandidateInfo candidateInfo = candidateService.queryCandidateForId(candidateId);
	request.setAttribute("candidateInfo", candidateInfo);
	request.getSession().setAttribute("onboardCandidateId", candidateId);
	return "index5";
}

    private EmployeeLog getEmployeeLog(Employee employee,User user,String type){
    	//修改前
    	Employee em = employeeService.queryEmployeeById(employee.getEmployeeId());
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
    	Timestamp time = null;
    	try {
			time = new Timestamp(sdf.parse(sdf.format(new Date())).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	EmployeeLog log = new EmployeeLog();
    	StringBuffer[] finalchangeInfo = null;
    	StringBuffer addInfo = null;
    	if(type.equals("0")){
    		addInfo = addInfo(employee);
    	}
        if(type.equals("1")){
        	finalchangeInfo = checkFieldChange(em,employee);
    	}
    	if(em!=null && employee !=null){
    		if(!em.getCsSubDept().equals(employee.getCsSubDept())){
        		log.setCsSubdeptIdNew(employee.getCsSubDept());
            	log.setCsSubdeptIdOriginal(em.getCsSubDept());
        	}
            if(!em.getRole().equals(employee.getRole())){
            	log.setRoleNew(employee.getRole());
            	log.setRoleOriginal(em.getRole());
        	}
            if(!em.getResourceStatus().equals(employee.getResourceStatus())){
            	log.setStatusNew(employee.getResourceStatus());
            	log.setStatusOriginal(em.getResourceStatus());
        	}
        	if(type.equals("0")){
        		log.setChangeInformation(addInfo.toString());
        	}
            if(type.equals("1")){
            	log.setChangeInformation(finalchangeInfo[0].toString());
        	}
        	log.setEmployeeId(employee.getEmployeeId());
        	log.setLogId(Utils.getUUID());
        	if(type.equals("0")){
        		log.setLogType("0");
        	}
            if(type.equals("1")){
            	log.setLogType("1");
        	}
            if(finalchangeInfo!=null){
            	log.setProjectStatus(finalchangeInfo[1].toString());
            	log.setContractStatus(finalchangeInfo[2].toString());
            	log.setLevelStatus(finalchangeInfo[3].toString());
            }
        	log.setOperationPerson(user.getUserId());
        	log.setUpdateDate(time);
    	}
    	return log;
    }
    private StringBuffer[] checkFieldChange(Employee before,Employee after){
    	StringBuffer[] finalchangeInfo = new StringBuffer[4];
    	StringBuffer changeInfo = new StringBuffer();
    	String projectStatus = "";
    	String contractStatus = "";
    	String levelStatus = "";
    	if(before!=null && after!=null){
    		//E-HR
            if(before.geteHr()!=null && !before.geteHr().equals(after.geteHr())){
            	changeInfo.append("EHr由["+before.geteHr()+"]变更为["+after.geteHr()+"];   ");
            	changeInfo.append("----");
        	}
        	//LOB
            if(before.getLob()!=null && !before.getLob().equals(after.getLob())){
            	changeInfo.append("Lob由["+before.getLob()+"]变更为["+after.getLob()+"];   ");
            	changeInfo.append("----");
            }
        	//HSBC Staff ID
            if(before.getHsbcStaffId()!=null && !before.getHsbcStaffId().equals(after.getHsbcStaffId())){
            	changeInfo.append("HsbcStaffId由["+before.getHsbcStaffId()+"]变更为["+after.getHsbcStaffId()+"];   ");
            	changeInfo.append("----");
            }
        	//Staff Name
            if(before.getStaffName()!=null && !before.getStaffName().equals(after.getStaffName())){
            	changeInfo.append("StaffName由["+before.getStaffName()+"]变更为["+after.getStaffName()+"];   ");
            	changeInfo.append("----");
            }
        	//LN
            if(before.getLn()!=null && !before.getLn().equals(after.getLn())){
            	changeInfo.append("Ln由["+before.getLn()+"]变更为["+after.getLn()+"];   ");
            	changeInfo.append("----");
            }
        	//Email
            if(before.getEmail()!=null && !before.getEmail().equals(after.getEmail())){
            	changeInfo.append("Email由["+before.getEmail()+"]变更为["+after.getEmail()+"];   ");
            	changeInfo.append("----");
            }
        	//Staff location
            if(before.getStaffLocation()!=null && !before.getStaffLocation().equals(after.getStaffLocation())){
            	changeInfo.append("StaffLocation由["+before.getStaffLocation()+"]变更为["+after.getStaffLocation()+"];   ");
            	changeInfo.append("----");
            }
        	//Location Type
            if(before.getLocationType()!=null && !before.getLocationType().equals(after.getLocationType())){
            	changeInfo.append("LocationType由["+before.getLocationType()+"]变更为["+after.getLocationType()+"];   ");
            	changeInfo.append("----");
            }
        	//CS Dept
            if(before.getCsSubDept()!=null && !before.getCsSubDept().equals(after.getCsSubDept())){
            	CSDept old = csDeptService.queryCSDeptById(before.getCsSubDept());
            	CSDept news = csDeptService.queryCSDeptById(after.getCsSubDept());
            	String oldname="";
            	String newsname="";
            	if(old!=null){
            		oldname=old.getCsSubDeptName();
            	}
            	if(news!=null){
            		newsname=news.getCsSubDeptName();
            	}
            	changeInfo.append("CsSubDept由["+oldname+"]变更为["+newsname+"];   ");
            	changeInfo.append("----");
            }

        	//GBGF
            if(before.getGbGf()!=null && !before.getGbGf().equals(after.getGbGf())){
            	changeInfo.append("GbGf由["+before.getGbGf()+"]变更为["+after.getGbGf()+"];   ");
            	changeInfo.append("----");
            	projectStatus="0";
            }
        	//HSBC Sub Dept
            if(before.getHsbcSubDept()!=null && !before.getHsbcSubDept().equals(after.getHsbcSubDept())){
            	HSBCDept old = hsbcDeptService.queryDemandHSBCSubDeptById(before.getHsbcSubDept());
            	HSBCDept news = hsbcDeptService.queryDemandHSBCSubDeptById(after.getHsbcSubDept());
            	String oldname="";
            	String newsname="";
            	if(old!=null){
            		oldname=old.getHsbcSubDeptName();
            	}
            	if(news!=null){
            		newsname=news.getHsbcSubDeptName();
            	}
            	changeInfo.append("HsbcSubDept由["+oldname+"]变更为["+newsname+"];   ");
            	changeInfo.append("----");
            	projectStatus="0";
            }
        	//HSBC Project Name
            if(before.getProjectName()!=null && !before.getProjectName().equals(after.getProjectName())){
            	changeInfo.append("ProjectName由["+before.getProjectName()+"]变更为["+after.getProjectName()+"];   ");
            	changeInfo.append("----");
            	projectStatus="0";
            }
        	//HSBC Project Manager
            if(before.getProjectManager()!=null && !before.getProjectManager().equals(after.getProjectManager())){
            	changeInfo.append("ProjectManager由["+before.getProjectManager()+"]变更为["+after.getProjectManager()+"];   ");
            	changeInfo.append("----");
            	projectStatus="0";
            }
        	//SOW#
            if(before.getSow()!=null && !before.getSow().equals(after.getSow())){
            	changeInfo.append("Sow由["+before.getSow()+"]变更为["+after.getSow()+"];   ");
            	changeInfo.append("----");
            	contractStatus="0";
            }
        	//SOW# Expired Date
            if(before.getSowExpiredDate()!=null && !before.getSowExpiredDate().equals(after.getSowExpiredDate())){
            	changeInfo.append("SowExpiredDate由["+before.getSowExpiredDate()+"]变更为["+after.getSowExpiredDate()+"];   ");
            	changeInfo.append("----");
            	contractStatus="0";
            }
        	//Staff Category
            if(before.getStaffCategory()!=null && !before.getStaffCategory().equals(after.getStaffCategory())){
            	changeInfo.append("StaffCategory由["+before.getStaffCategory()+"]变更为["+after.getStaffCategory()+"];   ");
            	changeInfo.append("----");
            }
        	//Engagement Type
            if(before.getEngagementType()!=null && !before.getEngagementType().equals(after.getEngagementType())){
            	changeInfo.append("EngagementType由["+before.getEngagementType()+"]变更为["+after.getEngagementType()+"];   ");
            	changeInfo.append("----");
            }
        	//Graduation Date
            if(before.getGraduationDate()!=null && !before.getGraduationDate().equals(after.getGraduationDate())){
            	changeInfo.append("GraduationDate由["+before.getGraduationDate()+"]变更为["+after.getGraduationDate()+"];   ");
            	changeInfo.append("----");
            }
        	//EntryDate
            if(before.getEntryDate()!=null && !before.getEntryDate().equals(after.getEntryDate())){
            	changeInfo.append("EntryDate由["+before.getEntryDate()+"]变更为["+after.getEntryDate()+"];   ");
            	changeInfo.append("----");
            }
        	//Staff Region
            if(before.getStaffRegion()!=null && !before.getStaffRegion().equals(after.getStaffRegion())){
            	changeInfo.append("StaffRegion由["+before.getStaffRegion()+"]变更为["+after.getStaffRegion()+"];   ");
            	changeInfo.append("----");
            }
        	//Onshore or Offshore
            if(before.getOnshoreOrOffshore()!=null && !before.getOnshoreOrOffshore().equals(after.getOnshoreOrOffshore())){
            	changeInfo.append("OnshoreOrOffshore由["+before.getOnshoreOrOffshore()+"]变更为["+after.getOnshoreOrOffshore()+"];   ");
            	changeInfo.append("----");
            }
        	//MSA Role
            if(before.getRole()!=null && !before.getRole().equals(after.getRole())){
            	changeInfo.append("Role由["+before.getRole()+"]变更为["+after.getRole()+"];   ");
            	changeInfo.append("----");
            	levelStatus="0";
            }
        	//Skills/Technology
            if(before.getSkill()!=null && !before.getSkill().equals(after.getSkill())){
            	changeInfo.append("Skill由["+before.getSkill()+"]变更为["+after.getSkill()+"];   ");
            	changeInfo.append("----");
            	levelStatus="0";
            }
        	//Billing Currency
            if(before.getBillingCurrency()!=null && !before.getBillingCurrency().equals(after.getBillingCurrency())){
            	changeInfo.append("BillingCurrency由["+before.getBillingCurrency()+"]变更为["+after.getBillingCurrency()+"];   ");
            	changeInfo.append("----");
            }
        	//Bill Rate
            if(before.getBillRate()!=null && !before.getBillRate().equals(after.getBillRate())){
            	changeInfo.append("BillRate由["+before.getBillRate()+"]变更为["+after.getBillRate()+"];   ");
            	changeInfo.append("----");
            	levelStatus="0";
            }
        	//HSBC DOJ
            if(before.getHsbcDOJ()!=null && !before.getHsbcDOJ().equals(after.getHsbcDOJ())){
            	changeInfo.append("HsbcDOJ由["+before.getHsbcDOJ()+"]变更为["+after.getHsbcDOJ()+"];   ");
            	changeInfo.append("----");
            }
        	//Resource Status
            if(before.getResourceStatus()!=null && !before.getResourceStatus().equals(after.getResourceStatus())){
            	changeInfo.append("ResourceStatus由["+before.getResourceStatus()+"]变更为["+after.getResourceStatus()+"];   ");
            	changeInfo.append("----");
            }
        	//LWD
            if(before.getTerminatedDate()!=null && !before.getTerminatedDate().equals(after.getTerminatedDate())){
            	changeInfo.append("TerminatedDate由["+before.getTerminatedDate()+"]变更为["+after.getTerminatedDate()+"];   ");
            	changeInfo.append("----");
            }
        	//Reason for Termination
            if(before.getTerminationReason()!=null && !before.getTerminationReason().equals(after.getTerminationReason())){
            	changeInfo.append("TerminationReason由["+before.getTerminationReason()+"]变更为["+after.getTerminationReason()+"];   ");
            	changeInfo.append("----");
            }
            //interviewStatus
//            if(before.getInterviewStatus()!=null && !before.getInterviewStatus().equals(after.getInterviewStatus())){
//            	changeInfo.append("interviewStatus由["+before.getInterviewStatus()+"]变更为["+after.getInterviewStatus()+"];");
//            }
    	}
    	finalchangeInfo[0]=changeInfo;
    	finalchangeInfo[1]=new StringBuffer(projectStatus);
    	finalchangeInfo[2]=new StringBuffer(contractStatus);
    	finalchangeInfo[3]=new StringBuffer(levelStatus);

    	return finalchangeInfo;
    }


    private StringBuffer addInfo(Employee employee){
    	StringBuffer changeInfo = new StringBuffer();
    	if(employee!=null){
    		//E-HR
    		if(employee.geteHr()!=null && !"".equals(employee.geteHr())){
    			changeInfo.append("EHr["+employee.geteHr()+"];----");
    		}
        	//LOB
    		if(employee.getLob()!=null && !"".equals(employee.getLob())){
    			changeInfo.append("Lob["+employee.getLob()+"];----");
    		}
        	//HSBC Staff ID
    		if(employee.getHsbcStaffId()!=null && !"".equals(employee.getHsbcStaffId())){
    			changeInfo.append("HsbcStaffId["+employee.getHsbcStaffId()+"];----");
    		}
        	//Staff Name
    		if(employee.getStaffName()!=null && !"".equals(employee.getStaffName())){
    			changeInfo.append("StaffName["+employee.getStaffName()+"];----");
    		}
        	//LN
    		if(employee.getLn()!=null && !"".equals(employee.getLn())){
    			changeInfo.append("Ln["+employee.getLn()+"];----");
    		}
        	//Email
    		if(employee.getEmail()!=null && !"".equals(employee.getEmail())){
    			changeInfo.append("Email["+employee.getEmail()+"];----");
    		}
        	//Staff location
    		if(employee.getStaffLocation()!=null && !"".equals(employee.getStaffLocation())){
    			changeInfo.append("StaffLocation["+employee.getStaffLocation()+"];----");
    		}
        	//Location Type
    		if(employee.getLocationType()!=null && !"".equals(employee.getLocationType())){
    			changeInfo.append("LocationType["+employee.getLocationType()+"];----");
    		}
        	//CS Dept
            CSDept csdept = null;
            if(employee.getCsSubDept()!=null && !"".equals(employee.getCsSubDept())){
            	csdept = csDeptService.queryCSDeptById(employee.getCsSubDept());
            }
            String csdeptname=null;
        	if(csdept!=null){
        		csdeptname=csdept.getCsSubDeptName();
        	}
        	if(csdeptname!=null && !"".equals(csdeptname)){
        		changeInfo.append("CsSubDept["+csdeptname+"];----");
        	}
        	//GBGF
        	if(employee.getGbGf()!=null && !"".equals(employee.getGbGf())){
        		changeInfo.append("GbGf["+employee.getGbGf()+"];----");
        	}
        	//HSBC Sub Dept
            HSBCDept hsbcdept = null;
        	if(employee.getHsbcSubDept()!=null && !"".equals(employee.getHsbcSubDept())){
        		hsbcdept = hsbcDeptService.queryDemandHSBCSubDeptById(employee.getHsbcSubDept());
        	}
            String hsbcdeptname="";
        	if(hsbcdept!=null){
        		hsbcdeptname=hsbcdept.getHsbcSubDeptName();
        	}
        	if(hsbcdeptname!=null && !"".equals(hsbcdeptname)){
        		changeInfo.append("HsbcSubDept["+hsbcdeptname+"];----");
        	}
        	//HSBC Project Name
        	if(employee.getProjectName()!=null && !"".equals(employee.getProjectName())){
        		changeInfo.append("ProjectName["+employee.getProjectName()+"];----");
        	}
        	//HSBC Project Manager
        	if(employee.getProjectManager()!=null && !"".equals(employee.getProjectManager())){
        		changeInfo.append("ProjectManager["+employee.getProjectManager()+"];----");
        	}
        	//SOW#
        	if(employee.getSow()!=null && !"".equals(employee.getSow())){
        		changeInfo.append("Sow["+employee.getSow()+"];----");
        	}
        	//SOW# Expired Date
        	if(employee.getSowExpiredDate()!=null && !"".equals(employee.getSowExpiredDate())){
        		changeInfo.append("SowExpiredDate["+employee.getSowExpiredDate()+"];----");
        	}
        	//Staff Category
        	if(employee.getStaffCategory()!=null && !"".equals(employee.getStaffCategory())){
        		changeInfo.append("StaffCategory["+employee.getStaffCategory()+"];----");
        	}
        	//Engagement Type
        	if(employee.getEngagementType()!=null && !"".equals(employee.getEngagementType())){
        		changeInfo.append("EngagementType["+employee.getEngagementType()+"];----");
        	}
        	//Graduation Date
        	if(employee.getGraduationDate()!=null && !"".equals(employee.getGraduationDate())){
        		changeInfo.append("GraduationDate["+employee.getGraduationDate()+"];----");
        	}
        	//EntryDate
        	if(employee.getEntryDate()!=null && !"".equals(employee.getEntryDate())){
        		changeInfo.append("EntryDate["+employee.getEntryDate()+"];----");
        	}
        	//Staff Region
        	if(employee.getStaffRegion()!=null && !"".equals(employee.getStaffRegion())){
        		changeInfo.append("StaffRegion["+employee.getStaffRegion()+"];----");
        	}
        	//Onshore or Offshore
        	if(employee.getOnshoreOrOffshore()!=null && !"".equals(employee.getOnshoreOrOffshore())){
        		changeInfo.append("OnshoreOrOffshore["+employee.getOnshoreOrOffshore()+"];----");
        	}
        	//MSA Role
        	if(employee.getRole()!=null && !"".equals(employee.getRole())){
        		changeInfo.append("Role["+employee.getRole()+"];----");
        	}
        	//Skills/Technology
        	if(employee.getSkill()!=null && !"".equals(employee.getSkill())){
        		changeInfo.append("Skill["+employee.getSkill()+"];----");
        	}
        	//Billing Currency
        	if(employee.getBillingCurrency()!=null && !"".equals(employee.getBillingCurrency())){
        		changeInfo.append("BillingCurrency["+employee.getBillingCurrency()+"];----");
        	}
        	//Bill Rate
        	if(employee.getBillRate()!=null && !"".equals(employee.getBillRate())){
        		changeInfo.append("BillRate["+employee.getBillRate()+"];----");
        	}
        	//HSBC DOJ
        	if(employee.getHsbcDOJ()!=null && !"".equals(employee.getHsbcDOJ())){
        		changeInfo.append("HsbcDOJ["+employee.getHsbcDOJ()+"];----");
        	}
        	//Resource Status
        	if(employee.getResourceStatus()!=null && !"".equals(employee.getResourceStatus())){
        		changeInfo.append("ResourceStatus["+employee.getResourceStatus()+"];----");
        	}
        	//LWD
        	if(employee.getTerminatedDate()!=null && !"".equals(employee.getTerminatedDate())){
        		changeInfo.append("TerminatedDate["+employee.getTerminatedDate()+"];----");
        	}
        	//Reason for Termination
        	if(employee.getTerminationReason()!=null && !"".equals(employee.getTerminationReason())){
        		changeInfo.append("TerminationReason["+employee.getTerminationReason()+"];----");
        	}
            //interviewStatus
            //changeInfo.append("interviewStatus["+employee.getInterviewStatus()+"];");
    	}
    	return changeInfo;
    }

    /**
     * Felix, 180116, Begin.
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/employeeGraph")
    public String employeeGraph(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/employeeGraph";
    }

    @RequestMapping("/employeeStatisticalGraph")
    @ResponseBody
    public Map<String, Map<Object, Object>> statisticalEmployee(final HttpServletRequest request,
            final HttpServletResponse response){
    	String startDate = request.getParameter("startDate");
    	String endDate = request.getParameter("endDate");
    	String bu = request.getParameter("csBUName");
    	String du = request.getParameter("csSubDept");
    	List<EmployeeLog> empLogList = new ArrayList<EmployeeLog>();
    	List<Integer> subDUIdList = null;
    	if(StringUtils.isEmpty(du) && StringUtils.isNotEmpty(bu)){
    		subDUIdList = new ArrayList<Integer>();
    		List<CSDept> csSubDeptList = csDeptService.queryCSSubDeptNameByCsBuName(bu);
    		for(CSDept csDept : csSubDeptList){
    			Integer duId = Integer.parseInt(csDept.getCsSubDeptId());
    			subDUIdList.add(duId);
    		}
    	}
    	EmployeeGraphParam graphParam = new EmployeeGraphParam();
    	graphParam.setStartDate(startDate);
    	graphParam.setEndDate(endDate);
    	graphParam.setBu(bu);
    	graphParam.setDu(du);
    	graphParam.setDuList(subDUIdList);
    	empLogList = employeeLogService.queryEmpLogByDUNew(graphParam);

    	Map<String, String> releaseMap01 = new HashMap<String, String>();
    	Map<String, String> levelMap01 = new HashMap<String, String>();
    	Map<String, String> terminatedMap01 = new HashMap<String, String>();
    	List<String> intoDUList01 = new ArrayList<String>();

    	Map<String, Map<Object, Object>> resultMap = new HashMap<String, Map<Object, Object>>();
    	Map<Object, Object> releaseMap = new HashMap<Object, Object>();
    	Map<Object, Object> levelMap = new HashMap<Object, Object>();
    	Map<Object, Object> terminatedMap = new HashMap<Object, Object>();
    	Map<Object, Object> intoDUMap = new HashMap<Object, Object>();
    	Map<Object, Object> outDUMap = countOutDU(startDate, endDate, du);
    	Set<String> set = new TreeSet<String>();
    	Map<Object, Object> xDataMap = new HashMap<Object, Object>();
    	if(null != outDUMap){
    		TreeSet<String> ymOutDUSet = (TreeSet<String>)outDUMap.get("ymOutDUSet");
    		set.addAll(ymOutDUSet);
    	}
    	if(null == empLogList || empLogList.isEmpty()){
    		resultMap.put("outDUMap", outDUMap);
    		xDataMap.put("xDataSet", set);
    		resultMap.put("ymDataMap", xDataMap);
    		return resultMap;
    	}

    	// month change
    	int tempMonth = Integer.parseInt(empLogList.get(0).getUpdateDate().toString().substring(5, 7));
    	int xFlag = 1;
    	for(int i=0; i<empLogList.size(); i++){
    		EmployeeLog empLog = empLogList.get(i);
    		String empId = empLog.getEmployeeId();
    		String updateDate = empLog.getUpdateDate().toString();
    		int updateMonth = Integer.parseInt(updateDate.substring(5, 7));
    		if(i == 0){
    			set.add(updateDate.substring(0, 7));
    		}
    		if(updateMonth != tempMonth){
    			releaseMap.put(xFlag, releaseMap01.size());
    			releaseMap01.clear();
    			levelMap.put(xFlag, levelMap01.size());
    			levelMap01.clear();
    			terminatedMap.put(xFlag, terminatedMap01.size());
    			terminatedMap01.clear();
    			intoDUMap.put(xFlag, intoDUList01.size());
    			intoDUList01.clear();
    			tempMonth = updateMonth;
    			xFlag++;
    			set.add(empLog.getUpdateDate().toString().substring(0, 7));
    		}
    		String statusNew = empLog.getStatusNew();
    		String statusOrg = empLog.getStatusOriginal();
    		String roleNew = empLog.getRoleNew();
    		String roleOrg = empLog.getRoleOriginal();
    		String duNew = empLog.getCsSubdeptIdNew();
    		String duOrg = empLog.getCsSubdeptIdOriginal();

    		// Release
    		if(!StringUtils.equals(statusNew, statusOrg) && "Released".equals(statusNew)){
    			if(releaseMap01.containsKey(empId)){
    				String firstStatusOrg = releaseMap01.get(empId);
    				if(StringUtils.equals(statusNew, firstStatusOrg)){
    					releaseMap01.remove(empId);
    				}
    			}else{
    				releaseMap01.put(empId, statusOrg);
    			}
    		}

    		// Level
    		if(!StringUtils.equals(roleNew, roleOrg)){
    			if(levelMap01.containsKey(empId)){
    				String firstRoleOrg = levelMap01.get(empId);
    				if(StringUtils.equals(roleNew, firstRoleOrg)){
    					levelMap01.remove(empId);
    				}
    			}else{
    				levelMap01.put(empId, roleOrg);
    			}
    		}

    		// Terminated
    		if(!StringUtils.equals(statusNew, statusOrg) && "Terminated".equals(statusNew)){
    			if(terminatedMap01.containsKey(empId)){
    				String firstStatusOrg = terminatedMap01.get(empId);
    				if(StringUtils.equals(statusNew, firstStatusOrg)){
    					terminatedMap01.remove(empId);
    				}
    			}else{
    				terminatedMap01.put(empId, statusOrg);
    			}
    		}

    		// into
    		if(StringUtils.isNotEmpty(du) && !StringUtils.equals(duNew, duOrg) && !intoDUList01.contains(empId)){
    			intoDUList01.add(empId);
    		}

    		if(i == empLogList.size()-1){
    			releaseMap.put(xFlag, releaseMap01.size());
    			levelMap.put(xFlag, levelMap01.size());
    			terminatedMap.put(xFlag, terminatedMap01.size());
    			intoDUMap.put(xFlag, intoDUList01.size());
    			set.add(empLog.getUpdateDate().toString().substring(0, 7));
    		}
    	}


    	xDataMap.put("xDataSet", set);
    	if(StringUtils.isEmpty(du)){
    		resultMap.put("intoDUMap", null);
    		resultMap.put("outDUMap", null);
    	}else{
    		resultMap.put("intoDUMap", intoDUMap);
    		resultMap.put("outDUMap", outDUMap);
    	}
    	resultMap.put("release", releaseMap);
    	resultMap.put("level", levelMap);
    	resultMap.put("terminated", terminatedMap);
    	resultMap.put("ymDataMap", xDataMap);
    	return resultMap;
    }

    private Map<Object, Object> countOutDU(String startDate, String endDate, String du){
    	Map<Object, Object> outDUMap = new HashMap<Object, Object>();
    	List<EmployeeLog> outDUempLogList = employeeLogService.queryEmpLogByDUOrg(startDate, endDate, du);
    	if(null == outDUempLogList || outDUempLogList.isEmpty() || StringUtils.isEmpty(du)){
    		return null;
    	}
    	int tempMonth = Integer.parseInt(outDUempLogList.get(0).getUpdateDate().toString().substring(5, 7));
    	List<String> outDUList01 = new ArrayList<String>();
    	int xFlag = 1;
    	Set<String> ymSet = new TreeSet<String>();
    	for(int i=0; i<outDUempLogList.size(); i++){
    		EmployeeLog empLog = outDUempLogList.get(i);
    		String duNew = empLog.getCsSubdeptIdNew();
    		String duOrg = empLog.getCsSubdeptIdOriginal();
    		String empId = empLog.getEmployeeId();
    		String updateDate = empLog.getUpdateDate().toString();
    		int updateMonth = Integer.parseInt(updateDate.substring(5, 7));
    		if(i == 0){
    			ymSet.add(updateDate.substring(0, 7));
    		}
    		if(updateMonth != tempMonth){
    			String xym = empLog.getUpdateDate().toString().substring(0, 7);
    			ymSet.add(xym);
    			outDUMap.put(xFlag, outDUList01.size());
    			outDUList01.clear();
    			tempMonth = updateMonth;
    			xFlag++;
    		}
    		if(!StringUtils.equals(duNew, duOrg) && !outDUList01.contains(empId)){
    			outDUList01.add(empId);
    		}
    		if(i == outDUempLogList.size()-1){
    			ymSet.add(empLog.getUpdateDate().toString().substring(0, 7));
    			outDUMap.put(xFlag, outDUList01.size());
    			outDUMap.put("ymOutDUSet", ymSet);
    		}
    	}
    	return outDUMap;
    }
    // Felix, 180116, End.

}