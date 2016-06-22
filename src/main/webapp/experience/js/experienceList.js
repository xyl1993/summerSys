$(function(){
	$('#experienceGrid').datagrid({
		url:'../experience/getCompanyList?creater='+userData.id,
	    fit:true,
	    method: 'POST',
	    idField: 'id',
	    striped: true,
	    fitColumns: true,
	    singleSelect: true,
	    rownumbers: true,
	    pagination: true,
	    nowrap: false,
	    showFooter: true,
	    pageSize: 5,
   		pageList: [5, 10, 15, 20, 25, 30],
	    beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
	    columns: [[
	        { field: 'startTime', title: '工作开始日期', width: 180, align: 'left' },
	        { field: 'endTime', title: '工作结束日期', width: 180, align: 'left' },
	        { field: 'name', title: '公司名称', width: 150, align: 'left'},
	        { field: 'address', title: '工作地点', width: 100, align: 'left' },
	        { field: 'duty', title: '工作职责', width: 100, align: 'left' }
	    ]],
	    toolbar: [{
				iconCls: 'icon-add',
				text:'新增',
				id:'exp_add',
				handler: function(){
					addCompany('add');
				}
			},'-',{
				iconCls: 'icon-edit',
				text:'修改',
				id:'exp_update',
				handler: function(){
					addCompany('edit');
				}
			},'-',{
				iconCls: 'icon-remove',
				text:'删除',
				id:'exp_delete',
				handler: function(index){
					delCompany(index);
				}
			}],
	    onDblClickRow: function (rowIndex, rowData) {
	    	if ($('#mainPanle').tabs('exists', '工作详情')){
		        $('#mainPanle').tabs('close', '工作详情'); 
		    }
	    	/**添加一个tab标签**/
		    $("#mainPanle").tabs('add',{
		    	title:'工作详情',
			    closable:true,
			    href:'../experience/experienceDetail.html',
				onLoad:function(){
					// 加载表单数据
					loadCompany(rowData);
					$('#prj-add-btn,#prj-edit-btn,#prj-del-btn,#prj-save-btn').linkbutton('disable');
				}
	   		});
	   		
        }
	});
	
})

function addCompany(type){
	if(type == 'edit'){
		var rec=$('#experienceGrid').datagrid('getSelections')[0];
		if(!rec){
			$.messager.alert('提示',selectOne,'warning');
			return;
		}
        if ($('#mainPanle').tabs('exists', '工作详情')){
	        $('#mainPanle').tabs('close', '工作详情'); 
	    } 
	    /**添加一个tab标签**/
	    $("#mainPanle").tabs('add',{
	    	title:'工作详情',
		    closable:true,
		    href:'../experience/experienceDetail.html',
			onLoad:function(){
				// 加载表单数据
				loadCompany(rec);
			}
   		});
    }else if(type == 'add'){
        if ($('#mainPanle').tabs('exists', '工作详情')){
	        $('#mainPanle').tabs('close', '工作详情'); 
	    } 
	     /**添加一个tab标签**/
	    $("#mainPanle").tabs('add',{
	    	title:'工作详情',
		    closable:true,
		    href:'../experience/experienceDetail.html',
			onLoad:function(){
				$('#exp_id').val(getRandomNo("op_exp"));
				$('#prj-add-btn,#prj-edit-btn,#prj-del-btn').linkbutton('disable');
			}
   		});
    }
}
//加载表单数据
function loadCompany(rec) {
	// 加载form表单数据
	$('#exp_id').val(rec.id);
	$('#exp_name').val(rec.name);
	$('#exp_address').val(rec.address);
	$('#exp_nature').val(rec.nature);
	$('#exp_size').val(rec.size);
	if(rec.startTime){
		$('#exp_start').datebox('setValue',getSmpFormatDateByLong(rec.startTime,false));
	}
	if(rec.endTime){
	    $('#exp_end').datebox('setValue',getSmpFormatDateByLong(rec.endTime,false));
	}
	$('#exp_industry').val(rec.industry);
	$('#exp_duty').val(rec.duty);
	$('#exp_remarks').val(rec.remarks);
}

function delCompany(row){
	var rec=$('#experienceGrid').datagrid('getSelected'),
	    rowIndex = $('#experienceGrid').datagrid('getRowIndex', rec);
	if(!rec){
		$.messager.alert('提示',selectOne,'warning');
		return;
	}
	$.messager.confirm('确认','确认删除?',function(row){
        if(row){
            $.ajax({  
                url:'../experience/delete?id='+rec.id, 
                type:'POST',
                success:function(){
                    showMsg('删除成功!');
                    $('#experienceGrid').datagrid('deleteRow',rowIndex);
                }  
            });  
        }
    })  
}