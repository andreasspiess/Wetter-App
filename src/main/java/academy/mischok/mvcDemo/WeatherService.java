package academy.mischok.mvcDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    public WeatherModel getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(apiUrl, city, apiKey);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("q",city)
                .queryParam("appid",apiKey)
                .queryParam("units","metric");
        String goodUrl = uriBuilder.toUriString();
        try {
            WeatherApiResponse response = restTemplate.getForObject(goodUrl, WeatherApiResponse.class);
            if (response == null) {
                System.out.println("API response is null for city: " + city);
                return null;
            }
            return mapToWeatherModel(city, response);
        } catch (Exception e) {
            System.out.println("Error when calling weather API: " + e.getMessage());
            return null;
        }
    }



    private WeatherModel mapToWeatherModel(String city, WeatherApiResponse response) {
        WeatherModel model = new WeatherModel();
        model.setCity(city);
        model.setTemperature(response.getMain().getTemp());
        model.setFeelsLike(response.getMain().getFeels_like());
        model.setMinTemp(response.getMain().getTemp_min());
        model.setMaxTemp(response.getMain().getTemp_max());
        model.setHumidity(response.getMain().getHumidity());
        model.setWindSpeed(response.getWind().getSpeed());
        model.setPressure(response.getMain().getPressure());
        model.setVisibility(response.getVisibility());

        String iconCode = response.getWeather()[0].getIcon();
        String iconUrl = "http://openweathermap.org/img/wn/" + iconCode + ".png";
        model.setIconUrl(iconUrl);

        return model;
    }
}
