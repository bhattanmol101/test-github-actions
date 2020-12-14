package com.aeradron.enigma;

import com.aeradron.enigma.service.utility.Constants;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LoggerDispatcherServlet extends DispatcherServlet {

    private static final long serialVersionUID = 1L;

    protected void doDispatch(HttpServletRequest request, HttpServletResponse response){
        try {
            if (!(request instanceof ContentCachingRequestWrapper)) {
                request = new ContentCachingRequestWrapper(request);
            }
            if (!(response instanceof ContentCachingResponseWrapper)) {
                response = new ContentCachingResponseWrapper(response);
            }
            HandlerExecutionChain handler = getHandler(request);
            try {
                super.doDispatch(request, response);
            }
            finally {
                String requestURL = request.getRequestURL().toString();
                if(requestURL.contains(Constants.ENIGMA_URL)) {
                    log(request, response, handler);
                }
                updateResponse(response);
            }
        } catch (Exception e){
            logger.error("Error occurred in LoggingDispatcherServlet: " + e, e);
        }
    }

    private void log(HttpServletRequest requestToLog,
                     HttpServletResponse responseToLog, HandlerExecutionChain handler)
            throws IOException {
        try {
            if (responseToLog != null) {
                LogMessage log = new LogMessage();
                log.setHttpStatus(responseToLog.getStatus());
                log.setHttpMethod(requestToLog.getMethod());
                log.setHttpPath(requestToLog.getRequestURI());
                log.setHttpRequestBody(getRequestBody(requestToLog));
                log.setJavaMethod(handler.toString());
                log.setResponse(getResponsePayload(responseToLog));
                logger.info("Http Request/Response log: " + log);
            }
        } catch (Exception e) {
            // eat the exception
        }
    }

    private String getRequestBody(HttpServletRequest request) {
        try {
            if (request instanceof ContentCachingRequestWrapper) {
                byte[] byteArray = ((ContentCachingRequestWrapper) request)
                        .getContentAsByteArray();
                if (byteArray != null) {
                    return new String(byteArray);
                }
            }
        } catch (Exception exception) {

        }
        return "";
    }

    /**
     * Returns the body of the HTTP response
     *
     * @param response
     * @return
     */
    private String getResponsePayload(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response,
                ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = Math.min(buf.length, 5120);
                try {
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    // eat the exception
                }
            }
        }
        return "[unknown]";
    }

    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper = WebUtils
                .getNativeResponse(response, ContentCachingResponseWrapper.class);
        assert responseWrapper != null;
        responseWrapper.copyBodyToResponse();
    }

    static class LogMessage {

        private String httpMethod;
        private String httpHeader;
        private String httpPath;
        private String httpRequestBody;
        private String javaMethod;
        private int httpStatus;
        private String response;

        public String getHttpMethod() {
            return httpMethod;
        }

        public void setHttpMethod(String httpMethod) {
            this.httpMethod = httpMethod;
        }

        public String getHttpHeader() {
            return httpHeader;
        }

        public void setHttpHeader(String httpHeader) {
            this.httpHeader = httpHeader;
        }

        public String getHttpPath() {
            return httpPath;
        }

        public void setHttpPath(String httpPath) {
            this.httpPath = httpPath;
        }

        public String getHttpRequestBody() {
            return httpRequestBody;
        }

        public void setHttpRequestBody(String httpRequestBody) {
            this.httpRequestBody = httpRequestBody;
        }

        public String getJavaMethod() {
            return javaMethod;
        }

        public void setJavaMethod(String javaMethod) {
            this.javaMethod = javaMethod;
        }

        public int getHttpStatus() {
            return httpStatus;
        }

        public void setHttpStatus(int httpStatus) {
            this.httpStatus = httpStatus;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        @Override
        public String toString() {
            return "{ " +
                    "httpMethod='" + httpMethod + '\'' +
                    ", httpHeader='" + httpHeader + '\'' +
                    ", httpPath='" + httpPath + '\'' +
                    ", httpRequestBody='" + httpRequestBody + '\'' +
                    ", javaMethod='" + javaMethod + '\'' +
                    ", httpStatus=" + httpStatus +
                    ", response='" + response + '\'' +
                    '}';
        }
    }
}
