package polymorphismExampleV3;

/** Vehicle의 공통 기능과 오토바이만의 고유 기능을 함께 가집니다. */
public class MotorBike extends Vehicle {

    public void performTrick() {
        System.out.println("오토바이가 묘기를 부립니다.");
    }
}
