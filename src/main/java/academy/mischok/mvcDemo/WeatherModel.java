package academy.mischok.mvcDemo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class WeatherModel {
    private String city;
    private double temperature;
    private double feelsLike;
    private double minTemp;
    private double maxTemp;
    private String description;
    private int humidity;
    private double windSpeed;
    private int pressure;
    private Long visibility;

   private String iconUrl;
}
