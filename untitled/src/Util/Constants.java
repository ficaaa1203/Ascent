package Util;

public class Constants {
    public static class directions{
        public static final int RIGHT = 0;
        public static final int LEFT = 1;
        public static final int UP = 2;
        public static final int DOWN = 3;
    }

    public static class robotConstant{
        public static final int IDLE = 0;
        public static final int MOVE = 1;

        public static int getFrameAmount(int player_action){
            switch(player_action){
                case IDLE:
                    return 8;
                case MOVE:
                    return 7;
                default:
                    return 0;
            }
        }
    }
}
