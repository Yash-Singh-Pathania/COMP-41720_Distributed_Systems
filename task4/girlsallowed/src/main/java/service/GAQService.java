package service.girlsallowed;

import service.core.AbstractQuotationService;
import service.core.ClientInfo;
import service.core.Quotation;

/**
 * Implementation of Quotation Service for Dodgy Geezers Insurance Company
 *
 * @author Rem
 */
public class GAQService extends AbstractQuotationService {
    // All references are to be prefixed with DG (e.g. DG001000)

    public static final String PREFIX = "GA";
    public static final String COMPANY = "Dodgy Geezers Corp.";

    /**
     * Quote generation: 10% discount for smoking, up to 20% discount for high
     * BMI, no penalty for medical issues.
     */
    @Override
    public Quotation generateQuotation(ClientInfo info) {
        // Create an initial quotation between 800 and 1000
        double price = generatePrice(800, 200);

        int discount = bmiDiscount(info);
        if (info.smoker) {
            discount += 10;
        }

        // Generate the quotation and send it back
        return new Quotation(COMPANY, generateReference(PREFIX), (price * (100 - discount)) / 100);
    }

    public int bmiDiscount(ClientInfo info) {
        double bmi = this.bmi(info.weight, info.height);
        if (bmi < 18.5) {
            return 0;
        } else if (bmi < 24.5) {
            return 5;
        } else if (bmi < 30) {
            return 10;
        } else if (bmi < 40) {
            return 15;
        } else {
            return 20;
        }
    }
}
