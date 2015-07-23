package net.wazim.chestnut;

import net.wazim.chestnut.config.ApplicationConfig;
import net.wazim.chestnut.domain.ItemRequest;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Arrays.asList;

public class ChestnutRunner {

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        ChestnutRunner chestnutRunner = new ChestnutRunner();
        chestnutRunner.start();
    }

    public void start() {
        applicationContext = new SpringApplicationBuilder(ApplicationConfig.class).showBanner(false).run();
        fillUpDatabase();
    }

    public void stop() {
        applicationContext.stop();
    }

    private static void fillUpDatabase() {
        RestTemplate restTemplate = new RestTemplate();
        List<String> filmTitles = asList("tt0411008", "tt0816692", "tt1375666", "tt0137523", "tt0110912", "tt0105236", "tt0099685", "tt0362270", "tt0449059", "tt0947798", "tt1285016", "tt1454468", "tt0790636", "tt2024544");

        filmTitles.stream().forEach(filmId -> {
            ItemRequest item = new ItemRequest();
            item.setImdbId(filmId);
            restTemplate.postForEntity("http://localhost:8080/items", item, String.class);
        });
    }
}