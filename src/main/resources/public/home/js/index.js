//获取计算机的视频列表
function getComputerList() 
{
	$.ajax({
		url:"/edu/course/getComputerList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
			   var compList='';
			   var indexL=0;
			   $.each(data.compList,function(index,ls){
				   if(index==0){
					   compList+='<div class="module-brand">'+
		                            '<span>计算机</span>'+
		                            '<a href="/edu/home/total?subjectId=297e386a56410ddd0156411a94c50005" target="_blank" class="total pull-right">查看全部>></a>	'+
		                          '</div>'+
		                          '<div class="row">'
				   }
				   
				   if(index>=0&&index<=4){
					   compList+=
						   '<div class="col-sm-1-5">'+
			    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
			    				'<div class="col-img">'+
			    					"<img class='img-responsive' src="+ls.imgUrl+">"+
			    				'</div>'+
			    				'<div class="col-content">'+
			    				"<div class='col-title'>"+ls.courNmae+"</div>"+
			    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
			    					'<div class="course-sch">'+
			    						ls.school+
			    						'<div class="pull-right">'+
			    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
			    						'</div>'+
			    					'</div>'+
			    				'</div>'+
			    			'</a>'+
			    		'</div>';
				   }
//				   $("#compList").append(
//				'<div class="vido">'+
//					'<div class="pic">'+
//						"<a target='_blank' href=/edu/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
//					'</div>'+
//					'<p class="tit fbluel">'+
//						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
//					'</p>'+
//					'<p class="intro">'+
//						"人气：<i>"+ls.score+"</i>"+
//					'</p>'+
//					"<p class='intro'>共"+ls.episodes+"集</p>"+
//				'</div>')
				
				
				if(index==4){
					compList+='</div>';
				}
			   if(index==5){
					compList+='<div class="row">';
				}
			   if(index>4&&index<10){
				   compList+=
					   '<div class="col-sm-1-5">'+
		    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
		    				'<div class="col-img">'+
		    					"<img class='img-responsive' src="+ls.imgUrl+">"+
		    				'</div>'+
		    				'<div class="col-content">'+
		    				"<div class='col-title'>"+ls.courNmae+"</div>"+
		    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
		    					'<div class="course-sch">'+
		    					    ls.school+
		    						'<div class="pull-right">'+
		    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
		    						'</div>'+
		    					'</div>'+
		    				'</div>'+
		    			'</a>'+
		    		'</div>';
			   }
			   indexL=index;
			   })
			   if(indexL>4){
				   compList+='</div>';
			   }
			   $("#compList").append(compList);
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
		url:"/edu/course/getEngList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
				var engList='';
				   var indexL=0;
				   $.each(data.engList,function(index,ls){
					   if(index==0){
						   engList+='<div class="module-brand">'+
			                            '<span>工科</span>'+
			                            '<a href="/edu/home/total?subjectId=297e386a56410ddd0156411a94c50004" target="_blank" class="total pull-right">查看全部>></a>	'+
			                          '</div>'+
			                          '<div class="row">'
					   }
					   
					   if(index>=0&&index<=4){
						   engList+=
							   '<div class="col-sm-1-5">'+
				    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
				    				'<div class="col-img">'+
				    					"<img class='img-responsive' src="+ls.imgUrl+">"+
				    				'</div>'+
				    				'<div class="col-content">'+
				    					"<div class='col-title'>"+ls.courNmae+"</div>"+
				    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
				    					'<div class="course-sch">'+
				    					    ls.school+
				    						'<div class="pull-right">'+
				    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
				    						'</div>'+
				    					'</div>'+
				    				'</div>'+
				    			'</a>'+
				    		'</div>';
					   }
					
					if(index==4){
						engList+='</div>';
					}
				   if(index==5){
					   engList+='<div class="row">';
					}
				   if(index>4&&index<10){
					   engList+=
						   '<div class="col-sm-1-5">'+
			    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
			    				'<div class="col-img">'+
			    					"<img class='img-responsive' src="+ls.imgUrl+">"+
			    				'</div>'+
			    				'<div class="col-content">'+
			    				"<div class='col-title'>"+ls.courNmae+"</div>"+
			    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
			    					'<div class="course-sch">'+
			    					    ls.school+
			    						'<div class="pull-right">'+
			    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
			    						'</div>'+
			    					'</div>'+
			    				'</div>'+
			    			'</a>'+
			    		'</div>';
				   }
				   indexL=index;
				   })
				   if(indexL>4){
					   engList+='</div>';
				   }
				   $("#engList").append(engList);
				
//			   $.each(data.engList,function(index,ls){
//				   $("#engList").append(
//				'<div class="vido">'+
//					'<div class="pic">'+
//						"<a target='_blank' href=/edu/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
//					'</div>'+
//					'<p class="tit fbluel">'+
//						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
//					'</p>'+
//					'<p class="intro">'+
//						"人气：<i>"+ls.score+"</i>"+
//					'</p>'+
//					"<p class='intro'>共"+ls.episodes+"集</p>"+
//				'</div>')})
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
		url:"/edu/course/getEcoList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
				
				var ecoList='';
				   var indexL=0;
				   $.each(data.ecoList,function(index,ls){
					   if(index==0){
						   ecoList+='<div class="module-brand">'+
			                            '<span>经济</span>'+
			                            '<a href="/edu/home/total?subjectId=297e386a56410ddd0156411a94c50001" target="_blank" class="total pull-right">查看全部>></a>	'+
			                          '</div>'+
			                          '<div class="row">'
					   }
					   
					   if(index>=0&&index<=4){
						   ecoList+=
							   '<div class="col-sm-1-5">'+
				    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
				    				'<div class="col-img">'+
				    					"<img class='img-responsive' src="+ls.imgUrl+">"+
				    				'</div>'+
				    				'<div class="col-content">'+
				    					"<div class='col-title'>"+ls.courNmae+"</div>"+
				    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
				    					'<div class="course-sch">'+
				    					    ls.school+
				    						'<div class="pull-right">'+
				    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
				    						'</div>'+
				    					'</div>'+
				    				'</div>'+
				    			'</a>'+
				    		'</div>';
					   }
					
					if(index==4){
						ecoList+='</div>';
					}
				   if(index==5){
					   ecoList+='<div class="row">';
					}
				   if(index>4&&index<10){
					   ecoList+=
						   '<div class="col-sm-1-5">'+
			    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
			    				'<div class="col-img">'+
			    					"<img class='img-responsive' src="+ls.imgUrl+">"+
			    				'</div>'+
			    				'<div class="col-content">'+
			    				"<div class='col-title'>"+ls.courNmae+"</div>"+
			    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
			    					'<div class="course-sch">'+
			    					    ls.school+
			    						'<div class="pull-right">'+
			    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
			    						'</div>'+
			    					'</div>'+
			    				'</div>'+
			    			'</a>'+
			    		'</div>';
				   }
				   indexL=index;
				   })
				   if(indexL>4){
					   ecoList+='</div>';
				   }
				   $("#ecoList").append(ecoList);
				
//			   $.each(data.ecoList,function(index,ls){
//				   $("#ecoList").append(
//				'<div class="vido">'+
//					'<div class="pic">'+
//						"<a target='_blank' href=/edu/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
//					'</div>'+
//					'<p class="tit fbluel">'+
//						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
//					'</p>'+
//					'<p class="intro">'+
//						"人气：<i>"+ls.score+"</i>"+
//					'</p>'+
//					"<p class='intro'>共"+ls.episodes+"集</p>"+
//				'</div>')})
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
		url:"/edu/course/getHumList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
				var humList='';
				   var indexL=0;
				   $.each(data.humList,function(index,ls){
					   if(index==0){
						   humList+='<div class="module-brand">'+
			                            '<span>人文</span>'+
			                            '<a href="/edu/home/total?subjectId=297e386a56410ddd0156411a94c50002" target="_blank" class="total pull-right">查看全部>></a>	'+
			                          '</div>'+
			                          '<div class="row">'
					   }
					   
					   if(index>=0&&index<=4){
						   humList+=
							   '<div class="col-sm-1-5">'+
				    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
				    				'<div class="col-img">'+
				    					"<img class='img-responsive' src="+ls.imgUrl+">"+
				    				'</div>'+
				    				'<div class="col-content">'+
				    					"<div class='col-title'>"+ls.courNmae+"</div>"+
				    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
				    					'<div class="course-sch">'+
				    					    ls.school+
				    						'<div class="pull-right">'+
				    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
				    						'</div>'+
				    					'</div>'+
				    				'</div>'+
				    			'</a>'+
				    		'</div>';
					   }
					
					if(index==4){
						humList+='</div>';
					}
				   if(index==5){
					   humList+='<div class="row">';
					}
				   if(index>4&&index<10){
					   humList+=
						   '<div class="col-sm-1-5">'+
			    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
			    				'<div class="col-img">'+
			    					"<img class='img-responsive' src="+ls.imgUrl+">"+
			    				'</div>'+
			    				'<div class="col-content">'+
			    				"<div class='col-title'>"+ls.courNmae+"</div>"+
			    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
			    					'<div class="course-sch">'+
			    					    ls.school+
			    						'<div class="pull-right">'+
			    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
			    						'</div>'+
			    					'</div>'+
			    				'</div>'+
			    			'</a>'+
			    		'</div>';
				   }
				   indexL=index;
				   })
				   if(indexL>4){
					   humList+='</div>';
				   }
				   $("#humList").append(humList);
				   
//			   $.each(data.humList,function(index,ls){
//				   $("#humList").append(
//				'<div class="vido">'+
//					'<div class="pic">'+
//						"<a target='_blank' href=/edu/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
//					'</div>'+
//					'<p class="tit fbluel">'+
//						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
//					'</p>'+
//					'<p class="intro">'+
//						"人气：<i>"+ls.score+"</i>"+
//					'</p>'+
//					"<p class='intro'>共"+ls.episodes+"集</p>"+
//				'</div>')})
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
		url:"/edu/course/getPhiList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
				var phiList='';
				   var indexL=0;
				   $.each(data.phiList,function(index,ls){
					   if(index==0){
						   phiList+='<div class="module-brand">'+
			                            '<span>哲学</span>'+
			                            '<a href="/edu/home/total?subjectId=297e386a56410ddd0156411a94c50003" target="_blank" class="total pull-right">查看全部>></a>	'+
			                          '</div>'+
			                          '<div class="row">'
					   }
					   
					   if(index>=0&&index<=4){
						   phiList+=
							   '<div class="col-sm-1-5">'+
				    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
				    				'<div class="col-img">'+
				    					"<img class='img-responsive' src="+ls.imgUrl+">"+
				    				'</div>'+
				    				'<div class="col-content">'+
				    					"<div class='col-title'>"+ls.courNmae+"</div>"+
				    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
				    					'<div class="course-sch">'+
				    					    ls.school+
				    						'<div class="pull-right">'+
				    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
				    						'</div>'+
				    					'</div>'+
				    				'</div>'+
				    			'</a>'+
				    		'</div>';
					   }
					
					if(index==4){
						phiList+='</div>';
					}
				   if(index==5){
					   phiList+='<div class="row">';
					}
				   if(index>4&&index<10){
					   phiList+=
						   '<div class="col-sm-1-5">'+
			    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
			    				'<div class="col-img">'+
			    					"<img class='img-responsive' src="+ls.imgUrl+">"+
			    				'</div>'+
			    				'<div class="col-content">'+
			    				"<div class='col-title'>"+ls.courNmae+"</div>"+
			    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
			    					'<div class="course-sch">'+
			    					    ls.school+
			    						'<div class="pull-right">'+
			    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
			    						'</div>'+
			    					'</div>'+
			    				'</div>'+
			    			'</a>'+
			    		'</div>';
				   }
				   indexL=index;
				   })
				   if(indexL>4){
					   phiList+='</div>';
				   }
				   $("#phiList").append(phiList);
				
//			   $.each(data.phiList,function(index,ls){
//				   $("#phiList").append(
//				'<div class="vido">'+
//					'<div class="pic">'+
//						"<a target='_blank' href=/edu/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
//					'</div>'+
//					'<p class="tit fbluel">'+
//						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
//					'</p>'+
//					'<p class="intro">'+
//						"人气：<i>"+ls.score+"</i>"+
//					'</p>'+
//					"<p class='intro'>共"+ls.episodes+"集</p>"+
//				'</div>')})
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
		url:"/edu/course/getIdeList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
				var ideList='';
				   var indexL=0;
				   $.each(data.ideList,function(index,ls){
					   if(index==0){
						   ideList+='<div class="module-brand">'+
			                            '<span>思政</span>'+
			                            '<a href="/edu/home/total?subjectId=297e386a56410ddd0156411a94c50006" target="_blank" class="total pull-right">查看全部>></a>	'+
			                          '</div>'+
			                          '<div class="row">'
					   }
					   
					   if(index>=0&&index<=4){
						   ideList+=
							   '<div class="col-sm-1-5">'+
				    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
				    				'<div class="col-img">'+
				    					"<img class='img-responsive' src="+ls.imgUrl+">"+
				    				'</div>'+
				    				'<div class="col-content">'+
				    					"<div class='col-title'>"+ls.courNmae+"</div>"+
				    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
				    					'<div class="course-sch">'+
				    					    ls.school+
				    						'<div class="pull-right">'+
				    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
				    						'</div>'+
				    					'</div>'+
				    				'</div>'+
				    			'</a>'+
				    		'</div>';
					   }
					
					if(index==4){
						ideList+='</div>';
					}
				   if(index==5){
					   ideList+='<div class="row">';
					}
				   if(index>4&&index<10){
					   ideList+=
						   '<div class="col-sm-1-5">'+
			    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
			    				'<div class="col-img">'+
			    					"<img class='img-responsive' src="+ls.imgUrl+">"+
			    				'</div>'+
			    				'<div class="col-content">'+
			    				"<div class='col-title'>"+ls.courNmae+"</div>"+
			    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
			    					'<div class="course-sch">'+
			    					    ls.school+
			    						'<div class="pull-right">'+
			    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
			    						'</div>'+
			    					'</div>'+
			    				'</div>'+
			    			'</a>'+
			    		'</div>';
				   }
				   indexL=index;
				   })
				   if(indexL>4){
					   ideList+='</div>';
				   }
				   $("#ideList").append(ideList);
				
				
//			   $.each(data.ideList,function(index,ls){
//				   $("#ideList").append(
//				'<div class="vido">'+
//					'<div class="pic">'+
//						"<a target='_blank' href=/edu/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
//					'</div>'+
//					'<p class="tit fbluel">'+
//						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
//					'</p>'+
//					'<p class="intro">'+
//						"人气：<i>"+ls.score+"</i>"+
//					'</p>'+
//					"<p class='intro'>共"+ls.episodes+"集</p>"+
//				'</div>')})
			}
			else
			{
				$.alert(data.msg,"提示");
			}
		}
	})	
}

//理科
function getSciList() 
{
	$.ajax({
		url:"/edu/course/getSciList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
				var sciList='';
				   var indexL=0;
				   $.each(data.sciList,function(index,ls){
					   if(index==0){
						   sciList+='<div class="module-brand">'+
			                            '<span>理科</span>'+
			                            '<a href="/edu/home/total?subjectId=297e386a56410ddd0156411a94c50007" target="_blank" class="total pull-right">查看全部>></a>	'+
			                          '</div>'+
			                          '<div class="row">'
					   }
					   
					   if(index>=0&&index<=4){
						   sciList+=
							   '<div class="col-sm-1-5">'+
				    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
				    				'<div class="col-img">'+
				    					"<img class='img-responsive' src="+ls.imgUrl+">"+
				    				'</div>'+
				    				'<div class="col-content">'+
				    					"<div class='col-title'>"+ls.courNmae+"</div>"+
				    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
				    					'<div class="course-sch">'+
				    					    ls.school+
				    						'<div class="pull-right">'+
				    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
				    						'</div>'+
				    					'</div>'+
				    				'</div>'+
				    			'</a>'+
				    		'</div>';
					   }
					
					if(index==4){
						sciList+='</div>';
					}
				   if(index==5){
					   sciList+='<div class="row">';
					}
				   if(index>4&&index<10){
					   sciList+=
						   '<div class="col-sm-1-5">'+
			    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
			    				'<div class="col-img">'+
			    					"<img class='img-responsive' src="+ls.imgUrl+">"+
			    				'</div>'+
			    				'<div class="col-content">'+
			    				"<div class='col-title'>"+ls.courNmae+"</div>"+
			    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
			    					'<div class="course-sch">'+
			    					    ls.school+
			    						'<div class="pull-right">'+
			    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
			    						'</div>'+
			    					'</div>'+
			    				'</div>'+
			    			'</a>'+
			    		'</div>';
				   }
				   indexL=index;
				   })
				   if(indexL>4){
					   sciList+='</div>';
				   }
				   $("#sciList").append(sciList);
				
				
//			   $.each(data.ideList,function(index,ls){
//				   $("#ideList").append(
//				'<div class="vido">'+
//					'<div class="pic">'+
//						"<a target='_blank' href=/edu/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
//					'</div>'+
//					'<p class="tit fbluel">'+
//						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
//					'</p>'+
//					'<p class="intro">'+
//						"人气：<i>"+ls.score+"</i>"+
//					'</p>'+
//					"<p class='intro'>共"+ls.episodes+"集</p>"+
//				'</div>')})
			}
			else
			{
				$.alert(data.msg,"提示");
			}
		}
	})	
}

//其他
function getOthList() 
{
	$.ajax({
		url:"/edu/course/getOthList",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
				var othList='';
				   var indexL=0;
				   $.each(data.othList,function(index,ls){
					   if(index==0){
						   othList+='<div class="module-brand">'+
			                            '<span>其他</span>'+
			                            '<a href="/edu/home/total?subjectId=297e386a56410ddd0156411a94c50008" target="_blank" class="total pull-right">查看全部>></a>	'+
			                          '</div>'+
			                          '<div class="row">'
					   }
					   
					   if(index>=0&&index<=4){
						   othList+=
							   '<div class="col-sm-1-5">'+
				    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
				    				'<div class="col-img">'+
				    					"<img class='img-responsive' src="+ls.imgUrl+">"+
				    				'</div>'+
				    				'<div class="col-content">'+
				    					"<div class='col-title'>"+ls.courNmae+"</div>"+
				    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
				    					'<div class="course-sch">'+
				    					    ls.school+
				    						'<div class="pull-right">'+
				    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
				    						'</div>'+
				    					'</div>'+
				    				'</div>'+
				    			'</a>'+
				    		'</div>';
					   }
					
					if(index==4){
						othList+='</div>';
					}
				   if(index==5){
					   othList+='<div class="row">';
					}
				   if(index>4&&index<10){
					   othList+=
						   '<div class="col-sm-1-5">'+
			    			"<a href=/edu/home/detail/"+ls.id+" target='_blank'>"+
			    				'<div class="col-img">'+
			    					"<img class='img-responsive' src="+ls.imgUrl+">"+
			    				'</div>'+
			    				'<div class="col-content">'+
			    				"<div class='col-title'>"+ls.courNmae+"</div>"+
			    					"<div class='course-total'>共<span class='course-no'>"+ls.episodes+"</span>集</div>"+
			    					'<div class="course-sch">'+
			    					    ls.school+
			    						'<div class="pull-right">'+
			    							"<span class='glyphicon glyphicon-user'><span class='person-num'>"+ls.score+"</span></span>"+
			    						'</div>'+
			    					'</div>'+
			    				'</div>'+
			    			'</a>'+
			    		'</div>';
				   }
				   indexL=index;
				   })
				   if(indexL>4){
					   othList+='</div>';
				   }
				   $("#othList").append(othList);
				
				
//			   $.each(data.ideList,function(index,ls){
//				   $("#ideList").append(
//				'<div class="vido">'+
//					'<div class="pic">'+
//						"<a target='_blank' href=/edu/home/detail/"+ls.id+"><img src="+ls.imgUrl+" data-top='1298' width='120' height='90'></a>"+
//					'</div>'+
//					'<p class="tit fbluel">'+
//						"<a href=/play/detail/"+ls.id+" target='_blank'>"+ls.courNmae+"</a>"+
//					'</p>'+
//					'<p class="intro">'+
//						"人气：<i>"+ls.score+"</i>"+
//					'</p>'+
//					"<p class='intro'>共"+ls.episodes+"集</p>"+
//				'</div>')})
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
			getSciList();
			getOthList();
		}
)