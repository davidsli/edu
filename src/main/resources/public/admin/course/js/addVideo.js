function SubmitVideo()
{
	var videoName=$("#videoName").val();
	var chapter=$("#chapter").val();
	var course=$("#course option:selected").val();
	var thumb=$("#thumb").val();
	var video=$("#video").val();
	if(videoName.length<1||chapter.length<1||course.length<1||thumb.length<1||video.length<1)
	{
		$.alert('表单填写不正确','提示');
		return false;
	}
	$.ajaxFileUpload({
		   url:"/admin/video/add?videoName="+videoName+"&chapter="+chapter+"&course="+course+"&thumb="+thumb+"&video="+video,
		   secureuri: false, //是否需要安全协议，一般设置为false
           fileElementId: ['thumb','video'], //文件上传域的ID
		   dataType:'JSON',
		   success: function(data)
		   {
			   getPage1('/admin/video/list');
		   }
	   });
}


function uploadFile()
{
	$.ajaxFileUpload({
	   url:"/test/add?name=0",
	   secureuri: false, //是否需要安全协议，一般设置为false
       fileElementId: 'file5', //文件上传域的ID
	   dataType:'JSON',
	   success: function(data)
	   {
		   alert(data);
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



