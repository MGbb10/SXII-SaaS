package com.usian.user.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.usian.common.constants.user.AdminConstants;
import com.usian.model.article.pojos.ApAuthor;
import com.usian.model.common.dtos.PageResponseResult;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.common.enums.AppHttpCodeEnum;
import com.usian.model.media.pojos.WmUser;
import com.usian.model.user.dtos.AuthDto;
import com.usian.model.user.pojos.ApUser;
import com.usian.model.user.pojos.ApUserRealname;
import com.usian.user.feign.ArticlelFeign;
import com.usian.user.feign.WmUserFeign;
import com.usian.user.mapper.ApUserMapper;
import com.usian.user.mapper.ApUserRealnameMapper;
import com.usian.user.service.ApUserRealnameService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {
    @Autowired
    private ApUserMapper apUserMapper;
    @Autowired
    private WmUserFeign wmUserFeign;
    @Autowired
    private ArticlelFeign articleFeign;
    @Override
    public PageResponseResult loadListByStatus(AuthDto dto) {
        //1.参数为空
        if (dto==null){
            return (PageResponseResult) ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.检查参数
        dto.checkParam();
        LambdaQueryWrapper<ApUserRealname> queryWrapper = new LambdaQueryWrapper<>();
        if (dto.getStatus()!=null){
            queryWrapper.eq(ApUserRealname::getStatus,dto.getStatus());
        }
        Page pageParam = new Page(dto.getPage(), dto.getSize());
        IPage page = page(pageParam, queryWrapper);
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setCode(0);
        responseResult.setData(page.getRecords());
        return responseResult;
    }

    @Override
    @GlobalTransactional
    public ResponseResult updateStatusById(AuthDto dto, Short status) {
        //1.参数判断
        if (dto==null ||dto.getId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.判断status
        if (statucCheck(status)){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //3.修改认证状态
        ApUserRealname apUserRealname = new ApUserRealname();
        //设置当前账号id
        apUserRealname.setId(dto.getId());
        //设置当前账号状态
        apUserRealname.setStatus(status);
        //设置驳回原因
        if (dto.getMsg()!=null){
            apUserRealname.setReason(dto.getMsg());
        }
        //调用方法，实现根据id修改状态，如果有驳回原因一并修改
        updateById(apUserRealname);
        //3.认真通过以后，添加自媒体账号和作者账号
        if (status.equals(AdminConstants.PASS_AUTH)){
            ResponseResult createResult = createWmUserAndAuthor(dto);
            if (createResult != null){
                return createResult;
            }}
        //测试分布式事务
        int i=1/0;
        //说明操作成功了
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    private boolean statucCheck(Short status) {
        if (status==null || (!status.equals(AdminConstants.FAIL_AUTH)&& !status.equals(AdminConstants.PASS_AUTH))){
            return true;
        }
        return false;
    }

    //创建自媒体账号和作者账号
    private ResponseResult createWmUserAndAuthor(AuthDto dto) {
        //查询用户信息，将用户信息对象查询出来
        ApUserRealname aur = getById(dto.getId());
        //在用户信息对象中，获取该对象中的用户id
        Integer userId = aur.getUserId();
        //调用ApUserMapper对象中，根据id查询用户对象的方法
        ApUser apUser = apUserMapper.selectById(userId);
        if (apUser == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //查询当前用户是否存在自媒体账号
        WmUser wmUser = wmUserFeign.findByName(apUser.getName());
        if (wmUser == null || wmUser.getId() == null){
            //当前该用户没有自媒体账号，就需要创建一个自媒体账号，并保存到数据库
            wmUser = new WmUser();
            wmUser.setApUserId(apUser.getId());
            wmUser.setCreatedTime(new Date());
            wmUser.setSalt(apUser.getSalt());
            wmUser.setName(apUser.getName());
            wmUser.setPassword(apUser.getPassword());
            wmUser.setStatus(9);
            wmUser.setPhone(apUser.getPhone());

            //远程调用，实现新增一条自媒体账号
            wmUserFeign.save(wmUser);
        }
        //创建作者账号
        createAuthor(wmUser);
        apUser.setFlag((short) 1);
        apUserMapper.updateById(apUser);
        return null;
    }


    /*
    创建作者账号
     */
    private void createAuthor(WmUser wmUser){
        //1.获取当前用户id
        Integer apUserId = wmUser.getApUserId();
        //2.先查询当前用户是否有作者账号
        ApAuthor apAuthor = articleFeign.findByUserId(apUserId);

        if (apAuthor == null){
            //如果没有作者账号就创建一个并新增
            apAuthor = new ApAuthor();
            apAuthor.setName(wmUser.getName());
            apAuthor.setType(2);
            apAuthor.setCreatedTime(new Date());
            apAuthor.setUserId(apUserId);
            articleFeign.save(apAuthor);
        }

    }
}
