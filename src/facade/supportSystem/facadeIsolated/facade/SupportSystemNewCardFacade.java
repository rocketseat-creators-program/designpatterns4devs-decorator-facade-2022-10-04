package facade.supportSystem.facadeIsolated.facade;

import facade.supportSystem.facadeIsolated.model.Card;
import facade.supportSystem.facadeIsolated.model.Registration;
import facade.supportSystem.facadeIsolated.service.*;

import java.util.List;

public class SupportSystemNewCardFacade {

    CardService cardService;
    RegistrationService registrationService;
    ReportService reportService;
    SecurityService securityService;


    public SupportSystemNewCardFacade() {
        cardService = new CardService();
        registrationService = new RegistrationService();
        reportService = new ReportService(registrationService);
        securityService = new SecurityService(cardService, registrationService);
    }

    public void cancelLastRegister(Long user) {
        Card card = cardService.getCardByUser(user);
        List<Registration> registers = registrationService.getRegistersByCard(card);
        registrationService.removeByIndex(card, registers.size() - 1);
        List<Registration> pendingRegisters = securityService.blockCard(card);
        Card newCard = cardService.createNewCard(123456L, 33445566L);
        registrationService.addCardRegisters(newCard, pendingRegisters);
        reportService.getSumary(newCard);
    }

    public void orderNewCard(Long user) {
        Card card = cardService.getCardByUser(user);
        List<Registration> pendingRegisters = securityService.blockCard(card);
        Card newCard = cardService.createNewCard(123456L, 99887766L);
        registrationService.addCardRegisters(newCard, pendingRegisters);
        reportService.getSumary(newCard);
    }
}
