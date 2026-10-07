package polymorphismExampleV5;

/**
 * 부모 타입인 Coffee 하나만 알고도 모든 종류의 커피를 구매할 수 있습니다.
 */
public class Customer {

    private int money;

    public Customer(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("보유 금액은 음수일 수 없습니다.");
        }
        this.money = money;
    }

    public void buyCoffee(Coffee coffee) {
        if (coffee == null) {
            throw new IllegalArgumentException("구매할 커피가 필요합니다.");
        }

        if (money < coffee.getPrice()) {
            System.out.println(coffee.getName() + " 구매 실패: 잔액이 부족합니다. (현재 "
                    + money + "원)");
            return;
        }

        money -= coffee.getPrice();

        /*
         * 변수의 타입은 Coffee이지만 실행되는 메서드는 실제 객체가 재정의한 메서드입니다.
         * 이를 동적 메서드 디스패치(dynamic method dispatch)라고 합니다.
         */
        coffee.prepare();
        System.out.println(coffee.getName() + " 구입 완료, 잔액: " + money + "원");
    }
}
