package test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.dao.CurrencysMapper;
import com.pmo.dashboard.dao.OfflineOperMapper;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.OfflineOperService;
import com.pom.dashboard.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/conf/spring-mybatis.xml"
//		,"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" 
//		,"classpath:/conf/spring-mvc.xml"
		})
@WebAppConfiguration
public class TestOfflineOperService {
	final static int PAGESIZE = 10, PAGENUMBER = 1 ;
	final static String YEAR= ""+LocalDate.now().getYear(), MONTH = ""+LocalDate.now().getMonthValue();
	@Resource
	private OfflineOperService offlineOperService;
	
	@Resource
	private OfflineOperMapper offlineOperMapper;
	
	@Resource
    private EmployeeService employeeService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CurrencysMapper currencysMapper;
	
	private ObjectMapper objectMapper = new ObjectMapper();  

//	@Before
//	public void initMockMvc() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//	}
	
//	public void testPage() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/offlineOper/query").param("pageNumber", "1")).andReturn();
//	}
	
	public void queryByRM() {
		OfflineOper condition = new OfflineOper();
		condition.seteHr("E000834441");
		condition.setStaffName("白世铭");
		User user = new User();
		
		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b");  // 张培  12
////		user.setUserId("cff5fa689a2e40afa02ba2ceda914bbb");  // 梁嘉杰 9
//		user.setUserId("20f1aeff297d49d4b3c42877687a7076");  // 张盛  9,12
		user.setUserType("5");
		
		user.setUserType("3");  
//		user.setUserId("c7b38226545c45e598f16a33031f85aa"); // c7b38226545c45e598f16a33031f85aa 李佳洲
		user.setCsdeptId("9,12");
		
		user.setUserType("1"); 
//		user.setUserId("1573"); // 风控数据事业部  潘亮
		user.setBu("风控数据事业部");
		
		List<OfflineOper> list = offlineOperService.query(condition, user, 2 << 16 , PAGENUMBER) ; //PAGESIZE
		PageInfo<OfflineOper> page = new PageInfo(list);
		
		System.out.println(list.size());
		System.out.println(page.getTotal());
		
	}
	
	
	public void rmCount() {
		OfflineOper condition = new OfflineOper();
		condition.setYear(YEAR); 
		condition.setMonth(MONTH);
		String rmId = "cb00bad3f16a4e8baf450e7b88af7c4b" ; 
		condition.setRmId(rmId); ;
		int count = offlineOperMapper.rmCount(condition);
		System.out.println(count);
	}
	
	
	public void queryFromEmployeeByRM() {
		
		OfflineOper condition = new OfflineOper();
		condition.setRmId("cb00bad3f16a4e8baf450e7b88af7c4b");
		
//		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b");  // 张培
//		user.setUserId("cff5fa689a2e40afa02ba2ceda914bbb");  // 梁嘉杰
//		user.setUserType("5");
		
		List<OfflineOper> list = offlineOperMapper.queryFromEmployeeByRM(condition);
		System.out.println(list.size());
	}
	
	public void employeeCount() {
		OfflineOper condition = new OfflineOper();
		String employeeId = "cb00bad3f16a4e8baf450e7b88af7c4b" ; 
		condition.setEmployeeId(employeeId);
		int count = offlineOperMapper.employeeCount(condition);
		System.out.println(count);
	}
	
	
	public void queryRMFromDept() throws Exception{
		OfflineOper condition = new OfflineOper();
		condition.setEmployeeId("04fcb811aeae4808bb303aaf2cabba52");
		OfflineOper o =offlineOperMapper.queryByEmployeeID(condition);
		System.out.println(o);
		System.out.println(objectMapper.writeValueAsString(o));
	}
	
	private void initOfflineOper(OfflineOper data,User user) {
		Employee e = employeeService.queryEmployeeById(data.getEmployeeId()) ;
		user = userService.queryUserById(user.getUserId()) ; 
		
		data.setYear(YEAR);
		data.setMonth(MONTH);
		data.setStaffName(e.getStaffName());
		data.seteHr(e.geteHr());
		data.setEmployeeId(e.getEmployeeId());
		data.setCsSubdeptId(e.getCsSubDept());
		data.setRmId(user.getUserId());
		data.setChsoftiAwHours(new BigDecimal("133"));
		data.setChsoftiIwHours(new BigDecimal("19")); // 27 19
		data.setChsoftiOtHours(new BigDecimal("8"));
		data.setChsoftiToHours(new BigDecimal("9"));
		data.setChsoftiApwHours(new BigDecimal("10"));
		data.setChsoftiInfTravel(new BigDecimal("11"));
		data.setChsoftiInfEquipment(new BigDecimal("12"));
		data.setChsoftiInfSub(new BigDecimal("13"));
		data.setRemark("test");
	}
	
//	public void queryCurrency() {
//		Currencys c = new Currencys();
//		c.setYear(YEAR);
//		c.setMonth(""+3);
//		c.setPlaceWork("HK");
//		c = currencysMapper.queryCurrency(c) ; 
//		System.out.println(c.getExRate());
//	}
	

	@Test
	public void save() {
		OfflineOper data = new OfflineOper();
		
//		data.setEmployeeId("04fcb811aeae4808bb303aaf2cabba52");  //白世铭
//		data.setStaffName("白世铭");
//		User user = new User();
//		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b");  // 张培  12
//		user.setUserType("5");
		
		data.setEmployeeId("1004");  // 王廣智
		data.setStaffName(" 王廣智");
		User user = new User();
		user.setUserId("a42f87d13fff455da434649ab3c8f876");  // 叶海伦  
		user.setUserType("5");
		
		List<OfflineOper> list =  offlineOperService.query(data, user, PAGESIZE , PAGENUMBER) ; //
		if(list.size()>0) {
			offlineOperService.delete(list.get(0).getId());
		}
//		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b");  // 张培  12
//////		user.setUserId("cff5fa689a2e40afa02ba2ceda914bbb");  // 梁嘉杰 9
////		user.setUserId("20f1aeff297d49d4b3c42877687a7076");  // 张盛  9,12
//		user.setUserType("5");
		
//		user.setUserType("3");  
////		user.setUserId("c7b38226545c45e598f16a33031f85aa"); // c7b38226545c45e598f16a33031f85aa 李佳洲
//		user.setCsdeptId("9,12");
		
//		user.setUserType("1"); 
////		user.setUserId("1573"); // 风控数据事业部  潘亮
//		user.setBu("风控数据事业部");
		
		initOfflineOper(data,user);
		System.out.println(offlineOperService.save(data, user));
		
	}
}
