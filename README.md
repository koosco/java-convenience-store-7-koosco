# java-convenience-store-precourse

## 학습목표
- 관련 함수를 묶어 클래스를 만들고, 객체들이 협력하여 하나의 큰 기능을 수행하도록 한다.
- 클래스와 함수에 대한 단위 테스트를 통해 의도한 대로 정확하게 작동하는 영역을 확보한다.
- 3주 차 공통 피드백을 최대한 반영한다.
- 비공개 저장소 과제 진행 가이드를 참고하여 새로운 방식으로 과제 제출물을 제출한다.

### 3주차 공통 피드백
- 함수(메서드) 라인에 대한 기준을 적용한다
- 예외 상황에 대해 고민한다
- 비즈니스 로직과 UI 로직을 분리한다
- 연관성 있는 상수는 static final 대신 enum을 활용한다
- final 키워드를 사용해 값의 변경을 막는다
- 객체의 상태 접근을 제한한다
- 객체는 객체답게 사용한다
- 필드(인스턴스 변수)의 수를 줄이기 위해 노력한다
- 성공하는 케이스뿐만 아니라 예외 케이스도 테스트한다
- 테스트 코드를 작성한다
- 테스트를 위한 코드는 구현 코드와 분리한다
- 단위 테스트하기 어려운 코드를 개선한다
  - private 함수를 테스트하고 싶을 때 클래스 분리를 고려한다

### 3주차 개인 피드백
- 변수명을 좀 더 직관적으로 작성한다
- 검증 로직을 분리하여 별도의 메서드를 선언한다
- 에러 메시지를 따로 관리하는데 빠진 부분이 있어 좀 더 꼼꼼하게 일관성있게 분리한다
- enum을 사용할 때 마지막 요소는 뒤에 ,를 빼고 작성한다
- 변수명에 자료형을 포함하지 않는다. List를 사용할 때는 lottoList보다는 lottos를 사용하는 것이 좋다
- 중복 제거를 할 때는 Set과 List를 이용할 수 있다
- 매직 넘버는 상수로 추출해서 의미를 부여한다
- 입력값으로 정수값을 받는 경우 예외 처리를 꼼꼼히 한다
- supplier를 이용하면 반복되는 while-try-catch 문을 제거할 수 있다
- EOF를 파일에 추가해준다

## 요구사항
### 기능 요구사항
구매자의 할인 혜택과 재고 상황을 고려하여 최종 결제 금액을 계산하고 안내하는 결제 시스템을 구현한다.

#### 전체 시스템
- [x] 사용자에게 상품 목록을 보여준다
  - [x] 상품은 가격명 가격 수량 프로모션을 보여준다
  - [x] 프로모션 상품과 일반 상품이 함께 존재하는 경우 둘을 함께 보여준다
  - [x] 프로모션 상품이 있고 일반 상품은 없는 경우에도 둘을 함께 보여준다
  - [x] 일반 상품만 존재하는 경우 일반 상품만 보여준다
- [ ] 사용자로부터 `[{상품명}-{수량}],[{상품명}-{수량}]` 포맷으로 입력을 받는다
  - 포맷이 다른 경우 `[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.`를 출력한다
  - 수량이 재고를 초과하는 경우 `[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.`를 출력한다
  - 존재하지 않는 상품인 경우 `[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.`를 출력한다
- [ ] 상품 구매를 마치면 멤버십 할인을 적용할지 물어본다
- 영수증을 출력한다
  - 구매한 물품 내역을 출력한다
    - [ ] 구매한 물품 내역에는 `상품명, 수량, 금액`이 포함된다
  - 증정품을 출력한다
    - [ ] 증정품이 있는 경우 상품명, 수량을 출력한다
  - 결제 요약을 출력한다
    - [ ] 총 구매액을 출력한다. `총 구매액 = 상품별 가격 X 수량`으로 계산한다
    - [ ] 프로모션 적용 비용(행사 할인)을 출력한다.
    - [ ] 멤버십 할인 비용을 출력한다
    - [ ] 최종 결제 금액을 출력한다
    - [ ] 영수증의 구성 요소를 보기 좋게 정렬하여 금액과 수량을 확인하게 쉽게한다

#### 재고 관리
- 상품 데이터는 products.md에 관리한다
  - [x] products.md에서 products 데이터를 읽어온다
- 프로모션 데이터는 promotions.md에 관리한다
  - [x] promotions.md에서 promotions 데이터를 읽어온다
- [ ] 상품을 구매한 후에는 재고가 감소해야 한다
- [ ] 상품의 재고가 0개가 되더라도 상품 데이터는 파일에 유지되어야 한다
- [ ] 재고가 차감된 후에는 최신 재고 상태를 유지해야 한다, 다음 고객이 구매할 때 정확한 재고 정보를 제공해야 한다

#### 프로모션 할인
- [ ] 오늘 날짜가 프로모션 기간 내에 포함된 경우에만 할인이 적용된다
- [ ] 프로모션 N개 구매 시 1개 무료 증정 형태로 진행한다
  - [ ] 동일 상품에 대해서는 여러 프로모션이 적용되지 않는다
- [ ] 프로모션 혜택은 프로모션 재고 내에서만 적용할 수 있다
- [ ] 프로모션 기간 중이라면 프로모션 재고를 우선적으로 차감한다
  - [ ] 프로모션 재고가 부족한 경우 일반 재고를 사용한다
- [ ] 프로모션 적용 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 필요한 수량을 추가로 가져오면 혜택을 받을 수 있음을 알린다
- [ ] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제하는 경우, 일부 수량에 대해 정가로 결제하게 됨을 알린다

#### 멤버십 할인
- [ ] 멤버십 할인은 미적용 금액의 30%를 할인받는다
- [ ] 프로모션 적용 후 남은 금액에 대해 멤버십 할인을 적용받는다
- [ ] 프로모션 적용을 하지 않은 경우 멤버십 적용이 가능하다
- [ ] 멤버십 할인 비용은 8천원까지만 적용 가능하다

#### 예외 처리
- [x] 사용자가 잘못된 값을 입력할 경우 IllegalArgumentException를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
    - [x] Exception이 아닌 IllegalArgumentException, IllegalStateException 등과 같은 명확한 유형을 처리한다.

### 입출력 요구사항
#### 입력
- 구현에 필요한 상품 목록과 행사 목록은 파일 입출력을 통해 불러온다
  - `src/main/resources/products.md`와 `src/main/resources/promotion.md` 파일을 이용한다
  - 두 파일 모두 내용 형식을 유지한다면 값을 수정할 수 있다
- 구매할 상품과 수량을 입력 받는다
  - 상품명, 수량은 `-`, 개별 상품은 `[]`로 묶어 `,`로 구분한다
```
[콜라-10],[사이다-3]
```

- 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 수량만큼 추가 여부를 입력받는다
  - Y : 증정 받을 수 있는 상품을 추가한다
  - N : 증정 받을 수 있는 상품을 추가한다
  - 잘못된 입력을 받는 경우 `[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.`를 출력한다
```
Y
```

- 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 묻는다
  - Y : 일부 수량에 대해 정가로 결제한다
  - N : 정가로 결제해야하는 수량만큼 제외한 후 결제를 진행한다
  - 잘못된 입력을 받는 경우 `[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.`를 출력한다
```
Y
```

- 멤버십 할인 적용 여부를 입력 받는다
  - Y : 멤버십 할인을 적용한다
  - N : 멤버십 할인을 적용하지 않는다
  - 잘못된 입력을 받는 경우 `[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.`를 출력한다
```
Y
```

#### 출력
- [x] 환영 인사를 출력한다
- [x] 상품명, 가격, 프로모션 이름, 재고를 안내한다
- [x] 재고가 0개라면 `재고 없음`을 출력한다
```
안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 10개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 5개
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
```

- [ ] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았다면, 혜택에 대한 안내 메시지를 출력한다
```
현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
```

- [ ] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부에 대한 안내 메시지를 출력한다
```
현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
```

- [ ] 멤버십 할인 적용 여부를 확인하기 위해 안내 문구를 출력한다
```
멤버십 할인을 받으시겠습니까? (Y/N)
```

- [ ] 구매 상품 내역, 증정 상품 내역, 금액 정보를 출력한다
```
==============W 편의점================
상품명		수량	금액
콜라		3 	3,000
에너지바 		5 	10,000
=============증	정===============
콜라		1
====================================
총구매액		8	13,000
행사할인			-1,000
멤버십할인			-3,000
내실돈			 9,000
```

- 추가 구매 여부를 확인하기 위해 안내 문구를 출력한다
```
감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
```

### 프로그래밍 요구사항
- JDK 21버전에서 실행 가능해야 한다
- 프로그램 실행 시작점은 Application의 main()이다
- build.gradle은 변경할 수 없으며, 외부 라이브러리를 사용할 수 없다
- 프로그램 종료 시 System.exit()를 호출하지 않는다
- 프로그래밍 요구 사항에 달리 명시하지 않는 한 파일, 패키지 등의 이름을 바꾸거나 변경하지 않는다
- 자바 코드 컨벤션을 지키며 프로그래밍한다
- indent는 2까지만 허용한다
- 3항 연산자를 사용하지 않는다
- 함수는 단일 책임 원칙을 가진다
- Junit5와 AssertJ를 이용해 정리한 기능 목록이 정상 작동하는지 테스트한다
- else를 사용하지 않는다
  - switch/case 또한 사용하지 않는다
- enum을 이용한다
- 구현한 기능에 대한 단위 테스트를 작성한다
  - UI 로직은 제외한다
- 함수의 길이가 10라인을 넘기지 않도록 구현한다
  - 함수가 한 가지 일만 잘하도록 구현한다
- 입출력을 담당하는 클래스를 별도로 관리한다
