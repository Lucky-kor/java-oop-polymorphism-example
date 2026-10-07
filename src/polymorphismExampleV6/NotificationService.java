package polymorphismExampleV6;

/**
 * 구체 클래스가 아닌 NotificationSender 인터페이스에 의존하는 서비스입니다.
 */
public class NotificationService {

    private final NotificationSender sender;

    /*
     * 생성자를 통해 사용할 구현체를 전달받습니다.
     * 서비스 코드를 수정하지 않고도 전송 방식을 교체할 수 있습니다.
     */
    public NotificationService(NotificationSender sender) {
        if (sender == null) {
            throw new IllegalArgumentException("알림 전송 수단이 필요합니다.");
        }
        this.sender = sender;
    }

    public void sendWelcomeMessage(String recipient) {
        sender.send(recipient, "가입을 환영합니다!");
    }
}
