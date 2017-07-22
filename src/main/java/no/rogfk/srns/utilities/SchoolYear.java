package no.rogfk.srns.utilities;

import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class SchoolYear {
    private Year year = Year.now();

    void setYear(Year year) {
        this.year = year;
    }

    public String getCurrentSchoolYear() {
        int currentYear = year.getValue();
        Calendar startOfSchoolYear = new GregorianCalendar(currentYear, 5, 1);
        Date now = new Date();
        String currentSchoolYear;

        if (now.before(startOfSchoolYear.getTime())) {
            currentSchoolYear = String.format("%d/%d", currentYear - 1, currentYear);
        } else {
            currentSchoolYear = String.format("%d/%d", currentYear, currentYear + 1);
        }

        return currentSchoolYear;

    }
}
