package com.vcyber.myframe.bean;

import java.util.List;

/**
 * description ：
 * author : zjl
 * date : 7/27/21
 */
public class TestJsonDataBean {
    private Data data;

    private static class Data {
        private GameData game_data;
        private String question_id;
        private String subject_id;

        private static class GameData {
            private String pic_url;
            private String play_url;
            private String subject_name;
            private List<AnswerList> answer_list;
            private List<IdiomAnswerList> idiom_answer_list;
            private List<PositionList> position_list;
            private List<String> idiom_data;
            private List<List<Integer>> idiom_list;

            private static class AnswerList {
                private String answer_name;
                private int is_right;
            }

            private static class IdiomAnswerList {
                private String desc;
                private long id;
                private long show_type;
            }

            private static class IdiomData {

            }

            private static class IdiomList {

            }

            private static class PositionList {
                private String desc;
                private int id;
                private int is_answer_position;
                private int position;
            }
        }
    }
}