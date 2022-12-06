package com.wagawin.myhousehold.application.person;

import org.apache.http.entity.ContentType;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import us.abstracta.jmeter.javadsl.JmeterDsl;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import us.abstracta.jmeter.javadsl.http.DslHttpSampler;

import java.io.IOException;
import java.time.Duration;

import static com.wagawin.myhousehold.application.person.PersonController.V1_PERSONS_URL;

@ActiveProfiles("performance")
@SpringBootTest
public class PersonControllerLoadTest {

    private static final String URL = "http://localhost:8080" + V1_PERSONS_URL;
    private static final String V1_PERSON_1_HOUSE_URL = URL + "/1/house";
    private static final String V1_PARENT_SUMMARY = URL + "/children";

    @Test
    void getHouse() throws IOException {
        DslHttpSampler sampler = JmeterDsl.httpSampler(V1_PERSON_1_HOUSE_URL).method("GET")
                .contentType(ContentType.APPLICATION_JSON);
        TestPlanStats stats = JmeterDsl.testPlan(JmeterDsl.threadGroup(2, 100000, sampler)).run();

        MatcherAssert.assertThat(stats.overall().errorsCount(), Matchers.is(0L));
        MatcherAssert.assertThat(stats.overall().sampleTimePercentile99(), Matchers.lessThan(Duration.ofSeconds(1)));
    }

    @Test
    void getParentSummary() throws IOException {
        DslHttpSampler sampler = JmeterDsl.httpSampler(V1_PARENT_SUMMARY).method("GET")
                .contentType(ContentType.APPLICATION_JSON);
        TestPlanStats stats = JmeterDsl.testPlan(JmeterDsl.threadGroup(2, 100000, sampler)).run();

        MatcherAssert.assertThat(stats.overall().errorsCount(), Matchers.is(0L));
        MatcherAssert.assertThat(stats.overall().sampleTimePercentile99(), Matchers.lessThan(Duration.ofSeconds(1)));
    }
}
