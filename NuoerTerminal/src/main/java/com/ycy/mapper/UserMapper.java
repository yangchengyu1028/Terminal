package com.ycy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ycy.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    /**
     * 通过手机号查询唯一
     * @param mobile
     * @return
     */
    UserEntity queryByMobile(@Param("mobile")String mobile);

}
