package cn.bank.ssm.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.bank.ssm.bean.Admin;
import cn.bank.ssm.bean.Log;
import cn.bank.ssm.bean.User;
import cn.bank.ssm.dao.BankDaoInterface;
import cn.bank.ssm.exception.BankException;
import cn.bank.ssm.exception.LoginException;
import cn.bank.ssm.exception.RegistException;
/**
 * ���е�ҵ����
 * @author Administrator
 *
 */
public class BankService {
	
	@Resource(name="Mapper")
	private BankDaoInterface Mapper;
	
	//ע��
	public void regist(User user){
		try{
			user.setTag("����״̬");
			Mapper.regist(user);
			
			Log log = new Log();
			log.setLogType("Regist Log");
			log.setLogContent(user.getUsername()+" is registting");
			log.setOperateTime(new Date());
			log.setUserid(user.getId());
			Mapper.addLog(log);
		}catch(Exception e){
			throw new RegistException("ע��ʧ��");
		}
	}
	
	//��ͨ�û���¼
	public User login(User user){
		User user1 =  Mapper.login(user);
		if(user1 == null){
			throw new LoginException("�˺ź����벻ƥ��"+user.getUsername()+user.getPassword());
		}
		return Mapper.login(user);
	}
	
	//����Ա��¼
	public Admin adminLogin(Admin admin){
		Admin admin1 = Mapper.adminLogin(admin);
		if(admin1 == null){
			throw new LoginException("�˺ź����벻ƥ��"+admin.getUsername()+admin.getPassword());
		}
		return Mapper.adminLogin(admin);
	}
	
	//���
	public double deposit(int id,double money){
		try{
			User user = Mapper.findById(id);
			double money1=user.getMoney();
			money1 = money1+money;
			user.setMoney(money1);
			Mapper.updateMoney(user);
			
			Log log = new Log();
			log.setLogType("Deposit Log");
			log.setLogContent(user.getUsername()+" is depositting "+money+" YUAN");
			log.setOperateTime(new Date());
			log.setUserid(user.getId());
			Mapper.addLog(log);
			return money1;
		}catch(Exception e){
		throw new RegistException("���ʧ��");
	}
		
	}
	
	
	//ȡ��
	public double withdrawal(int id,double money){
		try{
			User user = Mapper.findById(id);
			double money1=user.getMoney();
			if(money1 < money){
				throw new BankException("����");
			}
			money1 = money1 - money;
			user.setMoney(money1);
			Mapper.updateMoney(user);
			
			Log log = new Log();
			log.setLogType("Withdrawal Log");
			log.setLogContent(user.getUsername()+" is withdrawaling "+money+" YUAN");
			log.setOperateTime(new Date());
			log.setUserid(user.getId());
			Mapper.addLog(log);
			return money1;
	}catch(Exception e){
		throw new RegistException("ȡ��ʧ��");
	}
		
	}
	
	//��ѯ���
	public  double find(int id){
		User user = Mapper.findById(id);
		return user.getMoney();
	}
	
	//����id�����û�
	public  User findById(int id){
		User user = Mapper.findById(id);
		return user;
	}
	
	//����Ա�����û�״̬
	public  void updateTag(User user){
		 Mapper.updateTag(user);
	}
	
	//�����־
	public void addLog(Log log){ 
		Mapper.addLog(log);
	}
	
	//����Ա��ѯ���е��û�
	public List findAllUser(){
		return Mapper.findAll();
	}
	
	//ת��
	public  double transfer(int id,double money,int transferID){
		try{
			//��ѯת���û��Ƿ����
			if(Mapper.findById(transferID)  == null){
				throw new BankException("ת���û�������"+transferID);
			}
			//���ڵĻ��Ͳ�ѯ�����˻������
			User objUser = Mapper.findById(transferID);
			double objMoney = objUser.getMoney();
			
			//�õ���ǰ�˺ŵ����
			User user = Mapper.findById(id);
			double money1 = user.getMoney();
			if(money1 < money){
				throw new BankException("��������");
			}
			user.setMoney(money1-money);
			objUser.setMoney(objMoney+money);
			
			Mapper.updateMoney(user);
			Mapper.updateMoney(objUser);
			
			Log log = new Log();
			log.setLogType("Transfer Log");
			log.setLogContent(id+" is transfering "+money+" YUAN to "+transferID);
			log.setOperateTime(new Date());
			log.setUserid(user.getId());
			Mapper.addLog(log);
			
			Log log1 = new Log();
			log1.setLogType("Transfer Log");
			log1.setLogContent(transferID+" is recieving "+money+" YUAN from "+id);
			log1.setOperateTime(new Date());
			log1.setUserid(transferID);
			Mapper.addLog(log1);
			return money1;
	}catch(Exception e){
		throw new RegistException("ת��ʧ��");
	}
	}
}
