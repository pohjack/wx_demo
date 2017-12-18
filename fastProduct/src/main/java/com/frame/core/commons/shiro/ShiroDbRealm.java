/**
 * @Title: ShiroDbRealm.java
 * @Package com.frame.core.commons.shiro
 * @Description: shiro权限验证
 * @author: lzl
 * @date 2016年7月8日 下午3:06:33
 * @version V1.0
 */
package com.frame.core.commons.shiro;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frame.core.commons.utils.PropertiesUtil;
import com.frame.sys.entity.Role;
import com.frame.sys.entity.ShiroUser;
import com.frame.sys.entity.User;
import com.frame.sys.service.IRoleService;
import com.frame.sys.service.IUserService;

public class ShiroDbRealm extends AuthorizingRealm{
    
    private Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
    
    @Resource
    private IUserService userService;
    
    @Resource
    private IRoleService roleService;
    
	/**
	* @Description: shiro授权方法，当方法带有@requiresPermissions注解进行鉴权但缓存中无用户的授权信息时调用
	* @param @param principals 
	* @param @return
	* @author: shizh
	* @date 2016年10月28日 下午2:53:20
	* @throws
	 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        ShiroUser shiroUser = (ShiroUser)principals.getPrimaryPrincipal();
        List<Role> roleList = shiroUser.getRoleList();
        Set<String> roles = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for(Role role:roleList){
            roles.add(role.getId()) ;
            List<Map<String,Object>> roleResourceList = roleService.findResourceByRoleId(role.getId());
            if(roleResourceList!=null) {
                for(Map<String,Object> map:roleResourceList){
                    if(StringUtils.isNotEmpty(map.get("permission").toString())) {
                    	String[] pmss = StringUtils.split(map.get("permission").toString(), ",");
                    	for (String permission : pmss) {
                    		 info.addStringPermission(permission);
						}
                    }
                }
            }
        }
        info.addRoles(roles);
        return info;
    }
    
    /**
	* @Description: shiro登录验证方法
	* @param @param authcToken 
	* @param @return
	* @author: shizh
	* @date 2016年10月28日 下午2:54:37
	* @throws
	 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken){
        logger.info("Shiro开始登录认证");
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        User user = userService.findUserByLoginName(token.getUsername());
       
        if(user==null) {
            logger.info("用户不存在");
            throw new UnknownAccountException();
        }
        if(user.getLoginTimes() >= Integer.parseInt(PropertiesUtil.getLogProperty("loginTimes"))){
        	 Long long1 = ((new Date().getTime()) - (user.getLoginTime().getTime()))/60000;
        	 Long parseLong = Long.parseLong(PropertiesUtil.getLogProperty("accountLockedTime"));
        	 if(long1 <= parseLong){
						throw new LockedAccountException();
        	 }
        }
        if(user.getStatus() == 0){
        	throw new DisabledAccountException();
        }
        List<Role> roleList = roleService.findRoleIdByUserId(user.getId());
        ShiroUser shiroUser = new ShiroUser(user.getId(),user.getLoginName(),user.getRealName(),user.getOrgId(),roleList);
        return new SimpleAuthenticationInfo(shiroUser,user.getPassword().toCharArray(),getName());
    }
    
 
}
