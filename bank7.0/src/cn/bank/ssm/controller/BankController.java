package cn.bank.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.bank.ssm.bean.Admin;
import cn.bank.ssm.bean.User;
import cn.bank.ssm.exception.BankException;
import cn.bank.ssm.service.BankService;

/**
 * ��������
 * @author Administrator
 * @version 2017/9/30
 */

@Controller
public class BankController {
	
	@Resource(name="bankService")
	private BankService bankService;
	
	/*
	 * ����
	 */
	@RequestMapping("/chinese.do")
	public ModelAndView toChinese(HttpServletRequest request){
		
		return new ModelAndView(new RedirectView("./index.jsp")); 
	}
	
	/*
	 * Ӣ��
	 */
	@RequestMapping("/us.do")
	public ModelAndView toUs(HttpServletRequest request){
		
		return new ModelAndView(new RedirectView("./index.jsp")); 
	}
	
	/*
	 * �����¼ҳ��
	 */
	@RequestMapping("/toLogin.do")
	public ModelAndView toLogin(HttpServletRequest request){
		return new ModelAndView("login"); 
	}
	
	/*
	 * ������ҳ��
	 */
	@RequestMapping("/return.do")
	public ModelAndView returnToMain(HttpServletRequest request){
		return new ModelAndView("main"); 
	}
	
	
	
	//��¼
	@RequestMapping("/login.do")
	public ModelAndView login(String username,String password,String identity,HttpServletRequest request){
		if("user".equals(identity)||"�û�".equals(identity) ){
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			User user1 = bankService.login(user);
			request.getSession().setAttribute("user", user1);
			return new ModelAndView("main");
		}else{
			Admin admin = new Admin();
			admin.setUsername(username);
			admin.setPassword(password);
			Admin admin1 = bankService.adminLogin(admin);
			request.getSession().setAttribute("admin",admin);
			return new ModelAndView("redirect:/findAll.do");
		}
	}
	
	//ע��
	@RequestMapping("/regist.do")
	public String regsit(User user){
		bankService.regist(user);
		return "login";
	}
	
	//����Ա�����û��˺�״̬
	@RequestMapping("/updateTag.do")
	public ModelAndView updateTag(int id){
		User user = bankService.findById(id);
		if(user.getTag().equals("����״̬")){
			System.out.println("o normal");
			user.setTag("����״̬");
		}else{
			user.setTag("����״̬");
		}
		bankService.updateTag(user);
	    return new ModelAndView("redirect:/findAll.do");
	}
	
	//����Ա�鿴�����û�
	@RequestMapping("/findAll.do")
	public String findAll(HttpServletRequest request){
		List list = bankService.findAllUser();
		System.out.println(list.size()+"--"+list);
		request.setAttribute("list",list);
		return "admin";
	}
	
	
	
	/*
	 * ���
	 */
	@RequestMapping("/deposit.do")
	public ModelAndView deposit(HttpServletRequest request){
		
		User user=(User) request.getSession().getAttribute("user");
		if(user == null){
			ModelAndView mav1=new ModelAndView("error");
			return mav1;
		}
		String tag = user.getTag();
		if("����״̬".equals(tag)){
			throw new BankException("�˺��Ѿ������ᣬ���������������Ա�ⶳ");
		}
		
		int id=user.getId();
		
		String reqMoney = request.getParameter("money");
		String regex="[0-9]{1,6}";
		double depositMoney=0;
		if(reqMoney.matches(regex)){
			depositMoney = new Double(reqMoney);
			double money1= bankService.deposit(id,depositMoney);
			
			ModelAndView mav=new ModelAndView("list");
			mav.addObject("money", money1);
			return mav;
		}else{
			throw new BankException("�������������"+reqMoney);
		}
		
	}
	
	/*
	 * ȡ��
	 */
	@RequestMapping("/withdrawal.do")
	public ModelAndView withdrawal(HttpServletRequest request){
		
		User user=(User) request.getSession().getAttribute("user");
		if(user == null){
			ModelAndView mav1=new ModelAndView("error");
			return mav1;
		}
		String tag = user.getTag();
		if("����״̬".equals(tag)){
			throw new BankException("�˺��Ѿ������ᣬ���������������Ա�ⶳ");
		}
		int id=user.getId();
		
		String reqMoney = request.getParameter("money");
		String regex="[0-9]{1,6}";
		double withdrawalMoney=0;
		if(reqMoney.matches(regex)){
			withdrawalMoney = new Double(reqMoney);
			double money1= bankService.withdrawal(id,withdrawalMoney);
			
			ModelAndView mav=new ModelAndView("list");
			mav.addObject("money", money1);
			return mav;
		}else{
			throw new BankException("ȡ������������"+reqMoney);
		}
	}
	
	/*
	 * ת��
	 */
	@RequestMapping("/transfer.do")
	public ModelAndView transfer(HttpServletRequest request){
		
		User user=(User) request.getSession().getAttribute("user");
		if(user == null){
			ModelAndView mav1=new ModelAndView("error");
			return mav1;
		}
		String tag = user.getTag();
		if("����״̬".equals(tag)){
			throw new BankException("�˺��Ѿ������ᣬ���������������Ա�ⶳ");
		}
		int id=user.getId();
		
		String reqMoney = request.getParameter("money");
		String reqId = request.getParameter("objId");
		String regex="[0-9]{1,6}";
		double transferMoney=0;
		int transferId=0;
		if(reqMoney.matches(regex)){
			if(reqId.matches(regex)){
				transferId=new Integer(reqId);
				transferMoney = new Double(reqMoney);
				double money1= bankService.transfer(id,transferMoney,transferId);
				
				ModelAndView mav=new ModelAndView("list");
				mav.addObject("money", money1);
				
				return mav;
			
			}else{
				throw new BankException("ת��ID��������"+reqId);
			}
		}else{
			throw new BankException("ת�˽����������"+reqMoney);
		}
		
		
		
	}
	
	/*
	 * ��ѯ���
	 */
	@RequestMapping("/inqury.do")
	public ModelAndView inqury(HttpServletRequest request){
		
		User user=(User) request.getSession().getAttribute("user");
		if(user == null){
			ModelAndView mav1=new ModelAndView("error");
			return mav1;
		}
		String tag = user.getTag();
		if("����״̬".equals(tag)){
			throw new BankException("�˺��Ѿ������ᣬ���������������Ա�ⶳ");
		}
		int id=user.getId();
		
		double money1= bankService.find(id);
		
		ModelAndView mav=new ModelAndView("list");
		mav.addObject("money", money1);
		return mav;
	}

}
