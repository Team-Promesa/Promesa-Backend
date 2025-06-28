package com.promesa.promesa.domain.artist.api;

import com.promesa.promesa.domain.artist.application.ArtistService;
import com.promesa.promesa.domain.artist.dao.ArtistRepository;
import com.promesa.promesa.domain.artist.dto.ArtistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping("/{artistId}")
    public ResponseEntity<ArtistResponse> getArtistProfile(@PathVariable Long artistId,@AuthenticationPrincipal OAuth2User user) {
        return ResponseEntity.ok(artistService.getArtistProfile(artistId,user));
    }
}
