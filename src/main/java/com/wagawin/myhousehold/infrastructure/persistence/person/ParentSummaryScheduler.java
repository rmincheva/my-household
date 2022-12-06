package com.wagawin.myhousehold.infrastructure.persistence.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ParentSummaryScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParentSummaryScheduler.class);

    private static final String PARENT_SUMMARY_CACHE_EVICTION_IN_MIN = "0 */15 * * * *";

    private final JpaPersonRepository repository;

    public ParentSummaryScheduler(JpaPersonRepository repository) {
        this.repository = repository;
    }

    @Scheduled(cron = PARENT_SUMMARY_CACHE_EVICTION_IN_MIN)
    void createParentSummary() {
        LOGGER.debug("Starting generation of parent summary cache");
        repository.getAndCacheParentSummary();
    }
}
