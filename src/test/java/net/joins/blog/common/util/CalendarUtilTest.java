package net.joins.blog.common.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CalendarUtilTest {

    CalendarUtil util;

    @BeforeEach
    void setUp() {
        util = new CalendarUtil();
        Map<LocalDate, String> holidays = new HashMap<>();
        holidays.put(LocalDate.of(2021, 5, 5), "어린이날");
        holidays.put(LocalDate.of(2021, 5, 19), "석가탄신일");
        util.setHolidays(holidays);
    }

    @Test
    void count_return() {
        String current = "2021-05-01";
        int count = 30;
        // 1. count만큼 리턴되는지
        CalendarUtil.DateInfo result[] = util.getDateInfo(current, count);
        assertEquals(result.length, count);

        // 2. 날짜가 순차적인지 - date
        CalendarUtil.DateInfo first = result[0];
        CalendarUtil.DateInfo last = result[count - 1];
        assertEquals(first.getDate(), last.getDate().minusDays(count - 1));

        // 3. 요일이 잘 나오는지? - day
        for (CalendarUtil.DateInfo date : result) {
            log.info("day {}", date.getDay());
        }

        // 4. 휴일이 정상으로 표현되는지? - holiday
        assertNull(result[0].getHoliday());
        assertNotNull(result[4].getHoliday());  //  5-5
        assertNull(result[5].getHoliday());
        assertNotNull(result[18].getHoliday()); //  5-19
        assertNull(result[19].getHoliday());

    }
}
