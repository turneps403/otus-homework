package com.otus.homework.my.resources;

import io.micrometer.core.annotation.Timed;
import io.prometheus.client.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static java.lang.Thread.sleep;

@RestController
@RequestMapping(path = "/prom", produces = MediaType.APPLICATION_JSON_VALUE)
public class Prometheus {
    private final Counter requestCount;
    private final Gauge queueSize;
    private final Histogram histogramRequestDuration;
    private final Summary summaryRequestDuration;

    public Prometheus(CollectorRegistry collectorRegistry) {
        requestCount = Counter.build()
                .name("request_count")
                .help("Number of hello requests.")
                .register(collectorRegistry);

        queueSize = Gauge.build()
                .name("queue_size")
                .help("Size of queue.")
                .register(collectorRegistry);

        histogramRequestDuration = Histogram.build()
                .name("request_duration")
                .help("Time for HTTP request.")
                .register(collectorRegistry);

        summaryRequestDuration = Summary.build()
                .name("request_duration_summary")
                .help("Time for HTTP request.")
                .quantile(0.95, 0.01)
                .register(collectorRegistry);
    }

    @GetMapping(value = "/count")
    @Operation(hidden = true)
    public Map count() {
        requestCount.inc();
        return Collections.singletonMap("res", "counter has been increased");
    }

    @GetMapping(value = "/gauge/inc")
    @Operation(hidden = true)
    public Map gaugeInc() {
        queueSize.inc();
        return Collections.singletonMap("res", "gauge has been increased");
    }

    @GetMapping(value = "/gauge/dec")
    @Operation(hidden = true)
    public Map gaugeDec() {
        queueSize.dec();
        return Collections.singletonMap("res", "gauge has been decreased");
    }

    @GetMapping(value = "/hist")
    @Operation(hidden = true)
    public Map histogram() throws InterruptedException {
        Histogram.Timer timer = histogramRequestDuration.startTimer();

        long sleepDuration = Double.valueOf(Math.floor(Math.random() * 10 * 1000)).longValue();
        sleep(sleepDuration);

        timer.observeDuration();

        return Collections.singletonMap(
                "res",
                String.format("I kept you waiting for %s ms!", sleepDuration)
        );
    }

    @GetMapping(value = "/summ")
    @Operation(hidden = true)
    public Map summary() throws InterruptedException {
        Summary.Timer timer = summaryRequestDuration.startTimer();

        long sleepDuration = Double.valueOf(Math.floor(Math.random() * 10 * 1000)).longValue();
        sleep(sleepDuration);

        timer.observeDuration();

        return Collections.singletonMap(
                "res",
                String.format("I kept you waiting for %s ms!", sleepDuration)
        );
    }


    @Timed
    @GetMapping(value = "/hello")
    @Operation(hidden = true)
    public Map hello() {
        return Collections.singletonMap("res", "hello");
    }
}
