package com.example.controller;

import com.example.service.AmazonService;
import com.example.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    AmazonService amazonService;

    @Autowired
    WeatherService weatherService;

    @RequestMapping(value = "/amazon", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>(amazonService.searchProducts(keyword), HttpStatus.OK);
    }

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public ResponseEntity<?> getWeather(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>(weatherService.searchWeather(keyword), HttpStatus.OK);
    }

//    @RequestMapping(value = "/a", method = RequestMethod.GET)
//    public ResponseEntity<?> a(@RequestParam String keyword) throws IOException {
//        return new ResponseEntity<>("a", HttpStatus.OK);
//    }

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public ResponseEntity<?> a(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>("a", HttpStatus.OK);
    }

    @RequestMapping(value = "/b", method = RequestMethod.GET)
    public ResponseEntity<?> b(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>("b", HttpStatus.OK);
    }

    @RequestMapping(value = "/c", method = RequestMethod.GET)
    public ResponseEntity<?> c(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>("c", HttpStatus.OK);
    }

    @RequestMapping(value = "/d", method = RequestMethod.GET)
    public ResponseEntity<?> d(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>("d", HttpStatus.OK);
    }

    @RequestMapping(value = "", method = { RequestMethod.POST})
    public ResponseEntity<?> getBotResponse(@RequestBody BotQuery query) throws IOException {
        HashMap<String, String> params = query.getQueryResult().getParameters();
        String res = "Not found";
        if (params.containsKey("city")) {
            res = weatherService.searchWeather(params.get("city"));
        } else if (params.containsKey("product")) {
            res = amazonService.searchProducts(params.get("product"));
        }
        return new ResponseEntity<>(BotResponse.of(res), HttpStatus.OK);
    }


    static class BotQuery {
        QueryResult queryResult;

        public QueryResult getQueryResult() {
            return queryResult;
        }
    }

    static class QueryResult {
        HashMap<String, String> parameters;

        public HashMap<String, String> getParameters() {
            return parameters;
        }
    }

    static class BotResponse {
        String fulfillmentText;
        String source = "BOT";

        public String getFulfillmentText() {
            return fulfillmentText;
        }

        public String getSource() {
            return source;
        }

        public static BotResponse of(String fulfillmentText) {
            BotResponse res = new BotResponse();
            res.fulfillmentText = fulfillmentText;
            return res;
        }
    }
}
