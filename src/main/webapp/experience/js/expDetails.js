$(function(){
	console.log(userData.id);
	$('#exp_start,#exp_end,#projectStart,#projectEnd').datebox({
	    formatter: function (date) {  
                    var y = date.getFullYear();  
                    var m = date.getMonth() + 1;  
                    var d = date.getDate();  
                    return y + "年" + (m < 10 ? ("0" + m) : m) + "月";  
                }  
    });  
    $('.form-input-dyn .datebox').css({
        'height':'inherit',
        'width':'100%'
    })
    setTimeout(function(){
    	$('#prjGrid').datagrid({
			url:'../experience/getProjectList?companyId='+$('#exp_id').val(),
		    fit:true,
		    method: 'POST',
		    idField: 'id',
		    striped: true,
		    fitColumns: true,
		    singleSelect: true,
		    rownumbers: true,
		    pagination: false,
		    nowrap: false,
		    showFooter: true,
		    columns: [[
		        { field: 'prjName', title: '项目名称', width: 100, align: 'left'},
		        { field: 'startTime', title: '项目开始日期', width: 100, align: 'left' },
		        { field: 'endTime', title: '项目结束日期', width: 80, align: 'left' },
		        { field: 'describe', title: '项目描述', width: 100, align: 'left' },
		        { field: 'duty', title: '项目职责', width: 100, align: 'left' }
		    ]],
		    onDblClickRow: function (rowIndex, rowData) {
		    	loadProjectData(rowData);
	            $('#projectWin').dialog({    
				    title:'项目信息',    
				    width: 400,    
				    height: 400,       
				    modal: true,
				    buttons:[{
					text:'保存',
					disabled:true,
					handler:function(){}
				},{
					text:'重置',
					disabled:true,
					handler:function(){
						$('#projectForm')[0].reset();
					}
				}]
			});
				$('#projectForm input,#projectForm textarea').prop('readOnly',true);
		    }
		});
    },100);
})

function companyClick(){
    var companyData = {
        id:$('#exp_id').val(),
        creater:userData.id,
        modifier:userData.id,
        name:$('#exp_name').val(),
        address:$('#exp_name').val(),
        nature:$('#exp_name').val(),
        size:$('#exp_size').val(),
        startTime:$("input[name='exp_start']").val(),
        endTime:$("input[name='exp_end']").val(),
        industry:$('#exp_industry').val(),
        duty:$('#exp_duty').val(),
        remarks:$('#exp_remarks').val()
    }
    $.ajax({
        url:'../experience/save',
		method : 'post',
		data:JSON.stringify(companyData),
		dataType: 'json',
		contentType: 'application/json',
		success:function(res){
			if(res.success){
				showMsg('保存成功!');
			    $('#experienceGrid').datagrid('reload');
			    $('#prj-add-btn,#prj-edit-btn,#prj-del-btn').linkbutton('enable');
			}else{
				alert(res.message);
			}
		}
    })
}

function prjClick(type){
	$('#projectForm').form('clear');
	var title;
	if(type == 'edit'){
		title = '项目修改';
		var rec=$('#prjGrid').datagrid('getSelections')[0];
		if(!rec){
			$.messager.alert('提示',selectOne,'warning');
			return;
		}
		$('#projectForm input,#projectForm textarea').prop('readOnly',false);
		loadProjectData(rec);
    }else if(type == 'add'){
    	title = '项目新增'
    	$('#prjId').val(getRandomNo("op_prj"));
    	$('#projectForm input,#projectForm textarea').prop('readOnly',false);
    }
    $('#projectWin').dialog({    
	    title:title,    
	    width: 400,    
	    height: 400,       
	    modal: true,
	    buttons:[{
			text:'保存',
			disabled:false,
			handler:function(){
				savePrj();
			}
		},{
			text:'重置',
			disabled:false,
			handler:function(){
				$('#projectForm')[0].reset();
			}
		}]
	});
}

//保存项目信息
function savePrj(){
    var projectData = {
	    id:$('#prjId').val(),
	    prjName:$('#prjName').val(),
	    startTime:$("input[name='projectStart']").val(),
	    endTime:$("input[name='projectEnd']").val(),
	    describe:$('#prjDescribe').val(),
	    duty : $('#prjDuty').val(),
	    companyId:$('#exp_id').val()
	};
	$.ajax({
		type: "POST",
		data:JSON.stringify(projectData),
		url: "../experience/saveProject",
		dataType: 'json',
		contentType: 'application/json',
		success: function (res) {
			console.log(res);
			if(res.success){
				$('#projectWin').dialog('close');
	       		$('#prjGrid').datagrid('reload');	
	       		showMsg('保存成功!');
	       	}
		}
	});
}

//加载表单数据
function loadProjectData(rec) {
	// 加载form表单数据
	$('#prjName').val(rec.prjName);
	$('#prjDescribe').val(rec.describe);
	$('#prjDuty').val(rec.duty);
	if(rec.startTime){
		$('#projectStart').datebox('setValue',getSmpFormatDateByLong(rec.startTime,false));
	}
	if(rec.endTime){
	    $('#projectEnd').datebox('setValue',getSmpFormatDateByLong(rec.endTime,false));
	}
	$('#prjId').val(rec.id);
}

function del(){
    var rec=$('#prjGrid').datagrid('getSelected'),
	    rowIndex = $('#prjGrid').datagrid('getRowIndex', rec);
	if(!rec){
		$.messager.alert('提示',selectOne,'warning');
		return;
	}
	$.messager.confirm('确认','确认删除?',function(row){
        if(row){
            $.ajax({  
                url:'../experience/deleteProject?id='+rec.id, 
                type:'POST',
                success:function(){
                    showMsg('删除成功!');
                    $('#prjGrid').datagrid('deleteRow',rowIndex);
                }  
            });  
        }
    })  
}