package polymorphismExampleV4;

/** 새 메뉴를 추가할 때 Customer의 오버로드도 늘어나는지 확인하기 위한 타입입니다. */
public class VanillaLatte extends Coffee {

    public VanillaLatte() {
        super("바닐라 라테", 7_000);
    }
}
