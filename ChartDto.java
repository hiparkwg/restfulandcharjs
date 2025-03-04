package kr.jobtc.restful_chartjs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ChartDto {

    String[] bgColor = {"#f00", "#0f0","#00f","#f0f"};
    List<Map<String, Object>> data;

    public List<Map<String, Object>> getListData(){ // database 역활
        data = new ArrayList<>();
        // 월별 데이터 
        Random rnd = new Random();
        for(int month=1 ; month<=12; month++){
            int sale = rnd.nextInt(191)+10; // 10~200
            int color = (int)(Math.ceil(month/3.)-1);
            data.add(getMonthData(month,  sale, bgColor[color]));
        }

        return data;
    }
    public Map<String, Object> getMonthData(int month, int sale, String bgColor){
        Map<String, Object> map = new HashMap<>();
        map.put("month", month);
        map.put("sale", sale);
        map.put("bgColor", bgColor);
        return map;
    }

    public List<Map<String, Object>> getQuateData(){
        List<Map<String, Object>> quaterData = new ArrayList();
        int[] saleData = new int[4];
        if(data == null) data = getListData();

        for(Map<String, Object> m : data){
            Integer month = (Integer)m.get("month");
            Integer sale = (Integer)m.get("sale");
            Integer quater=(int)(Math.ceil(month/3.)-1);//1분기가 0 index

            saleData[quater] += sale;
        }
        for(int i=0 ; i<saleData.length ; i++){
            Map<String, Object> temp = new HashMap<>();
            temp.put("quater", (i+1));
            temp.put("sale", saleData[i]);
            temp.put("bgColor", bgColor[i]);
            quaterData.add(temp);
        }

        return quaterData;
    }

}
