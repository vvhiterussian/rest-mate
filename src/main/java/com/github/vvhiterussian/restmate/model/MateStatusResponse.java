package com.github.vvhiterussian.restmate.model;

import javax.persistence.*;

@Entity
@Table(name = "MATE_STATUS_RESPONSES")
public class MateStatusResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private boolean isMateConfirmed;

    @ManyToOne
    @JoinColumn(name = "ORGANIZER_ID")
    private User organizer;

    @OneToOne
    @JoinColumn(name = "REQUEST_ID")
    private MateStatusRequest request;

    public MateStatusResponse() {
    }

    public MateStatusResponse(boolean isMateConfirmed, User organizer, MateStatusRequest request) {
        this.isMateConfirmed = isMateConfirmed;
        this.organizer = organizer;
        this.request = request;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMateConfirmed() {
        return isMateConfirmed;
    }

    public void setMateConfirmed(boolean mateConfirmed) {
        isMateConfirmed = mateConfirmed;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public MateStatusRequest getRequest() {
        return request;
    }

    public void setRequest(MateStatusRequest request) {
        this.request = request;
    }
}
