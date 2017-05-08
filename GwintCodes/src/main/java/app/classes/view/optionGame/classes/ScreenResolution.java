package app.classes.view.optionGame.classes;

/**
 * Created by kills on 02.03.2017.
 */
public final class ScreenResolution {

    private ScreenResolution() {
        //do not use
    }

    public static final int[] WIDTH = {1920, 1600, 1366, 1280, 1024, 960, 864, 720, 640};
    public static final int[] HEIGHT = {1080, 900, 768, 720, 576, 540, 486, 405, 360};
    public static final double[] MULTIPLE = new double[]{
            (double) WIDTH[0] / WIDTH[0],
            (double) WIDTH[0] / WIDTH[1],
            (double) WIDTH[0] / WIDTH[2],
            (double) WIDTH[0] / WIDTH[3],
            (double) WIDTH[0] / WIDTH[4],
            (double) WIDTH[0] / WIDTH[5],
            (double) WIDTH[0] / WIDTH[6],
            (double) WIDTH[0] / WIDTH[7],
            (double) WIDTH[0] / WIDTH[8],
    };

    public static final class SIZE {
        private SIZE() {/* do not use */ }

        public static final class CARD {
            private CARD() {/* do not use */ }

            public static final class IN_HEND {
                public static final Integer WIDTH = 117;
                public static final Integer HEIGHT = 155;

                private IN_HEND() {/* do not use */ }
            }

            public static final class LIDER {
                public static final Integer WIDTH = 208;
                public static final Integer HEIGHT = 302;

                private LIDER() {/* do not use */ }
            }

            public static final class GAME {
                public static final Integer WIDTH = 132;
                public static final Integer HEIGHT = 191;

                private GAME() {/* do not use */ }
            }

            public static final class TRASH {
                public static final Integer WIDTH = 144;
                public static final Integer HEIGHT = 210;

                private TRASH() {/* do not use */ }
            }

            public static final class FOR_ANIMATION {
                public static final Integer WIDTH = 624;
                public static final Integer HEIGHT = 906;

                private FOR_ANIMATION(){/* do not use*/}
            }
        }

        public static final class CARDS_IN_HEND {
            public static final Integer WIDTH = 1377;
            public static final Integer HEIGHT = 165;

            private CARDS_IN_HEND() {/* do not use */ }
        }
    }

    public static final class PADDING {
        private PADDING() {/* do not use */ }

        public static final class CARDS_IN_HEND {
            public static final Integer LEFT = 230;
            public static final Integer BOTTOM = 10;
            public static final Integer RIGHT = 324;

            private CARDS_IN_HEND() {/* do not use */ }
        }

        public static final class CARD {
            private CARD() {/* do not use */ }

            public static final class LIDER {
                public static final Integer LEFT = 10;

                private LIDER() {/* do not use */ }

                public static final class FRIENDLY {
                    public static final Integer BOTTOM = 190;

                    private FRIENDLY() {/* do not use */ }

                }

                public static final class ENEMY {
                    public static final Integer TOP = 68;

                    private ENEMY() {/* do not use */ }

                }
            }

        }

        public static final class CIRCLE_SICE {
            public static final Integer LEFT = 63;
            public static final Integer TOP = 410;

            private CIRCLE_SICE() {/* do not use */ }

        }

        public static final class TRASH {
            public static final Integer RIGHT_FIRST = 10;
            public static final Integer RIGHT_SECOND = 167;

            private TRASH() {/* do not use */ }

            public static final class FRIENDLY {
                public static final Integer BOTTOM = 265;
                public static final Integer TOP = 607;

                private FRIENDLY() {/* do not use */ }

            }

            public static final class ENEMY {
                public static final Integer BOTTOM = 745;
                public static final Integer TOP = 131;

                private ENEMY() {/* do not use */ }

            }

        }

        public static final class FORCE_COUNTER {

            public static final Integer RIGHT = 278;

            private FORCE_COUNTER() {/* do not use */ }

            public static final class FRIENDLY {
                public static final Integer TOP = 520;
                public static final Integer BOTTOM = 518;

                private FRIENDLY() {/* do not use */ }


            }

            public static final class ENEMY {
                public static final Integer TOP = 380;
                public static final Integer BOTTOM = 645;

                private ENEMY() {/* do not use */ }


            }
        }

        public static final class LIFE {
            public static final Integer RIGHT_FIRST = 100;
            public static final Integer RIGHT_SECOND = 160;

            private LIFE() {/* do not use */ }

            public static final class FRIENDLY {
                public static final Integer TOP = 518;
                public static final Integer BOTTOM = 510;

                private FRIENDLY() {/* do not use */ }

            }

            public static final class ENEMY {
                public static final Integer TOP = 386;
                public static final Integer BOTTOM = 641;

                private ENEMY() {/* do not use */ }

            }
        }

        public static final class GAME {
            public static final Integer LEFT_FIRST = 270;
            public static final Integer LEFT_SECOND = 720;
            public static final Integer LEFT_THERT = 1170;

            private GAME() {/* do not use */ }


            public static final class FRIENDLY {
                public static final Integer BOTTOM_FIRST = 420;
                public static final Integer BOTTOM_SECOND = 200;

                private FRIENDLY() {/* do not use */ }

            }

            public static final class ENEMY {
                public static final Integer TOP_FIRST = 290;
                public static final Integer TOP_SECOND = 70;

                private ENEMY() {/* do not use */ }

            }
        }
    }
}
