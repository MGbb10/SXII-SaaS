package com.usian.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.usian.model.media.pojos.WmNewsMaterial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WmNewsMaterialMapper extends BaseMapper<WmNewsMaterial> {
    void saveRelationsByContent(@Param("materials")List<String> materials,@Param("newsId") Integer newsId,@Param("type") int type);
}