package kr.jobtc.restfulandcharjs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChartController {

    @Autowired
    ChartDto dto;

    @PostMapping("/loadData")
    public Map<String, Object> loadData(@RequestBody Map<String, String> param ){
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> data = null;
        String byList = param.get("byList");
        System.out.println(byList);

        if(byList.equals("월별")) data = dto.getMonthData();
        else                      data = dto.getQuaterData();

        resultMap.put("result", data);
        return resultMap;
    }

    @GetMapping("/createData")
    public void createData(){
        dto.createData();
    }

}
