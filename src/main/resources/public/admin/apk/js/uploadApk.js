//检查apk文件格式
//function checkApkForm(){
//	var apkFile=$.trim($("#apkFile").val());
//    var apk = apkFile.indexOf(".apk");
//	if(apk<0){
//		$("#apkFile").css("border-color","red");
//		$.alert("上传的不是apk文件","确定");
//		return false;
//	}
//	$("#apkFile").css("border-color","");
//	return true;
//}

/**
 * 上传apk文件
 */
function checkApkForm()
{
//	checkImgForm()
	var apk=$.trim($("#apkFile").val());
    var apkindex = apk.indexOf(".apk");
    if(apkindex<0){
    	$("#apkFile").css("border-color","red");
    	$.alert('上传的apk不对！','提示');
		return false;
    }
    $("#apkFile").css("border-color","");
    var newVersionName=$.trim($("#newVersionName").val());
    var newVersionCode=$.trim($("#newVersionCode").val());
    var updateDesc=$.trim($("#updateDesc").val());
    var isForced=$("#isForced");
    var isforce="false";
   
    if(newVersionName.length<1||newVersionCode.length<1||updateDesc.length<1)
	{
		$.alert('表单不能为空！！','提示');
		return false;
	}
    return true;
    //?newVersionName="+newVersionName+"&newVersionCode="+newVersionCode+"&updateDesc="+updateDesc+"&isforce="+isforce"
//    $.ajaxFileUpload({
//    	url:"/edu/admin/apk/add",
//		secureuri: false, //是否需要安全协议，一般设置为false
//        fileElementId: 'apkFile', //文件上传域的ID
//		dataType:'JSON',
//		success: function(data)
//		{
//			 if(data.success )
//			 {
//				getPage1('/edu/admin/course/list');
//			 }
//		}
//	   });
    
}


