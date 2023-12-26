package com.usian.apis.artical;

import com.usian.model.article.pojos.ApAuthor;
import com.usian.model.common.dtos.ResponseResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthorControllerApi {
    /**
     * 根据用户id查询作者信息
     * @param id
     * @return
     */
    public ApAuthor findByUserId(@PathVariable("id") Integer id);

    /**
     * 保存作者
     * @param apAuthor
     * @return
     */
    public ResponseResult save(@RequestBody ApAuthor apAuthor);

    /**
     * 根据名称查询作者
     * @param name
     * @return
     */
    public ApAuthor findByName(String name);

    /**
     * 根据id查询作者
     * @param id
     * @return
     */
    public ApAuthor findById(@PathVariable("id") Integer id);
}
