package kr.jobtc.restfulandcharjs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class ChartDto {
    String[] bgColor ={"#f00","#00f","#0f0", "#ff0"};
    List<Map<String, Object>> data;
    
    // db 대용
    public void createData(){
        data = new ArrayList<>();
        Random rnd = new Random();
        for(int month=1; month<=12 ; month++){
            int sale = rnd.nextInt(191)+10; // 10~200
            int color = (int)Math.ceil(month/3.0)-1;
            Map<String, Object> map = new HashMap<>();
            map.put("month", month);
            map.put("sale", sale);
            map.put("bgColor", bgColor[color]);
            data.add(map);
        }
    }

    // 월별 데이터
    public List<Map<String, Object>> getMonthData(){
        if(data==null) createData();
        return data;
    }

    // 분기별 데이터
    public List<Map<String, Object>> getQuaterData(){
        if(data==null) createData();
        List<Map<String, Object>> quaterData = new ArrayList();
        int[] saleData = new int[4];
        for(Map<String, Object> m : data){
            Integer month = (Integer)m.get("month");
            Integer sale = (Integer)m.get("sale");
            Integer quater = (int)Math.ceil(month/3.0)-1;
            saleData[quater] += sale;
        }

        for(int i=0; i<saleData.length ; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("month", (i+1));
            map.put("sale", saleData[i]);
            map.put("bgColor", bgColor[i]);
            quaterData.add(map);
        }
        return quaterData;
    }
}
