package BLIP404.service;

import BLIP404.entity.Marvel;
import BLIP404.repository.MarvelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Marvel> getChronological(Pageable page) {
        final Page<Marvel> result = marvelRepository.findChronologically(page);
        if (result == null || result.isEmpty()) {
            return Page.empty(page);
        }
        return result;
    }

    public Iterable<Marvel> getAllUpcoming() {
        return marvelRepository.findAllUpcoming();
    }

    public Marvel getNextRelease() {
        return marvelRepository.findNextRelease();
    }

    public Page<Marvel> getRelease(Pageable page) {
        final Page<Marvel> result = marvelRepository.findReleaseOrder(page);
        if (result == null || result.isEmpty()) {
            return Page.empty(page);
        }
        return result;
    }

    public Marvel getMarvelById(Long id) {
        return marvelRepository.findById(id).orElse(null);
    }
}
