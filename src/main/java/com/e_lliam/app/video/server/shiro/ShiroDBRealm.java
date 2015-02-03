package com.e_lliam.app.video.server.shiro;


import javax.annotation.Resource;

import com.e_lliam.app.video.server.dao.IAdminDao;
import com.e_lliam.app.video.server.entity.AdminEntity;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

@Resource
public class ShiroDBRealm extends AuthorizingRealm {
	@Resource
	private IAdminDao adminDao;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		String username = String.valueOf(getAvailablePrincipal(principals));
		AdminEntity user=adminDao.findByAdminName(username);
		if(user==null){
			throw new AuthenticationException();
		}else{
			info.addRole(user.getRole());
		}
		return info;
	}

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken uptoken=(UsernamePasswordToken)token;
		AdminEntity user=adminDao.findByAdminName(uptoken.getUsername());
		if(user!=null){
			return new SimpleAuthenticationInfo(user.getAdminName(),user.getAdminPass(),"");
		}
        return null;
    }
}