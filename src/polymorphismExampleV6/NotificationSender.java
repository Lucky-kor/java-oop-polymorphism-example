package polymorphismExampleV6;

/**
 * 알림 전송 수단들이 따라야 하는 공통 계약입니다.
 *
 * 인터페이스는 "어떻게 전송하는가"를 정하지 않고,
 * 모든 구현체가 send 메서드를 제공해야 한다는 사실만 선언합니다.
 */
public interface NotificationSender {

    void send(String recipient, String message);
}
