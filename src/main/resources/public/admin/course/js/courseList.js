
$(document)
.ready(
		function() {
			var UserTable = $('#courseList')
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
											text : '<i class="fa fa-plus-circle fa-lg" ></i>添加课程',
											className : 'btn btn-primary pull-right btn-pad',
											action : function(e, dt,node, config) {
												getPage1('/edu/admin/course/addCourse');
											}
									   }
								],
								"language" : {
									"lengthMenu" : "",
									"search" : "",
									"loadingRecords" : "数据加载中",
									"zeroRecords" : "<div class='norecord'>未找到相关数据</div>"
								},
								"sAjaxSource" : "/edu/admin/course/listData",
								"columns" : [
										{
											"data" : "courName",
											title : "课程名称"
										},{
											"data" : "teacherName",
											title : "讲师"
										},{
											"data" : "school",
											title : "开课学校"
										},{
											"data" : "subName",
											title : "所属学科"
										},{
											"data" : "id",
											title:"操作",
											className:'table-center',
											render:function(data, type, row, meta){
													  html=
														'<a style="cursor:pointer" class="table-link danger" onclick="deleteCourse(\''+data+'\')">'+
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

//删除课程
function deleteCourse(id)
{
	$.confirm({
	    title: '确认',
	    content: '确认删除该课程么？',
	    confirm: function(){
			$.ajax({
				url:"/edu/admin/course/delete",
				data:{"id":id},
				type:"post",
				dataType:"json",
				success:function(data)
				{
					if(data.success==true)
					{
						var table=$('#courseList').DataTable();
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

