package net.joins.blog.common.util;

import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class CalendarUtil {

    Map<LocalDate, String> holidays = new HashMap<>();

    public void setHolidays(Map<LocalDate, String> holidays) {
        this.holidays = holidays;
    }

    public DateInfo[] getDateInfo(String date, int count) {
        LocalDate today = DateTimeUtils.getDate(date, "yyyy-MM-dd");
        DateInfo[] result = new DateInfo[count];
        for (int i = 0; i < count; i++) {
            LocalDate next = today.plusDays(i);
            String holiday = searchHoliday(next);
            result[i] = DateInfo.builder().date(next).holiday(holiday).build();
        }
        return result;
    }

    String searchHoliday(LocalDate date) {
        return holidays.get(date);
    }

    @Getter
    @Builder
    public static class DateInfo {
        LocalDate date;
        String holiday;

        public DayOfWeek getDay() {
            return date.getDayOfWeek();
        }

        public String getFullDay() {
            return DateTimeFormatter.ofPattern("yyyy년 MM월 dd일(E)").withLocale(Locale.KOREA).format(date);
        }

        public int getDD() {
            return date.getDayOfMonth();
        }
    }
}
