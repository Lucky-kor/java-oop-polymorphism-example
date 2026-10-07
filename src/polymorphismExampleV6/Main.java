package polymorphismExampleV6;

/**
 * 인터페이스를 이용해 구현체를 교체하는 다형성 예제입니다.
 */
public class Main {

    public static void main(String[] args) {
        NotificationSender[] senders = {
                new EmailSender(),
                new SmsSender(),
                new PushSender()
        };

        String[] recipients = {
                "student@example.com",
                "010-1234-5678",
                "device-token-001"
        };

        System.out.println("=== 인터페이스 기반 알림 전송 ===");
        for (int index = 0; index < senders.length; index++) {
            NotificationService service = new NotificationService(senders[index]);
            service.sendWelcomeMessage(recipients[index]);
        }

        /*
         * 새로운 알림 방식이 필요하면 NotificationSender를 구현하는 클래스를 추가하면 됩니다.
         * NotificationService의 코드는 변경할 필요가 없습니다.
         */
    }
}
