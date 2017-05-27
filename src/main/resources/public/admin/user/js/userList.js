
$(document)
.ready(
		function() {
			var UserTable = $('#userList')
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
								"sAjaxSource" : "/edu/admin/user/listData",
								"columns" : [
										{
											"data" : "nickName",
											title : "昵称"
										},{
											"data" : "realName",
											title : "姓名"
										},{
											"data" : "email",
											title : "邮箱"
										},{
											"data" : "phoneNumber",
											title : "电话"
										},{
											"data" : "id",
											title:"操作",
											className:'table-center',
											render:function(data, type, row, meta){
													  html='<a style="cursor:pointer" class="table-link danger" onclick="deleteUser(\''+data+'\')">'+
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

//删除用户
function deleteUser(id){
	$.confirm({
	    title: '确认',
	    content: '确认删除该用户吗？',
	    confirm: function(){
		$.ajax({
			url:"/edu/admin/user/delete",
			data:{"id":id},
			type:"post",
			dataType:"json",
			success:function(data)
			{
				if(data.success==true)
				{
					var table=$('#userList').DataTable();
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



