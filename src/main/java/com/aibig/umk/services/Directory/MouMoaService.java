package com.aibig.umk.services.Directory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aibig.umk.repository.Directory.MouMoaRepository;
import com.aibig.umk.model.Directory.MouMoa;

@Service
public class MouMoaService {
    private final MouMoaRepository mouMoaRepository;

    public MouMoaService(MouMoaRepository mouMoaRepository) {
        this.mouMoaRepository = mouMoaRepository;
    }

    public void saveMouMoa(MouMoa mouMoa) {
        mouMoaRepository.save(mouMoa);
    }

    public MouMoa findByMouMoaId(int mouMoaId) {
        return mouMoaRepository.findById(mouMoaId).orElse(null);
    }

    public void deleteMouMoaById(int mouMoaId) {
        mouMoaRepository.deleteById(mouMoaId);
    }

    public void updateMouMoa(MouMoa mouMoa) {
        mouMoaRepository.save(mouMoa);
    }

    public List<MouMoa> findAllMouMoa() {
        return mouMoaRepository.findAll();
    }

}