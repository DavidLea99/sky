package com.sky.sample.cloud.order.controller;

import com.github.sky.commons.core.json.JsonResult;
import com.sky.sample.cloud.order.entity.OrderEntity;
import com.sky.sample.cloud.order.remoting.AccountRemoting;
import com.sky.sample.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by viruser on 2018/7/13.
 */
@RestController
public class OrderController {

    @Autowired
    private AccountRemoting accountRemoting;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/orderList")
    public String orderList(String tag){
        String result = accountRemoting.accountList(tag);
        System.out.println(result);
        return result;
    }

    @RequestMapping("/payOrder")
    public String payOrder(String orderNo){
        orderService.saveOrder();
        return "success";
    }

    @RequestMapping("/getOrders")
    public JsonResult getOrders(){
        List<OrderEntity> orderList = orderService.getOrderList();

        return new JsonResult(orderList);
    }

    @RequestMapping("/aaa")
    public String aaa(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                List<OrderEntity> orderList = orderService.getOrderList();
                System.out.println("orderList:" + orderList + ",size:" + orderList.size());
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 48 * 1000;
        // schedules the task to be run in an interval
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);
        return "aaa";
    }

    @RequestMapping("/insertTest")
    public String insertTest(){
        OrderEntity entity = new OrderEntity();
        entity.setOrderNo("test999");
        entity.setProductId(1l);
        entity.setAmount(1000d);
        orderService.insertTest(entity);
        return "insertTest";
    }
}
