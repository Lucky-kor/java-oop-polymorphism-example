package polymorphismExampleV3;

/** Vehicle의 공통 기능과 자동차만의 고유 기능을 함께 가집니다. */
public class Car extends Vehicle {

    public void giveRide() {
        System.out.println("자동차가 사람을 태웁니다.");
    }
}
