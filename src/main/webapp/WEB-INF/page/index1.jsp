<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<title>PMO</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

<!-- The styles -->
<link href="<%=path %>/css/bootstrap-cerulean.min.css" rel="stylesheet">

<link href="<%=path %>/css/charisma-app.css" rel="stylesheet">
<link
	href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet'>
<link
	href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link href='<%=path %>/bower_components/chosen/chosen.min.css'
	rel='stylesheet'>
<link href='<%=path %>/bower_components/colorbox/example3/colorbox.css'
	rel='stylesheet'>
<link
	href='<%=path %>/bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet'>
<link
	href='<%=path %>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>
<link
	href='<%=path %>/bower_components/bootstrap-val/bootstrapValidator.css'
	rel='stylesheet'>
<link href='<%=path %>/css/jquery.noty.css' rel='stylesheet'>
<link href='<%=path %>/css/noty_theme_default.css' rel='stylesheet'>
<link href='<%=path %>/css/elfinder.min.css' rel='stylesheet'>
<link href='<%=path %>/css/elfinder.theme.css' rel='stylesheet'>
<link href='<%=path %>/css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='<%=path %>/css/uploadify.css' rel='stylesheet'>
<link href='<%=path %>/css/animate.min.css' rel='stylesheet'>
<link href='<%=path%>/css/bootstrap-datetimepicker.css' rel='stylesheet'>
<link href='<%=path%>/css/bootstrap-datetimepicker.min.css' rel='stylesheet'>
<link href='<%=path%>/css/style.css' rel='stylesheet'>

<!-- jQuery -->
<script src="<%=path %>/bower_components/jquery/jquery.min.js"></script>

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="<%=path %>/img/favicon.ico">

</head>
<script>
var path='<%=path%>';
</script>
<body>
	<!-- topbar starts -->
	<c:import url="/service/manage/top" />
	<!-- topbar ends -->
	<div class="ch-container">
		<div class="row">
			<!-- left menu starts -->
			<c:import url="/service/manage/left" />
			<!-- left menu ends -->
			<div id="content" class="col-lg-10 col-sm-10">
				<!-- content starts -->
				<div class="row" >					
				<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well" data-original-title="">
								<h2>
									<i class="glyphicon glyphicon-user"></i> Add Employee
								</h2>
							</div>
							<div id="register" class="box-content" style="overflow: auto;">
							<form id="registerEmployeeForm" method="post">
							    
							    <div id="successAlert" class="alert alert-success" style="display: none;"></div>
							    <div class="form-group">
										<div class="group">
											<label class="col-sm-2 control-label">E-HR</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="eHr"
													id="eHr" data-bv-group=".group"/>
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">LOB</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="lob"
													id="lob" data-bv-group=".group"/>
											</div>
										</div>
										
								</div>
								
								<div class="form-group">
								
										<div class="group">
											<label class="col-sm-2 control-label">HSBC Staff ID</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="hsbcStaffId"
													id="hsbcStaffId" data-bv-group=".group"/>
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Staff Name</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="staffName"
													id="staffName" data-bv-group=".group"/>
											</div>
										</div>
								</div>
								
								<div class="form-group">
										<div class="group">
											<label class="col-sm-2 control-label">LN</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="LN"
													id="LN" data-bv-group=".group"/>
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Email</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="email"
													id="email" data-bv-group=".group"/>
											</div>
										</div>
										
								</div>
								
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Staff Location</label>
										<div class="col-lg-4">
											<select class="form-control" name="staffLocation" data-bv-notempty
												data-bv-notempty-message="Please Select  StaffLocation" id="staffLocation" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">Location Type</label>
										<div class="col-lg-4">
											<select class="form-control" name="locationType" data-bv-notempty
												data-bv-notempty-message="Please Select  LocationType" id="locationType" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
								</div>
								
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">CS Dept</label>
										<div class="col-lg-4">
											<select class="form-control" name="csSubDept" data-bv-notempty
												data-bv-notempty-message="Please Select  csDept" id="csSubDept" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">GBGF</label>
										<div class="col-lg-4">
											<select class="form-control" name="gbGf" data-bv-notempty
												data-bv-notempty-message="please select  GB_GF" id="gbGf" data-bv-group=".group">
											<option value="">--Option--</option>
											</select>
										</div>
										</div>
								</div>
								
								
								<div class="form-group">
								        <div class="group">
										<label class="col-lg-2 control-label">HSBC Dept</label>
										<div class="col-lg-4">
											<select class="form-control" name="hsbcDept" id="hsbcDept" data-bv-notempty
												data-bv-notempty-message="please select  HSBDept" data-bv-group=".group">
											<option value="">--Option--</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">HSBC Sub Dept</label>
										<div class="col-lg-4">
											<select class="form-control" name="hsbcSubDept"  id="hsbcSubDept" data-bv-notempty
												data-bv-notempty-message="please select  HSBSubDept" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
								</div>
								
								<div class="form-group">
										<div class="group">
											<label class="col-sm-2 control-label">SOW#</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="sow"
													id="sow" data-bv-group=".group"/>
											</div>
										</div>
										<div class="group">
										<label class="col-sm-2 control-label ">SOW# Expired Date</label>
										<div class="col-md-4">
											<div class="input-group date form_datetime1 col-sm-12"
												data-link-field="dt_set_order_time_input">
												<input class="form-control" type="text" readonly data-bv-group=".group"
													id="sowExpiredDate1" name="sowExpiredDate1"> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span> <input type="hidden"
													id="sowExpiredDate2" name="sowExpiredDate2" />
											</div>
										</div>
									    </div>
										
								</div>
								
								<div class="form-group">
										<div class="group">
											<label class="col-sm-2 control-label">HSBC Project Name</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="hsbcProjectName"
													id="hsbcProjectName" data-bv-group=".group"/>
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">HSBC Project Manager</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="hsbcProjectManager"
													id="hsbcProjectManager" data-bv-group=".group"/>
											</div>
										</div>
								</div>
								
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Staff Category</label>
										<div class="col-lg-4">
											<select class="form-control" name="staffCategory" data-bv-notempty
												data-bv-notempty-message="please select  staffCategory" id="staffCategory" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">Engagement Type</label>
										<div class="col-lg-4">
											<select class="form-control" name="engagementType" data-bv-notempty
												data-bv-notempty-message="please select  engagementType" id="engagementType" data-bv-group=".group">
												<option value="">--Option--</option>
											    <option value="Time&Material">Time&Material</option>
                                                <option value="Time&Material">Team Delivery</option>
											</select>
										</div>
										</div>
								</div>
								
								<div class="form-group">
									<div class="group">
										<label class="col-sm-2 control-label">Graduation Date</label>
										<div class="col-md-4">
											<div class="input-group date form_datetime0 col-sm-12"
												data-link-field="dt_set_order_time_input">
												<input class="form-control" type="text" readonly data-bv-group=".group"
													id="graduationDate1" name="graduationDate1"> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span> <input type="hidden"
													id="graduationDate2" name="graduationDate2" />
											</div>
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">EntryDate</label>
										<div class="col-md-4">
											<div class="input-group date form_datetime2 col-sm-12"
												data-link-field="dt_set_order_time_input">
												<input class="form-control" type="text" readonly data-bv-group=".group"
													id="entryDate1" name="entryDate1"> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span> <input type="hidden"
													id="entryDate2" name="entryDate2" />
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Staff Region</label>
										<div class="col-lg-4">
											<select class="form-control" name="staffRegion" data-bv-notempty
												data-bv-notempty-message="Please Select  staffRegion" id="staffRegion" data-bv-group=".group" onchange="changeData()">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">Onshore or Offshore</label>
										<div class="col-lg-4">
											<select class="form-control" name="onshoreOrOffshore" data-bv-notempty
												data-bv-notempty-message="Please Select  onshore or Offshore" id="onshoreOrOffshore" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
								</div>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">MSA Role</label>
										<div class="col-lg-4">
											<select class="form-control" name="role" data-bv-notempty
												data-bv-notempty-message="please select  msaRole" id="role" data-bv-group=".group" onchange="changeData()">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">Skills/Technology</label>
										<div class="col-lg-4">
											<select class="form-control" name="skill" data-bv-notempty
												data-bv-notempty-message="please select  skills" id="skill" data-bv-group=".group" onchange="changeData()">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
								</div>
								
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Billing Currency</label>
										<div class="col-lg-4">
											<select class="form-control" name="billingCurrency" data-bv-notempty
												data-bv-notempty-message="please select  billingCurrency" id="billingCurrency" data-bv-group=".group">
											<option value="">--Option--</option>
											</select>
										</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Bill Rate</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="billRate"
													id="billRate" data-bv-group=".group"/>
											</div>
										</div>
								</div>
								<div class="form-group">
									<div class="group">
										<label class="col-sm-2 control-label">HSBC DOJ</label>
										<div class="col-md-4">
											<div class="input-group date form_datetime col-sm-12"
												data-link-field="dt_set_order_time_input">
												<input class="form-control" type="text" readonly data-bv-group=".group"
													id="hsbcDOJ1" name="hsbcDOJ1"> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span> <input type="hidden"
													id="hsbcDOJ2" name="hsbcDOJ2" />
											</div>
										</div>
									</div>
									<div class="group">
											<label class="col-sm-2 control-label">RM</label>
											<div class="col-sm-4">
												<select class="form-control" name="nickName" data-bv-notempty
													 id="RM" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
											</div>
										</div>
								</div>
								
								<div class="form-group">
									<div class="group">
										<label class="col-sm-2 control-label">IT Work Year</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="itworkyear"
													id="itworkyear" data-bv-group=".group"/>
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">Chsofti Pro Number</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="chsoftiProNumber"
												   id="chsoftiProNumber" data-bv-group=".group"/>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="group">
										<label class="col-sm-2 control-label">Chsofti Pro StartDate</label>
										<div class="col-md-4">
											<div class="input-group date form_datetime3 col-sm-12"
												 data-link-field="dt_set_order_time_input">
												<input class="form-control" type="text" readonly data-bv-group=".group"
													   id="chsoftiProStartDate1" name="chsoftiProStartDate1"> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span> <input type="hidden" id="chsoftiProStartDate2" name="chsoftiProStartDate2" />
											</div>
										</div>
									</div>

									<div class="group">
										<label class="col-sm-2 control-label">Chsofti Pro Name</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="chsoftiProName"
												   id="chsoftiProName" data-bv-group=".group"/>
										</div>
									</div>
								</div>
								<!-- <div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Resource Status</label>
										<div class="col-lg-4">
											<select class="form-control" name="resourceStatus" data-bv-notempty
												data-bv-notempty-message="please select  resourceStatus" id="resourceStatus" data-bv-group=".group">
												<option value="">--Option--</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-sm-2 control-label">If terminated,mention LWD</label>
										<div class="col-md-4">
											<div class="input-group date form_datetime2 col-sm-12"
												data-link-field="dt_set_order_time_input">
												<input class="form-control" type="text" readonly data-bv-group=".group"
													id="terminatedDate1" name="terminatedDate1"> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span> <input type="hidden"
													id="terminatedDate2" name="terminatedDate2" />
											</div>
										</div>
									    </div>
								</div> -->
								
								
								
								<div class="form-group">
									    <div style="text-align:center;width:50%;float:left">
									    <input type="button" value="Add"
										name="subscribe" id="sub_search" href="#"
										class="button btn btn-primary" data-dismiss="modal"
										onclick="addEmployee()"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
									    </div>
									    <div style="text-align:center;width:50%;float:right">
									    <input type="reset" value="Reset"
										name="subscribe" id="sub_add" href="#"
										class="button btn btn-primary" data-dismiss="modal"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
									    </div>
								</div>
								
							</form>
							</div>
						</div>
				</div>
				</div>
				<!--/row-->
				<!-- content ends -->
			</div>
			<!--/#content.col-md-0-->
		</div>

		<hr>

		 
		

		<c:import url="/service/manage/footer" />

	</div>
	
	<!-- IT行业工作年限不能大于实际工作年限 -->
	<div class="modal fade" id="modal-container-489917" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					    <div class="modal-header">
							 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							 <h4 class="modal-title" id="myModalLabel">
								 Warning!
							 </h4>
						</div>
						<div class="modal-body">
				          <h5>IT industry work years can't longer than the actual work years</h5>
			            </div>
						<div class="modal-footer">
							 <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
					
				</div>
				
	</div>
	<!--/.fluid-container-->

	<!-- external javascript -->

	<script
		src="<%=path %>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script
		src="<%=path %>/bower_components/bootstrap-val/bootstrapValidator.min.js"></script>

	<!-- library for cookie management -->
	<script src="<%=path %>/js/jquery.cookie.js"></script>
	<!-- calender plugin -->
	<script src='<%=path %>/bower_components/moment/min/moment.min.js'></script>
	<script
		src='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='<%=path %>/js/jquery.dataTables.min.js'></script>

	<!-- select or dropdown enhancer -->
	<script src="<%=path %>/bower_components/chosen/chosen.jquery.min.js"></script>
	<!-- plugin for gallery image view -->
	<script
		src="<%=path %>/bower_components/colorbox/jquery.colorbox-min.js"></script>
	<!-- notification plugin -->
	<script src="<%=path %>/js/jquery.noty.js"></script>
	<!-- library for making tables responsive -->
	<script
		src="<%=path %>/bower_components/responsive-tables/responsive-tables.js"></script>
	<!-- tour plugin -->
	<script
		src="<%=path %>/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
	<!-- star rating plugin -->
	<script src="<%=path %>/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="<%=path %>/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="<%=path %>/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="<%=path %>/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="<%=path %>/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="<%=path %>/js/charisma.js"></script>

	<script type="text/javascript" src="<%=path %>/js/pmo/loadHSBCDept.js"></script>
	<script type="text/javascript" src="<%=path %>/js/pmo/employeeValid2.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>


