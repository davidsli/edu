//获取计算机的视频列表
function getComputerList() 
{
	$.ajax({
		url:"/course/getComputerList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
			   $.each(data.compList,function(index,ls){
				   $("#compList").append(
				'<div class="vido">'+
					'<div class="pic">'+
						"<a target='_blank' href=/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
					'</div>'+
					'<p class="tit fbluel">'+
						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
					'</p>'+
					'<p class="intro">'+
						"人气：<i>"+ls.score+"</i>"+
					'</p>'+
					"<p class='intro'>共"+ls.episodes+"集</p>"+
				'</div>')})
			}
			else
			{
				$.alert(data.msg,"提示");
			}
		}
	})	
}

//获取工科相关的视频列表
function getEngList() 
{
	$.ajax({
		url:"/course/getEngList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
			   $.each(data.engList,function(index,ls){
				   $("#engList").append(
				'<div class="vido">'+
					'<div class="pic">'+
						"<a target='_blank' href=/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
					'</div>'+
					'<p class="tit fbluel">'+
						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
					'</p>'+
					'<p class="intro">'+
						"人气：<i>"+ls.score+"</i>"+
					'</p>'+
					"<p class='intro'>共"+ls.episodes+"集</p>"+
				'</div>')})
			}
			else
			{
				$.alert(data.msg,"提示");
			}
		}
	})	
}

//获取经济相关的视频文件信息
function getEcoList() 
{
	$.ajax({
		url:"/course/getEcoList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
			   $.each(data.ecoList,function(index,ls){
				   $("#ecoList").append(
				'<div class="vido">'+
					'<div class="pic">'+
						"<a target='_blank' href=/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
					'</div>'+
					'<p class="tit fbluel">'+
						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
					'</p>'+
					'<p class="intro">'+
						"人气：<i>"+ls.score+"</i>"+
					'</p>'+
					"<p class='intro'>共"+ls.episodes+"集</p>"+
				'</div>')})
			}
			else
			{
				$.alert(data.msg,"提示");
			}
		}
	})	
}

//人文
function getHumList() 
{
	$.ajax({
		url:"/course/getHumList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
			   $.each(data.humList,function(index,ls){
				   $("#humList").append(
				'<div class="vido">'+
					'<div class="pic">'+
						"<a target='_blank' href=/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
					'</div>'+
					'<p class="tit fbluel">'+
						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
					'</p>'+
					'<p class="intro">'+
						"人气：<i>"+ls.score+"</i>"+
					'</p>'+
					"<p class='intro'>共"+ls.episodes+"集</p>"+
				'</div>')})
			}
			else
			{
				$.alert(data.msg,"提示");
			}
		}
	})	
}


//哲学
function getPhiList() 
{
	$.ajax({
		url:"/course/getPhiList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
			   $.each(data.phiList,function(index,ls){
				   $("#phiList").append(
				'<div class="vido">'+
					'<div class="pic">'+
						"<a target='_blank' href=/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
					'</div>'+
					'<p class="tit fbluel">'+
						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
					'</p>'+
					'<p class="intro">'+
						"人气：<i>"+ls.score+"</i>"+
					'</p>'+
					"<p class='intro'>共"+ls.episodes+"集</p>"+
				'</div>')})
			}
			else
			{
				$.alert(data.msg,"提示");
			}
		}
	})	
}

//思政
function getIdeList() 
{
	$.ajax({
		url:"/course/getIdeList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
			   $.each(data.ideList,function(index,ls){
				   $("#ideList").append(
				'<div class="vido">'+
					'<div class="pic">'+
						"<a target='_blank' href=/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
					'</div>'+
					'<p class="tit fbluel">'+
						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
					'</p>'+
					'<p class="intro">'+
						"人气：<i>"+ls.score+"</i>"+
					'</p>'+
					"<p class='intro'>共"+ls.episodes+"集</p>"+
				'</div>')})
			}
			else
			{
				$.alert(data.msg,"提示");
			}
		}
	})	
}


$(document)
.ready(
		function() {
			getComputerList();
			getEngList();
			getEcoList();
			getHumList();
			getPhiList();
			getIdeList();
		}
)