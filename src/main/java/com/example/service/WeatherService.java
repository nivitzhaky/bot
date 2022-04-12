package com.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class WeatherService {
    OkHttpClient client = new OkHttpClient().newBuilder().build();

    @Autowired
    ObjectMapper om;

    public String searchWeather(String keyword) throws IOException {
        return getWeatherLocationData(getWeatherLocationId(keyword));
    }


    public String getWeatherLocationData(Integer locationId) throws IOException {
        Request request = new Request.Builder()
                .url("https://www.yahoo.com/news/_tdnews/api/resource/WeatherService;crumb=vArqLJ.DtER;woeids=%5B" +locationId + "%5D?bkt=%5B%22xray-us-nel-about-03%22%2C%22xray-exp-api-ctrl%22%2C%22JARVISUSNEWSDESK-INSESSION-00%22%5D&device=desktop&ecma=modern&feature=cacheContentCanvas%2CdelayCacheHeaders%2CdisableCommentsMessage%2CenableCCPAFooter%2CenableCMP%2CenableConsentData%2CenableGDPRFooter%2CenableGuceJs%2CenableGuceJsOverlay%2Clivecoverage%2CnewContentAttribution%2CnewLogo%2CrivendellMigration%2Cuserintent%2CvideoDocking%2CoathPlayer%2CenableAdlite&intl=us&lang=en-US&partner=none&prid=57a2b4th0g2an&region=US&site=fp&tz=Asia%2FJerusalem&ver=0.0.12106076&returnMeta=true")
                .method("GET", null)
                .addHeader("authority", "www.yahoo.com")
                .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"98\", \"Google Chrome\";v=\"98\"")
                .addHeader("device-memory", "8")
                .addHeader("rtt", "150")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.80 Safari/537.36")
                .addHeader("viewport-width", "713")
                .addHeader("x-requested-with", "XMLHttpRequest")
                .addHeader("dpr", "2")
                .addHeader("downlink", "2.3")
                .addHeader("x-webp", "1")
                .addHeader("ect", "4g")
                .addHeader("sec-ch-ua-platform", "\"macOS\"")
                .addHeader("accept", "*/*")
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("referer", "https://www.yahoo.com/news/weather/israel/ramat-gan/ramat-gan-1967869")
                .addHeader("accept-language", "en-US,en;q=0.9,he;q=0.8")
                .addHeader("cookie", "B=8tdhqp1gvm1lg&b=3&s=gh; ucs=lbit=1; GUCS=ATCSMj9v; A1=d=AQABBLAG-2ECEAcdkXMXwsgVkT7r98g6to4FEgEBBgFWCWLuYl5Ub2UB_eMBAAcIsAb7Ycg6to4&S=AQAAAs_iXH4cHxZIyUe1SjhZV6E; A3=d=AQABBLAG-2ECEAcdkXMXwsgVkT7r98g6to4FEgEBBgFWCWLuYl5Ub2UB_eMBAAcIsAb7Ycg6to4&S=AQAAAs_iXH4cHxZIyUe1SjhZV6E; A1S=d=AQABBLAG-2ECEAcdkXMXwsgVkT7r98g6to4FEgEBBgFWCWLuYl5Ub2UB_eMBAAcIsAb7Ycg6to4&S=AQAAAs_iXH4cHxZIyUe1SjhZV6E&j=WORLD; GUC=AQEBBgFiCVZi7kIhvwTZ; cmp=t=1644692945&j=0; yhcstamp=bd2cb89b438cc1730c553c481ef2d89c; thamba=2; ucs=lbit=1")
                .build();
        Response response = client.newCall(request).execute();
        WeatherLocationData res = om.readValue(response.body().string(), WeatherLocationData.class);
        return res.getData().getWeathers().get(0).getObservation().getDayPartTexts().get(0).getText();
    }
    public Integer getWeatherLocationId(String keyword) throws IOException {
        Request request = new Request.Builder()
                .url("https://www.yahoo.com/news/_tdnews/api/resource/WeatherSearch;text=" + keyword +"?bkt=%5B%22xray-us-nel-about-03%22%2C%22xray-exp-api-ctrl%22%2C%22JARVISUSNEWSDESK-INSESSION-00%22%5D&device=desktop&ecma=modern&feature=cacheContentCanvas%2CdelayCacheHeaders%2CdisableCommentsMessage%2CenableCCPAFooter%2CenableCMP%2CenableConsentData%2CenableGDPRFooter%2CenableGuceJs%2CenableGuceJsOverlay%2Clivecoverage%2CnewContentAttribution%2CnewLogo%2CrivendellMigration%2Cuserintent%2CvideoDocking%2CoathPlayer%2CenableAdlite&intl=us&lang=en-US&partner=none&prid=57a2b4th0g2an&region=US&site=fp&tz=Asia%2FJerusalem&ver=0.0.12106076&returnMeta=true")
                .method("GET", null)
                .addHeader("authority", "www.yahoo.com")
                .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"98\", \"Google Chrome\";v=\"98\"")
                .addHeader("device-memory", "8")
                .addHeader("rtt", "150")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.80 Safari/537.36")
                .addHeader("viewport-width", "713")
                .addHeader("x-requested-with", "XMLHttpRequest")
                .addHeader("dpr", "2")
                .addHeader("downlink", "2.3")
                .addHeader("x-webp", "1")
                .addHeader("ect", "4g")
                .addHeader("sec-ch-ua-platform", "\"macOS\"")
                .addHeader("accept", "*/*")
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("referer", "https://www.yahoo.com/news/weather/israel/ramat-gan/ramat-gan-1967869")
                .addHeader("accept-language", "en-US,en;q=0.9,he;q=0.8")
                .addHeader("cookie", "B=8tdhqp1gvm1lg&b=3&s=gh; ucs=lbit=1; GUCS=ATCSMj9v; A1=d=AQABBLAG-2ECEAcdkXMXwsgVkT7r98g6to4FEgEBBgFWCWLuYl5Ub2UB_eMBAAcIsAb7Ycg6to4&S=AQAAAs_iXH4cHxZIyUe1SjhZV6E; A3=d=AQABBLAG-2ECEAcdkXMXwsgVkT7r98g6to4FEgEBBgFWCWLuYl5Ub2UB_eMBAAcIsAb7Ycg6to4&S=AQAAAs_iXH4cHxZIyUe1SjhZV6E; A1S=d=AQABBLAG-2ECEAcdkXMXwsgVkT7r98g6to4FEgEBBgFWCWLuYl5Ub2UB_eMBAAcIsAb7Ycg6to4&S=AQAAAs_iXH4cHxZIyUe1SjhZV6E&j=WORLD; GUC=AQEBBgFiCVZi7kIhvwTZ; cmp=t=1644692945&j=0; yhcstamp=bd2cb89b438cc1730c553c481ef2d89c; thamba=2; ucs=lbit=1")
                .build();
        Response response = client.newCall(request).execute();
        WeatherLocationResponse res = om.readValue(response.body().string(), WeatherLocationResponse.class );
        return res.getData().get(0).getWoeid();
    }

    static class WeatherLocationResponse {
        List<WeatherLocationObject> data;

        public List<WeatherLocationObject> getData() {
            return data;
        }
    }

    static class WeatherLocationObject {
        Integer woeid;

        public Integer getWoeid() {
            return woeid;
        }
    }


    static class WeatherLocationData {
        Weathers data;

        public Weathers getData() {
            return data;
        }
    }

    static class Weathers {
        List<Weather> weathers;

        public List<Weather> getWeathers() {
            return weathers;
        }
    }

    static class Weather {
        WObservation observation;

        public WObservation getObservation() {
            return observation;
        }
    }
    static class WObservation {
        List<DayPart> dayPartTexts;

        public List<DayPart> getDayPartTexts() {
            return dayPartTexts;
        }
    }

    static class DayPart {
        String text;

        public String getText() {
            return text;
        }
    }
}

