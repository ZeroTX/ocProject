package com.online.college.core.auth.service.impl;

import com.online.college.common.page.TailPage;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.core.auth.dao.AuthUserDao;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tx on 2018/7/6.
 */
@Service
public class AuthUserServiceImpl implements IAuthUserService {
    @Resource
    AuthUserDao authUserDao;
    /**
     * 根据username获取
     *
     * @param username
     */
    @Override
    public AuthUser getByUsername(String username) {
        return authUserDao.getByUsername(username);
    }

    /**
     * 创建
     *
     * @param entity
     */
    @Override
    public void createSelectivity(AuthUser entity) {
        authUserDao.createSelectivity(entity);
    }

    /**
     * 根据id获取
     *
     * @param id
     */
    @Override
    public AuthUser getById(Long id) {
        return authUserDao.getById(id);
    }

    /**
     * 根据username和password获取
     *
     * @param authUser
     */
    @Override
    public AuthUser getByUsernameAndPassword(AuthUser authUser) {
        return authUserDao.getByUsernameAndPassword(authUser);
    }

    /**
     * 获取首页推荐5个讲师
     **/
    @Override
    public List<AuthUser> queryRecommend() {
        List<AuthUser> users = authUserDao.queryRecommend();
        if (CollectionUtils.isNotEmpty(users)){
            users.forEach(user-> user.setHeader(QiniuStorage.getUrl(user.getHeader())));
        }
        return users;
    }

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     */
    @Override
    public TailPage<AuthUser> queryPage(AuthUser queryEntity, TailPage<AuthUser> page) {
        Integer itemsTotalCount = authUserDao.getTotalItemsCount(queryEntity);
        List<AuthUser> authUsers = authUserDao.queryPage(queryEntity,page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(authUsers);
        return page;
    }

    /**
     * 根据id更新
     *
     * @param entity
     */
    @Override
    public void update(AuthUser entity) {

    }

    /**
     * 根据id 进行可选性更新
     *
     * @param entity
     */
    @Override
    public void updateSelectivity(AuthUser entity) {

    }

    /**
     * 物理删除
     *
     * @param entity
     */
    @Override
    public void delete(AuthUser entity) {
        authUserDao.delete(entity);
    }

    /**
     * 逻辑删除
     *
     * @param entity
     */
    @Override
    public void deleteLogic(AuthUser entity) {
        authUserDao.deleteLogic(entity);
    }
}
