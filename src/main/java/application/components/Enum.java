package application.components;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class Enum {
    public final static String NOT_UNIQUE_MAIL = "Użytkownik o tym adresie email istnieje już w systemie";
    public final static String WRONG_PASS = "Hasło powinno zawierać przynajmniej dwie duże litery oraz jeden znak specjalny." +
            "\nNie może zawierać nazwiska.";
    public final static String CHANGE_PASS = "Hasło zostało zmienione";
    public final static String ERROR_CHANGE_PASS = "Nie udało się zmienić hasła. Sprawdź wprowadzane dane";
}
