// 注册
function regUser()
{
	var email=$("input[name='email']").val();
	var password=$("input[name='password']").val();
	if(email.length<4||password<1){
		alert("邮箱或密码不能为空");
		return false;
	}
	if(email.indexOf("@")<0||email.indexOf(".com")<0){
		alert("邮箱格式不正确！");
		return false;
	}
	if($("#password1").val()!=password){
		alert("两次输入的密码不一致！");
		return false;
	}
	$.ajax({
		type:"post",
		url:"/edu/home/register",
        data:{
        	email:email,
        	password:password
        },
	    dataType:"json",
        success:function(data)
        {
        	if(data.success)
       		{
        		if(data.isEmailExist==true){
        			alert("邮箱已经被注册");
        		}
        		else{
        			if(data.emailSuccecc==false){
            			alert("邮件发送失败");
            		}else{
                		alert("请激活登陆");
            		}
        		}
        		
       		}
        	else
       		{
       			alert("处理请求错误");
       		}
        }
	})
}


// 点击登陆发生的事件
function submitUser()
{
	var nickName=$("input[name='nickname']").val();
	var password=$("input[name='loginpassword']").val();
	if(nickName.length<1||password<1){
		alter("账号或密码不能为空");
		return false;
	}
	$.ajax({
		type:"post",
		url:"/edu/home/login",
        data:{
        	nickName:nickName,
        	password:password
        },
	    dataType:"json",
        success:function(data)
        {
        	if(data.success==1)
       		{
        		window.location.href ="/edu/home/index";
       		}
        	if(data.success==0)
       		{
       			alert("用户名或密码错误");
       		}
        	if(data.success==2)
       		{
       			alert("账号不存在！");
       		}
        }
	})
}

