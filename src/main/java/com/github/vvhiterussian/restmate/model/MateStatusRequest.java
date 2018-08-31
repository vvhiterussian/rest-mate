package com.github.vvhiterussian.restmate.model;


import javax.persistence.*;

@Entity
@Table(name = "MATE_STATUS_REQUESTS")
public class MateStatusRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "CANDIDATE_ID")
    private User candidate;

    @OneToOne(mappedBy = "request", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MateStatusResponse response;

    public MateStatusRequest() {
    }

    public MateStatusRequest(Event event, User candidate) {
        this.event = event;
        this.candidate = candidate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getCandidate() {
        return candidate;
    }

    public void setCandidate(User candidate) {
        this.candidate = candidate;
    }

    public MateStatusResponse getResponse() {
        return response;
    }
}
