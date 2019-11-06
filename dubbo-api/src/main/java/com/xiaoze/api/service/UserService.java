package com.xiaoze.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoze.api.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lp
 * @since 2019-10-31
 */
public interface UserService extends IService<User> {

    IPage<User> selectAll(int page, int pageSize);
}
