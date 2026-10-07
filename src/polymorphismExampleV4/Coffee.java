package polymorphismExampleV4;

/**
 * 모든 커피가 공통으로 가지는 가격과 이름을 저장합니다.
 */
public class Coffee {

    private final String name;
    private final int price;

    public Coffee(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
