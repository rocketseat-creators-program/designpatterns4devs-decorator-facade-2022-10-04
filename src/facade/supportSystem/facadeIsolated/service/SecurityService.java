package facade.supportSystem.facadeIsolated.service;


import facade.supportSystem.facadeIsolated.model.Card;
import facade.supportSystem.facadeIsolated.model.Registration;

import java.util.List;

public class SecurityService {

    private CardService cardService;
    private RegistrationService registrationService;

    public SecurityService(CardService cardService, RegistrationService registrationService) {
        this.cardService = cardService;
        this.registrationService = registrationService;
    }

    public List<Registration> blockCard(Card card) {
        System.out.println("Blocking card:" + card);
        List<Registration> pendingRegistries = registrationService.getRegistersByCard(card);
        cardService.removeCard(card);
        registrationService.deleteCardRegistries(card);
        return pendingRegistries;
    }
}
