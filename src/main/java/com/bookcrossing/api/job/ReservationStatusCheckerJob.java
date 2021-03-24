package com.bookcrossing.api.job;

import static com.bookcrossing.api.job.JobType.RESERVATION_STATUS_CHECKER;

import com.bookcrossing.api.config.ApplicationSetting;
import com.bookcrossing.api.domain.entity.BookHistory;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.entity.QBookHistory;
import com.bookcrossing.api.domain.repository.BookHistoryRepository;
import com.bookcrossing.api.service.BaseTaskService;
import com.bookcrossing.api.service.BookHistoryService;
import com.bookcrossing.api.utils.StreamSupportUtils;
import com.querydsl.core.BooleanBuilder;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReservationStatusCheckerJob extends DefaultJob {

    private static final QBookHistory QBH = QBookHistory.bookHistory;

    private final ApplicationSetting applicationSetting;
    private final BookHistoryRepository repository;
    private final BookHistoryService bookHistoryService;

    @Autowired
    public ReservationStatusCheckerJob(
            final BaseTaskService baseTaskService,
            final ApplicationSetting applicationSetting,
            final BookHistoryRepository repository,
            final BookHistoryService bookHistoryService) {
        super(baseTaskService);
        this.applicationSetting = applicationSetting;
        this.repository = repository;
        this.bookHistoryService = bookHistoryService;
    }

    @Override
    @Scheduled(cron = "${tasks.cron.reserverion-book-checker}")
    public void schedule() {
        super.schedule();
    }

    @Override
    public boolean toExecute() {
        Long limitReservation = applicationSetting.getLimitReservation();

        ZonedDateTime limit = ZonedDateTime.now().minusMinutes(limitReservation);

        BooleanBuilder bb = new BooleanBuilder(QBH.status.eq(BookStatus.BOOKED)
                .and(QBH.isDeleted.eq(Boolean.FALSE))
                .and(QBH.createdDate.loe(limit)));

        Iterable<BookHistory> all = repository.findAll(bb);

        StreamSupportUtils.toStream(all)
                .map(bookHistoryService::mapToDto)
                .forEach(bookHistoryService::cancelBookBooking);
        return true;
    }

    @Override
    public JobType jobName() {
        return RESERVATION_STATUS_CHECKER;
    }
}
