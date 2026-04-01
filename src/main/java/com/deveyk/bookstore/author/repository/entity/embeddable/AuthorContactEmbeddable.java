package com.deveyk.bookstore.author.repository.entity.embeddable;

import com.deveyk.bookstore.author.model.enums.ContactMethod;
import com.deveyk.bookstore.author.model.enums.ContactType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorContactEmbeddable {

    @Column(name = "EMAIL", length = 200, nullable = false, unique = true)
    private String email;

    @Column(name = "PHONE", length = 20)
    private String phone;

    @Column(name = "WEBSITE_URL", length = 300)
    private String websiteUrl;

    @Column(name = "TWITTER_HANDLE", length = 100)
    private String twitterHandle;

    @Column(name = "INSTAGRAM_HANDLE", length = 100)
    private String instagramHandle;

    @Column(name = "LINKEDIN_URL", length = 300)
    private String linkedinUrl;

    @Column(name = "AGENT_CONTACT", length = 300)
    private String agentContact;

    @Column(name = "PREFERRED_CONTACT_METHOD")
    @Enumerated(EnumType.STRING)
    private ContactMethod preferredContactMethod;

    @Column(name = "IS_AVAILABLE_FOR_EVENTS")
    private Boolean isAvailableForEvents;

    // Helper Methods
    public boolean isContactable() {
        return email != null || phone != null || agentContact != null;
    }

    public boolean hasWebPresence() {
        return websiteUrl != null;
    }

    public boolean hasSocialMediaPresence() {
        return hasTwitter() || instagramHandle != null || linkedinUrl != null;
    }

    public boolean hasTwitter() {
        return twitterHandle != null && !twitterHandle.trim().isEmpty();
    }

    public boolean hasInstagram() {
        return instagramHandle != null && !instagramHandle.trim().isEmpty();
    }

    public boolean hasLinkedIn() {
        return linkedinUrl != null && !linkedinUrl.trim().isEmpty();
    }

    public List<String> getActiveSocialPlatforms() {
        List<String> platforms = new ArrayList<>();
        if (hasTwitter()) platforms.add("Twitter");
        if (hasInstagram()) platforms.add("Instagram");
        if (hasLinkedIn()) platforms.add("LinkedIn");
        return platforms;
    }

    public String getPrimaryContactInfo() {
        if (preferredContactMethod == ContactMethod.EMAIL && email != null) {
            return email;
        }
        if (preferredContactMethod == ContactMethod.PHONE && phone != null) {
            return phone;
        }
        if (preferredContactMethod == ContactMethod.AGENT && agentContact != null) {
            return agentContact;
        }
        
        // Fallback to first available contact
        if (email != null) return email;
        if (phone != null) return phone;
        if (agentContact != null) return agentContact;
        
        return null;
    }

    public boolean isPubliclyReachable() {
        return email != null || websiteUrl != null || hasSocialMediaPresence();
    }

    public ContactType getBestContactType() {
        if (email != null) return ContactType.DIRECT;
        if (agentContact != null) return ContactType.THROUGH_AGENT;
        if (hasSocialMediaPresence()) return ContactType.SOCIAL_MEDIA;
        return ContactType.NOT_AVAILABLE;
    }
}