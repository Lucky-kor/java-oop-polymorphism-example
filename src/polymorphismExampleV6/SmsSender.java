package polymorphismExampleV6;

/** SMS 방식의 알림 전송 구현체입니다. */
public class SmsSender implements NotificationSender {

    @Override
    public void send(String recipient, String message) {
        System.out.println("SMS 전송 -> 받는 사람: " + recipient + ", 내용: " + message);
    }
}
