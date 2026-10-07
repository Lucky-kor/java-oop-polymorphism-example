package polymorphismExampleV2;

/**
 * instanceof가 변수의 선언 타입이 아니라 실제 객체의 타입을 검사하는지 확인하는 예제입니다.
 */
public class InstanceofTest {

    public static void main(String[] args) {
        Vehicle plainVehicle = new Vehicle();

        System.out.println("=== 실제 객체가 Vehicle인 경우 ===");
        printTypeCheckResult(plainVehicle);

        /*
         * 변수의 선언 타입은 Vehicle이지만 new Car()로 만든 실제 객체는 Car입니다.
         * Car는 Vehicle과 Object를 차례로 상속하므로 Car, Vehicle, Object에
         * 대한 검사 결과가 true가 됩니다.
         */
        Vehicle carAsVehicle = new Car();

        System.out.println();
        System.out.println("=== 실제 객체가 Car인 경우 ===");
        printTypeCheckResult(carAsVehicle);

        /*
         * null은 어떤 객체도 가리키지 않습니다.
         * null에 instanceof를 사용하면 예외가 아니라 항상 false를 반환합니다.
         */
        Vehicle noVehicle = null;

        System.out.println();
        System.out.println("=== 참조값이 null인 경우 ===");
        printTypeCheckResult(noVehicle);
    }

    private static void printTypeCheckResult(Vehicle vehicle) {
        System.out.println("Vehicle인가? " + (vehicle instanceof Vehicle));
        System.out.println("Car인가? " + (vehicle instanceof Car));
        System.out.println("MotorBike인가? " + (vehicle instanceof MotorBike));
        System.out.println("Object인가? " + (vehicle instanceof Object));
    }
}
