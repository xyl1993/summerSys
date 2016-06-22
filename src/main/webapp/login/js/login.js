
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
    $.backstretch("../public/images/1.jpg");
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    $('#subBtn').click(function(){
    	var error = true;
    	$('.login-form').find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			error = true;
    			$(this).addClass('input-error');
    			return;
    		}
    		else {
    			$(this).removeClass('input-error');
    			error = false
    		}
    	});
    	if(!error){
    	    var data  = {
			    userName : $('#form-username').val(),
			    password : MD5Util.setMD5($('#form-password').val())
			}
			$.ajax({
			    url:'../user/login',
			    method : 'post',
				data:JSON.stringify(data),
				dataType: 'json',
				contentType: 'application/json',
				success:function(res){
				    if(res.success){
				    	console.log(res);
				    	sessionStorage.removeItem("token");
						sessionStorage.setItem("token" , res.data);
						location.href = '../index/index.html';
				    }else{
				        alert(res.message);
				    }
				}
			})
    	}
    })
});