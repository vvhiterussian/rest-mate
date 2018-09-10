package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.OrganizerStatusRequest;
import com.github.vvhiterussian.restmate.model.User;

import java.util.List;

public interface OrganizerStatusRequestsDAO {
    List<OrganizerStatusRequest> getRequests();
    List<OrganizerStatusRequest> findRequests(User candidate, boolean hasResponse);
    void addRequest(OrganizerStatusRequest request);
    void cancelRequest(OrganizerStatusRequest request);
}
