package com.senac.aponte.dto.response.match;

import com.senac.aponte.dto.response.profile.ProfileResponseDTO;
import java.time.LocalDateTime;

public class MatchResponseDTO {
    private Integer id;
    private LocalDateTime expiresAt;
    private ProfileResponseDTO matchedProfile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public ProfileResponseDTO getMatchedProfile() {
        return matchedProfile;
    }

    public void setMatchedProfile(ProfileResponseDTO matchedProfile) {
        this.matchedProfile = matchedProfile;
    }
}
