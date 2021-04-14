package com.epam.jwd.hardziyevich.hr.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;

public class DateTimeTag extends TagSupport {
    private LocalDateTime localDateTime;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String dateTimeString = Dates.formatLocalDateTime(localDateTime);
            pageContext.getOut().write(dateTimeString);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}