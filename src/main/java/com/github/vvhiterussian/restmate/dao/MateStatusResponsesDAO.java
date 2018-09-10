package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.MateStatusResponse;

import java.util.List;

public interface MateStatusResponsesDAO {
    List<MateStatusResponse> getResponses(); // for test
    void addResponse(MateStatusResponse response);
}
