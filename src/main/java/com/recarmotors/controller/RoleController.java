package com.recarmotors.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.recarmotors.pojo.Role;
import com.recarmotors.service.RoleService;
import com.recarmotors.util.Result;

@RequestMapping("api/role")
@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**
	 * Consulta la lista de roles
	 */
	public Result list(int pageNum, int pageRow) {
		List<Role> list = roleService.list();
		return Result.createBySuccess("búsqueda exitosa!", list);
	}

	@RequestMapping("list")
	public Result getList() {
		Map map = roleService.getList();
		return Result.createBySuccess("búsqueda exitosa!", map);
	}

	/**
	 * rol agregado
	 */
//	@RequiresPermissions(value = { ":role:add", ":role:update" }, logical = Logical.OR)
	@RequestMapping("add")
	public Result add(@RequestBody JSONObject Role) {
		if (Role != null && !Role.equals("")) {
			String roleName = Role.getString("roleName");
			Long id = Role.getLong("id");
			// is_delete normalmente el front-end convertirá 0 y 1 en true y flase 
			String is_delete = Role.getString("isDelete");// "true","false"
			Role Role1 = new Role();
			Role1.setId(id);
			Role1.setRoleName(roleName);
			if (is_delete != null && !is_delete.equals("")) {
				if (is_delete.equals("true")) {
					Role1.setIsDelete("0");//Indica que la lógica no se borra
				} else {
					Role1.setIsDelete("1");// Indica lápida
				}
			}
			boolean saveOrUpdate = roleService.saveOrUpdate(Role1);
			return Result.createBySuccess("ejecución exitosa!", saveOrUpdate);

		}

		return Result.createByError();
	}

	/**
	 *Modificaciones agregadas por rol
	 */
//	@RequiresPermissions(":role:update")
	@RequestMapping("update")
	public Result update(@RequestBody JSONObject Role) {
		if (Role != null && !Role.equals("")) {
			String roleName = Role.getString("roleName");
			Long id = Role.getLong("id");
			String is_delete = Role.getString("isDelete");
			Role Role1 = new Role();
			Role1.setRoleName(roleName);
			Role1.setId(id);
			if (is_delete != null && !is_delete.equals("")) {
				if (is_delete.equals("true")) {
					Role1.setIsDelete("0");
				} else {
					Role1.setIsDelete("1");
				}

			}
			boolean saveOrUpdate = roleService.saveOrUpdate(Role1);
			return Result.createBySuccess("ejecución exitosa!", saveOrUpdate);

		}
		return Result.createByError();

	}

	/**
	 * Quitar permiso 
	 */
//	@RequiresPermissions(":role:delete")
	@RequestMapping("delete")
	public Result delete(@RequestBody JSONObject Role) {
		if (Role != null && !Role.equals("")) {
			Long id = Role.getLong("Role");
			boolean removeById = roleService.removeById(id);
			return Result.createBySuccess("eliminado correctamente!", removeById);//Eliminación física
		}
		return Result.createByError();
	}
}
