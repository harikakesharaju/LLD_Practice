package com.practice.CarRentalSystem;

import java.time.LocalDateTime;

public class Request {
    private int reqId;
    private User user;
    private int seats;
    private VehicleType type;
    private String loc;
    private LocalDateTime from;
    private LocalDateTime to;
    private Vehicle allottedV;
    private ReqStatus reqstatus;

    public Request(int reqId, User user, int seats, VehicleType type, String loc,
                   LocalDateTime from, LocalDateTime to) {
        this.reqId = reqId;
        this.user = user;
        this.seats = seats;
        this.type = type;
        this.loc = loc;
        this.from = from;
        this.to = to;
        this.reqstatus = ReqStatus.RESERVED;
    }

    public int getReqId() { return reqId; }
    public User getUser() { return user; }
    public int getSeats() { return seats; }
    public VehicleType getType() { return type; }
    public String getLoc() { return loc; }
    public LocalDateTime getFrom() { return from; }
    public LocalDateTime getTo() { return to; }
    public Vehicle getAllottedV() { return allottedV; }
    public void setAllottedV(Vehicle allottedV) { this.allottedV = allottedV; }
    public ReqStatus getReqstatus() { return reqstatus; }
    public void setReqstatus(ReqStatus reqstatus) { this.reqstatus = reqstatus; }
}
