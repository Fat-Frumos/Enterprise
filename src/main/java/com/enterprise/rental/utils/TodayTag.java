package com.enterprise.rental.utils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A base class for defining new tag handlers implementing Tag.
 *
 * <p> The TagSupport class is a utility class intended to be used as
 * the base class for new tag handlers.  The TagSupport class
 * implements the Tag and IterationTag interfaces and adds additional
 * convenience methods including getter methods for the properties in Tag.
 *
 * @author Pasha Pollack
 * @see TagSupport
 */
public class TodayTag extends TagSupport {
    private String mFormat;

    public void setFormat(String pFormat) {
        mFormat = pFormat;
    }

    /**
     * Default processing of the start tag, returning SKIP_BODY.
     * The current value of the out object (a JspWriter).
     *
     * @return SKIP_BODY
     * @throws JspException if an error occurs while processing this tag
     * @see Tag#doStartTag()
     */
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            Date today = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat(mFormat);
            out.print(dateFormatter.format(today));

        } catch (IOException ioe) {
            throw new JspException("Error: " + ioe.getMessage());
        }
        return SKIP_BODY;
    }

    /**
     * Default processing of the end tag returning SKIP_PAGE.
     *
     * @return SKIP_PAGE
     * @see Tag#doEndTag()
     */
    public int doEndTag() {
        return SKIP_PAGE;
    }
}