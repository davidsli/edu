
$(document)
.ready(
		function() {
			var UserTable = $('#subList')
					.DataTable(
							{
								'info' : false,
								"dom" : 'Bfrtip',
								'select' : 'single',
								"bServerSide" : true,
								"columnDefs" : [ {
									"targets" : [1 ],
									"searchable" : false,
									"sortable" : false
								} ],
								"aoColumnDefs" : [ {
									"sWidth" : "15%",
									"aTargets" : [ 1 ]
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
								"sAjaxSource" : "/edu/admin/subject/listData",
								"columns" : [
										{
											"data" : "subName",
											title : "学科名称"
										},{
											"data" : "id",
											title:"操作",
											className:'table-center',
											render:function(data, type, row, meta){
													  html='';
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


