package polymorphismExampleV4;

/**
 * 커피의 구체 타입마다 구매 메서드를 오버로딩한 예제입니다.
 */
public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer();

        System.out.println("=== 다형성을 적용하기 전: 타입별 메서드 호출 ===");
        customer.buyCoffee(new Americano());
        customer.buyCoffee(new CafeLatte());
        customer.buyCoffee(new VanillaLatte());

        /*
         * 새로운 커피 타입을 추가하려면 Coffee의 자식 클래스를 만드는 것만으로 끝나지 않고,
         * Customer에도 같은 모양의 buyCoffee 메서드를 추가해야 합니다.
         * V5에서는 부모 타입을 매개변수로 사용하여 이 중복을 제거합니다.
         */
    }
}
