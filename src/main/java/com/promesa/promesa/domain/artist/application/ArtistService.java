package com.promesa.promesa.domain.artist.application;

import com.promesa.promesa.domain.artist.dao.ArtistRepository;
import com.promesa.promesa.domain.artist.domain.Artist;
import com.promesa.promesa.domain.artist.dto.ArtistResponse;
import com.promesa.promesa.domain.artist.exception.ArtistNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistResponse getArtistProfile(Long artistId){
        // 작가 존재 여부 검증
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(()->ArtistNotFoundException.EXCEPTION);

        return ArtistResponse.from(artist);
    }
}
