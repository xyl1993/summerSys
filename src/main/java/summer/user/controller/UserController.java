package summer.user.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import summer.base.util.BASE64Encoder;
import summer.base.util.CompressPic;
import summer.base.util.DateEditor;
import summer.core.constant.BusinessError;
import summer.core.constant.GlobalConstant;
import summer.core.constant.GlobalHandler;
import summer.user.model.UserModel;
import summer.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object>save(@RequestBody UserModel model, HttpServletRequest request,HttpServletResponse response){
		return userService.save(model);
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String,Object>login(@RequestBody UserModel model){
		return userService.login(model);
	}

	@RequestMapping("/loginOut")
	@ResponseBody
	public Map<String,Object>loginOut(HttpServletRequest request,HttpServletResponse response){
		return userService.loginOut(request.getHeader("token"));
	}
	/**
	 * 
	 * @Title: uploadPic
	 * @Description: TODO(上传用户头像)
	 * @param @param positionId
	 * @param @return
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/uploadheadimg")
	@ResponseBody
	public Map<String, Object> uploadHeadPic(
			@RequestParam(value = "filepic", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String picName = file.getOriginalFilename();
			if (picName == null || picName.equals("")
					|| ("null").equals(picName)) {
				picName = "default.jpg";
			}
			String prefixName = picName.substring(picName.lastIndexOf(".") + 1,
					picName.length());
			// String prefix = GlobalHandler.getResourceValue("pic.prefix");

			if (prefixName.contains(",")
					|| !GlobalConstant.IMAGE_EXTENSIONS.contains(prefixName
							.toLowerCase())) {
				// map.put("data", null);
				map.put(GlobalConstant.CODE, BusinessError.IMAGE_FORMAT_ERROR);
			} else if (file.getSize() > GlobalConstant.IMAGE_SIZE) {
				// map.put("data", null);
				map.put(GlobalConstant.CODE, BusinessError.TOO_LARGE_IMAGE);
			} else {

				String path = request.getRealPath("/"
						+ GlobalHandler.getResourceValue("upload.path") + "/"
						+ GlobalHandler.getResourceValue("upload.image"));
				String fileName = BASE64Encoder.getPicName(picName);
				// String fileName = new Date().getTime()+".jpg";
				// System.out.println(path);
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				file.transferTo(targetFile);
//				String fileUrl = request.getContextPath() + "/"
//						+ GlobalHandler.getResourceValue("upload.path") + "/"
//						+ GlobalHandler.getResourceValue("upload.image") + "/"
//						+ fileName;
				CompressPic mypic = new CompressPic(); 
				String fileNameLit = BASE64Encoder.getPicName("little"+picName);
				String result = mypic.compressPic(path, path, fileName, fileNameLit, 256, 256, true);
				System.out.println(result);
				String fileUrl = request.getContextPath() + "/"
						+ GlobalHandler.getResourceValue("upload.path") + "/"
						+ GlobalHandler.getResourceValue("upload.image") + "/"
						+ fileNameLit;
				String token = request.getHeader("token");
				targetFile.delete();
				 System.out.println(fileUrl);
				Map<String, Object> mapResult = new HashMap<String, Object>();
				mapResult
						.put("fileUrl", GlobalConstant.UPLOAD_SERVER + fileUrl);
				mapResult
				.put("fileUrlWeb", fileUrl);
				map.put(GlobalConstant.DATA, mapResult);
				map.put(GlobalConstant.CODE, GlobalConstant.SUCCESS_CODE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// map.put("data", null);
		}
		return map;
	}
	
	/**
	 * 
	 * @Title: uploadPic
	 * @Description: TODO(上传图片)
	 * @param @param positionId
	 * @param @return
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/uploadPic")
	@ResponseBody
	public Map<String, Object> uploadPic(
			@RequestParam MultipartFile file,
			HttpServletRequest request, ModelMap model) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String picName = file.getOriginalFilename();
			if (picName == null || picName.equals("")
					|| ("null").equals(picName)) {
				picName = "default.jpg";
			}
			String prefixName = picName.substring(picName.lastIndexOf(".") + 1,
					picName.length());
			// String prefix = GlobalHandler.getResourceValue("pic.prefix");

			if (prefixName.contains(",")
					|| !GlobalConstant.IMAGE_EXTENSIONS.contains(prefixName
							.toLowerCase())) {
				// map.put("data", null);
				map.put(GlobalConstant.CODE, BusinessError.IMAGE_FORMAT_ERROR);
			} else if (file.getSize() > GlobalConstant.IMAGE_SIZE) {
				// map.put("data", null);
				map.put(GlobalConstant.CODE, BusinessError.TOO_LARGE_IMAGE);
			} else {

				String path = request.getRealPath("/"
						+ GlobalHandler.getResourceValue("upload.path") + "/"
						+ GlobalHandler.getResourceValue("upload.image"));
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
						+ GlobalHandler.getResourceValue("upload.image") + "/"
						+ fileName;
				// System.out.println(fileUrl);
				Map<String, Object> mapResult = new HashMap<String, Object>();
				mapResult.put("fileUrl", fileUrl);
				mapResult.put("fileUrlWeb", fileUrl);
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
	/**
	 * initBinder
	 * 
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
}
