package com.bookcrossing.api.job;

import static com.bookcrossing.api.job.JobType.RESERVATION_STATUS_CHECKER;

import com.bookcrossing.api.domain.entity.BookReservationHistory;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.entity.QBookReservationHistory;
import com.bookcrossing.api.domain.repository.BookReservationHistoryRepository;
import com.bookcrossing.api.service.BaseTaskService;
import com.bookcrossing.api.utils.StreamSupportUtils;
import com.querydsl.core.BooleanBuilder;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReservationStatusCheckerJob extends DefaultJob {

    private static final QBookReservationHistory QBRH = QBookReservationHistory.bookReservationHistory;

    private final BookReservationHistoryRepository repository;

    @Autowired
    public ReservationStatusCheckerJob(
            final BaseTaskService baseTaskService,
            final BookReservationHistoryRepository bookReservationHistoryRepository) {
        super(baseTaskService);
        this.repository = bookReservationHistoryRepository;
    }

    @Override
    @Scheduled(cron = "${tasks.cron.reserverion-book-checker}")
    public void schedule() {
        super.schedule();
    }

    @Override
    public boolean toExecute() {
        BooleanBuilder bb = new BooleanBuilder(QBRH.status.eq(BookStatus.RESERVATION)
                .and(QBRH.isDeleted.eq(Boolean.FALSE))
                .and(QBRH.expiredAt.loe(ZonedDateTime.now())));

        Iterable<BookReservationHistory> all = repository.findAll(bb);

        StreamSupportUtils.toStream(all)
                .map(this::process)
                .map(repository::saveAndFlush)
                .collect(Collectors.toList());

        return true;
    }

    private BookReservationHistory process(BookReservationHistory item) {
        item.setIsDeleted(Boolean.TRUE);
        item.setStatus(BookStatus.CANCELED);
        return item;
    }

    @Override
    public JobType jobName() {
        return RESERVATION_STATUS_CHECKER;
    }
}
