package com.jms.designpatterns.structural.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XMLAdapter implements JSONDataAnalyticsTool {
    private XMLData xmlData;

    public XMLAdapter(XMLData data) {
        xmlData = data;
    }

    @Override
    public void analyzeData() {
        log.info("Converting XML Data {} to JSON Data", xmlData.getXMLData());
        try {
            String jsonData = convertXMLToJSON(xmlData.getXMLData());
            JSONDataAnalyticsToolImpl jsonTool = new JSONDataAnalyticsToolImpl(jsonData);
            jsonTool.analyzeData();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String convertXMLToJSON(String data) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(data);
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(node);
    }
}
