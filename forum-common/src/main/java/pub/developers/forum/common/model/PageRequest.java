package pub.developers.forum.common.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 该类定义了页面请求的相关属性.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest<T> {
    private static final Integer DEF_PAGE_SIZE = 10;
    private static final Integer DEF_PAGE_NO = 1;

    private Integer pageSize = DEF_PAGE_SIZE;
    private Integer pageNo = DEF_PAGE_NO;
    private T filter;

    /**
     * 返回构建的页面
     * @param pageNo 要创建页面的序号
     * @param pageSize 要页面的大小
     * @param filter 过滤信息
     * @return
     */
    public static <T> PageRequest<T> build(Integer pageNo, Integer pageSize, T filter) {
        PageRequest<T> pageRequest = new PageRequest<>();
        pageRequest.setPageNo(pageNo);
        pageRequest.setPageSize(pageSize);
        pageRequest.setFilter(filter);

        return pageRequest;
    }

    /**
     * 返回构建的页面
     * @param pageNo 要创建页面的序号
     * @param pageSize 要页面的大小
     * @return
     */
    public static PageRequest build(Integer pageNo, Integer pageSize) {
        PageRequest pageRequest = new PageRequest<>();
        pageRequest.setPageNo(pageNo);
        pageRequest.setPageSize(pageSize);

        return pageRequest;
    }

}
