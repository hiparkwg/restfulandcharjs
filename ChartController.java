package kr.jobtc.restful_chartjs;

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
    public ModelAndView chart() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("chart");
        return mv;
    }


    @PostMapping("/listData")
    public Map<String, Object> listData(@RequestBody Map<String, String> param){
        String byList = param.get("byList");
        Map<String, Object> resp = new HashMap<>();
        List<Map<String, Object>> data=null;
        System.out.println(param);

        if(byList.equals("월별")){
            data = dto.getListData();
        }else if(byList.equals("분기별")){
            data = dto.getQuateData();
        }
        
        resp.put("result", data);
        return resp;
    }
}
