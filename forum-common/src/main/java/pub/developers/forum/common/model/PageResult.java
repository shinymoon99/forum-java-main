package pub.developers.forum.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 该类定义了页面结果的信息
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private Long total;
    private Integer size;

    private List<T> list;

    /**
     * 返回构建的页面结果信息
     */
    public static <T> PageResult<T> build(Long total, Integer size, List<T> list) {
        PageResult<T> result = new PageResult<>();
        result.setSize(size);
        result.setTotal(total);
        result.setList(list);

        return result;
    }

}
