package com.online.college.core.auth.dao;

import com.online.college.common.page.TailPage;
import com.online.college.core.auth.domain.AuthUser;
import com.qiniu.util.Auth;

import java.util.List;

/**
 * Created by tx on 2018/7/6.
 */
public interface AuthUserDao {
    /**
     * 根据id获取
     * @param id
     * @return
     */
    AuthUser getById(Long id);

    /**
     * 根据username获取
     * @param username
     * @return
     */
    AuthUser getByUsername(String username);

    /**
     * 根据用户名和密码获取用户信息
     * @param authUser
     * @return
     */
    AuthUser getByUsernameAndPassword(AuthUser authUser);

    /**
     * 获取首页推荐5个讲师
     * @return
     */
    List<AuthUser> queryRecommend();

    /**
     * 获取总数量（分页）
     * @param authUser
     * @return
     */
    Integer getTotalItemsCount(AuthUser authUser);

    /**
     * 分页查询
     * @param queryEntity
     * @param page
     * @return
     */
    List<AuthUser> queryPage(AuthUser queryEntity, TailPage<AuthUser> page);

    /**
     * 创建新纪录
     * @param authUser
     */
    void createSelectivity(AuthUser authUser);

    /**
     * 根据id更新
     * @param authUser
     */
    void updateById(AuthUser authUser);

    /**
     * 根据id选择更新
     * @param authUser
     */
    void updateSelectivty(AuthUser authUser);

    /**
     * 物流删除
     * @param authUser
     */
    void delete(AuthUser authUser);

    /**
     * 逻辑删除
     * @param authUser
     */
    void deleteLogic(AuthUser authUser);
}
