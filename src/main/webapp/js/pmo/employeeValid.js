$(document).ready(function() {
	$('#registerEmployeeForm').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded:[":disabled"],
        fields: {
        	eHr: {
				validators: {
                    notEmpty: {
                        message: 'Please enter E-HR'
                    },
                    regexp: {
                        regexp: /^E\d{9}$/,
                        message: 'Please enter the E-HR（E and 9 digits）'
                    },
                    remote:{
                    	type:"post",
                    	url: path+'/service/employee/checkEhr',
                        message:"E-HR already exists"
                    }

                 }
            },
            itworkyear: {
                validators: {
                    notEmpty: {
                        message: 'Please enter Itworkyear'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
                    regexp:{
            			regexp:/^([1-9]\d*(\.\d*[0-9])?(\/(m|M))?)$|^(0\.\d*[0-9](\/(m|M))?)$/,
            			message:'Please enter the correct format.'
            		},
                }
            },

            lob: {
                validators: {
                    notEmpty: {
                        message: 'Please enter lob'
                    },
                    numeric: {
                    	message:'Please enter number'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
                    remote:{
                    	type:"post",
                    	url: path+'/service/employee/checkLob',
                        message:"LOB already exists"
                    }
                }
            },
            hsbcStaffId: {
                validators: {
                    numeric: {
                    	message:'Please enter number'
                    },
                    remote:{
                    	type:"post",
                    	url: path+'/service/employee/checkHSBCStaffID',
                        message:"hsbcStaffId already exists"
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
                }
            },
            staffName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter staffName'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },

                }
            },
            LN: {
                validators: {
                	regexp:{
            			regexp:/^[0-9a-zA-Z\s?]+$/,
            			message:'please enter english.'
            		},
            		stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
                }
            },

            hsbcProjectName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter  hsbcProjectName'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },

                }
            },
        
            hsbcProjectManager: {
                validators: {
                    notEmpty: {
                        message: 'Please select  hsbcProjectManager'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },

                }
            },
            sow: {
                validators: {
                    notEmpty: {
                        message: 'Please select  sow'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },

                }
            },
            graduationDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select Graduation Date'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'  
                    }

                }
            },
            hsbcDOJ1: {
                validators: {
                    notEmpty: {
                        message: 'Please select hsbcDOJ'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'
                    }

                }
            },
            chsoftiProStartDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select chsoftiProStartDate'
                    },
                    date : {
                        format : 'YYYY-MM-DD',
                        message : 'Time format is incorrect'
                    }
                }
            },
            sowExpiredDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select sowExpiredDate'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'  
                    }

                }
            },
            billRate: {
                validators: {
                    notEmpty: {
                        message: 'Please Choose  billRate'
                    },
                    numeric:{
                    	message:'Please enter number'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
                    regexp:{
            			regexp:/^([1-9]\d*(\.\d*[1-9])?)|(0\.\d*[1-9])$/,
            			message:'billRate is not zero.'
            		},
                }
            },
            email: {
                validators: {
                	 regexp: {
                          regexp:/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/,
                          message: 'Please enter correct Email'
                      },
                      stringLength: {
                          max: 32,
                          message: 'Exceeded the maxLength'
                      },
                      
                }
            },
            entryDate1: {
            	validators: {
                notEmpty: {
                    message: 'Please select EntryDate'
                },
                date : {  
                    format : 'YYYY-MM-DD',  
                    message : 'Time format is incorrect'  
                }

            }
        },
         
        }
    });
    
    $('#updateEmployeeForm').bootstrapValidator({
		message: 'This value is not valid',

        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	eHr: {
				validators: {
                    notEmpty: {
                        message: 'Please enter  E-HR'
                    },
                    regexp: {
                        regexp: /^E\d{9}$/,
                        message: 'Please enter the E-HR（E and 9 digits）'
                    },
                    

                 }
            },

            lob: {
                validators: {
                    notEmpty: {
                        message: 'Please enter  lob'
                    },
                    numeric: {
                    	message:'Please enter number'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
                }
            },
            hsbcStaffId: {
                validators: {
                    numeric: {
                    	message:'Please enter number'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
                   
                }
            },
            staffName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter staffName'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },

                }
            },
            LN: {
                validators: {
            		regexp:{
            			regexp:/^[0-9a-zA-Z\s?]+$/,
            			message:'please enter english.'
            		},
            		stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
                }
            },
            
            hsbcProjectName: {
                validators: {
                    notEmpty: {
                        message: 'Please enter hsbcProjectName'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
            		
                }
            },
        
            hsbcProjectManager: {
                validators: {
                    notEmpty: {
                        message: 'Please select hsbcProjectManager'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },

                }
            },
            sow: {
                validators: {
                    notEmpty: {
                        message: 'Please select sow'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },

                }
            },
            billRate: {
                validators: {
                    notEmpty: {
                        message: 'Please Choose billRate'
                    },
                    numeric:{
                    	message:'please enter number'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
                    regexp:{
            			regexp:/^([1-9]\d*(\.\d*[1-9])?)|(0\.\d*[1-9])$/,
            			message:'billRate is not zero.'
            		},

                }
            },
            email: {
                validators: {
                	notEmpty: {
                		message: 'Please enter email'
                	},
                    emailAddress:{
                    	message:'Email address is incorrect'
                    },
                    stringLength: {
                        max: 32,
                        message: 'Exceeded the maxLength'
                    },
                }
            },
            sowExpiredDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select sowExpiredDate'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'  
                    }

                }
            },
            graduationDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select Graduation Date'
                    },
                    date : {  
                        format : 'YYYY-MM-DD',  
                        message : 'Time format is incorrect'  
                    }

                }
            },
            entryDate1: {
            	validators: {
                notEmpty: {
                    message: 'Please select EntryDate'
                },
                date : {  
                    format : 'YYYY-MM-DD',  
                    message : 'Time format is incorrect'  
                }

            }
        },
        hsbcDOJ1: {
            validators: {
                notEmpty: {
                    message: 'Please select hsbcDOJ'
                },
                date : {  
                    format : 'YYYY-MM-DD',  
                    message : 'Time format is incorrect'  
                }

            }
        },
            chsoftiProStartDate1: {
                validators: {
                    notEmpty: {
                        message: 'Please select chsoftiProStartDate'
                    },
                    date : {
                        format : 'YYYY-MM-DD',
                        message : 'Time format is incorrect'
                    }
                }
            },
        terminatedDate1: {
            validators: {
                notEmpty: {
                    message: 'Please select terminatedDate'
                },
                date : {  
                    format : 'YYYY-MM-DD',  
                    message : 'Time format is incorrect'  
                }

            }
        },
        }
    }) 
});



