package MoveMe;

import java.sql.Time;

public class Request {
    Address pickUpAdd;
    Address dropOffAdd;
    Rider passenger;
    Driver driver;
    double cost;
    Time ETA;
    String reqStatus;
    public Request(Address p, Address d, Rider r){
        pickUpAdd = p;
        dropOffAdd = d;
        passenger = r;
        cost = calcCost(p, d);
        ETA = calcETA(p, d);
    }
    private double calcCost (Address p, Address d){
        return 0;
    }
    //ignore nearby okokok
    private Time calcETA(Address p, Address d){
        Time time = new Time(3,3,3);
        return time;
    }
}
