package store.view.input;

import camp.nextstep.edu.missionutils.Console;
import store.common.exception.CustomException;
import store.common.exception.ErrorMessage;

public class PromotionInputView {

    private final Converter converter = new Converter();
    private final Validator validator = new Validator();

    public boolean askWithoutPromotion(String productName, int amount) {
        System.out.printf("%n현재 %s %,d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)%n", productName, amount);
        String answer = Console.readLine();
        validateFormat(answer);
        return converter.toBoolean(answer);
    }

    private void validateFormat(String answer) {
        validator.validateFormat(answer);
    }


    private static class Validator {

        public void validateFormat(String answer) {
            if (!answer.equals("Y") && !answer.equals("N")) {
                throw new CustomException(ErrorMessage.INVALID_FORMAT_ERROR);
            }
        }
    }

    private static class Converter {

        public boolean toBoolean(String answer) {
            return answer.equals("Y");
        }
    }
}
