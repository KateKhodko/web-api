package com.khodko.webapi.webapi.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HistoryInterceptor implements HandlerInterceptor {

    private final HistoryService historyService;

    @Autowired
    public HistoryInterceptor(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        historyService.produce(request.toString());
        return true;
    }
}
