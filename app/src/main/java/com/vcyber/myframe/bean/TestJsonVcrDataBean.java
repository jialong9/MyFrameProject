package com.vcyber.myframe.bean;

import java.util.List;

/**
 * description ：
 * author : zjl
 * date : 9/9/21
 */
public class TestJsonVcrDataBean {
    private int code;
    private Data data;

    private static class Data {

        private long question_id;
        private SubjectInfo subject_info;

        private static class SubjectInfo {
            private String _id;
            private String content_type;
            private List<ErrorAnswer> error_answer;
            private String play_url;
            private String question;
            private String subject_name;

            private static class ErrorAnswer {
                private String answer_id;
                private String answer_name;
            }
        }
    }
} 