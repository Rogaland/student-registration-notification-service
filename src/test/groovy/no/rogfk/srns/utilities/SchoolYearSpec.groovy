package no.rogfk.srns.utilities

import spock.lang.Specification

import java.time.Year

class SchoolYearSpec extends Specification {

    def "Get current school year"() {
        given:
        SchoolYear schoolYear = new SchoolYear(year: Year.parse("2016"))

        when:
        def currentSchoolYear = schoolYear.getCurrentSchoolYear()

        then:
        currentSchoolYear != null
    }
}
