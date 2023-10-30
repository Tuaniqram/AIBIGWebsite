package com.aibig.umk.services.Directory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aibig.umk.model.Directory.Grantt;
import com.aibig.umk.repository.Directory.GranttRepository;

@Service
public class GranttService {
    private final GranttRepository granttRepository;

    public GranttService(GranttRepository granttRepository) {
        this.granttRepository = granttRepository;
    }

    public void saveGrantt(Grantt grantt) {
        granttRepository.save(grantt);
    }

    public Grantt getGranttByGranttId(int granttId) {
        return granttRepository.findById(granttId).orElse(null);
    }

    public void deleteGranttByGranttId(int granttId) {
        granttRepository.deleteById(granttId);
    }

    public Grantt getGranttByGranttName(String granttName) {
        return granttRepository.findByGranttName(granttName);
    }

    public List<Grantt> getAllGrantts() {
        return granttRepository.findAll();
    }

}
