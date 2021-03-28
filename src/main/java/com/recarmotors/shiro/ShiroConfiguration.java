package com.recarmotors.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
* configuración de shiro
*/ 
@Configuration
public class ShiroConfiguration {
	/**
	* Nombre de fábrica del filtro web de Shiro: shiroFilter
	*/
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// Interfaz de seguridad central de Shiro, este atributo es obligatorio 
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, Filter> filterMap = new LinkedHashMap<>();
		filterMap.put("authc", new ApiAuthorizationFilter());
		shiroFilterFactoryBean.setFilters(filterMap);
		/* Definir la estructura del mapa de la cadena de filtros shiro
		* La ruta representada por el primer '/' en la clave (consulte el valor del valor en xml) en el mapa es relativa al valor de HttpServletRequest.getContextPath ()
		* anon: el filtro correspondiente está vacío y no se hace nada. Aquí, el * detrás de .do y .jsp significa parámetros, como login.jsp? main
		* authc: Las páginas bajo este filtro deben estar autenticadas antes de que se pueda acceder a ellas. Es un interceptor integrado org.apache.shiro.web.filter.authc.FormAuthenticationFilter en Shiro
		*/ 
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		/* La definición de la cadena de filtros se ejecuta en orden de arriba hacia abajo. Generalmente, / ** se coloca en la parte inferior: esto es un pozo, y el código no funcionará si no se tiene cuidado;
        authc: todas las URL deben estar autenticadas antes de que se pueda acceder a ellas; anon: se puede acceder a todas las URL de forma anónima */ 
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/api/**", "anon");
		filterChainDefinitionMap.put("/api/login/auth", "anon");
		filterChainDefinitionMap.put("/api/login/logout", "anon");
		filterChainDefinitionMap.put("/error", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	* Si no se especifica el nombre, se crea automáticamente un bean con la primera letra del nombre del método en minúsculas
	*/
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm());
		return securityManager;
	}

	/**
	* Shiro Realm hereda del Reino personalizado de AuthorizingRealm, es decir, la clase que especifica a Shiro para verificar que el inicio de sesión del usuario sea personalizado
	*/ 
	@Bean
	public UserRealm userRealm() {
		UserRealm userRealm = new UserRealm();
		return userRealm;
	}

	/**
	* Coincidencia de credenciales
	* (Debido a que nuestra verificación de contraseña se entrega a SimpleAuthenticationInfo de Shiro para su procesamiento
	* Entonces necesitamos modificar el código en doGetAuthenticationInfo;
	*)
	* El comparador de credenciales se puede extender para realizar funciones como el bloqueo después de ingresar la contraseña incorrecta para la próxima vez
	*/ 
	@Bean(name = "credentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		//Algoritmo hash: aquí se utiliza el algoritmo MD5; 
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		// El número de hashes, como hash dos veces, es equivalente a md5 (md5 ("")); 
		hashedCredentialsMatcher.setHashIterations(2);
		//storageCredentialsHexEncoded es verdadero de forma predeterminada. En este momento, el cifrado de contraseña usa codificación hexadecimal; cuando es falso, usa codificación Base64 
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}

	/**
	* Procesador de ciclo de vida Shiro
	*/
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	/**
	* Para habilitar las anotaciones de Shiro (como @RequiresRoles, @RequiresPermissions), debe usar SpringAOP para escanear clases que usan anotaciones de Shiro y realizar la verificación de la lógica de seguridad si es necesario
	* Configure los siguientes dos beans (DefaultAdvisorAutoProxyCreator (opcional) y AuthorizationAttributeSourceAdvisor) para lograr esta función
	*/
	@Bean
	@DependsOn({"lifecycleBeanPostProcessor"})
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}
}
