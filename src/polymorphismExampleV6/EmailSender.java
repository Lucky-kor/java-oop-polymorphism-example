package polymorphismExampleV6;

/** 이메일 방식의 알림 전송 구현체입니다. */
public class EmailSender implements NotificationSender {

    @Override
    public void send(String recipient, String message) {
        System.out.println("이메일 전송 -> 받는 사람: " + recipient + ", 내용: " + message);
    }
}
