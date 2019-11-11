package com.yao.mpg.service.impl;

import com.yao.mpg.po.User;
import com.yao.mpg.mapper.UserMapper;
import com.yao.mpg.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tututu
 * @since 2019-11-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
