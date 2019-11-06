package com.xiaoze.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoze.api.entity.User;
import com.xiaoze.api.service.UserService;
import com.xiaoze.provider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lp
 * @since 2019-10-31
 */
@Service(version = "${demo.service.version}")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> selectAll(int page, int pageSize) {
        try {
            Page<User> p = new Page<>(page, pageSize);
            p.setRecords(userMapper.selectAll(p));
            return p;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
