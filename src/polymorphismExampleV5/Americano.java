package polymorphismExampleV5;

/** Coffee의 추상 메서드를 아메리카노 방식으로 구현합니다. */
public class Americano extends Coffee {

    public Americano() {
        super(5_000);
    }

    @Override
    public String getName() {
        return "아메리카노";
    }

    @Override
    public void prepare() {
        System.out.println("에스프레소에 물을 더해 아메리카노를 만듭니다.");
    }
}
