package com.github.vvhiterussian.restmate.model;

import javax.persistence.*;

@Entity
@Table(name = "ORGANIZER_STATUS_RESPONSES")
public class OrganizerStatusResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private boolean isOrganizerConfirmed;

    @ManyToOne
    @JoinColumn(name = "OBSERVER_ID")
    private User observer;

    @OneToOne
    @JoinColumn(name = "REQUEST_ID")
    private OrganizerStatusRequest request;

    public OrganizerStatusResponse() {
    }

    public OrganizerStatusResponse(boolean isOrganizerConfirmed, User observer, OrganizerStatusRequest request) {
        this.isOrganizerConfirmed = isOrganizerConfirmed;
        this.observer = observer;
        this.request = request;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOrganizerConfirmed() {
        return isOrganizerConfirmed;
    }

    public void setOrganizerConfirmed(boolean organizerConfirmed) {
        isOrganizerConfirmed = organizerConfirmed;
    }

    public User getObserver() {
        return observer;
    }

    public void setObserver(User observer) {
        this.observer = observer;
    }

    public OrganizerStatusRequest getRequest() {
        return request;
    }

    public void setRequest(OrganizerStatusRequest request) {
        this.request = request;
    }
}
