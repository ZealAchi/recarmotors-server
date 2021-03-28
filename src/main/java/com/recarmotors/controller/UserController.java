package com.recarmotors.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.recarmotors.pojo.Role;
import com.recarmotors.pojo.User;
import com.recarmotors.service.RoleService;
import com.recarmotors.service.UserService;
import com.recarmotors.util.Result;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RequestMapping("/api/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * Consultar al usuario y el rol en el que se encuentra
	 */
	@RequestMapping("list")
//	@RequiresPermissions("user:list")
	public Result getList(int pageNum, int pageRow) {
		
		IPage<User> iPage = new Page<>(pageNum, pageRow);
		// 只查询没有冻结的用户 ： is_delete=0
		IPage<User> page = userService.page(iPage, new QueryWrapper<User>().lambda().eq(User::getIsDelete, "0"));
		List list = new ArrayList<>();
		// 查询对应的角色
		for (User User : page.getRecords()) {
			Role Role = roleService.getById(User.getRoleId());
			User.setRoleName(Role.getRoleName());
			User.setRole(Role);
			list.add(User);
		}
		page.setRecords(list);

		return Result.createBySuccess("búsqueda exitosa!", page);

	}

	/**
	 *añadir
	 */
//	@RequiresPermissions("user:add")
	@RequestMapping("add")
	public Result add(@RequestBody JSONObject jsonObject) {
		if (jsonObject != null && !jsonObject.equals("")) {
			String userName = jsonObject.getString("userName");
			String passWord = jsonObject.getString("passWord");
			String realName = jsonObject.getString("realName");
			Long rId = jsonObject.getLong("roleId");

			User User = new User();
			User.setUserName(userName);
			User.setPassWord(passWord);
			User.setIsDelete("0");
			User.setRealName(realName);
			User.setRoleId(rId);
			boolean flag = userService.saveOrUpdate(User);
			return Result.createBySuccess("¡Usuario agregado exitosamente!", flag);
		}
		return Result.createByError();

	}

	/**
	 * añadir
	 */
//	@RequiresPermissions("user:update")
	@RequestMapping("update")
	public Result update(@RequestBody JSONObject jsonObject) {
		if (jsonObject != null && !jsonObject.equals("")) {
			String userName = jsonObject.getString("userName");
			String passWord = jsonObject.getString("passWord");
			String realName = jsonObject.getString("realName");
			Long rId = jsonObject.getLong("roleId");
			Long id = jsonObject.getLong("id");
			User User = new User();
			User.setUserName(userName);
			User.setPassWord(passWord);
			User.setIsDelete("0");
			User.setRealName(realName);
			User.setRoleId(rId);
			User.setId(id);
			boolean flag = userService.saveOrUpdate(User);
			return Result.createBySuccess("¡Usuarios actualizados con éxito!", flag);
		}
		return Result.createByError();

	}

	/**
	 * Eliminación lógica de la interfaz de eliminación de usuario (congelada),
	 * equivalente a la actualización
	 */
//	@RequiresPermissions("user:delete")
	@RequestMapping("delete")
	public Result delete(@RequestBody JSONObject jsonObject) {
		if (jsonObject != null && !jsonObject.equals("")) {
			String is_delete = jsonObject.getString("isDelete");
			Long id = jsonObject.getLong("id");
			User User = new User();
			User.setIsDelete(is_delete);
			User.setId(id);
			boolean flag = userService.saveOrUpdate(User);
			return Result.createBySuccess("¡Congelar / descongelar usuarios con éxito!", flag);
		}
		return Result.createByError();

	}

//	@RequiresPermissions(value = { "user:add", "user:update" }, logical = Logical.OR)
	@RequestMapping("getRolelist")
	public Result getRolelist() {
		List<Role> list = roleService.list();
		return Result.createBySuccess("¡Obtuvo el permiso con éxito!", list);
	}
}
