package innovation.action;

import innovation.model.Work;
import innovation.service.WorkService;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@SessionAttributes("check")
public class WorkAction extends ActionSupport {

	//private Logger logger = LoggerFactory.getLogger(WorkController.class);
	
	@Autowired
	private WorkService workService;
	
	//private UserFormValidator validator;
	
	
	/*@ModelAttribute("fileupload")    //����ע����������
	public Work getWork1() {
		Work work = new Work();
		return work;
	}*/
	
	@ModelAttribute("check")
	public Work getWork() {
		Work work = new Work();
		return work;
	}
	/*@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm(@ModelAttribute("login") User user) {
		System.out.println("login");
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", user);
		return mav;
	}*/
	/*为了减少前端麻烦，全部使用html以及json*/
	@RequestMapping(value = "/check", method = RequestMethod.GET)    //��λ�ȡ�û���¼�����Ϣ����tid����Ӧ
	public String checkForm() {
		return "check";
	}
	
	//��Ʒ�鿴
	@RequestMapping(value = "/check", method = RequestMethod.POST)   
	@ResponseBody
	public Map<String,String> check(
			//@RequestParam(required = true, defaultValue = "") String name,
			//@RequestParam(required = true, defaultValue = "") String password,
			@RequestParam(required = true,defaultValue = "") int tid,
			@ModelAttribute("check") Work work, BindingResult result) { 
		//validator.checkValidate(work, result);
		HashMap<String,String> wk = new HashMap<String,String>();
		if (result.hasErrors()) {

			wk.put("status", "1");
			wk.put("code", result.getFieldError().getCode());
			wk.put("message",result.getFieldError().getDefaultMessage());
		} else {
			//if (workService.ValidateName(name)) {
				work = workService.check(tid);        //ҵ���߼�������tid��Ӧ����Ʒ��Ϣ
				if (work.getWid()!=0) {                 //�ж�wid�Ƿ������ݿ������
					JSONObject json_work = JSONObject.fromObject(work);  //��װ��Ʒ��Ϣ
					System.out.println(json_work.toString());
					wk.put("status", "0");
					wk.put("message", "check sucess");
					wk.put("data", json_work.toString());
				} else {
					wk.put("status", "2");
					wk.put("message", "Work have not been upload!");
				}
			} 
			/*else {
				hm.put("status", "3");
				hm.put("message", "Name Does not exist.");
			}*/
		
		return wk;
	}



	//��Ʒ�ϴ�
/*	public void fileupload(@RequestParam(required = true,defaultValue = "") int tid,
			@ModelAttribute("fileupload") Work work){
		work = workService.fileupload(tid);
	}*/
	
	//��Ʒ��Ϣ����
	@RequestMapping(value = "/updatefile", method = RequestMethod.POST)   
	@ResponseBody
	public String fileupdate(@RequestParam(required = true,defaultValue = "") int wid,
			String wname,String wintro,
			@ModelAttribute("fileupdate") Work work){
		work = workService.fileupdate(wid,wname,wintro);
		return "fileupdated!";
	} 
}

