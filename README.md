# 좌표계산기

- 프로그래밍 요구사항
- 기능 요구 사항
- 객체 설계
- 기능 목록

## 프로그래밍 요구사항

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

- `Figure` - 도형의 행동(메서드)을 추상화.
- `AbstractFigure` - 도형의 속성을 추상화.
- `Point` - 점.
- `Line` - 직선(두 개의 ‘점’으로 구성).
- `Rectangle` - 직사각형(네 개의 ‘점’으로 구성).
- `Triangle` - 삼각형(세 개의 ‘점’으로 구성).
- `PointMapper` - 입력 정보를 점(`Point`) 객체로 변환.
- `FigureFactory` - 도형(`Figure`)을 생성.

`view`

- `InputView` - 입력 UI를 담당.
- `ResultView` - 출력 UI를 담당.

`dto`

- `CoordinateInfo` - 사용자로 부터 입력받는 ‘좌표 정보’를 추상화.

`controller`

- `CoordinateCalculator` - UI에서 입력받은 좌표 정보를 계산하여 출력 UI에 반환한다.

## 기능 목록

- [x] 계산기를 실행한다 - `controller/CoordinateCalculator#run`
    - [x] 사용자 문자열을 입력받아 '좌표정보(`dto/CoordinateInfo`)'를 반환한다. - `view/InputView#askCoordinateInfo`
        - [x] '좌표정보(`dto/CoordinateInfo`)'는 괄호"(", ")"로 둘러쌓인 '좌표 값'으로 이뤄져 있다.
        - [x] '좌표 값'과 '좌표 값' 사이는 `-` 문자로 구분한다.
        - [x] '좌표 값'의 갯수는 2~4개이다.
    - [x] '좌표 정보'를 '점(`model/Point`)으로 이뤄진 리스트'로 변환한다. - `model/PointMapper#toPointList`
        - [x] 괄호로 둘러쌓인 `(`, `)` 좌표 값은 쉼표`,`로 x값과 y값을 구분한다.
        - [x] x, y값의 범위는 0-24이다.
    - [x] '점(`model/Point`)'으로 이뤄진 리스트'로 '도형(`model/Figure`)' 객체를 생성한다. - `model/FigureFactory#create`
        - [x] 리스트의 크기가 2일 경우, 선(`model/Line`) 인스턴스를 생성한다.
        - [x] 리스트의 크기가 3일 경우, 직사각형(`model/Rectangle`) 인스턴스를 생성한다.
        - [x] 리스트의 크기가 4일 경우, 선(`model/Triangle`) 인스턴스를 생성한다.
    - [x] 계산 결과를 출력한다. - `view/ResultView#showArea`
        - [x] 도형의 면적을 계산하여 보고한다. - `view/Figure#reportArea`
