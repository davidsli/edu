
$(function(){
	//局部加载用户的评论页面
	$("#contentWap").load("/home/detail/comment/index");
	$.ajax({
		url:"/home/detail/data",
		data:{"courseId":$("#courseNum").val()},
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
				$("#teacherSchoolIntro").append(
						'<div class="pictxt clearfix">'+
						'<div class="pic"></div>'+
						'<div class="txt fblue">'+
							"讲师："+data.teacherName+"<br> 学校：<a href='http://open.sina.com.cn/school/id_51/' target='_top'>"+data.school+"</a>"+
						'</div>'+
					'</div>'+
					"<p class='intro' title='巴黎高等商学院经济与决策学兼职教授'>"+data.teacherIntro+"</p>"
						);
				
				$("#teacherSchool").append(
						'<div class="pictxt clearfix" >'+
						'<div class="pic"></div>'+
						'<div class="txt fblue">'+
							"讲师："+data.teacherName+"<br> 学校："+data.school+"<a href='http://open.sina.com.cn/school/id_51/' target='_top'>巴黎高等商学院</a>"+
						'</div>'+
					'</div>'+
					"<p class='intro' title='巴黎高等商学院经济与决策学兼职教授'>"+data.teacherIntro+"</p>"		
				);
				
				$("#courseIntro").html(
						data.courIntro		
				);
				//添加视频列表页面
				$.each(data.videoList,function(index,ls){
					   //stoneHeader.append("<option value='"+user.id+"'>"+user.username+" </option>");
					$("#videoList").append(
							"<li> <a href='/home/detail/"+ls.courseId+"?videoId="+ls.id+"'>"+ 
							  "<img title='信任区间的修正练习' alt='信任区间的修正练习' src=/home/getVideoImage?imgname="+ls.thumb+" width='132' height='100'>"+
							  "<span title='信任区间的修正练习'>第"+ls.chapter+"集</span>"+
							'</a></li>'
					);});
				
			}
		}
	});
})
