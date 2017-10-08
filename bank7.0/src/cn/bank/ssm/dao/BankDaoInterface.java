package cn.bank.ssm.dao;

import java.util.List;

import cn.bank.ssm.bean.Admin;
import cn.bank.ssm.bean.Log;
import cn.bank.ssm.bean.User;
/**
 * ������Ŀ��7.0�汾
 * @author Administrator
 *
 */
public interface BankDaoInterface {
	
	public User login(User user);//��ͨ�û���¼��¼
	public Admin adminLogin(Admin admin);//admin��¼��¼
	public void regist(User user);//ע��
	public void updateMoney(User user);//�������
	public User findById(int id);//�鿴�˺��Ƿ����
	public List findAll();//�鿴�����û���Ϣ
	public void updateTag(User user);//����Ա�����û�״̬
	public void addLog(Log log);//����Ա�����û�״̬
	
	
	
	
}
