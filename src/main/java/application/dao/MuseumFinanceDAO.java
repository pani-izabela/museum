package application.dao;

import application.model.MuseumFinance;

public interface MuseumFinanceDAO {
    MuseumFinance getFinanceByKey(String key);
    MuseumFinance addAmount(MuseumFinance museumFinance);
    MuseumFinance updateAmount(MuseumFinance museumFinance, float amount);
}
