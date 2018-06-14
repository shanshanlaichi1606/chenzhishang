package com.wondersgroup.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wondersgroup.demo.entity.LoginUser;
import com.wondersgroup.demo.mapper.LoginUserMapper;
import com.wondersgroup.util.Constant;
import com.wondersgroup.util.RowBoundsUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class LoginUserService {
	@Resource
	private LoginUserMapper loginUserMapper;

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	public PageInfo<LoginUser> searchList(int pageNo, int pageSize) {
		Page<LoginUser> startPage = PageHelper.startPage(pageNo, pageSize);
		loginUserMapper.selectAll();
		PageInfo<LoginUser> pageInfo = startPage.toPageInfo();
		return pageInfo;
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<LoginUser> selectAll() {
		return loginUserMapper.selectAll();
	}

	/**
	 * 主键查询 返回实体
	 * 
	 * @param id
	 * @return
	 */
	public LoginUser selectByPrimaryKey(int id) {
		return loginUserMapper.selectByPrimaryKey(id);
	}

	/**
	 * 主键查询 返回boolean
	 * 
	 * @param id
	 * @return
	 */
	public boolean existsWithPrimaryKey(int id) {
		return loginUserMapper.existsWithPrimaryKey(id);
	}

	/**
	 * 根据实体属性查询 条件取相等
	 * 
	 * @param loginUser
	 * @return
	 */
	public List<LoginUser> select(LoginUser loginUser) {
		return loginUserMapper.select(loginUser);
	}

	/**
	 * 根据实体属性查询 条件取相等 分页查询 无总数
	 * 
	 * @param loginUser
	 * @return
	 */
	public List<LoginUser> selectByRowBounds(LoginUser loginUser, int pageNo, int pageSize) {
		return loginUserMapper.selectByRowBounds(loginUser, new RowBoundsUtil(pageNo, pageSize));
	}

	/**
	 * 自定义查询条件
	 * 
	 * @param loginUser
	 * @return
	 */
	public List<LoginUser> selectByExample(LoginUser loginUser) {
		Example example = new Example(LoginUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", loginUser.getId());
		criteria.andLike("username", loginUser.getUsername());
		return loginUserMapper.selectByExample(example);
	}

	/**
	 * 自定义查询条件 带分页 无总数
	 * 
	 * @param loginUser
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<LoginUser> selectByExampleAndRowBounds(LoginUser loginUser, int pageNo, int pageSize) {
		Example example = new Example(LoginUser.class);
		// Criteria criteria = example.createCriteria();
		// criteria.andEqualTo("id", loginUser.getId());
		// criteria.andLike("username", loginUser.getUsername());
		return loginUserMapper.selectByExampleAndRowBounds(example, new RowBoundsUtil(pageNo, pageSize));
	}

	/**
	 * 通过用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	public LoginUser selectByExampleUsername(String username) {
		Example example = new Example(LoginUser.class);
		example.createCriteria().andEqualTo("username", username);
		List<LoginUser> list = loginUserMapper.selectByExample(example);
		if (list != null && list.size() == 1) {
			if (Constant.DELETE_FLAG == list.get(0).getFlag()) {
				return null;
			}
			return list.get(0);
		} else {
			return null;
		}
	}
}
