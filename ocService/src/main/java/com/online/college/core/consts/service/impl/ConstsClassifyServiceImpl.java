package com.online.college.core.consts.service.impl;

import com.online.college.common.page.TailPage;
import com.online.college.core.consts.dao.ConstsClassifyDao;
import com.online.college.core.consts.domain.ConstsClassify;
import com.online.college.core.consts.service.IConstsClassifyService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tx on 2018/7/16.
 */
@Service
public class ConstsClassifyServiceImpl implements IConstsClassifyService {
    @Resource
    private ConstsClassifyDao constsClassifyDao;

    /**
     * 根据id获取
     *
     * @param id
     */
    @Override
    public ConstsClassify getById(Long id) {
        return null;
    }

    /**
     * 获取所有
     **/
    @Override
    public List<ConstsClassify> queryAll() {
        return constsClassifyDao.queryAll();
    }

    /**
     * 根据code获取
     *
     * @param code
     */
    @Override
    public ConstsClassify getByCode(String code) {
        if (StringUtils.isEmpty(code)){
            return null;
        }
        ConstsClassify classify = new ConstsClassify();
        classify.setCode(code);
        List<ConstsClassify> classifies = constsClassifyDao.queryByCondition(classify);
        if (CollectionUtils.isNotEmpty(classifies)){
            return classifies.get(0);
        }
        return null;
    }

    /**
     * 根据条件动态获取
     *
     * @param queryEntity
     */
    @Override
    public List<ConstsClassify> queryByCondition(ConstsClassify queryEntity) {
        return null;
    }

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     */
    @Override
    public TailPage<ConstsClassify> queryPage(ConstsClassify queryEntity, TailPage<ConstsClassify> page) {
        return null;
    }

    /**
     * 创建
     *
     * @param entity
     */
    @Override
    public void create(ConstsClassify entity) {

    }

    /**
     * 创建
     *
     * @param entity
     */
    @Override
    public void createSelectivity(ConstsClassify entity) {

    }

    /**
     * 根据id 进行可选性更新
     *
     * @param entity
     */
    @Override
    public void updateSelectivity(ConstsClassify entity) {

    }

    /**
     * 物理删除
     *
     * @param entity
     */
    @Override
    public void delete(ConstsClassify entity) {

    }

    /**
     * 逻辑删除
     *
     * @param entity
     */
    @Override
    public void deleteLogic(ConstsClassify entity) {

    }
}
