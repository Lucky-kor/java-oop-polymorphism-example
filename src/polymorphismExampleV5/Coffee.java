package polymorphismExampleV5;

/**
 * 구체적인 커피들이 공통으로 따라야 할 뼈대를 정의하는 추상 클래스입니다.
 */
public abstract class Coffee {

    private final int price;

    protected Coffee(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("커피 가격은 0원보다 커야 합니다.");
        }
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    /** 각 커피가 자신의 이름을 반환하도록 강제합니다. */
    public abstract String getName();

    /** 각 커피가 자신의 제조 방법을 구현하도록 강제합니다. */
    public abstract void prepare();
}
