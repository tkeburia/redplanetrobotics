package com.tkeburia.redplanetrobotics;

import com.tkeburia.redplanetrobotics.Services.Orchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    private final Orchestrator orchestrator;

    @Autowired
    public Application(Orchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @Override
    public void run(String... strings) throws Exception {
        LOG.info("Running");
        orchestrator.orchestrate();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
