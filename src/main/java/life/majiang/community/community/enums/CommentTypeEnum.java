package life.majiang.community.community.enums;

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private int type;

    public int getType() {
        return type;
    }

    CommentTypeEnum(int commentType) {
        this.type = commentType;
    }

    public static boolean isExist(Integer commentType) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType() == commentType) {
                return true;
            }
        }
        return false;
    }
}
