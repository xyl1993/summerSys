package summer.test.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import summer.base.util.BASE64Encoder;
import summer.core.constant.BusinessError;
import summer.core.constant.GlobalConstant;
import summer.core.constant.GlobalHandler;
import summer.test.model.DocConverter;


@Controller
@RequestMapping("testCtr")
public class TestController {

	/**
	 * 
	 * @Title: uploadPic
	 * @Description: TODO(上传文件)
	 * @param @param positionId
	 * @param @return
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public Map<String, Object> uploadFile(
			@RequestParam MultipartFile file,
			HttpServletRequest request, ModelMap model) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String picName = file.getOriginalFilename();
//			if (picName == null || picName.equals("")
//					|| ("null").equals(picName)) {
//				picName = "default.jpg";
//			}
			String prefixName = picName.substring(picName.lastIndexOf(".") + 1,
					picName.length());
			System.out.println(prefixName);
			// String prefix = GlobalHandler.getResourceValue("pic.prefix");

			if (file.getSize() > GlobalConstant.IMAGE_SIZE) {
				// map.put("data", null);
				map.put(GlobalConstant.CODE, BusinessError.TOO_LARGE_IMAGE);
			} else {

				String path = request.getRealPath("/"
						+ GlobalHandler.getResourceValue("upload.path"));
				String fileName = BASE64Encoder.getPicName(picName);
				// String fileName = new Date().getTime()+".jpg";
				// System.out.println(path);
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				file.transferTo(targetFile);
				String fileUrl = request.getContextPath() + "/"
						+ GlobalHandler.getResourceValue("upload.path") + "/"
						+ fileName;
				//获取需要转换的文件名,将路径名中的'\'替换为'/'  
		        String converfilename = path + "/"
						+ fileName.replaceAll("\\\\", "/");  
		      //调用转换类DocConverter,并将需要转换的文件传递给该类的构造方法  
		         DocConverter d = new DocConverter(converfilename);  
		         //调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;  
		         d.conver();  
		         //调用getswfPath()方法，打印转换后的swf文件路径  
		         System.out.println(d.getswfPath());  
		         //生成swf相对路径，以便传递给flexpaper播放器  
		         String swfpath = "upload"+d.getswfPath().substring(d.getswfPath().lastIndexOf("/"));  
		         System.out.println(swfpath);  
				// System.out.println(fileUrl);
				Map<String, Object> mapResult = new HashMap<String, Object>();
				mapResult.put("fileUrl", fileUrl);
				mapResult.put("fileUrlWeb", fileUrl);
				mapResult.put("swfpath", swfpath);
				map.put(GlobalConstant.DATA, mapResult);
				map.put(GlobalConstant.CODE, GlobalConstant.SUCCESS_CODE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// map.put("data", null);
			map.put(GlobalConstant.CODE, "系统内部错误");
		}
		return map;
	}
}
