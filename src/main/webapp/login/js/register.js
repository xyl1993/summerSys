$(function(){
    /*
        Fullscreen background
    */
   $.backstretch("../public/images/1.jpg");
//  $('#picUrl').val('../public/images/98.jpg');
  $('#id').val(getRandomNo("op_user"));
 // 在键盘按下并释放及提交后验证提交表单
  $("#signinForm").validate({
  	debug:true,
    rules: {
      userName: {
        required: true,
        minlength: 3,
     	maxlength:14
      },
      name:{
      	required: true,
      	minlength: 2,
      	maxlength:10
      },
      password: {
        required: true,
        minlength: 6
      },
      quePassword: {
        required: true,
        minlength: 6,
        equalTo: "#password"
      }
    },
    messages: {
      userName: {
        required: "请输入用户名",
        minlength: "用户名长度不能小于3个字符",
        maxlength:'用户名长度不能多于14个字符'
      },
      name: {
        required: "请输入真实姓名",
        minlength: "真实姓名长度不能小于2个字符",
        maxlength:'真实姓名长度不能多于10个字符'
      },
      password: {
        required: "请输入密码",
        minlength: "密码长度不能小于 6个字符"
      },
      quePassword: {
        required: "请输入密码",
        minlength: "密码长度不能小于 6 个字母",
        equalTo: "两次密码输入不一致"
      }
    },
    submitHandler: function(form) {      
	    var data = {};
	    data.id=$('#id').val();
	    data.userName = $('#userName').val();
	    data.name = $('#name').val();
	    data.password = MD5Util.setMD5($('#password').val());
	    data.picUrl = $('#picUrl').val();
	    $.ajax({
			url : '../user/save',
			method : 'post',
			data:JSON.stringify(data),
			dataType: 'json',
			contentType: 'application/json'
		}).success(function(resp) {
			if(resp.success){
			    alert('注册成功');
			    location.href="login.html";
			}else{
			    alert(resp.message);
			}
		})
	}  
  })
	// 初始化Web Uploader
	var uploader = WebUploader.create({

	    // 选完文件后，是否自动上传。
	    auto: true,

	    // swf文件路径
	    swf:  '../public/js-util/Uploader.swf',

	    // 文件接收服务端。
	    server: '../user/uploadPic',

	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#me-filePicker',

	    // 只允许选择图片文件。
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/*'
	    }
	});
	// 当有文件添加进来的时候
	uploader.on( 'fileQueued', function( file ) {
		$('.default-img').remove();
	    var $li = $(
	            '<div id="' + file.id + '" class="file-item thumbnail">' +
	                '<img>' +
	                '<div class="info">' + file.name + '</div>' +
	            '</div>'
	            ),
	        $img = $li.find('img');
        var $list = $('#fileList');
        document.getElementById("fileList").innerHTML = "";
	    // $list为容器jQuery实例
	    $list.append( $li );

	    // 创建缩略图
	    // 如果为非图片文件，可以不用调用此方法。
	    // thumbnailWidth x thumbnailHeight 为 100 x 100
	    uploader.makeThumb( file, function( error, src ) {
	        if ( error ) {
	            $img.replaceWith('<span>不能预览</span>');
	            return;
	        }

	        $img.attr( 'src', src );
	    }, 180, 180 );
	});
	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
	    var $li = $( '#'+file.id ),
	        $percent = $li.find('.progress span');

	    // 避免重复创建
	    if ( !$percent.length ) {
	        $percent = $('<p class="progress"><span></span></p>')
	                .appendTo( $li )
	                .find('span');
	    }

	    $percent.css( 'width', percentage * 100 + '%' );
	});

	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on( 'uploadSuccess', function( file,res ) {
		$('#picUrl').val(res.data.fileUrl);
	    $( '#'+file.id ).addClass('upload-state-done');
	});

	// 文件上传失败，显示上传出错。
	uploader.on( 'uploadError', function( file ) {
	    var $li = $( '#'+file.id ),
	        $error = $li.find('div.error');

	    // 避免重复创建
	    if ( !$error.length ) {
	        $error = $('<div class="error"></div>').appendTo( $li );
	    }

	    $error.text('上传失败');
	});

	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on( 'uploadComplete', function( file ) {
	    $( '#'+file.id ).find('.progress').remove();
	    
	});
})