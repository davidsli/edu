//点击查看更多进来看见的
$(function(){
	
	$.ajax({
		url:"/edu/home/total/data",
		data:{"subjectId":$("#subjectId").val()},
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
				$("#courseList").empty();
				$("#subName").empty();
				$("#pageSum").empty();
				
				$("#subjectId").val(data.subId);
				$("#subName").html(
						data.subName		
				);
				//添加视频列表页面
				$.each(data.courList,function(index,ls){
					   //stoneHeader.append("<option value='"+user.id+"'>"+user.username+" </option>");
					$("#courseList").append(
							'<div class="video-div clearfix">'+
							'<div class="video-pic">'+
								"<a href=/edu/home/detail/"+ls.id+" target='_blank'><img src="+ls.courImg+" class='img-responsive'></a>"+
							'</div>'+
							'<div class="video-txt">'+
								'<p class="video-tit">'+
									"<a href='#' target='_blank'>"+ls.courNmae+"</a>"+
								'</p>'+
								"<p class='video-type'>讲师："+ls.teacherName+""+
								"学校："+ls.school+"</p>"+
								"<p class='intro' title=''>"+ls.courIntro+"</p>"+
							'</div>'+
						'</div>'
					);});
				
				$("#pageSum").append(
						'<li><a href="#">&laquo;</a></li>'
						);
				for(var i=1;i<=data.totalPageCount;i++){
					$("#pageSum").append(
							"<li><a style='cursor: pointer;' onclick='getPage("+i+")'>"+i+"</a></li>"
							);
				}
				$("#pageSum").append(
						'<li><a href="#">&raquo;</a></li>'
						);
				
			}
		}
	});
})

//点击上一页下一页出发的事件
function getPage(pageNo){
	var searchContent=$("#searchContent").val();
	if(searchContent.length>0){//搜索框里面有数据
		$.ajax({
			url:"/edu/home/total/search",
			data:{"serarchContent":$("#searchContent").val(),"pageNo":pageNo},
			type:"post",
			dataType:"json",
			success:function(data)
			{
				if(data.success==true)
				{
					$("#courseList").empty();
					$("#subName").empty();
					$("#pageSum").empty();
					
//					$("#subjectId").val(data.subId);
//					$("#subName").html(
//							data.subName		
//					);
					//添加视频列表页面
					$.each(data.courList,function(index,ls){
						   //stoneHeader.append("<option value='"+user.id+"'>"+user.username+" </option>");
						$("#courseList").append(
								'<div class="video-div clearfix">'+
								'<div class="video-pic">'+
									"<a href=/edu/home/detail/"+ls.id+" target='_blank'><img src="+ls.courImg+" class='img-responsive'></a>"+
								'</div>'+
								'<div class="video-txt">'+
									'<p class="video-tit">'+
										"<a href='#' target='_blank'>"+ls.courNmae+"</a>"+
									'</p>'+
									"<p class='video-type'>讲师："+ls.teacherName+""+
									"学校："+ls.school+"</p>"+
									"<p class='intro' title=''>"+ls.courIntro+"</p>"+
								'</div>'+
							'</div>'
						);});
					
					$("#pageSum").append(
							'<li><a href="#">&laquo;</a></li>'
							);
					for(var i=1;i<=data.totalPageCount;i++){
						$("#pageSum").append(
								"<li><a style='cursor: pointer;' onclick='getPage("+i+")'>"+i+"</a></li>"
								);
					}
					$("#pageSum").append(
							'<li><a href="#">&raquo;</a></li>'
							);
					
				}
			}
		});
	}else{//搜索框里面没有数据
		$("#searchContent").empty();
		$.ajax({
			url:"/edu/home/total/data",
			data:{"subjectId":$("#subjectId").val(),"pageNo":pageNo},
			type:"post",
			dataType:"json",
			success:function(data)
			{
				if(data.success==true)
				{
					$("#courseList").empty();
					$("#subName").empty();
					$("#pageSum").empty();
					
					$("#subjectId").val(data.subId);
					$("#subName").html(
							data.subName		
					);
					//添加视频列表页面
					$.each(data.courList,function(index,ls){
						   //stoneHeader.append("<option value='"+user.id+"'>"+user.username+" </option>");
						$("#courseList").append(
								'<div class="video-div clearfix">'+
								'<div class="video-pic">'+
									"<a href=/edu/home/detail/"+ls.id+" target='_blank'><img src="+ls.courImg+" class='img-responsive'></a>"+
								'</div>'+
								'<div class="video-txt">'+
									'<p class="video-tit">'+
										"<a href='#' target='_blank'>"+ls.courNmae+"</a>"+
									'</p>'+
									"<p class='video-type'>讲师："+ls.teacherName+""+
									"学校："+ls.school+"</p>"+
									"<p class='intro' title=''>"+ls.courIntro+"</p>"+
								'</div>'+
							'</div>'
						);});
					
					$("#pageSum").append(
							'<li><a href="#">&laquo;</a></li>'
							);
					for(var i=1;i<=data.totalPageCount;i++){
						$("#pageSum").append(
								"<li><a style='cursor: pointer;' onclick='getPage("+i+")'>"+i+"</a></li>"
								);
					}
					$("#pageSum").append(
							'<li><a href="#">&raquo;</a></li>'
							);
					
				}
			}
		});
	}
    
}


//点击相关学科发生的事件
function getSubject(subId){
	$("#searchContent").val("");
	$.ajax({
		url:"/edu/home/total/data",
		data:{"subjectId":subId},
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
				$("#courseList").empty();
				$("#subName").empty();
				$("#pageSum").empty();
				
				$("#subjectId").val(data.subId);
				$("#subName").html(
						data.subName		
				);
				//添加视频列表页面
				$.each(data.courList,function(index,ls){
					   //stoneHeader.append("<option value='"+user.id+"'>"+user.username+" </option>");
					$("#courseList").append(
							'<div class="video-div clearfix">'+
							'<div class="video-pic">'+
								"<a href=/edu/home/detail/"+ls.id+" target='_blank'><img src="+ls.courImg+" class='img-responsive'></a>"+
							'</div>'+
							'<div class="video-txt">'+
								'<p class="video-tit">'+
									"<a href='#' target='_blank'>"+ls.courNmae+"</a>"+
								'</p>'+
								"<p class='video-type'>讲师："+ls.teacherName+""+
								"学校："+ls.school+"</p>"+
								"<p class='intro' title=''>"+ls.courIntro+"</p>"+
							'</div>'+
						'</div>'
					);});
				
				$("#pageSum").append(
						'<li><a href="#">&laquo;</a></li>'
						);
				for(var i=1;i<=data.totalPageCount;i++){
					$("#pageSum").append(
							"<li><a style='cursor: pointer;' onclick='getPage("+i+")'>"+i+"</a></li>"
							);
				}
				$("#pageSum").append(
						'<li><a href="#">&raquo;</a></li>'
						);
				
			}
		}
	});
}

//点击搜索发生的事件
function searchCour(){
	$.ajax({
		url:"/edu/home/total/search",
		data:{"serarchContent":$("#searchContent").val()},
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
				$("#courseList").empty();
				$("#subName").empty();
				$("#pageSum").empty();
				
//				$("#subjectId").val(data.subId);
//				$("#subName").html(
//						data.subName		
//				);
				//添加视频列表页面
				$.each(data.courList,function(index,ls){
					   //stoneHeader.append("<option value='"+user.id+"'>"+user.username+" </option>");
					$("#courseList").append(
							'<div class="video-div clearfix">'+
							'<div class="video-pic">'+
								"<a href=/edu/home/detail/"+ls.id+" target='_blank'><img src="+ls.courImg+" class='img-responsive'></a>"+
							'</div>'+
							'<div class="video-txt">'+
								'<p class="video-tit">'+
									"<a href='#' target='_blank'>"+ls.courNmae+"</a>"+
								'</p>'+
								"<p class='video-type'>讲师："+ls.teacherName+""+
								"学校："+ls.school+"</p>"+
								"<p class='intro' title=''>"+ls.courIntro+"</p>"+
							'</div>'+
						'</div>'
					);});
				
				$("#pageSum").append(
						'<li><a href="#">&laquo;</a></li>'
						);
				for(var i=1;i<=data.totalPageCount;i++){
					$("#pageSum").append(
							"<li><a style='cursor: pointer;' onclick='getPage("+i+")'>"+i+"</a></li>"
							);
				}
				$("#pageSum").append(
						'<li><a href="#">&raquo;</a></li>'
						);
				
			}
		}
	});
}
