package com.online.college.common.web.auth;

import java.util.Set;

/**
 * Created by tx on 2018/8/31.
 * 权限用户
 */
public interface SessionUser {

    String getUsername();

    Long getUserId();

    Set<String> getPermissions();
}
