package com.bookcrossing.api.job;

import static com.bookcrossing.api.job.JobType.RESERVATION_STATUS_CHECKER;

import com.bookcrossing.api.config.ApplicationSetting;
import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.domain.entity.BookHistory;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.entity.QBookHistory;
import com.bookcrossing.api.domain.repository.BookHistoryRepository;
import com.bookcrossing.api.service.BaseTaskService;
import com.bookcrossing.api.utils.StreamSupportUtils;
import com.querydsl.core.BooleanBuilder;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ReservationStatusCheckerJob extends DefaultJob {

    private static final QBookHistory QBH = QBookHistory.bookHistory;

    private final ApplicationSetting applicationSetting;
    private final BookHistoryRepository repository;

    @Autowired
    public ReservationStatusCheckerJob(
            final BaseTaskService baseTaskService,
            final ApplicationSetting applicationSetting,
            final BookHistoryRepository repository) {
        super(baseTaskService);
        this.applicationSetting = applicationSetting;
        this.repository = repository;
    }

    @Override
    @Scheduled(cron = "${tasks.cron.reserverion-book-checker}")
    public void schedule() {
        super.schedule();
    }

    @Override
    @Transactional
    public boolean toExecute() {
        Long limitReservation = applicationSetting.getLimitReservation();

        ZonedDateTime limit = ZonedDateTime.now().minusMinutes(limitReservation);

        BooleanBuilder bb = new BooleanBuilder(QBH.status.eq(BookStatus.BOOKED)
                .and(QBH.isDeleted.eq(Boolean.FALSE))
                .and(QBH.createdDate.loe(limit)));

        Iterable<BookHistory> all = repository.findAll(bb);

        StreamSupportUtils.toStream(all)
                .forEach(this::cancelBooking);

        return true;
    }

    private void cancelBooking(BookHistory history) {
        history.setStatus(BookStatus.CANCELED);
        Book book = history.getBook();

        BookHistory bookedBook = repository.findOne(QBH.book.id.eq(book.getId())
                .and(QBH.status.eq(BookStatus.RESERVED)))
                .orElseThrow(() -> new IllegalStateException("not.found.reserved.book"));

        bookedBook.setStatus(BookStatus.AVAILABLE);

        repository.saveAndFlush(bookedBook);
        repository.saveAndFlush(history);
    }

    @Override
    public JobType jobName() {
        return RESERVATION_STATUS_CHECKER;
    }
}
