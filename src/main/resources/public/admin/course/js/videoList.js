
$(document)
.ready(
		function() {
			var UserTable = $('#videoList')
					.DataTable(
							{
								'info' : false,
								"dom" : 'Bfrtip',
								'select' : 'single',
								"bServerSide" : true,
								"columnDefs" : [ {
									"targets" : [4 ],
									"searchable" : false,
									"sortable" : false
								} ],
								"aoColumnDefs" : [ {
									"sWidth" : "15%",
									"aTargets" : [ 4 ]
								} ],
								"buttons" : [
										{
											text : '<i class="fa fa-plus-circle fa-lg" ></i>添加视频',
											className : 'btn btn-primary pull-right btn-pad',
											action : function(e, dt,node, config) {
												getPage1('/edu/admin/video/addVideo');
											}
									   }
								],
								"language" : {
									"lengthMenu" : "",
									"search" : "",
									"loadingRecords" : "数据加载中",
									"zeroRecords" : "<div class='norecord'>未找到相关数据</div>"
								},
								"sAjaxSource" : "/edu/admin/video/listData",
								"columns" : [
										{
											"data" : "videoDesc",
											title : "视频名称"
										},{
											"data" : "timeLong",
											title : "时长"
										},{
											"data" : "chapter",
											title : "章节"
										},{
											"data" : "course",
											title : " 所属课程"
										},{
											"data" : "id",
											title:"操作",
											className:'table-center',
											render:function(data, type, row, meta){
													  html='<a style="cursor:pointer" class="table-link danger" onclick="deleteVideo(\''+data+'\')">'+
														'	<span class="fa-stack">'+
														'		<i class="fa1 fa-square fa-stack-2x"></i>'+
														'		<i class="fa1 fa-trash-o fa-stack-1x fa-inverse"></i>'+
														'	</span>'+
														'</a>';
												 return html;
											}
										}]
							});
			
});

//删除视频
function deleteVideo(id){
	$.confirm({
	    title: '确认',
	    content: '确认删除该视频吗？',
	    confirm: function(){
		$.ajax({
			url:"/edu/admin/video/delete",
			data:{"id":id},
			type:"post",
			dataType:"json",
			success:function(data)
			{
				if(data.success==true)
				{
					var table=$('#videoList').DataTable();
		    		table.ajax.reload();
				}
				else
				{
					$.alert(data.msg,"提示");
				}
			}
		});
	}
	});
	
}
