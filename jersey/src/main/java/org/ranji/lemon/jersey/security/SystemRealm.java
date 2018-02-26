package org.ranji.lemon.jersey.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.ranji.lemon.jersey.model.auth.User;
import org.ranji.lemon.jersey.service.auth.prototype.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class SystemRealm extends AuthorizingRealm {
	
	@Autowired
	@Lazy		//-- 解决redis缓存和shiro冲突的问题
	private IUserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		AuthenticationInfo authenInfo = null;
		UsernamePasswordToken token = (UsernamePasswordToken)authToken;
		User user = userService.findByUserName(token.getUsername());
		if(user!=null)authenInfo = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
		return authenInfo;
	}
}
