//点击收藏
function collectCourse(){
	$.ajax({
		url:"/edu/home/collectCourse/data",
		data:{"courseId":$("#courseNum").val()},
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.collection){
				$("#collection").removeClass('glyphicon-heart-empty');
				$("#collection").addClass('glyphicon-heart');
			}
			else{
				$("#collection").removeClass('glyphicon-heart');
				$("#collection").addClass('glyphicon-heart-empty');
			}
		}
	});
}

function getUseComment()
{
	//加载相关的用户评论信息
	$.ajax({
		url:"/edu/home/detail/comment/list",
		data:{"videoId":$("#videoId").val()},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.success==true){
				$.each(data.comList,function(index,ls){
					$('#commentList').append(
						'<div class="comment-list clearfix" >'+
							'<div class="list-pic">'+
								'<a href="">'+
									'<img src="../img/teacher.PNG">'+
								'</a>'+
							'</div>'+
							'<div class="list-content">'+
								'<div class="list-content-txt">'+
									"<a href='' title='子世无双' alt='子世无双'>"+ls.nickName+"</a>"+
									"<span class=''>："+ls.content+"</span>"+
								'</div>'+
								'<div class="list-content-action">'+
									'<span class="list-content-action-time">'+
										"<a href=''>"+ls.comTime+"</a>"+
									'</span>'+
									'<span class="list-content-action-more pull-right">'+
										'<a href="" data="">转发</a>'+
										'<span class="divider">|</span>'+
										'<a href="" data="">评论</a>'+
									'</span>'+
								'</div>'+
							'</div>'+
						'</div>'
					);
				})
			}
			else{
				$.alert("处理请求出错","提示");
			}
		 }
		})
}

//点击点赞发生的事件
function addVideoScore(){
	$.ajax({
		url:"/edu/home/detail/add/videoScore",
		data:{"videoId":$("#videoId").val()},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.success==true)
			{
				$("#dyixia").css("color","red");
			}
		}
	});
}
$(function(){
	//加载视频相关的信息
	$.ajax({
		url:"/edu/home/detail/data",
		data:{"courseId":$("#courseNum").val(),"videoId":$("#videoId").val()},
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
				//添加视频的集数
				$("#videoSize").empty();
				$("#videoSize").html("共 "+data.videoSize+" 集");
				
				$("#courName").empty();
				$("#courName").html(data.courName);
				//加载讲师介绍的部分
				var str=
				'<div class="row">'+
					'<div class="teacher-intr">'+
						'<div class="row-brand">讲师介绍</div>'+
						'<img src="../img/teacher.PNG" class="img-circle">'+
						'<ul class="teach-school">'+
							"<li>讲师：<span class='teacher-name'>"+data.teacherName+"</span></li>"+
							"<li>学校：<a href='#' class='teacher-sch'>"+data.school+"</a></li>"+
						'</ul>'+
						"<p class='intro'>"+data.teacherIntro+"</p>"+
					'</div>'+
				'</div>'+
				'<hr>'+											
				'<div class="row">'+
					'<div class="course-intr">'+
						'<div class="row-brand">课程介绍 '+
					     '</div>'+
						"<p class='intro'>"+data.teacherIntro+"</p>"+
					'</div>'+
					'<div class="click-collect">';
				if(data.dyx==true){
					   str+='<span id="dyixia" style="color:red;" disabled="disabled" class="glyphicon glyphicon-thumbs-up" onclick="addVideoScore()"></span>点赞';
				}else{
					   str+='<span id="dyixia" class="glyphicon glyphicon-thumbs-up" onclick="addVideoScore()"></span>点赞';
				}
				if(data.collection==true){
					str+='<span id="collection" class="glyphicon glyphicon-heart" onclick="collectCourse()"></span>收藏课程';
				}else{
					str+='<span id="collection" class="glyphicon glyphicon-heart-empty" onclick="collectCourse()"></span>收藏课程';
				}
				str+='</div>';
		str+='</div>';	
				
				$("#teacherSchoolIntro").append(
					str
						);
				
				//添加视频列表页面
				$.each(data.videoList,function(index,ls){
					   //stoneHeader.append("<option value='"+user.id+"'>"+user.username+" </option>");
					$("#videoList").append(
							'<li>'+
							  "<a href='/edu/home/detail/"+ls.courseId+"?videoId="+ls.id+"'>"+
									'<ul class="course-list-single">'+
										"<li><img src=/edu/home/getVideoImage?imgname="+ls.thumb+" class='img-thumbnail'></li>"+
										"<li><span class='title'>第<span>"+ls.chapter+"</span>集</span></li>"+
									'</ul>'+
								'</a>'+
							'</li>'
					);});
				
			}
		}
	});
	
	getUseComment();
	
})

//点击发表评论发生的事件
function subComment()
{
	var comment=$("#textarea").val();
	$.ajax({
		url:"/edu/home/detail/comment/add",
		data:{"videoId":$("#videoId").val(),"content":comment},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.success==true)
			{
				$("#textarea").val("");
				$('#commentList').empty();
				getUseComment();
			}
			else{
				$.alert("处理请求出错","提示");
			}
		}
		})
}
