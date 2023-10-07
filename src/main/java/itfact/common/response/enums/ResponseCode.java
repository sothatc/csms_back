package itfact.common.response.enums;

public enum ResponseCode {
    SUCCESS_SEARCH("0000", "조회되었습니다."),
    SUCCESS_SAVE("0000", "저장되었습니다."),
    SUCCESS_UPDATE("0000", "수정되었습니다."),
    SUCCESS_DELETE("0000", "삭제되었습니다."),
    FAIL_SEARCH("-1", "조회에 실패 되었습니다."),
    FAIL_SAVE("-1", "저장에 실패되었습니다."),
    FAIL_UPDATE("-1", "수정에 실패되었습니다."),
    FAIL_DELETE("-1", "삭제에 실패하였습니다"),
    FAIL_INSERT("-1", "이미 등록된 값입니다"),
    INVALID_INPUT_VALUE("0001", "필수 입력값 누락"),
    SUCCESS_SENDMAIL("0000", "메일발송되었습니다."),
    SUCCESS_LOGIN("0000", "로그인이 성공되었습니다."),
    FAIL_LOGIN("-1", "로그인이 실패되었습니다."),
    INVALID_LOGIN("0001", "이미 로그인된 사용자입니다."),
    EXISTING_USER("0001", "로그인된 사용자 없습니다."),
    PASSWORD_MAX_ERR("-1", "비밀번호 입력 오류 5회 초과"),
    PASSWORD_INIT("-1", "비밀번호 변경 대상자"),
    PASSWORD_ERROR("-1", "비밀번호 오류입니다"),
    FAIL_SENDMAIL("-1", "메일발송이 실패되었습니다."),
    SESSION_DUPLICATION("-1","중복로그인"),
    SESSION_END("-1","세션종료"),
    SUCCESS_AUTH("0000","인증되었습니다."),
    FAIL_CNT_AUTH("-1", "인증 횟수가 초과되었습니다."),
    FAIL_TIME_LIMIT_AUTH("-1","인증 시간이 초과되었습니다."),
    FAIL_AUTH("-1","인증번호가 틀렸습니다."),
    FAIL_NOT_EXIST_USER("-1","존재하지 않는 사용자입니다."),
    FAIL_PASSWORD("-1","비밀번호가 틀렸습니다."),
    PASSWORD_CHANGE_REQUIRED("-1", "비밀번호 재설정을 해야 합니다."),
    FILE_SIZE_EXCEED_THE_MAXIMUM_LIMIT("-1", "파일 크기가 최대 제한을 초과하였습니다."),
    FILE_PERMISION_FILE_MIME_TYPE("-1", "첨부 가능한 파일이 아닙니다.");
    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
