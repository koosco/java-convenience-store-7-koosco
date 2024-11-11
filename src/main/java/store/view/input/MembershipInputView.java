package store.view.input;

import camp.nextstep.edu.missionutils.Console;
import store.common.exception.CustomException;
import store.common.exception.ErrorMessage;

public class MembershipInputView {

    private final Converter converter = new Converter();
    private final Validator validator = new Validator();

    public boolean askMembership() {
        System.out.println("\n멤버십 할인을 받으시겠습니까? (Y/N)");
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
