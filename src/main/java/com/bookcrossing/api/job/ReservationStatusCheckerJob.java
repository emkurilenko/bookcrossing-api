package com.bookcrossing.api.job;

import static com.bookcrossing.api.job.JobType.RESERVATION_STATUS_CHECKER;

import com.bookcrossing.api.domain.entity.BookBookingHistory;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.entity.QBookBookingHistory;
import com.bookcrossing.api.domain.repository.BookBookingHistoryRepository;
import com.bookcrossing.api.service.BaseTaskService;
import com.bookcrossing.api.service.BookBookingHistoryService;
import com.bookcrossing.api.utils.StreamSupportUtils;
import com.querydsl.core.BooleanBuilder;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReservationStatusCheckerJob extends DefaultJob {

    private static final QBookBookingHistory QBBH = QBookBookingHistory.bookBookingHistory;

    private final BookBookingHistoryRepository repository;
    private final BookBookingHistoryService reservedBookHistoryService;

    @Autowired
    public ReservationStatusCheckerJob(
            final BaseTaskService baseTaskService,
            final BookBookingHistoryRepository bookReservationHistoryRepository,
            final BookBookingHistoryService reservedBookHistoryService) {
        super(baseTaskService);
        this.repository = bookReservationHistoryRepository;
        this.reservedBookHistoryService = reservedBookHistoryService;
    }

    @Override
    @Scheduled(cron = "${tasks.cron.reserverion-book-checker}")
    public void schedule() {
        super.schedule();
    }

    @Override
    public boolean toExecute() {
        BooleanBuilder bb = new BooleanBuilder(QBBH.status.eq(BookStatus.BOOKED)
                .and(QBBH.isDeleted.eq(Boolean.FALSE))
                .and(QBBH.expiredAt.loe(ZonedDateTime.now())));

        Iterable<BookBookingHistory> all = repository.findAll(bb);

        StreamSupportUtils.toStream(all)
                .forEach(item ->
                        reservedBookHistoryService.cancelReservingBook(
                                item.getBook().getId(),
                                item.getUser().getId()));
        return true;
    }

    @Override
    public JobType jobName() {
        return RESERVATION_STATUS_CHECKER;
    }
}
