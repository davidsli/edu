 
/**
 * 添加课程执行的函数
 */
function submitCourse1()
{
	var courseName=$("#courseName").val();
	var courIntro=$("#courIntro").val();
	var teacherName=$("#teacherName").val();
	var teacherIntro=$("#teacherIntro").val();
	var school=$("#school").val();
	var subject=$("#subject option:selected").val();
	if(courseName.length<1||courIntro.length<1||teacherName.length<1||teacherIntro.length<1||school.length<1||subject.length<1)
	{
		$.alert('表单填写不正确','提示');
		return false;
	}
	$.ajaxFileUpload({
		   url:"/admin/course/add?courseName="+courseName+"&courIntro="+courIntro+"&teacherName="+teacherName+"&teacherIntro="+teacherIntro+"&school="+school+"&subject="+subject,
		   secureuri: false, //是否需要安全协议，一般设置为false
           fileElementId: 'courseimgUrl', //文件上传域的ID
		   dataType:'JSON',
		   success: function(data)
		   {
			   getPage1('/admin/course/list');
		   }
	   });
}



function uploadFile()
{
	$.ajaxFileUpload({
	   url:"/test/add",
	   secureuri: false, //是否需要安全协议，一般设置为false
       fileElementId: 'file5', //文件上传域的ID
	   dataType:'JSON',
	   success: function(data)
	   {
		   if(data.success==true)
		   {
			   $.alert("添加课程成功","提示");
		   }
		   else
		   {
			   $.alert("什么鬼00",'提示');
		   }
	   },
	   error:function()
	   {
		   alert("处理请求错误");
	   }
   });
}



