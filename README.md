# 좌표계산기

- 회고
- 프로그래밍 요구사항
- 기능 요구 사항
- 객체 설계
- 기능 목록

## 회고 <a name ="retrospective"></a>

### 순수한 Java를 이용해 요구 사항들을 구현하여 객체지향 프로그래밍을 수련합니다

- [숫자야구게임 회고](https://github.com/eastshine-high/java-baseball-playground#retrospective)

### 상속에 대한 올바른 이해를 통해 다형성을 사용합니다 <a name ="polymorphism"></a>

좌표 계산기는 상속과 인터페이스를 활용하는 미션입니다. 처음 이 미션의 요구 사항을 설계하고 구현하고자 할 때는, OOP에서의 상속의 의미를 정확히 이해하지 못한 상태였기 때문에 설계와 구현에 확신을 가질 수 없었습니다.

이 불확실함은 조영호님의 저서 [오브젝트](https://github.com/eastshine-high/til/tree/main/books/object) 를 통해 해소할 수 있었습니다.

> 상속의 목적은 코드 재사용이 아니다. 상속은 **타입 계층**을 구조화하기 위해 사용해야 한다. 타입 계층은 객체지향 프로그래밍의 중요한 특성 중 하나인 다형성의 기반을 제공한다.
>

책 오브젝트의 13장 [서브클래싱과 서브타이핑](https://github.com/eastshine-high/til/blob/main/books/object/subclassing-subtyping.md) 에서는 **타입 계층**에 대해 이해할 수 있었습니다. 
타입 계층을 간단히 요약하면, 'is-a 관계와 행동 호환성이라는 조건을 만족하는 객체들의 포함 관계'입니다. 
설계 원칙 중의 하나인 리스코프 치환 원칙 또한 행동 호환성의 관점에서 이해해 볼 수 있습니다.
이를 통해 자바의 인터페이스는 [행동 호환성](https://github.com/eastshine-high/til/blob/main/books/object/subclassing-subtyping.md#%ED%96%89%EB%8F%99-%ED%98%B8%ED%99%98%EC%84%B1) 의 관점으로, 상속은 [서브 타입](https://github.com/eastshine-high/til/blob/main/books/object/subclassing-subtyping.md#%EC%84%9C%EB%B8%8C%ED%81%B4%EB%9E%98%EC%8B%B1%EA%B3%BC-%EC%84%9C%EB%B8%8C%ED%83%80%EC%9D%B4%ED%95%91-1) 의 관점으로 이해하고 미션 수행에 활용해 볼 수 있었습니다.

또한 오브젝트 12장 [다형성](https://github.com/eastshine-high/til/blob/main/books/object/polymorphism.md) 에서는 상속의 관점에서 다형성이 구현되는 기술적인 메커니즘을 이해해 볼 수 있었습니다.
이를 통해 다형성이 런타임에 메시지를 처리하기에 적합한 메서드를 동적으로 탐색하는 과정을 통해 구현되며, 상속이 이런 메서드를 찾기 위한 일종의 탐색 경로를 클래스 계층의 형태로 구현하기 위한 구현하기 위한 방법이라는 것을 이해할 수 있었습니다.



### 모던 Java(함수형 프로그래밍, 스트림, 람다)의 사용을 숙달합니다 <a name ="modern-java"></a>

좌표계산기와 숫자야구게임 미션의 [프로그래밍 요구사항](#programming-spec) 을 지키는 과정을 통해서는 모던 Java의 사용을 숙달해 볼 수 있었습니다.

예를 들어, 요구 사항의 조항 중에는 “indent(인덴트, 들여쓰기) depth를 2가 넘지 않도록 구현한다”는 항목이 있습니다. 
그런데 컬렉션 값들에 대한 유효성 검사는 이 조항으로 인해 `for` 문과 `if` 문을 동시에 사용하여 검사할 수 없었습니다. 
이 때, **스트림**을 활용하여 프로그래밍 요구사항은 준수하면서 컬렉션 값들에 대한 유효성 검사를 수행하였습니다.

```java
private void validateRange(List<Integer> baseballs) {
    if(baseballs.stream().anyMatch(
            number -> number < LOWER_LIMIT || number > UPPER_LIMIT)
    ){
        throw new IllegalArgumentException(ERROR_NUMBER_RANGE);
    }
}
```

또한 요구 사항 조항 중에 “`else` 예약어와 `switch`/`case` 문을 사용하지 않는다”는 항목으로 인해, 조건에 따라 다형 객체를 생성해야 하는 팩토리에서도 `if`문을 사용하지 않는 방법으로 다형 객체를 생성해야 했습니다. 
이 때는 Map과 **함수형 인터페이스**를 활용하여 기능을 구현해 볼 수 있었습니다.

```java
public class FigureFactory {
    private static final Map<Integer, Function<List<Point>, AbstractFigure>> operators = new HashMap<>();

    public static final int NUMBER_OF_POINTS_OF_LINE = 2;
    public static final int NUMBER_OF_POINTS_OF_TRIANGLE = 3;
    public static final int NUMBER_OF_POINTS_OF_RECTANGLE = 4;

    static {
        operators.put(NUMBER_OF_POINTS_OF_LINE, Line::new);
        operators.put(NUMBER_OF_POINTS_OF_TRIANGLE, Triangle::new);
        operators.put(NUMBER_OF_POINTS_OF_RECTANGLE, Rectangle::new);
    }

    public static AbstractFigure create(List<Point> points) {
        return operators.get(points.size()).apply(points);
    }
}
```


## 프로그래밍 요구사항 <a name ="programming-spec"></a>

- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    - 기본적으로 [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)을 원칙으로 한다.
    - 단, 들여쓰기는 '2 spaces'가 아닌 '4 spaces'로 한다.
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- else 예약어를 쓰지 않는다.
    - else 예약어를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
    - 힌트: if문에서 값을 반환하는 방식으로 구현하면 else 예약어를 사용하지 않아도 된다.
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
    - 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
    - UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
- 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
    - 함수(또는 메소드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- 모든 원시 값과 문자열을 포장한다.
- 일급 컬렉션을 쓴다.
- 줄여 쓰지 않는다(축약 금지).
- 예외 처리를 통해 프로그램이 중단 없이 진행되도록 한다.

## 기능 요구사항

### 1. 좌표계산기(선 길이)

- 사용자가 점에 대한 좌표 정보를 입력하는 메뉴를 구성한다.
- 좌표 정보는 괄호"(", ")"로 둘러쌓여 있으며 쉼표(,)로 x값과 y값을 구분한다.
- X, Y좌표 모두 최대 24까지만 입력할 수 있다.
- 입력 범위를 초과할 경우 에러 문구를 출력하고 다시 입력을 받는다.
- 정상적인 좌표값을 입력한 경우, 해당 좌표에 특수문자를 표시한다.
- 좌표값을 두 개 입력한 경우, 두 점을 있는 직선으로 가정한다. 좌표값과 좌표값 사이는 '-' 문자로 구분한다.
- 직선인 경우는 두 점 사이 거리를 계산해서 출력한다.

**실행 결과**
```
좌표를 입력하세요.
(10,10)-(14,15)
```
```
두 점 사이 거리는 6.403124
```
### 2. 좌표계산기(사각형 면적)

- 좌표값을 두 개 입력한 경우, 두 점을 있는 직선으로 가정한다. 좌표값과 좌표값 사이는 '-' 문자로 구분한다.
- 좌표값을 네 개 입력한 경우, 네 점을 연결하는 사각형으로 가정한다.
    - 네 점이 뒤틀어진 사다리꼴이나 마름모는 제외하고 직사각형만 허용하도록 검사한다.
    - 사각형인 경우 사각형의 넓이를 계산해서 출력한다.

**실행 결과**
```
좌표를 입력하세요.
(10,10)-(22,10)-(22,18)-(10,18)
```
```
사각형 넓이는 96
```

### 3. 좌표계산(삼각형 면적)

- 좌표값을 두 개 입력한 경우, 두 점을 있는 직선으로 가정한다. 좌표값과 좌표값 사이는 '-' 문자로 구분한다.
- 좌표값을 세 개 입력한 경우, 세 점을 연결하는 삼각형으로 가정한다.
    - 삼각형인 경우 삼각형의 넓이를 계산해서 출력한다.

**실행 결과**

```
좌표를 입력하세요.
(10,10)-(14,15)-(20,8)
```
```
삼각형 넓이는 29.0
```
## 객체 설계

```
├── CoordinateApp.java
├── controller
│   └── CoordinateCalculator.java
├── dto
│   └── CoordinateInfo.java
├── model
│   ├── AbstractFigure.java
│   ├── Figure.java
│   ├── FigureFactory.java
│   ├── Line.java
│   ├── Point.java
│   ├── PointMapper.java
│   ├── Rectangle.java
│   └── Triangle.java
└── view
    ├── InputView.java
    └── ResultView.java
```

`model`

- `Figure` - 도형의 행동(메서드)을 추상화한 인터페이스.
- `AbstractFigure` - 도형의 속성을 추상화한 추상 클래스.
- `Point` - 점(x와 y 필드로 이루어져 있으며 각 필드 값의 범위는 0-24이다).
- `Line` - 직선(두 개의 ‘점’으로 구성).
- `Rectangle` - 직사각형(네 개의 ‘점’으로 구성).
- `Triangle` - 삼각형(세 개의 ‘점’으로 구성).
- `FigureFactory` - 도형(`Figure`)을 생성.

`view`

- `InputView` - 입력 UI를 담당.
- `ResultView` - 출력 UI를 담당.

`dto`

- `CoordinateInfo` - 사용자로 부터 입력받는 ‘좌표 정보’를 추상화.
  - 괄호"(", ")"로 둘러쌓인 '좌표 값'으로 이뤄져 있다.
  - '좌표 값'과 '좌표 값' 사이는 `-` 문자로 구분한다.
  -  좌표 값'의 갯수는 2~4개이다.

`controller`

- `CoordinateCalculator` - UI에서 입력받은 좌표 정보를 계산하여 출력 UI에 반환.

`config`

- `CoordinateContext` - 의존성의 생성과 주입을 담당.

## 기능 목록

- [x] 계산기 의존성 생성 및 주입 - `config/CoordinateContext#getCoordinateCalculator`
- [x] 계산기를 실행한다 - `controller/CoordinateCalculator#run`
    - [x] 사용자 문자열을 입력받아 '좌표정보(`dto/CoordinateInfo`)'를 반환한다. - `view/InputView#askCoordinateInfo`
    - [x] '좌표정보(`dto/CoordinateInfo`)'를 '점(`model/Point`)으로 이뤄진 리스트'로 변환한다. - `dto/CoordinateInfo#toPointList`        
    - [x] '점(`model/Point`)'으로 이뤄진 리스트'로 '도형(`model/Figure`)' 객체를 생성한다. - `model/FigureFactory#create`
        - [x] 리스트의 크기가 2일 경우, 선(`model/Line`) 인스턴스를 생성한다.
        - [x] 리스트의 크기가 3일 경우, 직사각형(`model/Rectangle`) 인스턴스를 생성한다.
        - [x] 리스트의 크기가 4일 경우, 선(`model/Triangle`) 인스턴스를 생성한다.
    - [x] 계산 결과를 출력한다. - `view/ResultView#showArea`
        - [x] 도형의 면적을 계산하여 보고한다. - `view/Figure#reportArea`
