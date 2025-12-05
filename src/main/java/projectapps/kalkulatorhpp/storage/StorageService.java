package projectapps.kalkulatorhpp.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import projectapps.kalkulatorhpp.models.UserData;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

//Anggota 6
public class StorageService {
    private static final String DATA_DIR = "data";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public StorageService() {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
        } catch (Exception e) {
            System.out.println("Gagal membuat folder data: " + e.getMessage());
        }
    }

    public boolean userExists(String username) {
        return new File(getFilePath(username)).exists();
    }

    public void saveUserData(UserData ud) {
        try (FileWriter fw = new FileWriter(getFilePath(ud.username))) {
            gson.toJson(ud, fw);
        } catch (Exception e) {
            System.out.println("Gagal menyimpan: " + e.getMessage());
        }
    }

    public UserData loadUserData(String username) {
        File f = new File(getFilePath(username));
        if (!f.exists()) return null;
        try (FileReader fr = new FileReader(f)) {
            return gson.fromJson(fr, UserData.class);
        } catch (Exception e) {
            System.out.println("Gagal load user: " + e.getMessage());
            return null;
        }
    }

    private String getFilePath(String username) {
        return DATA_DIR + File.separator + username + ".json";
    }
}
