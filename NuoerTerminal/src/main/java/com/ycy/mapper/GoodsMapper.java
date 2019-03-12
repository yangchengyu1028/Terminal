package com.ycy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.ycy.entity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<GoodsEntity> {


    List<GoodsEntity> getAllBySymptom(Pagination page, @Param("symptomId") Integer symptomId);

}
