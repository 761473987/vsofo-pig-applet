package com.vsofo.applet.pigfarmstat.data;

import com.vsofo.applet.pigfarmstat.pojo.DataType;
import com.vsofo.applet.pigfarmstat.service.ProductionEfficiencyService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataTypeDic implements InitializingBean {
    @Autowired private ProductionEfficiencyService productionEfficiencyService;
    private List<DataType> dataTypes;

    /**
     * 获取dataType
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.dataTypes = productionEfficiencyService.getDataType();
    }

    public List<DataType> getDataTypes(){
        return this.getDataTypes();
    }

    public DataType getDataType(String dataTypeId){
        for (DataType d :dataTypes) {
            if (dataTypeId.equals(d.getDataType())){
                return d;
            }
        }
        return null;
    }

    public String getDataTypeName(String dataTypeId){
        DataType dataType = getDataType(dataTypeId);
        if (dataType != null){
            return dataType.getDataTypeName();
        }
        return "";
    }
}
