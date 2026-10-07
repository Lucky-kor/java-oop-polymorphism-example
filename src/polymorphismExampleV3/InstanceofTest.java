package polymorphismExampleV3;

/**
 * instanceof로 실제 타입을 확인한 뒤 안전하게 다운캐스팅하는 예제입니다.
 */
public class InstanceofTest {

    public static void main(String[] args) {
        Vehicle[] vehicles = {
                new Car(),
                new MotorBike(),
                new Vehicle()
        };

        /*
         * 배열의 모든 요소는 Vehicle 타입으로 다룰 수 있습니다.
         * 실제 객체의 타입은 각 요소마다 다르므로 useVehicle 내부에서 확인합니다.
         */
        for (Vehicle vehicle : vehicles) {
            useVehicle(vehicle);
            System.out.println();
        }
    }

    private static void useVehicle(Vehicle vehicle) {
        // 부모 클래스에 선언된 공통 기능은 다운캐스팅 없이 호출할 수 있습니다.
        vehicle.startEngine();

        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            car.giveRide();
        } else if (vehicle instanceof MotorBike) {
            MotorBike motorBike = (MotorBike) vehicle;
            motorBike.performTrick();
        } else {
            System.out.println("이 탈것에는 별도의 고유 기능이 없습니다.");
        }
    }
}
