
$(document)
.ready(
		function() {
			var UserTable = $('#comList')
					.DataTable(
							{
								'info' : false,
								"dom" : 'Bfrtip',
								'select' : 'single',
								"bServerSide" : true,
								"columnDefs" : [ {
									"targets" : [3 ],
									"searchable" : false,
									"sortable" : false
								} ],
								"aoColumnDefs" : [ {
									"sWidth" : "15%",
									"aTargets" : [ 3 ]
								} ],
								"buttons" : [
										{
											text : '<i></i>',
											
											action : function(e, dt,node, config) {
												
											}
									   }
								],
								"language" : {
									"lengthMenu" : "",
									"search" : "",
									"loadingRecords" : "数据加载中",
									"zeroRecords" : "<div class='norecord'>未找到相关数据</div>"
								},
								"sAjaxSource" : "/edu/admin/com/listData",
								"columns" : [
										{
											"data" : "comer",
											title : "评论人"
										},{
											"data" : "videoName",
											title : "视频"
										},{
											"data" : "conntent",
											title : "评论内容"
										},{
											"data" : "id",
											title:"操作",
											className:'table-center',
											render:function(data, type, row, meta){
													  html='<a style="cursor:pointer" class="table-link danger" onclick="deleteComm(\''+data+'\')">'+
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

//删除评论
function deleteComm(id)
{
	$.confirm({
    title: '确认',
    content: '确认删除评论吗？',
    confirm: function(){
		$.ajax({
			url:"/edu/admin/com/delete",
			data:{"id":id},
			type:"post",
			dataType:"json",
			success:function(data)
			{
				if(data.success==true)
				{
					var table=$('#comList').DataTable();
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

