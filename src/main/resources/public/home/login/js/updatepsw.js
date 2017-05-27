// 更改密码
function submitPsw()
{
	var userId=$("#userId").val();
	var password1=$("#password1").val();
	var password2=$("#password2").val();
	if(password1!=password2){
		alert("两次输入的密码不一致！！");
		return false;
	}
	$.ajax({
		type:"post",
		url:"/edu/home/setpsw",
        data:{
        	userId:userId,
        	password:password1
        },
	    dataType:"json",
        success:function(data)
        {
    		if(data.success==true){
    			alert("更新密码成功！");
    			window.location.href="/edu/home/loginUI";
    		}
    		else{
    			alert("更新密码失败！");
    		}
        }
	})
}

