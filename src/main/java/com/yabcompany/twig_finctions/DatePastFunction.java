package com.yabcompany.twig_finctions;

import org.jtwig.functions.FunctionRequest;
import org.jtwig.functions.JtwigFunction;
import org.jtwig.functions.SimpleJtwigFunction;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class DatePastFunction implements JtwigFunction {
    private final String LESS_THAN_MINUTE = "Less than 1 minute";
    private final String LESS_THAN_HOUR = "Less than 1 hour";
    private final String LESS_THAN_DAY = "Less than 1 day";

    @Override
    public String name() {
        return "datePast";
    }

    @Override
    public Collection<String> aliases() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public Object execute(FunctionRequest request) {
        String message = "Error: IllegalArgumentException";
        if (request.getNumberOfArguments() == 1) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime date = (LocalDateTime) request.get(0);
            return calculateTime(now, date);
        }
        return message;
    }

    private String calculateTime(LocalDateTime now, LocalDateTime date) {

        if (now.getDayOfYear() - date.getDayOfYear() < 1) {
            if (now.getHour() - date.getHour() < 2) {
                if (now.getMinute() - date.getMinute() < 1) {
                    return LESS_THAN_MINUTE;
                } else {
                    return LESS_THAN_HOUR;
                }
            } else {
                return LESS_THAN_DAY;
            }
        } else {
            return date.toString();
        }
    }
}
