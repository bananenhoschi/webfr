package ch.fhnw.webfr.flashcard.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.StrSubstitutor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Slf4j
public class TranslationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.debug("TranslationFilter doFilter!");

        ServletResponse newResponse = response;

        if (request instanceof HttpServletRequest) {
            newResponse = new CharResponseWrapper((HttpServletResponse) response);
        }

        chain.doFilter(request, newResponse);

        if (newResponse instanceof CharResponseWrapper) {
            String text = newResponse.toString();
            log.debug(text);
            if (text != null) {
                Locale currentLocale = Locale.forLanguageTag(request.getServletContext().getInitParameter("lang"));
                log.info("Language: {}", currentLocale);
                ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", currentLocale);

                Map<String, String> texts = resourceBundle.keySet().stream().collect(Collectors.toMap(k -> k, resourceBundle::getString));
                String translatedText = StrSubstitutor.replace(text, texts, "{", "}");
                log.debug(translatedText);
                response.getWriter().write(translatedText);
                response.setContentLength(translatedText.length());
            }
        }

    }


    @Override
    public void init(FilterConfig fConfig) {
        log.debug("TranslationFilter init!");
    }

    @Override
    public void destroy() {
        log.debug("TranslationFilter destroy!");
    }

    class CharResponseWrapper extends HttpServletResponseWrapper {
        protected CharArrayWriter charWriter;

        protected PrintWriter writer;

        protected boolean getOutputStreamCalled;

        protected boolean getWriterCalled;

        public CharResponseWrapper(HttpServletResponse response) {
            super(response);

            charWriter = new CharArrayWriter();
        }

        public ServletOutputStream getOutputStream() throws IOException {
            if (getWriterCalled) {
                throw new IllegalStateException("getWriter already called");
            }

            getOutputStreamCalled = true;
            return super.getOutputStream();
        }

        public PrintWriter getWriter() throws IOException {
            if (writer != null) {
                return writer;
            }
            if (getOutputStreamCalled) {
                throw new IllegalStateException("getOutputStream already called");
            }
            getWriterCalled = true;
            writer = new PrintWriter(charWriter);
            return writer;
        }

        public String toString() {
            String s = null;

            if (writer != null) {
                s = charWriter.toString();
            }
            return s;
        }
    }

}
