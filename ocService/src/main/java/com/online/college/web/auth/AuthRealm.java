package com.online.college.web.auth;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.storage.ThumbModel;
import com.online.college.common.web.SessionContext;
import com.online.college.common.web.auth.SessionUser;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tx on 2018/8/31.
 * shiro实现登录验证
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private IAuthUserService authUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection == null){
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        SessionUser sessionUser = SessionContext.getAuthUser();
        if (sessionUser == null){
            return null;
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(sessionUser.getPermissions());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        AuthUser authUser = null;

        try {
            AuthUser tmpAuthUser = new AuthUser();
            tmpAuthUser.setUsername(username);
            tmpAuthUser.setPassword(password);
            tmpAuthUser = authUserService.getByUsernameAndPassword(tmpAuthUser);
            if (null != tmpAuthUser){
                authUser = new AuthUser();
                authUser .setId(tmpAuthUser.getId());
                authUser.setRealname(tmpAuthUser.getRealname());
                authUser.setUsername(tmpAuthUser.getUsername());
                authUser.setStatus(tmpAuthUser.getStatus());
                if (!StringUtils.isNotBlank(tmpAuthUser.getHeader())){
                    authUser.setHeader(QiniuStorage.getUrl(tmpAuthUser.getHeader(), ThumbModel.THUMB_48));
                }else {
                    authUser.setHeader("/res/i/header.jpg");//设置默认头像
                }
            }else {
                throw new AuthenticationException("## user password is not correct! ");
            }
        }catch (Exception e){
            throw new AuthenticationException("## user password is not correct! ");
        }
        //创建授权用户
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authUser,password,getName());
        return info;
    }
}
