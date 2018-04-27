var cInfoStrJSon = JSON.parse(cInfoStr);

$(function(){
	loadEngagementType();
	loadRole();
	loadSkill();
	loadBillingEntity();
	loadBillingCurrency();
    loadResourceStatus();
	loadCSDept();
	loadUserType();
	loadStaffRegion();
	loadStaffLocation();
	loadLocationType();
	dateType();
	dateType0();
	dateType1();
	dateType2();
	$('#staffName').val(cInfoStrJSon.candidateName);
	$('#email').val(cInfoStrJSon.email);
	$('#graduationDate1').val(cInfoStrJSon.graduateDate);
})

$("#staffRegion").change(function(){
	var staffRegion = $("#staffRegion").val();
	$("#staffLocation").val(regionMap.get(staffRegion));
});

var lastConditionStr = "";
function addEmployee(){
	var bootstrapValidator = $("#registerEmployeeForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
		var eHr = $('#eHr').val();
		var lob = $('#lob').val();
		var staffName = $('#staffName').val();
		var email = $('#email').val();
		var staffRegion = $('#staffRegion').val();
		var staffLocation = $('#staffLocation').val();
		var locationType = $('#locationType').val();
		var csSubDept = $('#csSubDept').val();
		var engagementType = $('#engagementType').val();
		var graduationDate = $('#graduationDate1').val();
		var role = $('#role').val();
		var skill = $('#skill').val();
		var billingCurrency = $('#billingCurrency').val();
		var billRate = $('#billRate').val();
		var entryDate = $('#entryDate1').val();
		var resourceStatus =  $('#resourceStatus').val();
		var chsoftiProNumber =  $('#chsoftiProNumber').val();
		var chsoftiProStartDate1 = $('#chsoftiProStartDate1').val();
		var chsoftiProName = $('#chsoftiProName').val();
		
		
		var addEmpConditionStr = eHr + lob  + staffName + staffRegion + staffLocation + locationType
						 		 + csSubDept + engagementType + graduationDate + role + skill
						         + billingCurrency + billRate  + email + entryDate + resourceStatus
						         + chsoftiProNumber + chsoftiProStartDate1 + chsoftiProName;
		if (lastConditionStr != addEmpConditionStr) {
			lastConditionStr = addEmpConditionStr;
		} else {
			$("html,body").animate({
				scrollTop : 0
			}, 500);
			$('#successAlert').html('Duplicate data,do not resubmit!').show();
			setTimeout(function() {
				$('#successAlert').hide();
			}, 2000);
			return;
		}
		$.ajax({
			url:path+'/service/employee/addEmployee',
			dataType:"json",
			data:{"eHr":eHr,"lob":lob,"staffName":staffName,"staffRegion":staffRegion,"staffLocation":staffLocation,
				"locationType":locationType,"csSubDept":csSubDept,"engagementType":engagementType,
				"graduationDate":graduationDate,"role":role,"skill":skill,"billingCurrency":billingCurrency,
				"billRate":billRate,"resourceStatus":resourceStatus,"email":email,"entryDate":entryDate,
				"chsoftiProNumber":chsoftiProNumber,"chsoftiProStartDate1":chsoftiProStartDate1,"chsoftiProName":chsoftiProName
			},
			async:true,
			cache:false,
			type:"post",
			success:function(resultFlag){
				
				$.ajax({
					url:path+'/service/candidate/updateOnboardCandidate',
					dataType:"json",
					async:true,
					cache:false,
					type:"post",
					success:function(result){
						if(result){
							$("html,body").animate({scrollTop:0}, 500);
							$('#successAlert').html('Employee:'+staffName+' information added succesffully').show();
							setTimeout(function () {
								$('#successAlert').hide();
							}, 2000);
						}
					}
				})
				
				if(resultFlag){
					$("html,body").animate({scrollTop:0}, 500);
					$('#successAlert').html('Employee:'+staffName+' information added succesffully').show();
					setTimeout(function () {
						$('#successAlert').hide();
					}, 2000);
				}
			}
		})
		
	}else{
		return;
	}
	
	
}


function dateType(){
	$('.form_datetime').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	}).on('changeDate', function(ev){
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'hsbcDOJ1'); 
	
	});
}

function dateType0(){
	$('.form_datetime0').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	}).on('changeDate', function(ev){
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'graduationDate1'); 
	
	});
}

function dateType1(){
	$('.form_datetime1').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	}).on('changeDate', function(ev){
	
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'sowExpiredDate1'); 
		
	});
}

function dateType2(){
	$('.form_datetime2').datetimepicker({
		weekStart: 1,
		minView:'month',
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		language:'zh-CN',
		format: 'yyyy-mm-dd',
		pickerPosition: 'bottom-left',
		showMeridian: 1
	}).on('changeDate', function(ev){		 
		 $('#registerEmployeeForm').bootstrapValidator('revalidateField', 'entryDate1'); 
	});
}



function loadLocationType(){
	var url = path+'/json/locationType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#locationType").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadStaffLocation(){
	var url = path+'/json/staffLocation.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#staffLocation").append("<option>"+item.name+"</option>");
	       })
	});
}


var regionMap = new Map();
function loadStaffRegion(){
	var url = path+'/json/staffRegion.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#staffRegion").append("<option>"+item.name+"</option>");
	    	   regionMap.set(item.name,item.key);
	       })
	});
}



var userTypeMap = new Map();
function loadUserType(){
	var url = path+'/json/userType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#type").append("<option value='"+item.key+"'>"+item.name+"</option>");
	    	   userTypeMap.set(item.name,item.key);
	       })
	});
}


function loadResourceStatus(){
	var url = path+'/json/resourceStatus.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#resourceStatus").append("<option>"+item.name+"</option>");
	       })
	});
}


function loadBillingCurrency(){
	var url = path+'/json/billingCurrency.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#billingCurrency").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadBillingEntity(){
	var url = path+'/json/billingEntity.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#billingEntity").append("<option>"+item.name+"</option>");
	       })
	});
}

function loadSkill(){
	var url = path+'/json/skill.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#skill").append("<option>"+item.name+"</option>");
	       })
	       $('#skill').val(cInfoStrJSon.skill);
	});
}


function loadRole(){
	var url = path+'/json/role.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#role").append("<option>"+item.name+"</option>");
	       })
	 $('#role').val(cInfoStrJSon.role);
	});
}


function loadEngagementType(){
	var url = path+'/json/engagementType.json'
	$.getJSON(url,  function(data) {
	       $.each(data, function(i, item) {
	    	   $("#engagementType").append("<option>"+item.name+"</option>");
	       })
	});
}



function loadCSDept(){
	$.ajax({
		url:path+'/service/csDept/queryAllCSSubDept',
		dataType:"json",
		async:true,
		cache:false,
		type:"post",
		success:function(result){
			var userType = result.user.userType;
			
			var csSubs = result.csSubDepts;
			if(userType=='0'){
				for(var i = 0;i<result.data.length;i++){
					$("#csSubDept").append("<option value='"+result.data[i].csSubDeptId+"'>"+result.data[i].csSubDeptName+"</option>");
				}
			}else{
				if(csSubs.length==1){
					$("#csSubDept").append("<option value='"+csSubs[0].csSubDeptId+"'>"+csSubs[0].csSubDeptName+"</option>");
					$('#csSubDept').val(csSubs[0].csSubDeptId);
					$("#csSubDept").attr("disabled","disabled");
					loadUserForRM($('#csSubDept').val());
				}else if(csSubs.length>1){
					$("#csSubDept").empty();
					for(var i = 0;i<csSubs.length;i++){
						$("#csSubDept").append("<option value='"+csSubs[i].csSubDeptId+"'>"+csSubs[i].csSubDeptName+"</option>");
					}
					loadUserForRM($('#csSubDept').val());
				}else{
					for(var i = 0;i<result.data.length;i++){
						$("#csSubDept").append("<option value='"+result.data[i].csSubDeptId+"'>"+result.data[i].csSubDeptName+"</option>");
					}
				}
			}
		}
	})
}





function changeData(){
	var staffRegion = $('#staffRegion').val();
	var role = $('#role').val();
	var skill = $('#skill').val();
	if('' == staffRegion || staffRegion== null){
		return;
	}
	if('' == role || role== null){
		return;
	}
	if('' == skill || skill== null){
		return;
	}
	
	$.ajax({
		url:path+'/service/interview/getBillRate',
		dataType:"json",
		async:true,
		data:{"staffRegion":staffRegion,"role":role,"skill":skill},
		cache:false,
		type:"post",
		success:function(data){
			if(data != null){
				$('#billRate').val(data);
			}
		}
	})
}
