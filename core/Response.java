package com.metlife.investments.cohesion.core;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.core.Response.Status.Family;

/** Response extends EntityMessage and is used to represent an abstract interface
 * to the response that comes back from request to a Cohesion service
 */
public interface Response extends EntityMessage
{
    /** gets the integer valued status of the response
     * 
     * @return the response status
     */
    int getStatus();
    
    /** gets the detailed status info for the response
     * 
     * @return the detailed status type information
     */
    javax.ws.rs.core.Response.StatusType getStatusInfo();
        
    /** predicate to determine if the response contains an entity
     * 
     * @return true if it contains an entity
     */
    boolean hasEntity();
    
    /** get the media type of the response
     * 
     * @return media type
     */
    String getMediaType();
    
    /** gets the length of the response
     * 
     * @return length of response
     */
    int getLength();
    
    /**
     * Get message date.
     *
     * @return the message date, otherwise null if not present
     */
    public abstract Date getDate();

    /**
     * Get the last modified date.
     *
     * @return the last modified date, otherwise null if not present
     */
    public abstract Date getLastModified();
    
    /**
     * Get view of the response headers and their string values.
     *
     * @return response headers as a string view of header values.
     */
    public abstract MultivaluedMap<String, String> getStringHeaders();

    /**
     * Get a message header as a single string value.
     *
     * @param name the message header.
     * @return the message header value. 
     */
    public abstract String getHeaderString(String name);
    
//    public interface StatusType {
//
//        /**
//         * Get the associated status code.
//         *
//         * @return the status code.
//         */
//        public int getStatusCode();
//
//        /**
//         * Get the class of status code.
//         *
//         * @return the class of status code.
//         */
//        public Status.Family getFamily();
//
//        /**
//         * Get the reason phrase.
//         *
//         * @return the reason phrase.
//         */
//        public String getReasonPhrase();
//    }

    /**
     * Commonly used status codes defined by HTTP, 
     */
    public enum Status implements  javax.ws.rs.core.Response.StatusType {

        OK(200, "OK"),
        CREATED(201, "Created"),
        ACCEPTED(202, "Accepted"),
        NO_CONTENT(204, "No Content"),
        RESET_CONTENT(205, "Reset Content"),
        PARTIAL_CONTENT(206, "Partial Content"),
        MOVED_PERMANENTLY(301, "Moved Permanently"),
        FOUND(302, "Found"),
        SEE_OTHER(303, "See Other"),
        NOT_MODIFIED(304, "Not Modified"),
        USE_PROXY(305, "Use Proxy"),
        TEMPORARY_REDIRECT(307, "Temporary Redirect"),
        BAD_REQUEST(400, "Bad Request"),
        UNAUTHORIZED(401, "Unauthorized"),
        PAYMENT_REQUIRED(402, "Payment Required"),
        FORBIDDEN(403, "Forbidden"),
        NOT_FOUND(404, "Not Found"),
        METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
        NOT_ACCEPTABLE(406, "Not Acceptable"),
        PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
        REQUEST_TIMEOUT(408, "Request Timeout"),
        CONFLICT(409, "Conflict"),
        GONE(410, "Gone"),
        LENGTH_REQUIRED(411, "Length Required"),
        PRECONDITION_FAILED(412, "Precondition Failed"),
        REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
        REQUEST_URI_TOO_LONG(414, "Request-URI Too Long"),
        UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
        REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable"),
        EXPECTATION_FAILED(417, "Expectation Failed"),
        INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
        NOT_IMPLEMENTED(501, "Not Implemented"),
        BAD_GATEWAY(502, "Bad Gateway"),
        SERVICE_UNAVAILABLE(503, "Service Unavailable"),
        GATEWAY_TIMEOUT(504, "Gateway Timeout"),
        HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported");

        private final int code;
        private final String reason;
        private final javax.ws.rs.core.Response.Status.Family family;

        Status(final int statusCode, final String reasonPhrase) 
        {
            this.code = statusCode;
            this.reason = reasonPhrase;
            this.family = Family.familyOf(statusCode);
        }

        /**
         * Get the class of status code.
         *
         * @return the class of status code.
         */
        @Override
        public Family getFamily() 
        {
            return family;
        }

        /**
         * Get the associated status code.
         *
         * @return the status code.
         */
        @Override
        public int getStatusCode() 
        {
            return code;
        }

        /**
         * Get the reason phrase.
         *
         * @return the reason phrase.
         */
        @Override
        public String getReasonPhrase() 
        {
            return toString();
        }

        /**
         * Get the reason phrase.
         *
         * @return the reason phrase.
         */
        @Override
        public String toString() 
        {
            return reason;
        }

        /**
         * Convert a numerical status code into the corresponding Status.
         *
         * @param statusCode the numerical status code.
         * @return the matching Status or null is no matching Status is defined.
         */
        public static Status fromStatusCode(final int statusCode) {
            for (Status s : Status.values()) {
                if (s.code == statusCode) {
                    return s;
                }
            }
            return null;
        }
    }
}
