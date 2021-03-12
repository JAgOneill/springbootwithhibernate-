package com.jbk.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jbk.dao.SpringDao;
import com.jbk.entity.Country;
import com.jbk.entity.Employee;
import com.jbk.entity.Registration;

@Service
public class SpringService {
@Autowired
SpringDao springdao;
public String getMessage()
{
	return springdao.getMessage();
}
public List<Employee>getAllEmployee()
{
	List<Employee>emplist= springdao.getAllEmployee();
	return emplist;
}



public List<Registration>getAllreg()
{
	List<Registration>reglist= springdao.getAllreg();
	return reglist;
}

public List<Country>getAllcounties()
{
	List<Country>countrylist=springdao.getAllcounties();
	return countrylist;
}
public Employee getEmployeeById(int id)
{
	Employee listemp=springdao.getEmployeeById(id);
 return listemp;
}

public List<Country>getCountryById(int id )
{
	List<Country>countrylist=springdao.getCountryById(id);
	return countrylist;
}
public List<Employee>getEmployeebystatus(String status)
{
	List<Employee>listemp=springdao.getEmployeebystatus(status);
	return listemp;
}
public List<Employee>getEmployeebyName(String name)
{
	List<Employee>listemp=springdao.getEmployeebyName(name);
	return listemp;
}
public String addEmployee(Employee emp)
{
	String msg= springdao.addEmployee(emp);
	return msg;
}


public String addNewReg( Registration reg)
{
	String msg=springdao.addNewReg(reg);
	return msg;
}
/*public List<Employee> addEmployee(Employee emp)
{
	List<Employee>listemp=springdao.addEmployee(emp);
	return listemp;
}
*/
public String addcountry(Country country)
{
	String msg=springdao.addcountry(country);
	return msg;
}
public String deleteEmployeeById(int id)
{
	String msg = springdao.deleteEmployeeById(id);
	return msg;
}


public String updateEmployee(Employee emp)
{
	String msg= springdao.updateEmployee(emp);
	return msg;
}
public String suspendEmployeebyid(int id)
{
	System.out.println("inside suspend Employee Controller");
	String suspendresult=springdao.suspendEmployeebyid(id);
	return "Employee got suspended Successfully";
}

public String ChangeStatus(Employee emp )
{
	String msg= springdao.ChangeStatus(emp);
	return msg;
}
//public Registration checkLogin(String email,String password)
//{
//	System.out.println("Email is " +email);
//	System.out.println("inside service method of chevklogin");
//	Registration user= springdao.checkLogin(email,password);
//	return user;
//}


public HashMap checkLogin(String email,String password)
{
	Registration reg= springdao.checkLogin(email, password);
	HashMap hm = new HashMap();
	if(reg==null)
	{
		hm.put("msg","invalid user");
		
	}
	else
	{
		hm.put("msg","valid user");
		hm.put("user", reg);
		
	}
	return hm ;
}
}
