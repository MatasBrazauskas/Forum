package com.example.demo.Middleware;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@Setter
@Getter
public class FixedWindowCounter
{
    @Data
    @AllArgsConstructor
    private class RequestCounter
    {
        public long timestamp;
        public int count;
    }

    private final int maxRequestsGuest = 2;
    private final int maxRequestUser = 4;

    private final Map<String, RequestCounter> requestCounters = new ConcurrentHashMap<>();

    public boolean isAllowed(String uuid, String role)
    {
        final int maxRequests = "GUEST".equals(role) ? this.maxRequestsGuest : this.maxRequestUser;
        final long timestamp = System.currentTimeMillis() / 1000;

        final RequestCounter newCounter = requestCounters.compute( uuid, (key, counter) -> {
            if(counter == null || counter.timestamp != timestamp)
            {
                return new RequestCounter(timestamp, 1);
            }
            counter.count++;
            return counter;
        });

        return newCounter.count <= maxRequests;
    }
}
