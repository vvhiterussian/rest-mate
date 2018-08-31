package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.Event;
import com.github.vvhiterussian.restmate.model.MateStatusRequest;
import com.github.vvhiterussian.restmate.model.User;

import java.util.List;

public interface MateStatusRequestsDAO {
    List<MateStatusRequest> getRequests();
    List<MateStatusRequest> findRequests(Event event, User user, boolean hasResponse);
    void addRequest(MateStatusRequest request);
    void cancelRequest(MateStatusRequest request);
}
