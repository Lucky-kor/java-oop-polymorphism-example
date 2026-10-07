package polymorphismExampleV4;

/**
 * 다형성을 충분히 활용하지 않았을 때 생기는 중복을 보여 주는 클래스입니다.
 */
public class Customer {

    private int money = 20_000;

    /*
     * 세 메서드는 매개변수 타입만 다른 오버로딩(overloading)입니다.
     * 내부 로직은 같은데도 커피 종류마다 메서드를 추가해야 하며,
     * 할인이나 영수증 기능이 바뀌면 모든 메서드를 함께 수정해야 합니다.
     */
    public void buyCoffee(Americano americano) {
        money -= americano.getPrice();
        printReceipt(americano.getName(), americano.getPrice());
    }

    public void buyCoffee(CafeLatte cafeLatte) {
        money -= cafeLatte.getPrice();
        printReceipt(cafeLatte.getName(), cafeLatte.getPrice());
    }

    public void buyCoffee(VanillaLatte vanillaLatte) {
        money -= vanillaLatte.getPrice();
        printReceipt(vanillaLatte.getName(), vanillaLatte.getPrice());
    }

    private void printReceipt(String coffeeName, int price) {
        System.out.println(coffeeName + " 구입: " + price + "원, 잔액: " + money + "원");
    }
}
