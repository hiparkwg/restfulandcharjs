package kr.jobtc.restfulandchartjs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class ChartDto {
    String[] bgColor ={"#f00","#0f0","#00f", "#f0f"}; //분기별 색상
    List<Map<String, Object>> data;

    public List<Map<String, Object>> getMonthData(){ // 월별 데이터
        data = new ArrayList<>();
        Random rnd = new Random(); //월
        for(int month = 1; month<=12 ; month++){
            int sale = rnd.nextInt(191)+10; // 10~200
            int color = (int)Math.ceil(month/3.0)-1;//0~3

            Map<String, Object> map = new HashMap<>();
            map.put("month", month );
            map.put("sale", sale);
            map.put("bgColor", bgColor[color]);
            data.add(map);
        }
        return data;
    }

    public List<Map<String, Object>> getQuateData(){
        List<Map<String, Object>> quateData = new ArrayList<>();
        int[] saleData = new int[4];
        if(data == null) data = getMonthData();
        for(Map<String, Object> m : data){
            Integer month = (Integer)m.get("month");
            Integer sale = (Integer)m.get("sale");
            Integer quater = (int)Math.ceil(month/3.0)-1;
            saleData[quater] += sale;
        }

        for(int i=0 ; i<saleData.length ; i++){
            Map<String, Object> temp = new HashMap<>();
            temp.put("quater", (i+1));
            temp.put("sale", saleData[i]);
            quateData.add(temp);            
        }

        return quateData;

    }

}
