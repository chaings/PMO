package com.pmo.dashboard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.ExportExcel;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.OfflineOperService;

@Controller
@RequestMapping(value="/offlineOper")
public class OfflineOperController {
	private static Logger logger = LoggerFactory
            .getLogger(OfflineOperController.class);
	private ObjectMapper objectMapper = new ObjectMapper();  
	
	@Resource
	private OfflineOperService offlineOperService;
	
	@Resource
	private CSDeptService csDeptService;
	
	@Resource
    private EmployeeService employeeService;
	
	@Resource
	private ExportExcel exportExcel;
	
	
	@RequestMapping("/listPage")
	public String listPage(){
		return "offline/offline";
	}
	
	/**
	 * 查询数据
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/query")
	@ResponseBody
	public String query(int pageSize,int pageNumber,OfflineOper condition,HttpServletRequest request) throws JsonProcessingException{
		User user = (User) request.getSession().getAttribute("loginUser");
		List<OfflineOper> data = offlineOperService.query(condition,user,pageSize,pageNumber);
		PageInfo<OfflineOper> page = new PageInfo(data);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", page.getTotal());
		map.put("rows", data);
		return objectMapper.writeValueAsString(map);
	}
	
	/**
	 * 保存数据
	 * @param offo
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value= {"/save"},method=RequestMethod.POST)
	@ResponseBody
	public String save(@RequestBody OfflineOper offlineOper,HttpServletRequest request) throws JsonProcessingException{
		User user = (User) request.getSession().getAttribute("loginUser");
		boolean rtn = offlineOperService.save(offlineOper,user);
		return objectMapper.writeValueAsString( rtn? "0" : "-1");
	}
	
	@RequestMapping(value= {"/export"})
	public String export(HttpServletRequest request,HttpServletResponse response) throws JsonProcessingException{
		User user = (User) request.getSession().getAttribute("loginUser");
//		boolean rtn = offlineOperService.save(offlineOper,user);
//		return objectMapper.writeValueAsString( rtn? "0" : "-1");
		
		String fileName = exportExcel.export("过程数据",user);
		File file = new File(fileName);
		String date = LocalDate.now().toString();
		
		String downloadFilename = "Bussiness Data_"+date+".xlsx";
		// 以流的形式下载文件。
		try {
	        InputStream fis = new BufferedInputStream(new FileInputStream(fileName));
	        byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        fis.close();
	        // 清空response
	        response.reset();
	        // 设置response的Header
	        response.addHeader("Content-Disposition", "attachment;filename=" + downloadFilename);
	        //response.setContentType("application/octet-stream");
	        response.setContentType("application/vnd.ms-excel");
	        response.addHeader("Content-Length", "" + file.length());
	        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	        
	        toClient.write(buffer);
	        toClient.flush();
	        toClient.close();
	        file.delete();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
