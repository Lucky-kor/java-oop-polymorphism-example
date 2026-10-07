package polymorphismExampleV5;

/** Coffee의 추상 메서드를 바닐라 라테 방식으로 구현합니다. */
public class VanillaLatte extends Coffee {

    public VanillaLatte() {
        super(7_000);
    }

    @Override
    public String getName() {
        return "바닐라 라테";
    }

    @Override
    public void prepare() {
        System.out.println("에스프레소에 우유와 바닐라 시럽을 더합니다.");
    }
}
