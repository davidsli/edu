//点击更新密码发生的事件
function updatePassword(){
	if($("#oldpsw").val()!=$("#oldpsw1").val()){
		alert("新旧密码不一致");
		return false;
	}
	
	if($("#newpsw2").val()!=$("#confirmpsw").val()){
		alert("两次密码不一致");
		return false;
	}
	$.ajax({
		url:"/edu/home/updatePassword/data",
		data:{"newpsw":$("#newpsw2").val()},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.success){
				$("#modifyPsw").modal('hide');
			}
		}
	});
}

//点击历史页码
function getHisPage(pageNo){
	$.ajax({
		url:"/edu/home/courseManage/data",
		data:{"pageNo":pageNo},
		type:"post",
		dataType:"json",
		success:function(data){
			$("#readCourse").empty();
			var str="";
			$.each(data.hisList,function(index,ls){
				str+='<div class="item">'+
						'<div class="item-div clearfix">'+
							"<div class='collect-time'>收藏时间：</div>"+
							"<div class='video-pic'><a href=/edu/home/detail/"+ls.courId+" target='_blank'><img src="+ls.imgUrl+" class='img-responsive'></a></div>"+
							'<div class="video-txt">'+
								"<p class='video-tit'><a href=/edu/home/detail/"+ls.courId+" target='_blank'>"+ls.courName+"</a></p>"+
								"<p class='video-type'>讲师：<a href='#'>"+ls.teacherName+"</a>学校：<a href='#'>"+ls.school+"</a></p>"+
								"<p class='intro' title=''>"+ls.courIntro+"</p>"+
							'</div>'+
						'</div>'+
					'</div>';
					});
						
				str+='<ul class="pagination pull-right">'+
					    '<li><a href="#">&laquo;</a></li>';
					    for(var i= 1;i<=data.hisPageCount;i++){
				        	str+="<li><a style='cursor: pointer;' onclick='getHisPage("+i+")'>"+i+"</a></li>";
				        }
				  str+=
					    '<li><a href="#">&raquo;</a></li>'+
					'</ul>';
				$("#readCourse").append(str);
		}
	});
}

//点击课程管理发生的事件
function getCollPage(pageNo){
	$.ajax({
		url:"/edu/home/courseManage/data",
		data:{"pageNo":pageNo},
		type:"post",
		dataType:"json",
		success:function(data){
		$("#myTabContent").empty();
			var str='<div class="tab-pane fade in active" id="courseMessage">'+
				'<ul class="nav nav-tabs">'+
					'<li class="active"><a href="#collectCourse" data-toggle="tab">收藏课程</a></li>'+
					'<li><a href="#readCourse" data-toggle="tab">已浏览课程</a></li>'+
				'</ul>'+
				'<div class="tab-content">'+
					'<div class="tab-pane fade in active" id="collectCourse">';
						$.each(data.collList,function(index,ls){
							str+='<div class="item">'+
										'<div class="item-div clearfix">'+
										"<div class='collect-time'>收藏时间："+ls.collTime+"</div>"+
										"<div class='video-pic'><a href=/edu/home/detail/"+ls.courId+" target='_blank'><img src="+ls.imgUrl+" class='img-responsive'></a></div>"+
										'<div class="video-txt">'+
											"<p class='video-tit'><a href='#' target='_blank'>"+ls.courName+"</a></p>"+
											"<p class='video-type'>讲师：<a href='#'>"+ls.teacherName+"</a>学校：<a href='#'>"+ls.school+"</a></p>"+
											"<p class='intro' title=''>"+ls.courIntro+"</p>"+
										'</div>'+
									'</div>'+
									
								'</div>'
						});
						
					str+='<ul class="pagination pull-right">'+
						    '<li><a href="#">&laquo;</a></li>';
					        for(var i= 1;i<=data.pageCount;i++){
					        	str+="<li><a style='cursor: pointer;' onclick='getCollPage("+i+")'>"+i+"</a></li>";
					        }
					str+=
						    '<li><a href="#">&raquo;</a></li>'+
						'</ul>'+
					'</div>'+
					'<div class="tab-pane fade" id="readCourse">';
						$.each(data.hisList,function(index,ls){
					str+='<div class="item">'+
							'<div class="item-div clearfix">'+
								"<div class='collect-time'>收藏时间：</div>"+
								"<div class='video-pic'><a href=/edu/home/detail/"+ls.courId+" target='_blank'><img src="+ls.imgUrl+" class='img-responsive'></a></div>"+
								'<div class="video-txt">'+
									"<p class='video-tit'><a href='#' target='_blank'>"+ls.courName+"</a></p>"+
									"<p class='video-type'>讲师：<a href='#'>"+ls.teacherName+"</a>学校：<a href='#'>"+ls.school+"</a></p>"+
									"<p class='intro' title=''>"+ls.courIntro+"</p>"+
								'</div>'+
							'</div>'+
						'</div>';
						});
							
					str+='<ul class="pagination pull-right">'+
						    '<li><a href="#">&laquo;</a></li>';
						    for(var i= 1;i<=data.hisPageCount;i++){
					        	str+="<li><a style='cursor: pointer;' onclick='getHisPage("+i+")'>"+i+"</a></li>";
					        }
					  str+=
						    '<li><a href="#">&raquo;</a></li>'+
						'</ul>'+
					'</div>'+
				'</div>'+
			'</div>';
			$("#myTabContent").append(str);
		}
	});
}

//点击账号绑定发生的事件
function accountBind(){
	
	$.ajax({
		url:"/edu/home/account/data",
		type:"post",
		dataType:"json",
		success:function(data){
		$("#oldpsw1").val(data.userPassword);
		$("#myTabContent").empty();
		     var str=
		    '<div class="tab-pane fade in active" id="IdAction">'+
		 		'<div class="smailTitle">'+
		 				'<span>账号绑定</span>'+
		 			'</div>'+
		 			'<div class="actionItem clearfix">'+
		 				'<div class="pull-left icons"><span class="glyphicon glyphicon-envelope icon"></span></div>'+
		 				'<div class="Cnt pull-left">'+
		 					"<p>邮箱<span class='userEmail'>"+data.email+"</span><span class='isBind'>已绑定</span></p>"+
		 					'<p class="ps">可用邮箱加密码登录慕课网，可用邮箱找回密码</p>'+
		 				'</div>'+
		 				'<button disabled="disabled" class="btn btn-default pull-right actionBtn" type="button" value="modify" data-toggle="modal" data-target="#modifyEmail">更改</button>'+
		 			'</div>'+
		 			'<div class="actionItem clearfix">'+
		 				'<div class="pull-left icons"><span class="glyphicon glyphicon-phone icon"></span></div>'+
		 				'<div class="Cnt pull-left">';
		                   if(data.phoneNumber.length>0){
		                	   str+="<p>手机("+data.phoneNumber+")<span class='userEmail'></span><span class='isBind'>已绑定</span></p>";
		                	 }
		                   else{
		                	   str+="<p>没有绑定手机号<span class='userEmail'></span><span class='isBind'></span></p>";
		                   }
		 					str+='<p class="ps">可用手机号加密码登录慕课网，可通过手机号找回密码</p>'+
		 				'</div>'+
		 				'<button disabled="disabled" class="btn btn-default pull-right actionBtn" type="button" value="modify" data-toggle="modal" data-target="#bindPhone">立即绑定</button>'+
		 			'</div>'+
		 			'<div class="actionItem clearfix">'+
		 				'<div class="pull-left icons"><span class="glyphicon glyphicon-lock icon"></span></div>'+
		 				'<div class="Cnt pull-left">'+
		 					'<p>密码<span class="isBind">已设置</span></p>'+
		 					'<p class="ps">用于保护账号信息和登录安全</p>'+
		 				'</div>'+
		 				'<button class="btn btn-default pull-right actionBtn" type="button" value="modify" data-toggle="modal" data-target="#modifyPsw">修改</button>'+
		 			'</div>'+
		 	'</div>';
		     $("#myTabContent").append(
		    			str
		 	);
		     
		}
	});
	
}


//点击课程管理发生的事件
function courseManage(){
	$.ajax({
		url:"/edu/home/courseManage/data",
		type:"post",
		dataType:"json",
		success:function(data){
		$("#myTabContent").empty();
			var str='<div class="tab-pane fade in active" id="courseMessage">'+
				'<ul class="nav nav-tabs">'+
					'<li class="active"><a href="#collectCourse" data-toggle="tab">收藏课程</a></li>'+
					'<li><a href="#readCourse" data-toggle="tab">已浏览课程</a></li>'+
				'</ul>'+
				'<div class="tab-content">'+
					'<div class="tab-pane fade in active" id="collectCourse">';
						$.each(data.collList,function(index,ls){
							str+='<div class="item">'+
										'<div class="item-div clearfix">'+
										"<div class='collect-time'>收藏时间："+ls.collTime+"</div>"+
										"<div class='video-pic'><a href=/edu/home/detail/"+ls.courId+" target='_blank'><img src="+ls.imgUrl+" class='img-responsive'></a></div>"+
										'<div class="video-txt">'+
											"<p class='video-tit'><a href='#' target='_blank'>"+ls.courName+"</a></p>"+
											"<p class='video-type'>讲师：<a href='#'>"+ls.teacherName+"</a>学校：<a href='#'>"+ls.school+"</a></p>"+
											"<p class='intro' title=''>"+ls.courIntro+"</p>"+
										'</div>'+
									'</div>'+
									
								'</div>'
						});
						
					str+='<ul class="pagination pull-right">'+
						    '<li><a href="#">&laquo;</a></li>';
					        for(var i= 1;i<=data.pageCount;i++){
					        	str+="<li><a style='cursor: pointer;' onclick='getCollPage("+i+")'>"+i+"</a></li>";
					        }
					str+=
						    '<li><a href="#">&raquo;</a></li>'+
						'</ul>'+
					'</div>'+
					'<div class="tab-pane fade" id="readCourse">';
					$.each(data.hisList,function(index,ls){
						str+='<div class="item">'+
									'<div class="item-div clearfix">'+
									"<div class='collect-time'>收藏时间：</div>"+
									"<div class='video-pic'><a href=/edu/home/detail/"+ls.courId+" target='_blank'><img src="+ls.imgUrl+" class='img-responsive'></a></div>"+
									'<div class="video-txt">'+
										"<p class='video-tit'><a href='#' target='_blank'>"+ls.courName+"</a></p>"+
										"<p class='video-type'>讲师：<a href='#'>"+ls.teacherName+"</a>学校：<a href='#'>"+ls.school+"</a></p>"+
										"<p class='intro' title=''>"+ls.courIntro+"</p>"+
									'</div>'+
								'</div>'+
								
							'</div>'
					});
					
				str+='<ul class="pagination pull-right">'+
					    '<li><a href="#">&laquo;</a></li>';
				        for(var i= 1;i<=data.hisPageCount;i++){
				        	str+="<li><a style='cursor: pointer;' onclick='getHisPage("+i+")'>"+i+"</a></li>";
				        }
				str+=
					    '<li><a href="#">&raquo;</a></li>'+
					'</ul>'+
				  '</div>'+
				'</div>'+
			'</div>';
			$("#myTabContent").append(str);
		}
	});
}

//点击个人信息发生的事件
function personalInfo(){
	$.ajax({
		url:"/edu/home/personalInfo/data",
		type:"post",
		dataType:"json",
		success:function(data){
			$("#myTabContent").empty();
			$("#myTabContent").append(
					'<div class="tab-pane fade in active">'+
			     		'<div class="smailTitle">'+
			     			'<span>个人信息</span>'+
			     			'<a href="#" target="_blank" class="pull-right" data-toggle="modal" data-target="#editUserMsg">'+
			     				'<span class="glyphicon glyphicon-pencil"></span>编辑'+
			     			'</a>'+
			     		'</div>	'+
			     			
			     		'<div class="userContent">'+
			     			'<div class="userItem clearfix">'+
			     				'<div class="userItemTitle pull-left">邮箱</div>'+
			     				"<div class='userItemCnt pull-right'>"+data.email+"</div>"+
			     			'</div>'+
			     			'<div class="userItem clearfix">'+
			     				'<div class="userItemTitle pull-left">昵称</div>'+
			     				"<div class='userItemCnt pull-right'>"+data.nickName+"</div>"+
			     			'</div>'+
			     			'<div class="userItem clearfix">'+
			     				'<div class="userItemTitle pull-left">性别</div>'+
			     				"<div class='userItemCnt pull-right'>"+data.sex+"</div>"+
			     			'</div>'+
			     			'<div class="userItem clearfix">'+
			     				'<div class="userItemTitle pull-left">电话</div>'+
			     				"<div class='userItemCnt pull-right'>"+data.phoneNumber+"</div>"+
			     			'</div>'+
			     			'<div class="userItem clearfix">'+
			     				'<div class="userItemTitle pull-left">个性签名</div>'+
			     				"<div class='userItemCnt pull-right'>"+data.personalIntro+"</div>"+
			     			'</div>'+
			     		'</div>'+
			    	'</div>'
						);
		}
	});
		
}

//初始化加载的界面
$(function(){
	personalInfo();
})


