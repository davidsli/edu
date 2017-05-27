// 忘记密码
function submitEmail()
{
	var forEmail=$("#forEmail").val();
	if(forEmail.indexOf("@")<0||forEmail.indexOf(".com")<0){
		alert("邮箱格式不正确！");
		return false;
	}
	$.ajax({
		type:"post",
		url:"/edu/home/forgetpsw",
        data:{
        	email:forEmail
        },
	    dataType:"json",
        success:function(data)
        {
    		if(data.success==true){
    			alert("请登录邮箱完成密码重置！");
    			window.location.href="/web/home/loginUI"
    		}
    		else{
    			alert("邮箱号不存在");
    		}
        		
        }
	})
}

