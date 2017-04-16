
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
											text : '<i class="fa fa-plus-circle fa-lg" ></i>添加学科',
											className : 'btn btn-primary pull-right btn-pad',
											action : function(e, dt,node, config) {
													$('#addItemStoneTitle').empty();
													$('#addItemStoneTitle').text("添加项目成员");
													additemMember();
											}
									   }
								],
								"language" : {
									"lengthMenu" : "",
									"search" : "",
									"loadingRecords" : "数据加载中",
									"zeroRecords" : "<div class='norecord'>未找到相关数据</div>"
								},
								"sAjaxSource" : "/admin/subject/listData",
								"columns" : [
										{
											"data" : "subName",
											title : "学科名称"
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

//点击添加项目成员发生的事件
function additemMember()
{
	$.ajax({
		url:"itemMember/getUserNotInItem",
		data:{"id":$("#itemDetailMain").attr("data")},
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
				console.log(data.innerUserList);
			   var stoneHeader=$("#stoneHeader");
			   stoneHeader.empty();
			   $.each(data.innerUserList,function(index,user){
				   stoneHeader.append("<option value='"+user.id+"'>"+user.username+" </option>");
   	           });
			   $("#addItemMember").nifty("show");
			}
			else
			{
				$.alert(data.msg,"提示");
			}
		}
	})
}

$(function(){
	$("#stoneHeader").select2();
	$("#memberRole").select2();
	$("#clientLeader").select2();
	$("#clientTaker").select2();
	$("#clientId").select2();
	$("#clientRole").select2();
	if("charge"!=$("#itemMemnerRoleId").attr("ItemRole")&&"true"!=$("#member_manager").val())
	{
       $(".addItemMemberButton").remove(); 
       $(".addClientMemberButton").remove(); 
	}
													
});

//添加客户人员
function addClientUser()
{
	$.ajax({
		url:"itemMember/getClientUserNotItem",
		data:{"id":$("#itemDetailMain").attr("data")},
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
			{
			   var clientUser=$("#clientId");
			   clientUser.empty();
			   $.each(data.clientUserList,function(index,user){
				   clientUser.append("<option value='"+user.id+"'>"+user.name+" </option>");
   	           });
			   $("#addClientMember").nifty("show");
			}
			else
			{
				$.alert(data.msg,"提示");
			}
		}
	})
}
//点击客户获取客户人员列表
function changeClientUser()
{
	//alert($("#clientId option:selected").val());
	var clientId=$("#clientId option:selected").val();
	var clientLeader= $("#clientLeader");//负责人
	var clientTaker= $("#clientTaker");//参与人
	$.ajax({
		url:"itemMember/getAllClientUsers",
		data:{"clientId":clientId},
		type:"post",
		dataType:"json",
		success:function(data)
		{
			if(true==data.success)
			{
				clientLeader.empty();
     	        $.each(data.ClientUserList,function(index,user){
     	        	clientLeader.append("<option value='"+user.id+"'>"+user.name+" </option>");
     	        });
     	        //触发负责人 消除
     	        $("#clientLeader").trigger("change");
     	        //重置参与人多选
     	        resetSelect2("clientTaker");
     	       clientTaker.empty();
    	        $.each(data.ClientUserList,function(index,user){
    	        	clientTaker.append("<option value='"+user.id+"'>"+user.name+" </option>");
    	        });
     	        
			}
			else
			{
				alert(data.msg);
			}
		},
		error:function()
		{
			$.alert("处理请求错误！","确认");
		}
	})
}

//保存客户人员
function submitClientMember()
{
	var form = $( "#addClientMemberForm" );
	form.validate();
	var valid=form.valid();
	var clientUserId=$("#clientId").val();
	var clientRole=$("#clientRole option:selected").val();
	if(valid)
	{
	    $.ajax({
            type: "get",
            url:"itemMember/saveClientMember/"+$("#itemDetailMain").attr("data")+"?clientUserId="+clientUserId+
            "&clientRole="+clientRole,
            dataType: "json",
            success: function(data) {
                if(data.success==true){
                    $("#addClientMember").nifty("hide");
                    var table=$('#clientUser').DataTable();
		    		table.ajax.reload();
		    		resetSelect2("clientId");
                }else{
             	   $.alert(data.msg,'提示');
                }
             }
	    });
	}
}

//提交项目成员
function submitItemMember()
{
	var form = $( "#addItemMemberForm" );
	form.validate();
	var valid=form.valid();
	var userId=$("#stoneHeader").val();
	var itemRole=$("#memberRole option:selected").val();
	if(valid)
	{
	    $.ajax({
            type: "get",
            url:"itemMember/saveItemMember/"+$("#itemDetailMain").attr("data")+"?userId="+userId+"&itemRole="+itemRole,
            dataType: "json",
            success: function(data) {
                if(data.success==true){
                    $("#addItemMember").nifty("hide");
                    var table=$('#member').DataTable();
		    		table.ajax.reload();
                }else{
             	   $.alert(data.msg,'提示');
                }
             }
	    });
	    resetSelect2("stoneHeader");
	}
}

//删除项目人员
function deleteItemMember(id){
	$.confirm({
	    title: '确认',
	    content: '确认删除该人员吗？',
	    confirm: function(){
	    	//showOverlay();
			$.ajax({
                cache: true,
                type: "post",
                url:"itemMember/deleteItemMember",
                dataType: "json",
                data:{
                	id:id
                },// 你的formid
                success: function(data) {
                   if(data.success==true){
                	   var table=$('#member').DataTable();
  		    		   table.ajax.reload();
                   }else{
                	   $.alert(data.msg,'提示');
                   }
                   //hideOverlay();
                }
            });
	    }
	});
}

function deleteClientMember(id)
{
	$.confirm({
	    title: '确认',
	    content: '确认删除该人员吗？',
	    confirm: function(){
	    	//showOverlay();
			$.ajax({
                cache: true,
                type: "post",
                url:"itemMember/deleteClientMember",
                dataType: "json",
                data:{
                	id:id
                },// 你的formid
                success: function(data) {
                   if(data.success==true){
                	   var table=$('#clientUser').DataTable();
  		    		   table.ajax.reload();
                   }else{
                	   $.alert(data.msg,'提示');
                   }
                   //hideOverlay();
                }
            });
	    }
	});
}



