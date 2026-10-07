package polymorphismExampleV5;

/**
 * 부모 타입 하나로 여러 자식 객체를 처리하는 다형성 예제입니다.
 */
public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer(20_000);

        /*
         * 서로 다른 자식 객체를 부모 타입인 Coffee 배열 하나에 저장할 수 있습니다.
         * Customer 역시 구체 타입이 아닌 Coffee 타입 하나만 매개변수로 받습니다.
         */
        Coffee[] order = {
                new Americano(),
                new CafeLatte(),
                new VanillaLatte(),
                new Americano()
        };

        System.out.println("=== 다형성을 적용한 커피 주문 ===");
        for (Coffee coffee : order) {
            customer.buyCoffee(coffee);
            System.out.println();
        }
    }
}
