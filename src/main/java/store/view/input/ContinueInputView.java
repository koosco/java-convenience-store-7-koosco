package store.view.input;

import camp.nextstep.edu.missionutils.Console;
import store.common.exception.CustomException;
import store.common.exception.ErrorMessage;

public class ContinueInputView {

    private final Converter converter = new Converter();
    private final Validator validator = new Validator();

    public boolean askContinue() {
        System.out.println("\n감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        String answer = Console.readLine();
        validator.validateFormat(answer);
        return converter.toBoolean(answer);
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
