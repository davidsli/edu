//检查课程缩略图的图片格式
function checkImgForm(){
	var thumb=$.trim($("#courseimgUrl").val());
    var jpg = thumb.indexOf(".jpg");
    var png = thumb.indexOf(".png");
	if(jpg<0&&png<0){
		$("#courseimgUrl").css("border-color","red");
		$.alert("只能上传png,jpg格式的文件","确定");
		return false;
	}
	$("#courseimgUrl").css("border-color","");
	return true;
}

/**
 * 添加课程执行的函数
 */
function submitCourse1()
{
//	checkImgForm()
	var thumb=$.trim($("#courseimgUrl").val());
    var jpg = thumb.indexOf(".jpg");
    var png = thumb.indexOf(".png");
	if(jpg<0&&png<0){
		$("#courseimgUrl").css("border-color","red");
		$.alert("只能上传png,jpg格式的文件","确定");
		return false;
	}
	$("#courseimgUrl").css("border-color","");
		
	var courseName=$.trim($("#courseName").val());
	var courIntro=$.trim($("#courIntro").val());
	var teacherName=$.trim($("#teacherName").val());
	var teacherIntro=$.trim($("#teacherIntro").val());
	var school=$.trim($("#school").val());
	var subject=$.trim($("#subject option:selected").val());
	if(courseName.length<1||courIntro.length<1||teacherName.length<1||teacherIntro.length<1||school.length<1||subject.length<1)
	{
		$.alert('表单填写不正确','提示');
		return false;
	}
	$.ajaxFileUpload({
		   url:"/edu/admin/course/add?courseName="+courseName+"&courIntro="+courIntro+"&teacherName="+teacherName+"&teacherIntro="+teacherIntro+"&school="+school+"&subject="+subject,
		   secureuri: false, //是否需要安全协议，一般设置为false
           fileElementId: 'courseimgUrl', //文件上传域的ID
		   dataType:'JSON',
		   success: function(data)
		   {
			   getPage1('/edu/admin/course/list');
		   }
	   });
}



