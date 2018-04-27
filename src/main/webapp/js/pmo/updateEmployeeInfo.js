$(function() {

	loadEmployeeInfo();
	dateType();
});

function changeData() {
	var staffRegion = $('#staffRegion').val();
	var role = $('#role').val();
	var skill = $('#skill').val();
	if ('' == staffRegion || staffRegion == null) {
		return;
	}
	if ('' == role || role == null) {
		return;
	}
	if ('' == skill || skill == null) {
		return;
	}

	$.ajax({
		url : path + '/service/interview/getBillRate',
		dataType : "json",
		async : true,
		data : {
			"staffRegion" : staffRegion,
			"role" : role,
			"skill" : skill
		},
		cache : false,
		type : "post",
		success : function(data) {
			if (data != null) {
				$('#billRate').val(data);
			}
		}
	})
}

function updateEmployee() {
	var bootstrapValidator = $("#updateEmployeeForm")
			.data('bootstrapValidator');
	bootstrapValidator.validate();
	if (bootstrapValidator.isValid()) {
		var employeeId = $('#employeeId').val();
		var eHr = $('#eHr').val();
		var lob = $('#lob').val();
		var hsbcStaffId = $('#hsbcStaffId').val();
		var staffName = $('#staffName').val();
		var LN = $('#LN').val();
		var staffRegion = $('#staffRegion').val();
		var staffLocation = $('#staffLocation').val();
		var locationType = $('#locationType').val();
		var onshoreOrOffshore = $('#onshoreOrOffshore').val();
		var csSubDept = $('#csSubDept').val();
		var hsbcSubDept = $('#hsbcSubDept').val();
		var projectName = $('#hsbcProjectName').val();
		var projectManager = $('#hsbcProjectManager').val();
		var sow = $('#sow').val();
		var sowExpiredDate = $('#sowExpiredDate1').val();
		var staffCategory = $('#staffCategory').val();
		var engagementType = $('#engagementType').val();
		var hsbcDOJ = $('#hsbcDOJ1').val();
		var graduationDate = $('#graduationDate1').val();
		var role = $('#role').val();
		var skill = $('#skill').val();
		var billingCurrency = $('#billingCurrency').val();
		var billRate = $('#billRate').val();
		var resourceStatus = $('#resourceStatus').val();
		var terminatedDate = $('#terminatedDate1').val();
		var terminationReason = $('#terminationReason').val();
		var email =  $('#email').val();
		var entryDate = $('#entryDate1').val();
		var gbGf = $('#gbGf').val();
		
		var chsoftiProNumber =  $('#chsoftiProNumber').val();
		var chsoftiProStartDate1 = $('#chsoftiProStartDate1').val();
		var chsoftiProName = $('#chsoftiProName').val();
		
		//拿到IT行业工作年限
		var itWorkYear = $("#itworkyear").val();
		
		//alert("毕业日期"+graduationDate);
		var currentDate = getCurrentDate();
		//alert("当前日期"+currentDate);
		var date1_temp = graduationDate.split("-");  
		var date2_temp = currentDate.split("-"); 
		var date1 = new Date(date1_temp[0], date1_temp[1]-1, date1_temp[2]);
		var date2 = new Date(date2_temp[0], date2_temp[1]-1, date2_temp[2]);  
		var days = date2.getTime() - date1.getTime(); 
		var year = parseInt(days / (1000 * 60 * 60 * 24 * 365)); 
		//alert("毕业的年限"+year);
		//判断IT工作年限不能大于实际工作年限
		//if(year!=null && year!=""){
			//alert(parseInt(itWorkYear));
			//alert(parseInt(year));
			if(parseInt(itWorkYear)>parseInt(year)){
				$("#modal-container-489917").modal('show');
				return;
			}
		//}
		
		
		$.ajax({
			url:path+'/service/employee/updateEmployee',
			dataType:"json",
			data:{"employeeId":employeeId,"eHr":eHr,"lob":lob,"hsbcStaffId":hsbcStaffId,"staffName":staffName,"LN":LN,"staffRegion":staffRegion,"staffLocation":staffLocation,"locationType":locationType,"onshoreOrOffshore":onshoreOrOffshore,"csSubDept":csSubDept,"hsbcSubDept":hsbcSubDept,"projectName":projectName,"projectManager":projectManager,"sow":sow,"sowExpiredDate":sowExpiredDate,"staffCategory":staffCategory,"engagementType":engagementType,"hsbcDOJ":hsbcDOJ,"graduationDate":graduationDate,"role":role,"skill":skill,"billingCurrency":billingCurrency,"billRate":billRate,"resourceStatus":resourceStatus,"terminatedDate":terminatedDate,"terminationReason":terminationReason,
				"email":email,"entryDate":entryDate,"gbGf":gbGf,"itindustryWorkYear":itWorkYear,
				"chsoftiProNumber":chsoftiProNumber,"chsoftiProStartDate":chsoftiProStartDate1,"chsoftiProName":chsoftiProName	
			},
			async:true,
			cache:false,
			type:"post",
			success:function(resultFlag){
				if(resultFlag){
					$("html,body").animate({scrollTop:0}, 500);
					$('#successAlert').html('Staff:'+staffName+' information edited succesffully').show();
					setTimeout(function () {
						$('#successAlert').hide();
					}, 2000);
					
					var urlTo = path+'/service/employee/employeeInfo.html';
					window.location.href = urlTo;
				}
				if(!resultFlag){
					alert('1');
				}
			}
		})
	}else{
		return;
	}
}

function loadEmployeeInfo() {
	var employeeId = $('#employeeId').val();

	$.ajax({
		url : path + '/service/employee/queryEmployeeById',
		dataType : "json",
		data : {
			"employeeId" : employeeId
		},
		async : true,
		cache : false,
		type : "post",
		success : function(employee) {

			loadStaffCategory(employee);
			loadEngagementType(employee);
			loadRole(employee);
			loadSkill(employee);
			loadBillingCurrency(employee);
			loadResourceStatus(employee);
			loadCSDept(employee);
			loadStaffRegion(employee);
			loadStaffLocation(employee);
			loadLocationType(employee);
			loadOnshoreOrOffshore(employee);
			loadTerminationReason(employee);
			loadPersonHsbcDept(employee);
			loadGbGf(employee);
			$('#hsbcStaffId').val(employee.hsbcStaffId);
			$('#lob').val(employee.lob);
			$('#hsbcProjectName').val(employee.projectName);
			$('#hsbcProjectManager').val(employee.projectManager);
			$('#staffName').val(employee.staffName);
			$('#LN').val(employee.ln);
			$('#sow').val(employee.sow);
			$('#sowExpiredDate1').val(employee.sowExpiredDate);
			$('#hsbcDOJ1').val(employee.hsbcDOJ);
			$('#graduationDate1').val(employee.graduationDate);
			$('#billRate').val(employee.billRate);
			$('#terminatedDate1').val(employee.terminatedDate);
			$('#eHr').val(employee.eHr);
			$('#email').val(employee.email);
			$('#entryDate1').val(employee.entryDate);
			$('#itworkyear').val(employee.itindustryWorkYear);
			$('#chsoftiProNumber').val(employee.chsoftiProNumber);
			$('#chsoftiProStartDate1').val(employee.chsoftiProStartdate);
			$('#chsoftiProName').val(employee.chsoftiProName);
		}
	})
}

function dateType() {
	$('.form_datetime').datetimepicker({
		weekStart : 1,
		minView : 'month',
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		pickerPosition : 'bottom-left',
		showMeridian : 1
	}).on('changeDate', function(ev){
		 $('#updateEmployeeForm').bootstrapValidator('revalidateField', 'sowExpiredDate1'); 
		 $('#updateEmployeeForm').bootstrapValidator('revalidateField', 'graduationDate1');
		 $('#updateEmployeeForm').bootstrapValidator('revalidateField', 'entryDate1');
		 $('#updateEmployeeForm').bootstrapValidator('revalidateField', 'hsbcDOJ1');
		 $('#updateEmployeeForm').bootstrapValidator('revalidateField', 'terminatedDate1');
		 $('#updateEmployeeForm').bootstrapValidator('revalidateField', 'chsoftiProStartDate1');
	});;
}

function loadOnshoreOrOffshore(employee) {
	var url = path + '/json/onshoreOrOffshore.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#onshoreOrOffshore")
					.append("<option>" + item.name + "</option>");
		})
		$('#onshoreOrOffshore').val(employee.onshoreOrOffshore);
	});
}

function loadLocationType(employee) {
	var url = path + '/json/locationType.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#locationType").append("<option>" + item.name + "</option>");
		})
		$('#locationType').val(employee.locationType);
	});
}

function loadStaffLocation(employee) {
	var url = path + '/json/staffLocation.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#staffLocation").append("<option>" + item.name + "</option>");
		})
		$('#staffLocation').val(employee.staffLocation);
	});
}

function loadStaffRegion(employee) {
	var url = path + '/json/staffRegion.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#staffRegion").append("<option>" + item.name + "</option>");
		})
		$('#staffRegion').val(employee.staffRegion);
	});
}

function loadResourceStatus(employee) {
	var url = path + '/json/resourceStatus.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#resourceStatus").append("<option>" + item.name + "</option>");
		})
		$('#resourceStatus').val(employee.resourceStatus);
		if ($('#resourceStatus').val() == "Active") {
			$("#terminatedDateDiv").hide();
			$("#terminationReasonDiv").hide();
		} else if ($('#resourceStatus').val() == "Released") {
			$("#terminatedDateDiv").show();
			$("#terminationReasonDiv").hide();
		}else if ($('#resourceStatus').val() == "Transfer") {
			$("#terminatedDateDiv").show();
			$("#terminationReasonDiv").hide();
		} else {
			$("#terminatedDateDiv").show();
			$("#terminationReasonDiv").show();
		}
	});

}

$("#resourceStatus").bind("click", function() {
	if ($('#resourceStatus').val() == "Active") {
		$("#terminatedDateDiv").hide();
		$("#terminationReasonDiv").hide();
	} else if ($('#resourceStatus').val() == "Released") {
		$("#terminatedDateDiv").show();
		$("#terminationReasonDiv").hide();
	} else if ($('#resourceStatus').val() == "Transfer") {
		$("#terminatedDateDiv").show();
		$("#terminationReasonDiv").hide();
	}  else {
		$("#terminatedDateDiv").show();
		$("#terminationReasonDiv").show();
	}
});

function loadBillingCurrency(employee) {
	var url = path + '/json/billingCurrency.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#billingCurrency").append("<option>" + item.name + "</option>");
		})
		$('#billingCurrency').val(employee.billingCurrency);
	});
}

function loadBillingEntity() {
	var url = path + '/json/billingEntity.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#billingEntity").append("<option>" + item.name + "</option>");
		})
	});
}

function loadSkill(employee) {
	var url = path + '/json/skill.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#skill").append("<option>" + item.name + "</option>");
		})
		$('#skill').val(employee.skill);
	});
}

function loadSource() {
	var url = path + '/json/source.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#source").append("<option>" + item.name + "</option>");
		})
	});
}

function loadRole(employee) {
	var url = path + '/json/role.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#role").append("<option>" + item.name + "</option>");
		})
		$('#role').val(employee.role);
	});
}
function loadGbGf(employee){
	var url = path + '/json/gbGf.json';
	$.getJSON(url, function(data){
		$.each(data, function(i, item){
			$("#gbGf").append("<option>"+item.name+"</option>");
		})
		$('#gbGf').val(employee.gbGf);
	});
}
function loadStaffCategory(employee) {
	var url = path + '/json/staffCategory.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#staffCategory").append("<option>" + item.name + "</option>");
		})
		$('#staffCategory').val(employee.staffCategory);
	});
}

function loadEngagementType(employee) {
	var url = path + '/json/engagementType.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#engagementType").append("<option>" + item.name + "</option>");
		})
		$('#engagementType').val(employee.engagementType);
	});
}

function loadCSDept(employee) {

	$.ajax({
		url : path + '/service/csDept/queryAllCSSubDept',
		dataType : "json",
		async : true,
		cache : false,
		type : "post",
		success : function(result) {
			var userType = result.user.userType;

			var csSubs = result.csSubDepts;

			for (var i = 0; i < result.data.length; i++) {
				$("#csSubDept").append(
						"<option value='" + result.data[i].csSubDeptId + "'>"
								+ result.data[i].csSubDeptName + "</option>");
			}
			$('#csSubDept').val(employee.csSubDept);
			if(userType!=0){
				$("#csSubDept").attr("disabled","disabled");
			}
		}
	})

}

function loadPersonHsbcDept(employee) {
	var hsbcSubDeptId = employee.hsbcSubDept;
	if(hsbcSubDeptId==""||hsbcSubDeptId==null){
		loadFirstDept(employee);
	}else{
		$.ajax({
			url : path + '/service/hsbcDept/queryDeptById',
			dataType : "json",
			async : true,
			cache : false,
			data : {
				"hsbcSubDeptId" : hsbcSubDeptId
			},
			type : "post",
			success : function(hsbcDept) {

				loadDept(employee, hsbcDept);
			}
		})
	}
	
	
}

function loadFirstDept(employee) {
	$.ajax({
		url : path + '/service/hsbcDept/queryDeptName',
		dataType : "json",
		async : true,
		cache : false,
		type : "post",
		success : function(list) {
			
			for (var i = 0; i < list.length; i++) {

				$("#hsbcDept").append(
						"<option value='" + list[i].hsbcSubDeptId + "'>"
								+ list[i].hsbcDeptName + "</option>");
			}

			loadHSBCSubDept(employee);
		}
	})
}

function loadDept(employee, hsbcDept) {
	$.ajax({
		url : path + '/service/hsbcDept/queryDeptName',
		dataType : "json",
		async : true,
		cache : false,
		type : "post",
		success : function(list) {
			
			var hsbcDeptName = hsbcDept.hsbcDeptName;

			for (var i = 0; i < list.length; i++) {

				$("#hsbcDept").append(
						"<option value='" + list[i].hsbcSubDeptId + "'>"
								+ list[i].hsbcDeptName + "</option>");
				if (list[i].hsbcDeptName == hsbcDeptName) {
					$("#hsbcDept").val(list[i].hsbcSubDeptId);
				}
			}

			loadHSBCSubDept(employee);
		}
	})
}

function loadHSBCSubDept(employee) {
	var hsbcSubDeptId = employee.hsbcSubDept;
	$.ajax({
		url : path + '/service/hsbcDept/querySubDeptName',
		dataType : "json",
		async : true,
		data : {
			"hsbcSubDeptId" : hsbcSubDeptId
		},
		cache : false,
		type : "post",
		success : function(list) {
			if (list.length == 1 && list[0].hsbcSubDeptName == null) {
				$("#hsbcSubDept").append(
						"<option value='"
								+ $('#hsbcDept').find("option:selected").val()
								+ "'>"
								+ $('#hsbcDept').find("option:selected").text()
								+ "</option>");
			} else {
				$("#hsbcSubDept").find("option").remove();
				$("#hsbcSubDept").append(
						"<option value=''>-- Option --</option>");
				for (var i = 0; i < list.length; i++) {
					$("#hsbcSubDept").append(
							"<option value='" + list[i].hsbcSubDeptId + "'>"
									+ list[i].hsbcSubDeptName + "</option>");
				}
			}
			if (hsbcSubDeptId != null && hsbcSubDeptId != '') {
				$("#hsbcSubDept").val(employee.hsbcSubDept)
			}
		}
	})
}

function loadTerminationReason(employee) {
	var url = path + '/json/terminatedReason.json'
	$.getJSON(url, function(data) {
		$.each(data, function(i, item) {
			$("#terminationReason").append(
					"<option value='" + item.name + "'>" + item.name
							+ "</option>");
			if (item.name == employee.terminationReason) {
				$('#terminationReason').val(item.name);
			}
		})
	});
}

$("#hsbcDept").change(
		function() {
			var hsbcSubDeptId = $("#hsbcDept").val();
			$.ajax({
				url : path + '/service/hsbcDept/querySubDeptName',
				dataType : "json",
				async : true,
				data : {
					"hsbcSubDeptId" : hsbcSubDeptId
				},
				cache : false,
				type : "post",
				success : function(list) {
					$("#hsbcSubDept").find("option").remove();
					if (list.length == 1 && list[0].hsbcSubDeptName == null) {
						$("#hsbcSubDept").append(
								"<option value='"
										+ $('#hsbcDept')
												.find("option:selected").val()
										+ "'>"
										+ $('#hsbcDept')
												.find("option:selected").text()
										+ "</option>");
					} else {
						$("#hsbcSubDept").find("option").remove();
						$("#hsbcSubDept").append(
								"<option value=''>-- Option --</option>");
						for (var i = 0; i < list.length; i++) {
							$("#hsbcSubDept").append(
									"<option value='" + list[i].hsbcSubDeptId
											+ "'>" + list[i].hsbcSubDeptName
											+ "</option>");
						}
					}
				}
			})
		})
		
		
//获取当前时间，格式YYYY-MM-DD
function getCurrentDate() {
     var date = new Date();
     var seperator1 = "-";
     var year = date.getFullYear();
     var month = date.getMonth() + 1;
     var strDate = date.getDate();
     if (month >= 1 && month <= 9) {
         month = "0" + month;
     }
     if (strDate >= 0 && strDate <= 9) {
         strDate = "0" + strDate;
     }
     var currentdate = year + seperator1 + month + seperator1 + strDate;
     return currentdate;
}
