package projectapps.kalkulatorhpp.auth;

import projectapps.kalkulatorhpp.models.UserData;
import projectapps.kalkulatorhpp.storage.StorageService;

//Anggota 5: Keira
public class AuthService {
    private final StorageService storage;

    public AuthService(StorageService storage) {
        this.storage = storage;
    }

    public boolean register(String username, String password) {
        if (storage.userExists(username)) return false;
        UserData ud = new UserData(username, password);
        storage.saveUserData(ud);
        return true;
    }

    public UserData login(String username, String password) {
        UserData ud = storage.loadUserData(username);
        if (ud == null) return null;
        if (ud.password.equals(password)) return ud;
        return null;
    }

    public void persist(UserData ud) {
        storage.saveUserData(ud);
    }
}
