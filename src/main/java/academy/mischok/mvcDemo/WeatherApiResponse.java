package academy.mischok.mvcDemo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
public class WeatherApiResponse {
    private Main main;
    private Weather[] weather;
    private Wind wind;
    private Long visibility;

    @Setter
    @Getter
    static class Main {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int humidity;
        private int pressure;
    }

    @Setter
    @Getter
    public static class Weather {
        private String description;
        private String icon;
    }

    @Setter
    @Getter
    static class Wind {
        private double speed;

        public void setSpeed(double speed) {
            this.speed = speed;
        }
    }
}