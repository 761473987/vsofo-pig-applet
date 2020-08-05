package com.vsofo.applet.pigfarmstat.comm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 查看类型
 * @author: liuzhiyun
 * @create: 2020-08-04 11:48
 **/
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class ViewTypes implements Serializable {
    private static List<ViewType> viewTypeList = new ArrayList<>();
    static {
        viewTypeList.add(new ViewType(1, "按项目"));
        viewTypeList.add(new ViewType(2, "按阶段"));
    }
    static final class ViewType{
        int code;
        String typeName;
    
        public ViewType(int code, String typeName) {
            this.code = code;
            this.typeName = typeName;
        }
    
        public int getCode() {
            return code;
        }
    
        public String getTypeName() {
            return typeName;
        }
    }
    public static List<ViewType> getViewTypeList () {
        return viewTypeList;
    }
}
