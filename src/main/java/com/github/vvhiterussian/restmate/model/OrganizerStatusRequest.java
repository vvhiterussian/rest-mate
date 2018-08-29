package com.github.vvhiterussian.restmate.model;

import javax.persistence.*;

@Entity
@Table(name = "ORGANIZER_STATUS_REQUESTS")
public class OrganizerStatusRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID")
    private User candidate;

    @OneToOne(mappedBy = "request", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private OrganizerStatusResponse response;

    public OrganizerStatusRequest() {
    }

    public OrganizerStatusRequest(User candidate) {
        this.candidate = candidate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCandidate() {
        return candidate;
    }

    public void setCandidate(User candidate) {
        this.candidate = candidate;
    }

    public OrganizerStatusResponse getResponse() {
        return response;
    }
}
