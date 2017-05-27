//检查视频缩略图的图片格式
function checkImgForm(){
	var thumb=$.trim($("#thumb").val());
    var jpg = thumb.indexOf(".jpg");
    var png = thumb.indexOf(".png");
	if(jpg<0&&png<0){
		$("#thumb").css("border-color","red");
		$.alert("只能上传png,jpg格式的文件","确定");
		return false;
	}
	$("#thumb").css("border-color","");
	return true;
}

//检查视频文件格式
function  checkVideoForm(){
	var thumb=$.trim($("#video").val());
    var flv = thumb.indexOf(".flv");
    var mp4 = thumb.indexOf(".mp4");
	if(flv<0&&mp4<0){
		$("#video").css("border-color","red");
		$.alert("只能上传flv,mp4格式的文件","确定");
		return false;
	}
	$("#video").css("border-color","");
	return true;
}

//添加视频
function SubmitVideo()
{
	//checkImgForm()
	var thumb=$.trim($("#thumb").val());
    var jpg = thumb.indexOf(".jpg");
    var png = thumb.indexOf(".png");
	if(jpg<0&&png<0){
		$("#thumb").css("border-color","red");
		$.alert("只能上传png,jpg格式的文件","确定");
		return false;
	}
	$("#thumb").css("border-color","");
	
	//checkVideoForm();
	var thumb=$.trim($("#video").val());
    var flv = thumb.indexOf(".flv");
    var mp4 = thumb.indexOf(".mp4");
	if(flv<0&&mp4<0){
		$("#video").css("border-color","red");
		$.alert("只能上传flv,mp4格式的文件","确定");
		return false;
	}
	$("#video").css("border-color","");
	
	var videoName=$.trim($("#videoName").val());
	var chapter=$.trim($("#chapter").val());
	var course=$.trim($("#course option:selected").val());
	var thumb=$.trim($("#thumb").val());
	var video=$.trim($("#video").val());
	if(videoName.length<1||chapter.length<1||course.length<1||thumb.length<1||video.length<1)
	{
		$.alert('表单填写不能为空','提示');
		return false;
	}
	$.ajaxFileUpload({
		   url:"/edu/admin/video/add?videoName="+videoName+"&chapter="+chapter+"&course="+course+"&thumb="+thumb+"&video="+video,
		   secureuri: false, //是否需要安全协议，一般设置为false
           fileElementId: ['thumb','video'], //文件上传域的ID
		   dataType:'JSON',
		   success: function(data)
		   {
			   getPage1('/edu/admin/video/list');
		   }
	   });
}



