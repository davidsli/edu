
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
											text : '<i class="fa fa-plus-circle fa-lg" ></i>添加课程',
											className : 'btn btn-primary pull-right btn-pad',
											action : function(e, dt,node, config) {
												getPage1('/admin/course/addCourse');
											}
									   }
								],
								"language" : {
									"lengthMenu" : "",
									"search" : "",
									"loadingRecords" : "数据加载中",
									"zeroRecords" : "<div class='norecord'>未找到相关数据</div>"
								},
								"sAjaxSource" : "/admin/user/listData",
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
													  html='<a style="cursor:pointer" class="table-link danger" onclick="deleteItemMember(\''+data+'\')">'+
														'	<span class="fa-stack">'+
														'		<i class="fa1 fa-square fa-stack-2x"></i>'+
														'		<i class="fa1 fa-trash-o fa-stack-1x fa-inverse"></i>'+
														'	</span>'+
														'</a>';
												 return html;
											}
										}]
							});
			
			var validator = $("#addItemMemberForm").bind("invalid-form.validate", function() {
			}).validate({
				errorElement: "p",
				errorPlacement: function(error, element) {
					error.appendTo(element.parent("div").next("span"));
				},rules: {
					serviceDesc: {
						required: true
					}
				}
			});
			
});



