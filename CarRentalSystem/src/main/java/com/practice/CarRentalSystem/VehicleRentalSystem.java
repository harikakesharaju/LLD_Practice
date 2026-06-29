package com.practice.CarRentalSystem;

import java.util.ArrayList;
import java.util.List;

public class VehicleRentalSystem {
    private List<User> users = new ArrayList<>();
    private List<Store> stores = new ArrayList<>();

    public void addUser(User u) { users.add(u); }
    public void addStore(Store s) { stores.add(s); }
    public List<User> getUsers() { return users; }
    public List<Store> getStores() { return stores; }
}
