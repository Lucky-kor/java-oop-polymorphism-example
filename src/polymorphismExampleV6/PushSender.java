package polymorphismExampleV6;

/** 모바일 푸시 방식의 알림 전송 구현체입니다. */
public class PushSender implements NotificationSender {

    @Override
    public void send(String recipient, String message) {
        System.out.println("푸시 알림 전송 -> 기기: " + recipient + ", 내용: " + message);
    }
}
