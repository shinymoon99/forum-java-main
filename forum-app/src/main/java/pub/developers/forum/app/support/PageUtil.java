package pub.developers.forum.app.support;

import pub.developers.forum.api.model.PageRequestModel;
import pub.developers.forum.api.model.PageResponseModel;
import pub.developers.forum.common.model.PageRequest;
import pub.developers.forum.common.model.PageResult;

import java.util.List;

/**
 * 页面工具，配置页面相关，如显示内容多少等
 * @author Qiangqiang.Bian
 * @create 20/7/30
 * @desc
 * 页面工具，配置页面相关，如显示内容多少等
 **/
public class PageUtil {

    public static <T> PageRequest<T> buildPageRequest(PageRequestModel pageRequestModel, T filter) {
        return PageRequest.build(pageRequestModel.getPageNo(), pageRequestModel.getPageSize(), filter);
    }

    public static PageRequest buildPageRequest(PageRequestModel pageRequestModel) {
        return PageRequest.build(pageRequestModel.getPageNo(), pageRequestModel.getPageSize());
    }

    public static <T> PageResponseModel<T> buildPageResponseModel(PageResult pageResult, List<T> list) {
        PageResponseModel<T> pageResponseModel = new PageResponseModel<>();
        pageResponseModel.setSize(pageResult.getSize());
        pageResponseModel.setTotal(pageResult.getTotal());
        pageResponseModel.setList(list);

        return pageResponseModel;
    }


}
