package academy.mischok.mvcDemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam(value = "city", required = false, defaultValue = "Mindelheim") String city, Model model) {
        WeatherModel weather = weatherService.getWeather(city);
        if (weather != null) {
            addWeatherAttributesToModel(model, weather);

        } else {
            model.addAttribute("error", "Keine Wetterdaten verfügbar.");
        }
        return "weather";
    }

    @PostMapping("/weather")
    public String postWeather(@RequestParam("city") String city, Model model) {
        WeatherModel weather = weatherService.getWeather(city);
        if (weather != null) {
            addWeatherAttributesToModel(model, weather);
        } else {
            model.addAttribute("error", "Keine Wetterdaten verfügbar.");
        }
        return "weather";
    }

    private void addWeatherAttributesToModel(Model model, WeatherModel weather) {
        model.addAttribute("weather", weather);
        model.addAttribute("iconUrl", weather.getIconUrl());
    }
}
