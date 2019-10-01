package life.majiang.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Ye YinYong
 * @create: 2019-10-01 15:18
 **/
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirst;
    private boolean showEnd;
    private Integer pageNum;
    private List<Integer> pageList = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer pageNum, Integer pageSize) {
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        //当页码小于1时，设置为1；大于总页数时，设置为总页数
        pageNum = pageNum < 1 ? 1 : pageNum;
        pageNum = pageNum > totalPage ? totalPage : pageNum;
        this.pageNum = pageNum;
        //添加列表元素,分页下面的页码链接，点击可以跳转到某页的列表
        for (int i = pageNum - 3; i < pageNum + 4; i++) {
            if (i > 0 && i <= totalPage) {
                pageList.add(i);
            }
        }
        //是否展示上一页连接：当前页码不是1就展示；是1不展示
        showPrevious = pageNum != 1;
        //是否展示下一页连接：当前页码不是最后一页就展示，是最后一页就不展示
        showNext = !pageNum.equals(totalPage);
        //是否展示首页的连接:页码列表不包含第一页就展示，包含就不展示
        showFirst = !pageList.contains(1);
        //是否展示末页链接：页码列表不包含最后一页就展示，包含就不展示
        showEnd = !pageList.contains(totalPage);
    }
}
