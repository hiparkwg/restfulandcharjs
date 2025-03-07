package kr.jobtc.restfulandchartjs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ChartController {
    
    @Autowired
    ChartDto dto;

    @GetMapping("/")
    public ModelAndView chart(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("chart"); // templates/chart.html
        return mv;
    }

    @PostMapping("loadData")
    public Map<String, Object> loadData(@RequestBody Map<String, String> param){
        Map<String, Object> returnMap = new HashMap<>();
        List<Map<String, Object>> data = null;

        String byList =param.get("byList");
        if(byList.equals("월별")){
            data = dto.getMonthData();
        }else{ // 분기별
            data = dto.getQuateData();
        }

        returnMap.put("result", data);
        return returnMap;
    }
    
}
