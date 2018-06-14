package com.wondersgroup.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wondersgroup.demo.entity.LoginUser;
import com.wondersgroup.demo.service.LoginUserService;
import com.wondersgroup.demo.util.annotation.LoginAnnotation;
import com.wondersgroup.util.CommonStatusResult;

@RestController
@RequestMapping("page/")
public class LoginUserController {

	@Resource
	private LoginUserService loginUserService;

	/**
	 * 分页查询 查询所有
	 * 
	 * @return
	 */
	@LoginAnnotation("admin1")
	@GetMapping("/searchList/{pageNo}/{pageSize}")
	public CommonStatusResult searchList(@PathVariable int pageNo,@PathVariable int pageSize) {
		PageInfo<LoginUser> loginUserList=  loginUserService.searchList(pageNo, pageSize);
		return CommonStatusResult.success("0", loginUserList);
		
	}
	
	@GetMapping("/selectAll")
	public List<LoginUser> selectAll() {
		List<LoginUser> loginUserList=  loginUserService.selectAll();
		return loginUserList;
		
	}

	/**
	 * 主键查询 返回实体
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/selectByPrimaryKey/{id}")
	public LoginUser selectByPrimaryKey(@PathVariable int id) {
		LoginUser selectByPrimaryKey = loginUserService.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	/**
	 * 主键查询 返回boolean
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/existsWithPrimaryKey/{id}")
	public boolean existsWithPrimaryKey(@PathVariable int id) {
		return loginUserService.existsWithPrimaryKey(id);
	}

	/**
	 * 根据实体属性查询 条件取相等
	 * 
	 * @param loginUser
	 * @return
	 */
	@GetMapping("/select/{id}")
	public List<LoginUser> select(@PathVariable int id) {
		LoginUser loginUser = new LoginUser();
		loginUser.setUsername("admin");
		loginUser.setId(id);
		return loginUserService.select(loginUser);
	}

	/**
	 * 根据实体属性查询 条件取相等 分页查询 无总数
	 * 
	 * @param loginUser
	 * @return
	 */
	@GetMapping("/selectByRowBounds/{id}/{username}/{pageNo}/{pageSize}")
	public List<LoginUser> selectByRowBounds(@PathVariable int id, @PathVariable String userName,
			@PathVariable int pageNo, @PathVariable int pageSize) {
		LoginUser loginUser = new LoginUser();
		loginUser.setUsername(userName);
		loginUser.setId(id);
		return loginUserService.selectByRowBounds(loginUser, pageNo, pageSize);
	}

	/**
	 * 自定义查询条件
	 * 
	 * @param loginUser
	 * @return
	 */
	@GetMapping("/selectByExample")
	public List<LoginUser> selectByExample() {
		LoginUser loginUser = new LoginUser();
		loginUser.setUsername("%a%");
		loginUser.setId(1);
	return loginUserService.selectByExample(loginUser);
	}

	/**
	 * 自定义查询条件 带分页 无总数
	 * 
	 * @param loginUser
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/selectByExampleAndRowBounds/{pageNo}/{pageSize}")
	public List<LoginUser> selectByExampleAndRowBounds(int pageNo, int pageSize) {
		LoginUser loginUser = new LoginUser();
		loginUser.setUsername("%a%");
		loginUser.setId(1);
		return loginUserService.selectByExampleAndRowBounds(loginUser, pageNo, pageSize);
	}

}
