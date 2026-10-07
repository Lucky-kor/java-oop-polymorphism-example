package polymorphismExampleV5;

/** Coffee의 추상 메서드를 카페라테 방식으로 구현합니다. */
public class CafeLatte extends Coffee {

    public CafeLatte() {
        super(6_000);
    }

    @Override
    public String getName() {
        return "카페라테";
    }

    @Override
    public void prepare() {
        System.out.println("에스프레소에 우유를 더해 카페라테를 만듭니다.");
    }
}
