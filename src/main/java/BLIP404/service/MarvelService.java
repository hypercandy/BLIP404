package BLIP404.service;

import BLIP404.entity.Marvel;
import BLIP404.repository.MarvelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarvelService {
    private final MarvelRepository marvelRepository;

    @Autowired
    public MarvelService(MarvelRepository marvelRepository) {
        this.marvelRepository = marvelRepository;
    }

    public Iterable<Marvel> getAllMarvel() {
        return marvelRepository.findAll();
    }

    public Iterable<Marvel> getChronological() {
        return marvelRepository.findChronologically();
    }

    public Iterable<Marvel> getAllUpcoming() {
        return marvelRepository.findAllUpcoming();
    }

    public Marvel getNextRelease() {
        return marvelRepository.findNextRelease();
    }

    public Iterable<Marvel> getRelease() {
        return marvelRepository.findReleaseOrder();
    }

    public Marvel getMarvelById(Long id) {
        return marvelRepository.findById(id).orElse(null);
    }
}
