$(function(){
    $.ajax({
        url:'../skills/getData?creater='+userData.id,
        type:'POST',
        success:function(res){
            if(res.data){
                $('#skillsId').val(res.data.id);
                $('#skillsText').val(res.data.text);
            }else{
                $('#skillsId').val(getRandomNo("op_skil"));
            }
        }
    })
})
function skillSubmit(){
    var creater = userData.id;
    var data = {
        id:$('#skillsId').val(),
        text:$('#skillsText').val(),
        creater:userData.id
    }
    console.log(data);
    $.ajax({
        url:'../skills/save',
        data:JSON.stringify(data),
        type:'POST',
        dataType: 'json',
		contentType: 'application/json',
        success:function(res){
            if(res.success){
                showMsg('保存成功!');
			}else{
				alert(res.message);
			}
        }
    })
}