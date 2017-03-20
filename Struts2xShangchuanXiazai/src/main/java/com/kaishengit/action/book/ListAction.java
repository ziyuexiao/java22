package com.kaishengit.action.book;

import com.kaishengit.action.BaseAction;

public class ListAction extends BaseAction {//当action包有子包的时候，子包的名字就是namespace，请求为http://localhost/book/list

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
