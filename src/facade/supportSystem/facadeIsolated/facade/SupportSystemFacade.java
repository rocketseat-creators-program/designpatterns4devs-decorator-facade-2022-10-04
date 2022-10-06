package facade.supportSystem.facadeIsolated.facade;

import facade.supportSystem.facadeIsolated.model.Card;
import facade.supportSystem.facadeIsolated.service.CardService;
import facade.supportSystem.facadeIsolated.service.PaymentService;
import facade.supportSystem.facadeIsolated.service.RegistrationService;
import facade.supportSystem.facadeIsolated.service.ReportService;

public class SupportSystemFacade {

    CardService cardService;
    RegistrationService registrationService;
    ReportService reportService;
    PaymentService paymentService;

    public SupportSystemFacade() {
        cardService = new CardService();
        registrationService = new RegistrationService();
        reportService = new ReportService(registrationService);
        paymentService = new PaymentService(registrationService);
    }

    public Long getCardByUser(Long l) {
        Card card = cardService.getCardByUser(l);
        return card.getCardNumber();
    }

    public void getSumary(Long cardNumber) {
        reportService.getSumary(new Card(null, cardNumber));
    }

    public void getPaymentInfoByCard(Long cardNumber) {
        paymentService.getPaymentInfoByCard(new Card(null, cardNumber));
    }

}
