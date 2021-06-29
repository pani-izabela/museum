package application.dao;

import application.model.Reserve;

public interface ReserveDAO {
    Reserve getReserveByKey(String key);
    Reserve updateReserve(Reserve reserve, float amount);
}
