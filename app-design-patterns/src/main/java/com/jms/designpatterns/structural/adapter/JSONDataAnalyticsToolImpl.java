package com.jms.designpatterns.structural.adapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSONDataAnalyticsToolImpl implements JSONDataAnalyticsTool {
    private String jsonData;

    public JSONDataAnalyticsToolImpl(String data) {
        jsonData = data;
    }

    @Override
    public void analyzeData() {
        log.info("Analyzing Data {}", jsonData);
    }
}
