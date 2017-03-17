package com.kaishengit.util;

/**
 * Created by lenovo on 2017/3/16.
 */

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;



import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;

/**
 * 搜索条件q_like_s_title_or_daoyan=laozhang
 */
public class QueryParam {

    private String type;//eq like gt ge le lt...
    private String propertyName;//键，q_like_s_title_or_daoyan，属性的名字
    private Object value;//值laozhang

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    //查询条件
    public static List<QueryParam> builderQueryParamByRequest(HttpServletRequest request) {
        List<QueryParam> list = Lists.newArrayList();
//request.getParameterNames()获取的是查询条件中的键，如q_like_s_title_or_daoyan=
        Enumeration<String> parameterNames = request.getParameterNames();//Enumeration不是枚举，是可迭代的一个对象
        while(parameterNames.hasMoreElements()) {
            String paramterName = parameterNames.nextElement();
            String value = request.getParameter(paramterName);
            if(paramterName.startsWith("q_") && StringUtils.isNotEmpty(value)) {//避免q_like_s_title_or_daoyan=laozhang&q_eq_s_releaseyear=&p=5情况出现

                String[] array = paramterName.split("_",4);//以"_"分割，分割为4份q,like,s,title_or_daoyan
                if(array.length != 4) {
                    throw new IllegalArgumentException("查询参数异常");
                }

                Object v = null;
                String dataType = array[2];//q_like_s_title_or_daoyan中的“s”，代表查询条件的类型
                if("d".equalsIgnoreCase(dataType)) {
                    v = Double.valueOf(value);
                } else if("f".equalsIgnoreCase(dataType)) {
                    v = Float.valueOf(value);
                } else if("l".equalsIgnoreCase(dataType)) {
                    v = Long.valueOf(value);
                } else if("i".equalsIgnoreCase(dataType)) {
                    v = Integer.valueOf(value);
                } else {

                    try {
                        v = new String(value.getBytes("ISO8859-1"),"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }


                QueryParam queryParam = new QueryParam();
                queryParam.setPropertyName(array[3]);
                queryParam.setType(array[1]);
                queryParam.setValue(v);
                list.add(queryParam);

                request.setAttribute(paramterName,v);//将paramterName,v放入request空间，前段取值时就可以不用从url中取，不会出现中文乱码
            }
        }
        return list;
    }
}
