package polymorphismExampleV1;

/**
 * 업캐스팅과 다운캐스팅의 차이를 확인하는 예제입니다.
 */
public class Main {

    public static void main(String[] args) {
        Car car = new Car();

        /*
         * 자식 타입인 Car 객체를 부모 타입인 Vehicle 변수에 저장하는 것을
         * 업캐스팅이라고 합니다. 상속 관계가 분명하므로 형변환 연산자를
         * 생략할 수 있으며, 실제 객체는 여전히 Car입니다.
         */
        Vehicle vehicle = car;
        System.out.println("=== 업캐스팅 ===");
        System.out.println("vehicle이 가리키는 실제 객체: "
                + vehicle.getClass().getSimpleName());
        vehicle.startEngine();

        /*
         * 변수의 타입이 Vehicle이므로 컴파일러는 Vehicle에 선언된 멤버만
         * 사용할 수 있게 합니다. 실제 객체가 Car여도 아래 호출은 컴파일되지 않습니다.
         */
        // vehicle.giveRide(); // Vehicle 타입에는 giveRide 메서드가 없음

        /*
         * 부모 타입 변수로부터 자식 타입의 고유 기능을 사용하려면
         * 명시적으로 다운캐스팅해야 합니다. vehicle의 실제 객체가 Car이므로 안전합니다.
         */
        Car restoredCar = (Car) vehicle;
        System.out.println();
        System.out.println("=== 안전한 다운캐스팅 ===");
        restoredCar.giveRide();
        System.out.println("car와 restoredCar는 같은 객체인가? " + (car == restoredCar));

        /*
         * 컴파일러는 Vehicle 변수가 Car일 가능성과 MotorBike일 가능성을 모두 허용합니다.
         * 하지만 실제 객체의 타입과 다른 타입으로 다운캐스팅하면 실행 중에
         * ClassCastException이 발생합니다.
         */
        Vehicle motorBikeAsVehicle = new MotorBike();

        System.out.println();
        System.out.println("=== 잘못된 다운캐스팅 ===");
        try {
            Car wrongCar = (Car) motorBikeAsVehicle;
            wrongCar.giveRide();
        } catch (ClassCastException exception) {
            System.out.println("MotorBike 객체는 Car로 변환할 수 없습니다.");
            System.out.println("실제 타입을 확인하지 않은 다운캐스팅은 위험합니다.");
        }

        /*
         * Car와 MotorBike는 서로 부모·자식 관계가 아닌 형제 타입입니다.
         * 따라서 다음 코드는 형변환 연산자를 사용해도 컴파일되지 않습니다.
         */
        // MotorBike motorBike = (MotorBike) car;
    }
}
