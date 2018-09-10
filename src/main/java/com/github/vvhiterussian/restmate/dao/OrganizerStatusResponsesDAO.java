package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.OrganizerStatusResponse;

import java.util.List;

public interface OrganizerStatusResponsesDAO {
    List<OrganizerStatusResponse> getResponses(); // for test
    void addResponse(OrganizerStatusResponse response);
}
