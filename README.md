# 자바 객체지향과 다형성 학습 가이드

이 프로젝트는 자바의 업캐스팅과 다운캐스팅부터 `instanceof`, 메서드 오버라이딩, 추상 클래스, 인터페이스 기반 설계까지 순서대로 학습하기 위한 예제입니다.
각 예제를 실행하기 전에 **어떤 메서드가 호출될지 먼저 예상하고**, 실행 결과가 예상과 다른 이유를 변수의 타입과 실제 객체의 타입으로 나누어 설명해 보세요.

## 학습 목표

이 프로젝트를 모두 학습하면 다음 내용을 설명할 수 있어야 합니다.

- 변수의 선언 타입과 변수가 가리키는 실제 객체의 타입을 구분할 수 있다.
- 업캐스팅과 다운캐스팅의 방향 및 형변환 연산자 필요 여부를 설명할 수 있다.
- 다운캐스팅이 객체 자체를 다른 종류로 바꾸는 일이 아님을 설명할 수 있다.
- 잘못된 다운캐스팅에서 `ClassCastException`이 발생하는 이유를 설명할 수 있다.
- `instanceof`로 실제 타입을 검사하고 안전하게 다운캐스팅할 수 있다.
- 오버로딩과 오버라이딩의 차이를 구분할 수 있다.
- 부모 타입의 변수·매개변수·배열로 여러 자식 객체를 한 방식으로 처리할 수 있다.
- 실제 객체에 따라 재정의된 메서드가 선택되는 동적 메서드 디스패치를 설명할 수 있다.
- 추상 클래스와 인터페이스가 다형성에서 담당하는 역할을 비교할 수 있다.
- 구체 타입이 아니라 추상 타입에 의존할 때 변경 범위가 줄어드는 이유를 설명할 수 있다.

## 선수 지식

다음 내용을 먼저 알고 있으면 예제를 이해하기 쉽습니다.

- 클래스와 객체 생성
- 필드, 메서드, 생성자
- `extends`를 사용한 클래스 상속
- 배열, 반복문, 조건문

## 권장 학습 순서

| 순서 | 실행 클래스 | 핵심 주제 |
| --- | --- | --- |
| 1 | `polymorphismExampleV1.Main` | 업캐스팅, 다운캐스팅, `ClassCastException` |
| 2 | `polymorphismExampleV2.InstanceofTest` | `instanceof`의 판단 기준과 `null` |
| 3 | `polymorphismExampleV3.InstanceofTest` | 타입 확인 후 안전한 다운캐스팅 |
| 4 | `polymorphismExampleV4.Main` | 타입별 오버로딩과 코드 중복 |
| 5 | `polymorphismExampleV5.Main` | 추상 클래스, 오버라이딩, 동적 메서드 디스패치 |
| 6 | `polymorphismExampleV6.Main` | 인터페이스 기반 다형성과 구현체 교체 |

`V1`부터 `V3`까지는 타입 변환 자체를 익히는 단계입니다. `V4`와 `V5`는 같은 커피 주문 문제를 서로 다른 방식으로 구현하여 다형성의 장점을 비교합니다. 마지막 `V6`에서는 상속할 구현이 없는 경우에도 인터페이스로 공통 계약을 만들고 구현체를 교체하는 방법을 학습합니다.

## 예제 실행 방법

### IntelliJ IDEA에서 실행

1. 프로젝트 폴더를 IntelliJ IDEA에서 엽니다.
2. `src` 폴더를 소스 루트로 지정합니다. 이미 파란색 폴더로 표시된다면 이 단계는 생략합니다.
3. 위 표에 있는 실행 클래스의 `main` 메서드를 엽니다.
4. `main` 메서드 왼쪽의 실행 아이콘을 누르고 아래쪽 **Run** 창에서 결과를 확인합니다.

### 터미널에서 실행

프로젝트 최상위 폴더에서 다음 명령을 실행합니다.

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out polymorphismExampleV1.Main
```

마지막 줄의 클래스 이름을 권장 학습 순서 표에 있는 이름으로 바꾸면 다른 예제도 실행할 수 있습니다.

## 다형성을 이해하는 핵심 질문 두 가지

다음 한 줄에는 서로 다른 두 타입 정보가 있습니다.

```java
Vehicle vehicle = new Car();
```

- 왼쪽의 `Vehicle`: 변수의 **선언 타입**
- 오른쪽의 `Car`: 만들어진 객체의 **실제 타입**

컴파일할 때 사용할 수 있는 멤버는 기본적으로 선언 타입을 기준으로 결정됩니다. 반면 오버라이딩된 인스턴스 메서드 중 무엇이 실행될지는 실제 객체의 타입을 기준으로 결정됩니다.

```java
vehicle.startEngine(); // Vehicle 타입에 선언되어 있으므로 호출 가능
// vehicle.giveRide(); // Vehicle 타입에 선언되어 있지 않아 컴파일 불가
```

이 두 기준을 분리해서 생각하는 것이 전체 예제를 이해하는 열쇠입니다.

## 1. 업캐스팅과 다운캐스팅

학습 파일:

- [`src/polymorphismExampleV1/Main.java`](src/polymorphismExampleV1/Main.java)
- [`src/polymorphismExampleV1/Vehicle.java`](src/polymorphismExampleV1/Vehicle.java)
- [`src/polymorphismExampleV1/Car.java`](src/polymorphismExampleV1/Car.java)
- [`src/polymorphismExampleV1/MotorBike.java`](src/polymorphismExampleV1/MotorBike.java)

### 업캐스팅

자식 객체를 부모 타입 변수에 저장하는 것을 업캐스팅이라고 합니다.

```java
Car car = new Car();
Vehicle vehicle = car;
```

모든 `Car`는 `Vehicle`이므로 자바가 안전하다고 판단하며 형변환 연산자를 생략할 수 있습니다. 업캐스팅 후에도 새로운 객체가 만들어지거나 기존 객체가 `Vehicle` 객체로 바뀌지 않습니다. 두 변수는 같은 `Car` 객체를 가리킵니다.

### 다운캐스팅

부모 타입 변수를 자식 타입 변수로 다시 다루려면 명시적 형변환이 필요합니다.

```java
Car restoredCar = (Car) vehicle;
```

이 코드는 `vehicle`이 가리키던 객체를 `Car`로 바꾸는 코드가 아닙니다. 원래부터 `Car`였던 객체를 `Car` 타입 변수로 다시 참조하는 코드입니다.

### 잘못된 다운캐스팅

다음 코드는 컴파일되지만 실행하면 실패합니다.

```java
Vehicle vehicle = new MotorBike();
Car car = (Car) vehicle; // ClassCastException
```

컴파일러는 `Vehicle` 변수에 `Car`가 들어 있을 가능성까지는 인정합니다. 그러나 실행 시 실제 객체가 `MotorBike`인 것이 확인되므로 `ClassCastException`이 발생합니다. 예제는 학습 흐름이 중단되지 않도록 이 예외를 `try-catch`로 확인합니다.

### 직접 해보기

- `Vehicle vehicle = car;`를 `Vehicle vehicle = new Car();`로 바꾸고 결과가 같은지 확인합니다.
- `restoredCar.giveRide()`를 주석 처리하고 `vehicle.giveRide()`를 작성하여 컴파일 오류를 읽어 봅니다.
- 잘못된 다운캐스팅의 `Car`를 `MotorBike`로 바꾸어 정상적으로 동작하는지 확인합니다.
- `car == restoredCar`가 `true`인 이유를 객체 그림으로 표현해 봅니다.

## 2. instanceof로 실제 타입 확인하기

학습 파일: [`src/polymorphismExampleV2/InstanceofTest.java`](src/polymorphismExampleV2/InstanceofTest.java)

`instanceof`는 왼쪽의 참조값이 오른쪽 타입의 객체로 안전하게 취급될 수 있는지 검사합니다.

```java
Vehicle vehicle = new Car();

vehicle instanceof Car;       // true
vehicle instanceof Vehicle;   // true
vehicle instanceof MotorBike; // false
vehicle instanceof Object;    // true
```

`Car` 객체는 `Car`이면서 부모 타입인 `Vehicle`이고, 모든 클래스의 최상위 부모인 `Object`이기도 합니다. 변수 왼쪽에 `Vehicle`이라고 선언되어 있다는 사실만 검사하는 것이 아니라 실제 객체와 상속 관계를 함께 검사합니다.

### null과 instanceof

`null`은 어떤 객체도 가리키지 않으므로 어떤 참조 타입으로 검사해도 결과는 `false`입니다.

```java
Vehicle noVehicle = null;
System.out.println(noVehicle instanceof Vehicle); // false
```

이 검사는 `NullPointerException`을 발생시키지 않습니다.

### 직접 해보기

- 실제 객체가 `MotorBike`인 변수를 추가하고 네 가지 검사 결과를 먼저 예상합니다.
- `Car car = new Car();`에서 `car instanceof Vehicle`의 결과를 확인합니다.
- `Object object = new Car();`를 만들고 `Car`, `Vehicle`, `Object`에 대한 결과를 비교합니다.
- 세 경우의 상속 관계를 `Object → Vehicle → Car` 형태로 그려 봅니다.

## 3. 안전한 다운캐스팅

학습 파일: [`src/polymorphismExampleV3/InstanceofTest.java`](src/polymorphismExampleV3/InstanceofTest.java)

여러 자식 객체를 `Vehicle[]` 배열 하나에 저장한 후 공통 메서드와 고유 메서드를 차례로 호출합니다.

```java
if (vehicle instanceof Car) {
    Car car = (Car) vehicle;
    car.giveRide();
}
```

`instanceof` 결과가 `true`일 때만 같은 타입으로 다운캐스팅하므로 `ClassCastException`을 피할 수 있습니다. 공통 기능인 `startEngine`은 `Vehicle`에 선언되어 있으므로 타입 검사나 다운캐스팅 없이 호출합니다.

### instanceof 분기가 많아질 때

안전한 다운캐스팅이 항상 좋은 최종 설계라는 뜻은 아닙니다. 자식 타입이 늘어날 때마다 다음과 같은 분기가 계속 추가된다면, 공통 메서드를 부모 타입에 선언하고 각 자식이 오버라이딩할 수 있는지 검토할 필요가 있습니다.

```java
if (vehicle instanceof Car) {
    // Car 처리
} else if (vehicle instanceof MotorBike) {
    // MotorBike 처리
}
```

실제 프로그램에서 타입별 동작을 선택하는 코드가 여러 곳에 흩어지면 새 타입을 추가할 때 빠뜨리기 쉽습니다. `V5`와 `V6`에서 조건문 대신 오버라이딩된 메서드를 호출하는 구조를 확인합니다.

### 직접 해보기

- `Vehicle[]` 요소의 순서를 바꾸고 출력 순서가 어떻게 달라지는지 확인합니다.
- `Vehicle`을 상속한 `Bus`와 `takePassengers()`를 추가한 뒤 현재 구조에서 어디를 수정해야 하는지 찾아봅니다.
- `null` 요소를 배열에 추가하면 어느 줄에서 어떤 예외가 발생할지 예상합니다.
- 사용 중인 JDK가 16 이상이라면 `if (vehicle instanceof Car car)` 패턴 매칭 문법으로 바꿔 봅니다.

## 4. 다형성을 적용하기 전: 타입별 오버로딩

학습 파일:

- [`src/polymorphismExampleV4/Customer.java`](src/polymorphismExampleV4/Customer.java)
- [`src/polymorphismExampleV4/Main.java`](src/polymorphismExampleV4/Main.java)

`V4`의 `Customer`는 커피의 구체 타입마다 `buyCoffee` 메서드를 하나씩 가지고 있습니다.

```java
void buyCoffee(Americano americano) { ... }
void buyCoffee(CafeLatte cafeLatte) { ... }
void buyCoffee(VanillaLatte vanillaLatte) { ... }
```

이처럼 이름은 같고 매개변수 목록이 다른 메서드를 여러 개 정의하는 것을 **오버로딩**이라고 합니다. 호출할 메서드는 컴파일할 때 전달 인자의 선언 타입을 기준으로 결정됩니다.

예제의 세 메서드는 거의 같은 일을 하므로 다음 문제가 드러납니다.

- 커피 종류를 추가할 때마다 `Customer`도 수정해야 합니다.
- 잔액 확인이나 할인 정책을 추가하면 같은 변경을 여러 메서드에 반복해야 합니다.
- 빠뜨린 메서드마다 동작이 달라질 수 있습니다.

이 예제의 중복은 학습을 위해 의도적으로 남겨 둔 것입니다. `V5`에서 부모 타입 매개변수 하나로 어떻게 바뀌는지 비교하세요.

### 직접 해보기

- `Mocha` 클래스를 추가하려면 어떤 파일들을 수정해야 하는지 먼저 적어 봅니다.
- 세 `buyCoffee` 메서드에 잔액 검사 코드를 각각 추가해 보고 중복되는 줄을 세어 봅니다.
- `Coffee coffee = new Americano();`를 전달할 때 어떤 오버로드가 선택될지 예상하고 컴파일 결과를 확인합니다.

## 5. 부모 타입과 오버라이딩으로 중복 제거하기

학습 파일:

- [`src/polymorphismExampleV5/Coffee.java`](src/polymorphismExampleV5/Coffee.java)
- [`src/polymorphismExampleV5/Customer.java`](src/polymorphismExampleV5/Customer.java)
- [`src/polymorphismExampleV5/Main.java`](src/polymorphismExampleV5/Main.java)

### 추상 클래스와 추상 메서드

`Coffee`는 공통 가격을 보관하면서 커피 종류마다 달라야 하는 이름과 제조 방법을 추상 메서드로 선언합니다.

```java
public abstract class Coffee {
    public abstract String getName();
    public abstract void prepare();
}
```

추상 클래스는 직접 객체로 만들 수 없습니다. 구체적인 자식 클래스가 추상 메서드를 모두 구현해야 객체를 만들 수 있습니다. 따라서 모든 커피가 이름과 제조 방법을 제공한다는 규칙을 컴파일러가 확인해 줍니다.

### 부모 타입 매개변수

`Customer`에는 이제 `buyCoffee(Coffee coffee)` 하나만 있습니다.

```java
public void buyCoffee(Coffee coffee) {
    coffee.prepare();
}
```

`Americano`, `CafeLatte`, `VanillaLatte`는 모두 `Coffee`이므로 같은 메서드에 전달할 수 있습니다. 새 커피가 추가되어도 `Coffee`를 올바르게 상속했다면 `Customer`는 수정할 필요가 없습니다.

### 동적 메서드 디스패치

`coffee` 변수의 선언 타입은 항상 `Coffee`이지만 `prepare()`를 실행한 결과는 실제 객체마다 다릅니다.

| 실제 객체 | 실행되는 메서드 |
| --- | --- |
| `new Americano()` | `Americano.prepare()` |
| `new CafeLatte()` | `CafeLatte.prepare()` |
| `new VanillaLatte()` | `VanillaLatte.prepare()` |

실행 시점의 실제 객체를 기준으로 재정의된 인스턴스 메서드를 선택하는 동작을 동적 메서드 디스패치라고 합니다. 이것이 다형성의 핵심 동작입니다.

### 하나의 배열로 여러 객체 처리하기

서로 다른 커피 객체를 `Coffee[]` 하나에 담을 수 있습니다.

```java
Coffee[] order = {
        new Americano(),
        new CafeLatte(),
        new VanillaLatte()
};
```

반복문은 구체 타입을 구분하지 않고 모든 요소에 같은 구매 요청을 보냅니다. 예제의 마지막 주문은 잔액 부족으로 실패하므로 성공 흐름과 실패 흐름을 함께 확인할 수 있습니다.

### 직접 해보기

- `Coffee coffee = new Coffee(5_000);`가 컴파일되지 않는 이유를 설명합니다.
- `Mocha` 클래스를 추가하여 `getName`과 `prepare`를 구현하고 배열에 넣습니다. `Customer`를 수정하지 않아도 되는지 확인합니다.
- `CafeLatte`에서 `prepare`의 `@Override` 아래 메서드 이름을 일부러 틀려 컴파일러가 무엇을 알려 주는지 확인합니다.
- 초기 금액을 바꾸어 어느 주문부터 실패하는지 예상한 뒤 실행합니다.
- `Coffee[]` 대신 `List<Coffee>`를 사용하도록 바꿔 봅니다.

## 6. 인터페이스 기반 다형성

학습 파일:

- [`src/polymorphismExampleV6/NotificationSender.java`](src/polymorphismExampleV6/NotificationSender.java)
- [`src/polymorphismExampleV6/NotificationService.java`](src/polymorphismExampleV6/NotificationService.java)
- [`src/polymorphismExampleV6/Main.java`](src/polymorphismExampleV6/Main.java)

커피 예제는 공통 상태와 구현을 공유하기 위해 추상 클래스를 사용했습니다. 알림 예제의 이메일, SMS, 푸시 전송은 공유할 상태나 부모 구현보다 **모두 전송할 수 있다**는 공통 계약이 중요하므로 인터페이스를 사용합니다.

```java
public interface NotificationSender {
    void send(String recipient, String message);
}
```

각 클래스는 같은 계약을 서로 다른 방식으로 구현합니다.

```java
public class EmailSender implements NotificationSender { ... }
public class SmsSender implements NotificationSender { ... }
public class PushSender implements NotificationSender { ... }
```

### 구체 클래스가 아니라 인터페이스에 의존하기

`NotificationService`는 `EmailSender`나 `SmsSender`를 직접 만들지 않고 생성자로 `NotificationSender`를 전달받습니다.

```java
NotificationSender sender = new EmailSender();
NotificationService service = new NotificationService(sender);
```

이 구조에서는 서비스 코드를 바꾸지 않고 생성할 때 전달하는 구현체만 바꾸어 전송 방식을 교체할 수 있습니다. 테스트용 가짜 구현체를 전달하거나 새로운 알림 방식을 추가하기도 쉬워집니다.

### 추상 클래스와 인터페이스 선택 기준

| 기준 | 추상 클래스 | 인터페이스 |
| --- | --- | --- |
| 공통 인스턴스 상태 | 필드로 가질 수 있음 | 일반 인스턴스 필드를 가질 수 없음 |
| 공통 구현 | 생성자와 일반 메서드로 공유 가능 | `default` 메서드 등 제한된 형태로 가능 |
| 관계를 표현하는 말 | 같은 계열의 **~이다** | 어떤 역할을 **할 수 있다** |
| 클래스가 가질 수 있는 수 | 하나만 상속 가능 | 여러 인터페이스 구현 가능 |
| 이 프로젝트의 예 | 여러 종류의 `Coffee` | 여러 방식의 `NotificationSender` |

항상 한쪽이 더 좋은 것은 아닙니다. 공유해야 할 상태와 구현이 있는지, 여러 클래스에 역할만 부여하려는지에 따라 선택합니다.

### 직접 해보기

- 콘솔에 기록만 남기는 `ConsoleSender`를 추가하고 배열에 넣습니다.
- `NotificationService`를 수정하지 않고 새 구현체가 동작하는지 확인합니다.
- `NotificationSender` 타입 변수에 세 구현체를 차례로 대입하고 같은 `send` 호출의 결과를 비교합니다.
- 전송 내용을 메모리에 저장하는 가짜 구현체를 만들어 `sendWelcomeMessage`가 올바른 문구를 전달하는지 확인합니다.

## 오버로딩과 오버라이딩 비교

두 용어는 이름이 비슷하지만 목적과 선택 시점이 다릅니다.

| 구분 | 오버로딩 | 오버라이딩 |
| --- | --- | --- |
| 의미 | 같은 이름, 다른 매개변수 목록의 메서드를 여러 개 정의 | 부모나 인터페이스의 메서드를 자식이 다시 구현 |
| 주로 비교할 위치 | 같은 클래스 안 | 부모와 자식 클래스 사이 |
| 선택 기준 | 전달 인자의 선언 타입과 메서드 시그니처 | 객체의 실제 타입 |
| 선택 시점 | 컴파일 시점 | 실행 시점 |
| 이 프로젝트 | `V4`의 여러 `buyCoffee` | `V5`의 `prepare`, `V6`의 `send` |

다음 코드를 읽을 때는 `buyCoffee` 자체는 어떤 시그니처로 선택되는지, 그 안의 `prepare`는 어느 객체의 구현이 실행되는지를 따로 생각해야 합니다.

```java
Coffee coffee = new Americano();
customer.buyCoffee(coffee); // buyCoffee(Coffee)가 선택됨
                         // 내부의 coffee.prepare()는 Americano.prepare() 실행
```

## 자주 하는 오해와 주의점

### 캐스팅하면 객체 종류가 바뀐다

캐스팅은 객체를 새로 만들거나 실제 종류를 바꾸지 않습니다. 참조값을 어떤 타입의 관점으로 사용할지 지정할 뿐입니다.

### 부모 타입 변수는 부모 객체만 가리킨다

부모 타입 변수는 부모 객체뿐 아니라 모든 자식 객체도 가리킬 수 있습니다. 이것이 여러 구현을 하나의 타입으로 다루는 출발점입니다.

### instanceof를 많이 쓰면 다형성을 잘 활용한 것이다

`instanceof`는 외부 입력을 분류하거나 타입별 고유 기능에 접근할 때 필요할 수 있습니다. 하지만 동일한 타입 분기가 여러 곳에 반복되면 오버라이딩이나 인터페이스로 동작을 객체 안에 옮길 수 있는지 검토하세요.

### 부모 타입으로 선언하면 자식 메서드는 실행되지 않는다

부모 타입에 선언된 메서드를 자식이 오버라이딩했다면 실제 자식 객체의 메서드가 실행됩니다. 다만 부모 타입에 전혀 선언되지 않은 자식 전용 메서드는 다운캐스팅 없이 호출할 수 없습니다.

### 모든 메서드가 실제 객체 타입에 따라 선택된다

이 프로젝트에서 다루는 동적 디스패치는 오버라이딩 가능한 인스턴스 메서드에 해당합니다. `static` 메서드와 필드는 같은 방식으로 다형적으로 선택되지 않습니다.

## 최종 점검 문제

1. `Vehicle vehicle = new Car();`에서 선언 타입과 실제 타입은 각각 무엇인가요?
2. 업캐스팅에는 보통 형변환 연산자가 필요 없고 다운캐스팅에는 필요한 이유는 무엇인가요?
3. 컴파일에 성공한 다운캐스팅이 실행 중 실패할 수 있는 이유는 무엇인가요?
4. `null instanceof Object`의 결과는 무엇인가요?
5. `V4`에 커피 종류를 하나 추가할 때와 `V5`에 추가할 때 수정 범위는 어떻게 다른가요?
6. `Coffee coffee = new CafeLatte(); coffee.prepare();`는 어느 클래스의 메서드를 실행하나요?
7. 오버로딩과 오버라이딩은 각각 언제 호출할 메서드가 결정되나요?
8. `NotificationService`가 `EmailSender` 대신 `NotificationSender`를 필드 타입으로 사용하는 장점은 무엇인가요?
9. 추상 클래스와 인터페이스 중 하나를 선택할 때 어떤 기준을 고려해야 하나요?
10. 타입을 확인하는 `if-else`가 계속 늘어날 때 어떤 설계 개선을 검토할 수 있나요?

위 질문에 코드를 보지 않고 답할 수 있고, 새로운 자식 클래스나 인터페이스 구현체를 기존 처리 코드의 큰 수정 없이 추가할 수 있다면 이 프로젝트의 핵심 학습 목표를 달성한 것입니다.
