package uth.edu.koi_show_java.Services;

import org.springframework.stereotype.Service;
import uth.edu.koi_show_java.Models.koiShowAccount;
import uth.edu.koi_show_java.Repositories.koiShowAccountRepository;

import java.util.List;

@Service
public class koiShowAccountService {
    private koiShowAccountRepository accountrepository;
    public void create(koiShowAccount a) {
        accountrepository.save(a);
    }
    public koiShowAccount getFindById(int id) {
        return accountrepository.findById(id);
    }
    public void getUpdate(koiShowAccount a) {
        accountrepository.update(a);
    }
    public void delete(koiShowAccount a) {
        accountrepository.delete(a.getId());
    }
    public List<koiShowAccount> getAll() {
        return accountrepository.findAll();
    }
}
